package domain.db;

import domain.model.Event;

import java.util.List;

public interface EventDB {
    /**
     * Adds the event to the database.
     * @param event The event to be added
     * @throws DbException if the given event is null
     * @throws DbException if the given event can not be added
     */
    void addEvent(Event event);

    /**
     * Removes the event with given eventId out of the database.
     * @param eventId The eventid of the event that needs to be removed
     * @throws DbException if the given eventId is null
     * @throws DbException if the given eventId can not be found
     */
    void removeEvent(String eventId);

    /**
     * Returns the requested person
     * @param eventId The userid of the requested person
     * @throws DbException if the given eventId is null
     * @throws DbException if the given eventId can not be found
     */
    Event getEvent(String eventId);

    /**
     * Returns a list with all events
     * @return A List with all events stored in the database
     * @throws DbException if something went wrong
     */
    List<Event> getAllEvents();

    /**
     * Returns a list with eventid's of were corona was present
     * @return A list of strings with eventid
     * @throws DbException if something went wrong
     */
    List<String> getEventsWithCoronaPresent();
}
