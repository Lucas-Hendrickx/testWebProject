package domain.model;

import java.util.Date;

public class Event {
    private String eventId, name;
    private Date date;
    private int duration, amountOfPeopleAllowed;

    // Constructors

    public Event(String eventId, String name, Date date, int duration, int amountOfPeopleAllowed) {
        setEventId(eventId);
        setName(name);
        setDate(date);
        setDuration(duration);
        setAmountOfPeopleAllowed(amountOfPeopleAllowed);
    }

    public Event() {
    }

    // Setters

    public void setEventId(String eventId) {
        if (eventId.isEmpty()) {
            throw new DomainException("eventId is empty");
        }
        this.eventId = eventId;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            throw new DomainException("name is empty");
        }
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDuration(int duration) {
        if (duration <= 0) {
            throw new DomainException("The duration is not a positive number");
        }
        this.duration = duration;
    }

    public void setAmountOfPeopleAllowed(int amountOfPeopleAllowed) {
        if (amountOfPeopleAllowed <= 0) {
            throw new DomainException("The amount of people allowed is not a positive number");
        }
        this.amountOfPeopleAllowed = amountOfPeopleAllowed;
    }
    
    // Getters

    public String getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }

    public int getAmountOfPeopleAllowed() {
        return amountOfPeopleAllowed;
    }
}
