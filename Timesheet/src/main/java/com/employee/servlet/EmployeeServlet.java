package com.employee.servlet;

import com.employee.details.EmployeeDetails;
import com.employee.utils.DatabaseUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Employee")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EmployeeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EmployeeDetails> employeeList = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM employees";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int empID = resultSet.getInt("empID");
                    String name = resultSet.getString("name");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    EmployeeDetails employee = new EmployeeDetails(empID, name, phoneNumber, email, password);
                    employeeList.add(employee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		}

        request.setAttribute("employeeList", employeeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
				try {
					addEmployee(request, response);
				} catch (ClassNotFoundException | ServletException | IOException e) {
					
					e.printStackTrace();
				}
                    break;
                case "delete":
				try {
					deleteEmployee(request, response);
				} catch (ClassNotFoundException | ServletException | IOException e) {
				
					e.printStackTrace();
				}
                    break;
            }
        }
        
        int empID = Integer.parseInt(request.getParameter("empID"));
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

       //register
        
        try {
            if (registerUser(empID, name, phoneNumber, email, password)) {
                response.getWriter().write("User registered successfully.");
            } else {
                response.getWriter().write("User already exists with this User ID.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred during registration.");
        }
        
               
        //login

        try {
            if (loginUser(empID, password)) {
                response.getWriter().write("Login successful.");
            } else {
                response.getWriter().write("Invalid User ID or password. Please try again.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred during login.");
        }
    }
    
    

    private synchronized void addEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
       
    	int empID = Integer.parseInt(request.getParameter("empID"));
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO employees (empID, name, phoneNumber, email, password) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
            	statement.setInt(1, empID);
            	statement.setString(2, name);
                statement.setString(3, phoneNumber);
                statement.setString(4, email);
                statement.setString(5, password);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        doGet(request, response);
    }

    private synchronized void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("deleteid"));

        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "DELETE FROM employees WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        doGet(request, response);
    }
    
    private boolean registerUser(int empID, String name,String phoneNumber,String email, String password) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseUtil.getConnection();

    
        String selectQuery = "SELECT name FROM Employee_details WHERE userId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, empID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            preparedStatement.close();
            connection.close();
            return false; 
        }

       
        String insertQuery = "INSERT INTO Employee_details (empID, name, phoneNumber, email, password) VALUES (?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setInt(1, empID);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, phoneNumber);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, password);
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
        return true;
    }
    
    private boolean loginUser(int empID, String password) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseUtil.getConnection();

        String selectQuery = "SELECT * FROM Employee_details WHERE userId = ? AND password = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, empID);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        boolean loginSuccessful = resultSet.next();

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return loginSuccessful;
    }
}
