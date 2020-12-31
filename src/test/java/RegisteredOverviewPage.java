import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class RegisteredOverviewPage extends Page{
    /***
     * Jerome de Meester & Robbe Vanluyten
     */


    /***
     * Constructor
     */
    public RegisteredOverviewPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"Controller?command=Open_Overview");
    }


    /***
     * Functions
     */
    public boolean containsUserWithEmail(String email) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(email)) {
                found=true;
            }
        }
        return found;
    }

    public boolean containsUserWithFirstName(String firstName) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(firstName)) {
                found=true;
            }
        }
        return found;
    }

    public boolean containsUserWithLastName(String lastName) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(lastName)) {
                found=true;
            }
        }
        return found;
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector(".alert-danger"));
        return (message.equals(errorMsg.getText()));
    }

}
