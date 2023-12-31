package com.browserstack.checkout.loginaddress;

import com.browserstack.checkout.shoppingbag.ShoppingBagPage;
import com.browserstack.common.LoginPage;
import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import core.PropertiesFile;
import org.slf4j.Logger;


import java.util.Date;

import static core.BaseTest.jse;

public class LoginAddressPage extends BasePage {
    private static Logger logger = LogHelper.getLogger();

    public LoginAddressPage(KeywordWeb key) {
        super(key);
    }

    public LoginAddressPage() {
        super();
    }

    public ShoppingBagPage objShoppingBagPage;

//    private LoginPage objLogin = new LoginPage(this.keyword);
    private LoginPage objLogin;
    public void moveToAddressPage() throws InterruptedException {
        Thread.sleep(5000);
        keyword.webDriverWaitForElementPresent("CHECKOUT_BTN_CONTINUE_GUEST", 40);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        keyword.click("CHECKOUT_BTN_CONTINUE_GUEST");
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

    }

    public void moveToPagecheckOut() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        Thread.sleep(7000);
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        keyword.verifyElementPresent("CHECKOUT_BTN_CHECKOUT");
        System.out.printf("done===");
        logger.info("DONE-1=======");
//        Thread.sleep(5000);
        keyword.click("CHECKOUT_BTN_CHECKOUT");
        logger.info("DONE-=====");
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        Thread.sleep(5000);
        keyword.webDriverWaitForElementPresent("CHECKOUT_LA_LBL_CHECKOUT", 20);
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");

    }
    public void fillContactInformation(boolean isSuggestion, String street,
                                       String code, String city) throws InterruptedException {
        String timestamp = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        keyword.webDriverWaitForElementPresent("CHECKOUT_LA_TBX_FIRST",20);
        keyword.sendKeys("CHECKOUT_LA_TBX_FIRST","LOGIN_DATA_ALERT_USERNAME");
        keyword.sendKeys("CHECKOUT_LA_TBX_LAST","LOGIN_DATA_ALERT_USERNAME");
        String mail = "dung"+timestamp+"@gmail.com";
        PropertiesFile.serPropValue("CHECKOUT_LA_DATA_EMAIL",mail);
        keyword.sendKeys("CHECKOUT_LA_TBX_EMAIL","CHECKOUT_LA_DATA_EMAIL");
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("CHECKOUT_LA_TBX_CFEMAIL","CHECKOUT_LA_DATA_EMAIL");
        keyword.sendKeys("CHECKOUT_LA_TBX_PHONE","AFFIRM_DATA_PHONE");
        if (isSuggestion){
            keyword.sendKeys("CHECKOUT_LA_TBX_STREET","CHECKOUT_LA_DATA_STREET_2");
            Thread.sleep(2000);
            keyword.sendKeys("CHECKOUT_LA_TBX_STREET"," ");
            keyword.webDriverWaitForElementPresent("CHECKOUT_LA_SUGGESTLIST",40);
            keyword.click("CHECKOUT_LA_SUGGESTLIST");
        }else {
            keyword.sendKeys("CHECKOUT_LA_TBX_STREET",street);
            keyword.sendKeys("CHECKOUT_LA_TBX_ZIP",code);
            keyword.sendKeys("CHECKOUT_LA_TBX_CITY",city);
        }
        keyword.click("CHECKOUT_BTN_CHECKOUT_ADDRESS");

    }
    public void verifyMelissa() throws InterruptedException {
        keyword.untilJqueryIsDone(70L);
        keyword.webDriverWaitForElementPresent("CHECKOUT_LA_SHIPMENT_SELECTED",20);
        keyword.webDriverWaitForElementPresent("CHECKOUT_LA_MELISSA_ENABLE",20);
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
    }
    public void deleteAllCookies() throws InterruptedException {
//        objLoginAddress = new LoginAddressPage(this.keyword);

        keyword.deleteAllCookies();
        keyword.reLoadPage();
        keyword.untilJqueryIsDone(50L);
    }
    public void resetForNewCase() throws InterruptedException {
        Thread.sleep(2000);
        deleteAllCookies();
//        objShoppingBagPage = new ShoppingBagPage(this.keyword);
        objLogin = new LoginPage(this.keyword);
        objLogin.acceptAllCookies();
    }
    public void compareAddress(String data, String location){
        keyword.webDriverWaitForElementPresent(location,10);
        keyword.assertEquals(data,location);
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

    }
    public void chooseAddressOnValidation(boolean isSuggest, String btnAdd) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        if (!isSuggest){
            Thread.sleep(2000);
            keyword.click("CHECKOUT_LA_CBX_YOURINPUT");
        }
        keyword.untilJqueryIsDone(50L);
        keyword.click(btnAdd);
    }
    public void addNewAddress(boolean isSuggestion, String street,
                              String code, String city, String btnAdd) throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        Thread.sleep(2000);
        keyword.click(btnAdd);
        keyword.untilJqueryIsDone(50L);
        keyword.webDriverWaitForElementPresent("CHECKOUT_LA_TBX_FIRST",20);
        keyword.sendKeys("CHECKOUT_LA_TBX_FIRST_2","LOGIN_DATA_ALERT_USERNAME");
        keyword.sendKeys("CHECKOUT_LA_TBX_LAST_2","LOGIN_DATA_ALERT_USERNAME");
        keyword.sendKeys("CHECKOUT_LA_TBX_PHONE_2","AFFIRM_DATA_PHONE");
        if (isSuggestion){
            keyword.sendKeys("CHECKOUT_LA_TBX_STREET_2","CHECKOUT_LA_DATA_STREET_2");
            Thread.sleep(2000);
            keyword.sendKeys("CHECKOUT_LA_TBX_STREET_2"," ");
//            keyword.keysBoardWithDOWN("/html/body/div[4]/aside[4]/div[2]/div/div/form/div/div/fieldset/div/div/div");
//            keyword.pressEnter();
            if(street != null){
                keyword.webDriverWaitForElementPresent("CHECKOUT_LA_SUGGESTLIST_45",10);
                keyword.click("CHECKOUT_LA_SUGGESTLIST_45");

            }else{
                Thread.sleep(2000);
                keyword.webDriverWaitForElementPresent("CHECKOUT_LA_SUGGESTLIST_2",10);
                keyword.click("CHECKOUT_LA_SUGGESTLIST_2");
            }

        }else {
            keyword.sendKeys("CHECKOUT_LA_TBX_STREET_2",street);
            keyword.sendKeys("CHECKOUT_LA_TBX_ZIP_2",code);
            keyword.sendKeys("CHECKOUT_LA_TBX_CITY_2",city);
        }
        Thread.sleep(2000);
        if(keyword.verifyElementPresent("CHECKOUT_LA_LBL_CODE")){
            keyword.assertEquals("CHECKOUT_LA_MESSAGES_CODE","CHECKOUT_LA_LBL_CODE");
        }else{
            keyword.click("CHECKOUT_LA_BTN_SAVE_ADDRESS");
        }
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");


    }
    public void isAddNewAddress(){
        keyword.webDriverWaitForElementPresent("CHECKOUT_LA_MELISSA_ENABLE_2",30);
        keyword.webDriverWaitForElementPresent("CHECKOUT_LA_MELISSA_ENABLE_3",50);
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

    }
    public void editAddress(String btnEdit) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.click(btnEdit);
        keyword.untilJqueryIsDone(50L);
        keyword.clearText("CHECKOUT_LA_TBX_ZIP_2");
        keyword.sendKeys("CHECKOUT_LA_TBX_ZIP_2","BT62 4HB");
        keyword.click("CHECKOUT_LA_BTN_SAVE_ADDRESS");
    }
    public void goBack(String verifyElement) throws InterruptedException {
        //a[@class="actions action-back-to-cart"]
//        keyword.scrollDownToElement("CHECKOUT_LA_BTN_BACK");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click("CHECKOUT_LA_BTN_BACK");
        keyword.untilJqueryIsDone(50L);
        keyword.webDriverWaitForElementPresent(verifyElement,30);
    }
    public void resetAndRefillAddress() throws InterruptedException {
        keyword.webDriverWaitForElementPresent("CHECKOUT_LA_TBX_FIRST",20);
        keyword.clearText("CHECKOUT_LA_TBX_STREET");
        keyword.clearText("CHECKOUT_LA_TBX_ZIP");
        keyword.clearText("CHECKOUT_LA_TBX_CITY");
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("CHECKOUT_LA_TBX_STREET_3","CHECKOUT_LA_DATA_STREET_5");
        keyword.sendKeys("CHECKOUT_LA_TBX_ZIP_3","CHECKOUT_LA_DATA_CODE_3");
        keyword.sendKeys("CHECKOUT_LA_TBX_CITY_3","CHECKOUT_LA_DATA_CITY_3");
        keyword.click("CHECKOUT_BTN_CHECKOUT_ADDRESS");
    }
    public void editBillingAddress() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");

        keyword.click("CHECKOUT_BTN_EDIT_BILLING_ADDRESS");
        resetAndRefillAddress();
        verifyMelissa();
        compareAddress("CHECKOUT_DATA_EXPECT_DATA_3","CHECKOUT_LBL_ADDRESS_INFO");
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

    }
    public void loginOnLAPage() throws InterruptedException {
        keyword.sendKeys("CHECKOUT_LA_TBX_USERNAME","LOGIN_DATA_EMAIL");
        keyword.sendKeys("CHECKOUT_LA_TBX_PASS","LOGIN_DATA_PASSWORD");
        keyword.click("CHECKOUT_LA_BTN_LOGIN");
        keyword.untilJqueryIsDone(30L);
        keyword.webDriverWaitForElementPresent("CHECKOUT_LBL_CHECKOUT",10);
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

    }
    public void loginFailed(String flag) throws InterruptedException {
        if (flag.equals("noEmail")){
            keyword.untilJqueryIsDone(50L);
            keyword.sendKeys("CHECKOUT_LA_TBX_PASS","LOGIN_DATA_PASSWORD");
            keyword.untilJqueryIsDone(30L);
            keyword.click("CHECKOUT_LA_BTN_LOGIN");
            keyword.untilJqueryIsDone(30L);
            keyword.assertEquals("CHECKOUT_MESSAGES_UPDATE_24","CHECKOUT_LA_LBL_ERROR_MAIL");
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

        }else if(flag.equals("wrongPass")){
            keyword.sendKeys("CHECKOUT_LA_TBX_USERNAME","LOGIN_DATA_EMAIL");
            keyword.sendKeys("CHECKOUT_LA_TBX_PASS","CHECKOUT_DATA_PASSWORD_ENTER");
            keyword.click("CHECKOUT_LA_BTN_LOGIN");
            keyword.untilJqueryIsDone(30L);
            keyword.assertEquals("CHECKOUT_LA_DATA_ERROR_PASS","CHECKOUT_LA_LBL_ERROR_PASS");
        } else if (flag.equals("invalidEmail")) {
            keyword.sendKeys("CHECKOUT_LA_TBX_USERNAME","LOGIN_DATA_PASSWORD");
            keyword.sendKeys("CHECKOUT_LA_TBX_PASS","CHECKOUT_DATA_PASSWORD_ENTER");
            keyword.click("CHECKOUT_LA_BTN_LOGIN");
            keyword.untilJqueryIsDone(30L);
            keyword.assertEquals("CHECKOUT_LA_MESSAGE_USERNAME","CHECKOUT_LA_LBL_ERROR_MAIL");
        }
    }
    public void addNewBillingAddress(boolean isSuggestion, String street,
                                     String code, String city) throws InterruptedException {
        keyword.untilJqueryIsDone(70L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click("CHECKOUT_HPL_NEW_ADDRESS_LOGIN");
        keyword.untilJqueryIsDone(50L);
        keyword.webDriverWaitForElementPresent("CHECKOUT_LA_TBX_FIRST",20);
        keyword.sendKeys("CHECKOUT_LA_TBX_FIRST_3","LOGIN_DATA_ALERT_USERNAME");
        keyword.sendKeys("CHECKOUT_LA_TBX_LAST_3","LOGIN_DATA_ALERT_USERNAME");
        keyword.sendKeys("CHECKOUT_LA_TBX_PHONE_3","AFFIRM_DATA_PHONE");
        if (isSuggestion){
            keyword.sendKeys("CHECKOUT_LA_TBX_STREET_4","CHECKOUT_LA_DATA_STREET_2");
            keyword.webDriverWaitForElementPresent("CHECKOUT_LA_SUGGESTLIST",10);
            keyword.click("CHECKOUT_LA_SUGGESTLIST");
        }else {
            keyword.sendKeys("CHECKOUT_LA_TBX_STREET_4",street);
            keyword.sendKeys("CHECKOUT_LA_TBX_ZIP_4",code);
            keyword.sendKeys("CHECKOUT_LA_TBX_CITY_4",city);
        }
        keyword.click("CHECKOUT_LA_BTN_SAVE_ADDRESS_2");
    }
    public void removeAddress(String btnRemove) throws InterruptedException {
        keyword.webDriverWaitForElementPresent(btnRemove,20);
        keyword.untilJqueryIsDone(70L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click(btnRemove);
        keyword.untilJqueryIsDone(70L);
        keyword.click("CHECKOUT_LA_BTN_REMOVE_BILLING_ADDRESS_CF");
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.waitForElementNotVisible(10,btnRemove);
    }



}
