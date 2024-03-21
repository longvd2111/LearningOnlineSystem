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

/**
 *
 * @author ADMIN
 */
@WebServlet(name="ResetPasswordController", urlPatterns={"/reset"})
public class ResetPasswordController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
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
        HttpSession session = request.getSession();
        String resetTokenFromSession = (String) session.getAttribute("resetToken");
        if (resetTokenFromSession != null){
            String token = request.getParameter("token");
            if (token.equalsIgnoreCase(resetTokenFromSession)){
                request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
            } else{
                response.sendRedirect("ForgotPassword.jsp");
            }
//            String email = request.getParameter("email");
//            String pass = request.getParameter("pass");
//            String repass = request.getParameter("repass");
//            if(!pass.equals(repass)){
//                request.setAttribute("mess", "The enter password does not match");
//                request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
//            }else{
//                DAOUsers dao = new DAOUsers();
//                dao.forgotPassword(email, pass);
//                session.removeAttribute("resetToken");
//                session.removeAttribute("resetEmail");
//            }
        }
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
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("resetEmail");
            String pass = request.getParameter("pass");
            String repass = request.getParameter("repass");
            if(!pass.equals(repass)){
                request.setAttribute("mess", "The enter password does not match");
                request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
            }else{
                DAOUsers dao = new DAOUsers();
                dao.forgotPassword(email, pass);
                session.removeAttribute("resetToken");
                session.removeAttribute("resetEmail");
                response.sendRedirect("ResetPasswordSuccess.jsp");
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
