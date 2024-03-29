/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dal.CategoryDAO;
import dal.CourseDAO;
import dal.DAOCategory;
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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import model.Course;
import model.categories;
import model.users;
import model.users_roles;

@MultipartConfig
@WebServlet(name="UpdateOverviewCourse", urlPatterns={"/UpdateOverviewCourse"})
public class UpdateOverviewCourse extends HttpServlet {
   private final CourseDAO daoCourse = new CourseDAOImpl();   
    
    private final CategoryDAO daoCate = new CategoryDAOImpl();
    
    private static final String VIEW_PATH = "/Dashboard/admin/CourseDetail_Admin.jsp";
    
       private final UserDAO daoUser = new UserDAOImpl();   
    
    private final PriceCourseDAO daoPrice = new PriceCourseDAOImpl();
    
    private final LessonDAO daoLesson = new LessonDAOImpl();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String message = "";
        
        
        HttpSession session = request.getSession();
        users_roles userRole = (users_roles)session.getAttribute("userRole");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        
        String action = request.getParameter("action");
        request.setAttribute("action", action);     
        
        int expertId = 0;
        if (userRole.getRole_id()==1) {
             expertId = Integer.parseInt(request.getParameter("expertId"));
        }
        else{
            expertId = daoCourse.getCourseByCourseId(courseId).getExpert_id();
        }
        int cateID = Integer.parseInt(request.getParameter("cateID"));        
        String courseName = request.getParameter("courseName");
        String tagLine = request.getParameter("courseTagLine");
        int feature = Integer.parseInt(request.getParameter("feature"));
        int status = Integer.parseInt(request.getParameter("publish"));            
        String description = request.getParameter("description");             
        Part image =  request.getPart("image");        
        String realPath = request.getServletContext().getRealPath("/img/course");
     
        String fileName;
        if (image.getSubmittedFileName()==null||image.getSubmittedFileName().equals("")) {
            fileName = daoCourse.getCourseByCourseId(courseId).getImage();
        }else{
            fileName = Paths.get(image.getSubmittedFileName()).getFileName().toString();
        }       
       
        if (!Files.exists(Paths.get(realPath))) {
            Files.createDirectory(Paths.get(realPath));
        }

//        System.out.println(realPath+"\\"+fileName);
        image.write(realPath + "\\" + fileName);       
        Course courseUpdate = new Course(courseId, expertId, cateID, courseName, fileName, tagLine, status, description,daoCourse.getCourseByCourseId(courseId).getCreated_at() , feature);
        
        int numberOfPricePackage = daoPrice.countPricePackageStatus(status, courseId);
        int numberOfLesson = daoLesson.countLessonByCourseID(courseId);
        
        
        
            List<users> listUserByRole = daoUser.getUserByRole(4);
            List<categories> listCate = daoCate.getAllCategory();
            
            request.setAttribute("listUserByRole", listUserByRole);
            request.setAttribute("listCate", listCate);

            
//        System.out.println(course);
        
        
        if (status==0) {
            daoCourse.updateCourse(courseUpdate);
            message = "Update Course success!";
        }      
        else{
            if (numberOfPricePackage>=1&&numberOfLesson>=1) {
                daoCourse.updateCourse(courseUpdate);
                message = "Update Course success!";               
            }
            else{
                message = "This course needs to have at least one price package and one lesson to be activated.";
            }
        }
        
        Course course = daoCourse.getCourseByCourseId(courseId);
        request.setAttribute("course", course);
        request.setAttribute("message", message);
        
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
        
        
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
