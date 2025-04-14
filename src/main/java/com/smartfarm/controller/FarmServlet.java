package com.smartfarm.controller;

import com.smartfarm.db.model.Animal;
import com.smartfarm.db.model.Plant;
import com.smartfarm.db.model.Sensor;
import com.smartfarm.service.AnimalService;
import com.smartfarm.service.PlantService;
import com.smartfarm.service.SensorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/my_farm")
public class FarmServlet extends HttpServlet {

    private final PlantService plantService = PlantService.getInstance();
    private final AnimalService animalService = AnimalService.getInstance();
    private final SensorService sensorService = SensorService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Plant> plants = plantService.getPlantsByOwnerId(userId);
        List<Animal> animals = animalService.getAnimalsByOwnerId(userId);
        List<Sensor> sensors = sensorService.getSensorsByUserId(userId);

        request.setAttribute("plants", plants);
        request.setAttribute("animals", animals);
        request.setAttribute("sensors", sensors);
        request.getRequestDispatcher("farm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String method = request.getParameter("_method");
        if ("DELETE".equalsIgnoreCase(method)) {
            doDelete(request, response);
            return;
        }

        String resource = request.getParameter("resource");
        String type = request.getParameter("type");

        try {
            switch (resource) {
                case "animal":
                    String name = request.getParameter("name");
                    animalService.addAnimal(type, name, userId);
                    break;
                case "plant":
                    String dateStr = request.getParameter("plantedDate");
                    Date plantedDate = Date.valueOf(dateStr);
                    plantService.addPlant(type, plantedDate, userId);
                    break;
                case "sensor":
                    String location = request.getParameter("location");
                    sensorService.addSensor(type, location, userId);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный тип ресурса");
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при добавлении ресурса");
            return;
        }

        response.sendRedirect("/Java_Project_war_exploded/my_farm");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String resource = request.getParameter("resource");
        int id = Integer.parseInt(request.getParameter("id"));

        switch (resource) {
            case "animal":
                Animal animal = animalService.getAnimalById(id);
                if (animal != null && animal.getOwnerId() == userId) {
                    animalService.deleteAnimalById(id);
                }
                break;
            case "plant":
                Plant plant = plantService.getPlantById(id);
                if (plant != null && plant.getOwnerId() == userId) {
                    plantService.deletePlantById(id);
                }
                break;
            case "sensor":
                Sensor sensor = sensorService.getSensorById(id);
                if (sensor != null && sensor.getUserId() == userId) {
                    sensorService.deleteSensorById(id);
                }
                break;
            default:
                break;
        }

        response.sendRedirect("/Java_Project_war_exploded/my_farm");
    }
}
