package com.browserstack;

import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;

public class SeleniumTest {
    public WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new SafariDriver();
        driver.navigate().to("https://www.glamira.co.uk/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
