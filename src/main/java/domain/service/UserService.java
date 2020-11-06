package domain.service;

import domain.db.UserDB;
import domain.db.UserDBSQL;
import domain.model.User;

import java.util.List;

public class UserService {
    private UserDB db = new UserDBSQL();

    public void addUser(User user) {
        db.addUser(user);
    }

    public void removeUser(String userId) {
        db.removeUser(userId);
    }

    public User getUser(String registeredEmail, String firstname, String lastname) {
        return db.getUser(registeredEmail, firstname, lastname);
    }

    public List<User> getAllUsers() {
        return db.getAllUsers();
    }

    public List<User> getUsersWithEmail(String email) {
        return db.getUsersWithEmail(email);
    }

}
