package com.smartfarm.model;

public class Animal {
    private int id;
    private String type;
    private String name;
    private int ownerId;

    public Animal(int id, String type, String name, int ownerId) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getOwnerId() {
        return ownerId;
    }
}
