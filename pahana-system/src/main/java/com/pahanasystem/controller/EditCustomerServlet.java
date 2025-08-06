package com.pahanasystem.controller;

import com.pahanasystem.dao.impl.CustomerDaoImpl;
import com.pahanasystem.model.Customer;
import com.pahanasystem.service.CustomerService;
import com.pahanasystem.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/edit-customer")
public class EditCustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final CustomerService customerService = new CustomerServiceImpl(new CustomerDaoImpl());

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountNo = request.getParameter("accountNo");
        if (accountNo == null || accountNo.isEmpty()) {
            response.sendRedirect("customers");
            return;
        }

        Optional<Customer> optionalCustomer = customerService.findCustomerByAccountNo(accountNo);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("/edit-customer.jsp").forward(request, response);
        } else {
            response.sendRedirect("customers");
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountNo = request.getParameter("accountNo");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        int units = Integer.parseInt(request.getParameter("units"));

        Customer customer = new Customer();
        customer.setAccountNo(accountNo);
        customer.setName(name);
        customer.setAddress(address);
        customer.setTelephone(telephone);
        customer.setUnitsConsumed(units);

        try {
            customerService.updateCustomer(customer);
            response.sendRedirect("customers");  // redirect to your customer listing page
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/edit-customer.jsp?accountNo=" + accountNo).forward(request, response);
        }
    }
}
