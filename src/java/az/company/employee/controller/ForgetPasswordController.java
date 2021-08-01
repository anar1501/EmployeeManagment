package az.company.employee.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ForgetPasswordController", urlPatterns = {"/forget-password"})
public class ForgetPasswordController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("home_url", request.getContextPath() + "/home");
        request.setAttribute("admin_url", request.getContextPath() + "/admin/admin-panel");
        request.setAttribute("employeelist_url", request.getContextPath() + "/private/employee-list");
        request.setAttribute("insert_url", request.getContextPath() + "/private/insert");
        request.setAttribute("edit_url", request.getContextPath() + "/private/edit-employee-page");
        request.setAttribute("delete_url", request.getContextPath() + "/private/delete-employee-page");
        request.setAttribute("login_url", "login");
        request.setAttribute("register_url", "register");
        request.setAttribute("selected_home", "active");

        request.getRequestDispatcher("forgetpasswordpage.jsp").forward(request, response);

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
        processRequest(request, response);
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
