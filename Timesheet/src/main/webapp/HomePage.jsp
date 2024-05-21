<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.employee.details.EmployeeDetails" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Details</title>
<style>
body {
    text-align: center;
}
</style>
</head>
<body>
    <form method="post" action="Employee">
        <input type="hidden" name="action" value="add">
        <label for="name" class="label">Name</label>
        <input id="name" type="text" class="input" name="name"><br><br>
        <label for="phoneNumber" class="label">Phone Number</label>
        <input id="phoneNumber" type="text" class="input" name="phoneNumber"><br><br>
        <label for="email" class="label">Email</label>
        <input id="email" type="text" class="input" name="email"><br><br>
        <input type="submit" class="button" value="Add Employee">
    </form>
</body>
</html>
