package com.smartfarm.dao;

import com.smartfarm.db.DatabaseConnection;
import com.smartfarm.db.model.Plant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlantDAO {
    private static PlantDAO instance;

    private PlantDAO() {}

    public static synchronized PlantDAO getInstance() {
        if (instance == null) {
            instance = new PlantDAO();
        }
        return instance;
    }

    public void addPlant(String type, Date plantedDate, int ownerId) {
        String sql = "INSERT INTO Plants (type, planted_date, owner_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, type);
            stmt.setDate(2, plantedDate);
            stmt.setInt(3, ownerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Plant> getPlants() {
        List<Plant> plants = new ArrayList<>();
        String sql = "SELECT * FROM Plants";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                plants.add(mapResultSetToPlant(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plants;
    }

    public Plant getPlantById(int id) {
        String sql = "SELECT * FROM Plants WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPlant(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Plant> getPlantByOwnerId(int ownerId) {
        List<Plant> plants = new ArrayList<>();
        String sql = "SELECT * FROM Plants WHERE owner_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ownerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    plants.add(mapResultSetToPlant(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plants;
    }


    public void updatePlant(Plant plant) {
        String sql = "UPDATE Plants SET type = ?, planted_date = ?, owner_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, plant.getType());
            stmt.setDate(2, plant.getPlantedDate());
            stmt.setInt(3, plant.getOwnerId());
            stmt.setInt(4, plant.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePlant(int id) {
        String sql = "DELETE FROM Plants WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Plant mapResultSetToPlant(ResultSet rs) throws SQLException {
        return new Plant(
                rs.getInt("id"),
                rs.getString("type"),
                rs.getDate("planted_date"),
                rs.getInt("owner_id")
        );
    }
}
