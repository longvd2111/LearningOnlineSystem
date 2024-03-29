/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CategoryDAO;
import dal.CourseDAO;
import dal.DAOCourseRegister;
import dal.DAOUsers;
import dal.Impl.CategoryDAOImpl;
import dal.Impl.CourseDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Course;
import model.users;

/**
 *
 * @author admin
 */
@WebServlet(name="CourseRegister", urlPatterns={"/CourseRegister"})
public class CourseRegister extends HttpServlet {
   
    private final CourseDAO daoCourse = new CourseDAOImpl();   
    
    private final CategoryDAO daoCate = new CategoryDAOImpl();


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
       
            String courseId = request.getParameter("courseid");

            // Validate courseId to ensure it is not null or empty
            if (courseId != null && !courseId.isEmpty()) {
                try {
                    int courseIdInt = Integer.parseInt(courseId); // Chuyển đổi thành số nguyên

                    HttpSession session = request.getSession();
                    users user = (users) session.getAttribute("acc");
 
                    if (user != null && user.getEmail() != null) {
                        
                        String userEmail = user.getEmail();
                        DAOCourseRegister daoCourseRegister = new DAOCourseRegister();
                        DAOUsers daoUsers = new DAOUsers();
                         
                                              
                        Course course = daoCourse.getCourseByCourseId(courseIdInt);
                   

                        int userId = user.getUser_id();
 
                        // Log thông tin
                        System.out.println("Course ID from request: " + courseId);
                        System.out.println("User ID from session: " + userId);
                        System.out.println("Course information: " + course);

                        boolean registrationSuccess = daoCourseRegister.registerUserForCourse(userId, courseIdInt);

                        // Log kết quả đăng ký
                        System.out.println("Registration Success: " + registrationSuccess);

                        request.setAttribute("registrationStatus", registrationSuccess);
                        request.setAttribute("detail", course);
                        request.getRequestDispatcher("CourseDetails.jsp").forward(request, response);
                    } else {
                        request.setAttribute("registrationStatus", "userNotLoggedIn");
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("registrationStatus", "invalidCourseId");
                
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
