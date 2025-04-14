package com.smartfarm.service;

import com.smartfarm.dao.EventDAO;
import com.smartfarm.db.model.Event;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class EventService {
    private static EventService instance;
    private final EventDAO eventDAO;

    private EventService() {
        this.eventDAO = EventDAO.getInstance();
    }

    public static synchronized EventService getInstance() {
        if (instance == null) {
            instance = new EventService();
        }
        return instance;
    }

    public void addEvent(String eventType, String description, Date eventDate) {
        Timestamp timestamp = new Timestamp(eventDate.getTime());
        eventDAO.addEvent(eventType, description, timestamp);
    }

    public List<Event> getAllEvents() {
        return eventDAO.getEvents();
    }

    public Event getEventById(int id) {
        return eventDAO.getEventById(id);
    }

    public void updateEvent(Event event) {
        eventDAO.updateEvent(event);
    }

    public void deleteEventById(int id) {
        eventDAO.deleteEvent(id);
    }
}
