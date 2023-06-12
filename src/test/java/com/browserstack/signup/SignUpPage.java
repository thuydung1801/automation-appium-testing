package com.browserstack.signup;

import com.browserstack.home.LoginPage;
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
import static core.BaseTest.jse;

public class SignUpPage extends BasePage {
    private LoginPage loginPage;

    public SignUpPage(KeywordWeb key) {
        super(key);
        loginPage = new LoginPage(this.keyword);
    }

    public String createNewEmail() {
        String timestamp = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return "Vucuong" + timestamp + "@gmail.com";
    }

    public String createPhoneNumber() {
        String randomNumbers = RandomStringUtils.randomNumeric(5);
        return "3637" + randomNumbers;
    }
    public void clearTextAndSendKey(String clearTextBox, String dataSendKey){
        keyword.clearText(clearTextBox);
        keyword.sendKeys(clearTextBox, dataSendKey);
    }
    public void goToSignUp() throws InterruptedException {
        Thread.sleep(10000);
        keyword.click("LOGIN_MENU_LEFT");
        keyword.untilJqueryIsDone(50L);
        keyword.click("MOBILE_BTN_LOGIN");
        keyword.untilJqueryIsDone(50L);
        keyword.click("CREATE_ACCOUNT_BTN");
    }
    public void loginToBackEnd(String urlBe) throws InterruptedException {
        keyword.navigateToUrl(urlBe);
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        if(keyword.verifyElementVisible("LOGIN_FORM_PASSWORD_BE")) {
            keyword.sendKeys("LOGIN_FORM_USER_NAME_BE", "ACCOUNT_BE");
            keyword.sendKeys("LOGIN_FORM_PASSWORD_BE", "PASS_BE");
            keyword.click("LOGIN_FORM_BTN_SUBMIT_BE");
        }
    }
    public void inputDataFirstSignUpScreen(String firstName, String lastName, String phoneNumber, String email, String emailConfirm) throws InterruptedException{
        Thread.sleep(3000);
        keyword.sendKeys("SIGNUP_FIRST_NAME_TXT", firstName);
        keyword.sendKeys("SIGNUP_LAST_NAME_TXT", lastName);
        if (keyword.verifyElementVisible("SIGNUP_PHONE_NUMBER_TXT")) {
            keyword.click("SIGNUP_FLAG_DROPDOWN");
            Thread.sleep(3000);
            keyword.scrollToPositionByScript("window.scrollBy(0,200)");
            Thread.sleep(3000);
            keyword.click("FLAG_VIETNAM");
            clearTextAndSendKey("SIGNUP_PHONE_NUMBER_TXT", phoneNumber);
        }
        clearTextAndSendKey("SIGNUP_EMAIL_TXT", email);
        clearTextAndSendKey("SIGNUP_EMAIL_CONFIRM_TXT", emailConfirm);
        keyword.click("SIGNUP_NEXT_BTN");
    }
    public void inputDataSecondSignUpScreen(String passWord) throws InterruptedException{
        Thread.sleep(1000);
        keyword.clearText("SIGNUP_PASSWORD_TXT");
        keyword.sendKeys("SIGNUP_PASSWORD_TXT", passWord);
        keyword.click("SIGNUP_SUBSCRIBE_CHECKBOX");
        keyword.click("SIGNUP_NEXT_BTN_ON_2/3");
    }
    public void chooseSignUpMethod(String method, String firstName, String lastName, String email, String emailConfirm ,String phoneNumber) throws InterruptedException{
        if(method.equals("email")) {
            inputDataFirstSignUpScreen(firstName, lastName, " ", email, emailConfirm);
        }
        else {
            inputDataFirstSignUpScreen(firstName, lastName, phoneNumber, email ,emailConfirm );
        }
    }
    public String takeActiveGmailCode(String urlBe,String urlFe, String resend) throws InterruptedException {
        loginToBackEnd(urlBe);
        Thread.sleep(5000);
        if (resend.equals("resend")) {
            keyword.navigateToUrl(urlFe);
            keyword.webDriverWaitForElementPresent("SIGNUP_ACTIVE_BTN", 20);
            Thread.sleep(40000);
            keyword.click("SIGNUP_RESEND_BTN");
            Thread.sleep(5000);
            keyword.navigateToUrl("EMAIL_LOG_URL");
        }
        else{
            keyword.webDriverWaitForElementPresent("CLOSE_BTN",20);
            keyword.click("CLOSE_BTN");
            keyword.navigateToUrl("EMAIL_LOG_URL");
            keyword.untilJqueryIsDone(50L);
        }
        keyword.webDriverWaitForElementPresent("SELECT_DROPDOWN", 20);
        keyword.click("SELECT_DROPDOWN");
        Thread.sleep(5000);
        keyword.click("VIEW_BTN");
        Thread.sleep(3000);
        keyword.switchToIFrameByXpath("IFRAME_STAGE");
        return keyword.getText("VERIFY_CODE");
    }

    public void checkFieldInFirstSignUpScreen(String flag, String expect, String actual) throws InterruptedException{
        if(flag.contains("email") || flag.contains("Phone")){
            Thread.sleep(3000);
            keyword.assertEquals(expect, actual);
        }
        else {
            keyword.assertEquals("SIGNUP_ERROR_MESSAGE", "SIGNUP_FIRST_NAME_ERROR");
            keyword.assertEquals("SIGNUP_ERROR_MESSAGE", "SIGNUP_LAST_NAME_ERROR");
            if (keyword.verifyElementVisible("SIGNUP_PHONE_NUMBER_TXT")) {
                keyword.assertEquals("SIGNUP_ERROR_PHONE", "SIGNUP_PHONE_NUMBER_ERROR");
            }
            else {
                keyword.assertEquals("SIGNUP_ERROR_MESSAGE", "SIGNUP_EMAIL_ERROR");
                keyword.assertEquals("SIGNUP_ERROR_MESSAGE", "SIGNUP_EMAIL_CONFIRM_ERROR");
            }
        }
    }
    public void inputCodeActiveAndLogOut(String codeActive, String signUpMethod, String account) throws InterruptedException{
        if(keyword.verifyElementVisible("LOGIN_INFORMATION_SCREEN")){
            loginPage.login(account,"PASSWORD");
        }
        keyword.sendKeys("SIGNUP_VERIFY_CODE_TXT", codeActive);
        keyword.click("SIGNUP_ACTIVE_BTN");
        keyword.webDriverWaitForElementPresent("HELLO_MESSAGE",10);
        if(signUpMethod.contains("email")) {
            keyword.verifyElementPresent("CREATE_SUCCESSFUL_MESSAGE_UK");
        }
        else {
            keyword.verifyElementPresent("CREATE_SUCCESSFUL_MESSAGE");
        }
        keyword.click("MY_ACCOUNT_BTN");
        Thread.sleep(3000);
        keyword.click("SIGNUP_LOGOUT_BTN");
        keyword.click("SIGNUP_CONFIRM_LOGOUT_BTN");
    }
    public void isSignUpSuccessOrFail(String flag, String signUpMethod) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        String email = createNewEmail();
        String phoneNumber = createPhoneNumber();
        switch (flag) {
            case "successfullyWithEmail":
                inputDataFirstSignUpScreen("FIRST_NAME", "LAST_NAME"," ", email, email);
                keyword.untilJqueryIsDone(50L);
                inputDataSecondSignUpScreen("PASSWORD");
                keyword.untilJqueryIsDone(50L);
                Thread.sleep(6000);
                String url = keyword.getUrl();
                inputErrorCode(); // NSU 12
                String codeActive = takeActiveGmailCode("BE_URL",""," ");
                keyword.navigateToUrl(url);
                keyword.untilJqueryIsDone(50L);
                Thread.sleep(3000);
                inputCodeActiveAndLogOut(codeActive, "email",email);
                break;
            case "successfullyWithPhoneNumber":
                inputDataFirstSignUpScreen("FIRST_NAME", "LAST_NAME",phoneNumber, email, email);
                inputDataSecondSignUpScreen("PASSWORD");
                keyword.untilJqueryIsDone(50L);
                Thread.sleep(6000);
                String urlSMS = keyword.getUrl();
                inputErrorCode(); // NSU 23
                String codeSMS = getSMSCode("SMS_CODE");
                keyword.navigateToUrl(urlSMS);
                Thread.sleep(3000);
                inputCodeActiveAndLogOut(codeSMS,"phoneNumber",phoneNumber);
                break;
            case "fullBlankField": //NSU2 - NSU15
                chooseSignUpMethod(signUpMethod, " "," "," ",  " "," ");
                Thread.sleep(2000);
                checkFieldInFirstSignUpScreen("blank"," "," ");
                break;
            case "emailInvalid": //NSU3
                inputDataFirstSignUpScreen(" "," "," ","EMAIL_INVALID"," ");
                Thread.sleep(2000);
                checkFieldInFirstSignUpScreen("emailInvalid","SIGNUP_EMAIL_ERROR_FORM","SIGNUP_EMAIL_ERROR");
                break;
            case "emailNotEqualEmailConfirm": //NSU4
                inputDataFirstSignUpScreen(" "," "," ","EMAIL_VALID1","EMAIL_VALID2");
                Thread.sleep(2000);
                checkFieldInFirstSignUpScreen("emailNotEqualEmailConfirm","SIGNUP_EMAIL_NOT_MATCH","SIGNUP_EMAIL_CONFIRM_ERROR");
                break;
            case "emailValidButExist": //NSU5 - NSU24
                chooseSignUpMethod(signUpMethod,"FIRST_NAME","LAST_NAME","EMAIL_VALID1","EMAIL_VALID1",phoneNumber);
                Thread.sleep(2000);
                checkFieldInFirstSignUpScreen("emailValidButExist","MESSAGE_EXIST_ACCOUNT","SIGNUP_ACCOUNT_EXIST_ERROR");
                break;
            case "emailEqualPassword": //NSU6
                inputDataFirstSignUpScreen("FIRST_NAME","LAST_NAME"," ",email,email);
                keyword.untilJqueryIsDone(50L);
                inputDataSecondSignUpScreen(email);
                Thread.sleep(4000);
                checkFieldInFirstSignUpScreen("emailEqualPassword","SIGNUP_PASSWORD_SAME_MAIL","PASS_SAME_EMAIL_ERROR");
                break;
            case "invalidPhoneNumber":
                inputDataFirstSignUpScreen("FIRST_NAME","LAST_NAME","SIGNUP_INVALID_PHONE_NUMBER",email,email);
                keyword.untilJqueryIsDone(50L);
                checkFieldInFirstSignUpScreen("invalidPhoneNumber","SIGNUP_INVALID_PHONE","SIGNUP_PHONE_NUMBER_ERROR");
                break;
            case "validPhoneNumber":
                inputDataFirstSignUpScreen("FIRST_NAME","LAST_NAME","VALID_PHONE_NUMBER",email,email);
                keyword.untilJqueryIsDone(50L);
                checkFieldInFirstSignUpScreen("validPhoneNumber","MESSAGE_EXIST_ACCOUNT","SIGNUP_ACCOUNT_EXIST_ERROR");
                break;
        }
    }
    public void checkFieldPassWord(int numberSuccessFields, String flag){
        String success = PropertiesFile.getPropValue("SIGNUP_SUCCESS_PASSWORD_FILED");
        String fail = PropertiesFile.getPropValue("SIGNUP_MISSING_PASSWORD_FILED");
        int numberErrorFields = Integer.parseInt(flag.substring(0,1));
        int test = (numberErrorFields > numberSuccessFields) ? numberErrorFields : numberSuccessFields;
        for(int i = 1; i <= test; i++) {
            if (i <= numberErrorFields) {
                driver.findElement(By.xpath(fail + "[" + i + "]")).isDisplayed();
            }
            if (i <= numberSuccessFields) {
                driver.findElement(By.xpath(success + "[" + i + "]")).isDisplayed();
            }
        }

    }
    public void checkFieldErrorPassWord(String flag) {
        keyword.assertEquals("SIGNUP_PASSWORD_ERROR_MESSAGE", "SIGNUP_PASS_ERROR");
        keyword.assertEquals("SIGNUP_PASSWORD_CHARACTER_MESSAGE", "SIGNUP_8_CHARACTER");
        keyword.assertEquals("SIGNUP_PASSWORD_NUMBER_MESSAGE", "SIGNUP_1_NUMBER");
        keyword.assertEquals("SIGNUP_PASSWORD_LOWERCASE_MESSAGE", "SIGNUP_LOWERCASE");
        keyword.assertEquals("SIGNUP_PASSWORD_UPPERCASE_MESSAGE", "SIGNUP_UPPERCASE");
        keyword.assertEquals("SIGNUP_PASSWORD_SPECIAL_CHARACTER_MESSAGE", "SIGNUP_SPECIAL_CHARACTER");
        int check = 5;
        switch (flag) {
            case "noFieldErrorPassWord":
                checkFieldPassWord( check, "0FieldError");
                break;
            case "1fieldErrorPassWord":
                check --;
                checkFieldPassWord( check, "1fieldError");
                break;
            case "2fieldErrorPassWord":
                check -= 2;
                checkFieldPassWord( check, "2fieldError");
                break;
            case "3fieldErrorPassWord":
                check -= 3;
                checkFieldPassWord( check, "3fieldError");
                break;
            case "4fieldErrorPassWord":
                check -= 4;
                checkFieldPassWord( check, "4fieldError");
                break;
            case "5fieldErrorPassWord":
                check -= 5;
                checkFieldPassWord( check, "5fieldError");
                break;
        }
    }
    public void inputErrorPassWord(String flag, String signUpMethod) throws InterruptedException {
        switch (flag) {
            case "1fieldErrorPassWord": //NSU7_8_9_10_11_18_19_20_21_22
                String email = createNewEmail();
                String phoneNumber = createPhoneNumber();
                Thread.sleep(3000);
                chooseSignUpMethod(signUpMethod,"FIRST_NAME","LAST_NAME",email,email, phoneNumber);
                keyword.untilJqueryIsDone(50L);
                keyword.sendKeys("SIGNUP_PASSWORD_TXT", "PASS_LESS_8_CHARACTER");
                Thread.sleep(2000);
                checkFieldErrorPassWord("1fieldErrorPassWord");

                clearTextAndSendKey( "SIGNUP_PASSWORD_TXT", "PASS_WITHOUT_NUMBER");
                Thread.sleep(2000);
                checkFieldErrorPassWord("1fieldErrorPassWord");

                clearTextAndSendKey( "SIGNUP_PASSWORD_TXT", "PASS_WITHOUT_LOWERCASE");
                Thread.sleep(2000);
                checkFieldErrorPassWord("1fieldErrorPassWord");

                clearTextAndSendKey("SIGNUP_PASSWORD_TXT", "PASS_WITHOUT_UPPERCASE");
                Thread.sleep(2000);
                checkFieldErrorPassWord("1fieldErrorPassWord");

                clearTextAndSendKey( "SIGNUP_PASSWORD_TXT", "PASS_WITHOUT_SPECIAL_CHARACTER");
                Thread.sleep(2000);
                checkFieldErrorPassWord("1fieldErrorPassWord");
                if(signUpMethod.equals("email")){
                    resendActiveCode("email",email);
                }
                break;
        }
    }
    public void inputErrorCode() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("SIGNUP_VERIFY_CODE_TXT", "WRONG_CODE");
        keyword.click("SIGNUP_ACTIVE_BTN");
        Thread.sleep(3000);
        keyword.assertEquals("SIGNUP_INVALID_CODE_MESSAGE", "INVALID_CODE");
    }

    public void resendActiveCode( String signUpMethod, String email) throws InterruptedException {
//        String emailAccount = createNewEmail();
//        String phoneNumber = createPhoneNumber();
//        chooseSignUpMethod(signUpMethod,"FIRST_NAME","LAST_NAME",emailAccount,emailAccount,phoneNumber);
        inputDataSecondSignUpScreen("PASSWORD");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(6000);
        String urlFe = keyword.getUrl();
        Thread.sleep(60000);//60s
        String Code = takeActiveGmailCode("BE_URL",urlFe,"resend");
        keyword.untilJqueryIsDone(50L);
        keyword.navigateToUrl("BASE_URL_UK");
        loginPage.login(email,"PASSWORD");
        keyword.webDriverWaitForElementPresent("SIGNUP_VERIFY_CODE_TXT",10);
        keyword.sendKeys("SIGNUP_VERIFY_CODE_TXT", Code);
        keyword.click("SIGNUP_ACTIVE_BTN");
        Thread.sleep(5000);
        keyword.verifyElementVisible("CREATE_SUCCESSFUL_MESSAGE_UK");
    }

    public String getSMSCode(String xpath) throws InterruptedException {
        loginToBackEnd("BE_URL");
        keyword.untilJqueryIsDone(50L);
        keyword.webDriverWaitForElementPresent("CLOSE_BTN",20);
        keyword.click("CLOSE_BTN");
        keyword.navigateToUrl("SMS_LOG_URL");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        String s = keyword.getText(xpath);
        return s.substring(s.indexOf(':') + 2, s.length());
    }
}
