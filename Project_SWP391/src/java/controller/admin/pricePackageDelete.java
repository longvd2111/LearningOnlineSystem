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
                message = "There must be at least one active price package";
            } else {
                daoPrice.deletePricePackage(pk_id);
                message = "Delete successful!";
            }

        } else {

            daoPrice.deletePricePackage(pk_id);
            message = "Delete successful!";
        }

        //end delete
        List<price_package> listPrice = daoPrice.getPricePackageByCourseID(courseId);

        String priceKeyword = request.getParameter("priceKeyword") == null ? "" : request.getParameter("priceKeyword");

        request.setAttribute("priceKeyword", priceKeyword);

        int duration = Integer.parseInt(request.getParameter("duration") == null ? "-1" : request.getParameter("duration"));
        request.setAttribute("duration", duration);

        int status = Integer.parseInt(request.getParameter("status") == null ? "1" : request.getParameter("status"));
        request.setAttribute("status", status);

        System.out.println("k" + priceKeyword);
        System.out.println("d" + duration);
        System.out.println("s" + status);

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

        for (price_package object : listPrice) {
            System.out.println(object.toString());
        }

        request.setAttribute("listPrice", listPrice);

        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
