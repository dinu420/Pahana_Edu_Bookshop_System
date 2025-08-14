package com.pahanasystem.controller;

import com.pahanasystem.dao.impl.UserDaoImpl;
import com.pahanasystem.service.UserService;
import com.pahanasystem.service.impl.UserServiceImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteCashier")
public class DeleteCashierServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl(new UserDaoImpl());
    
    // Load the delete cashier page with the cashier list
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("cashierList", userService.getAllCashiers());
        request.getRequestDispatcher("/delete-cashier.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");

        try {
            boolean isDeleted = userService.deleteByUsername(username);
            if (isDeleted) {
                request.setAttribute("message", "Cashier deleted successfully.");
            } else {
                request.setAttribute("error", "Failed to delete cashier. Username not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while deleting the cashier.");
        }

        // Load updated cashier list for display
        request.setAttribute("cashierList", userService.getAllCashiers());

        // Forward to the delete-cashier JSP
        request.getRequestDispatcher("/delete-cashier.jsp").forward(request, response);
    }
}


