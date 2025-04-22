package com.smartfarm.controller;


import com.smartfarm.db.model.User;
import com.smartfarm.service.UserService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class AuthServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String email = request.getParameter("email");

        UserService userService = UserService.getInstance();
        List<User> users = userService.getAllUsers();

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getId());
                session.setAttribute("userName", user.getName());
                response.sendRedirect("my_farm");
                return;
            }
        }

        response.sendRedirect("login.jsp?error=true");
    }
}
