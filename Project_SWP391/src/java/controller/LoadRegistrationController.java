/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAOUsers;
import dal.DAOuserCourse;
import dal.Impl.CourseDAOImpl;
import dal.Impl.PriceCourseDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;
import model.price_package;
import model.user_course;
import model.users;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="LoadRegistrationController", urlPatterns={"/LoadRegistration"})
public class LoadRegistrationController extends HttpServlet {
   
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
        int courseId = Integer.parseInt(request.getParameter("course_id"));
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int price_package_id = Integer.parseInt(request.getParameter("price_package_id"));
        PriceCourseDAOImpl daoP = new PriceCourseDAOImpl();
        DAOUsers daoU = new DAOUsers();
        CourseDAOImpl daoC = new CourseDAOImpl();
        DAOuserCourse daoUC = new DAOuserCourse();
        
        
        price_package p = daoP.getPricePackageByPricePacKageID(price_package_id);
        users u = daoU.getUserByID(user_id);
        Course c = daoC.getCourseByCourseId(courseId);
        user_course uc  = daoUC.getUserCourseById(user_id, courseId);
        
        request.setAttribute("pack", p);
        request.setAttribute("user", u);
        request.setAttribute("course", c);
        request.setAttribute("user_course", uc);
        request.getRequestDispatcher("EditRegistrationList").forward(request, response);
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
