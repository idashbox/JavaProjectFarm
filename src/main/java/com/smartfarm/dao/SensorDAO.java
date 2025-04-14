package com.smartfarm.dao;

import com.smartfarm.db.DatabaseConnection;
import com.smartfarm.db.model.Sensor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SensorDAO {
    private static SensorDAO instance;

    private SensorDAO() {}

    public static synchronized SensorDAO getInstance() {
        if (instance == null) {
            instance = new SensorDAO();
        }
        return instance;
    }

    public void addSensor(String type, String location, int farmId) {
        String sql = "INSERT INTO Sensors (type, location, farm_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, type);
            stmt.setString(2, location);
            stmt.setInt(3, farmId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Sensor> getSensors() {
        List<Sensor> sensors = new ArrayList<>();
        String sql = "SELECT * FROM Sensors";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                sensors.add(mapResultSetToSensor(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sensors;
    }

    public Sensor getSensorById(int id) {
        String sql = "SELECT * FROM Sensors WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToSensor(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Sensor> getSensorsByFarmId(int farmId) {
        List<Sensor> sensors = new ArrayList<>();
        String sql = "SELECT * FROM Sensors WHERE farm_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, farmId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    sensors.add(mapResultSetToSensor(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sensors;
    }


    public void updateSensor(Sensor sensor) {
        String sql = "UPDATE Sensors SET type = ?, location = ?, farm_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sensor.getType());
            stmt.setString(2, sensor.getLocation());
            stmt.setInt(3, sensor.getUserId());
            stmt.setInt(4, sensor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSensor(int id) {
        String sql = "DELETE FROM Sensors WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Sensor mapResultSetToSensor(ResultSet rs) throws SQLException {
        return new Sensor(
                rs.getInt("id"),
                rs.getString("type"),
                rs.getString("location"),
                rs.getInt("farm_id")
        );
    }
}
