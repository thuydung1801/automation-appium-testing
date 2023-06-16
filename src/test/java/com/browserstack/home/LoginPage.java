package com.browserstack.home;

import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import org.slf4j.Logger;

public class LoginPage extends BasePage {
    private static final Logger logger = LogHelper.getLogger();
    public LoginPage(KeywordWeb keywordWeb){
        super(keywordWeb);
    }

    public void login(String email, String password) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(7000);
        keyword.click("LOGIN_MENU_LEFT");
        keyword.untilJqueryIsDone(50L);
        keyword.click("MOBILE_BTN_LOGIN");
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("LOGIN_EMAIL_TXT", email);
        keyword.sendKeys("LOGIN_PASSWORD_TXT", password);
        keyword.click("LOGIN_BTN");
        keyword.untilJqueryIsDone(50L);
    }

    public void acceptAllCookies() throws InterruptedException {
        Thread.sleep(5000);
        keyword.untilJqueryIsDone(60L);
        keyword.scrollToPositionByScript("window.scrollBy(0,200)");
        keyword.untilJqueryIsDone(60L);
        chooseLanguages();
        keyword.webDriverWaitForElementPresent("BTN_COOKIES", 50);
        if (keyword.verifyElementPresent("BTN_COOKIES")) {
            keyword.untilJqueryIsDone(60L);
            Thread.sleep(5000);
            keyword.click("BTN_COOKIES");
        }
    }

    public void chooseLanguages() throws InterruptedException {
        logger.info("choose language");
        Thread.sleep(7000);
        if (keyword.verifyElementPresent("LOGIN_BTN_LANGUAGE")) {
            Thread.sleep(7000);
            keyword.click("LOGIN_BTN_LANGUAGE");
        }
    }
}