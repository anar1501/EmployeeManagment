package az.company.employee.controller;

import az.company.employee.dao.abstracts.PermissionDaoService;
import az.company.employee.dao.abstracts.RoleDaoService;
import az.company.employee.dao.abstracts.RolePermissionDaoService;
import az.company.employee.dao.abstracts.UserDaoService;
import az.company.employee.dao.concrets.PermissionDaoManager;
import az.company.employee.dao.concrets.RoleDaoManager;
import az.company.employee.dao.concrets.RolePermissionDaoManager;
import az.company.employee.dao.concrets.UserDaoManager;
import az.company.employee.model.concrets.Permission;
import az.company.employee.model.concrets.Role;
import az.company.employee.model.concrets.RolePermission;
import az.company.employee.model.concrets.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RoleListController", urlPatterns = {"/admin/rolelist"})
public class RoleListController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Object userObject = session.getAttribute("user");
        User user = (User) userObject;

        if (user != null) {
            request.setAttribute("admin_url", request.getContextPath() + "/admin/admin-panel");
            request.setAttribute("home_url", request.getContextPath() + "/home");
            request.setAttribute("employeelist_url", request.getContextPath() + "/private/employee-list");
            request.setAttribute("insert_url", request.getContextPath() + "/private/insert");
            request.setAttribute("edit_url", request.getContextPath() + "/private/edit-employee-page");
            request.setAttribute("delete_url", request.getContextPath() + "/private/delete-employee-page");
            request.setAttribute("login_url", "login");
            request.setAttribute("register_url", "register");

            RoleDaoService rsd = new RoleDaoManager();
            UserDaoService usd = new UserDaoManager();
            PermissionDaoService pds = new PermissionDaoManager();
            RolePermissionDaoService rpsd = new RolePermissionDaoManager();
            int permissionId = usd.findPermissionOfRoleById(user.getRoleId());

            List<RolePermission> rolePermissions = rpsd.findRoleAndPermissionId(user.getRoleId(), permissionId);

            List<Permission> permissions = pds.findAll();

            int roleId = usd.findRoleIdByUserId(user.getId());

//            List<Role> rolepermission = rsd.findPermissionIdByRoleId(roleId);

            List<Role> roles = rsd.findAll();
            request.setAttribute("roles", roles);
            request.setAttribute("rsd", rsd);

//            request.setAttribute("rolepermission", rolepermission);
            request.getRequestDispatcher("/rolepage.jsp").forward(request, response);
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
