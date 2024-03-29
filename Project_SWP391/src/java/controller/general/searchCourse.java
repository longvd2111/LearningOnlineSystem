/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.general;

import dal.CategoryDAO;
import dal.CourseDAO;
import dal.Impl.CategoryDAOImpl;
import dal.Impl.CourseDAOImpl;
import dal.Impl.PriceCourseDAOImpl;
import dal.PriceCourseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Course;
import model.categories;
import model.price_package;
import utils.Constans;
import static utils.Constans.PAGINATION_DEFAULT_PAGE_SIZE;

/**
 *
 * @author MH
 */
@WebServlet(name = "searchCourse", urlPatterns = {"/searchCourse"})
public class searchCourse extends HttpServlet {

    private static final String VIEW_PATH = "/general/search-course.jsp";

    private final CourseDAO daoCourse = new CourseDAOImpl();

    private final CategoryDAO daoCate = new CategoryDAOImpl();

    private final PriceCourseDAO daoPrice = new PriceCourseDAOImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String cateIDs = (request.getParameter("cateID") == null || request.getParameter("cateID").isEmpty()) ? "0" : request.getParameter("cateID");
        int cateID = Integer.parseInt(cateIDs);
        request.setAttribute("cateID", cateID);

        String courseKeyword = request.getParameter("courseKeyword").trim() == null ? "" : request.getParameter("courseKeyword").trim();
        request.setAttribute("courseKeyword", courseKeyword);

        String filter = (request.getParameter("filter") == null||request.getParameter("filter").isEmpty()) ? "latest" : request.getParameter("filter");

        String pageIndexS = (request.getParameter("page")==null||request.getParameter("page").isEmpty())?"1":request.getParameter("page");
        int pageIndex = Integer.parseInt(pageIndexS);
        request.setAttribute("pageIndex", pageIndex);

        int sale;
        String saleS = request.getParameter("sale");
        if (saleS == null || saleS.equals("")) {
            sale = 0;
        } else {
            sale = Integer.parseInt(saleS);
        }

        request.setAttribute("sale", sale);

        StringBuilder sql = new StringBuilder("SELECT c.*\n"
                + "FROM courses c\n"
                + "INNER JOIN (\n"
                + "    SELECT course_id, MAX(price) AS max_price\n"
                + "    FROM price_package\n"
                + "    WHERE status_sale =");
        if (sale == 0) {
            sql.append(0).append(" or status_sale = 1 and  status = 1 GROUP BY course_id\n"
                    + ") AS p ON c.course_id = p.course_id\n"
                    + "WHERE c.status = 1 ");
        } else {
            sql.append(sale).append(" and  status = 1 GROUP BY course_id\n"
                    + ") AS p ON c.course_id = p.course_id\n"
                    + "WHERE c.status = 1 ");
        }
        if (cateID >= 1) {
            sql.append(" and c.category_id =").append(cateID).append("and");
        } else {
            sql.append(" and");
        }
        sql.append(" c.name like CONCAT('%','").append(courseKeyword).append("','%') ");

        if (filter.equals("latest")) {
            sql.append(" order by c.created_at desc");
            request.setAttribute("filter", filter);
        }
        if (filter.equals("desc")) {
            sql.append(" order by p.max_price desc");
            request.setAttribute("filter", filter);
        }
        if (filter.equals("asc")) {
            sql.append(" order by p.max_price asc");
            request.setAttribute("filter", filter);

        }

        int count = daoCourse.findAll(sql.toString()).size();
        request.setAttribute("numberOfCourse", count);

        sql.append(" offset (").append(pageIndex).append("-1)*").append(PAGINATION_DEFAULT_PAGE_SIZE).append(" ROW FETCH NEXT ").append(PAGINATION_DEFAULT_PAGE_SIZE).append(" ROWS only;");

        List<Course> listCourse = daoCourse.findAll(sql.toString());

        int endPage = 0;

        endPage = count / PAGINATION_DEFAULT_PAGE_SIZE;
        if (count % PAGINATION_DEFAULT_PAGE_SIZE != 0) {
            endPage = endPage + 1;
        }
        if (pageIndex > endPage) {
            pageIndex = endPage;
        }

        request.setAttribute("endPage", endPage);
        request.setAttribute("listCourse", listCourse);

        List<categories> listCate = daoCate.getAllCategory();
        request.setAttribute("listCate", listCate);
        
        Course FCourse = daoCourse.getCourseBestSeller();
            
            request.setAttribute("FCourse", FCourse);

        List<price_package> listPrice = daoPrice.getAllPricePackageMin();
        request.setAttribute("listPrice", listPrice);

        request.getRequestDispatcher(VIEW_PATH).forward(request, response);

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

        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
