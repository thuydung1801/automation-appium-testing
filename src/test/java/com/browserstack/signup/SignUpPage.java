package com.browserstack.signup;

import com.microsoft.playwright.K;
import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import core.PropertiesFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.apache.commons.lang.RandomStringUtils;
import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import java.security.Key;
import java.util.Date;
import java.util.List;

import static core.BaseTest.driver;

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
        keyword.sendKeys("SIGNUP_FIRST_NAME_TXT", firstName);
        keyword.sendKeys("SIGNUP_LAST_NAME_TXT", lastName);
        if(!phoneNumber.equals(" ")){
            keyword.sendKeys("SIGNUP_PHONE_NUMBER_TXT",phoneNumber);
        }
        keyword.sendKeys("SIGNUP_EMAIL_TXT", email);
        keyword.sendKeys("SIGNUP_EMAIL_CONFIRM_TXT", emailConfirm);
        keyword.click("SIGNUP_NEXT_BTN");
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
        if (resend.equals("resend")) {
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
        keyword.sendKeys("SIGNUP_PASSWORD_TXT", "PASSWORD");
        keyword.click("SIGNUP_SUBSCRIBE_CHECKBOX");
        keyword.click("SIGNUP_NEXT_BTN_ON_2/3");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(3000);
        String url = keyword.getUrl();
//        System.out.println("url đó là: " + url);
        Thread.sleep(3000);
        String code = takeActiveGmailCode("BE_URL", email, " ");
        Thread.sleep(1000);
        keyword.navigateToUrl(url);
//        keyword.openNewTabFromTabBase(0,url);
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(3000);
        keyword.sendKeys("SIGNUP_VERIFY_CODE_TXT", code);
        keyword.click("SIGNUP_ACTIVE_BTN");
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementPresent("SIGNUP_CREATE_SUCCESS_MESS");
    }

    public void inputDataSignUp(String firstName, String lastName, String phoneNumber, String email, String emailConfirm) {
        keyword.sendKeys("SIGNUP_FIRST_NAME_TXT", firstName);
        keyword.sendKeys("SIGNUP_LAST_NAME_TXT", lastName);
        if (keyword.verifyElementVisible("SIGNUP_PHONE_NUMBER_TXT")) {
            keyword.sendKeys("SIGNUP_PHONE_NUMBER_TXT", phoneNumber);
            keyword.click("SIGNUP_NEXT_BTN");
            keyword.assertEquals("SIGNUP_MESS_ERROR", "SIGNUP_FiRST_NAME_ERROR_MESS");
            keyword.assertEquals("SIGNUP_MESS_ERROR", "SIGNUP_LAST_NAME_ERROR_MESS");
            keyword.assertEquals("SIGNUP_MESS_ERROR_PHONE","SIGNUP_PHONE_NUMBER_ERROR_MESS");
        }
        else {
            keyword.sendKeys("SIGNUP_EMAIL_TXT", email);
            keyword.sendKeys("SIGNUP_EMAIL_CONFIRM_TXT", emailConfirm);
            keyword.click("SIGNUP_NEXT_BTN");
            keyword.assertEquals("SIGNUP_MESS_ERROR", "SIGNUP_FiRST_NAME_ERROR_MESS");
            keyword.assertEquals("SIGNUP_MESS_ERROR", "SIGNUP_LAST_NAME_ERROR_MESS");
            keyword.assertEquals("SIGNUP_MESS_ERROR", "SIGNUP_EMAIL_ERROR_MESS");
            keyword.assertEquals("SIGNUP_MESS_ERROR", "SIGNUP_EMAIL_CONFIRM_ERROR_MESS");
        }
    }

    public void signUpWithInputErrorData(String flag) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        switch (flag) {
            case "fullBlankField": //NSU2 - NSU15
                    inputDataSignUp(" "," "," "," "," ");
                break;
            case "emailInvalid": //NSU3
                //keyword.clearText("EMAIL_TXT");
                inputDataSignUp(" "," "," ","EMAIL_VALID1"," ");
                Thread.sleep(2000);
                keyword.assertEquals("SIGNUP_MESS_EMAIL_ERROR_FORM", "SIGNUP_EMAIL_ERROR_MESS");
                break;
            case "emailNotEqualEmailConfirm": //NSU4
                keyword.clearText("EMAIL_TXT");
                inputDataSignUp(" "," "," ","EMAIL_VALID1","EMAIL_VALID2");
                Thread.sleep(2000);
                keyword.assertEquals("SIGNUP_MESS_EMAIL_NOT_MATCH", "SIGNUP_EMAIL_CONFIRM_ERROR_MESS");
                break;
            case "emailValidButExist": //NSU5
                keyword.clearText("SIGNUP_EMAIL_TXT");
                keyword.clearText("SIGNUP_EMAIL_CONFIRM_TXT");
                inputDataSignUp(" "," "," ","EMAIL_VALID1","EMAIL_VALID1");
                Thread.sleep(2000);
                keyword.assertEquals("MESSAGE_EXIST_ACCOUNT", "SIGNUP_ACCOUNT_EXIST_ERROR_MESS");
                break;
            case "emailEqualPassword": //NSU6
                String email = createNewEmail();
                keyword.clearText("SIGNUP_EMAIL_TXT");
                keyword.clearText("SIGNUP_EMAIL_CONFIRM_TXT");
                inputDataSignUp("FIRST_NAME","LAST_NAME"," ",email,email);
                keyword.untilJqueryIsDone(50L);
                keyword.sendKeys("SIGNUP_PASSWORD_TXT", email);
                keyword.click("SIGNUP_SUBSCRIBE_CHECKBOX");
                keyword.click("SIGNUP_NEXT_BTN_ON_2/3");
                Thread.sleep(2000);
                keyword.assertEquals("SIGNUP_MESS_PASSWORD_SAME_MAIL", "PASS_SAME_EMAIL_ERROR_MESS");
                break;
            case "invalidPhoneNumber":
                String email1 = createNewEmail();
                inputDataSignUp("FIRST_NAME","LAST_NAME","INVALID_PHONE_NUMBER",email1,email1);
                keyword.untilJqueryIsDone(50L);
                keyword.assertEquals("SIGNUP_MESS_INVALID_PHONE","SIGNUP_PHONE_NUMBER_ERROR_MESS");
                break;
            case "validPhoneNumber":
                String email2 = createNewEmail();
                keyword.clearText("SIGNUP_EMAIL_TXT");
                keyword.clearText("SIGNUP_EMAIL_CONFIRM_TXT");
                keyword.clearText("SIGNUP_PHONE_NUMBER_TXT");
                inputDataSignUp("FIRST_NAME","LAST_NAME","VALID_PHONE_NUMBER",email2,email2);
                keyword.untilJqueryIsDone(50L);
                keyword.assertEquals("MESSAGE_EXIST_ACCOUNT","SIGNUP_ACCOUNT_EXIST_ERROR_MESS");
                break;
        }
    }
    public void checkFieldPassWord(int check, String flag){
        String success = PropertiesFile.getPropValue("SIGNUP_SUCCESS_PASSWORD_FILED");
        String fail = PropertiesFile.getPropValue("SIGNUP_MISS_PASSWORD_FILED");
        int key = 0; int i = 0;
        for(int k = 0; k <= 5; k++){
            if(flag.equals(k + "FieldErrorPassWord")){
                key = k;
            }
        }
        if(key > check) {
            i = key;
        }
        else {
            i = check;
        }
        for(int k = 1; k <= i; k++) {
            driver.findElement(By.xpath(success + "[" + k + "]")).isDisplayed();
            if (k <= check) {
                driver.findElement(By.xpath(fail + "[" + check + "]")).isDisplayed();
            }
        }

    }
    public void checkFieldErrorPassWord(String flag) throws InterruptedException {
        keyword.assertEquals("SIGNUP_MESS_ERROR_PASSWORD", "SIGNUP_PASS_ERROR_MESS");
        keyword.assertEquals("SIGNUP_MESS_PASSWORD_CHARACTER", "SIGNUP_8_CHARACTER_MESS");
        keyword.assertEquals("SIGNUP_MESS_PASSWORD_NUMBER", "SIGNUP_1_NUMBER_MESS");
        keyword.assertEquals("SIGNUP_MESS_PASSWORD_LOWERCASE", "SIGNUP_LOWERCASE_MESS");
        keyword.assertEquals("SIGNUP_MESS_PASSWORD_UPPERCASE", "SIGNUP_UPPERCASE_MESS");
        keyword.assertEquals("SIGNUP_MESS_PASSWORD_SPECIAL_CHARACTER", "SIGNUP_SPECIAL_CHARACTER_MESS");
        int check = 0;
        switch (flag) {
            case "noFieldError":
                checkFieldPassWord( check, "0FieldErrorPassWord");
                break;
            case "1fieldError":
                check ++;
                checkFieldPassWord( check, "1fieldErrorPassWord");
                break;
            case "2fieldError":
                check += 2;
                checkFieldPassWord( check, "2fieldErrorPassWord");
                break;
            case "3fieldError":
                check += 3;
                checkFieldPassWord( check, "3fieldErrorPassWord");
                break;
            case "4fieldError":
                check += 4;
                checkFieldPassWord( check, "4fieldErrorPassWord");
                break;
            case "5FieldErrorPassWord":
                check += 5;
                checkFieldPassWord( check, "5fieldErrorPassWord");
                break;
        }

    }
    public void clearTextAndSendKey(String clearText, String inputSendKey, String dataSendKey) throws InterruptedException {
        keyword.clearText(clearText);
        keyword.sendKeys(inputSendKey, dataSendKey);
    }
    public void inputErrorPassWord(String flag) throws InterruptedException {
        switch (flag) {
            case "1fieldErrorPassWord": //NSU7_8_9_10_11
                keyword.click("NEXT_BTN");
                String email = createNewEmail();
                goToSecondSignUpScreen("FIRST_NAME", "LAST_NAME", " ", email, email);
                keyword.untilJqueryIsDone(50L);
                keyword.sendKeys("SIGNUP_PASSWORD_TXT", "PASS_LESS_8_CHARACTER");
                Thread.sleep(3000);
                checkFieldErrorPassWord("1fieldErrorPassWord");

                clearTextAndSendKey("SIGNUP_PASSWORD_TXT", "SIGNUP_PASSWORD_TXT", "PASS_WITHOUT_NUMBER");
                Thread.sleep(3000);
                checkFieldErrorPassWord("1fieldErrorPassWord");

                clearTextAndSendKey("SIGNUP_PASSWORD_TXT", "SIGNUP_PASSWORD_TXT", "PASS_WITHOUT_LOWERCASE");
                Thread.sleep(3000);
                checkFieldErrorPassWord("1fieldErrorPassWord");

                clearTextAndSendKey("SIGNUP_PASSWORD_TXT", "SIGNUP_PASSWORD_TXT", "PASS_WITHOUT_UPPERCASE");
                Thread.sleep(3000);
                checkFieldErrorPassWord("1fieldErrorPassWord");

                clearTextAndSendKey("SIGNUP_PASSWORD_TXT", "SIGNUP_PASSWORD_TXT", "PASS_WITHOUT_SPECIAL_CHARACTER");
                Thread.sleep(3000);
                checkFieldErrorPassWord("1fieldErrorPassWord");
                break;
//            case "passWordWithoutNumber": //NSU8
//                keyword.clearText("SIGNUP_PASSWORD_TXT");
//                keyword.sendKeys("SIGNUP_PASSWORD_TXT", "PASS_WITHOUT_NUMBER");
//                Thread.sleep(3000);
//                checkFieldErrorPassWord("PASS_WITHOUT_NUMBER");
//                break;
//            case "passWordWithoutLowerCase": //NSU9
//                keyword.clearText("SIGNUP_PASSWORD_TXT");
//                keyword.sendKeys("SIGNUP_PASSWORD_TXT", "PASS_WITHOUT_LOWERCASE");
//                Thread.sleep(3000);
//                checkFieldErrorPassWord("PASS_WITHOUT_LOWERCASE");
//                break;
//            case "passWordWithoutUpperCase": //NSU10
//                keyword.clearText("SIGNUP_PASSWORD_TXT");
//                keyword.sendKeys("SIGNUP_PASSWORD_TXT", "PASS_WITHOUT_UPPERCASE");
//                Thread.sleep(3000);
//                checkFieldErrorPassWord("PASS_WITHOUT_UPPERCASE");
//                break;
//            case "passWordWithoutSpecialCharacter": //NSU11
//                keyword.clearText("SIGNUP_PASSWORD_TXT");
//                keyword.sendKeys("SIGNUP_PASSWORD_TXT", "PASS_WITHOUT_SPECIAL_CHARACTER");
//                Thread.sleep(3000);
//                checkFieldErrorPassWord("PASS_WITHOUT_SPECIAL_CHARACTER");
//                break;
//        }
        }
    }

    public void enterErrorVerifyCodeOrResend(String flag) throws InterruptedException {
        String email = createNewEmail();
        goToSecondSignUpScreen("FIRST_NAME", "LAST_NAME"," ", email, email);
        keyword.sendKeys("SIGNUP_PASSWORD_TXT", "PASSWORD");
        keyword.click("SIGNUP_SUBSCRIBE_CHECKBOX");
        keyword.click("SIGNUP_NEXT_BTN_ON_2/3");
        if (flag.equals("Error verify code")) {
            keyword.untilJqueryIsDone(50L);
            keyword.sendKeys("SIGNUP_VERIFY_CODE_TXT", "WRONG_CODE");
            keyword.click("SIGNUP_ACTIVE_BTN");
            keyword.assertEquals("SIGNUP_MESS_INVALID_CODE", "INVALID_CODE");

        } else {
            keyword.untilJqueryIsDone(50L);
            keyword.click("SIGNUP_RESEND_BTN");
            Thread.sleep(5000);
            String url = keyword.getWindowHandle();
            String Code = takeActiveGmailCode("BE_URL", email, "resend");
            keyword.untilJqueryIsDone(50L);
            keyword.navigateToUrl(url);
            keyword.sendKeys("SIGNUP_VERIFY_CODE_TXT", Code);
            keyword.click("SIGNUP_ACTIVE_BTN");
            keyword.untilJqueryIsDone(50L);
            keyword.verifyElementPresent("MESSAGE_ENTER_ACTIVE_CODE");
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
        keyword.sendKeys("SIGNUP_PASSWORD_TXT", "PASSWORD");
        keyword.click("SIGNUP_SUBSCRIBE_CHECKBOX");
        keyword.click("SIGNUP_NEXT_BTN_ON_2/3");
        String code = getSMSCode("SMS_CODE","SMS_LOG_URL");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(3000);
        keyword.sendKeys("SIGNUP_VERIFY_CODE_TXT", code);
        keyword.click("SIGNUP_ACTIVE_BTN");
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementPresent("CREATE_SUCCESSFUL_MESS");
    }
}

