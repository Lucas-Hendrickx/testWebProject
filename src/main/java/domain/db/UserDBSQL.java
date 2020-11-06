package domain.db;

import domain.model.User;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDBSQL implements UserDB {
    private Connection connection;
    private String schema;

    // Constructor

    public UserDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }

    // Functions

    @Override
    public void addUser(User user) {
        if (user == null) {
            throw new DbException("Nothing to add.");
        }
        String sql = String.format("Insert into %s.user (registeredEmail, firstname, lastname, usergroup)" +
                "values (?, ?, ?, ?)", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, user.getRegisteredEmail());
            statementSQL.setString(2, user.getFirstname());
            statementSQL.setString(3, user.getLastname());
            statementSQL.setString(4, user.getUsergroup());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void removeUser(String userId) {
        if (userId == null) {
            throw new DbException("No email given");
        }
        String sql = String.format("Delete From %s.user Where userid = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setInt(1, Integer.parseInt(userId));
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public User getUser(String registeredEmail, String firstname, String lastname) {
        if (registeredEmail == null || firstname == null || lastname == null) {
            throw new DbException("email, firstname and or lastname were empty");
        }
        User user = new User();
        String sql = String.format("Select * From %s.user Where registeredEmail = ? and firstname = ? and lastname = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, registeredEmail);
            statementSQL.setString(2, firstname);
            statementSQL.setString(3, lastname);
            ResultSet result = statementSQL.executeQuery();
            if (result.next()) {
                user = makeUser(result);
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        String sql = String.format("Select * From %s.user", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            ResultSet result = statementSQL.executeQuery();
            while (result.next()) {
                users.add(makeUser(result));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public List<User> getUsersWithEmail(String email) {
        if (email == null) {
            throw new DbException("No email given.");
        }
        List<User> users = new ArrayList<User>();
        String sql = String.format("Select * From %s.user Where registeredEmail = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, email);
            ResultSet result = statementSQL.executeQuery();
            while (result.next()) {
                users.add(makeUser(result));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return users;
    }

    // Extra

    public User makeUser(ResultSet result) throws SQLException {
        String userId = result.getString("userId");
        String registeredEmail = result.getString("registeredEmail");
        String firstname = result.getString("firstname");
        String lastname = result.getString("lastname");
        String usergroup = result.getString("usergroup");
        return new User(userId, registeredEmail, firstname, lastname, usergroup);

    }
}
