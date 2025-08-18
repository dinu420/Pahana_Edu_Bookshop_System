package com.pahanasystem.controller;

import com.pahanasystem.dao.impl.UserDaoImpl;
import com.pahanasystem.model.User;
import com.pahanasystem.service.UserService;
import com.pahanasystem.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<User> optionalUser = userService.findUserByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getPassword().equals(password)) {
                
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

               
                String role = user.getRole().toLowerCase();
                switch (role) {
                    case "cashier":
                        request.getRequestDispatcher("/home.jsp").forward(request, response);
                        return;
                    case "admin":
                        request.getRequestDispatcher("/admin-dashboard.jsp").forward(request, response);
                        return;
                    default:
                        
                        request.setAttribute("error", "Unknown user role");
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                        return;
                }
            }
        }

        
        request.setAttribute("error", "Invalid username or password");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
