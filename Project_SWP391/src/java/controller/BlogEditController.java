/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOCategory;
import dal.DAOPost;
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
import java.util.List;
import model.categories;
import model.posts;
import model.users;

/**
 *
 * @author caomi
 */
@WebServlet(name = "BlogEditController", urlPatterns = {"/BlogEdit"})
public class BlogEditController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BlogEditController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogEditController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String p = request.getParameter("postId");
        int postId = Integer.parseInt(p);
        DAOPost daoPost = new DAOPost();
        posts post = daoPost.getPostByPostId(postId);

        DAOCategory daoCate = new DAOCategory();
        List<categories> listCategory = daoCate.getAllCategory();
        System.out.println(post.toString());
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("post", post);
        request.getRequestDispatcher("BlogEdit.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int post_id = Integer.parseInt(request.getParameter("post_id"));
        String content = request.getParameter("editor1");
        String title = request.getParameter("title");
        String thumbnail = request.getParameter("thumbnail");
        String cateId = request.getParameter("cateId");
        int cate = Integer.parseInt(cateId);
        int active = request.getParameter("active") == null ? 0 : 1;
        String sub_content = request.getParameter("sub_content");

        String formattedPost = content.replace("'", "''");
        request.setAttribute("post", formattedPost);

        DAOPost daoPost = new DAOPost();

        posts p = daoPost.getPostByPostId(post_id);
        p.setTitle(title);
        p.setContent(content);
        p.setCategory_post_id(cate);
        p.setIs_feature(active);
        p.setSub_content(sub_content);
        p.setThumbnail(thumbnail);
        System.out.println(p.toString());

        daoPost.updatePost(p);

        DAOCategory daoCate = new DAOCategory();
        List<categories> listCategory = daoCate.getAllCategory();
        System.out.println(listCategory);
        request.setAttribute("infor", "Update post successfully.");

        request.setAttribute("listCategory", listCategory);
        response.sendRedirect("BlogManager");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
