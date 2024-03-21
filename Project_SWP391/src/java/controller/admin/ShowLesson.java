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
import java.util.stream.Stream;
import model.Course;
import model.chapters;
import model.lessons;
import static utils.Constans.PAGINATION_DEFAULT_PAGE_SIZE;


@WebServlet(name="ShowLesson", urlPatterns={"/ShowLesson"})
public class ShowLesson extends HttpServlet {
    private static final String VIEW_PATH = "/Dashboard/admin/LessonList.jsp";
    
    private final LessonDAO daoLesson = new LessonDAOImpl();
    
    private final CourseDAO daoCourse = new CourseDAOImpl();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String message = "";
            
            String action = request.getParameter("action");
            request.setAttribute("action", action);
            
            int courseID = Integer.parseInt(request.getParameter("courseId"));
            Course course = daoCourse.getCourseByCourseId(courseID);
            request.setAttribute("course", course);
            
           
            
            
            String pageIndexS = request.getParameter("page")==null?"1":request.getParameter("page");
            int pageIndex = Integer.parseInt(pageIndexS);
            request.setAttribute("pageIndex", pageIndex);
            
            
            int chapterID = Integer.parseInt(request.getParameter("chapterID"));
            chapters chapter = daoLesson.getChapterByChapterId(chapterID);
            request.setAttribute("chapter", chapter);
            
        

        int numberOfLesson = daoLesson.countLessonByChapterID(chapterID,1);
        
            
        request.setAttribute("numberOfLesson", numberOfLesson);

        
        int endPage = 0;

        endPage = numberOfLesson / PAGINATION_DEFAULT_PAGE_SIZE;
        if (numberOfLesson % PAGINATION_DEFAULT_PAGE_SIZE != 0) {
            endPage = endPage + 1;
        }
        if (pageIndex > endPage) {
            pageIndex = endPage;
        }

        request.setAttribute("endPage", endPage);

        List<lessons> listLesson = daoLesson.getLessonsByChapterID(chapterID);            
        
        listLesson = listLesson.stream().filter(lessons->lessons.getStatus()==1)
                .skip(0) 
                .limit(PAGINATION_DEFAULT_PAGE_SIZE)
                .collect(Collectors.toList());
        
        
        request.setAttribute("listLesson", listLesson);

        request.setAttribute("message", message);        
        
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
            
        }
    } 

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
