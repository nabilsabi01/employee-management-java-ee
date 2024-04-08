package com.devart.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devart.dao.EmployeeDao;
import com.devart.model.Employee;

@WebServlet("/")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDao employeeDao;

    public void init() {
        employeeDao = new EmployeeDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertEmployee(request, response);
                    break;
                case "/delete":
                    deleteEmployee(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateEmployee(request, response);
                    break;
                default:
                    listEmployee(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Employee> listEmployee = employeeDao.selectAllEmployees();
        request.setAttribute("listEmployee", listEmployee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("employee-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("employee-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int employee_id = Integer.parseInt(request.getParameter("employee_id"));
        Employee existingEmployee = employeeDao.selectEmployee(employee_id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("employee-form.jsp");
        request.setAttribute("employee", existingEmployee);
        dispatcher.forward(request, response);
    }

    private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // Retrieve parameters from the request
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String department = request.getParameter("department");

        // Create a new Employee object
        Employee newEmployee = new Employee(0, first_name, last_name, address, email, salary, department);

        // Call DAO method to insert the new employee
        employeeDao.insertEmployee(newEmployee);

        // Redirect to the list page
        response.sendRedirect(request.getContextPath() + "/");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // Retrieve parameters from the request
        int employee_id = Integer.parseInt(request.getParameter("employee_id"));
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String department = request.getParameter("department");

        // Create an updated Employee object
        Employee updatedEmployee = new Employee(employee_id, first_name, last_name, address, email, salary, department);

        // Call DAO method to update the employee
        employeeDao.updateEmployee(updatedEmployee);

        // Redirect to the list page
        response.sendRedirect(request.getContextPath() + "/");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // Retrieve employee_id parameter from the request
        int employee_id = Integer.parseInt(request.getParameter("employee_id"));

        // Call DAO method to delete the employee
        employeeDao.deleteEmployee(employee_id);

        // Redirect to the list page
        response.sendRedirect(request.getContextPath() + "/");
    }
}
