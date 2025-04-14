package com.smartfarm.db.model;

import java.sql.Date;

public class Plant {
    private int id;
    private String type;
    private Date plantedDate;
    private int ownerId;

    public Plant(int id, String type, Date plantedDate, int ownerId) {
        this.id = id;
        this.type = type;
        this.plantedDate = plantedDate;
        this.ownerId = ownerId;
    }

    public Plant(String type, Date plantedDate, int ownerId) {
        this(0, type, plantedDate, ownerId);
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Date getPlantedDate() {
        return plantedDate;
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

    public void setPlantedDate(Date plantedDate) {
        this.plantedDate = plantedDate;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", plantedDate=" + plantedDate +
                ", ownerId=" + ownerId +
                '}';
    }
}
