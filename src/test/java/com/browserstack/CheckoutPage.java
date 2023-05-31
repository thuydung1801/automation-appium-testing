package com.browserstack;

import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import org.slf4j.Logger;

public class CheckoutPage extends BasePage {
    private static final Logger logger = LogHelper.getLogger();

    public CheckoutPage(KeywordWeb keywordWeb){
        super(keywordWeb);
    }


    public void login(String email, String password) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click("LOGIN_MENULEFT");
        Thread.sleep(2000);
        keyword.untilJqueryIsDone(50L);
        keyword.click("MOBILE_BTN_LOGIN");
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("LOGIN_TXT_EMAIL", email);
        Thread.sleep(2000);
        keyword.sendKeys("LOGIN_TXT_PASSWORD", password);
        Thread.sleep(2000);
        keyword.click("LOGIN_BTN_SUBMITLOGIN");
        keyword.untilJqueryIsDone(50L);
    }

    public void acceptAllCookies() throws InterruptedException {
        keyword.untilJqueryIsDone(60L);
        keyword.untilJqueryIsDone(60L);
        keyword.scrollToPositionByScript("window.scrollBy(0,300)");
        keyword.untilJqueryIsDone(60L);
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
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(10000);
        if (keyword.verifyElementPresent("LOGIN_BTN_LANGUAGE")) {
            Thread.sleep(10000);
            keyword.click("LOGIN_BTN_LANGUAGE");
        }
    }


}
