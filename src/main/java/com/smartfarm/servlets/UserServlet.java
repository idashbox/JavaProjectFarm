package com.smartfarm.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.smartfarm.dao.UserDAO;
import com.smartfarm.model.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<User> users = UserDAO.getUsers();

        System.out.println("Пользователи получены: " + users);
        String json = new Gson().toJson(users);

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
