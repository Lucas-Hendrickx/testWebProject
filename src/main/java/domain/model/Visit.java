package domain.model;

public class Visit {
    private String eventId, userId;


    /***
     * Constructors
     */
    public Visit(String eventId, String userId) {
        setEventId(eventId);
        setUserId(userId);
    }

    public Visit() {
    }


    /***
     * Setters
     */
    public void setEventId(String eventId) {
        if (eventId.isEmpty()) {
            throw new DomainException("eventId is empty");
        }
        this.eventId = eventId;
    }

    public void setUserId(String userId) {
        if (userId.isEmpty()) {
            throw new DomainException("userId is empty");
        }
        this.userId = userId;
    }


    /***
     * Getters
     */
    public String getEventId() {
        return eventId;
    }

    public String getUserId() {
        return userId;
    }
}
