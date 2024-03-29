/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.chapters;
import model.lessons;
import model.users;

/**
 *
 * @author caomi
 */
@WebServlet(name = "CourseLearnController", urlPatterns = {"/CourseLearn"})
public class CourseLearnController extends HttpServlet {

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
        HttpSession session = request.getSession();
        users customer = (users) session.getAttribute("acc");
        if (customer == null) {
            response.sendRedirect("Home");
        }
        String courseId = request.getParameter("courseId");
        String lessonId = request.getParameter("lessonId");
        LessonDAO daoLesson = new LessonDAOImpl();
        lessons lessonVideo =new lessons();
        if (lessonId == null) {
            lessonVideo =null;
        }else{
            lessonVideo = daoLesson.getLessonByLessonID(Integer.parseInt(lessonId));
            if (daoLesson.lessonIsArchived(customer.getUser_id(), lessonVideo.getLesson_id())) {
                    lessonVideo.setIs_archived(1);
                    System.out.println("jis_achived"+ lessonVideo.getLesson_id());
                }
        }
        List<chapters> removeChapter = new ArrayList<>();
        List<chapters> listChapter = daoLesson.getChapterByCourseID(Integer.parseInt(courseId));
        for (chapters chapter : listChapter) {
            chapter.setListLesson(daoLesson.getLessonsByChapterID(chapter.getChapter_id()));
            List<lessons> ls = new ArrayList<>();
            for (lessons lesson : chapter.getListLesson()) {
                if(lesson.getStatus() == 0){
                    continue;
                }
                if (daoLesson.lessonIsArchived(customer.getUser_id(), lesson.getLesson_id())) {
                    lesson.setIs_archived(1);
                }
                ls.add(lesson);
            }
            if(ls.isEmpty()||chapter.getStatus()==0){
                System.out.println(chapter);
                removeChapter.add(chapter);
            }
            
            chapter.setListLesson(ls);
        }
        removeChapter.forEach(chapter -> listChapter.remove(chapter));
        CourseDAO daoCourse = new CourseDAOImpl();
        Course course = daoCourse.getCourseByCourseId(Integer.parseInt(courseId));
        if(lessonId == null){
            lessonVideo = listChapter.get(0).getListLesson().get(0);
        }
        
        int doneLesson = daoLesson.countDoneLessonByUserId(customer.getUser_id(),course.getCourse_id());
        int totalLesson = daoLesson.countLessonByCourseID(course.getCourse_id());
        request.setAttribute("doneLesson", doneLesson);
        request.setAttribute("totalLesson", totalLesson);
        
        request.setAttribute("listChapter", listChapter);
        request.setAttribute("course", course);
        request.setAttribute("lessonContent", lessonVideo);
        request.setAttribute("chapterVideoId", lessonVideo.getChapter_id());
        request.getRequestDispatcher("CourseLearn.jsp").forward(request, response);

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
