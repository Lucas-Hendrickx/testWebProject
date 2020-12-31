import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import static org.junit.Assert.*;

public class Story4_LogoutTest {

    // Lennert Van Oosterwyck r0782485 & Jens Gervais r0782113

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
    public void test_logout_works_when_you_are_logged_in() throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setEmailField("VitsEvemie@chiroKeerbergen.be");
        homePage.setPasswordField("chiro");
        homePage.submitLoginButton();

        assertTrue(homePage.logOutButtonIsPresent());
        homePage.submitLogOutButton();
        assertTrue(homePage.loginButtonIsPresent());

    }

    @Test
    public void test_can_not_logout_when_not_logged_in() {
        HomePage login_logoutPage = PageFactory.initElements(driver, HomePage.class);
        assertFalse(login_logoutPage.logOutButtonIsPresent());
    }
}
