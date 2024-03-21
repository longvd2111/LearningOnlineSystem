/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOCategory;

import dal.DAOPost;
import dal.DAOSlider;
import dal.DAOUsers;
import dal.DAOuserCourse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Course;
import model.categories;
import model.posts;
import model.slider;

/**
 *
 * @author caomi
 */
@WebServlet(name = "HomeController", urlPatterns = {"/Home"})
public class HomeController extends HttpServlet {

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
        /* TODO output your page here. You may use following sample code. */
            int page = 0;
            String p = request.getParameter("p");
            if (p == null) {
                page = 1;
            } else {
                page = Integer.parseInt(p);
            }

            int pageSize = 6;
            DAOPost daopost = new DAOPost();
            DAOuserCourse daoUCourse = new DAOuserCourse();
            List<Course> listCourse = daoUCourse.getCourseTop6BestSeller();
            List<posts> listPost = daopost.getAllHotPost();
            List<Course> listCourseByPage = new ArrayList<Course>();
            for (int i = (page - 1) * pageSize; i < (page - 1) * pageSize + pageSize; i++) {
                listCourseByPage.add(listCourse.get(i));
                if (i == listCourse.size() - 1) {
                    break;
                }
            }
            List<posts> listHotPost = new ArrayList<posts>();
            List<posts> listLastest = daopost.getPostOrder();
            List<posts> listLastestPost = new ArrayList<posts>();
            Collections.reverse(listLastest);

            for (int i = 0; i < 4; i++) {
                if (i == listPost.size()) {
                    break;
                }
                listHotPost.add(listPost.get(i));
            }
            for (int i = 0; i < 4; i++) {
                if (i == listLastest.size()) {
                    break;
                }
                listLastestPost.add(listLastest.get(i));
            }

            DAOSlider daoSlider = new DAOSlider();
            List<slider> listSlider = daoSlider.getAllActiveSlider();
            DAOUsers daoUser = new DAOUsers();
            for (posts post : listHotPost) {
                String authorName = daoUser.getUserByID(post.getAuthor_id()).getFullname();
                post.setAuthorName(authorName);
            }
            for (posts post : listLastestPost) {
                String authorName = daoUser.getUserByID(post.getAuthor_id()).getFullname();
                post.setAuthorName(authorName);
            }
            request.setAttribute("listSlider", listSlider);
            request.setAttribute("listCourse", listCourseByPage);
            request.setAttribute("listPost", listHotPost);
            request.setAttribute("listLastestPost", listLastestPost);
            request.getRequestDispatcher("Home.jsp").forward(request, response);
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
        processRequest(request, response);
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
