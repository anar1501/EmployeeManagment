package az.company.employee.controller;

import az.company.employee.dao.abstracts.UserDaoService;
import az.company.employee.dao.concrets.UserDaoManager;
import az.company.employee.enums.UserStatusEnum;
import az.company.employee.model.concrets.User;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SaveEditUserController", urlPatterns = {"/admin/save-edit-user"})
public class SaveEditUserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("submit") != null) {

            String idStr = request.getParameter("id-user");
            String roleIdStr = request.getParameter("role-id");
            if (idStr != null && roleIdStr != null) {
                int id = Integer.parseInt(idStr);
                int roleId = Integer.parseInt(roleIdStr);
                UserDaoService usd = new UserDaoManager();
                User user = usd.findById(id);

                if (user.getStatus() == UserStatusEnum.CONFIRMED.getValue()) {
                    user.setStatus(UserStatusEnum.UNCONFIRMED.getValue());
                    user.setId(id);
                    user.setRoleId(roleId);
                    usd.updateRoleIdAndStatus(user);
                    response.sendRedirect(request.getContextPath() + "/admin/userlist");
                } else if (user.getStatus() == UserStatusEnum.UNCONFIRMED.getValue()) {
                    user.setStatus(UserStatusEnum.CONFIRMED.getValue());
                    user.setId(id);
                    user.setRoleId(roleId);
                    usd.updateRoleIdAndStatus(user);
                    response.sendRedirect(request.getContextPath() + "/admin/userlist");
                }

            }
            response.sendRedirect(request.getContextPath() + "/admin/userlist");
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/userlist");
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
