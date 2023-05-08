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


    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {

        driver.navigate().to("https://www.glamira.co.uk/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
