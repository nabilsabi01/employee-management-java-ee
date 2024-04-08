<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Employee Management Application</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous">
</head>
<body>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${employee != null}">
                <form action="<%=request.getContextPath()%>/update" method="post">
            </c:if>
            <c:if test="${employee == null}">
                <form action="<%=request.getContextPath()%>/insert" method="post">
            </c:if>

            <caption>
                <h2>
                    <c:if test="${employee != null}">Edit Employee</c:if>
                    <c:if test="${employee == null}">Add New Employee</c:if>
                </h2>
            </caption>

            <fieldset class="form-group">
                <label>Employee Id</label> 
                <input type="text" value="${employee != null ? employee.employee_id : ''}" class="form-control" name="employee_id" required="required">
            </fieldset>

            <c:if test="${employee != null}">
                <input type="hidden" name="employee_id" value="${employee.employee_id}" />
            </c:if>

            <fieldset class="form-group">
                <label>First Name</label> 
                <input type="text" value="${employee != null ? employee.first_name : ''}" class="form-control" name="first_name" required="required">
            </fieldset>

            <fieldset class="form-group">
                <label>Last Name</label> 
                <input type="text" value="${employee != null ? employee.last_name : ''}" class="form-control" name="last_name" required="required">
            </fieldset>

            <fieldset class="form-group">
                <label>Email</label> 
                <input type="text" value="${employee != null ? employee.email : ''}" class="form-control" name="email">
            </fieldset>

            <fieldset class="form-group">
                <label>Address</label> 
                <input type="text" value="${employee != null ? employee.address : ''}" class="form-control" name="address">
            </fieldset>

            <fieldset class="form-group">
                <label>Salary</label> 
                <input type="text" value="${employee != null ? employee.salary : ''}" class="form-control" name="salary">
            </fieldset>

            <fieldset class="form-group">
                <label>Department</label> 
                <input type="text" value="${employee != null ? employee.department : ''}" class="form-control" name="department">
            </fieldset>

            <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
