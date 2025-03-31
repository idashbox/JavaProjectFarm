package com.smartfarm.dao;

import com.smartfarm.db.DatabaseConnection;
import com.smartfarm.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static void addUser(String name, String email) {
        String sql = "INSERT INTO Users (name, email) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("Пользователь добавлен: " + name);
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении пользователя: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении пользователей: " + e.getMessage());
            e.printStackTrace();
        }
        return users;
    }

    public static void printUsers() {
        List<User> users = getUsers();
        if (users.isEmpty()) {
            System.out.println("В базе нет пользователей.");
        } else {
            System.out.println("Users:");
            users.forEach(user ->
                    System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Email: " + user.getEmail())
            );
        }
    }
}
