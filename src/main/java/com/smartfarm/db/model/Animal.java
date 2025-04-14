package com.smartfarm.db.model;


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

    public Animal(String type, String name, int ownerId) {
        this(0, type, name, ownerId);
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

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }
}

