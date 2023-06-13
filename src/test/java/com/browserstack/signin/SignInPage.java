package com.browserstack.signin;

import com.browserstack.home.LoginPage;
import com.browserstack.signup.SignUpPage;
import core.BasePage;
import core.KeywordWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;



import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static core.BaseTest.driver;
import static core.BaseTest.jse;

public class SignInPage extends BasePage {
    private SignUpPage signUpPage;

    public SignInPage(KeywordWeb key) {
        super(key);
        signUpPage = new SignUpPage(this.keyword);
    }

    public void goToSignIn(String url) throws InterruptedException {
        if(url.equals("https://stage.glamira.com/")){
        keyword.webDriverWaitForElementPresent("BANNER_WEB",20);
        }
        else {
        keyword.webDriverWaitForElementPresent("BANNER_UK_WEB", 20);
        }
        keyword.click("LOGIN_MENU_LEFT");
        keyword.untilJqueryIsDone(50L);
        keyword.click("MOBILE_BTN_LOGIN");
        keyword.untilJqueryIsDone(50L);
    }
    public void signIn(String account, String password, String method) {
        if(method.equals("phone")){
            keyword.click("MOBILE_NUMBER_BTN");
            keyword.click("LOGIN_FLAG_DROPDOWN");
            keyword.scrollToPositionByScript("window.scrollBy(0,200)");
            keyword.click("LOGIN_FLAG_VIETNAM");
            signUpPage.clearTextAndSendKey("LOGIN_PHONE_NUMBER_TXT",account);
            signUpPage.clearTextAndSendKey("LOGIN_PASSWORD_TXT",password);
        }
        else {
            signUpPage.clearTextAndSendKey("LOGIN_EMAIL_TXT",account);
            signUpPage.clearTextAndSendKey("LOGIN_PASSWORD_TXT",password);
        }
        keyword.click("LOGIN_BTN");
    }
    public void logOut() throws InterruptedException{
        keyword.click("MY_ACCOUNT_BTN");
        Thread.sleep(3000);
        keyword.click("SIGNUP_LOGOUT_BTN");
        keyword.click("SIGNUP_CONFIRM_LOGOUT_BTN");
    }
    public void checkFieldsSignIn(String flag, String signInMethod, String expect, String actual){
        if(signInMethod.contains("email") && flag.contains("fullBlankField")){
            keyword.assertEquals("LOGIN_ERROR_MESSAGE","LOGIN_ERROR_EMAIL");
            keyword.assertEquals("LOGIN_ERROR_MESSAGE","LOGIN_ERROR_PASSWORD");
        }
        else if(signInMethod.contains("phone") && flag.contains("fullBlankField")){
            keyword.assertEquals("LOGIN_ERROR_MESSAGE","LOGIN_ERROR_PHONE_NUMBER");
            keyword.assertEquals("LOGIN_ERROR_MESSAGE","LOGIN_ERROR_PASSWORD");
        }
        else {
            keyword.assertEquals(expect,actual);
        }
    }
    public void isSignInSuccessOrFail(String flag, String signInMethod) throws InterruptedException {
        switch (flag) {
            case "successfullyWithEmail":
                signIn("EMAIL_VALID1", "PASSWORD","email");
                keyword.webDriverWaitForElementPresent("SIGN_IN_SUCCESS_MESSAGE",20);
                logOut();
                break;
            case "successfullyWitPhoneNumber":
                signIn("VALID_PHONE_NUMBER", "PASSWORD","phone");
                keyword.webDriverWaitForElementPresent("SIGN_IN_SUCCESS_MESSAGE",20);
                logOut();
                break;
            case "fullBlankField": // 2 case
                signIn("", "",signInMethod);
                checkFieldsSignIn("fullBlankField",signInMethod,"","");
                break;
            case "blankPassWord":
                signIn("EMAIL_VALID1", "",signInMethod);
                checkFieldsSignIn("blankPassWord",signInMethod,"LOGIN_ERROR_MESSAGE","LOGIN_ERROR_PASSWORD");
                break;
            case "blankEmail":
                signIn("", "PASSWORD",signInMethod);
                checkFieldsSignIn("blankEmail",signInMethod,"LOGIN_ERROR_MESSAGE","LOGIN_ERROR_EMAIL");
                break;
            case "wrongEmailPassword":
                signIn("WRONG_EMAIL", "WRONG_PASSWORD",signInMethod);
                Thread.sleep(3000);
                checkFieldsSignIn("wrongEmailPassword",signInMethod,"INVALID_ACCOUNT_PASSWORD_MESSAGE","INVALID_ACCOUNT_PASSWORD");
                break;
            case "wrongPassWord": // 2 case
                if(signInMethod.equals("email")) {
                    signIn("EMAIL_VALID1", "WRONG_PASSWORD", signInMethod);
                }
                else {
                    signIn("VALID_PHONE_NUMBER", "WRONG_PASSWORD", signInMethod);
                }
                Thread.sleep(3000);
                checkFieldsSignIn("wrongPassWord",signInMethod,"INVALID_ACCOUNT_PASSWORD_MESSAGE","INVALID_ACCOUNT_PASSWORD");
                break;
            case "wrongFormat":
                signIn("EMAIL_INVALID", "PASSWORD",signInMethod);
                checkFieldsSignIn("wrongFormat",signInMethod,"LOGIN_EMAIL_ERROR_FORM","LOGIN_ERROR_EMAIL");
                break;
            case "invalidPhone":
                Thread.sleep(3000);
                signIn("LOGIN_INVALID_PHONE_NUMBER", "PASSWORD",signInMethod);
                Thread.sleep(3000);
                checkFieldsSignIn("invalidPhone",signInMethod,"INVALID_ACCOUNT_PASSWORD_MESSAGE","INVALID_ACCOUNT_PASSWORD");
                break;
            case "wrongPhone":
                signIn("LOGIN_WRONG_PHONE_NUMBER", "PASSWORD",signInMethod);
                Thread.sleep(3000);
                checkFieldsSignIn("wrongPhone",signInMethod,"LOGIN_WRONG_PHONE_MESSAGE","LOGIN_ERROR_PHONE_NUMBER");
                break;
        }
    }

    public void forgotPassWord(String signUpMethod) throws InterruptedException {
        keyword.click("FORGOT_PASSWORD_BTN");
        switch (signUpMethod) {
            case "email": //SNI13_14_15_16
                keyword.sendKeys("EMAIL_FORGOT_PASSWORD_TXT", "EMAIL_VALID1");
                keyword.click("EMAIL_FORGOT_PASSWORD_BTN");
                keyword.webDriverWaitForElementPresent("FORGOT_PASSWORD_CODE_MESSAGE",20);
                jse.executeScript("document.getElementsByClassName('input-text l-letter-space')[0].value='123456';");

                WebElement element = driver.findElement(By.xpath("FORGOT_PASSWORD_SUBMIT_BTN"));

                jse.executeScript("arguments[0].click()" ,element);

                //keyword.click("FORGOT_PASSWORD_SUBMIT_BTN");
                Thread.sleep(5000);
                keyword.assertEquals("FORGOT_PASSWORD_MESSAGE","FORGOT_PASSWORD_INVALID_CODE");

//                String urlFe = keyword.getUrl();
//                String activeCode = signUpPage.takeActiveGmailCode("BE_URL",urlFe," ");
//                keyword.navigateToUrl(urlFe);
//                keyword.webDriverWaitForElementPresent("FORGOT_PASSWORD_SUBMIT_BTN",20);
//                keyword.sendKeys("FORGOT_PASSWORD_CODE_TXT",activeCode);
//                keyword.click("FORGOT_PASSWORD_SUBMIT_BTN");
//                Thread.sleep(5000);
//
//                signUpPage.checkInputPassword("FORGOT_NEW_PASSWORD_TXT");    // SNI15
//
//                signUpPage.clearTextAndSendKey("FORGOT_NEW_PASSWORD_TXT","PASSWORD");
//                keyword.click("FORGOT_NEW_PASSWORD_BTN");
//                Thread.sleep(8000);
//                keyword.assertEquals("UPDATE_PASSWORD_SUCCESS_MESSAGE","UPDATE_PASSWORD_SUCCESS");

                    break;
            case "phone": //NSI17_18_19
                keyword.click("FORGOT_PASSWORD_PHONE_BTN");
                keyword.click("FORGOT_PASSWORD_FLAG_DROPDOWN");
                keyword.scrollToPositionByScript("window.scrollBy(0,200)");
                keyword.click("FORGOT_PASSWORD_FLAG_VIETNAM");
                keyword.sendKeys("FORGOT_PASSWORD_MOBILE_TXT","VALID_PHONE_NUMBER"); // NSI17
                keyword.click("FORGOT_PASSWORD_PHONE_SUBMIT_BTN");
                String codeSMS = getSMSCodeCreateNewPass("CODE_RESET_PASSWORD");

                keyword.webDriverWaitForElementPresent("FORGOT_PASSWORD_PHONE_SUBMIT_BTN",20);
                keyword.sendKeys("FORGOT_PASSWORD_CODE_MOBILE_TXT",codeSMS);
                keyword.click("FORGOT_PASSWORD_PHONE_SUBMIT_BTN");
                Thread.sleep(5000);

                signUpPage.checkInputPassword("FORGOT_NEW_PASSWORD_TXT");  //NSI19

                signUpPage.clearTextAndSendKey("FORGOT_NEW_PASSWORD_TXT","PASSWORD");
                keyword.click("FORGOT_NEW_PASSWORD_BTN");
                Thread.sleep(8000);
                keyword.assertEquals("UPDATE_PASSWORD_SUCCESS_MESSAGE","UPDATE_PASSWORD_SUCCESS");
                break;
        }
    }

    public String getSMSCodeCreateNewPass(String xpath) throws InterruptedException{
        signUpPage.loginToBackEnd("BE_URL");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(8000);
        if(keyword.verifyElementVisible("CLOSE_BTN")) {
            keyword.click("CLOSE_BTN");
        }
        keyword.navigateToUrl("SMS_LOG_URL");
        keyword.webDriverWaitForElementPresent("DATA_ROW_BE",20);
        String s = keyword.getText(xpath);
        return s.substring(s.length() - 6, s.length());
    }

//    public void openNewTab() throws InterruptedException{
//        //Store the parent window
//        String parentWindow = driver.getWindowHandle();
////
//        //Open a new Windows(Mailtrap)
////        String a = "window.open('https://stage.glamira.com/secured2021/','_blank');";
////        jse.executeScript(a);
//        //keyword.openNewTab("https://stage.glamira.com/secured2021/");
//
//         keyword.openNewTabFromTabBase(2,"BE_URL");
//
//        Thread.sleep(4000);
////        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+ "t");
//
//        keyword.navigateToUrl("https://demo.guru99.com/popup.php");
//        String MainWindow=driver.getWindowHandle();
//
//        driver.findElement(By.xpath("//a[text()='Click Here']")).click();
//        Thread.sleep(30000);
////
////        // To handle all new opened window.
//
//        Set<String> s1=driver.getWindowHandles();
//        List<String> s2 = new ArrayList<>();
//        s2.addAll(s1);
//        Thread.sleep(20000);
//            // Switching to Child window
//            driver.switchTo().window(s2.get(1));
////            keyword.sendKeys("LOGIN_FORM_USER_NAME_BE","ACCOUNT_BE");
//
////        driver.findElement(By.xpath("//a[text()='Click Here']")).click();
//
//        driver.findElement(By.name("emailid"))
//                .sendKeys("gaurav.3n@gmail.com");
//
//        driver.findElement(By.name("btnLogin")).click();
//
//        Thread.sleep(10000);
//            // Closing the Child Window.
//            driver.close();
//
//        // Switching to Parent window i.e Main Window.
//
//        Thread.sleep(5000);
//        driver.close();
//        driver.switchTo().window(parentWindow);
//    }
}
