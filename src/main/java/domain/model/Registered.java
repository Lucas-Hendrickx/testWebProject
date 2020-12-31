package domain.model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registered {
    private String firstname, lastname, email, gsmnumber, password;
    private Role role;


    /***
     * Constructors
     */
    public Registered(String firstname, String lastname, String email, String gsmnumber, String password, Role role) {
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setGsmnumber(gsmnumber);
        setPassword(password);
        setRole(role);
    }

    public Registered() {
    }


    /***
     * Setters
     */
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

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEmail(String email) {
        if(email.isEmpty()){
            throw new DomainException("No email given");
        }
        String USERID_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new DomainException("Email not valid");
        }
        this.email = email;
    }

    public void setGsmnumber(String gsmnumber) {
        if (gsmnumber.isEmpty()) {
            throw new DomainException("No gsmnumber given");
        }
        this.gsmnumber = gsmnumber;
    }


    /***
     * Getters
     */
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getGsmnumber() {
        return gsmnumber;
    }

    public Role getRole(){
        return role;
    }


    /***
     * Password
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password.isEmpty()){
            throw new DomainException("No password given");
        }
        this.password = password;
    }

    public void setHashedPassword(String password) {
        if (password.isEmpty()) {
            throw new DomainException("No password given");
        }
        this.password = hashPassword(password);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-512");
            crypt.reset();
            byte[] passwordBytes = password.getBytes("UTF-8");
            crypt.update(passwordBytes);
            byte[] digest = crypt.digest();
            BigInteger digestAsBigInteger = new BigInteger(1, digest);
            return digestAsBigInteger.toString(16);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new DomainException(e.getMessage(), e);
        }
    }

    public boolean isCorrectPassword(String password) {
        if(password.isEmpty()){
            throw new DomainException("No password given");
        }
        return getPassword().equals(hashPassword(password));
    }


    /***
     * Extra
     */
    @Override
    public String toString(){
        return getFirstname() + " " + getLastname() + ": " + getEmail();
    }
}
