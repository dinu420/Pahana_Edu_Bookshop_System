package com.pahanasystem.controller;

import com.pahanasystem.model.Bill;
import com.pahanasystem.model.Customer;
import com.pahanasystem.model.User;
import com.pahanasystem.service.BillingService;
import com.pahanasystem.service.impl.BillingServiceImpl;
import com.pahanasystem.dao.impl.BillDaoImpl;
import com.pahanasystem.dao.impl.CustomerDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@WebServlet("/billing")
public class BillingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final BillingService billingService = new BillingServiceImpl(
            new BillDaoImpl(),
            new CustomerDaoImpl()
    );

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountNo = request.getParameter("accountNo");

        if (accountNo != null && !accountNo.trim().isEmpty()) {
            Optional<Customer> customerOpt = billingService.findCustomer(accountNo);
            if (customerOpt.isPresent()) {
                request.setAttribute("customer", customerOpt.get());
            } else {
                request.setAttribute("error", "Customer not found.");
            }
        }

        request.getRequestDispatcher("/billing.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountNo = request.getParameter("accountNo");
        
        Optional<Customer> customerOpt = billingService.findCustomer(accountNo);

        if (customerOpt.isEmpty()) {
            request.setAttribute("error", "Customer not found.");
            request.getRequestDispatcher("/billing.jsp").forward(request, response);
            return;
        }

        Customer customer = customerOpt.get();

        int unitsConsumed = customer.getUnitsConsumed();
        User loggedInUser = (User) request.getSession().getAttribute("user");
        if (loggedInUser == null) {
            request.setAttribute("error", "User not authenticated.");
            request.getRequestDispatcher("/billing.jsp").forward(request, response);
            return;
        }

        String generatedBy = loggedInUser.getUsername();  


        try {
            // Generate and save the bill
            Bill bill = billingService.generateBill(accountNo, unitsConsumed, generatedBy);

            // Prepare response headers for PDF download
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"bill_" + accountNo + ".pdf\"");

            // Generate PDF using data from bill and customer
            generatePdfBill(response, customer, bill.getUnits(), bill.getAmount());

        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("/billing.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Failed to generate bill PDF: " + e.getMessage());
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("/billing.jsp").forward(request, response);
        }
    }

    private void generatePdfBill(HttpServletResponse response, Customer customer, int units, BigDecimal amount) throws Exception {
        try (
                com.itextpdf.kernel.pdf.PdfWriter writer = new com.itextpdf.kernel.pdf.PdfWriter(response.getOutputStream());
                com.itextpdf.kernel.pdf.PdfDocument pdfDoc = new com.itextpdf.kernel.pdf.PdfDocument(writer);
                com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDoc)
        ) {
            document.add(new com.itextpdf.layout.element.Paragraph("Pahana BookShop Bill").setBold().setFontSize(20));
            document.add(new com.itextpdf.layout.element.Paragraph("Account No: " + customer.getAccountNo()));
            document.add(new com.itextpdf.layout.element.Paragraph("Name: " + customer.getName()));
            document.add(new com.itextpdf.layout.element.Paragraph("Telephone: " + customer.getTelephone()));
            document.add(new com.itextpdf.layout.element.Paragraph("Units Consumed: " + units));
            document.add(new com.itextpdf.layout.element.Paragraph("Rate per Unit: Rs. 10"));
            document.add(new com.itextpdf.layout.element.Paragraph("Total Amount: Rs. " + amount));
            document.add(new com.itextpdf.layout.element.Paragraph("Thank you!"));
        }
    }
}
