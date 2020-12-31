package myProject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterTest {
    private WebDriver driver;
    private String path = "http://localhost:8081/";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\School\\Extra Programma's\\Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"/form.jsp");
    }

    @After
    public void clean() {
        driver.quit();
    }


    @Test
    public void test_Register_AllFieldsFilledInCorrectly_UserIsRegistered() {
        String firstnumber= String.valueOf(Math.floor(Math.random()*100));

        submitForm("jan.janssens"+firstnumber.charAt(0)+"@hotmail.com", "Jan", "Janssens", "0499221656", "Jan");

        String title = driver.getTitle();
        assertEquals("Home",title);


        assertEquals("The account is successfully created.", driver.findElement(By.xpath("/html/body/div/div[2]/div/p")).getText());
    }


    @Test
    public void test_Register_FirstNameNotFilledIn_ErrorMessageGivenForFirstNameAndOtherFieldsValueKept(){
        submitForm("jan.janssens@hotmail.com", "", "Janssens", "0491221675", "1234");

        String title = driver.getTitle();
        assertEquals("Sign Up",title);

/*      Werkt niet meer door JavaScript validatie op de client side

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No firstname given", errorMsg.getText());
*/

        WebElement fieldFirstName=driver.findElement(By.id("registeredFirstname"));
        assertEquals("",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("registeredLastname"));
        assertEquals("Janssens",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("registeredEmail"));
        assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
    }


    @Test
    public void test_Register_LastNameNotFilledIn_ErrorMessageGivenForLastNameAndOtherFieldsValueKept(){
        submitForm("jan.janssens@hotmail.com", "Jan", "", "0491221675", "1234");

        String title = driver.getTitle();
        assertEquals("Sign Up",title);

/*      Werkt niet meer door JavaScript validatie op de client side

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No last name given", errorMsg.getText());
        */

        WebElement fieldFirstName=driver.findElement(By.id("registeredFirstname"));
        assertEquals("Jan",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("registeredLastname"));
        assertEquals("",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("registeredEmail"));
        assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
    }


    @Test
    public void test_Register_EmailNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        submitForm("", "Jan", "Janssens", "0491221675", "1234");

        String title = driver.getTitle();
        assertEquals("Sign Up",title);

/*      Werkt niet meer door JavaScript validatie op de client side

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No email given", errorMsg.getText());
*/


        WebElement fieldFirstName=driver.findElement(By.id("registeredFirstname"));
        assertEquals("Jan",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("registeredLastname"));
        assertEquals("Janssens",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("registeredEmail"));
        assertEquals("",fieldEmail.getAttribute("value"));
    }

    @Test
    public void test_Register_PasswordNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        submitForm("jan.janssens@hotmail.com", "Jan", "Janssens", "0491221675", "");

        String title = driver.getTitle();
        assertEquals("Sign Up",title);


    /*  Werkt niet meer door JavaScript validatie op de client side

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No password given", errorMsg.getText());
    */

        WebElement fieldFirstName=driver.findElement(By.id("registeredFirstname"));
        assertEquals("Jan",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("registeredLastname"));
        assertEquals("Janssens",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("registeredEmail"));
        assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
    }

    @Test
    public void test_Register_UserAlreadyExists_ErrorMessageGiven(){
        String firstnumber= String.valueOf(Math.floor(Math.random()*10));
        String secondnumber= String.valueOf(Math.floor(Math.random()*10));

        submitForm("pieter.pieters"+firstnumber.charAt(0)+"@hotmail.com", "Pieter", "Pieters", "0474608269", "Pieter");

        driver.get(path+"/form.jsp");

        submitForm("pieter.pieters"+firstnumber.charAt(0)+"@hotmail.com", "Pieter", "Pieters", "0474608269", "Pieter");

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("User already exists", errorMsg.getText());
    }



    private void fillOutField(String name,String value) {
        WebElement field=driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

    private void submitForm(String email, String firstname, String lastname, String gsmnumber, String password) {
        fillOutField("registeredEmail", email);
        fillOutField("registeredFirstname", firstname);
        fillOutField("registeredLastname", lastname);
        fillOutField("registeredGsmnumber", gsmnumber);
        fillOutField("registeredPassword", password);

        WebElement button = driver.findElement(By.id("registeredSignup"));
        button.click();
    }

}
