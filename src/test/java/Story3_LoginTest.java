import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Story3_LoginTest {

    // Lucas Hendrickx r0748690 & Michiel Janssen r0789294

    private WebDriver driver;
    private String path = "http://localhost:8081/Controller";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\School\\Extra Programma's\\Driver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_All_Fields_Are_Filled_In_Correct() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setEmailField("VitsEvemie@chiroKeerbergen.be");
        homePage.setPasswordField("chiro");

        homePage.submitLoginButton();

        assertEquals("Home", homePage.getTitle());
        assertTrue(homePage.containsWelcomeText("Welcome Evemie"));
    }

}
