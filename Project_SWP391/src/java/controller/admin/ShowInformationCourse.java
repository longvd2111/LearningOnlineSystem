/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dal.CategoryDAO;
import dal.CourseDAO;


import dal.DAOUsers;
import dal.DimensionDAO;
import dal.Impl.CategoryDAOImpl;
import dal.Impl.CourseDAOImpl;
import dal.Impl.DimensionDAOImpl;
import dal.Impl.PriceCourseDAOImpl;
import dal.Impl.UserDAOImpl;
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
import java.util.List;
import model.Course;
import model.cate_dimension;
import model.categories;
import model.dimension;
import model.price_package;
import model.users;

/**
 *
 * @author MH
 */
@WebServlet(name = "ShowInformationCourse", urlPatterns = {"/ShowInformationCourse"})
public class ShowInformationCourse extends HttpServlet {
   
    private final DimensionDAO daoDimen = new DimensionDAOImpl();
    
    private final PriceCourseDAO daoPrice = new PriceCourseDAOImpl();
    
    private final CourseDAO daoCourse = new CourseDAOImpl();   
    
    private final CategoryDAO daoCate = new CategoryDAOImpl();
    
    private static final String VIEW_PATH = "/Dashboard/admin/CourseDetail_Admin.jsp";
    
    private final UserDAO daoUser = new UserDAOImpl();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
            

            
            HttpSession session = request.getSession();
            
            String message = "";
            request.setAttribute("message", message);
            
            String action = request.getParameter("action");
            request.setAttribute("action", action);

            int courseId = Integer.parseInt(request.getParameter("courseId"));
            
            Course course = daoCourse.getCourseByCourseId(courseId);
            
            List<users> listUserByRole = daoUser.getUserByRole(4);
            List<categories> listCate = daoCate.getAllCategory();
            
            request.setAttribute("listUserByRole", listUserByRole);
            request.setAttribute("listCate", listCate);

            request.setAttribute("course", course);
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    } 

    
    
    
        

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
