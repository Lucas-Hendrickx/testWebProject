package domain.db;

import domain.model.Registered;
import domain.model.User;

import java.util.List;

public interface UserDB {
    /**
     * Stores the given person
     * @param user The person to be added
     * @throws DbException if the given person is null
     * @throws DbException if the given person can not be added
     */
    void addUser(User user);

    /**
     * Removes the person with given userId
     * @param userId The userid of the person that needs to be removed
     * @throws DbException if the given userId is null
     * @throws DbException if the given userId can not be found
     */
    void removeUser(String userId);

    /**
     * Returns the requested person
     * @param registeredEmail The email of the requested person
     * @param firstname The firstname of the requested person
     * @param lastname The lastname of the requested person
     * @throws DbException if the given email is null
     * @throws DbException if the given firstname is null
     * @throws DbException if the given lastname is null
     * @throws DbException if the given email,firstname and lastname can not be found together
     */
    User getUser(String registeredEmail, String firstname, String lastname);

    /**
     * Returns a list with all users
     * @return A List with all users stored in the database
     * @throws DbException if something went wrong
     */
     List<User> getAllUsers();

    /**
     * Returns a list with users with the given email
     * @param email The userid of the requested person
     * @return A List with all users with the given email in the database
     * @throws DbException if the given email is null
     * @throws DbException if the given email can not be found
     */
    List<User> getUsersWithEmail(String email);


}
