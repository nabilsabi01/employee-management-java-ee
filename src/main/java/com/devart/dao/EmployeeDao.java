package com.devart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.devart.model.Employee;

public class EmployeeDao {

    public String jdbcURL = "jdbc:mysql://localhost:3306/employee_db";
    public String jdbcUserName = "root";
    public String jdbcPassword = "";
    public String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employees" +
            "  (employee_id, first_name, last_name, address, email, salary, department) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_EMPLOYEE_BY_ID = "select employee_id, first_name, last_name, address, email, salary, department from employees where employee_id =?";
    private static final String SELECT_ALL_EMPLOYEES = "select * from employees";
    private static final String DELETE_EMPLOYEE_SQL = "delete from employees where employee_id = ?;";
    private static final String UPDATE_EMPLOYEE_SQL = "update employees set first_name = ?, last_name = ?, address = ?, email = ?, salary = ?, department = ? where employee_id = ?;";

    public EmployeeDao() {

    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertEmployee(Employee employee) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {
            preparedStatement.setInt(1, employee.getEmployee_id());
            preparedStatement.setString(2, employee.getFirst_name());
            preparedStatement.setString(3, employee.getLast_name());
            preparedStatement.setString(4, employee.getAddress());
            preparedStatement.setString(5, employee.getEmail());
            preparedStatement.setDouble(6, employee.getSalary());
            preparedStatement.setString(7, employee.getDepartment());

            preparedStatement.executeUpdate();
        }
    }

    public Employee selectEmployee(int employeeId) {
        Employee employee = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);) {
            preparedStatement.setInt(1, employeeId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int employee_id = Integer.parseInt(rs.getString("employee_id"));
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                double salary = rs.getDouble("salary");
                String department = rs.getString("department");

                employee = new Employee(employee_id, first_name, last_name, address, email, salary, department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public List<Employee> selectAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int employee_id = Integer.parseInt(rs.getString("employee_id"));
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                double salary = rs.getDouble("salary");
                String department = rs.getString("department");

                employees.add(new Employee(employee_id, first_name, last_name, address, email, salary, department));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public boolean deleteEmployee(int employee_id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE_SQL);) {
            statement.setInt(1, employee_id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateEmployee(Employee employee) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL);) {
            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, employee.getAddress());
            statement.setString(4, employee.getEmail());
            statement.setDouble(5, employee.getSalary());
            statement.setString(6, employee.getDepartment());
            statement.setInt(7, employee.getEmployee_id());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}