package com.browserstack.signup;

import com.browserstack.home.LoginPage;
import com.browserstack.signin.SignInPage;
import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import core.PropertiesFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.apache.commons.lang.RandomStringUtils;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static core.BaseTest.driver;
import static core.BaseTest.jse;

public class SignUpPage extends BasePage {
    private LoginPage loginPage;
    private SignInPage signInPage;

    public SignUpPage(KeywordWeb key) {
        super(key);
        signInPage = new SignInPage(this.keyword);
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
    public void goToSignUp(String url) throws InterruptedException {
        signInPage.goToSignIn(url);
        keyword.click("CREATE_ACCOUNT_BTN");
    }
    public void loginToBackEnd(String urlBe) throws InterruptedException {
        keyword.navigateToUrl(urlBe);
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(10000);
        if(keyword.verifyElementVisible("LOGIN_FORM_PASSWORD_BE")) {
            keyword.sendKeys("LOGIN_FORM_USER_NAME_BE", "ACCOUNT_BE");
            keyword.sendKeys("LOGIN_FORM_PASSWORD_BE", "PASS_BE");
            keyword.click("LOGIN_FORM_BTN_SUBMIT_BE");
        }
    }
    public void inputDataFirstSignUpScreen(String firstName, String lastName, String phoneNumber, String email, String emailConfirm) throws InterruptedException{
        keyword.webDriverWaitForElementPresent("SIGNUP_FIRST_NAME_TXT",20);
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
        Thread.sleep(2000);
        keyword.clearText("SIGNUP_PASSWORD_TXT");
        keyword.sendKeys("SIGNUP_PASSWORD_TXT", passWord);
        keyword.click("SIGNUP_SUBSCRIBE_CHECKBOX");
        keyword.click("SIGNUP_NEXT_BTN_ON_2/3");
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
            keyword.webDriverWaitForElementPresent("BE_CLOSE_BTN",20);
            keyword.click("BE_CLOSE_BTN");
            keyword.navigateToUrl("EMAIL_LOG_URL");
            keyword.untilJqueryIsDone(50L);
        }
        keyword.webDriverWaitForElementPresent("BE_SELECT_DROPDOWN", 20);
        keyword.click("BE_SELECT_DROPDOWN");
        Thread.sleep(5000);
        keyword.click("BE_VIEW_BTN");
        Thread.sleep(3000);
        keyword.switchToIFrameByXpath("BE_IFRAME_STAGE");
        return keyword.getText("BE_VERIFY_CODE");
    }

    public void checkFieldInFirstSignUpScreen(String flag, String expect, String actual) throws InterruptedException{
        Thread.sleep(2000);
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
        keyword.webDriverWaitForElementPresent("HELLO_MESSAGE",20);
        if(signUpMethod.contains("email")) {
            keyword.verifyElementPresent("CREATE_SUCCESSFUL_MESSAGE_UK");
        }
        else {
            keyword.verifyElementPresent("CREATE_SUCCESSFUL_MESSAGE");
        }
        signInPage.logOut();
    }
    public void isSignUpSuccessOrFail(String flag) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        String email = createNewEmail();
        String phoneNumber = createPhoneNumber();
        switch (flag) {
            case "successfullyWithEmail":
                inputErrorCode(email,""); // NSU 12
                String url = keyword.getUrl();
                String codeActive = takeActiveGmailCode("BE_URL",""," ");
                keyword.navigateToUrl(url);
                keyword.untilJqueryIsDone(50L);
                Thread.sleep(3000);
                inputCodeActiveAndLogOut(codeActive, "email",email);
                break;
            case "successfullyWithPhoneNumber":
                inputErrorCode(email,phoneNumber); // NSU 23
                String urlSMS = keyword.getUrl();
                String codeSMS = getSMSCode("BE_SMS_CODE");
                keyword.navigateToUrl(urlSMS);
                Thread.sleep(3000);
                inputCodeActiveAndLogOut(codeSMS,"phoneNumber",phoneNumber);
                break;
            case "fullBlankField": //NSU2 - NSU15
                inputDataFirstSignUpScreen("", "", " ", "", "");
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
                inputDataFirstSignUpScreen("FIRST_NAME","LAST_NAME",phoneNumber,"EMAIL_VALID1","EMAIL_VALID1");
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
    public void inputPassword(String xpath) throws InterruptedException {
        List<String> errorPassWordList = new ArrayList<>();
        errorPassWordList.add("PASS_LESS_8_CHARACTER");
        errorPassWordList.add("PASS_WITHOUT_NUMBER");
        errorPassWordList.add("PASS_WITHOUT_LOWERCASE");
        errorPassWordList.add("PASS_WITHOUT_UPPERCASE");
        errorPassWordList.add("PASS_WITHOUT_SPECIAL_CHARACTER");
        for (int i = 0; i < errorPassWordList.size(); i++) {
            clearTextAndSendKey(xpath, errorPassWordList.get(i));
            Thread.sleep(2000);
            checkFieldErrorPassWord("1fieldErrorPassWord");
        }
    }
    public void checkInputErrorPassWord( String signUpMethod) throws InterruptedException {
        //NSU7_8_9_10_11_18_19_20_21_22
        String email = createNewEmail();
        String phoneNumber = createPhoneNumber();
        Thread.sleep(3000);
        inputDataFirstSignUpScreen("FIRST_NAME","LAST_NAME",phoneNumber,email, email);
        keyword.untilJqueryIsDone(50L);
        inputPassword("SIGNUP_PASSWORD_TXT");
        if(signUpMethod.equals("email")){
            resendActiveCode("email",email);
        }
    }
    public void inputErrorCode(String email, String phoneNumber) throws InterruptedException {
        keyword.webDriverWaitForElementPresent("SIGNUP_FIRST_NAME_TXT",20);
        inputDataFirstSignUpScreen("FIRST_NAME", "LAST_NAME",phoneNumber, email, email);
        keyword.untilJqueryIsDone(50L);
        inputDataSecondSignUpScreen("PASSWORD");
        keyword.untilJqueryIsDone(50L);
        keyword.webDriverWaitForElementPresent("SIGNUP_VERIFY_CODE_TXT",20);
        keyword.sendKeys("SIGNUP_VERIFY_CODE_TXT", "WRONG_CODE");
        keyword.click("SIGNUP_ACTIVE_BTN");
        keyword.webDriverWaitForElementPresent("INVALID_CODE",20);
        keyword.assertEquals("SIGNUP_INVALID_CODE_MESSAGE", "INVALID_CODE");
    }

    public void resendActiveCode( String signUpMethod, String email) throws InterruptedException {
//        String emailAccount = createNewEmail();
//        String phoneNumber = createPhoneNumber();
//        chooseSignUpMethod(signUpMethod,"FIRST_NAME","LAST_NAME",emailAccount,emailAccount,phoneNumber);
        inputDataSecondSignUpScreen("PASSWORD");
        keyword.untilJqueryIsDone(50L);
        keyword.webDriverWaitForElementPresent("SIGNUP_VERIFY_CODE_TXT",20);
        String urlFe = keyword.getUrl();
        Thread.sleep(60000);//60s
        String Code = takeActiveGmailCode("BE_URL",urlFe,"resend");
        keyword.untilJqueryIsDone(50L);
        keyword.navigateToUrl("BASE_URL_UK");
        loginPage.login(email,"PASSWORD");
        keyword.webDriverWaitForElementPresent("SIGNUP_VERIFY_CODE_TXT",20);
        keyword.sendKeys("SIGNUP_VERIFY_CODE_TXT", Code);
        keyword.click("SIGNUP_ACTIVE_BTN");
        Thread.sleep(10000);
        keyword.verifyElementVisible("CREATE_SUCCESSFUL_MESSAGE_UK");
    }

    public String getSMSCode(String xpath) throws InterruptedException {
        loginToBackEnd("BE_URL");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(8000);
        if(keyword.verifyElementVisible("BE_CLOSE_BTN")) {
            keyword.click("BE_CLOSE_BTN");
        }
        keyword.navigateToUrl("SMS_LOG_URL");
        keyword.webDriverWaitForElementPresent("DATA_ROW_BE",20);
        String s = keyword.getText(xpath);
        return s.substring(s.indexOf(':') + 2, s.length());
    }
}

