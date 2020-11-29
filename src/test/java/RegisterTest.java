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
        System.setProperty("webdriver.chrome.driver", "C:\\School\\3de_Semester\\Webontwikkeling-3\\Extra\\Chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"/form.jsp");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Register_AllFieldsFilledInCorrectly_UserIsRegistered() {
        submitForm("jan.janssens@hotmail.com", "Jan", "Janssens", "0499221655", "Jan");

        String title = driver.getTitle();
        assertEquals("Overview",title);

        driver.get(path+"Controller?command=Overview");

        ArrayList<WebElement> listItems=(ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains("jan.janssens@hotmail.com") && listItem.getText().contains("Jan")) {
                found=true;
            }
        }
        assertTrue(found);
    }

    @Test
    public void test_Register_UseridNotFilledIn_ErrorMessageGivenForUseridAndOtherFieldsValueKept(){
        submitForm("", "Jan", "Janssens", "jan.janssens@hotmail.com", "1234");

        String title = driver.getTitle();
        assertEquals("Sign Up",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No userid given", errorMsg.getText());

        WebElement fieldUserid=driver.findElement(By.id("userid"));
        assertEquals("",fieldUserid.getAttribute("value"));

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("Jan",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("Janssens",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
    }

    @Test
    public void test_Register_FirstNameNotFilledIn_ErrorMessageGivenForFirstNameAndOtherFieldsValueKept(){
        submitForm("jakke", "", "Janssens", "jan.janssens@hotmail.com", "1234");

        String title = driver.getTitle();
        assertEquals("Sign Up",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No firstname given", errorMsg.getText());

        WebElement fieldUserid=driver.findElement(By.id("userid"));
        assertEquals("jakke",fieldUserid.getAttribute("value"));

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("Janssens",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
    }

    @Test
    public void test_Register_LastNameNotFilledIn_ErrorMessageGivenForLastNameAndOtherFieldsValueKept(){
        submitForm("jakke", "Jan", "", "jan.janssens@hotmail.com", "1234");

        String title = driver.getTitle();
        assertEquals("Sign Up",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No last name given", errorMsg.getText());

        WebElement fieldUserid=driver.findElement(By.id("userid"));
        assertEquals("jakke",fieldUserid.getAttribute("value"));

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("Jan",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
    }

    @Test
    public void test_Register_EmailNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        submitForm("jakke", "Jan", "Janssens", "", "1234");

        String title = driver.getTitle();
        assertEquals("Sign Up",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No email given", errorMsg.getText());

        WebElement fieldUserid=driver.findElement(By.id("userid"));
        assertEquals("jakke",fieldUserid.getAttribute("value"));

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("Jan",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("Janssens",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("",fieldEmail.getAttribute("value"));
    }

    @Test
    public void test_Register_PasswordNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        submitForm("jakke", "Jan", "Janssens", "jan.janssens@hotmail.com", "");

        String title = driver.getTitle();
        assertEquals("Sign Up",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No password given", errorMsg.getText());

        WebElement fieldUserid=driver.findElement(By.id("userid"));
        assertEquals("jakke",fieldUserid.getAttribute("value"));

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("Jan",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("Janssens",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
    }

    @Test
    public void test_Register_UserAlreadyExists_ErrorMessageGiven(){
        submitForm("pieter.pieters@hotmail.com", "Pieter", "Pieters", "0474608259", "Pieter");

        driver.get(path+"/form.jsp");

        submitForm("pieter.pieters@hotmail.com", "Pieter", "Pieters", "0474608259", "Pieter");

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("User already exists", errorMsg.getText());

/*        WebElement fieldUserid=driver.findElement(By.id("userid"));
        assertEquals(useridRandom,fieldUserid.getAttribute("value"));*/

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("Pieter",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("Pieters",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("pieter.pieters@hotmail.com",fieldEmail.getAttribute("value"));
    }



    private void fillOutField(String name,String value) {
        WebElement field=driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

    private void submitForm(String email, String firstname, String lastname, String gsmnumber, String password) {
        fillOutField("email", email);
        fillOutField("firstname", firstname);
        fillOutField("lastname", lastname);
        fillOutField("gsmnumber", gsmnumber);
        fillOutField("password", password);

        WebElement button = driver.findElement(By.id("signUp"));
        button.click();
    }

}
