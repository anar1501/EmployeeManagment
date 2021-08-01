package az.company.employee.controller;

import az.company.employee.dao.abstracts.UserDaoService;
import az.company.employee.dao.concrets.UserDaoManager;
import az.company.employee.model.concrets.User;
import az.company.employee.security.PasswordHasher;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SaveChangePassword", urlPatterns = {"/save-change-password"})
public class SaveChangePasswordController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String password = request.getParameter("password");
            String passwordRepeat = request.getParameter("passwordRepeat");

            if (password == null || !password.equals(passwordRepeat)) {

                request.setAttribute("error", "Password is not same");
                request.getRequestDispatcher("changepasswordpage.jsp").forward(request, response);

            } else {

                int id = Integer.parseInt(request.getParameter("id"));
                String hashedPassword = PasswordHasher.hashPassword(password);
                User user = new User();
                user.setId(id);
                user.setPassword(hashedPassword);
                UserDaoService userDaoService = new UserDaoManager();
                userDaoService.updatePassword(user);
                request.setAttribute("infos", "Password successfully changed!");
                request.getRequestDispatcher("successinfopage.jsp").forward(request, response);

            }
        } catch (GeneralSecurityException ex) {
            
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
