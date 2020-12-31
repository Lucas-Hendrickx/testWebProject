package domain.db;

import domain.model.Event;
import domain.service.Service;
import util.DBConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDBSQL implements EventDB{
    private Connection connection;
    private String schema;
    private Service service = new Service();


    /***
     * Constructor
     */
    public EventDBSQL() {
        this.connection = DBConnectionService.getDbConnection();
        this.schema = DBConnectionService.getSchema();
    }


    /***
     * Functions
     */
    @Override
    public void addEvent(Event event) {
        if (event == null) {
            throw new DbException("Nothing to add.");
        }
        String sql = String.format("Insert into %s.event (name, date, duration, amountofpeopleallowed)" +
                " values (?, ?::timestamp without time zone, ?::int, ?::int)", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, event.getName());
            statementSQL.setString(2, event.getDate().toString());
            statementSQL.setString(3, Integer.toString(event.getDuration()));
            statementSQL.setString(4, Integer.toString(event.getAmountOfPeopleAllowed()));
            statementSQL.execute();
        }  catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void removeEvent(String eventId) {
        if (eventId == null) {
            throw new DbException("No eventid given");
        }
        String sql = String.format("Delete From %s.event Where eventid = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setInt(1, Integer.parseInt(eventId));
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

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

    @Override
    public List<String> getEventsWithCoronaPresent() {
        List<String> eventids = new ArrayList<>();
        String location = String.format("%s", this.schema);
        String sql = String.format("Select eventid From "+location+".event, "+location+".coronatest " +
                "Where date > dateoftest and date < dateoftest + interval '1' day * 28 Group By eventid", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            ResultSet result = statementSQL.executeQuery();
            while (result.next()) {
                eventids.add(result.getString("eventId"));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return eventids;
    }


    /***
     * Extra
     */
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
}
