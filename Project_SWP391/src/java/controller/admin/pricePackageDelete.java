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
@WebServlet(name = "pricePackageDelete", urlPatterns = {"/pricePackageDelete"})
public class pricePackageDelete extends HttpServlet {

    private final CourseDAO daoCourse = new CourseDAOImpl();

    private final PriceCourseDAO daoPrice = new PriceCourseDAOImpl();

    private static final String VIEW_PATH = "/Dashboard/admin/PricePackage.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        request.setAttribute("action", action);

        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = daoCourse.getCourseByCourseId(courseId);
        request.setAttribute("course", course);
        String message = "";

        //start delete
        int pk_id = Integer.parseInt(request.getParameter("pk_id"));
        price_package pk = daoPrice.getPricePackageByPricePacKageID(pk_id);

        if (pk.getStatus() == 1) {
            int count = daoPrice.countPricePackageStatus(1, courseId);
            if (count == 1) {
                message = "There must be at least one active price package!";
            } else {
                daoPrice.deletePricePackage(pk_id);
                message = "Delete successful!";
            }

        } else {

            daoPrice.deletePricePackage(pk_id);
            message = "Delete successful!";
        }

        //end delete
        
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
        List<price_package> listPrice = daoPrice.getPricePackageByCourseID(courseId, 1);
        request.setAttribute("listPrice", listPrice);
        
        request.setAttribute("numberOfPk", numberOfPk);
        
        request.setAttribute("message", message);


        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
