package com.smartfarm.service;

import com.smartfarm.dao.AnimalDAO;
import com.smartfarm.db.model.Animal;

import java.util.List;

public class AnimalService {
    private static AnimalService instance;
    private final AnimalDAO animalDAO;

    private AnimalService() {
        this.animalDAO = AnimalDAO.getInstance();
    }

    public static synchronized AnimalService getInstance() {
        if (instance == null) {
            instance = new AnimalService();
        }
        return instance;
    }

    public void addAnimal(String type, String name, int ownerId) {
        animalDAO.addAnimal(type, name, ownerId);
    }

    public List<Animal> getAllAnimals() {
        return animalDAO.getAnimals();
    }

    public Animal getAnimalById(int id) {
        return animalDAO.getAnimalById(id);
    }
    public List<Animal> getAnimalsByOwnerId(int id) {
        return animalDAO.getAnimalsByOwnerId(id);
    }

    public void updateAnimal(Animal animal) {
        animalDAO.updateAnimal(animal);
    }

    public void deleteAnimalById(int id) {
        animalDAO.deleteAnimal(id);
    }
}
