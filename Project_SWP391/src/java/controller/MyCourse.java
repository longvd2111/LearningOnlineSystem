/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;


import dal.DAOUsers;
import dal.DAOuserCourse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;
import model.Course;
import model.user_course;
import model.users;

/**
 *
 * @author admin
 */
@WebServlet(name="MyCourse", urlPatterns={"/MyCourse"})
public class MyCourse extends HttpServlet {
   
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
        try (PrintWriter out = response.getWriter()) {
        String indexPage = request.getParameter("index");
        int index = 1;
        if (indexPage != null && !indexPage.isEmpty()) {
            index = Integer.parseInt(indexPage);
        }
        

        HttpSession session = request.getSession();
        String sortBy = (String) session.getAttribute("sortBy");
        String sortOrder = (String) session.getAttribute("sortOrder");


        String newSortBy = request.getParameter("sort");
        String newSortOrder = request.getParameter("order");
        if (newSortBy != null && !newSortBy.equals(sortBy)) {
            sortBy = newSortBy;
            session.setAttribute("sortBy", sortBy);
        }
        if (newSortOrder != null && !newSortOrder.equals(sortOrder)) {
            sortOrder = newSortOrder;
            session.setAttribute("sortOrder", sortOrder);
        }
        

        DAOuserCourse daoUsercourse = new DAOuserCourse();

        users user = (users) session.getAttribute("acc");
        
        int userid = user.getUser_id();
            System.out.println(userid);

        List<Course> allUserCourses = daoUsercourse.getAllUserCourses(userid);


        if (sortBy != null) {
            boolean ascendingOrder = sortOrder != null && sortOrder.equals("asc");
            switch (sortBy) {
                case "register_time":
                    if (ascendingOrder) {
                        allUserCourses.sort(Comparator.comparing(Course::getCreated_at));
                    } else {
                        allUserCourses.sort(Comparator.comparing(Course::getCreated_at).reversed());
                    }
                    break;

                default:

            }
        }


        int itemsPerPage = 3; 
        int start = (index - 1) * itemsPerPage;
        int end = Math.min(start + itemsPerPage, allUserCourses.size());
        List<Course> userCoursesForPage = allUserCourses.subList(start, end);

        int totalItems = allUserCourses.size();
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        request.setAttribute("usercourse", userCoursesForPage);
        request.setAttribute("endP", totalPages);
        request.getRequestDispatcher("MyCourses.jsp").forward(request, response);
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
