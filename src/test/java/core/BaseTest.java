package core;
import io.appium.java_client.*;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import io.appium.java_client.safari.SafariDriver;
//import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Parameters;
import java.net.URL;

public class BaseTest {
    private static Logger logger = LogHelper.getLogger();
    protected KeywordWeb keyword;
    public BaseTest() {
        keyword = new KeywordWeb();
    }
    public static IOSDriver iosDriver;
    public static SafariDriver driver;

    //public static WebDriver driver = new SafariDriver();

    public static void setUpRealDevice(String method, String version, String id) {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("automationName", "XCUITest");
        cap.setCapability("platformName", "iOS");
        cap.setCapability("platformVersion", version);
        cap.setCapability("deviceName", "iPhone");
        cap.setCapability("udid", id);
        cap.setCapability("xcodeSigningId", "iPhone Developer");
        cap.setCapability("noReset", true);
//        cap.setCapability("usePrebuiltWDA",true);
        cap.setCapability("derivedDataPath", "/Users/vucuong/Library/Developer/Xcode/DerivedData/WebDriverAgent-aghlrsejdreqngftgvcqwnjgrbou");
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
    public static void setUpSimulator() {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("automationName", "XCUITest");
        cap.setCapability("platformName", "iOS");
        cap.setCapability("platformVersion", "15.2");
        cap.setCapability("deviceName", "iPhone 13");
        cap.setCapability(CapabilityType.BROWSER_NAME, "safari");
        cap.setCapability("udid", "A34F8601-07EE-416D-B969-9B702BE50112");
        cap.setCapability("noReset", true);
        try {
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new SafariDriver(url, cap);
        } catch (Exception e) {
            throw new RuntimeException("Could not create session");
        }
    }
    @BeforeSuite
    public void beforeSuite() throws Exception {
        PropertiesFile.setPropertiesFile();
    }
    public static JavascriptExecutor jse = (JavascriptExecutor) driver;
    @BeforeTest(alwaysRun = true)
    @Parameters("platform")
    public void setUp(String platform) throws InterruptedException {
        switch (platform) {
            case "realDevice":
                setUpRealDevice("web", "16.5.1", "1987bcb8658934de6b03ba2a5a1b8d9c79dda580");
                break;
            case "simulator" :
                setUpSimulator();
                break;
            case "browserStack" :
                break;
        }
        driver.get("https://stage.glamira.co.uk/");
    }
    @AfterTest(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
