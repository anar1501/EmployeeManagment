package az.company.employee.controller;

import az.company.employee.dao.abstracts.RoleDaoService;
import az.company.employee.dao.concrets.RoleDaoManager;
import az.company.employee.model.concrets.Permission;
import az.company.employee.model.concrets.Role;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SaveEditRoleController", urlPatterns = {"/admin/save-edit-role"})
public class SaveEditRoleController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("submit") != null) {

            String roleStr = request.getParameter("role-id");
            if (roleStr != null) {
                int roleId = Integer.parseInt(roleStr);
                Role role = new Role();
                role.setId(roleId);
                String permissionStr[] = request.getParameterValues("p-id");
                for (int i = 0; i < permissionStr.length; i++) {
                    if (permissionStr[i] != null) {
                        Permission permission = new Permission();
                        permission.setId(Integer.parseInt(permissionStr[i]));
                        RoleDaoService rds = new RoleDaoManager();
                        rds.save(role.getId(), permission.getId());
                        response.sendRedirect(request.getContextPath() + "/admin/rolelist");
                    }

                }
            }

        } else {

            response.sendRedirect(request.getContextPath() + "/admin/rolelist");

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
