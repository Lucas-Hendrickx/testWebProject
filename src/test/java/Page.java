import org.openqa.selenium.WebDriver;

public class Page {

    // Lucas Hendrickx r0748690 & Michiel Janssen r0789294

    WebDriver driver;
    String path = "http://localhost:8081/";

    public Page (WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return driver.getTitle();
    }

}