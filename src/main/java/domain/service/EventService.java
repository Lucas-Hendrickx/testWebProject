package domain.service;

import domain.db.EventDB;
import domain.db.EventDBSQL;
import domain.model.Event;

import java.util.List;

public class EventService {
    private EventDB db = new EventDBSQL();

    public Event getEvent(String eventId) {
        return db.getEvent(eventId);
    }

    public List<Event> getAllEvents() {
        return db.getAllEvents();
    }
}
