/**
 * Created by  Reinoud Berkein
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage extends PersonPage {

    @FindBy(id="userid")
    private WebElement userIdField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id="signUp")
    private WebElement btnSignUp;

    public SignUpPage(WebDriver driver) {
        super(driver);
        getDriver().get(getPath()+"?action=register");
    }

    public void setUserId(String userId) {
        userIdField.clear();
        userIdField.sendKeys(userId);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public HomePage submitValid() {
        btnSignUp.click();
        return PageFactory.initElements(getDriver(), HomePage.class);
    }

    public void submitInvalid() {
        btnSignUp.click();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = getDriver().findElement(By.cssSelector("div.error > div > p"));
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasStickyUserId (String UserId) {
        return UserId.equals(userIdField.getAttribute("value"));
    }

    public boolean hasEmptyUserId () {
        return userIdField.getAttribute("value").isEmpty();
    }


}