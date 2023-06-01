package core;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
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
//    public BaseTest(KeywordWeb keyword) {
//        this.keyword = keyword;
//    }

    @BeforeSuite
    public void beforeSuite() throws Exception {
        PropertiesFile.setPropertiesFile();
    }
    public static WebDriver driver = new SafariDriver();

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
//        driver.navigate().to("https://stage.glamira.co.uk/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
