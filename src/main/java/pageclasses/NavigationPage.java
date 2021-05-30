package pageclasses;

import basepage.CustomDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationPage extends CustomDriver {
    public WebDriver driver;

    public NavigationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h4[contains(text(),'Welcome to your store!')]")
    @CacheLookup
    WebElement NAV_PAGE_TITLE;

    @FindBy(xpath = "//a[@href='/logout']")
    @CacheLookup
    WebElement LOGOUT_LINK;

    public String getPageTitle(){
        return NAV_PAGE_TITLE.getText();
    }

    public LoginPage logOut(){
        elementClick(LOGOUT_LINK, "Logout Link");

        return new LoginPage(driver);
    }
}
