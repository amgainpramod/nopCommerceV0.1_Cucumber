package testbaseclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import utilities.Constants;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    private static WebDriver driver = null;

    //Singleton
    //Only one instance of the class can exist at a time
    private static final WebDriverFactory instance = new WebDriverFactory();

    private WebDriverFactory() {

    }

    public static WebDriverFactory getInstance() {
        return instance;
    }

    private static ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<>();
    private static ThreadLocal<String> threadedBrowser = new ThreadLocal<>();

    public WebDriver getDriver(String browser) {
        if(driver != null){
            return driver;
        }
        setDriver(browser);
        threadedBrowser.set(browser);
        if (threadedDriver.get() == null) {
            try {
                if (browser.equalsIgnoreCase(Constants.FIREFOX)) {
                    FirefoxOptions options = setFirefoxOptions();
                    driver = new FirefoxDriver(options);
                    threadedDriver.set(driver);
                }
                if (browser.equalsIgnoreCase(Constants.CHROME)) {
                    ChromeOptions options = setChromeOptions();
                    driver = new ChromeDriver(options);
                    threadedDriver.set(driver);
                }
                if (browser.equalsIgnoreCase(Constants.IExplorer)) {
                    InternetExplorerOptions options = setInternetExplorerOptions();
                    driver = new InternetExplorerDriver(options);
                    threadedDriver.set(driver);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            threadedDriver.get().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            threadedDriver.get().manage().window().maximize();
        }
        return threadedDriver.get();
    }

    public String getBrowser(){
        return threadedBrowser.get();
    }

    public void quitDriver() {
        threadedDriver.get().quit();
        threadedDriver.set(null);
        driver = null;
    }

    private void setDriver(String browser) {
        String driverPath = "";
        String os = Constants.OS_NAME.toLowerCase().substring(0, 3);
        System.out.println("OS name from system property :: " + os);
        String directory = Constants.USER_DIRECTORY + Constants.DRIVERS_DIRECTORY;
        String driverKey = "";
        String driverValue = "";

        if (browser.equalsIgnoreCase(Constants.FIREFOX)) {
            driverKey = Constants.GECKO_DRIVER_KEY;
            driverValue = Constants.GECKO_DRIVER_VALUE;
        }
        else if (browser.equalsIgnoreCase(Constants.CHROME)) {
            driverKey = Constants.CHROME_DRIVER_KEY;
            driverValue = Constants.CHROME_DRIVER_VALUE;
        }
        else if(browser.equalsIgnoreCase(Constants.IExplorer)){
            driverKey = Constants.IE_DRIVER_KEY;
            driverValue = Constants.IE_DRIVER_VALUE;
        } else {
            System.out.println("Browser type not supported");
        }

        driverPath = directory + driverValue + (os.equals("win") ? ".exe" : "");
        System.out.println("Driver Binary :: " + driverPath);
        System.setProperty(driverKey, driverPath);
    }

    private ChromeOptions setChromeOptions(){
        ChromeOptions options = new ChromeOptions();

        //This is to disable info-bar at the start of every test
//        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
//        options.setExperimentalOption("useAutomationExtension", false);
        return options;
    }

    private FirefoxOptions setFirefoxOptions(){
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
        return options;
    }

    private InternetExplorerOptions setInternetExplorerOptions(){
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
        options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
        options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
        options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
        options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, false);
        return options;
    }
}
