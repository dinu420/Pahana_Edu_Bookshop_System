package com.pahanasystem.controller;

import com.pahanasystem.dao.impl.CustomerDaoImpl;
import com.pahanasystem.model.Customer;
import com.pahanasystem.service.CustomerService;
import com.pahanasystem.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/add-customer")
public class AddCustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final CustomerService customerService = new CustomerServiceImpl(new CustomerDaoImpl());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        String unitsParam = request.getParameter("units");

        try {
            int units = Integer.parseInt(unitsParam.trim());

            Customer customer = new Customer();
            customer.setName(name);
            customer.setAddress(address);
            customer.setTelephone(telephone);
            customer.setUnitsConsumed(units);

            customerService.registerCustomer(customer);
            response.sendRedirect("customers");

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/add-customer.jsp").forward(request, response);
        }
    }

}
