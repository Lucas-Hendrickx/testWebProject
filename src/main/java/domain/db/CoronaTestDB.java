package domain.db;

import domain.model.CoronaTest;
import domain.model.Registered;
import domain.model.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public interface CoronaTestDB {
    /**
     * Adds the coronatest to the database
     * @param coronatest The coronatest to be added
     * @throws DbException if the given coronatest is null
     * @throws DbException if the given coronatest can not be added
     */
    void addCoronaTest(CoronaTest coronatest);

    /**
     * Returns a HashMap with  date of the test and the user
     * @param email The email of the guardian
     * @return A HashMap with key the date of the test and value the user who got tested.
     * @throws DbException if the given email is null
     * @throws DbException if the given email can not be found
     */
    HashMap<String, User> getAllCoronaTestsOfRegistered(String email);

    /**
     * Returns a list with all coronatests of all the users
     * @return A HashMap with key the date of the test and value the user who got tested.
     * @throws DbException if something went wrong
     */
    HashMap<String, User> getAllCoronaTests();

    /**
     * Searches for all contacts who were at an event with somebody who tested positive
     * @param userid The userid of the person who tested positive
     * @param dateoftest The date of when the person got tested positive
     * @param daysaftertest The amount of days we should check after the date of the test
     * @return A HashMap with key the Registered and key the User
     * @throws DbException if the given userid is null
     * @throws DbException if the given userid can not be found
     * @throws DbException if the given dateoftest is null
     * @throws DbException if the given daysaftertest isn't positive
     */
    HashMap<User, Registered> searchContactsOfUser(String userid, LocalDateTime dateoftest, int daysaftertest);
}
