/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dal.CategoryDAO;
import dal.CourseDAO;
import dal.Impl.CategoryDAOImpl;
import dal.Impl.CourseDAOImpl;
import dal.Impl.PriceCourseDAOImpl;
import dal.Impl.UserDAOImpl;
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
import java.sql.Date;



import java.util.List;
import model.Course;
import model.categories;
import model.users;
import model.users_roles;
import static utils.Constans.PAGINATION_DEFAULT_PAGE_SIZE;

/**
 *
 * @author MH
 */
@MultipartConfig
@WebServlet(name="addCourse", urlPatterns={"/addCourse"})
public class addCourse extends HttpServlet {
   
    private static final String VIEW_PATH = "/Dashboard/admin/SubjectsList.jsp";

    private final CourseDAO daoCourse = new CourseDAOImpl();

    private final CategoryDAO daoCate = new CategoryDAOImpl();

    private final PriceCourseDAO daoPrice = new PriceCourseDAOImpl();

    private final UserDAO daoUser = new UserDAOImpl();
    
    
    

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
               
       
        
        users acc = (users) session.getAttribute("acc");
        users_roles userRole = (users_roles) session.getAttribute("userRole");
        
        String pageIndexS = request.getParameter("page")==null ? "1" :request.getParameter("page");
        int pageIndex = Integer.parseInt(pageIndexS);
        request.setAttribute("pageIndex", pageIndex);
        
        int numberOfCourse = daoCourse.CountAllCourse();
        request.setAttribute("numberOfCourse", numberOfCourse);

        int endPage = 0;
        endPage = numberOfCourse / PAGINATION_DEFAULT_PAGE_SIZE;
        if (numberOfCourse % PAGINATION_DEFAULT_PAGE_SIZE != 0) {
            endPage = endPage + 1;
        }
        if (pageIndex > endPage) {
            pageIndex = endPage;
        }
        
        String courseName = request.getParameter("courseName");
        System.out.println(courseName);
        
        String tagLine = request.getParameter("courseTagLine");
        System.out.println(tagLine);
        
        int feature = Integer.parseInt(request.getParameter("feature"));
        
        
        int cateID = Integer.parseInt(request.getParameter("cate_ID"));        
                       
        
        String description = request.getParameter("description")==null?"":request.getParameter("description");             
        Part image =  request.getPart("image");        
        String realPath = request.getServletContext().getRealPath("/img/course");       
        
        String fileName;
        
        fileName = Paths.get(image.getSubmittedFileName()).getFileName().toString();
        if (fileName == null) {
            fileName="";
        }         
        if (!Files.exists(Paths.get(realPath))) {
            Files.createDirectory(Paths.get(realPath));
        }
//        System.out.println(realPath+"\\"+fileName);
        image.write(realPath + "\\" + fileName);
        
        String message="";
        Date createdAt = new Date(System.currentTimeMillis());
        
        int expertID = Integer.parseInt(request.getParameter("expertId"));
        
        daoCourse.insertCourse(new Course(0, expertID, cateID, courseName, fileName, tagLine, 0, description,createdAt , 0));
        
        int newNumberOfCourse = daoCourse.CountAllCourse();
        
        message="Add new course successful";
        
        request.setAttribute("message", message);
        
        
        request.setAttribute("endPage", endPage);
        List<Course> listCourse = daoCourse.getAllCourse(pageIndex, PAGINATION_DEFAULT_PAGE_SIZE);
        List<categories> listCate = daoCate.getAllCategory();
        request.setAttribute("listCate", listCate);
        
        List<users> listUser = daoUser.getUser();

        request.setAttribute("listUser", listUser);             
        request.setAttribute("listCourse", listCourse);

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
