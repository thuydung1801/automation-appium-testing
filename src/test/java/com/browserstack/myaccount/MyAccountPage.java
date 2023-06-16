package com.browserstack.myaccount;

import com.browserstack.home.LoginPage;
import com.browserstack.signin.SignInPage;
import com.browserstack.signup.SignUpPage;
import core.BasePage;
import core.KeywordWeb;
import core.PropertiesFile;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

import static core.BaseTest.driver;

public class MyAccountPage extends BasePage {

    private SignUpPage signUpPage;
    private SignInPage signInPage;
    private LoginPage loginPage;
//    public MyAccountPage() {
//        super();
//        signUpPage = new SignUpPage();
//        signInPage = new SignInPage();
//    }
    public MyAccountPage(KeywordWeb key) {
        super(key);
        signUpPage = new SignUpPage(key);
        signInPage = new SignInPage(key);
    }

    public void inputPersonalInform(String firstName, String lastName,String newPass, String newMail, String change)  {
        keyword.click("MAC_PERSONAL_INFORM_BTN");
        keyword.webDriverWaitForElementPresent("MAC_PERSONAL_INFORM_SCREEN", 20);
        if (change.equals("Email")) {
            keyword.sendKeys("MAC_FIRST_NAME_TXT","FIRST_NAME");
            keyword.sendKeys("MAC_LAST_NAME_TXT","LAST_NAME");
            keyword.click("MAC_CHANGE_EMAIl_BTN");
            keyword.sendKeys("MAC_CURRENT_PASS_TXT", "CURRENT_PASSWORD");
            keyword.sendKeys("MAC_EMAIL_TXT", newMail);
            PropertiesFile.serPropValue("EMAIL_VALID2", newMail);
            keyword.click("MAC_SAVE_CHANGE_MAIL_BTN");
        }
        else if (change.equals("Pass")) {
            keyword.click("MAC_CHANGE_PASS_BTN");
            keyword.sendKeys("MAC_CURRENT_PASS_TXT", "CURRENT_PASSWORD");
            keyword.sendKeys("MAC_NEW_PASS_TXT", newPass);
            keyword.sendKeys("MAC_CONFIRM_PASS_TXT", newPass);
            PropertiesFile.serPropValue("CURRENT_PASSWORD", newPass);
            keyword.click("MAC_SAVE_CHANGE_PASS_BTN");
        }
        else {
            signUpPage.clearTextAndSendKey("MAC_FIRST_NAME_TXT", firstName);
            signUpPage.clearTextAndSendKey("MAC_LAST_NAME_TXT", lastName);
            keyword.click("MAC_SAVE_CHANGE_INFORM_BTN");
        }
    }

    public void checkChangePersonInform(String flag, String firstName, String lastName,String newPass, String newMail) throws InterruptedException{
        if(flag.equals("blank")) {
            keyword.assertEquals("MAC_SAVE_INFORM_ERROR_MESSAGE","MAC_ERROR_MESS1");
            keyword.assertEquals("MAC_SAVE_INFORM_ERROR_MESSAGE","MAC_ERROR_MESS2");
        }
        else {
            keyword.webDriverWaitForElementPresent("HELLO_MESSAGE", 20);
            keyword.assertEquals("MAC_CHANGE_SUCCESS_MESS", "MAC_VERIFY_CHANGE_SUCCESS");
            if (flag.equals("successfully")) {
                String hello = keyword.getText("HELLO_MESSAGE");
                boolean check = hello.contains(firstName + " " + lastName) ? true : false;
                Assert.assertEquals(check, true);
            }
            else if (flag.equals("successfullyWithEmail")) {
                String contactInform = keyword.getText("MAC_VERIFY_CHANGE_MAIL");
                boolean check = contactInform.contains(newMail) ? true : false;
                Assert.assertEquals(check, true);
            }
            else {
                signInPage.logOut();
                signInPage.goToSignIn("");
                signInPage.signIn(newMail,newPass,"email");
            }
        }

    }
    public void isChangePersonalInform(String flag) throws InterruptedException {
        String newEmail = signUpPage.createNewEmail();
        String randomNumbers = RandomStringUtils.randomNumeric(5);
        String newPass = "Duccuong@" + randomNumbers;
        switch (flag) {
            case "successfully":
                inputPersonalInform("FIRST_NAME", "LAST_NAME", " ","","");
                checkChangePersonInform("successfully", "FIRST_NAME","LAST_NAME","","");
                break;
            case "blank":
                inputPersonalInform(" ", " ", " ","","");
                checkChangePersonInform("blank", "","","","");
                break;
            case "successfullyWithEmail":
                inputPersonalInform(" ", " ", "",newEmail,"Email");
                checkChangePersonInform("blank", "","","",newEmail);
                break;
            case "successfullyWithPass":
                inputPersonalInform(" ", " ", newPass,"","Pass");
                checkChangePersonInform("blank", "","",newPass,newEmail);
                break;
        }
    }
    public void goToDelete(String passWord){
        keyword.click("MY_ACCOUNT_BTN");
        keyword.click("MAC_PERSONAL_INFORM_BTN");
        keyword.webDriverWaitForElementPresent("MAC_PERSONAL_INFORM_SCREEN", 20);
        keyword.click("MAC_DELETE_ACCOUNT_BTN");
        keyword.sendKeys("MAC_DELETE_ACCOUNT_TXT", passWord);
        keyword.click("MAC_DELETE_ACCOUNT_BTN2");
        keyword.click("MAC_DELETE_ACCOUNT_OK_BTN");
        if(passWord.equals("PASSWORD")) {
            keyword.webDriverWaitForElementPresent("MY_ACCOUNT_BTN", 20);
            keyword.assertEquals("MAC_DELETE_ACC_FAIL_MESS", "MAC_DELETE_ACCOUNT_SUCCESS");
        }
        else{
            keyword.webDriverWaitForElementPresent("MAC_UNDO_ACTION_BTN", 20);
            keyword.assertEquals("MAC_DELETE_ACC_SUCCESS_MESS", "MAC_DELETE_ACCOUNT_FAIL");
        }
    }
    public void isDeleteAccount(String flag)  throws InterruptedException{
        if(flag.equals("deleteAccountSuccess")) {
            goToDelete("CURRENT_PASSWORD");
        }
        else {
            signInPage.logOut();
            signInPage.goToSignIn("");
            signInPage.signIn("EMAIL_VALID1","PASSWORD","email");
            goToDelete("PASSWORD");
        }
    }
    public void isDisablePhoneNumberTxt(){
        keyword.click("MAC_PERSONAL_INFORM_BTN");
        keyword.webDriverWaitForElementPresent("MAC_PERSONAL_INFORM_SCREEN",20);
        boolean check = driver.findElement(By.xpath("MAC_PHONE_NUMBER_TXT")).isEnabled() ? true : false;
        Assert.assertEquals(check, false);
        }
    }