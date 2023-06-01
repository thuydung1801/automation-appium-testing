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

    public SignUpPage(KeywordWeb keywordWeb) {
        super(keywordWeb);
    }

    public String createNewEmail(){
        String timestamp = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return "vucuong" + timestamp + "@gmail.com";
    }
//    public void openTabBE(String urlBe) throws InterruptedException {
//        keyword.executeJavaScript("window.open()");
//        keyword.switchToTab(1);
//        keyword.maximizeWindow();
//        keyword.navigateToUrl(urlBe);
//        keyword.untilJqueryIsDone(50L);
//        keyword.verifyElementPresent("USER_DASHBOARD_BE");
//    }
    public void goToSignUp() throws InterruptedException{
        keyword.untilJqueryIsDone(50L);
        keyword.click("LOGIN_MENU_LEFT");
        keyword.untilJqueryIsDone(50L);
        keyword.click("MOBILE_BTN_LOGIN");
        keyword.untilJqueryIsDone(50L);
        keyword.click("CREATE_ACCOUNT_BTN");
    }
    public String takeActiveCode (String urlBe) throws InterruptedException
    {
        keyword.executeJavaScript("window.open()");
        keyword.switchToTab(1);
        keyword.maximizeWindow();
        keyword.navigateToUrl(urlBe);
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("LOGIN_FORM_USER_NAME_BE","ACCOUNT_BE");
        keyword.sendKeys("LOGIN_FORM_PASSWORD_BE","PASS_BE");
        keyword.click("LOGIN_FORM_BTN_SUBMIT_BE");
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementPresent("USER_DASHBOARD_BE");
        keyword.untilJqueryIsDone(50L);
        keyword.click("CUSTOMER_BTN_BE");
        keyword.untilJqueryIsDone(50L);
        keyword.click("EMAIL_LOG_BTN_BE");
        keyword.selectByText("SELECT_DROPDOWN","View");
        return keyword.getText("VERIFY_CODE");
    }
    public void signUpSuccessful(String firstName, String lastName, String email, String emailConfirm) throws InterruptedException{
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("FIRST_NAME_TXT", firstName);
        keyword.sendKeys("LAST_NAME_TXT", lastName);
        keyword.sendKeys("EMAIL_TXT", email);
        keyword.sendKeys("EMAIL_CONFIRM_TXT", emailConfirm);
        keyword.click("NEXT_BTN");
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("PASSWORD_BTN","PASSWORD");
        keyword.click("SUBSCRIBE_CHECKBOX");
        keyword.click("NEXT_BTN_ON_2/3");
        String Code = takeActiveCode("BE_URL");
        keyword.switchToTab(0);
        keyword.sendKeys("VERIFY_CODE_TXT", Code);
        keyword.click("ACTIVE_BTN");
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementPresent("CREATE_SUCCESS_MESS");

    }
    public void signUpWithBankField(String firstName, String lastName, String email, String emailConfirm)throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("FIRST_NAME_TXT", firstName);
        keyword.sendKeys("LAST_NAME_TXT", lastName);
        keyword.sendKeys("EMAIL_TXT", email);
        keyword.sendKeys("EMAIL_CONFIRM_TXT", emailConfirm);
        keyword.click("NEXT_BTN");
        if (firstName == " " && lastName == " " && email == " " && emailConfirm == " ") {
            keyword.assertEquals(keyword.getText("FiRST_NAME_ERROR_MESS"), "MESSAGE_ERROR");
            keyword.assertEquals(keyword.getText("LAST_NAME_ERROR_MESS"), "MESSAGE_ERROR");
            keyword.assertEquals(keyword.getText("EMAIL_ERROR_MESS"), "MESSAGE_ERROR");
            keyword.assertEquals(keyword.getText("EMAIL_CONFIRM_ERROR_MESS"), "MESSAGE_ERROR");
        }
        else if(email != emailConfirm){
            keyword.assertEquals(keyword.getText("MESSAGE_EMAIL_ERROR_FORM"), "MESSAGE_EMAIL_NOT_MATCH");
        }
        else {
            keyword.assertEquals(keyword.getText("MESSAGE_EMAIL_ERROR_FORM"), "MESSAGE_EMAIL_ERROR_FORM");
        }
    }
}
