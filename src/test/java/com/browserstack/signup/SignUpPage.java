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
        keyword.navigateToUrl(urlBe);
       // keyword.switchToWindow(urlBe);
//        keyword.switchToTab(1);
//        keyword.maximizeWindow();
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("LOGIN_FORM_USER_NAME_BE","ACCOUNT_BE");
        keyword.sendKeys("LOGIN_FORM_PASSWORD_BE","PASS_BE");
        Thread.sleep(2000);
        keyword.click("LOGIN_FORM_BTN_SUBMIT_BE");
        keyword.untilJqueryIsDone(50L);
//        Thread.sleep(5000);
//        keyword.click("CLOSE_BTN");
       // keyword.verifyElementPresent("USER_DASHBOARD_BE");
        Thread.sleep(5000);
        keyword.click("CUSTOMER_BTN_BE");
        Thread.sleep(3000);
        keyword.click("EMAIL_LOG_BTN_BE");
        Thread.sleep(5000);
//        keyword.click("SELECT_DROPDOWN");
//        keyword.click("VIEW_BTN");
        keyword.selectByText("SELECT_DROPDOWN","View");
        Thread.sleep(3000);
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
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        String url = keyword.getWindowHandle();
        String Code = takeActiveCode("BE_URL");
        keyword.untilJqueryIsDone(50L);
        keyword.navigateToUrl(url);
        keyword.sendKeys("VERIFY_CODE_TXT", Code);
        keyword.click("ACTIVE_BTN");
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementPresent("CREATE_SUCCESS_MESS");

    }
//    public void signUpWithInputErrorData1(String firstName, String lastName, String email, String emailConfirm, String passWord)throws InterruptedException {
//        keyword.untilJqueryIsDone(50L);
//        keyword.sendKeys("FIRST_NAME_TXT", firstName);
//        keyword.sendKeys("LAST_NAME_TXT", lastName);
//        keyword.sendKeys("EMAIL_TXT", email);
//        keyword.sendKeys("EMAIL_CONFIRM_TXT", emailConfirm);
//        keyword.click("NEXT_BTN");
//
//        if (firstName == " " && lastName == " " && email == " " && emailConfirm == " ") {
//            keyword.assertEquals(keyword.getText("FiRST_NAME_ERROR_MESS"), "MESSAGE_ERROR");
//            keyword.assertEquals(keyword.getText("LAST_NAME_ERROR_MESS"), "MESSAGE_ERROR");
//            keyword.assertEquals(keyword.getText("EMAIL_ERROR_MESS"), "MESSAGE_ERROR");
//            keyword.assertEquals(keyword.getText("EMAIL_CONFIRM_ERROR_MESS"), "MESSAGE_ERROR");
//        }
//        else if(email != emailConfirm){
//            //Thread.sleep(2000);
//            keyword.assertEquals(keyword.getText("EMAIL_CONFIRM_ERROR_MESS"), "MESSAGE_EMAIL_NOT_MATCH");
//        }
//        else if(email.equals("EMAIL_VALID") && emailConfirm.equals("EMAIL_VALID")){
//            keyword.assertEquals(keyword.getText("EMAIL_EXIST_ERROR_MESS"), "MESSAGE_EXIST_EMAIL");
//        }
//        else if(email.equals(passWord)){
//            Thread.sleep(2000);
//           keyword.assertEquals(keyword.getText(), );
//        }
//        else {
//            keyword.assertEquals(keyword.getText("MESSAGE_EMAIL_ERROR_FORM"), "MESSAGE_EMAIL_ERROR_FORM");
//        }
//    }
    public void signUpWithInputErrorData(String url,String flag, String firstName, String lastName, String email, String emailConfirm, String passWord)throws InterruptedException {
        keyword.navigateToUrl(url);
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("FIRST_NAME_TXT", firstName);
        keyword.sendKeys("LAST_NAME_TXT", lastName);
        keyword.sendKeys("EMAIL_TXT", email);
        keyword.sendKeys("EMAIL_CONFIRM_TXT", emailConfirm);
        keyword.click("NEXT_BTN");
        Thread.sleep(3000);
        switch (flag) {
            case "fullBlankField":
                keyword.assertEquals("MESSAGE_ERROR" , "FiRST_NAME_ERROR_MESS");
//                keyword.scrollDownToElement("FiRST_NAME_ERROR_MESS");
//                Thread.sleep(3000);

                keyword.assertEquals("MESSAGE_ERROR","LAST_NAME_ERROR_MESS");
                keyword.assertEquals("MESSAGE_ERROR", "EMAIL_ERROR_MESS");
                keyword.assertEquals("MESSAGE_ERROR", "EMAIL_CONFIRM_ERROR_MESS");
                break;
            case "emailNotEqualEmailConfirm":
                keyword.assertEquals("MESSAGE_EMAIL_NOT_MATCH","EMAIL_CONFIRM_ERROR_MESS");
                break;
            case "emailValidButExist":
                keyword.assertEquals("MESSAGE_EXIST_EMAIL","EMAIL_EXIST_ERROR_MESS" );
                break;
            case "emailInvalid":
                keyword.assertEquals("MESSAGE_EMAIL_ERROR_FORM","EMAIL_ERROR_MESS");
                break;
            case "emailEqualPassword":
                Thread.sleep(3000);
                keyword.sendKeys("PASSWORD_BTN",email);
                //            keyword.assertEquals(keyword.getText(), );
                break;
        }
    }
}
