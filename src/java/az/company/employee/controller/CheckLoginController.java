package az.company.employee.controller;

import az.company.employee.dao.abstracts.UserDaoService;
import az.company.employee.dao.concrets.UserDaoManager;
import az.company.employee.enums.UserStatusEnum;
import az.company.employee.model.concrets.User;
import az.company.employee.security.PasswordHasher;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CheckLoginController", urlPatterns = {"/check-login"})
public class CheckLoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            UserDaoService userDaoService = new UserDaoManager();
            User user = userDaoService.findByEmail(email);
            if (user == null) {
                request.setAttribute("error", "Email is incorrect!");
                request.getRequestDispatcher("login").forward(request, response);

            } else {
                boolean passwordIsValid = PasswordHasher.verifyPassword(password, user.getPassword());
                if (passwordIsValid) {
                    
                    if (user.getStatus() == UserStatusEnum.CONFIRMED.getValue()) {

                        HttpSession session = request.getSession();
                        session.setAttribute("user", user);
                        session.setMaxInactiveInterval(3600);
                        response.sendRedirect("home");
                        
                    } else {
                        
                        request.setAttribute("infoex", "Your account is not confirmed!");
                        request.setAttribute("infos", request.getContextPath()+"/resend?id="+user.getId());
                        request.getRequestDispatcher("/error-info").forward(request, response);
                        
                    }
                } else {
                    
                    request.setAttribute("error", "Password is incorrect!");
                    request.getRequestDispatcher("login").forward(request, response);
                    
                }
            }
        } catch (GeneralSecurityException exception) {

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
