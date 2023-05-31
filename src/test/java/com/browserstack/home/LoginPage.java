package com.browserstack.home;

import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import org.slf4j.Logger;

public class LoginPage extends BasePage {
    private static Logger logger = LogHelper.getLogger();
    public LoginPage(){ super(); }
    public LoginPage(KeywordWeb key){
        super(key);
    }


    public void login(String email, String password) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click("LOGIN_MENULEFT");
        Thread.sleep(2000);
        keyword.untilJqueryIsDone(50L);
        keyword.click("LOGIN_BTN_LOGIN");
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("LOGIN_TXT_EMAIL", email);
        Thread.sleep(2000);
        keyword.sendKeys("LOGIN_TXT_PASSWORD", password);
        Thread.sleep(2000);
        keyword.click("LOGIN_BTN_SUBMIT_LOGIN");
        keyword.untilJqueryIsDone(50L);
    }
    public void acceptAllCookies() throws InterruptedException {
        Thread.sleep(10000);
        logger.info("accept All Cookies");
        keyword.untilJqueryIsDone(60L);
        keyword.webDriverWaitForElementPresent("BTN_COOKIES", 50);
        if (keyword.verifyElementPresent("BTN_COOKIES")) {
            keyword.untilJqueryIsDone(60L);
            Thread.sleep(5000);
            keyword.click("BTN_COOKIES");
        }
    }

    public void chooseLanguages() throws InterruptedException {
        Thread.sleep(10000);
        logger.info("choose language");
        keyword.untilJqueryIsDone(60L);
        keyword.scrollToPositionByScript("window.scrollBy(0,300)");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        if (keyword.verifyElementPresent("BTN_LANGUAGE")) {
            Thread.sleep(5000);
            keyword.click("BTN_LANGUAGE");
        }
    }
}