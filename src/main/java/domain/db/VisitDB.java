package domain.db;

import domain.model.Event;
import domain.model.User;

import java.util.List;

public interface VisitDB {
    /**
     * Returns a list of all events that the user was at.
     * @param userId The userid of the person we are investigating
     * @throws DbException if the given userId is null
     * @throws DbException if the given userId can not be found
     */
    List<Event> getAllEventsOfUser(String userId);

    /**
     * Returns a list of all events that the user was at.
     * @param eventId The userid of the person we are investigating
     * @throws DbException if the given eventId is null
     * @throws DbException if the given eventId can not be found
     */
    List<User> getAllUsersOfEvent(String eventId);

    /**
     * Adds the person from userId to the event with evenId
     * @param userId The userid of the person we are going to add
     * @param eventId The eventid of the event that we are adding the person to
     * @throws DbException if the given userId is null
     * @throws DbException if the given userId can not be found
     * @throws DbException if the given eventId is null
     * @throws DbException if the given eventId can not be found
     */
    void addUserToEvent(String userId, String eventId);
}
