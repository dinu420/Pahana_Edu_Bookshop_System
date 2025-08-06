package com.pahanasystem.controller;

import com.pahanasystem.dao.impl.ItemDaoImpl;
import com.pahanasystem.model.Item;
import com.pahanasystem.service.ItemService;
import com.pahanasystem.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/items")
public class ItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ItemService itemService = new ItemServiceImpl(new ItemDaoImpl());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        List<Item> items = itemService.getAllItems();
        request.setAttribute("items", items);

        request.getRequestDispatcher("/item.jsp").forward(request, response);
    }
}
