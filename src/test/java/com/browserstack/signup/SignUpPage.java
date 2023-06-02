package com.browserstack.signup;

import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import java.util.Date;
import java.util.List;
public class SignUpPage extends BasePage {
    private static final Logger logger = LogHelper.getLogger();

    public SignUpPage() {
        super();
    }

    public String createNewEmail(){
        String timestamp = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return "Vucuong" + timestamp + "@gmail.com";
    }
    public void goToSignUp() throws InterruptedException{
        keyword.untilJqueryIsDone(50L);
        keyword.click("LOGIN_MENU_LEFT");
        keyword.untilJqueryIsDone(50L);
        keyword.click("MOBILE_BTN_LOGIN");
        keyword.untilJqueryIsDone(50L);
        keyword.click("CREATE_ACCOUNT_BTN");
    }
    public void goToSecondSignUpScreen(String firstName, String lastName, String email, String emailConfirm) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("FIRST_NAME_TXT", firstName);
        keyword.sendKeys("LAST_NAME_TXT", lastName);
        keyword.sendKeys("EMAIL_TXT", email);
        keyword.sendKeys("EMAIL_CONFIRM_TXT", emailConfirm);
        keyword.click("NEXT_BTN");
    }
    public void loginToBackEnd(String urlBe)throws InterruptedException{
        keyword.executeJavaScript("window.open()");
        keyword.navigateToUrl(urlBe);
        keyword.switchToTab(0);
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("LOGIN_FORM_USER_NAME_BE","ACCOUNT_BE");
        keyword.sendKeys("LOGIN_FORM_PASSWORD_BE","PASS_BE");
        Thread.sleep(2000);
        keyword.click("LOGIN_FORM_BTN_SUBMIT_BE");

    }
    public String takeActiveCode (String urlBe, String email, String resend) throws InterruptedException
    {
        loginToBackEnd(urlBe);
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
//        keyword.click("CLOSE_BTN");
//        keyword.webDriverWaitForElementPresent("CUSTOMER_BTN_BE",10);
//        keyword.click("CUSTOMER_BTN_BE");
//        keyword.webDriverWaitForElementPresent("VERIFY_CUSTOMER", 20);
//
//        keyword.webDriverWaitForElementPresent("EMAIL_LOG_BTN_BE", 20);
//        Thread.sleep(5000);
//        keyword.click("EMAIL_LOG_BTN_BE");
        keyword.navigateToUrl("https://stage.glamira.com/secured2021/admin/smtp/log/");
        keyword.untilJqueryIsDone(50L);

        keyword.webDriverWaitForElementPresent("FILTER-BTN", 20);
        keyword.click("FILTER-BTN");
        keyword.webDriverWaitForElementPresent("RECIPIENT_TXT", 20);
        keyword.clearText("RECIPIENT_TXT");
        keyword.sendKeys("RECIPIENT_TXT", email);
        keyword.click("APPLY_FILTER_BTN");
        Thread.sleep(3000);
        if(resend == "resend"){
            keyword.webDriverWaitForElementPresent("SELECT_DROPDOWN2", 20);
            keyword.click("SELECT_DROPDOWN2");
            keyword.webDriverWaitForElementPresent("SELECT_ACTIVE", 20);
            keyword.click("VIEW_BTN2");
        }
        else {
            keyword.webDriverWaitForElementPresent("SELECT_DROPDOWN1", 20);
            keyword.click("SELECT_DROPDOWN1");
            keyword.webDriverWaitForElementPresent("SELECT_ACTIVE", 20);
            keyword.click("VIEW_BTN");
        }
        Thread.sleep(3000);
        keyword.switchToIFrameByXpath("IFRAME_STAGE");
        return keyword.getText("VERIFY_CODE");
    }
    public void signUpSuccessful(String firstName, String lastName, String email, String emailConfirm) throws InterruptedException{
        keyword.untilJqueryIsDone(50L);
        goToSecondSignUpScreen(firstName,lastName,email,emailConfirm);
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("PASSWORD_TXT","PASSWORD");
        keyword.click("SUBSCRIBE_CHECKBOX");
        keyword.click("NEXT_BTN_ON_2/3");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        String url = keyword.getWindowHandle();
        String Code = takeActiveCode("BE_URL",email," ");
        keyword.untilJqueryIsDone(50L);
        keyword.navigateToUrl(url);
        keyword.sendKeys("VERIFY_CODE_TXT", Code);
        keyword.click("ACTIVE_BTN");
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementPresent("CREATE_SUCCESS_MESS");

    }
    public void checkFieldErrPassWord(String missField) throws InterruptedException{
        keyword.assertEquals("MESSAGE_ERROR_PASSWORD","PASS_ERROR_MESS");
        keyword.assertEquals("MESS_PASSWORD_8_CHARACTER", "8_CHARACTER_MESS");
        keyword.assertEquals("MESS_PASSWORD_1_NUMBER", "1_NUMBER_MESS");
        keyword.assertEquals("MESS_PASSWORD_1_LOWERCASE", "LOWERCASE_MESS");
        keyword.assertEquals("MESS_PASSWORD_1_UPPERCASE", "UPPERCASE_MESS");
        keyword.assertEquals("MESS_PASSWORD_1_SPECIAL_CHARACTER", "SPECIAL_CHARACTER_MESS_1");

        keyword.verifyElementVisible(missField);
    }

    public void signUpWithInputErrorData(String flag, String firstName, String lastName, String email, String emailConfirm, String passWord)throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("FIRST_NAME_TXT", firstName);
        keyword.sendKeys("LAST_NAME_TXT", lastName);
        switch (flag) {
            case "fullBlankField": //NSU2
                keyword.sendKeys("EMAIL_TXT", email);
                keyword.sendKeys("EMAIL_CONFIRM_TXT", emailConfirm);
                keyword.click("NEXT_BTN");
                Thread.sleep(2000);
                keyword.assertEquals("MESSAGE_ERROR" , "FiRST_NAME_ERROR_MESS");
                keyword.assertEquals("MESSAGE_ERROR","LAST_NAME_ERROR_MESS");
                keyword.assertEquals("MESSAGE_ERROR", "EMAIL_ERROR_MESS");
                keyword.assertEquals("MESSAGE_ERROR", "EMAIL_CONFIRM_ERROR_MESS");
                break;
            case "emailInvalid": //NSU3
                keyword.clearText("EMAIL_TXT");
                keyword.sendKeys("EMAIL_TXT",email);
                keyword.click("NEXT_BTN");
                Thread.sleep(2000);
                keyword.assertEquals("MESSAGE_EMAIL_ERROR_FORM","EMAIL_ERROR_MESS");
                break;
            case "emailNotEqualEmailConfirm": //NSU4
                keyword.clearText("EMAIL_TXT");
                keyword.sendKeys("EMAIL_TXT",email);
                keyword.sendKeys("EMAIL_CONFIRM_TXT",emailConfirm);
                keyword.click("NEXT_BTN");
                Thread.sleep(2000);
                keyword.assertEquals("MESSAGE_EMAIL_NOT_MATCH","EMAIL_CONFIRM_ERROR_MESS");
                break;
            case "emailValidButExist": //NSU5
                keyword.clearText("EMAIL_TXT");
                keyword.sendKeys("EMAIL_TXT",email);
                keyword.clearText("EMAIL_CONFIRM_TXT");
                keyword.sendKeys("EMAIL_CONFIRM_TXT",emailConfirm);
                keyword.click("NEXT_BTN");
                Thread.sleep(2000);
                keyword.assertEquals("MESSAGE_EXIST_EMAIL","EMAIL_EXIST_ERROR_MESS" );
                break;
            case "emailEqualPassword": //NSU6
                keyword.clearText("EMAIL_TXT");
                keyword.sendKeys("EMAIL_TXT",email);
                keyword.clearText("EMAIL_CONFIRM_TXT");
                keyword.sendKeys("EMAIL_CONFIRM_TXT",emailConfirm);
                keyword.click("NEXT_BTN");
                keyword.untilJqueryIsDone(50L);
                keyword.sendKeys("PASSWORD_TXT",email);
                keyword.click("SUBSCRIBE_CHECKBOX");
                keyword.click("NEXT_BTN_ON_2/3");
                Thread.sleep(2000);
                keyword.assertEquals("MESSAGE_PASSWORD_SAME_MAIL","PASS_SAME_EMAIL_ERROR_MESS" );
                break;
        }
    }
    public void inputErrorPassWord( String flag) throws InterruptedException
    {
        switch (flag) {
            case "passWordLess8Character": //NSU7
//                keyword.click("NEXT_BTN");
                keyword.untilJqueryIsDone(50L);
                keyword.sendKeys("PASSWORD_TXT","PASS_LESS_8_CHARACTER");
                Thread.sleep(3000);
                checkFieldErrPassWord("MISS_FILED_8_CHARACTER");
                break;
            case "passWordWithoutNumber": //NSU8
                keyword.clearText("PASSWORD_TXT");
                keyword.sendKeys("PASSWORD_TXT","PASS_WITHOUT_NUMBER");
                Thread.sleep(3000);
                checkFieldErrPassWord("MISS_FILED_1_NUMBER");
                break;
            case "passWordWithoutLowerCase": //NSU9
                keyword.clearText("PASSWORD_TXT");
                keyword.sendKeys("PASSWORD_TXT","PASS_WITHOUT_LOWERCASE");
                Thread.sleep(3000);
                checkFieldErrPassWord("MISS_FILED_LOWERCASE");
                break;
            case "passWordWithoutUpperCase": //NSU10
                keyword.clearText("PASSWORD_TXT");
                keyword.sendKeys("PASSWORD_TXT","PASS_WITHOUT_UPPERCASE");
                Thread.sleep(3000);
                checkFieldErrPassWord("MISS_FILED_UPPERCASE");
                break;
            case "passWordWithoutSpecialCharacter" : //NSU11
                keyword.clearText("PASSWORD_TXT");
                keyword.sendKeys("PASSWORD_TXT","PASS_WITHOUT_SPECIAL_CHARACTER");
                Thread.sleep(3000);
                checkFieldErrPassWord("MISS_FILED_SPECIAL_CHARACTER");
                break;
        }
    }
    public void enterErrorVerifyCodeAndResend(String flag) throws InterruptedException {
        String email = createNewEmail();
        goToSecondSignUpScreen("FIRST_NAME", "LAST_NAME", email, email);
        keyword.sendKeys("PASSWORD_TXT", "PASSWORD");
        keyword.click("SUBSCRIBE_CHECKBOX");
        keyword.click("NEXT_BTN_ON_2/3");
        if (flag == "Error verify code") {
            keyword.untilJqueryIsDone(50L);
            keyword.sendKeys("VERIFY_CODE_TXT", "WRONG_CODE");
            keyword.click("ACTIVE_BTN");
            keyword.assertEquals("MESSAGE_INVALID_CODE", "INVALID_CODE");

        }
        else{
            keyword.untilJqueryIsDone(50L);
            keyword.click("RESEND_BTN");
            Thread.sleep(5000);
            String url = keyword.getWindowHandle();
            String Code = takeActiveCode("BE_URL",email,"resend");
            keyword.untilJqueryIsDone(50L);
            keyword.navigateToUrl(url);
            keyword.sendKeys("VERIFY_CODE_TXT", Code);
            keyword.click("ACTIVE_BTN");
            keyword.untilJqueryIsDone(50L);
            keyword.verifyElementPresent("CREATE_SUCCESS_MESS");
        }
    }
}

