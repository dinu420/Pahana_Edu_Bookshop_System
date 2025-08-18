package com.pahanasystem.controller;

import com.pahanasystem.dao.impl.CustomerDaoImpl;
import com.pahanasystem.service.CustomerService;
import com.pahanasystem.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/delete-customer")
public class DeleteCustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
	private final CustomerService customerService = new CustomerServiceImpl(new CustomerDaoImpl());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountNo = request.getParameter("accountNo");

        if (accountNo != null && !accountNo.isEmpty()) {
            try {
                customerService.deleteCustomer(accountNo);
            } catch (Exception e) {
                
            }
        }

        response.sendRedirect("customers");
    }
}