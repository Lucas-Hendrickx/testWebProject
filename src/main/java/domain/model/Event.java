package domain.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
    private String eventId, name;
    private LocalDateTime date;
    private int duration, amountOfPeopleAllowed;

    // Constructors

    public Event(String eventId, String name, LocalDateTime date, int duration, int amountOfPeopleAllowed) {
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
            throw new DomainException("Name is empty");
        }
        this.name = name;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDateFromString(String date, String time) {
        if (date.isEmpty() || time.isEmpty()) {
            throw new DomainException("Date or Time is empty");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date + " " + time, formatter);
        this.date = dateTime;
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

    public LocalDateTime getDate() {
        return date;
    }

    public String getStringDate() {
        String[] stringdate = date.toString().split("T");
        return stringdate[0] +" "+ stringdate[1];
    }

    public int getDuration() {
        return duration;
    }

    public int getAmountOfPeopleAllowed() {
        return amountOfPeopleAllowed;
    }

    // Extra

    @Override
    public String toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                ", amountOfPeopleAllowed=" + amountOfPeopleAllowed +
                '}';
    }
}
