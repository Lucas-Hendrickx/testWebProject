import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class EventTest {
    private WebDriver driver;
    private String path = "http://localhost:8081/";

    /**
     * Opmerking:
     *
     * Je kan de tests maar 1 keer uitvoeren, daarna moet je onderstaande commands uitvoeren.
     * Dit moet want anders zit het event al vol.
     * Je moet de tests 1 per 1 uitvoeren van boven naar beneden.
     *
     * Delete From web3_project_r0748690.visit Where userid = 1 and eventid = 1;
     * Delete From web3_project_r0748690.visit Where userid = 2 and eventid = 1;
     * Delete From web3_project_r0748690.visit Where userid = 3 and eventid = 1;
     * Delete From web3_project_r0748690.visit Where userid = 4 and eventid = 1;
     */

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\School\\3de_Semester\\Webontwikkeling-3\\Extra\\Chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"/index.jsp");
    }

    @After
    public void clean() {
        driver.quit();
    }

    // Tests

    @Test
    public void test_DontSelectUserAndSendForm_ErrorMessageGiven() {
        loginAndGoToVisitOverview("Korra.Celeste@gaming.com", "gaming");
        WebElement button = driver.findElement(By.id("addUserToEvent"));
        button.click();

        String error = driver.findElement(By.xpath("/html/body/div/div[2]/div/ul/li")).getText();
        assertEquals("You first need to select a user.", error);
    }

    @Test
    public void test_UserSendAndEventNotFull_UserIsAdded() {
        loginAndGoToVisitOverview("Hendrickxjan@telenet.be", "ff");
        addUserToEventForm("Simon Hendrickx");

        assertTrue(findUserInOverview("Simon", "Hendrickx", "Toppers"));
    }

    @Test
    public void test_AddUserWhoIsAllreadyAdded_ErrorMessageGiven_UserNotAddedAgain() {
        loginAndGoToVisitOverview("Korra.Celeste@gaming.com", "gaming");
        addUserToEventForm("Lise Leys");
        addUserToEventForm("Lise Leys");

        String error = driver.findElement(By.xpath("/html/body/div/div[2]/div/ul/li")).getText();
        assertEquals("User is already registered in the event", error);
    }

    @Test
    public void test_AddUserWhenEventIsFull_ErrorMessageGiven_UserNotAdded() {
        loginAndGoToVisitOverview("Korra.Celeste@gaming.com", "gaming");
        addUserToEventForm("Sofie Verheyen");
        addUserToEventForm("Sofie Verheyen");

        String error = driver.findElement(By.xpath("/html/body/div/div[2]/div/ul/li")).getText();
        assertEquals("The event is full, you cannot add more users", error);
    }

    // Functions:

    private void fillOutField(String id, String value) {
        WebElement field=driver.findElement(By.id(id));
        field.clear();
        field.sendKeys(value);
    }

    private void submitLoginForm(String email, String password) {
        fillOutField("email", email);
        fillOutField("password", password);
        WebElement button = driver.findElement(By.id("login"));
        button.click();
    }

    private void addUserToEventForm(String fullname) {
        Select dropdown = new Select(driver.findElement(By.id("user")));
        dropdown.selectByVisibleText(fullname);
        WebElement button = driver.findElement(By.id("addUserToEvent"));
        button.click();
    }

    private void loginAndGoToVisitOverview(String email, String password) {
        submitLoginForm(email, password);
        WebElement link = driver.findElement(By.xpath("//*[@id=\"mySidebar\"]/li[3]/a"));
        link.click();
    }

    private boolean findUserInOverview(String firstname, String lastname, String group) {
        ArrayList<WebElement> listItems = (ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr"));
        boolean found=false;
        for (WebElement listItem : listItems) {
            if (listItem.getText().contains(firstname) && listItem.getText().contains(lastname) && listItem.getText().contains(group)) {
                found=true;
            }
        }
        return found;
    }
}
