package domain.service;

import domain.db.RegisteredDB;
import domain.db.RegisteredDBSQL;
import domain.model.Registered;

import java.util.List;

public class RegisteredService {
    private RegisteredDB db = new RegisteredDBSQL();

    public void addRegistered(Registered registered) {
        db.addRegistered(registered);
    }

    public Registered getRegistered(String email) {
        return db.getRegistered(email);
    }

    public List<Registered> getAllRegistered() {
        return db.getAllRegistered();
    }

    public void removeRegistered(String email) {
        db.removeRegistered(email);
    }

    public void changeRegisteredPassword(String email, String password) {
        db.changeRegisteredPassword(email, password);
    }

}
