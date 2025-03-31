package com.smartfarm.db;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseInitializer {

    public static void initializeDatabase() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            String createUsersTable = "CREATE TABLE IF NOT EXISTS Users (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100) UNIQUE NOT NULL" +
                    ");";

            String createAnimalsTable = "CREATE TABLE IF NOT EXISTS Animals (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "type VARCHAR(50) NOT NULL, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "owner_id INT, " +
                    "FOREIGN KEY (owner_id) REFERENCES Users(id) ON DELETE CASCADE" +
                    ");";

            String createPlantsTable = "CREATE TABLE IF NOT EXISTS Plants (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "type VARCHAR(50) NOT NULL, " +
                    "planted_date DATE NOT NULL, " +
                    "owner_id INT, " +
                    "FOREIGN KEY (owner_id) REFERENCES Users(id) ON DELETE CASCADE" +
                    ");";

            String createSensorsTable = "CREATE TABLE IF NOT EXISTS Sensors (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "type VARCHAR(50) NOT NULL, " +
                    "location VARCHAR(100) NOT NULL, " +
                    "farm_id INT" +
                    ");";

            String createEventsTable = "CREATE TABLE IF NOT EXISTS Events (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "event_type VARCHAR(50) NOT NULL, " +
                    "description TEXT, " +
                    "event_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ");";

            statement.execute(createUsersTable);
            statement.execute(createAnimalsTable);
            statement.execute(createPlantsTable);
            statement.execute(createSensorsTable);
            statement.execute(createEventsTable);

            System.out.println("Таблицы успешно созданы или уже существуют.");
        } catch (SQLException e) {
            System.err.println("Ошибка при создании таблиц: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        initializeDatabase();
    }
}
