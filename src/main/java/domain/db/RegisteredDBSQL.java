package domain.db;

import domain.model.Registered;
import domain.model.Role;
import util.DBConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegisteredDBSQL implements RegisteredDB {
    private Connection connection;
    private String schema;


    /***
     * Constructor
     */
    public RegisteredDBSQL() {
        this.connection = DBConnectionService.getDbConnection();
        this.schema = DBConnectionService.getSchema();
    }


    /***
     * Functions
     */
    @Override
    public void addRegistered(Registered registered) {
        if (registered == null) {
            throw new DbException("Nothing to add.");
        }
        String sql = String.format("Insert Into %s.registered (firstname, lastname, email, gsmnumber, password) " +
                "Values (?, ?, ?, ?, ?)", this.schema);
        try {
            PreparedStatement statementSQL  = connection.prepareStatement(sql);
            statementSQL.setString(1, registered.getFirstname());
            statementSQL.setString(2, registered.getLastname());
            statementSQL.setString(3, registered.getEmail());
            statementSQL.setString(4, registered.getGsmnumber());
            statementSQL.setString(5, registered.getPassword());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public Registered getRegistered(String email) {
        if (email == null) {
            throw new DbException("No email given");
        }
        Registered registered = new Registered();
        String sql = String.format("Select * From %s.registered Where email = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, email);
            ResultSet result = statementSQL.executeQuery();
            if (result.next()) {
                registered = makeRegistered(result);
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return registered;
    }

    @Override
    public List<Registered> getAllRegistered() {
        List<Registered> allRegistered = new ArrayList<Registered>();
        String sql = String.format("Select * From %s.registered", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            ResultSet result = statementSQL.executeQuery();
            while (result.next()) {
                allRegistered.add(makeRegistered(result));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return allRegistered;
    }

    @Override
    public void removeRegistered(String email) {
        if (email == null) {
            throw new DbException("No email given");
        }
        String sql = String.format("Delete From %s.registered Where email = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, email);
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void changeRegisteredPassword(String email, String password) {
        if (email == null) {
            throw new DbException("No email given");
        } if (password == null) {
            throw new DbException("No password given");
        }
        String sql = String.format("Update %s.registered Set password = ? Where email = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, password);
            statementSQL.setString(2, email);
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }


    /***
     * Extra
     */
    public Registered makeRegistered(ResultSet result) throws SQLException {
        Registered registered = new Registered();
        registered.setFirstname(result.getString("firstname"));
        registered.setLastname(result.getString("lastname"));
        registered.setEmail(result.getString("email"));
        registered.setGsmnumber(result.getString("gsmnumber"));
        registered.setPassword(result.getString("password"));
        registered.setRole(Role.valueOf(result.getString("role").toUpperCase()));
        return registered;
    }
}
