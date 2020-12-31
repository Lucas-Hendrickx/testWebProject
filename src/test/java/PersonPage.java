
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by  Reinoud Berkein
 */

public class PersonPage extends Page{

    @FindBy(id="firstName")
    private WebElement firstNameField;

    @FindBy(id="lastName")
    private WebElement lastNameField;

    @FindBy(id="email")
    private WebElement emailField;

    public PersonPage(WebDriver driver) {
        super(driver);
    }

    public void setFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public boolean hasStickyFirstName (String firstName) {
        return firstName.equals(firstNameField.getAttribute("value"));
    }

    public boolean hasStickyLastName (String lastname) {
        return lastname.equals(lastNameField.getAttribute("value"));
    }

    public boolean hasStickyEmail (String email) {
        return email.equals(emailField.getAttribute("value"));
    }

    public boolean hasEmptyFirstName () {
        return firstNameField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyLastName () {
        return lastNameField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyEmail () {
        return emailField.getAttribute("value").isEmpty();
    }
}

