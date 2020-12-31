package domain.service;

import domain.db.*;

import domain.model.CoronaTest;
import domain.model.User;
import domain.model.Registered;
import domain.model.Event;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class Service {
    private UserDB       userDb       = new UserDBSQL();
    private RegisteredDB registeredDb = new RegisteredDBSQL();
    private VisitDB      visitDb      = new VisitDBSQL();
    private EventDB      eventDb      = new EventDBSQL();
    private CoronaTestDB coronatestDb = new CoronaTestDBSQL();


    /***
     * User
     */
    public void addUser(User user) {
        userDb.addUser(user);
    }

    public void removeUser(String userId) {
        userDb.removeUser(userId);
    }

    public User getUser(String registeredEmail, String firstname, String lastname) {
        return userDb.getUser(registeredEmail, firstname, lastname);
    }

    public List<User> getAllUsers() {
        return userDb.getAllUsers();
    }

    public List<User> getUsersWithEmail(String email) {
        return userDb.getUsersWithEmail(email);
    }


    /**
     * Registered
     */
    public void addRegistered(Registered registered) {
        registeredDb.addRegistered(registered);
    }

    public Registered getRegistered(String email) {
        return registeredDb.getRegistered(email);
    }

    public List<Registered> getAllRegistered() {
        return registeredDb.getAllRegistered();
    }

    public void removeRegistered(String email) {
        registeredDb.removeRegistered(email);
    }

    public void changeRegisteredPassword(String email, String password) {
        registeredDb.changeRegisteredPassword(email, password);
    }


    /***
     * Visit
     */
    public List<Event> getAllEventsOfUser(String userId) {
        return visitDb.getAllEventsOfUser(userId);
    }

    public List<User> getAllUsersOfEvent(String eventId) {
        return visitDb.getAllUsersOfEvent(eventId);
    }

    public void addUserToEvent(String userId, String eventId) {
        visitDb.addUserToEvent(userId, eventId);
    }

    public void removeUserFromEvent(String userId, String eventId) {
        visitDb.removeUserFromEvent(userId, eventId);
    }


    /***
     * Event
     */
    public void addEvent(Event event) {
        eventDb.addEvent(event);
    }

    public Event getEvent(String eventId) {
        return eventDb.getEvent(eventId);
    }

    public void removeEvent(String eventId) {
        eventDb.removeEvent(eventId);
    }

    public List<Event> getAllEvents() {
        return eventDb.getAllEvents();
    }

    public List<String> getEventsWithCoronaPresent() {
        return eventDb.getEventsWithCoronaPresent();
    }


    /***
     * CoronaTest
     */
    public void addCoronaTest(CoronaTest coronatest) {
        coronatestDb.addCoronaTest(coronatest);
    }

    public HashMap<String, User> getAllCoronaTestsOfRegistered(String email) {
        return coronatestDb.getAllCoronaTestsOfRegistered(email);
    }

    public HashMap<String, User> getAllCoronaTests() {
        return coronatestDb.getAllCoronaTests();
    }

    public HashMap<User, Registered> searchContactsOfUser(String userid, LocalDateTime dateoftest, int daysaftertest) {
        return coronatestDb.searchContactsOfUser(userid, dateoftest, daysaftertest);
    }

    public HashMap<String, User> getAllCoronaTestsOfRegisteredBetweenDates(String email, Timestamp fromDate, Timestamp untilDate) {
        return coronatestDb.getAllCoronaTestsOfRegisteredBetweenDates(email, fromDate, untilDate);
    }

    public HashMap<String, User> getAllCoronaTestsBetweenDates(Timestamp fromDate, Timestamp untilDate) {
        return coronatestDb.getAllCoronaTestsBetweenDates(fromDate, untilDate);
    }

    public void removeCoronaTestsOfUser(String userid) {
        coronatestDb.removeCoronaTestsOfUser(userid);
    }
}
