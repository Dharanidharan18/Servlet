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
body{
text-align:center;

}
</style>
</head>
<body>
    <form method="post" action="Employee">
     
            <input type="hidden" name="action" value="add">
            
                  <label for="userId" class="label">User Id</label>
                  <input id="userId" type="text" class="input" name="userId"><br><br>                
              
                  <label for="name" class="label">Name</label>
                  <input id="name" type="text" class="input" name="name"><br><br> 
                
              
                  <label for="pass" class="label">Password</label>
                  <input id="pass" type="password" class="input" data-type="password" name="password"><br><br>  
                                   
            
                  <input type="submit" class="button" value="Sign Up">
                
        
      
        
         </form>
      </body>

</html>
