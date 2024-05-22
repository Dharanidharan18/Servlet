<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
</head>
<body>
    <form method="post" action="loginUser">
        <label for="empID">Employee ID : </label>
        <input type="text" id="empID" name="empID" required><br><br>
        
        <label for="password">Password : </label>
        <input type="password" id="password" name="password" required><br><br>
        
        <input type="submit" value="Login">
    </form>
</body>
</html>
