package com.pahanasystem.controller;

import com.pahanasystem.dao.impl.UserDaoImpl;
import com.pahanasystem.model.User;
import com.pahanasystem.service.UserService;
import com.pahanasystem.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/createCashier")
public class CreateCashierServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
	private final UserService userService = new UserServiceImpl(new UserDaoImpl());


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            
            User newCashier = new User(username, password, "cashier");
            userService.addUser(newCashier);

            request.setAttribute("message", "Cashier account created successfully.");
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
        }

        request.getRequestDispatcher("/create-cashier.jsp").forward(request, response);
    }
}
