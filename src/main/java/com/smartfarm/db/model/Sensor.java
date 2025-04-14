package com.smartfarm.db.model;

public class Sensor {
    private int id;
    private String type;
    private String location;
    private int farmId;

    public Sensor(int id, String type, String location, int farmId) {
        this.id = id;
        this.type = type;
        this.location = location;
        this.farmId = farmId;
    }

    public Sensor(String type, String location, int farmId) {
        this(0, type, location, farmId);
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public int getUserId() {
        return farmId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", farmId=" + farmId +
                '}';
    }
}
