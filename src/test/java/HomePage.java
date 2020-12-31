import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Page{
    /***
     *  Combinatie van:
     *  Lucas Hendrickx r0748690 & Michiel Janssen r0789294
     *  Lennert Van Oosterwyck r0782485 & Jens Gervais r0782113
     *  Jerome de Meester & Robbe Vanluyten
     */


    /***
     * FindBy
     */
    @FindBy(id="email")
    private WebElement emailField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id="login")
    private WebElement loginButton;


    /***
     * Constructor
     */
    public HomePage(WebDriver driver){
        super(driver);
        this.driver.get(getPath()+"Controller?command=Open_Index");
    }


    /***
     * Setters
     */
    public void setEmailField(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void setPasswordField(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }


    /***
     * Functions
     */
    public boolean loginButtonIsPresent(){
        try {
            WebElement webElement = driver.findElement(By.id("login"));
            return true;
        }catch (NoSuchElementException e) {
            return false;
        }
    }

    public void submitLoginButton() {
        loginButton.click();
        PageFactory.initElements(driver, HomePage.class);
    }

    public boolean logOutButtonIsPresent(){
        try {
            WebElement webElement = driver.findElement(By.id("logout"));
            return true;
        }catch (NoSuchElementException e) {
            return false;
        }
    }

    public void submitLogOutButton() {
        WebElement buttonLogin = driver.findElement(By.id("logout"));
        buttonLogin.submit();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }

    public void submitInvalid() {
        loginButton.click();
    }

    public boolean hasStickyEmail(String email) {
        return email.equals(emailField.getAttribute("value"));
    }

    public boolean containsWelcomeText(String message) {
       WebElement welcomeElement = driver.findElement(By.xpath("/html/body/div/div[2]/h2[1]"));
       return message.equals(welcomeElement.getText());
    }

    public void loginAsAdmin() {
        setEmailField("VitsEvemie@chiroKeerbergen.be");
        setPasswordField("chiro");
        submitLoginButton();
    }

    public void login(String email, String password) {
        setEmailField(email);
        setPasswordField(password);
        submitLoginButton();
    }

    public void goToContact(){
        WebElement webElement = driver.findElement(By.linkText("CoronaTest"));
        webElement.click();
    }
}

