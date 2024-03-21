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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Course;
import model.users;
import model.categories;
import model.users_roles;
import static utils.Constans.PAGINATION_DEFAULT_PAGE_SIZE;

/**
 *
 * @author MH
 */
@WebServlet(name = "SearchSubjectList", urlPatterns = {"/SearchSubjectList"})
public class SearchSubjectList extends HttpServlet {

    private static final String VIEW_PATH = "/Dashboard/admin/SubjectsList.jsp";

    private final CourseDAO daoCourse = new CourseDAOImpl();

    private final CategoryDAO daoCate = new CategoryDAOImpl();

    private final PriceCourseDAO daoPrice = new PriceCourseDAOImpl();

    private final UserDAO daoUser = new UserDAOImpl();

    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchSubjectList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchSubjectList at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        
        String action = request.getParameter("action");
        request.setAttribute("action", action);

        users acc = (users) session.getAttribute("acc");
        users_roles userRole = (users_roles) session.getAttribute("userRole");
        
         List<users> listUserByRole = daoUser.getUserByRole(4);
        request.setAttribute("listUserByRole", listUserByRole);

        String cateIDs = request.getParameter("cateID");
        int cateID;
        if (cateIDs == null || cateIDs.equals("")) {
            cateID = 0;
        } else {
            cateID = Integer.parseInt(cateIDs);

        }

        request.setAttribute("cateID", cateID);

        String courseKeyword = request.getParameter("courseKeyword").trim();
        request.setAttribute("courseKeyword", courseKeyword);

        String pageIndexS = request.getParameter("page");
        int pageIndex = Integer.parseInt(pageIndexS);
        request.setAttribute("pageIndex", pageIndex);

        int status;
        String statusS = request.getParameter("status");
        if (statusS == null || statusS.isEmpty()) {
            status = -1;
        } else {
            status = Integer.parseInt(statusS);
        }

        StringBuilder sql = new StringBuilder();

        sql.append("select * from courses where name like '%").append(courseKeyword).append("%' ");

        if (userRole.getRole_id() != 1) {
            sql.append(" and expert_id = ").append(acc.getUser_id());
        }

        if (status != -1) {
            sql.append("and status = ").append(status);
        }

        if (cateID != 0) {
            sql.append(" and category_id = ").append(cateID);
        }

        sql.append(" order by course_id asc");

        int numberOfCourse = daoCourse.findAll(sql.toString()).size();
        request.setAttribute("numberOfCourse", numberOfCourse);

        sql.append(" offset (").append(pageIndex).append("-1)*").append(PAGINATION_DEFAULT_PAGE_SIZE).append(" ROW FETCH NEXT ").append(PAGINATION_DEFAULT_PAGE_SIZE).append(" ROWS only;");

        int endPage = 0;

        endPage = numberOfCourse / PAGINATION_DEFAULT_PAGE_SIZE;
        if (numberOfCourse % PAGINATION_DEFAULT_PAGE_SIZE != 0) {
            endPage = endPage + 1;
        }
        if (pageIndex > endPage) {
            pageIndex = endPage;
        }
        request.setAttribute("endPage", endPage);

        List<Course> listCourse = daoCourse.findAll(sql.toString());

        List<categories> listCate = daoCate.getAllCategory();
        request.setAttribute("listCate", listCate);
        
        List<users> listUser = daoUser.getUser();

        request.setAttribute("listUser", listUser);             
        request.setAttribute("listCourse", listCourse);
        request.setAttribute("status", status);

        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
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
