import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class Story6_AddCoronaTest {
    private static WebDriver driver;
    private final String path = "http://localhost:8081/Controller";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\School\\Extra Programma's\\Driver\\chromedriver.exe");
        driver = new ChromeDriver();

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        loginTestGebruiker();
    }

    @After
    public void clean() {
        driver.quit();
    }


    @Test
    public void test_RegisterTest_AllFieldsFilledInCorrectly_TestRegistered(){
        ContactPage contactPage = new ContactPage(driver);
        int firstCount = contactPage.getAantalContacten();

        Date today = new Date();
        String date = new SimpleDateFormat("ddMMyyyy").format(today);
        String time = String.valueOf((int) Math.floor(Math.random()*2400));

        contactPage.addContact(date, time, "First Child");
        int secondCount = contactPage.getAantalContacten();


        assertEquals(firstCount + 1,secondCount);
        Assert.assertTrue(contactPage.getTitle().equals("Corona Test"));
    }


    @Test
    public void test_RegisterTest_WordFilledIn_ErrorMessageGiven() {
        String date = "test123";
        ContactPage covidTestPage = new ContactPage(driver);

        covidTestPage.setDate(date);
        covidTestPage.submitInvalid();

        Assert.assertTrue(covidTestPage.hasErrorMessage("Please fill out a value"));
    }


    @Test
    public void test_RegisterTest_EmptyField_ErrorMessageGiven() {
        String date = "";

        ContactPage covidTestPage = new ContactPage(driver);
        covidTestPage.setDate(date);
        covidTestPage.submitInvalid();

        Assert.assertTrue(covidTestPage.hasErrorMessage("Please fill out a value"));
        Assert.assertTrue(covidTestPage.hasEmptyDate());
    }


    public void loginTestGebruiker(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.login("GotContacts@gmail.com", "0400000000");
    }
}
