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
    table {
        width: 100%;
        border-collapse: collapse;
    }
    table, th, td {
        border: 1px solid black;
    }
    th, td {
        padding: 8px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
</style>
</head>
<body>
    <h2>Employee List</h2>
    <table>
        <tr>
        	<th>Employee Id</th>
            <th>Name</th>
            <th>Phone Number</th>
            <th>Email</th>
            <th>Password</th>
            <th>Delete Employee</th>
        </tr>
        <%
            List<EmployeeDetails> employeeList = (List<EmployeeDetails>) request.getAttribute("employeeList");
            if (employeeList != null) {
                for (EmployeeDetails employee : employeeList) {
                    out.println("<tr>");
                    out.println("<td>" + employee.getEmpID() + "</td>");
                    out.println("<td>" + employee.getName() + "</td>");
                    out.println("<td>" + employee.getPhoneNumber() + "</td>");
                    out.println("<td>" + employee.getEmail() + "</td>");
                    out.println("<td>" + employee.getPassword() + "</td>");
                    out.println("<td>");
        %>
                    <form action="Employee" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="deleteid" value="<%= employeeList.indexOf(employee)  %>">
                        <button type="submit">Delete</button>
                    </form>
        <%
                    out.println("</td>");
                    out.println("</tr>");
                }
            }
        %>
    </table>
</body>
</html>
