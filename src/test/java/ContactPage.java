import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

public class ContactPage extends Page{

    public ContactPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"Controller?command=Open_CoronaTest");
    }

    // Part 1

    public void setDate(String date) {
        WebElement dateField = driver.findElement(By.id("coronatestDate"));
        dateField.clear();
        dateField.sendKeys(date);
    }

    public ContactPage submitValid() {
        WebElement btnSubmit = driver.findElement(By.id("createCoronaTest"));
        btnSubmit.click();
        return PageFactory.initElements(getDriver(), ContactPage.class);
    }

    public void submitInvalid() {
        WebElement btnSubmit = driver.findElement(By.id("createCoronaTest"));
        btnSubmit.click();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = getDriver().findElement(By.cssSelector("#error-for-coronatestDate"));
        System.out.println(errorMsg.getText());
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasStickyDate (String date){
        WebElement dateField = driver.findElement(By.id("coronatestDate"));
        return date.equals(dateField.getAttribute("value"));
    }

    public boolean hasEmptyDate(){
        WebElement dateField = driver.findElement(By.id("coronatestDate"));
        return dateField.getAttribute("value").isEmpty();
    }

    // Part 2

    public int getAantalContacten(){
        ArrayList<WebElement> contactlist = (ArrayList<WebElement>)  driver.findElements(By.id("contactlist"));
        return contactlist.size();
    }

    public void addContact(String date, String time, String user) {
        submitForm(date, time, user);
    }

    private void fillOutField(String name, String value) {
        WebElement field = driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

    private void submitForm(String date, String time, String user) {
        fillOutField("coronatestDate", date);
        fillOutField("coronatestTime", time);
        Select dropdown = new Select(driver.findElement(By.id("user")));
        dropdown.selectByVisibleText(user);
        WebElement button = driver.findElement(By.id("createCoronaTest"));
        button.click();
    }

    public void goToHome(){
        WebElement webElement = driver.findElement(By.linkText("Home"));
        webElement.click();
    }
}
