package domain.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CoronaTest {
    private String userId;
    private LocalDateTime dateOfTest;

    // Constructors

    public CoronaTest(String userId, LocalDateTime date) {
        setUserId(userId);
        setDateOfTest(date);
    }

    public CoronaTest() {
    }

    // Setters

    public void setUserId(String userId) {
        if (userId.isEmpty()) {
            throw new DomainException("userId is empty");
        }
        this.userId = userId;
    }

    public void setDateOfTest(LocalDateTime dateOfTest) {
        this.dateOfTest = dateOfTest;
    }

    public void setDateOfTestFromString(String date, String time) {
        if (date.isEmpty() || time.isEmpty()) {
            throw new DomainException("Date or TIme is empty");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date + " " + time, formatter);
        this.dateOfTest = dateTime;
    }


    // Getters

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getDateOfTest() {
        return dateOfTest;
    }


    // Extra

    @Override
    public String toString() {
        return "Corona{" +
                "userId='" + userId + '\'' +
                ", dateOfTest=" + dateOfTest +
                '}';
    }
}
