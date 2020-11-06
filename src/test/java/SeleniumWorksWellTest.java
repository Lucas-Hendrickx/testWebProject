import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class SeleniumWorksWellTest {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\School\\3de_Semester\\Webontwikkeling-3\\Extra\\Chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://nl.wikipedia.org/wiki/Hoofdpagina");
    }


    @After
    public void clean(){
        driver.quit();
    }

    @Test
    public void browserVindtWikipedia() {
        assertEquals("Wikipedia, de vrije encyclopedie", driver.getTitle());
    }

    @Test
    public void wikipediaVindtSelenium() {
        WebElement field = driver.findElement(By.id("searchInput"));
        field.clear();
        field.sendKeys("selenium");
        WebElement link = driver.findElement(By.id("searchButton"));
        link.click();

        assertEquals("Selenium - Wikipedia", driver.getTitle());

        assertEquals("Selenium", driver.findElement(By.tagName("h1")).getText());

    }

}
