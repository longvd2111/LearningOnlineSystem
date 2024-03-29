/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dal.CategoryDAO;
import dal.CourseDAO;
import dal.Impl.CategoryDAOImpl;
import dal.Impl.CourseDAOImpl;
import dal.Impl.LessonDAOImpl;
import dal.Impl.PriceCourseDAOImpl;
import dal.Impl.UserDAOImpl;
import dal.LessonDAO;
import dal.PriceCourseDAO;
import dal.UserDAO;
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
import java.util.stream.Collectors;
import model.Course;
import model.categories;
import model.users;
import model.users_roles;
import static utils.Constans.PAGINATION_DEFAULT_PAGE_SIZE;

/**
 *
 * @author MH
 */
@WebServlet(name="deleteCourse", urlPatterns={"/deleteCourse"})
public class deleteCourse extends HttpServlet {
   
     private static final String VIEW_PATH = "/Dashboard/admin/SubjectsList.jsp";

    private final CourseDAO daoCourse = new CourseDAOImpl();
    
    private final CategoryDAO daoCate = new CategoryDAOImpl();
    
    private final PriceCourseDAO daoPrice = new PriceCourseDAOImpl();
    
    private final UserDAO daoUser = new UserDAOImpl();
    
    private final LessonDAO daoLesson = new LessonDAOImpl();

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        
        String action = request.getParameter("action");
        request.setAttribute("action", action);
        
        users acc = (users) session.getAttribute("acc");
        users_roles userRole = (users_roles)session.getAttribute("userRole");
        
        String message = "";
        //start
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        if(daoCourse.countCourseUserRegisted(courseId)>0){
            message = "Delete this course failed! There are still people learning this course.";
        }
        else{
            daoPrice.deletePricePackageByCourseID(courseId);
            daoLesson.deleteLessonByCourseID(courseId);
            daoLesson.deleteChapterByCourseID(courseId);
            daoCourse.deleteCourse(courseId);
            message = "Delete this course successfull!";
        }
        
        //
        request.setAttribute("message", message);
        
        List<categories> listCate = daoCate.getAllCategory();
        request.setAttribute("listCate", listCate);
        int numberOfCourse=0;
        
        List<Course> listCourse = new ArrayList<Course>();
        if (userRole.getRole_id()==1) {
            listCourse = daoCourse.getAllCourseActiveAdmin();
            numberOfCourse= listCourse.size();
        }
        
        else if(userRole.getRole_id()==4){
            listCourse = daoCourse.getCourseActiveByExpertId(acc.getUser_id());
            numberOfCourse= listCourse.size();
        }
               
        List<users> listUserByRole = daoUser.getUserByRole(4);
        request.setAttribute("listUserByRole", listUserByRole);
        
        int pageIndex = 1;
        request.setAttribute("pageIndex", pageIndex);  
        
        listCourse = listCourse.stream()
                .skip(0) 
                .limit(PAGINATION_DEFAULT_PAGE_SIZE)
                .collect(Collectors.toList());
        request.setAttribute("listCourse", listCourse);
                    
        
        
        int endPage = 0;
        
            endPage= numberOfCourse/PAGINATION_DEFAULT_PAGE_SIZE;
            if (numberOfCourse % PAGINATION_DEFAULT_PAGE_SIZE != 0) {
                endPage = endPage + 1;
            }
            if (pageIndex > endPage) {
                pageIndex = endPage;
            }
        request.setAttribute("pageIndex", pageIndex);
        
        request.setAttribute("endPage", endPage);
        
        List<users> listUser = daoUser.getUser();
        request.setAttribute("listUser", listUser);   
        request.setAttribute("numberOfCourse", numberOfCourse);
               
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
