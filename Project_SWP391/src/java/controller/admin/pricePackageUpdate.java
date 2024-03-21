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
        int pk_id= Integer.parseInt(request.getParameter("pk_id"));
        String name = request.getParameter("pk_name");
        int durationPk = Integer.parseInt(request.getParameter("pk_duration"));
        int pricePk = Integer.parseInt(request.getParameter("pk_price"));
        int salePrice = Integer.parseInt(request.getParameter("pk_saleprice"));
        String statusSaleString = request.getParameter("pk_statusSale");
        int statusSale = Integer.parseInt(request.getParameter("pk_statusSale")) ;
        int statusPk = Integer.parseInt(request.getParameter("pk_status")) ;

        int countStatusActive = 0;
        if (statusPk == 1) {
            countStatusActive = daoPrice.countPricePackageStatus(statusPk, courseId);
        }
        else if(statusPk == 0)
        {
            countStatusActive = daoPrice.countPricePackageStatus(1, courseId);
        }
        message = "At one time, there can only be a maximum of 3 price packages active.";
        
        
        if (countStatusActive <= 3 && countStatusActive >1) {
        if (pricePk > salePrice) {
            daoPrice.updatePricePackage(new price_package(pk_id, courseId, name, pricePk, salePrice, durationPk, statusSale, statusPk));
            message = "Update successfull!";

        } else {
            message = "Update failed! Sale price need cheaper than normal price.";
        }
        }
        //end add

        List<price_package> listPrice = daoPrice.getPricePackageByCourseID(courseId);

        String priceKeyword = request.getParameter("priceKeyword") == null ? "" : request.getParameter("priceKeyword");

        request.setAttribute("priceKeyword", priceKeyword);

        int duration = Integer.parseInt(request.getParameter("duration") == null ? "-1" : request.getParameter("duration"));
        request.setAttribute("duration", duration);

        int status = Integer.parseInt(request.getParameter("status") == null ? "1" : request.getParameter("status"));
        request.setAttribute("status", status);

        

        if (duration > 0) {

            switch (duration) {
                case 1:
                    listPrice = listPrice.stream()
                            .filter(price
                                    -> price.getName().trim().toLowerCase().contains(priceKeyword.toLowerCase().trim())
                            && price.getStatus() == status
                            && price.getDuration() >= 1
                            && price.getDuration() <= 3
                            )
                            .collect(Collectors.toList());
                    break;
                case 2:
                    listPrice = listPrice.stream()
                            .filter(price -> price.getName().trim().toLowerCase().contains(priceKeyword.toLowerCase().trim())
                            && price.getStatus() == status
                            && price.getDuration() >= 3
                            && price.getDuration() <= 6
                            )
                            .collect(Collectors.toList());
                    break;
                case 3:
                    listPrice = listPrice.stream()
                            .filter(price -> price.getName().toLowerCase().contains(priceKeyword.toLowerCase().trim())
                            && price.getStatus() == status
                            && price.getDuration() >= 6
                            && price.getDuration() <= 12
                            )
                            .collect(Collectors.toList());
                    break;
                case 0:
                    listPrice = listPrice.stream()
                            .filter(price -> price.getName().trim().toLowerCase().contains(priceKeyword.toLowerCase().trim())
                            && price.getStatus() == status
                            && price.getDuration() == 0
                            )
                            .collect(Collectors.toList());
                    break;
                default:

                    break;

            }
        } else {
            listPrice = listPrice.stream()
                    .filter(price -> price.getName().trim().toLowerCase().contains(priceKeyword.toLowerCase().trim())
                    && price.getStatus() == status
                    ).collect(Collectors.toList());
        }

        request.setAttribute("message", message);

        

        request.setAttribute("listPrice", listPrice);

        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
