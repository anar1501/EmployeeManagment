package az.company.employee.controller;

import az.company.employee.dao.abstracts.UserDaoService;
import az.company.employee.dao.concrets.UserDaoManager;
import az.company.employee.mail.MD5;
import az.company.employee.model.concrets.User;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PasswordConfirm", urlPatterns = {"/password-confirm"})
public class PasswordConfirmController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String code = request.getParameter("code");
        UserDaoService userDaoService = new UserDaoManager();

        if (code == null) {

            request.setAttribute("infoe", "Forget Password Confirmation code is not correct!");
            request.getRequestDispatcher("error-info").forward(request, response);

        } else {

            code = MD5.hashedMd5(code);

            User user = userDaoService.findByPasswordConfirmationCode(code);

            if (user == null) {

                request.setAttribute("infoe", "Forget Password Confirmation code is not correct!");
                request.getRequestDispatcher("error-info").forward(request, response);

            } else {
                LocalDateTime expiredDate = user.getPswExpiredDate();
                LocalDateTime currentDate = LocalDateTime.now();

                if (expiredDate.isBefore(currentDate)) {
                    request.setAttribute("infoex", "Forget Password Confirmation code is expired!");
                    request.setAttribute("infos", request.getContextPath()+"/resend?id="+user.getId());
                    request.getRequestDispatcher("error-info").forward(request, response);

                } else {

                    request.setAttribute("id", user.getId());
                    request.getRequestDispatcher("changepasswordpage.jsp").forward(request, response);
                    
                }

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
