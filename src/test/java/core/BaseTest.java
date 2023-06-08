package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private static Logger logger = LogHelper.getLogger();
    protected KeywordWeb keyword;

    public BaseTest() {
        keyword = new KeywordWeb();
    }
    @BeforeSuite
    public void beforeSuite() throws Exception {
        PropertiesFile.setPropertiesFile();
    }
    public static WebDriver driver = new SafariDriver();

    @BeforeTest(alwaysRun = true)
    public void setUp() throws Exception {
//        driver.get("https://stage.glamira.co.uk/");
        driver.navigate().to("https://stage.glamira.com/");
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
