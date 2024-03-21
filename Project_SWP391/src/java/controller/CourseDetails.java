/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAOCourseRegister;
import dal.DAOuserCourse;
import dal.Impl.CourseDAOImpl;
import dal.Impl.PriceCourseDAOImpl;
import dal.PriceCourseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Course;
import model.price_package;
import model.users;
import org.apache.catalina.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="CourseDetails", urlPatterns={"/CourseDetails"})
public class CourseDetails extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String course_id = request.getParameter("courseid");
//        int price = Integer.parseInt(request.getParameter("price"));
        if (course_id != null && !course_id.isEmpty()){
            
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            users users = (users) session.getAttribute("acc");

            int courseId = Integer.parseInt(course_id);
            
            CourseDAOImpl daoCourse = new CourseDAOImpl();
            Course course = daoCourse.getCourseByCourseId(courseId);
            
            if (course != null){
                PriceCourseDAOImpl daoPrice = new PriceCourseDAOImpl();
                List<price_package> list = daoPrice.getPricePackageByCourseID(courseId);
                
//                price_package p = daoPrice.getPricePackageByCourseIDAndDuration(courseId, price);
                DAOCourseRegister daoRegis = new DAOCourseRegister();
                     int userId = (users != null) ? users.getUser_id() : 0;
                     
                   boolean isRegisted = daoRegis.checkUserRegistered(userId, courseId);
                   
                   request.setAttribute("price", list);
                   request.setAttribute("detail", course);
                   request.setAttribute("user", user);
//                   request.setAttribute("pricepackage", p);
                   request.setAttribute("isRegisted", isRegisted);
                   request.getRequestDispatcher("CourseDetails.jsp").forward(request, response);
            }
            
        }
        
        
        
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

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
        processRequest(request, response);
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
