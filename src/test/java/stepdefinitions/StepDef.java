package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageclasses.LoginPage;
import pageclasses.NavigationPage;

public class StepDef {

    public WebDriver driver;
    private NavigationPage navigationPage;
    private LoginPage loginPage;

    public StepDef() {
        loginPage = BaseTest.loginPage;
        navigationPage = BaseTest.navigationPage;
    }

    @Given("User launches Chrome Browser")
    public void userLaunchesChromeBrowser() {
//        System.setProperty(Constants.CHROME_DRIVER_KEY, Constants.USER_DIRECTORY + Constants.DRIVERS_DIRECTORY
//                + Constants.CHROME_DRIVER_VALUE);
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        loginPage = new LoginPage(driver);
//        navigationPage = new NavigationPage(driver);
    }
    @Given("User is at login page")
    public void userIsAtLoginPage() {
    }

//    @When("User Opens URL {string}")
//    public void userOpensURL(String url) {
////        driver.get(url);
//    }

    @When("User enters Email as {string} and password as {string}")
    public void userEntersEmailAsAndPasswordAs(String email, String password) {
        loginPage.setEmailAndPassword(email, password);
    }

    @And("Clicks on login")
    public void clicksOnLogin() {
        loginPage.clickLogin();
    }

    @Then("Navigation Page title should be {string}")
    public void navigationPageTitleShouldBe(String expTitle) throws InterruptedException {
        String actualTitle = navigationPage.getPageTitle();
        Assert.assertEquals(expTitle, actualTitle);
        Thread.sleep(3000);
    }

    @When("User clicks on log out link")
    public void userClicksOnLogOutLink(){
        navigationPage.logOut();
    }

    @Then("login page title should be {string}")
    public void loginPageTitleShouldBe(String expTitle) {
        String actualTitle = loginPage.getPageTitle();
        Assert.assertEquals(expTitle, actualTitle);
    }

//    @And("close browser")
//    public void closeBrowser() {
//        driver.quit();
//    }


}
