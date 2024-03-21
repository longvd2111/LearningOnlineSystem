/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.CourseDAO;
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
import java.util.stream.Collectors;
import model.Course;
import model.price_package;
import static utils.Constans.PAGINATION_DEFAULT_PAGE_SIZE;

/**
 *
 * @author MH
 */
@WebServlet(name = "pricePackageView", urlPatterns = {"/pricePackageView"})
public class pricePackageView extends HttpServlet {

    private final CourseDAO daoCourse = new CourseDAOImpl();

    private final PriceCourseDAO daoPrice = new PriceCourseDAOImpl();

    private static final String VIEW_PATH = "/Dashboard/admin/PricePackage.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        request.setAttribute("action", action);
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = daoCourse.getCourseByCourseId(courseId);
        request.setAttribute("course", course);
        int endPage = 0;

        String pageIndexS = request.getParameter("page") == null ? "1" : request.getParameter("page");
        int pageIndex = Integer.parseInt(pageIndexS);
        request.setAttribute("pageIndex", pageIndex);

        int numberOfPk = daoPrice.countPricePackageStatus(1, courseId);
        endPage = numberOfPk / PAGINATION_DEFAULT_PAGE_SIZE;
        if (numberOfPk % PAGINATION_DEFAULT_PAGE_SIZE != 0) {
            endPage = endPage + 1;
        }
        if (pageIndex > endPage) {
            pageIndex = endPage;
        }

        request.setAttribute("endPage", endPage);

        List<price_package> listPrice = daoPrice.getPricePackageByCourseID(courseId);

        request.setAttribute("listPrice", listPrice);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
