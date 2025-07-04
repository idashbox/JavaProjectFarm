package com.smartfarm.dao;

import com.smartfarm.db.DatabaseConnection;
import com.smartfarm.db.model.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    private static AnimalDAO instance;

    private AnimalDAO() {}

    public static synchronized AnimalDAO getInstance() {
        if (instance == null) {
            instance = new AnimalDAO();
        }
        return instance;
    }

    public void addAnimal(String type, String name, int ownerId) {
        String sql = "INSERT INTO Animals (type, name, owner_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, type);
            stmt.setString(2, name);
            stmt.setInt(3, ownerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Animal> getAnimals() {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM Animals";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                animals.add(mapResultSetToAnimal(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    public Animal getAnimalById(int id) {
        String sql = "SELECT * FROM Animals WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToAnimal(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Animal> getAnimalsByOwnerId(int ownerId) {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM Animals WHERE owner_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ownerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    animals.add(mapResultSetToAnimal(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }


    public void deleteAnimalById(int id) {
        String sql = "DELETE FROM Animals WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAnimal(Animal animal) {
        String sql = "UPDATE Animals SET type = ?, name = ?, owner_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, animal.getType());
            stmt.setString(2, animal.getName());
            stmt.setInt(3, animal.getOwnerId());
            stmt.setInt(4, animal.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAnimal(int id) {
        String sql = "DELETE FROM Animals WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Animal mapResultSetToAnimal(ResultSet rs) throws SQLException {
        return new Animal(
                rs.getInt("id"),
                rs.getString("type"),
                rs.getString("name"),
                rs.getInt("owner_id")
        );
    }
}
