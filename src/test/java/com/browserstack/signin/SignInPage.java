package com.browserstack.signin;

import com.browserstack.home.LoginPage;
import com.browserstack.signup.SignUpPage;
import core.BasePage;
import core.KeywordWeb;

public class SignInPage extends BasePage {
    private SignUpPage signUpPage;

    public SignInPage(KeywordWeb key) {
        super(key);
        signUpPage = new SignUpPage(this.keyword);
    }

    public void signIn(String account, String password, String method) throws InterruptedException {
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
    public void checkFieldsSignUp(String flag, String signInMethod, String expect, String actual){
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
                Thread.sleep(5000);
                keyword.verifyElementPresent("SIGN_IN_SUCCESS_MESSAGE");
                break;
            case "successfullyWitPhoneNumber":
                signIn("VALID_PHONE_NUMBER", "PASSWORD","phone");
                Thread.sleep(5000);
                keyword.verifyElementVisible("SIGN_IN_SUCCESS_MESSAGE");
                break;
            case "fullBlankField": // 2 case
                signIn("", "",signInMethod);
                checkFieldsSignUp("fullBlankField",signInMethod,"","");
                break;
            case "blankPassWord":
                signIn("EMAIL_VALID1", "",signInMethod);
                checkFieldsSignUp("blankPassWord",signInMethod,"LOGIN_ERROR_MESSAGE","LOGIN_ERROR_PASSWORD");
                break;
            case "blankEmail":
                signIn("", "PASSWORD",signInMethod);
                checkFieldsSignUp("blankEmail",signInMethod,"LOGIN_ERROR_MESSAGE","LOGIN_ERROR_EMAIL");
                break;
            case "wrongEmailPassword":
                signIn("WRONG_EMAIL", "WRONG_PASSWORD",signInMethod);
                Thread.sleep(3000);
                checkFieldsSignUp("wrongEmailPassword",signInMethod,"INVALID_ACCOUNT_PASSWORD_MESSAGE","INVALID_ACCOUNT_PASSWORD");
                break;
            case "wrongPassWord": // 2 case
                if(signInMethod.equals("email")) {
                    signIn("EMAIL_VALID1", "WRONG_PASSWORD", signInMethod);
                }
                else {
                    signIn("VALID_PHONE_NUMBER", "WRONG_PASSWORD", signInMethod);
                }
                Thread.sleep(3000);
                checkFieldsSignUp("wrongPassWord",signInMethod,"INVALID_ACCOUNT_PASSWORD_MESSAGE","INVALID_ACCOUNT_PASSWORD");
                break;
            case "wrongFormat":
                signIn("EMAIL_INVALID", "PASSWORD",signInMethod);
                checkFieldsSignUp("wrongFormat",signInMethod,"LOGIN_EMAIL_ERROR_FORM","LOGIN_ERROR_EMAIL");
                break;
            case "invalidPhone":
                Thread.sleep(3000);
                signIn("LOGIN_INVALID_PHONE_NUMBER", "PASSWORD",signInMethod);
                Thread.sleep(3000);
                checkFieldsSignUp("invalidPhone",signInMethod,"INVALID_ACCOUNT_PASSWORD_MESSAGE","INVALID_ACCOUNT_PASSWORD");
                break;
            case "wrongPhone":
                signIn("LOGIN_WRONG_PHONE_NUMBER", "PASSWORD",signInMethod);
                Thread.sleep(3000);
                checkFieldsSignUp("wrongPhone",signInMethod,"LOGIN_WRONG_PHONE_MESSAGE","LOGIN_ERROR_PHONE_NUMBER");
                break;
        }
    }
    public void forgotPassWord(String signUpMethod, String flag) throws InterruptedException {
        keyword.click("FORGOT_PASSWORD_BTN");
        switch (flag) {
            case "success": //SNI13_14_16
                if(signUpMethod.equals("email")){
                    keyword.sendKeys("EMAIL_FORGOT_PASSWORD_TXT", "EMAIL_VALID1");
                    keyword.click("FORGOT_PASSWORD_SUBMIT_BTN");
                    keyword.webDriverWaitForElementPresent("FORGOT_PASSWORD_SUBMIT_BTN",10);
                    keyword.sendKeys("FORGOT_PASSWORD_CODE_TXT","WRONG_CODE");
                    keyword.click("FORGOT_PASSWORD_SUBMIT_BTN");
                    Thread.sleep(5000);
                    keyword.assertEquals("FORGOT_PASSWORD_MESSAGE","FORGOT_PASSWORD_INVALID_CODE");
                    String urlFe = keyword.getUrl();
                    String activeCode = signUpPage.takeActiveGmailCode("BE_URL",urlFe," ");
                    keyword.navigateToUrl(urlFe);
                    keyword.webDriverWaitForElementPresent("FORGOT_PASSWORD_SUBMIT_BTN",20);
                    keyword.sendKeys("FORGOT_PASSWORD_CODE_TXT",activeCode);
                    keyword.click("FORGOT_PASSWORD_SUBMIT_BTN");
                    Thread.sleep(5000);
                    keyword.sendKeys("FORGOT_NEW_PASSWORD_TXT","PASSWORD");
                    keyword.click("FORGOT_NEW_PASSWORD_BTN");
                    Thread.sleep(8000);
                    keyword.assertEquals("UPDATE_PASSWORD_SUCCESS_MESSAGE","UPDATE_PASSWORD_SUCCESS");

                }
                else {
                    keyword.click("FORGOT_PASSWORD_PHONE_BTN");
                    keyword.click("FORGOT_PASSWORD_FLAG_DROPDOWN");
                    keyword.scrollToPositionByScript("window.scrollBy(0,200)");
                    keyword.click("FORGOT_PASSWORD_FLAG_VIETNAM");
                    keyword.sendKeys("FORGOT_PASSWORD_MOBILE_TXT","VALID_PHONE_NUMBER");
                    keyword.click("FORGOT_PASSWORD_PHONE_SUBMIT_BTN");
                }
                break;
            case "wrongCode":

                break;
            case "invalidPassWord":
                signUpPage.checkFieldErrorPassWord("1fieldError");

                break;


        }
    }


}
