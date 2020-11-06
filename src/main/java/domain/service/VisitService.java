package domain.service;

import domain.db.VisitDB;
import domain.db.VisitDBSQL;
import domain.model.Event;
import domain.model.User;

import java.util.List;

public class VisitService {
    private VisitDB db = new VisitDBSQL();

    public List<Event> getAllEventsOfUser(String userId) {
        return db.getAllEventsOfUser(userId);
    }

    public List<User> getAllUsersOfEvent(String eventId) {
        return db.getAllUsersOfEvent(eventId);
    }

    public void addUserToEvent(String userId, String eventId) {
        db.addUserToEvent(userId, eventId);
    }

}
