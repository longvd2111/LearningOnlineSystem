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
@WebServlet(name = "SearchPricePackage", urlPatterns = {"/SearchPricePackage"})
public class SearchPricePackage extends HttpServlet {

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

        List<price_package> listPrice = daoPrice.getPricePackageByCourseID(courseId);

        String priceKeyword = request.getParameter("priceKeyword") == null ? "" : request.getParameter("priceKeyword");

        request.setAttribute("priceKeyword", priceKeyword);

        int duration;
        String durationParam = request.getParameter("duration");
        if (durationParam == null || durationParam.isEmpty()) {
            duration = -1;
        } else {
            duration = Integer.parseInt(durationParam);
        }
        request.setAttribute("duration", duration);

        int status;
        String statusParam = request.getParameter("status");
        if (statusParam == null || statusParam.isEmpty()) {
            status = 1;
        } else {
            status = Integer.parseInt(statusParam);
        }
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
        
                String pageIndexS = request.getParameter("page") == null || request.getParameter("page") .isEmpty() ? "1" : request.getParameter("page");
        int pageIndex = Integer.parseInt(pageIndexS);
        request.setAttribute("pageIndex", pageIndex);

        int endPage = 0;
        
        int numberOfPk = daoPrice.countPricePackageStatus(status, courseId);
        endPage = numberOfPk / PAGINATION_DEFAULT_PAGE_SIZE;
        if (numberOfPk % PAGINATION_DEFAULT_PAGE_SIZE != 0) {
            endPage = endPage + 1;
        }
        if (pageIndex > endPage &&endPage > 0) {
            pageIndex = endPage;
        }

        System.out.println("number"+numberOfPk);
        request.setAttribute("numberOfPk", numberOfPk);
        request.setAttribute("endPage", endPage);

        String message = "";
        request.setAttribute("message", message);

        request.setAttribute("listPrice", listPrice);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
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
