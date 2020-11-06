package domain.db;

import domain.model.Event;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDBSQL implements EventDB{
    private Connection connection;
    private String schema;

    // Constructor

    public EventDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }

    // Functions

    @Override
    public Event getEvent(String eventId) {
        if (eventId == null) {
            throw new DbException("No eventId given");
        }
        Event event = new Event();
        String sql = String.format("Select * From %s.event Where eventId = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setInt(1, Integer.parseInt(eventId));
            ResultSet result = statementSQL.executeQuery();
            if (result.next()) {
                event = makeEvent(result);
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return event;
    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<Event>();
        String sql = String.format("Select * From %s.event", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            ResultSet result = statementSQL.executeQuery();
            while (result.next()) {
                events.add(makeEvent(result));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return events;
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
}
