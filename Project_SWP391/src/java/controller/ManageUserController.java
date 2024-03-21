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
import model.users;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="ManageUserController", urlPatterns={"/manage"})
public class ManageUserController extends HttpServlet {
   
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
        processRequest(request, response);
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        DAOUsers dao = new DAOUsers();
        users u = dao.getUserByID(user_id);
        request.setAttribute("user", u);
        request.getRequestDispatcher("UsersProfile.jsp").forward(request, response);
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
        users u = (users) session.getAttribute("acc");
        int userID = u.getUser_id();
        String uname = request.getParameter("user_name");
        String avatar = request.getParameter("image");
        String fname = request.getParameter("full_name");
        String phone = request.getParameter("phone_number");
        int gen = Integer.parseInt(request.getParameter("gender"));
        DAOUsers dao = new DAOUsers();
        if (dao.getUser(userID)!= null ){
            dao.UpdateUser(uname, avatar, phone, gen, fname, userID);
            session.setAttribute("managerchanged", true);
            String redirectURL = "manage?user_id=" + userID;
            response.sendRedirect(redirectURL);
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
