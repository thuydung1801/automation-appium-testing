package com.browserstack.SignUp;

import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import core.PropertiesFile;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;

import java.util.Date;

public class SignUpPage extends BasePage {
    private static final Logger logger = LogHelper.getLogger();

    public SignUpPage(KeywordWeb keywordWeb) {
        super(keywordWeb);
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
        if (keyword.verifyElementPresent("BTN_CREATE_NEW_ACCOUNT")) {
            keyword.untilJqueryIsDone(30L);
            keyword.click("BTN_CREATE_NEW_ACCOUNT");
        }
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

    public void createInformationSFormPassword(String dataPassword) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementVisible("REGISTER_FORM");
        keyword.sendKeys("PASSWORD_SIGNUP", dataPassword);
        keyword.untilJqueryIsDone(50L);
        keyword.click("ACCEPT_AGREE");
        keyword.untilJqueryIsDone(50L);
        keyword.click("BTN_CREATE_ACCOUNT");
        keyword.untilJqueryIsDone(50L);
    }

    public void confirmPasswordEntryCondition(String titleError, String Message, String characters, String number, String lowerLetter, String upperLetter, String charactersLike, String checkElement) throws InterruptedException {
        keyword.assertEquals(titleError, Message);
        keyword.assertEquals("SIGNUP_EXPECTED_MESSAGE_PASSWORD_01", characters);
        keyword.assertEquals("SIGNUP_EXPECTED_MESSAGE_PASSWORD_05", number);
        keyword.assertEquals("SIGNUP_EXPECTED_MESSAGE_PASSWORD_02", lowerLetter);
        keyword.assertEquals("SIGNUP_EXPECTED_MESSAGE_PASSWORD_03", upperLetter);
        keyword.assertEquals("SIGNUP_EXPECTED_MESSAGE_PASSWORD_07", charactersLike);
        keyword.webDriverWaitForElementPresent(checkElement, 10);
    }
    public void clearTextAndSendKey(String clearText, String inputSendKey, String dataSendKey) throws InterruptedException {
        keyword.clearText(clearText);
        keyword.sendKeys(inputSendKey, dataSendKey);
    }
    public void createCustomerExistData() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        createInformationStep1(false, "", false,
                false, "", true, "EMAIL_SIGNUP", true, "EMAIL_SIGNUP");
        keyword.untilJqueryIsDone(50L);
        keyword.assertEquals("CONTENT_REQUIRED_FAILED", "MESSAGE_REQUIRED_FAILED_FRIST_NAME");
        keyword.assertEquals("CONTENT_REQUIRED_FAILED", "MESSAGE_REQUIRED_FAILED_LAST_NAME");
    }

    public void createCustomerEmailInvalid() throws InterruptedException {
        keyword.reLoadPage();
        Thread.sleep(5000);
        keyword.untilJqueryIsDone(50L);
        createInformationStep1(true, "Nguyen", true,
                false, "", true, "EMAIL_SIGNUP_INVALID", true, "EMAIL_SIGNUP_INVALID");
        keyword.untilJqueryIsDone(50L);
        keyword.assertEquals("CONTENT_MESSAGE_ERROR", "EMAIL_ERROR");
        keyword.assertEquals("CONTENT_MESSAGE_ERROR", "EMAIL_CONFIRM_ERROR");
    }

    public void createCustomerEmailNotSame() throws InterruptedException {
        keyword.reLoadPage();
        Thread.sleep(5000);
        createInformationStep1(true, "Nguyen", true,
                false, "", true, "EMAIL_SIGNUP", true, "EMAIL_SIGNUP_NOT_SAME");
        keyword.assertEquals("CONTENT_MESSAGE_EMAIL_NOT_SAME", "EMAIL_CONFIRM_ERROR");
    }

    public void createCustomerEmailExist() throws InterruptedException {
        keyword.reLoadPage();
        Thread.sleep(5000);
        createInformationStep1(true, "Nguyen", true,
                false, "", true, "EMAIL_EXIST_ON_STORE", true, "EMAIL_EXIST_ON_STORE");
        keyword.assertEquals("CONTENT_MESSAGE_EMAIL_EXIST", "MESSAGE_EMAIL_EXIST");
    }

    public void passwordAtLeast8character() throws InterruptedException {
        keyword.sendKeys("PASSWORD_SIGNUP","PASSWORD_LEAST_8_CHARACTER");
        confirmPasswordEntryCondition("SIGNUP_MESSAGE_PASSWORD_FAIL01",
                "SIGNUP_ACTUAL_MESSAGE01", "SIGNUP_ACTUAL_MESSAGE04", "SIGNUP_ACTUAL_MESSAGE_AT_LAST_NUMBER",
                "SIGNUP_ACTUAL_MESSAGE_AT_LAST_LOWER", "SIGNUP_ACTUAL_MESSAGE_AT_LAST_UPPER", "SIGNUP_ACTUAL_MESSAGE_AT_LAST_CHARACTERS",
                "SIGNUP_ACTUAL_MESSAGE04"
        );
    }
    public void passwordAtLeastNumber()throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        clearTextAndSendKey("SIGNUP_PASSWORD_INFORMATION", "SIGNUP_PASSWORD_INFORMATION", "SIGNUP_CREATE_PASSWORD_FAIL_02");
        keyword.untilJqueryIsDone(50L);
        confirmPasswordEntryCondition("SIGNUP_MESSAGE_PASSWORD_FAIL01",
                "SIGNUP_ACTUAL_MESSAGE01", "SIGN_MESSAGE_CHARACTERS", "SIGNUP_MESSAGE_ERROR_NUMBER",
                "SIGNUP_ACTUAL_MESSAGE_AT_LAST_LOWER", "SIGNUP_ACTUAL_MESSAGE_AT_LAST_UPPER", "SIGNUP_ACTUAL_MESSAGE_AT_LAST_CHARACTERS",
                "SIGNUP_MESSAGE_ERROR_NUMBER"
        );
    }
    public void confirmPasswordEntryConditionLowerLetter() throws InterruptedException {
        clearTextAndSendKey("SIGNUP_PASSWORD_INFORMATION", "SIGNUP_PASSWORD_INFORMATION", "SIGNUP_CREATE_PASSWORD_FAIL_04");
        confirmPasswordEntryCondition("SIGNUP_MESSAGE_PASSWORD_FAIL01",
                "SIGNUP_ACTUAL_MESSAGE01", "SIGN_MESSAGE_CHARACTERS", "SIGNUP_ACTUAL_MESSAGE_AT_LAST_NUMBER",
                "SIGNUP_MESSAGE_ERROR_LOWER_LETTER", "SIGNUP_ACTUAL_MESSAGE_AT_LAST_UPPER", "SIGNUP_ACTUAL_MESSAGE_AT_LAST_CHARACTERS",
                "SIGNUP_MESSAGE_ERROR_LOWER_LETTER"
        );
    }
    public void confirmPasswordEntryConditionLowerUpper() throws InterruptedException {
        clearTextAndSendKey("SIGNUP_PASSWORD_INFORMATION", "SIGNUP_PASSWORD_INFORMATION", "SIGNUP_CREATE_PASSWORD_FAIL_05");
        confirmPasswordEntryCondition("SIGNUP_MESSAGE_PASSWORD_FAIL01",
                "SIGNUP_ACTUAL_MESSAGE01", "SIGN_MESSAGE_CHARACTERS", "SIGNUP_ACTUAL_MESSAGE_AT_LAST_NUMBER",
                "SIGNUP_ACTUAL_MESSAGE_AT_LAST_LOWER", "SIGNUP_MESSAGE_ERROR_UPPER_LETTER", "SIGNUP_ACTUAL_MESSAGE_AT_LAST_CHARACTERS",
                "SIGNUP_MESSAGE_ERROR_UPPER_LETTER"
        );
    }
    public void confirmPasswordEntryConditionCharactersLike() throws InterruptedException {
        clearTextAndSendKey("SIGNUP_PASSWORD_INFORMATION", "SIGNUP_PASSWORD_INFORMATION", "SIGNUP_CREATE_PASSWORD_FAIL_06");
        confirmPasswordEntryCondition("SIGNUP_MESSAGE_PASSWORD_FAIL01",
                "SIGNUP_ACTUAL_MESSAGE01", "SIGN_MESSAGE_CHARACTERS", "SIGNUP_ACTUAL_MESSAGE_AT_LAST_NUMBER",
                "SIGNUP_ACTUAL_MESSAGE_AT_LAST_LOWER", "SIGNUP_ACTUAL_MESSAGE_AT_LAST_UPPER", "SIGNUP_MESSAGE_ERROR_CHARACTERS_LIKE",
                "SIGNUP_MESSAGE_ERROR_CHARACTERS_LIKE"
        );
    }
    public void createCustomerPasswordSameEmail() throws InterruptedException {
        keyword.reLoadPage();
        Thread.sleep(5000);
        String timestamp = new java.text.SimpleDateFormat("ddHHmmss").format(new Date());
        String email = "Ngoc305" + timestamp + "@gmail.com";
        keyword.untilJqueryIsDone(10L);
        PropertiesFile.serPropValue("SIGNUP_EMAIL_SIGNUP", email);
        createInformationStep1(true, "Nguyen", true,
                false, "", true, "SIGNUP_EMAIL_SIGNUP", true, "SIGNUP_EMAIL_SIGNUP");
        createInformationSFormPassword("SIGNUP_EMAIL_SIGNUP");
        keyword.untilJqueryIsDone(30L);
        keyword.assertEquals("CONTENT_MESSAGE_PASSWORD_SAME_EMAIL", "MESSAGE_PASSWORD_SAME_EMAIL");
    }
}
