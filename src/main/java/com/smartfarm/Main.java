package com.smartfarm;

import com.smartfarm.dao.AnimalDAO;
import com.smartfarm.dao.UserDAO;
import com.smartfarm.db.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.getConnection();
//        UserDAO.addUser("Лена", "lena@example.com");
//        UserDAO.addUser("Петя", "petya@example.com");
//        UserDAO.printUsers();

//        AnimalDAO.addAnimal("Кошка", "Мурка", 1);
//        AnimalDAO.addAnimal("Собака", "Шарик", 2);
//        AnimalDAO.printAnimals();


    }
}
