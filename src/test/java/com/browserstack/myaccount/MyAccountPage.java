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

import java.util.ArrayList;
import java.util.List;

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
    public void chooseScreenTest(String Screen) {
        keyword.click("MY_ACCOUNT_BTN");
        switch (Screen) {
            case "personInformation" :
                keyword.click("MAC_PERSONAL_INFORM_BTN");
                keyword.webDriverWaitForElementPresent("MAC_PERSONAL_INFORM_SCREEN", 20);
                break;
            case "myAddress" :
                keyword.click("MAC_MY_ADDRESS_BTN");
                keyword.webDriverWaitForElementPresent("MAC_MY_ADDRESS_SCREEN",20);
                break;
            case "myWishlist" :
                keyword.click("MAC_MY_WISHLIST_BTN");
                keyword.webDriverWaitForElementPresent("MAC_MY_WISHLIST_SCREEN",20);
                break;
            case "myOverview" :
                keyword.click("MAC_MY_OVERVIEW_BTN");
                keyword.webDriverWaitForElementPresent("MAC_MY_OVERVIEW_SCREEN",20);
                break;
        }
    }

    public void inputPersonalInform(String firstName, String lastName,String newPass, String newMail, String change)  {
        chooseScreenTest("personInformation");
        if (change.equals("Email")) {
            keyword.sendKeys("MAC_FIRST_NAME_TXT",firstName);
            keyword.sendKeys("MAC_LAST_NAME_TXT",lastName);
            keyword.click("MAC_CHANGE_EMAIl_BTN");
            keyword.sendKeys("MAC_CHANGE_MAIL_CURRENT_PASS_TXT", "CURRENT_PASSWORD");
            keyword.sendKeys("MAC_EMAIL_TXT", newMail);
            PropertiesFile.serPropValue("CURRENT_EMAIL", newMail);
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

    public void checkChangePersonInform(String flag, String newPass, String newMail) throws InterruptedException{
        if(flag.equals("blank")) {
            keyword.assertEquals("MAC_SAVE_INFORM_ERROR_MESSAGE","MAC_ERROR_MESS1");
            keyword.assertEquals("MAC_SAVE_INFORM_ERROR_MESSAGE","MAC_ERROR_MESS2");
        }
        else {
            keyword.webDriverWaitForElementPresent("HELLO_MESSAGE", 20);
            keyword.assertEquals("MAC_VERIFY_CHANGE_SUCCESS","MAC_CHANGE_SUCCESS_MESS");
            if (flag.equals("successfully")) {
                String hello = keyword.getText("HELLO_MESSAGE");
                String firstName = PropertiesFile.getPropValue("FIRST_NAME");
                String lastName = PropertiesFile.getPropValue("LAST_NAME");
                boolean check = hello.contains(firstName + " " +  lastName) ? true : false;
                System.out.println(firstName);
                System.out.println(lastName);
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
                keyword.webDriverWaitForElementPresent("HELLO_MESSAGE",20);
            }
        }

    }
    public void isChangePersonalInform(String flag) throws InterruptedException {
        String newEmail = signUpPage.createNewEmail();
        String randomNumbers = RandomStringUtils.randomNumeric(3);
        String newPass = "Duccuong@" + randomNumbers;
        switch (flag) {
            case "successfully":
                inputPersonalInform("FIRST_NAME", "LAST_NAME", " ","","");
                checkChangePersonInform("successfully","","");
                break;
            case "blank":
                inputPersonalInform(" ", " ", " ","","");
                checkChangePersonInform("blank", "","");
                break;
            case "successfullyWithEmail":
                inputPersonalInform(" ", " ", "",newEmail,"Email");
                checkChangePersonInform("successfullyWithEmail", "",newEmail);
                break;
            case "successfullyWithPass":
                inputPersonalInform(" ", " ", newPass,"","Pass");
                checkChangePersonInform("successfullyWithPass", newPass,"CURRENT_EMAIL");
                break;
        }
    }
    public void goToDelete(String passWord){
        chooseScreenTest("personInformation");
        keyword.click("MAC_DELETE_ACCOUNT_BTN");
        keyword.sendKeys("MAC_DELETE_ACCOUNT_TXT", passWord);
        keyword.click("MAC_DELETE_ACCOUNT_BTN2");
        keyword.click("MAC_DELETE_ACCOUNT_OK_BTN");
        if(passWord.equals("PASSWORD")) {
            keyword.webDriverWaitForElementPresent("MY_ACCOUNT_BTN", 20);
            keyword.assertEquals("MAC_DELETE_ACC_FAIL_MESS","MAC_DELETE_ACCOUNT_FAIL");
        }
        else{
            keyword.webDriverWaitForElementPresent("MAC_UNDO_ACTION_BTN", 20);
            keyword.assertEquals("MAC_DELETE_ACC_SUCCESS_MESS", "MAC_DELETE_ACCOUNT_SUCCESS");
            keyword.click("MAC_UNDO_ACTION_BTN");
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
        chooseScreenTest("personInformation");
        boolean check = driver.findElement(By.xpath("MAC_PHONE_NUMBER_TXT")).isEnabled() ? true : false;
        Assert.assertEquals(check, false);
        }
    public void setAsDefaultAddress(){
        chooseScreenTest("myAddress");
        keyword.click("MAC_SET_AS_DEFAULT_BTN");
        keyword.webDriverWaitForElementPresent("MAC_CHANGE_SUCCESS_MESS",20);
        String billingAdd = keyword.getText("MAC_DEFAULT_BILLING_ADDRESS_LBL");
        String shippingAdd = keyword.getText("MAC_DEFAULT_BILLING_ADDRESS_LBL");
        String addAddress = keyword.getText("MAC_ADDITIONAL_ADDRESS_ROW");
        boolean check = (billingAdd.equals(addAddress) && shippingAdd.equals(addAddress)) ? true : false;
        Assert.assertEquals(check, true);
    }
    public void gotoEdit(String method){
        if(method.equals("defaultShipping")){
            keyword.click("MAC_DEFAULT_SHIPPING_BTN");
        }
        else if(method.equals("defaultBilling")){
            keyword.click("MAC_DEFAULT_BILLING_BTN");
        }
        else{
            keyword.click("MAC_EDIT_ADDITIONAL_ADDRESS");
        }
        keyword.webDriverWaitForElementPresent("MAC_MY_ADDRESS_SCREEN",20);
    }
    public void editAddress(String method, String flag){
        gotoEdit(method);
        signUpPage.clearTextAndSendKey("MAC_FIRST_NAME_TXT","FIRST_NAME");
        signUpPage.clearTextAndSendKey("MAC_LAST_NAME_TXT","LAST_NAME");
        signUpPage.clearTextAndSendKey("MAC_COMPANY_TXT","MY_ACCOUNT_COMPANY");
        signUpPage.clearTextAndSendKey("MAC_STREET_TXT","MY_ACCOUNT_STREET");
        signUpPage.clearTextAndSendKey("MAC_STATE_TXT","MY_ACCOUNT_STATE");
        signUpPage.clearTextAndSendKey("MAC_ZIP_CODE_TXT","MY_ACCOUNT_ZIP_CODE");
        if(flag.equals("setBilling")){
            keyword.click("MAC_DEFAULT_BILLING_RADIO_BTN");
        }
        else {
            keyword.click("MAC_DEFAULT_SHIPPING_RADIO_BTN");
        }
        keyword.click("MAC_SAVE_ADDRESS_BTN");
    }
    public void checkAddress(String method, String flag){
        editAddress(method, flag);
        List<String> informEdit = new ArrayList<>();
        informEdit.add("FIRST_NAME"); informEdit.add("LAST_NAME"); informEdit.add("MY_ACCOUNT_COMPANY"); informEdit.add("MY_ACCOUNT_STREET");
        informEdit.add("MY_ACCOUNT_STATE"); informEdit.add("MY_ACCOUNT_ZIP_CODE");
        keyword.webDriverWaitForElementPresent("MAC_MY_ADDRESS_SCREEN",20);
        keyword.assertEquals("MAC_VERIFY_CHANGE_SUCCESS","MAC_CHANGE_SUCCESS_MESS");
        if(method.equals("defaultShipping") || flag.equals("setBilling")){
            String billingAdd = keyword.getText("MAC_DEFAULT_BILLING_ADDRESS_LBL");
            boolean check = (billingAdd.contains(informEdit.toString())) ? true : false;
            Assert.assertEquals(check , true);
        }
        else {
            String shippingAdd = keyword.getText("MAC_DEFAULT_SHIPPING_ADDRESS_LBL");
            boolean check = (shippingAdd.contains(informEdit.toString())) ? true : false;
            Assert.assertEquals(check, true);
        }
    }
}
