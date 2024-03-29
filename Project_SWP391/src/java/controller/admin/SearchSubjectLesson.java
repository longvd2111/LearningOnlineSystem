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
@WebServlet(name = "SearchSubjectLesson", urlPatterns = {"/SearchSubjectLesson"})
public class SearchSubjectLesson extends HttpServlet {

    private static final String VIEW_PATH = "/Dashboard/admin/LessonList.jsp";

    private final LessonDAO daoLesson = new LessonDAOImpl();

    private final CourseDAO daoCourse = new CourseDAOImpl();

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
        
        String pageIndexS = request.getParameter("page") == null  || request.getParameter("page").isEmpty() ? "1" : request.getParameter("page");
        pageIndex = Integer.parseInt(pageIndexS);
        request.setAttribute("pageIndex", pageIndex);
        


        int chapterID = Integer.parseInt(request.getParameter("chapterId"));
        chapters chapter = daoLesson.getChapterByChapterId(chapterID);
        request.setAttribute("chapter", chapter);

        int status = Integer.parseInt(request.getParameter("status") == "" ? "1" : request.getParameter("status"));
        request.setAttribute("status", status);

        int numberOfLesson = daoLesson.countLessonByChapterID(chapterID, status);
        request.setAttribute("numberOfLesson", numberOfLesson);

        int endPage = 0;

        endPage = numberOfLesson / PAGINATION_DEFAULT_PAGE_SIZE;
        if (numberOfLesson % PAGINATION_DEFAULT_PAGE_SIZE != 0) {
            endPage = endPage + 1;
        }
        if (pageIndex > endPage && endPage > 0 ) {
            pageIndex = endPage;
        }

        request.setAttribute("endPage", endPage);

        List<lessons> listLesson = daoLesson.getLessonsByChapterID(chapterID);

        String keywordLesson = request.getParameter("keywordLesson");
        request.setAttribute("keywordLesson", keywordLesson);

        listLesson = listLesson.stream().filter(lesson -> lesson.getStatus() == status)
                .filter(lesson -> lesson.getLesson_name().toLowerCase().contains(keywordLesson.toLowerCase().trim()))
                .skip((pageIndex - 1) * PAGINATION_DEFAULT_PAGE_SIZE)
                .limit(PAGINATION_DEFAULT_PAGE_SIZE)
                .collect(Collectors.toList());

        request.setAttribute("listLesson", listLesson);

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
