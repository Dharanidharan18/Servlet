<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.employee.details.EmployeeDetails" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Details</title>
</head>
<body>
    <h2>Employee Details</h2>
    <form method="post" action="Employee">
        User ID: <input type="text" name="userId" required/><br/>
        Name: <input type="text" name="name" required/><br/>
        Password: <input type="password" name="password" required/><br/>
        <input type="submit" value="Add Employee"/>
    </form>

    <h2>Employee List</h2>
    <table border="1">
        <tr>
            <th>User ID</th>
            <th>Name</th>
            <th>Password</th>
        </tr>
        <%
            List<EmployeeDetails> employeeList = (List<EmployeeDetails>) request.getAttribute("employeeList");
            if (employeeList != null) {
                for (EmployeeDetails employee : employeeList) {
                    out.println("<tr>");
                    out.println("<td>" + employee.getUserId() + "</td>");
                    out.println("<td>" + employee.getName() + "</td>");
                    out.println("<td>" + employee.getPassword() + "</td>");
                    out.println("</tr>");
                }
            }
        %>
    </table>
</body>
</html>
