package com.smartfarm.service;

import com.smartfarm.dao.PlantDAO;
import com.smartfarm.db.model.Plant;

import java.sql.Date;
import java.util.List;

public class PlantService {
    private static PlantService instance;
    private final PlantDAO plantDAO;

    private PlantService() {
        this.plantDAO = PlantDAO.getInstance();
    }

    public static synchronized PlantService getInstance() {
        if (instance == null) {
            instance = new PlantService();
        }
        return instance;
    }

    public void addPlant(String type, Date plantedDate, int ownerId) {
        plantDAO.addPlant(type, plantedDate, ownerId);
    }

    public List<Plant> getAllPlants() {
        return plantDAO.getPlants();
    }

    public Plant getPlantById(int id) {
        return plantDAO.getPlantById(id);
    }

    public List<Plant> getPlantsByOwnerId(int id) {
        return plantDAO.getPlantByOwnerId(id);
    }

    public void updatePlant(Plant plant) {
        plantDAO.updatePlant(plant);
    }

    public void deletePlantById(int id) {
        plantDAO.deletePlant(id);
    }
}
