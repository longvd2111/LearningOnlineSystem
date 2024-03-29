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

@WebServlet(name = "pricePackageUpdate", urlPatterns = {"/pricePackageUpdate"})
public class pricePackageUpdate extends HttpServlet {

    private final CourseDAO daoCourse = new CourseDAOImpl();

    private final PriceCourseDAO daoPrice = new PriceCourseDAOImpl();

    private static final String VIEW_PATH = "/Dashboard/admin/PricePackage.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        request.setAttribute("action", action);

        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = daoCourse.getCourseByCourseId(courseId);
        request.setAttribute("course", course);
        String message = "";

        //            start update
        int pk_id = Integer.parseInt(request.getParameter("pk_id"));
        String name = request.getParameter("pk_name");
        int durationPk = Integer.parseInt(request.getParameter("pk_duration"));
        int pricePk = Integer.parseInt(request.getParameter("pk_price"));
        int salePrice = Integer.parseInt(request.getParameter("pk_saleprice"));
        String statusSaleString = request.getParameter("pk_statusSale");
        int statusSale = Integer.parseInt(request.getParameter("pk_statusSale"));
        int statusPk = Integer.parseInt(request.getParameter("pk_status"));

        int countStatusActive = 0;
        if (statusPk == 1) {
            countStatusActive = daoPrice.countPricePackageStatus(statusPk, courseId);
        } else if (statusPk == 0) {
            countStatusActive = daoPrice.countPricePackageStatus(1, courseId);
        }
        message = "At one time, there can only be a maximum of 3 and at least 1 price packages active!";

        if (countStatusActive < 3 && statusPk == 1) {
            if (pricePk > salePrice) {
                daoPrice.updatePricePackage(new price_package(pk_id, courseId, name, pricePk, salePrice, durationPk, statusSale, statusPk));
                message = "Update successfull!";

            } else {
                message = "Update failed! Sale price need cheaper than normal price.";
            }
        }
        else{
            daoPrice.updatePricePackage(new price_package(pk_id, courseId, name, pricePk, salePrice, durationPk, statusSale, statusPk));
            message = "Update successfull!";
        }
        
        //end add

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
        request.setAttribute("numberOfPk", numberOfPk);
        request.setAttribute("endPage", endPage);
        List<price_package> listPrice = daoPrice.getPricePackageByCourseID(courseId, 1);
        request.setAttribute("listPrice", listPrice);
        request.setAttribute("message", message);
 
        
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
