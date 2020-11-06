package domain.db;

import domain.model.Event;

import java.util.List;

public interface EventDB {
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
}
