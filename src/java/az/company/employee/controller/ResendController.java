package az.company.employee.controller;

import az.company.employee.dao.abstracts.UserDaoService;
import az.company.employee.dao.concrets.UserDaoManager;
import az.company.employee.mail.MD5;
import az.company.employee.mail.SendEmail;
import az.company.employee.model.concrets.User;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ResendController", urlPatterns = {"/resend"})
public class ResendController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("id");
        UserDaoService userDaoService = new UserDaoManager();
        if (userId != null) {
            int id = Integer.parseInt(userId);
            User user = userDaoService.findById(id);
            
            if (user != null) {

                String activationCode = UUID.randomUUID().toString();
                String hashedCode = MD5.hashedMd5(activationCode);
                LocalDateTime expiredDate = LocalDateTime.now().plusHours(1);

                user.setActivationCode(hashedCode);
                user.setExpiredDate(expiredDate);
                user.setId(id);
                userDaoService.updateConfirmation(user);

                String subject = "Activation Code";

                String link = "http://localhost:8084/employee/registerconfirm?code=" + activationCode;

                String text = "Your activation code link:\n" + link;

                SendEmail.sendAsync(user.getEmail(), text, subject);

                request.setAttribute("info2", "Activation code was successfully sent! Please check your email!");
                request.getRequestDispatcher("success-info").forward(request, response);
            }

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
