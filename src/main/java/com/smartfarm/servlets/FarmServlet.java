package com.smartfarm.servlets;

import com.smartfarm.dao.UserDAO;
import com.smartfarm.dao.AnimalDAO;
import com.smartfarm.model.User;
import com.smartfarm.model.Animal;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class FarmServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = UserDAO.getUsers();
        List<Animal> animals = AnimalDAO.getAnimals();

        request.setAttribute("users", users);
        request.setAttribute("animals", animals);

        request.getRequestDispatcher("farm.jsp").forward(request, response);
    }
}
