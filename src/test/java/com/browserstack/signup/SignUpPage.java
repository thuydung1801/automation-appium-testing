package com.browserstack.signup;

import com.microsoft.playwright.K;
import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.apache.commons.lang.RandomStringUtils;
import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import java.security.Key;
import java.util.Date;
import java.util.List;
public class SignUpPage extends BasePage {
    private static final Logger logger = LogHelper.getLogger();

    public SignUpPage() {
        super();
    }

    public String createNewEmail() {
        String timestamp = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return "Vucuong" + timestamp + "@gmail.com";
    }

    public String createPhoneNumber() {
        String randomNumbers = RandomStringUtils.randomNumeric(5);
        return "3637" + randomNumbers;
    }

    public void goToSignUp() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.click("LOGIN_MENU_LEFT");
        keyword.untilJqueryIsDone(50L);
        keyword.click("MOBILE_BTN_LOGIN");
        keyword.untilJqueryIsDone(50L);
        keyword.click("CREATE_ACCOUNT_BTN");
    }

    public void goToSecondSignUpScreen(String firstName, String lastName, String phoneNumber, String email, String emailConfirm) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("FIRST_NAME_TXT", firstName);
        keyword.sendKeys("LAST_NAME_TXT", lastName);
        if(phoneNumber != " "){
            keyword.sendKeys("PHONE_NUMBER_TXT",phoneNumber);
        }
        keyword.sendKeys("EMAIL_TXT", email);
        keyword.sendKeys("EMAIL_CONFIRM_TXT", emailConfirm);
        keyword.click("NEXT_BTN");
    }

    public void loginToBackEnd(String urlBe) throws InterruptedException {
        keyword.executeJavaScript("window.open()");
        keyword.navigateToUrl(urlBe);
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("LOGIN_FORM_USER_NAME_BE", "ACCOUNT_BE");
        keyword.sendKeys("LOGIN_FORM_PASSWORD_BE", "PASS_BE");
        Thread.sleep(2000);
        keyword.click("LOGIN_FORM_BTN_SUBMIT_BE");
    }

    public String takeActiveGmailCode(String urlBe, String email, String resend) throws InterruptedException {
        loginToBackEnd(urlBe);
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(7000);
        keyword.click("CLOSE_BTN");
        keyword.navigateToUrl("EMAIL_LOG_URL");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        keyword.webDriverWaitForElementPresent("FILTER-BTN", 20);
        keyword.click("FILTER-BTN");
        keyword.webDriverWaitForElementPresent("RECIPIENT_TXT", 20);
        Thread.sleep(5000);
        keyword.clearText("RECIPIENT_TXT");
        keyword.sendKeys("RECIPIENT_TXT", email);
        Thread.sleep(3000);
        keyword.webDriverWaitForElementPresent("APPLY_FILTER_BTN", 20);
        keyword.click("APPLY_FILTER_BTN");
        Thread.sleep(5000);
        keyword.untilJqueryIsDone(50L);
        if (resend == "resend") {
            keyword.webDriverWaitForElementPresent("SELECT_DROPDOWN2", 20);
            keyword.click("SELECT_DROPDOWN2");
            //keyword.webDriverWaitForElementPresent("SELECT_ACTIVE", 20);
            keyword.click("VIEW_BTN2");
        } else {
            keyword.webDriverWaitForElementPresent("SELECT_DROPDOWN1", 20);
            keyword.click("SELECT_DROPDOWN1");
            Thread.sleep(3000);
            //keyword.webDriverWaitForElementPresent("SELECT_ACTIVE", 20);
            keyword.click("VIEW_BTN1");
        }
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(3000);
        keyword.switchToIFrameByXpath("IFRAME_STAGE");
        return keyword.getText("VERIFY_CODE");
    }

    public void signUpSuccessfulWithEmail() throws InterruptedException {
        String email = createNewEmail();
        keyword.untilJqueryIsDone(50L);
        goToSecondSignUpScreen("FIRST_NAME", "LAST_NAME"," ", email, email);
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("PASSWORD_TXT", "PASSWORD");
        keyword.click("SUBSCRIBE_CHECKBOX");
        keyword.click("NEXT_BTN_ON_2/3");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(3000);
        String url = keyword.getUrl();
        System.out.println("url đó là: " + url);
        Thread.sleep(3000);
        String code = takeActiveGmailCode("BE_URL", email, " ");
        Thread.sleep(1000);
        keyword.navigateToUrl(url);
//        keyword.openNewTabFromTabBase(0,url);
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(3000);
        keyword.sendKeys("VERIFY_CODE_TXT", code);
        keyword.click("ACTIVE_BTN");
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementPresent("CREATE_SUCCESS_MESS");
    }

    public void checkFieldErrPassWord(String missField) throws InterruptedException {
        keyword.assertEquals("MESSAGE_ERROR_PASSWORD", "PASS_ERROR_MESS");
        keyword.assertEquals("MESS_PASSWORD_8_CHARACTER", "8_CHARACTER_MESS");
        keyword.assertEquals("MESS_PASSWORD_1_NUMBER", "1_NUMBER_MESS");
        keyword.assertEquals("MESS_PASSWORD_1_LOWERCASE", "LOWERCASE_MESS");
        keyword.assertEquals("MESS_PASSWORD_1_UPPERCASE", "UPPERCASE_MESS");
        keyword.assertEquals("MESS_PASSWORD_1_SPECIAL_CHARACTER", "SPECIAL_CHARACTER_MESS_1");

        keyword.verifyElementVisible(missField);
    }
    public void inputDataSignUp(String firstName, String lastName, String phoneNumber, String email, String emailConfirm) {
        keyword.sendKeys("FIRST_NAME_TXT", firstName);
        keyword.sendKeys("LAST_NAME_TXT", lastName);
        if (keyword.verifyElementVisible("PHONE_NUMBER_TXT")) {
            keyword.sendKeys("PHONE_NUMBER_TXT", phoneNumber);
        }
        keyword.sendKeys("EMAIL_TXT", email);
        keyword.sendKeys("EMAIL_CONFIRM_TXT", emailConfirm);
        keyword.click("NEXT_BTN");
    }

    public void signUpWithInputErrorData(String flag) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        switch (flag) {
            case "fullBlankField": //NSU2 - NSU15
                if(keyword.verifyElementVisible("PHONE_NUMBER_TXT")){
                    inputDataSignUp(" "," "," "," "," ");
                    keyword.assertEquals("MESSAGE_ERROR", "FiRST_NAME_ERROR_MESS");
                    keyword.assertEquals("MESSAGE_ERROR", "LAST_NAME_ERROR_MESS");
                    keyword.assertEquals("MESSAGE_ERROR2","PHONE_NUMBER_ERROR_MESS");
                }
                else{
                    inputDataSignUp(" ",","," "," "," ");
                    keyword.assertEquals("MESSAGE_ERROR", "FiRST_NAME_ERROR_MESS");
                    keyword.assertEquals("MESSAGE_ERROR", "LAST_NAME_ERROR_MESS");
                    keyword.assertEquals("MESSAGE_ERROR", "EMAIL_ERROR_MESS");
                    keyword.assertEquals("MESSAGE_ERROR", "EMAIL_CONFIRM_ERROR_MESS");
                }
                break;
            case "emailInvalid": //NSU3
                //keyword.clearText("EMAIL_TXT");
                inputDataSignUp(" "," "," ","EMAIL_VALID1"," ");
                Thread.sleep(2000);
                keyword.assertEquals("MESSAGE_EMAIL_ERROR_FORM", "EMAIL_ERROR_MESS");
                break;
            case "emailNotEqualEmailConfirm": //NSU4
                keyword.clearText("EMAIL_TXT");
                inputDataSignUp(" "," "," ","EMAIL_VALID1","EMAIL_VALID2");
                Thread.sleep(2000);
                keyword.assertEquals("MESSAGE_EMAIL_NOT_MATCH", "EMAIL_CONFIRM_ERROR_MESS");
                break;
            case "emailValidButExist": //NSU5
                keyword.clearText("EMAIL_TXT");
                keyword.clearText("EMAIL_CONFIRM_TXT");
                inputDataSignUp(" "," "," ","EMAIL_VALID1","EMAIL_VALID1");
                Thread.sleep(2000);
                keyword.assertEquals("MESSAGE_EXIST_ACCOUNT", "ACCOUNT_EXIST_ERROR_MESS");
                break;
            case "emailEqualPassword": //NSU6
                String email = createNewEmail();
                keyword.clearText("EMAIL_TXT");
                keyword.clearText("EMAIL_CONFIRM_TXT");
                inputDataSignUp("FIRST_NAME","LAST_NAME"," ",email,email);
                keyword.untilJqueryIsDone(50L);
                keyword.sendKeys("PASSWORD_TXT", email);
                keyword.click("SUBSCRIBE_CHECKBOX");
                keyword.click("NEXT_BTN_ON_2/3");
                Thread.sleep(2000);
                keyword.assertEquals("MESSAGE_PASSWORD_SAME_MAIL", "PASS_SAME_EMAIL_ERROR_MESS");
                break;
            case "invalidPhoneNumber":
                String email1 = createNewEmail();
                inputDataSignUp("FIRST_NAME","LAST_NAME","INVALID_PHONE_NUMBER",email1,email1);
                keyword.untilJqueryIsDone(50L);
                keyword.assertEquals("MESS_INVALID_PHONE","PHONE_NUMBER_ERROR_MESS");
                break;
            case "validPhoneNumber":
                String email2 = createNewEmail();
                keyword.clearText("EMAIL_TXT");
                keyword.clearText("EMAIL_CONFIRM_TXT");
                keyword.clearText("PHONE_NUMBER_TXT");
                inputDataSignUp("FIRST_NAME","LAST_NAME","VALID_PHONE_NUMBER",email2,email2);
                keyword.untilJqueryIsDone(50L);
                keyword.assertEquals("MESSAGE_EXIST_ACCOUNT","ACCOUNT_EXIST_ERROR_MESS");
                break;
        }
    }

    public void inputErrorPassWord(String flag) throws InterruptedException {
        switch (flag) {
            case "passWordLess8Character": //NSU7
//                keyword.click("NEXT_BTN");
                String email = createNewEmail();
                goToSecondSignUpScreen("FIRST_NAME", "LAST_NAME", " ",email, email);
                keyword.untilJqueryIsDone(50L);
                keyword.sendKeys("PASSWORD_TXT", "PASS_LESS_8_CHARACTER");
                Thread.sleep(3000);
                checkFieldErrPassWord("MISS_FILED_8_CHARACTER");
                break;
            case "passWordWithoutNumber": //NSU8
                keyword.clearText("PASSWORD_TXT");
                keyword.sendKeys("PASSWORD_TXT", "PASS_WITHOUT_NUMBER");
                Thread.sleep(3000);
                checkFieldErrPassWord("MISS_FILED_1_NUMBER");
                break;
            case "passWordWithoutLowerCase": //NSU9
                keyword.clearText("PASSWORD_TXT");
                keyword.sendKeys("PASSWORD_TXT", "PASS_WITHOUT_LOWERCASE");
                Thread.sleep(3000);
                checkFieldErrPassWord("MISS_FILED_LOWERCASE");
                break;
            case "passWordWithoutUpperCase": //NSU10
                keyword.clearText("PASSWORD_TXT");
                keyword.sendKeys("PASSWORD_TXT", "PASS_WITHOUT_UPPERCASE");
                Thread.sleep(3000);
                checkFieldErrPassWord("MISS_FILED_UPPERCASE");
                break;
            case "passWordWithoutSpecialCharacter": //NSU11
                keyword.clearText("PASSWORD_TXT");
                keyword.sendKeys("PASSWORD_TXT", "PASS_WITHOUT_SPECIAL_CHARACTER");
                Thread.sleep(3000);
                checkFieldErrPassWord("MISS_FILED_SPECIAL_CHARACTER");
                break;
        }
    }

    public void enterErrorVerifyCodeOrResend(String flag) throws InterruptedException {
        String email = createNewEmail();
        goToSecondSignUpScreen("FIRST_NAME", "LAST_NAME"," ", email, email);
        keyword.sendKeys("PASSWORD_TXT", "PASSWORD");
        keyword.click("SUBSCRIBE_CHECKBOX");
        keyword.click("NEXT_BTN_ON_2/3");
        if (flag == "Error verify code") {
            keyword.untilJqueryIsDone(50L);
            keyword.sendKeys("VERIFY_CODE_TXT", "WRONG_CODE");
            keyword.click("ACTIVE_BTN");
            keyword.assertEquals("MESSAGE_INVALID_CODE", "INVALID_CODE");

        } else {
            keyword.untilJqueryIsDone(50L);
            keyword.click("RESEND_BTN");
            Thread.sleep(5000);
            String url = keyword.getWindowHandle();
            String Code = takeActiveGmailCode("BE_URL", email, "resend");
            keyword.untilJqueryIsDone(50L);
            keyword.navigateToUrl(url);
            keyword.sendKeys("VERIFY_CODE_TXT", Code);
            keyword.click("ACTIVE_BTN");
            keyword.untilJqueryIsDone(50L);
            keyword.verifyElementPresent("CREATE_SUCCESSFUL_MESS");
        }
    }

    public String getSMSCode(String xpath, String urlBe) throws InterruptedException {
        loginToBackEnd(urlBe);
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(7000);
        keyword.click("CLOSE_BTN");
        keyword.navigateToUrl("EMAIL_LOG_URL");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        String s = keyword.getText(xpath);
        return s.substring(s.indexOf(':') + 2, s.length());
    }

    public void signUpSuccessfulWithPhoneNumber() throws InterruptedException {
        String phoneNumber = createPhoneNumber();
        String email = createNewEmail();
        goToSecondSignUpScreen("FIRST_NAME", "LAST_NAME",phoneNumber, email, email);
        keyword.sendKeys("PASSWORD_TXT", "PASSWORD");
        keyword.click("SUBSCRIBE_CHECKBOX");
        keyword.click("NEXT_BTN_ON_2/3");
        String code = getSMSCode("SMS_CODE","SMS_LOG_URL");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(3000);
        keyword.sendKeys("VERIFY_CODE_TXT", code);
        keyword.click("ACTIVE_BTN");
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementPresent("CREATE_SUCCESSFUL_MESS");
    }
}

