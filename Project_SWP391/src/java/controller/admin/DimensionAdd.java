/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dal.CourseDAO;
import dal.DimensionDAO;
import dal.Impl.CourseDAOImpl;
import dal.Impl.DimensionDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dimension;

/**
 *
 * @author MH
 */
@WebServlet(name="DimensionAdd", urlPatterns={"/admin/course/dimension/add"})
public class DimensionAdd extends HttpServlet {
   
    private final DimensionDAO daoDimen = new DimensionDAOImpl();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    } 

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String Dimen_id = request.getParameter("dimenId");
        int dimenId;
        if (Dimen_id==null || Dimen_id.equals("")) {
            
            dimenId=0;
        }
        else{
            dimenId = Integer.parseInt(Dimen_id);
        }
        String dimenName = request.getParameter("dimensionName");
            int cateDimenId = Integer.parseInt(request.getParameter("cate_dimension_id"));   
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            String description = request.getParameter("description");
            dimension dimen = new dimension(dimenId, courseId, dimenName, cateDimenId, description);
            daoDimen.insertDimension(dimen);
            String message = "Add Successful!";
            request.setAttribute("message", message);
            response.sendRedirect("ShowInformationCourse?courseId="+courseId);
        
    }

    
    

}
