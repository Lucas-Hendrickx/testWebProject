import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CoronaAtEventTest {
    private WebDriver driver;
    private String path = "http://localhost:8081/";

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

    /**
     * Opmerking:
     *
     * Deze test werkt enkel als er maar 3 corona besmettingen zijn.
     * De eerste event is safe en de tweede danger
     */

    @Test
    public void Test_Check_if_event_has_safe_as_state() {
        submitLoginForm("Korra.Celeste@gaming.com", "gaming");

        // Getting all data from coronatests:
        navigateTo("//*[@id=\"mySidebar\"]/li[4]/a");
        LocalDateTime[] testdates = getDatesOfTests();
        LocalDateTime[] datesPeriodAfterTest = getDatesWithPeriod(testdates, 28);

        // Getting all data from event:
        navigateTo("//*[@id=\"mySidebar\"]/li[5]/a");
        LocalDateTime eventdate = getDate("/html/body/div/div[2]/table/tbody/tr[2]/td[2]", "yyyy-MM-dd HH:mm");
        Boolean safe = getState("/html/body/div/div[2]/table/tbody/tr[2]/td[5]");

        // Checking the data:
        Boolean check = true;
        int counter = 0;
        while (counter < 3) {
            if (eventdate.isAfter(testdates[counter]) && eventdate.isBefore(datesPeriodAfterTest[counter])) {
                check = false;
            }
            counter ++;
        }
        assertEquals(check, safe);
    }

    @Test
    public void Test_Check_if_event_has_danger_as_state() {
        submitLoginForm("Korra.Celeste@gaming.com", "gaming");

        // Getting all data from coronatests:
        navigateTo("//*[@id=\"mySidebar\"]/li[4]/a");
        LocalDateTime[] testdates = getDatesOfTests();
        LocalDateTime[] datesPeriodAfterTest = getDatesWithPeriod(testdates, 28);

        // Getting all data from event:
        navigateTo("//*[@id=\"mySidebar\"]/li[5]/a");
        LocalDateTime eventdate = getDate("/html/body/div/div[2]/table/tbody/tr[3]/td[2]", "yyyy-MM-dd HH:mm");
        Boolean safe = getState("/html/body/div/div[2]/table/tbody/tr[3]/td[5]");

        System.out.println(eventdate +" - "+ safe);

        // Checking the data:
        Boolean check = true;
        int counter = 0;
        while (counter < 3) {
            if (eventdate.isAfter(testdates[counter]) && eventdate.isBefore(datesPeriodAfterTest[counter])) {
                check = false;
            }
            counter ++;
        }
        assertEquals(check, safe);
    }


    // Functions

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

    private void navigateTo(String xpath) {
        WebElement button = driver.findElement(By.xpath(xpath));
        button.click();
    }

    private LocalDateTime[] getDatesOfTests() {
        LocalDateTime[] testdates = {null, null, null};
        int amountoftests = 2;
        while (amountoftests < 5) {
            LocalDateTime dateTime = getDate("/html/body/div/div[2]/table/tbody/tr["+amountoftests+"]/td[1]", "yyyy-MM-dd HH:mm:00");
            testdates[amountoftests-2] = dateTime;
            amountoftests ++;
        }
        return testdates;
    }

    private LocalDateTime getDate(String xpath, String format) {
        String stringdate = driver.findElement(By.xpath(xpath)).getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(stringdate, formatter);
    }

    private Boolean getState(String xpath) {
        String state = driver.findElement(By.xpath(xpath)).getText();
        if (state.equals("Safe")) return true;
        return false;
    }

    private LocalDateTime[] getDatesWithPeriod(LocalDateTime[] testdates, int days) {
        LocalDateTime[] datesPeriodAfterTest = {null, null, null};
        int location = 0;
        for (LocalDateTime testdate : testdates) {
            datesPeriodAfterTest[location] = testdate.plus(days, ChronoUnit.DAYS);
            location++;
        }
        return datesPeriodAfterTest;
    }

}
