package domain.db;

import domain.model.Registered;

import java.util.List;

public interface RegisteredDB {
    /**
     * Stores the given person
     * @param registered The person to be added
     * @throws DbException if the given person is null
     * @throws DbException if the given person can not be added
     */
    void addRegistered(Registered registered);

    /**
     * Returns the requested person
     * @param email The email of the requested person
     * @throws DbException if the given email is null
     * @throws DbException if the given email can not be found
     */
    Registered getRegistered(String email);

    /**
     * Returns a list with all registered
     * @return A List with all registered stored in the database
     * @throws DbException if something went wrong
     */
    List<Registered> getAllRegistered();

    /**
     * Removes the person with given email
     * @param email The email of the person that needs to be removed
     * @throws DbException if the given email is null
     * @throws DbException if the given email can not be found
     */
    void removeRegistered(String email);

    /**
     * Changes password of the given person
     * @param email The email of the person whose password will be changed
     * @param password The new password of the person
     * @throws DbException if the given email is null
     * @throws DbException if the given email can not be found
     * @throws DbException if the given password is null
     */
    void changeRegisteredPassword(String email, String password);
}
