<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Management Application</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
</head>
<body>

<div class="row">
    <div class="container py-3">
        <h3 class="text-center mb-5 fs-2">List of Employees</h3>
        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New Employee</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Address</th>
                <th>Salary</th>
                <th>Department</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="employee" items="${listEmployee}">
                <tr>
                    <td><c:out value="${employee.employee_id}" /></td>
                    <td><c:out value="${employee.first_name}" /></td>
                    <td><c:out value="${employee.last_name}" /></td>
                    <td><c:out value="${employee.email}" /></td>
                    <td><c:out value="${employee.address}" /></td>
                    <td><c:out value="${employee.salary}" /></td>
                    <td><c:out value="${employee.department}" /></td>
                    <td>
                        <a href="edit?employee_id=<c:out value='${employee.employee_id}' />">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?employee_id=<c:out value='${employee.employee_id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
