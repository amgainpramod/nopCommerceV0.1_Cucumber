package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import pageclasses.LoginPage;
import pageclasses.NavigationPage;
import testbaseclass.WebDriverFactory;

public  class BaseTest {
    public WebDriver driver;
    public static NavigationPage navigationPage;
    public static LoginPage loginPage;

    @Before
    public void commonSetUp(){
        driver = WebDriverFactory.getInstance().getDriver("chrome");
        driver.get("http://admin-demo.nopcommerce.com/login");
        loginPage = new LoginPage(driver);
        navigationPage = new NavigationPage(driver);
    }

    @After
    public void commonTearDown(){
        WebDriverFactory.getInstance().quitDriver();

    }
}
