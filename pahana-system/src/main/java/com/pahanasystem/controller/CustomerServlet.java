package com.pahanasystem.controller;

import com.pahanasystem.dao.impl.CustomerDaoImpl;
import com.pahanasystem.model.Customer;
import com.pahanasystem.service.CustomerService;
import com.pahanasystem.service.impl.CustomerServiceImpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CustomerService customerService = new CustomerServiceImpl(new CustomerDaoImpl());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Customer> customers = customerService.getAllCustomers();

        
        
        request.setAttribute("customers", customers);

        
        request.getRequestDispatcher("/customer.jsp").forward(request, response);
    }
}

