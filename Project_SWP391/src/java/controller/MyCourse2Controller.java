/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CourseDAO;
import dal.DAOCategory;
import dal.DAOuserCourse;
import dal.Impl.CourseDAOImpl;
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
import model.posts;
import model.users;

/**
 *
 * @author caomi
 */
@WebServlet(name="MyCourse2Controller", urlPatterns={"/MyCourse2"})
public class MyCourse2Controller extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyCourse2Controller</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyCourse2Controller at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

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
        HttpSession session = request.getSession();
        users customer = (users)session.getAttribute("acc");
        String cateId = request.getParameter("cateId");
        String search = request.getParameter("search");
        String rpage = request.getParameter("page");
        int time = Integer.parseInt(request.getParameter("time") == null ? "1" : request.getParameter("time"));
        
        DAOCategory daoCate = new DAOCategory();
        DAOuserCourse daoUCourse = new DAOuserCourse();
        List<Course> listCourse = daoUCourse.getAllUserCourses(customer.getUser_id());
        
        int cid = cateId==null?-1:Integer.parseInt(cateId);
        
        
        if (cateId != null && search != null && Integer.parseInt(cateId) != -1) {
            
            listCourse = listCourse.stream().filter(course -> 
                            course.getCourse_name().trim().toLowerCase().contains(search.trim().toLowerCase())
                            && course.getCategory_id()==cid)
                    .collect(Collectors.toList());
        } else {
            if (cateId != null && Integer.parseInt(cateId) != -1) {
                listCourse = listCourse.stream().filter(course -> 
                            course.getCategory_id()==cid)
                    .collect(Collectors.toList());
            } else if (search != null && Integer.parseInt(cateId) == -1) {
                listCourse = listCourse.stream().filter(course -> 
                            course.getCourse_name().trim().toLowerCase().contains(search.trim().toLowerCase()))
                    .collect(Collectors.toList());
            }
        }
        
        List<Course> listPostByPage;
        int endPage = 1;
        if (!listCourse.isEmpty()) {
            int page = rpage != null ? Integer.parseInt(rpage) : 1;
            int pageSize = 4;
            listPostByPage = new ArrayList<Course>();
            for (int i = (page - 1) * pageSize; i < (page - 1) * pageSize + pageSize; i++) {
                listPostByPage.add(listCourse.get(i));
                if (i == (listCourse.size() - 1)) {
                    break;
                }
            }

            endPage = ((listCourse.size() - 1) / pageSize) + 1;
        } else {
            endPage = 1;
            listPostByPage = null;
        }
        List<categories> listCategory = daoCate.getAllCategory();
        
        request.setAttribute("time", time);
        request.setAttribute("search", search==null?"":search);
        request.setAttribute("cid", cid);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listCourse", listPostByPage);
        request.setAttribute("listCategory", listCategory);
        request.getRequestDispatcher("MyCourse2.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
