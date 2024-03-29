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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.categories;

import model.posts;

/**
 *
 * @author caomi
 */
@WebServlet(name = "BlogList2Controller", urlPatterns = {"/BlogList2"})
public class BlogList2Controller extends HttpServlet {

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
        String cateId = request.getParameter("cateId");
        String search = request.getParameter("search");
        String rpage = request.getParameter("page");
        int time = Integer.parseInt(request.getParameter("time") == null ? "1" : request.getParameter("time"));
        int cid = -1;
        if (cateId == null) {
            cid = -1;
        } else {
            cid = Integer.parseInt(cateId);
        }

        DAOPost daopost = new DAOPost();
        DAOCategory daoCate = new DAOCategory();
        List<posts> listPost;
        if (cateId != null && search != null && Integer.parseInt(cateId) != -1) {
            listPost = daopost.getSearchCatePost(search, Integer.parseInt(cateId));
        } else {
            if (cateId != null && Integer.parseInt(cateId) != -1) {
                listPost = daopost.getPostByCategory(Integer.parseInt(cateId));
            } else if (search != null && Integer.parseInt(cateId) == -1) {
                listPost = daopost.getSearchPost(search);
            } else {
                listPost = daopost.getAllPost();
            }
        }

        List<posts> listPostActive = new ArrayList<posts>();

        if (listPost != null) {
            for (posts post : listPost) {
                if (post.getIs_feature() == 1) {
                    listPostActive.add(post);
                }
            }
        }

        if (time == 1) {
            if (listPostActive != null) {
                Collections.reverse(listPostActive);
            }
        }

        List<categories> listCategory = daoCate.getAllCategory();
        List<posts> listPostByPage;
        int endPage = 1;
        if (!listPostActive.isEmpty()) {
            int page = rpage != null ? Integer.parseInt(rpage) : 1;
            int pageSize = 4;
            listPostByPage = new ArrayList<posts>();
            for (int i = (page - 1) * pageSize; i < (page - 1) * pageSize + pageSize; i++) {
                listPostByPage.add(listPostActive.get(i));
                if (i == (listPostActive.size() - 1)) {
                    break;
                }
            }

            endPage = ((listPostActive.size() - 1) / pageSize) + 1;
        } else {
            endPage = 1;
            listPostByPage = null;
        }
        
        DAOUsers daoUser = new DAOUsers();
        if (listPostByPage != null) {
            for (posts post : listPostByPage) {
                String authorName = daoUser.getUserByID(post.getAuthor_id()).getFullname();
                post.setAuthorName(authorName);
            }
        }
        if (search == null) {
            search = "";
        }
        request.setAttribute("time", time);
        request.setAttribute("search", search);
        request.setAttribute("cid", cid);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listPost", listPostByPage);
        request.setAttribute("listCategory", listCategory);
        request.getRequestDispatcher("BlogList2.jsp").forward(request, response);

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
