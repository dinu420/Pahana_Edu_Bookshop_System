package com.pahanasystem.controller;

import com.pahanasystem.dao.impl.ItemDaoImpl;
import com.pahanasystem.model.Item;
import com.pahanasystem.service.ItemService;
import com.pahanasystem.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/add-item")
public class AddItemServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
	private final ItemService itemService = new ItemServiceImpl(new ItemDaoImpl());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String unitPriceStr = request.getParameter("unitPrice");
        String stockQtyStr = request.getParameter("stockQty"); 

        try {
            BigDecimal unitPrice = new BigDecimal(unitPriceStr);
            int stockQty = Integer.parseInt(stockQtyStr);

            Item item = new Item();
            item.setTitle(title);
            item.setUnitPrice(unitPrice);
            item.setStockQty(stockQty);

            itemService.addItem(item);
            response.sendRedirect("items");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid input for Unit Price or Stock Quantity");
            request.getRequestDispatcher("/add-item.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/add-item.jsp").forward(request, response);
        }
    }
}
