/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.CourseDAO;
import dal.Impl.CourseDAOImpl;
import dal.Impl.LessonDAOImpl;
import dal.LessonDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Course;
import model.chapters;
import model.lessons;
import static utils.Constans.PAGINATION_DEFAULT_PAGE_SIZE;

/**
 *
 * @author MH
 */
@WebServlet(name = "SearchChapter", urlPatterns = {"/SearchChapter"})
public class SearchChapter extends HttpServlet {

    private static final String VIEW_PATH = "/Dashboard/admin/chapterList.jsp";

    private final LessonDAO daoLesson = new LessonDAOImpl();

    private final CourseDAO daoCourse = new CourseDAOImpl();

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    

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
        String message = "";

        String action = request.getParameter("action");
        request.setAttribute("action", action);

        int courseID = Integer.parseInt(request.getParameter("courseId"));
        Course course = daoCourse.getCourseByCourseId(courseID);
        request.setAttribute("course", course);

        int pageIndex = 1;
        String pageIndexS = request.getParameter("page") == null ? "1" : request.getParameter("page");
        if(Integer.parseInt(pageIndexS)>0){
            pageIndex = Integer.parseInt(pageIndexS);
        }
        request.setAttribute("page", pageIndex);

        
        int status = Integer.parseInt(request.getParameter("status")==""?"1":request.getParameter("status"));
        request.setAttribute("status", status);

        
        

        

        List<chapters> listChapter = daoLesson.getChapterByCourseID(courseID);
               

        String keywordChapter = request.getParameter("keywordChapter").trim() == null ? "" : request.getParameter("keywordChapter").trim();

        request.setAttribute("keywordChapter", keywordChapter);

        
        
        listChapter = listChapter.stream()
                .filter(chapter-> chapter.getStatus() == status)
                .filter(chapter->chapter.getChapter_name().toLowerCase().contains(keywordChapter.toLowerCase()))
                .collect(Collectors.toList());
        
        int numberOfChapter = listChapter.size();
        request.setAttribute("numberOfChapter", numberOfChapter);

        int endPage = 0;

        endPage = numberOfChapter / PAGINATION_DEFAULT_PAGE_SIZE;
        if (numberOfChapter % PAGINATION_DEFAULT_PAGE_SIZE != 0) {
            endPage = endPage + 1;
        }
        if (pageIndex > endPage &&endPage > 0) {
            pageIndex = endPage;
        }
        listChapter = listChapter.stream()               
                .skip((pageIndex - 1) * PAGINATION_DEFAULT_PAGE_SIZE) 
                .limit(PAGINATION_DEFAULT_PAGE_SIZE)
        .collect(Collectors.toList());
        
        request.setAttribute("endPage", endPage);

        request.setAttribute("listChapter", listChapter);

        request.setAttribute("message", message);

        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
        
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
