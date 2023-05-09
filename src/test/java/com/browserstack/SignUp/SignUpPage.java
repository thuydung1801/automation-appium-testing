package com.browserstack.SignUp;

import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import org.slf4j.Logger;

public class SignUpPage extends BasePage {
    private static final Logger logger = LogHelper.getLogger();

    public SignUpPage(KeywordWeb keywordWeb) {
        super(keywordWeb);
    }

    //    Accep geoip and cookies
    public void acceptAllCookies() throws InterruptedException {
        keyword.untilJqueryIsDone(60L);
        keyword.scrollToPositionByScript("window.scrollBy(0,300)");
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

    //    --------------------------------------------------------------
    public void goToFormSignup() throws InterruptedException {
        keyword.untilJqueryIsDone(20L);
        keyword.click("SIGNUP_MENU_LEFT");
        keyword.untilJqueryIsDone(30L);
        keyword.verifyElementVisible("ICON_LOGIN");
        keyword.click("ICON_LOGIN");
        keyword.verifyElementVisible("FORM_SIGNUP");
    }

    public void createInformationStep1(boolean checkFistName, String dataName, boolean checkLastName,
                                       boolean checkPhone, String dataPhone, boolean checkEmail, String dataEmail,
                                       boolean checkConfirmEmail, String dataEmailConfirm) throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.click("BTN_CREATE_NEW_ACCOUNT");
        keyword.untilJqueryIsDone(50L);
        if (checkFistName) {
            keyword.sendKeys("SIGNUP_FIST_NAME", dataName);
        }
        if (checkLastName) {
            keyword.sendKeys("SIGNUP_LAST_NAME", dataName);
        }
        if (checkPhone) {
            keyword.sendKeys("phone", dataPhone);
        }
        if (checkEmail) {
            keyword.sendKeys("SIGNUP_EMAIL", dataEmail);
        }
        if (checkConfirmEmail) {
            keyword.sendKeys("SIGNUP_EMAIL_CONFIRM", dataEmailConfirm);
        }
        keyword.click("BTN_NEXT_STEP");
    }

    public void createCustomerExistData() throws InterruptedException {
        createInformationStep1(false, "", false,
                false, "", true, "EMAIL_SIGNUP", true, "EMAIL_SIGNUP");
        keyword.untilJqueryIsDone(50L);
        keyword.assertEquals("CONTENT_REQUIRED_FAILED", "MESSAGE_REQUIRED_FAILED_FRIST_NAME");
        keyword.assertEquals("CONTENT_REQUIRED_FAILED", "MESSAGE_REQUIRED_FAILED_LAST_NAME");
    }

    public void createCustomerEmailInvalid() throws InterruptedException {
        keyword.reLoadPage();
        keyword.untilJqueryIsDone(50L);
        createInformationStep1(true, "Nguyen", true,
                false, "", true, "EMAIL_SIGNUP_INVALID", true, "EMAIL_SIGNUP_INVALID");
        keyword.untilJqueryIsDone(50L);
//        keyword.assertEquals("","");
//        keyword.assertEquals("","");
    }
}
