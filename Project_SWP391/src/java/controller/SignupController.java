/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAOUsers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="SignupController", urlPatterns={"/signup"})
public class SignupController extends HttpServlet {
   
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
        response.setContentType("text/html;charset=UTF-8");
         
        
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
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email");
        String uname = request.getParameter("user_name");
        String fname = request.getParameter("full_name");
        String phone = request.getParameter("phone_number");
        Date creatAt = Date.valueOf(request.getParameter("created_at"));
        int gen = Integer.parseInt(request.getParameter("gender"));
        String password = request.getParameter("pass");
        String repass = request.getParameter("repass");
        DAOUsers dao = new DAOUsers();        
        if (password.equals(repass)){
            if (dao.checkEmailExist(email) == null){
                dao.signUp(uname, email, phone, gen, password, gen, fname, creatAt,"");
                String verificationToken = UUID.randomUUID().toString();
                String verificationLink = "http://localhost:9999/Project_SWP391/verify?token="+verificationToken;
                HttpSession session = request.getSession();
                session.setAttribute("verificationToken", verificationToken);
                session.setMaxInactiveInterval(180);
                session.setAttribute("email", email);

                utils.UtilSignup.sendSignUpEmail(email, verificationLink);
                response.sendRedirect("SignupEmailSuccess.jsp");
            }else{
                request.setAttribute("existed", "This email has been registered");
                request.getRequestDispatcher("SignUp.jsp").forward(request, response);
            }
 
        } else {
            request.setAttribute("mess", "Password is not match");
            request.getRequestDispatcher("SignUp.jsp").forward(request, response);
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
