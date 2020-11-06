package domain.db;

import domain.model.Event;
import domain.model.User;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VisitDBSQL implements VisitDB{
    private Connection connection;
    private String schema;

    // Constructor

    public VisitDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }

    // Functions

    @Override
    public List<Event> getAllEventsOfUser(String userId) {
        if (userId == null) {
            throw new DbException("No userid given");
        }
        List<Event> events = new ArrayList<Event>();
        String sql = String.format("Select * From %s.visit Inner Join %s.event Where userId = ?", this.schema);
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

    // Extra

    public Event makeEvent(ResultSet result) throws SQLException {
        String eventId = result.getString("eventId");
        String name = result.getString("name");
        Date date = result.getDate("date");
        int duration = result.getInt("duration");
        int amountOfPeopleAllowed = result.getInt("amountOfPeopleAllowed");
        return new Event(eventId, name, date, duration, amountOfPeopleAllowed);
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
