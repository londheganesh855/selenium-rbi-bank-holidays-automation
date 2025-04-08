package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.Constants;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        switch (Constants.BROWSER.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver",
                        Constants.CHROME_DRIVER_PATH);
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver",
                        Constants.CHROME_DRIVER_PATH);
                driver = new FirefoxDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver",
                        Constants.CHROME_DRIVER_PATH);
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser not supported: " + Constants.BROWSER);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));
        // driver.get(Constants.BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
