/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAOCourse;
import dal.DAOCourseRegister;
import dal.DAOUsers;
import dal.RegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import java.util.UUID;
import model.Course;
import model.users;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="AddUserCourseController", urlPatterns={"/AddUserCourse"})
public class AddUserCourseController extends HttpServlet {
   
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
        RegistrationDAO dao = new RegistrationDAO();
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        
        Course c = dao.getCourseByCourseId(course_id);
        request.setAttribute("course", c);
        request.getRequestDispatcher("AddUserCourse.jsp").forward(request, response);
        
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
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        int gen = Integer.parseInt(request.getParameter("gender"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Date creatAt = Date.valueOf(request.getParameter("created_at"));
        String verificationToken = UUID.randomUUID().toString();
        DAOUsers dao = new DAOUsers();
        if (dao.checkEmailExist(email) == null){
            dao.signUp(name, email, phone, gen, verificationToken, 1, name, creatAt, "");
            users u = dao.getUserbyEmail(email);
            int user_id = u.getUser_id();
            DAOCourseRegister daoC = new DAOCourseRegister();
            boolean isSuccess = daoC.registerUserForCourse(user_id, courseId);
        if (isSuccess){
            response.sendRedirect("RegistrationList");
        } else {
            System.out.println("Dang ky khong thanh cong");
        }
        } else {
            DAOCourseRegister daoC = new DAOCourseRegister();
            users u = dao.getUserbyEmail(email);
            int user_id = u.getUser_id();
            boolean isSuccess = daoC.registerUserForCourse(user_id, courseId);
        if (isSuccess){
            response.sendRedirect("RegistrationList");
        } else {
            System.out.println("Dang ky khong thanh cong");
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
