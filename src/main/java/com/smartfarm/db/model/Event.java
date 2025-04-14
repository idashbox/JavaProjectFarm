package com.smartfarm.db.model;

import java.sql.Timestamp;

public class Event {
    private int id;
    private String eventType;
    private String description;
    private Timestamp eventDate;

    public Event(int id, String eventType, String description, Timestamp eventDate) {
        this.id = id;
        this.eventType = eventType;
        this.description = description;
        this.eventDate = eventDate;
    }

    public Event(String eventType, String description) {
        this(0, eventType, description, null);
    }

    public int getId() {
        return id;
    }

    public String getEventType() {
        return eventType;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getEventDate() {
        return eventDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventType='" + eventType + '\'' +
                ", description='" + description + '\'' +
                ", eventDate=" + eventDate +
                '}';
    }
}
