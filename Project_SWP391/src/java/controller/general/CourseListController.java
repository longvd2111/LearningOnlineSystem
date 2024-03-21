/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.general;

import dal.CategoryDAO;
import dal.CourseDAO;
import dal.Impl.CategoryDAOImpl;
import dal.Impl.CourseDAOImpl;
import dal.Impl.PriceCourseDAOImpl;
import dal.PriceCourseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

import java.util.List;
import java.util.Locale;
import model.Course;
import model.LowestPrice;
import model.categories;

import model.price_package;
import model.sub_categories;

/**
 *
 * @author MH
 */
@WebServlet(name = "CourseListController", urlPatterns = {"/CourseListURL"})
public class CourseListController extends HttpServlet {
    

    
    private static final String VIEW_PATH = "/general/search-course.jsp";

    private final CourseDAO daoCourse = new CourseDAOImpl();
    
    private final CategoryDAO daoCate = new CategoryDAOImpl();
    
    private final PriceCourseDAO daoPrice = new PriceCourseDAOImpl();
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            
            
            HttpSession session = request.getSession();
            
            List<price_package> listPrice = daoPrice.getAllPricePackage();
            
            //lay catecid
            String SelectedCate = request.getParameter("category");
            int cateID;
            if (SelectedCate == null || SelectedCate.equals("")) {
                cateID = 0;
            } else {
                cateID = Integer.parseInt(SelectedCate);
            }

            
            List<categories> listCate = daoCate.getAllCategory();

            //paginated
            int pageSize = 4;
            int pageIndex = 1;
            int endPage;
            String pageString = request.getParameter("page");
            if (pageString != null) {
                pageIndex = Integer.parseInt(pageString);
            }
            if (pageIndex < 1) {
                pageIndex = 1;
            }
            
            int count = daoCourse.CountAllCourse();
            endPage= count/pageSize;
            request.setAttribute("numberOfCourse", count);
            
            if (count % pageSize != 0) {
                endPage = endPage + 1;
            }
            if (pageIndex >= endPage) {
                pageIndex = endPage;
            }
            List<Course> listCourse = daoCourse.getAllCourse(pageIndex, pageSize);
            
            //Fearture
            
            
            
            request.setAttribute("listPrice", listPrice);
            request.setAttribute("pageIndex", pageIndex);
            request.setAttribute("endPage", endPage);
            request.setAttribute("listCourse", listCourse);
            request.setAttribute("listCate", listCate);
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);

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
        processRequest(request, response);
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
