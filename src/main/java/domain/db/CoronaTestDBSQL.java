package domain.db;

import domain.model.CoronaTest;
import domain.model.Registered;
import domain.model.User;
import util.DBConnectionService;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class CoronaTestDBSQL implements CoronaTestDB{
    private Connection connection;
    private String schema;


    /***
     * Constructor
     */
    public CoronaTestDBSQL() {
        this.connection = DBConnectionService.getDbConnection();
        this.schema = DBConnectionService.getSchema();
    }


    /***
     * Functions
     */
    @Override
    public void addCoronaTest(CoronaTest coronatest) {
        if (coronatest == null) {
            throw new DbException("Nothing to add.");
        }
        String sql = String.format("Insert into %s.coronatest (userid, dateoftest)" +
                " values (?, ?::timestamp without time zone)", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setInt(1, Integer.parseInt(coronatest.getUserId()));
            statementSQL.setString(2, coronatest.getDateOfTest().toString());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public HashMap<String, User> getAllCoronaTestsOfRegistered(String email) {
        if (email == null) {
            throw new DbException("No email given");
        }
        HashMap<String, User> data = new HashMap<>();
        String location = String.format("%s", this.schema);
        String sql = String.format("Select * From "+location+".coronatest c Inner Join "+location+".user u " +
                        "using(userid) Where registeredemail = ? Order By dateoftest", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, email);
            ResultSet result =  statementSQL.executeQuery();
            while (result.next()) {
                data.put(result.getString("dateoftest"), makeUser(result));
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return data;
    }

    @Override
    public HashMap<String, User> getAllCoronaTests() {
        HashMap<String, User> data = new HashMap<>();
        String location = String.format("%s", this.schema);
        String sql = String.format("Select * From "+location+".coronatest c Inner Join "+location+".user u " +
                "using(userid) Order By dateoftest desc", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            ResultSet result =  statementSQL.executeQuery();
            while (result.next()) {
                data.put(result.getString("dateoftest"), makeUser(result));
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return data;
    }

    @Override
    public HashMap<User, Registered> searchContactsOfUser(String userid, LocalDateTime dateoftest, int daysaftertest) {
        HashMap<User, Registered> data = new HashMap<>();
        String location = String.format("%s", this.schema);
        String sql = String.format( "Select Distinct email, gsmnumber, r.firstname as guardianF, r.lastname as guardianL, " +
                                            "u.firstname as userF, u.lastname as userL, usergroup " +
                                    "From "+location+".visit v Inner Join "+location+".user u using(userid) Inner Join " +
                                            location+".registered r on r.email = u.registeredemail " +
                                    "Where userid <> ? and eventid in ( " +
                                        "Select eventid " +
                                        "From "+location+".event e Inner Join "+location+".visit v using(eventid) " +
                                        "Where userid = ? and date > ?::timestamp without time zone and date < ?::timestamp " +
                                                "without time zone ) Order By email", this.schema );
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setInt(1, Integer.parseInt(userid));
            statementSQL.setInt(2, Integer.parseInt(userid));
            statementSQL.setString(3, dateoftest.toString());
            LocalDateTime afterdate = dateoftest.plus(daysaftertest, ChronoUnit.DAYS);
            statementSQL.setString(4, afterdate.toString());

            ResultSet result = statementSQL.executeQuery();

            while (result.next()) {
                Object[] created = createData(result);
                data.put((User) created[0], (Registered) created[1]);
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return data;
    }

    @Override
    public HashMap<String, User> getAllCoronaTestsBetweenDates(Timestamp fromDate, Timestamp untilDate) {
        HashMap<String, User> data = new HashMap<>();
        String location = String.format("%s", this.schema);
        String sql = String.format("Select * From "+location+".coronatest c Inner Join "+location+".user u " +
                "using(userid) Where dateoftest between ? and ? Order By dateoftest desc", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setTimestamp(1, fromDate);
            statementSQL.setTimestamp(2, untilDate);
            ResultSet result =  statementSQL.executeQuery();
            while (result.next()) {
                data.put(result.getString("dateoftest"), makeUser(result));
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return data;
    }

    @Override
    public HashMap<String, User> getAllCoronaTestsOfRegisteredBetweenDates(String email, Timestamp fromDate, Timestamp untilDate) {
        HashMap<String, User> data = new HashMap<>();
        String location = String.format("%s", this.schema);
        String sql = String.format("Select * From "+location+".coronatest c Inner Join "+location+".user u " +
                "using(userid) Where registeredemail = ? and dateoftest between ? and ? Order By dateoftest", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, email);
            statementSQL.setTimestamp(2, fromDate);
            statementSQL.setTimestamp(3, untilDate);
            System.out.println(statementSQL);
            ResultSet result =  statementSQL.executeQuery();
            while (result.next()) {
                data.put(result.getString("dateoftest"), makeUser(result));
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return data;
    }

    @Override
    public void removeCoronaTestsOfUser(String userid) {
        if (userid == null) {
            throw new DbException("No userid given");
        }
        String sql = String.format("Delete From %s.coronatest Where userid = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setInt(1, Integer.parseInt(userid));
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    /***
     * Extra
     */
    public User makeUser(ResultSet result) throws SQLException {
        User user = new User();
        user.setUserId(result.getString("userid"));
        user.setFirstname(result.getString("firstname"));
        user.setLastname(result.getString("lastname"));
        user.setUsergroup(result.getString("usergroup"));
        return user;
    }

    public Object[] createData(ResultSet result) throws SQLException {
        User user = new User();
        user.setFirstname(result.getString("userF"));
        user.setLastname(result.getString("userL"));
        user.setRegisteredEmail(result.getString("email"));
        user.setUsergroup(result.getString("usergroup"));

        Registered registered = new Registered();
        registered.setFirstname(result.getString("guardianF"));
        registered.setLastname(result.getString("guardianL"));
        registered.setGsmnumber(result.getString("gsmnumber"));

        Object[] created = {user, registered};
        return created;
    }
}
