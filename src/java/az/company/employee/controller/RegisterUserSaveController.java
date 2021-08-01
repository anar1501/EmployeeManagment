package az.company.employee.controller;

import az.company.employee.dao.abstracts.UserDaoService;
import az.company.employee.dao.concrets.UserDaoManager;
import az.company.employee.mail.MD5;
import az.company.employee.mail.SendEmail;
import az.company.employee.model.concrets.User;
import az.company.employee.security.PasswordHasher;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterUserSaveController", urlPatterns = {"/register-user-save"})
public class RegisterUserSaveController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String repeatPassword = request.getParameter("password-repeat");

            UserDaoService userDaoService = new UserDaoManager();
            User userdao = userDaoService.findByEmail(email);
            if (userdao != null) {
                if (email.equals(userdao.getEmail())) {
                    //errore bu emailde register varsa onun atributudur
                    request.setAttribute("errore", "There is already such an e-mail subscription!");
                    request.getRequestDispatcher("register").forward(request, response);
                }
            } else if (!password.equals(repeatPassword)) {

                //errorp password repeat password eyni olmazsa onun atributudur
                request.setAttribute("errorp", "Password repeat is not same!");
                request.getRequestDispatcher("register").forward(request, response);

            } else {

                User user = new User();
                user.setName(name);
                user.setSurname(surname);
                user.setEmail(email);
                user.setPassword(PasswordHasher.hashPassword(password));
                String activationCode = UUID.randomUUID().toString();
                user.setActivationCode(MD5.hashedMd5(activationCode));
                LocalDateTime expiredDate = LocalDateTime.now().plusHours(1);
                user.setExpiredDate(expiredDate);
                userDaoService.save(user);

                String subject = "Confirm Registration";

                String link = "http://localhost:8084/employee/registerconfirm?code=" + activationCode;

                String title = "Your confirmation link:\n " + link;

                SendEmail.sendAsync(email, title, subject);

                //infos-info-success sehifesine yonlendirilen atributdur
                request.setAttribute("infos", "Your Registration was successfully! Pls check your email.");
                request.getRequestDispatcher("success-info").forward(request, response);

            }
        } catch (GeneralSecurityException e) {

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
