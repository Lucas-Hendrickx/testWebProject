package domain.model;

public class User {
    private String userId, registeredEmail, firstname, lastname, usergroup;


    /***
     * Constructors
     */
    public User(String userId, String registeredEmail, String firstname, String lastname, String usergroup) {
        setUserId(userId);
        setRegisteredEmail(registeredEmail);
        setFirstname(firstname);
        setLastname(lastname);
        setUsergroup(usergroup);
    }

    public User() {
    }


    /***
     * Setters
     */
    public void setUserId(String userId) {
        if (userId.isEmpty()) {
            throw new DomainException("No userId given");
        }
        this.userId = userId;
    }

    public void setRegisteredEmail(String registeredEmail) {
        if (registeredEmail.isEmpty()) {
            throw new DomainException("No email given");
        }
        this.registeredEmail = registeredEmail;
    }

    public void setFirstname(String firstname) {
        if(firstname.isEmpty()){
            throw new DomainException("No firstname given");
        }
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        if(lastname.isEmpty()){
            throw new DomainException("No lastname given.");
        }
        this.lastname = lastname;
    }

    public void setUsergroup(String usergroup) {
        if(usergroup == null){
            throw new DomainException("No usergroup given");
        }
        this.usergroup = usergroup;
    }


    /***
     * Getters
     */
    public String getUserId() {
        return userId;
    }

    public String getRegisteredEmail() {
        return registeredEmail;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsergroup() {
        return usergroup;
    }


    /***
     * Extra
     */
    @Override
    public String toString(){
        return getFirstname() + " " + getLastname();
    }
}
