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
                    String name = resultSet.getString("name");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    String email = resultSet.getString("email");
                    EmployeeDetails employee = new EmployeeDetails(name, phoneNumber, email);
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
    }

    private synchronized void addEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");

        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO employees (name, phoneNumber, email) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, name);
                statement.setString(2, phoneNumber);
                statement.setString(3, email);
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
}
