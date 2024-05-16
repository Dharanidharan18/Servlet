package com.employee.servlet;

import com.employee.details.EmployeeDetails;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Employee")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private List<EmployeeDetails> employeeList = new ArrayList<>();

    public EmployeeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employeeList", employeeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        EmployeeDetails employee = new EmployeeDetails(userId, name, password);
        employeeList.add(employee);

        doGet(request, response);
    }
}
