package com.smartfarm.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:h2:c:/Users/Huisan/IdeaProjects/Java_Project/src/main/java/com/smartfarm/db/smartfarm";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    private static final String DB_DRIVER = "org.h2.Driver";

    public static Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC драйвер не найден!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Ошибка SQL: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
