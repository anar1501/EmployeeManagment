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

@WebServlet(name = "ForgetPasswordConfirm", urlPatterns = {"/forget-password-confirm"})
public class ForgetPasswordConfirmController extends HttpServlet {

    /*
    Bu controllerde forget password confirm codu emaile gonderilir  
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        
        if (email == null) {
            
            request.setAttribute("error", "No user found for such e-mail.");
            request.getRequestDispatcher("forget-password").forward(request, response);
            
        } else {
            
            UserDaoService usd = new UserDaoManager();
            User user = usd.findByEmail(email);
            
            if (user == null) {
                
                request.setAttribute("error", "No user found for such e-mail.");
                request.getRequestDispatcher("forget-password").forward(request, response);

            }else{
                
                String pswConfirmCode=UUID.randomUUID().toString();
                String hascode=MD5.hashedMd5(pswConfirmCode);
                LocalDateTime pswExpiredDate=LocalDateTime.now().plusMinutes(5);
                
                user.setPswActivationCode(hascode);
                user.setPswExpiredDate(pswExpiredDate);
                usd.updateForgetPasswordConfirmation(user);
                
                String subject = "Password Confirmation Code";

                String link = "http://localhost:8084/employee/password-confirm?code=" + pswConfirmCode;

                String text = "Your password confirmation code link:\n" + link;

                SendEmail.sendAsync(user.getEmail(), text, subject);

                request.setAttribute("infos", "Password Confirmation code was successfully sent! Please check your email!");
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
