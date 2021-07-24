package az.company.employee.controller;

import az.company.employee.dao.abstracts.EmployeeDaoService;
import az.company.employee.dao.concrets.EmployeeDaoManager;
import az.company.employee.model.concrets.Employee;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EmployeeSaveController", urlPatterns = {"/private/employee-save"})
public class EmployeeSaveController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("submit") != null) {
            Employee employee = new Employee();
            employee.setName(request.getParameter("name"));
            employee.setSurname(request.getParameter("surname"));
            employee.setAge(Integer.parseInt(request.getParameter("age")));
            employee.setSalary(Double.parseDouble(request.getParameter("salary")));
            EmployeeDaoService employeeDaoService=new EmployeeDaoManager();
            employeeDaoService.save(employee);
            response.sendRedirect(request.getContextPath()+"/private/employee-list");
        } else {
            response.sendRedirect(request.getContextPath()+"/private/employee-list");
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
