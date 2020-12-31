package myProject;

import static org.junit.Assert.assertTrue;

import domain.model.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class CoronaAtEventTest {
    private WebDriver driver;
    private String path = "http://localhost:8081/";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\School\\Extra Programma's\\Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"/index.jsp");
    }

    @After
    public void clean() {
        driver.quit();
    }


    // Tests


    @Test
    public void Test_Check_if_event_has_safe_as_state() {
        /*
            Stappenplan:

            1) Login als admin
            2) Maak event in 2010 (waar geen corona tests zijn)
            3) Doe controle

            Cleanup:

            1) Remove event
         */

        submitLoginForm("VitsEvemie@chiroKeerbergen.be", "chiro");
        submitEventForm("Verjaardag Lucas", "28042010", "1400", "3", "25");
        Boolean isSafe = findStateOfEventAndRemove("Verjaardag Lucas");

        assertTrue(isSafe);
    }


 /*   @Test
    public void Test_Check_if_event_has_danger_as_state() {
        *//*
            Stappenplan:

            1) Login als admin
            2) Maak event in 2015

            3) Login als guardian
            4) voeg kind toe aan event
            5) registreer coronatest op zelfde moment
            6) Login als admin
            7) check safe
            8) remove event

            Cleanup:

            1) Remove event
         *//*
        submitLoginForm("VitsEvemie@chiroKeerbergen.be", "chiro");
        submitEventForm("Verjaardag Lucas", "28042015", "1400", "3", "25");
        logout();

        submitRegisteredForm("TestTwee@hotmail.com", "Test", "Twee", "0491221675", "testwachtwoord");
        submitLoginForm("TestTwee@hotmail.com", "testwachtwoord");
        submitUserForm("Kind", "Een");

        // Add User to event

        // Add Corona test

        // Add
    }
*/


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

    private void submitEventForm(String name, String date, String time, String duration, String amount) {
        navigateTo("//*[@id=\"mySidebar\"]/li[2]/a");
        fillOutField("eventName", name);
        fillOutField("eventDate", date);
        fillOutField("eventTime", time);
        fillOutField("eventDuration", duration);
        fillOutField("eventAmount", amount);
        WebElement button = driver.findElement(By.id("eventCreate"));
        button.click();
    }

    private void submitRegisteredForm(String email, String firstname, String lastname, String gsmnumber, String password) {
        navigateTo("//*[@id=\"mySidebar\"]/li[2]/a");
        fillOutField("registeredEmail", email);
        fillOutField("registeredFirstname", firstname);
        fillOutField("registeredLastname", lastname);
        fillOutField("registeredGsmnumber", gsmnumber);
        fillOutField("registeredPassword", password);
        WebElement button = driver.findElement(By.id("registeredSignup"));
        button.click();
    }

    private void submitUserForm(String firstname, String lastname) {
        navigateTo("//*[@id=\"mySidebar\"]/li[2]/a");
        fillOutField("userFirstname", firstname);
        fillOutField("userLastname", lastname);
        Select dropdown = new Select(driver.findElement(By.id("user")));
        dropdown.selectByVisibleText("Kerels");
        WebElement button = driver.findElement(By.id("userSignup"));
        button.click();
    }

    private void logout() {
        navigateTo("//*[@id=\"mySidebar\"]/li[1]/a");
        WebElement logoutButton = driver.findElement(By.id("logout"));
    }

    private void navigateTo(String xpath) {
        WebElement button = driver.findElement(By.xpath(xpath));
        button.click();
    }

    private Boolean findStateOfEventAndRemove(String name) {
        int teller = 2;
        while (true) {
            String eventName = driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr["+teller+"]/td[1]")).getText();
            if (name.equals(eventName)) {
                String isSafe = driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr["+teller+"]/td[5]")).getText();
                removeEvent(teller);
                return isSafe.equals("Safe");
            }
            teller++;
            System.out.println("poging");
        }
    }

    private void removeEvent(int location) {
        WebElement detailsButton = driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr["+location+"]/td[6]/a"));
        detailsButton.click();
        WebElement removeButton = driver.findElement(By.id("removeEvent"));
        removeButton.click();
    }

}
