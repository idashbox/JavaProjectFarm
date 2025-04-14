package com.smartfarm.service;

import com.smartfarm.dao.UserDAO;
import com.smartfarm.db.model.User;

import java.util.List;

public class UserService {
    private static UserService instance;
    private final UserDAO userDAO;

    private UserService() {
        this.userDAO = UserDAO.getInstance();
    }

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void addUser(String name, String email) {
        userDAO.addUser(name, email);
    }

    public List<User> getAllUsers() {
        return userDAO.getUsers();
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUserById(int id) {
        userDAO.deleteUser(id);
    }
}
