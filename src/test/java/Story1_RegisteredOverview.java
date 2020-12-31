import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class Story1_RegisteredOverview {

    // Jerome de Meester & Robbe Vanluyten

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
    public void test_Overview_NotLoggedIn_ShowsError() {
        RegisteredOverviewPage registeredOverviewPage = PageFactory.initElements(driver, RegisteredOverviewPage.class);
        assertEquals("Home", registeredOverviewPage.getTitle());
        assertTrue(registeredOverviewPage.hasErrorMessage("You have insufficient rights to have a look at the page"));
    }

    @Test
    public void test_Overview_LoggedInAsUser_ShowsError() {
        RegisteredOverviewPage personOverviewPage = PageFactory.initElements(driver, RegisteredOverviewPage.class);
        assertEquals("Home", personOverviewPage.getTitle());
        assertTrue(personOverviewPage.hasErrorMessage("You have insufficient rights to have a look at the page"));
    }

    @Test
    public void test_Overview_LoggedInAsAdmin_ShowsOverviewPage() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.loginAsAdmin();
        RegisteredOverviewPage registeredOverviewPage = PageFactory.initElements(driver, RegisteredOverviewPage.class);
        assertEquals("Overview", registeredOverviewPage.getTitle());
        assertTrue(registeredOverviewPage.containsUserWithEmail("Hendrickxjan@telenet.be"));
        assertTrue(registeredOverviewPage.containsUserWithFirstName("Jan"));
        assertTrue(registeredOverviewPage.containsUserWithLastName("Hendrickx"));
    }

}
