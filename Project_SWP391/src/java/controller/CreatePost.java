/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAOCategory;
import dal.DAOPost;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import model.categories;
import model.posts;
import model.users;

/**
 *
 * @author caomi
 */
@WebServlet(name="CreatePostController", urlPatterns={"/CreatePost"})
public class CreatePost extends HttpServlet {
   
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
            /* TODO output your page here. You may use following sample code. */
            
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
        DAOCategory daoCate = new DAOCategory();
        List<categories> listCategory = daoCate.getAllCategory();
        System.out.println(listCategory);
        request.setAttribute("listCategory", listCategory);
        request.getRequestDispatcher("CreatePost.jsp").forward(request, response);
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
        String content = request.getParameter("editor1");
        String title = request.getParameter("title");
        String thumbnail = request.getParameter("thumbnail");
        String cateId = request.getParameter("cateId");
        int cate = Integer.parseInt(cateId);
        int active = request.getParameter("active")==null?0:1;
        String sub_content = request.getParameter("sub_content");
        HttpSession session = request.getSession();
        users marketing = (users)session.getAttribute("acc");
        if(marketing == null){
            response.sendRedirect("CreatePost");
        }
            int author_id = marketing.getUser_id();
            
            
       
//        System.out.println(post);
        
        String formattedPost = content.replace("'", "''");
        request.setAttribute("post", formattedPost);
        
        Date date_post = new Date(System.currentTimeMillis());
        System.out.println(date_post);
        posts p = new posts(1,author_id,title,content,date_post,active,sub_content,cate,thumbnail);
        System.out.println(p.toString());
        DAOPost daoPost = new DAOPost();
        daoPost.createPost(p);
        

        DAOCategory daoCate = new DAOCategory();
        List<categories> listCategory = daoCate.getAllCategory();
        System.out.println(listCategory);
        request.setAttribute("infor", "Create post successfully.");
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("p", p);
        request.getRequestDispatcher("CreatePost.jsp").forward(request, response);
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
