package core;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.appium.java_client.safari.SafariDriver;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class BaseTest {
    private static Logger logger = LogHelper.getLogger();
    protected KeywordWeb keyword;

    public static IOSDriver iosDriver;
    public static SafariDriver driver;
    public BaseTest() {
        keyword = new KeywordWeb();
    }

    public static void setUpRealDevice(String method, String version, String id) {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("automationName", "XCUITest");
        cap.setCapability("platformName", "iOS");
        cap.setCapability("platformVersion", version);
        cap.setCapability("deviceName", "iPhone");
        cap.setCapability("udid", id);
        cap.setCapability("xcodeSigningId", "iPhone Developer");
        cap.setCapability("noReset", true);
        // cap.setCapability("usePrebuiltWDA",true);
        cap.setCapability("derivedDataPath", "/Users/phamvanthai/Library/Developer/Xcode/DerivedData/WebDriverAgent-aghlrsejdreqngftgvcqwnjgrbou");
        try {
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            if(method.equals("web")) {
                cap.setCapability(CapabilityType.BROWSER_NAME,"safari");
                driver = new SafariDriver(url, cap);

            }
            else {
                cap.setCapability("bundleId", "com.apple.mobilesafari");
                iosDriver = new IOSDriver(url, cap);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not create session");
        }

    }
    @BeforeSuite
    public void beforeSuite() throws Exception {
        PropertiesFile.setPropertiesFile();
    }

    public static JavascriptExecutor jse = driver;

    @BeforeTest(alwaysRun = true)
    public void setUp() throws Exception {
        //setUpRealDevice("web","12.5.7","fb5764acced8b08f7f3ba94b1068d90836687d6b");
        setUpRealDevice("web","16.5.1","1987bcb8658934de6b03ba2a5a1b8d9c79dda580");
        driver.navigate().to("https://stage.glamira.co.uk/");
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
