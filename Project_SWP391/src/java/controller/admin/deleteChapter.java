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
@WebServlet(name="deleteChapter", urlPatterns={"/deleteChapter"})
public class deleteChapter extends HttpServlet {
   
    
    private static final String VIEW_PATH = "/Dashboard/admin/chapterList.jsp";
    
    private final LessonDAO daoLesson = new LessonDAOImpl();

    private final CourseDAO daoCourse = new CourseDAOImpl();
    

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        
        
        
        
        String message = "";

        String action = request.getParameter("action");
        request.setAttribute("action", action);

        int courseID = Integer.parseInt(request.getParameter("courseId"));
        Course course = daoCourse.getCourseByCourseId(courseID);
        request.setAttribute("course", course);

        String pageIndexS = request.getParameter("page") == null ? "1" : request.getParameter("page");
        int pageIndex = Integer.parseInt(pageIndexS);
        request.setAttribute("pageIndex", pageIndex);
        
        
         // delete start
            
            int chapterID = Integer.parseInt(request.getParameter("chapterId"));
            List<lessons> listLesson = daoLesson.getLessonsByChapterID(chapterID);
            for (lessons object : listLesson) {
                 daoLesson.deleteLessonByLessonID(object.getLesson_id());
            }
            
            daoLesson.deleteChapter(chapterID);
            
            message = "Delete Chapter successful!";
            request.setAttribute("message", message);
            
            //delete end

        int numberOfChapter = daoLesson.countChapterByCourseID(courseID);
        System.out.println(numberOfChapter);
        
        request.setAttribute("numberOfChapter", numberOfChapter);

        int endPage = 0;

        endPage = numberOfChapter / PAGINATION_DEFAULT_PAGE_SIZE;
        if (numberOfChapter % PAGINATION_DEFAULT_PAGE_SIZE != 0) {
            endPage = endPage + 1;
        }
        if (pageIndex > endPage) {
            pageIndex = endPage;
        }

        
        request.setAttribute("endPage", endPage);

        List<chapters> listChapter = daoLesson.getChapterByCourseID(courseID);
        listChapter = listChapter.stream()
                .filter(chapter->chapter.getStatus()==1)
                .skip(0) 
                .limit(PAGINATION_DEFAULT_PAGE_SIZE)
                .collect(Collectors.toList());
        
        
        for (chapters object : listChapter) {
            System.out.println(object.toString());
        }
        
        request.setAttribute("listChapter", listChapter);

        request.setAttribute("message", message);

        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
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
