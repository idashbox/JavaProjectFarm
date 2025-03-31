package com.smartfarm.dao;

import com.smartfarm.db.DatabaseConnection;
import com.smartfarm.model.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    public static void addAnimal(String type, String name, int ownerId) {
        String sql = "INSERT INTO Animals (type, name, owner_id) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, type);
            stmt.setString(2, name);
            stmt.setInt(3, ownerId);
            stmt.executeUpdate();
            System.out.println("Животное добавлено: " + name);
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении животного: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<Animal> getAnimals() {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM Animals";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Animal animal = new Animal(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getInt("owner_id")
                );
                animals.add(animal);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении животных: " + e.getMessage());
            e.printStackTrace();
        }
        return animals;
    }

    public static void printAnimals() {
        List<Animal> animals = getAnimals();
        if (animals.isEmpty()) {
            System.out.println("В базе нет животных.");
        } else {
            animals.forEach(animal ->
                    System.out.println("ID: " + animal.getId() + ", Вид: " + animal.getType() +
                            ", Имя: " + animal.getName() + ", Владелец ID: " + animal.getOwnerId())
            );
        }
    }
}
