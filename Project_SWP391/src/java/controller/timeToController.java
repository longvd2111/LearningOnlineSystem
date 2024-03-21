/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import RegistrationsList.RegistrationList;
import dal.RegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import model.Course;
import model.user_course;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="timeToController", urlPatterns={"/timeTo"})
public class timeToController extends HttpServlet {
   
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
        String timeToParam = request.getParameter("timeTo");
        Date timeTo = null;        
        List <RegistrationList> listT;
        
        
        // Kiểm tra xem tham số được truyền vào có giá trị hợp lệ hay không
        if (timeToParam != null && !timeToParam.isEmpty() && !timeToParam.equals("-1")) {
            try {
                // Chuyển đổi giá trị String thành kiểu Date
                timeTo = new Date(Long.parseLong(timeToParam));
            } catch (NumberFormatException e) {
                // Xử lý lỗi nếu có
                e.printStackTrace(); // Hoặc thực hiện xử lý khác tùy ý của bạn
            }
        }

        RegistrationDAO dao = new RegistrationDAO();

        if (timeTo == null){
            listT = dao.RegitrationList();
        } else {
            listT = dao.getRegitrationListBytimeTo(timeTo);
        }
        
        
        List<Course> listC = dao.getAllCourse();
        List<user_course> listU = dao.getAllUserCourse();
        request.setAttribute("listCourse", listC);
        request.setAttribute("listUserCourse", listU);
        request.setAttribute("listRegistration", listT);
        request.getRequestDispatcher("RegistrationList.jsp").forward(request, response);
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
        processRequest(request, response);
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
