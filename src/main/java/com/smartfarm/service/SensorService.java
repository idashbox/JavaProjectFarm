package com.smartfarm.service;

import com.smartfarm.dao.SensorDAO;
import com.smartfarm.db.model.Animal;
import com.smartfarm.db.model.Sensor;

import java.util.List;

public class SensorService {
    private static SensorService instance;
    private final SensorDAO sensorDAO;

    private SensorService() {
        this.sensorDAO = SensorDAO.getInstance();
    }

    public static synchronized SensorService getInstance() {
        if (instance == null) {
            instance = new SensorService();
        }
        return instance;
    }

    public void addSensor(String type, String location, int farmId) {
        sensorDAO.addSensor(type, location, farmId);
    }

    public List<Sensor> getAllSensors() {
        return sensorDAO.getSensors();
    }

    public Sensor getSensorById(int id) {
        return sensorDAO.getSensorById(id);
    }

    public List<Sensor> getSensorsByUserId(int id) {
        return sensorDAO.getSensorsByFarmId(id);
    }

    public void updateSensor(Sensor sensor) {
        sensorDAO.updateSensor(sensor);
    }

    public void deleteSensorById(int id) {
        sensorDAO.deleteSensor(id);
    }
}
