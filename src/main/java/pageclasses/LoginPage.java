package pageclasses;

import basepage.CustomDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CustomDriver {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "Email")
    @CacheLookup
    WebElement EMAIL_FIELD;

    @FindBy(id = "Password")
    @CacheLookup
    WebElement PASSWORD_FIELD;

    @FindBy(xpath = "//button[text() = 'Log in']")
    @CacheLookup
    WebElement LOGIN_BUTTON;

    @FindBy(xpath = "//div[@class='title']/strong[contains(text(), 'Welcome, please sign in!')]")
    @CacheLookup
    WebElement LOGIN_PAGE_TITLE;

    public String getPageTitle(){
        //return LOGIN_PAGE_TITLE.getText();
        return LOGIN_PAGE_TITLE.getText();

    }

    public void setEmailAndPassword(String email, String password){
        sendData(EMAIL_FIELD, email, "Email Field", true);
        sendData(PASSWORD_FIELD, password, "Password Field", true);

    }

    public NavigationPage clickLogin(){
        elementClick(LOGIN_BUTTON, "Login Button");

        return new NavigationPage(driver);
    }



}
