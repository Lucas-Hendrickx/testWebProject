package domain.db;

import domain.model.Event;
import domain.model.User;
import util.DBConnectionService;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VisitDBSQL implements VisitDB{
    private Connection connection;
    private String schema;

    // Constructor

    public VisitDBSQL() {
        this.connection = DBConnectionService.getDbConnection();
        this.schema = DBConnectionService.getSchema();
    }

    // Functions

    @Override
    public List<Event> getAllEventsOfUser(String userId) {
        if (userId == null) {
            throw new DbException("No userid given");
        }
        List<Event> events = new ArrayList<Event>();
        String location = String.format("%s", this.schema);
        String sql = String.format("Select * From "+location+".visit Inner Join "+location+".event using (eventId) Where userId = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setInt(1, Integer.parseInt(userId));
            ResultSet result =  statementSQL.executeQuery();
            while (result.next()) {
                events.add(makeEvent(result));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return events;
    }

    @Override
    public List<User> getAllUsersOfEvent(String eventId) {
        if (eventId == null) {
            throw new DbException("No eventid given");
        }
        List<User> users = new ArrayList<User>();
        String location = String.format("%s", this.schema);
        String sql = String.format("Select * From "+location+".visit inner join "+location+".user using (userId) Where eventId = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setInt(1, Integer.parseInt(eventId));
            ResultSet result =  statementSQL.executeQuery();
            while (result.next()) {
                users.add(makeUser(result));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public void addUserToEvent(String userId, String eventId) {
        if (eventId == null || userId == null) {
            throw new DbException("No eventid or userId given");
        }
        String sql = String.format("Insert into %s.visit (userId, eventId)  values (?, ?);", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setInt(1, Integer.parseInt(userId));
            statementSQL.setInt(2, Integer.parseInt(eventId));
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void removeUserFromEvent(String userId, String eventId) {
        if (eventId == null || userId == null) {
            throw new DbException("No eventid or userId given");
        }
        String sql = String.format("Delete from %s.visit Where eventid = ? and userid = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setInt(1, Integer.parseInt(eventId));
            statementSQL.setInt(2, Integer.parseInt(userId));
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    // Extra

    public Event makeEvent(ResultSet result) throws SQLException {
        Event event = new Event();
        event.setEventId(result.getString("eventId"));
        event.setName(result.getString("name"));
        String[] dateTime = result.getString("date").split(" ");
        String[] time = dateTime[1].split(":");
        event.setDateFromString(dateTime[0], time[0]+":"+time[1]);
        event.setDuration(result.getInt("duration"));
        event.setAmountOfPeopleAllowed(result.getInt("amountOfPeopleAllowed"));
        return event;
    }

    public User makeUser(ResultSet result) throws SQLException {
        String userId = result.getString("userId");
        String registeredEmail = result.getString("registeredEmail");
        String firstname = result.getString("firstname");
        String lastname = result.getString("lastname");
        String usergroup = result.getString("usergroup");
        return new User(userId, registeredEmail, firstname, lastname, usergroup);
    }

}
