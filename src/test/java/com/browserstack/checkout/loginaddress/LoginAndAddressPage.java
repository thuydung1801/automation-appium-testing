package com.browserstack.checkout.loginaddress;

import com.browserstack.checkout.shoppingbag.ShoppingBagPage;
import com.browserstack.common.LoginPage;
import com.microsoft.playwright.T;
import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import org.slf4j.Logger;

import static core.BaseTest.jse;

public class LoginAndAddressPage extends BasePage {
    private static Logger logger = LogHelper.getLogger();
    private LoginPage objLogin ;
    private ShoppingBagPage objShoppingBagPage;
    private LoginAddressPage objLoginAddress;

    public LoginAndAddressPage(){
    }
    public LoginAndAddressPage(KeywordWeb keywordWeb){
        super(keywordWeb);
        objShoppingBagPage= new ShoppingBagPage(this.keyword);
        objLoginAddress = new LoginAddressPage(this.keyword);
    }
    public void loginCustomer(String email, String password) throws InterruptedException {
        keyword.untilJqueryIsDone(100L);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        keyword.sendKeys("LA_INPUT_EMAIL", email);
        keyword.sendKeys("LA_INPUT_PASSWORD", password);
        keyword.untilJqueryIsDone(10L);
        keyword.click("LA_BTN_LOGIN");
        Thread.sleep(3000);
    }
    //Login customer with Email not exist or Password invalid from checkout page
    public void loginCustomerWithEmailNotExistPassword() throws InterruptedException {
        objShoppingBagPage.addProduct("https://stage.glamira.co.uk/mens-ring-smart-queen-skup7013.html?alloy=white-375&utm_widget=recommendation");
        objShoppingBagPage.clickShoppingBagPage();
        objShoppingBagPage.moveToPagecheckOut();
        loginCustomer("LA_DATA_EMAIL","LA_DATA_PASSWORD");
        keyword.untilJqueryIsDone(100L);
        keyword.assertEquals("LA_MESSAGE_INVALID_LOGIN", "LA_INPUT_MESSAGE_INVALID_LOGIN");
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
        Thread.sleep(5000);
    }
    public void clearTextAndSendKey(String clearText, String inputSendKey, String dataSendKey) throws InterruptedException {
        keyword.clearText(clearText);
        keyword.sendKeys(inputSendKey, dataSendKey);
    }
    //Input invalid email on the Login and Address tab
    public void invalidEmailOnTheLogin() throws InterruptedException {
        clearTextAndSendKey("LA_INPUT_EMAIL", "LA_INPUT_EMAIL", "LA_DATA_EMAIL_INVALID");
        keyword.click("LA_BTN_LOGIN");
        keyword.untilJqueryIsDone(100L);
        keyword.assertEquals("LA_MESSAGE_EMAIL_INVALID", "CHECKOUT_LA_LBL_ERROR_MAIL");
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

    }
    //Leave blank Email and Password
    public void leaveBlankEmailAndPassword() throws InterruptedException {
        keyword.clearText("LA_INPUT_EMAIL");
        keyword.clearText("LA_INPUT_PASSWORD");
        keyword.click("CHECKOUT_LA_BTN_LOGIN");
        keyword.assertEquals("LA_LIST_MESSAGE_REQUIRED_FIELD", "CHECKOUT_LA_LBL_ERROR_MAIL");
        keyword.assertEquals("LA_LIST_MESSAGE_REQUIRED_FIELD", "LA_ERROR_PASSWORD");
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

    }
    //Check link forgot password
    public void checkLinkForgotPassword() throws InterruptedException {
        keyword.click("LA_LINK_FORGOT_PASSWORD");
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementVisible("LP_FORGOT_PASSWORD_PAGE");
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
        Thread.sleep(5000);
    }
    public void sendAllDataForm(String firstName, String lastName, String email, String confirmEmail, String phone, String company, String street, String zipCode, String country) throws InterruptedException {
        Thread.sleep(2000);
//        keyword.click("LP_INPUT_FIRST_NAME");
        keyword.sendKeys("LP_INPUT_TELEPHONE", phone);
        keyword.sendKeys("LP_INPUT_COMPANY", company);
        keyword.sendKeys("LP_INPUT_STREET", street);
        keyword.sendKeys("LP_INPUT_POSTAL", zipCode);
        keyword.sendKeys("LP_INPUT_CITY", country);
        keyword.sendKeys("LP_INPUT_FIRST_NAME", firstName);
        keyword.sendKeys("LP_INPUT_LAST_NAME", lastName);
        keyword.sendKeys("LP_INPUT_EMAIL", email);
        keyword.sendKeys("LP_INPUT_CONFIRM_EMAIL", confirmEmail);

    }
    //Next to Payment page successfully with Guest option and Ship to this address is yes
    public void nextToPaymentPageWithAddress() throws InterruptedException {
        keyword.click("LA_BTN_BACK");
        keyword.untilJqueryIsDone(50L);
        keyword.click("LP_BTN_CONTINUE");
        sendAllDataForm("LA_DATA_FIRST_NAME", "LA_DATA_LAST_NAME", "LA_DATA_EMAIL",
                "LP_EMAIL_CONFIRM", "LA_DATA_PASSWORD", "LP_DATA_COMPANY",
                "LP_DATA_STREET", "LP_POSTAL_CODE_MALTA", "LP_COUNTRY");
        keyword.assertEquals("LA_MESSAGE_EMAIL_CONFIRM_ERROR", "LP_INPUT_EMAIL_CONFIRM_ERROR");
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
        Thread.sleep(5000);
    }
    //    emailInvalid
    public void emailInvalid() throws InterruptedException {
        clearTextAndSendKey("LP_INPUT_CONFIRM_EMAIL", "LP_INPUT_CONFIRM_EMAIL", "LP_EMAIL_INVALID");
        keyword.untilJqueryIsDone(70L);
        keyword.assertEquals("LA_MESSAGE_EMAIL_INVALID", "LP_EMAIL_CONFIRM_INVALID");
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
        Thread.sleep(5000);
    }
    public void checkNotCopyPasteEmail() throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.clearText("LP_INPUT_EMAIL");
        keyword.clearText("LP_INPUT_CONFIRM_EMAIL");
        keyword.untilJqueryIsDone(30L);
        keyword.copyPaste("LP_INPUT_EMAIL", "LP_INPUT_CONFIRM_EMAIL","LP_DATA_EMAIL_COPY_PASTE");
//        keyword.checkNotCopyPastesKeyboardEvents("LP_INPUT_EMAIL", "LP_DATA_EMAIL_COPY_PASTE");
        keyword.untilJqueryIsDone(10L);
        keyword.assertEquals("LA_MESSAGE_NOT_ALLOW", "LA_INPUT_MESSAGE_NOT_ALLOW_ACTION");
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
        Thread.sleep(5000);
    }
    //clear all
    public void clearAllData() throws InterruptedException {
        keyword.clearText("LP_INPUT_FIRST_NAME");
        keyword.clearText("LP_INPUT_LAST_NAME");
        keyword.clearText("LP_INPUT_EMAIL");
        keyword.clearText("LP_INPUT_TELEPHONE");
        keyword.clearText("LP_INPUT_COMPANY");
        keyword.clearText("LP_INPUT_STREET");
        keyword.clearText("LP_INPUT_POSTAL");
        keyword.clearText("LP_INPUT_CITY");
    }
    //verify All  required field
    public void verifyAllRequiredField() throws InterruptedException {
        keyword.assertEquals("LA_LIST_MESSAGE_REQUIRED_FIELD", "LP_MESSAGE_FIELD_FIRST_NAME");
        keyword.assertEquals("LA_LIST_MESSAGE_REQUIRED_FIELD", "LA_MESSAGE_FIELD_LAST_NAME");
        keyword.assertEquals("LA_LIST_MESSAGE_REQUIRED_FIELD", "LA_MESSAGE_FIELD_EMAIL");
        keyword.assertEquals("LA_LIST_MESSAGE_REQUIRED_FIELD", "LA_MESSAGE_FIELD_CONFIRM_EMAIL");
        keyword.assertEquals("LA_LIST_MESSAGE_REQUIRED_FIELD", "LA_MESSAGE_FIELD_PHONE");
        keyword.assertEquals("LA_LIST_MESSAGE_REQUIRED_FIELD", "LA_MESSAGE_STREET_FIELD");
        keyword.assertEquals("LA_LIST_MESSAGE_REQUIRED_FIELD", "LA_MESSAGE_FIELD_ZIP_CODE");
        keyword.assertEquals("LA_LIST_MESSAGE_REQUIRED_FIELD", "LA_MESSAGE_FIELD_CITY");
    }
    //    leaveBlankRequiredForm
    public void leaveBlankRequiredForm() throws InterruptedException {
        keyword.click("LP_BTN_CONTINUE");
        keyword.untilJqueryIsDone(20L);
        clearAllData();
        keyword.click("CHECKOUT_BTN_CHECKOUT_ADDRESS");
        keyword.untilJqueryIsDone(30L);
        verifyAllRequiredField();
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");


    }
    //    enterInvalidPhone
    public void enterInvalidPhone() throws InterruptedException {
        Thread.sleep(5000);
        sendAllDataForm("LA_DATA_FIRST_NAME", "LA_DATA_LAST_NAME", "LA_DATA_EMAIL",
                "LA_DATA_CONFIRM_EMAIL", "LA_DATA_PHONE_INVALID", "LP_DATA_COMPANY",
                "LP_DATA_STREET", "LP_POSTAL_CODE_MALTA", "LP_COUNTRY");
        keyword.assertEquals("LA_DATA_MESSAGE_PHONE_FIELD", "LA_MESSAGE_FIELD_PHONE");
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

    }
    public void nextToPaymentSuccess() throws InterruptedException {
        Thread.sleep(5000);
        clearTextAndSendKey("LP_INPUT_TELEPHONE", "LP_INPUT_TELEPHONE", "LA_DATA_PHONE");
        keyword.click("CHECKOUT_BTN_CHECKOUT_ADDRESS");
        keyword.waitForElementNotVisible(60, "//div[@class='loading-mask']");
        keyword.untilJqueryIsDone(60L);
        keyword.click("LA_CHOOSE_ITEM_ADDRESS");
        keyword.untilJqueryIsDone(100L);
        keyword.click("LA_BTN_APPLY_ADDRESS");
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        keyword.untilJqueryIsDone(20L);
        Thread.sleep(5000);
        String textShipping = keyword.getText("LP_INPUT_MESSAGE_SHOPPING_ADDRESS").replaceAll("\\s", " ");
        System.out.printf("=====" + textShipping );
        keyword.simpleAssertEquals("LA_SHIPPING_ADDRESS_MESSAGE", textShipping);
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

    }
    //    getAttributeValue
    public void verifyFillAllData() throws InterruptedException {
        keyword.verifyAttributeValues("LA_DATA_FIRST_NAME", "LA_INPUT_FILL_DATA_FIRST_NAME");
        keyword.verifyAttributeValues("LA_DATA_LAST_NAME", "LA_INPUT_FILL_DATA_LAST_NAME");
        keyword.verifyAttributeValues("LA_DATA_PHONE", "LA_INPUT_FILL_DATA_PHONE");
        keyword.verifyAttributeValues("LP_DATA_COMPANY", "LA_INPUT_FILL_DATA_COMPANY");
        keyword.verifyAttributeValues("LP_DATA_STREET", "LA_INPUT_FILL_DATA_STREET");
        keyword.verifyAttributeValues("LP_POSTAL_CODE_MALTA", "LA_INPUT_FILL_DATA_POSTCODE");
        keyword.verifyAttributeValues("LP_COUNTRY", "LA_INPUT_FILL_DATA_CITY");
//        keyword.getAttributeValues("LP_COUNTRY", "LA_INPUT_FILL_DATA_COUNTRY_ID");
    }
    //    nextToPaymentSuccessOnChangeNoShipToAddress
    public void nextToPaymentSuccessOnChangeNoShipToAddress() throws InterruptedException {
        keyword.untilJqueryIsDone(20L);
        keyword.clearLocalStorage();
        keyword.reLoadPage();
        keyword.untilJqueryIsDone(50L);
        keyword.untilJqueryIsDone(50L);
        keyword.click("LP_BTN_CONTINUE");
        sendAllDataForm("LA_DATA_FIRST_NAME", "LA_DATA_LAST_NAME", "LA_DATA_EMAIL",
                "LA_DATA_CONFIRM_EMAIL", "LA_DATA_PHONE", "LP_DATA_COMPANY",
                "LP_DATA_STREET", "LP_POSTAL_CODE_MALTA", "LP_COUNTRY");
        keyword.click("LA_BTN_NO_SHIP_ADDRESS");
        keyword.untilJqueryIsDone(50L);
        keyword.click("LP_BTN_TO_PLAY");
        verifyFillAllData();
    }

    //    editAddressWithCustomerNotLogin
    public void editAddressWithCustomerNotLogin() throws InterruptedException {
        Thread.sleep(2000);
        keyword.click("LA_INPUT_SAVE_ADDRESS");
        keyword.waitForElementNotVisible(60, "//div[@class='loading-mask']");
        clearTextAndSendKey("LA_EDIT_INPUT_LAST_NAME", "LA_EDIT_INPUT_LAST_NAME", "LA_DATA_EDIT_INPUT_LAST_NAME");
        keyword.click("LA_BTN_SAVE_SHIPPMENT");
        Thread.sleep(5000);
        keyword.assertEquals("LA_MESSAGE_SAVE_SUCCESS", "LA_INPUT_MESSAGE_SAVE_SUCCESS");
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
    }
    //Add New Address successfully with customer login
    public void addNewAddress() throws InterruptedException {
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        keyword.untilJqueryIsDone(20L);
        keyword.click("LA_BTN_BACK_CHECKOUT");
        keyword.untilJqueryIsDone(70L);
        loginCustomer("COM_INP_DATA_EMAIL_STAGE","COM_INP_DATA_PASS_STAGE");
        Thread.sleep(7000);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        keyword.click("LA_BTN_EDIT_LOGIN_ADDRESS");
        keyword.untilJqueryIsDone(100L);
        objLoginAddress.addNewBillingAddress(false, "CHECKOUT_LA_DATA_STREET_1",
                "CHECKOUT_LA_DATA_CODE_1", "CHECKOUT_LA_DATA_CITY_1");
        keyword.assertEquals("LA_MESSAGE_SAVE_SUCCESS", "LA_INPUT_MESSAGE_SAVE_SUCCESS");
        objLoginAddress.verifyMelissa();
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

    }
    public void addNewAddressFillAllRequiredField() throws InterruptedException {
        Thread.sleep(5000);
        keyword.untilJqueryIsDone(70L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click("CHECKOUT_HPL_NEW_ADDRESS_LOGIN");
        keyword.untilJqueryIsDone(50L);
        keyword.click("CHECKOUT_LA_BTN_SAVE_ADDRESS_2");
        keyword.assertEquals("LA_LIST_MESSAGE_REQUIRED_FIELD", "LA_MESSAGE_FIELD_PHONE");
        keyword.assertEquals("LA_LIST_MESSAGE_REQUIRED_FIELD", "LA_MESSAGE_STREET_FIELD");
        keyword.assertEquals("LA_LIST_MESSAGE_REQUIRED_FIELD", "LA_MESSAGE_FIELD_ZIP_CODE");
        keyword.assertEquals("LA_LIST_MESSAGE_REQUIRED_FIELD", "LA_MESSAGE_FIELD_CITY");
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

    }
    //    removeAddressWithCustomerLogin
    public void removeAddressWithCustomerLogin() throws InterruptedException {
        Thread.sleep(5000);
        objLoginAddress.removeAddress("LA_BTN_REMOVE_BILLING_ADDRESS");

    }
    //editAddressWithCustomerLogin
    public void editAddressWithCustomerLogin() throws InterruptedException {
        Thread.sleep(7000);
        keyword.click("LA_BTN_EDIT_CUSTOMER_LOGIN");
        clearTextAndSendKey("LA_EDIT_INPUT_FIRST_NAME", "LA_EDIT_INPUT_FIRST_NAME", "LA_DATA_EDIT_INPUT_FIRST_NAME");
        keyword.click("CHECKOUT_LA_BTN_SAVE_ADDRESS_2");
        keyword.untilJqueryIsDone(50L);
        keyword.assertEquals("LA_MESSAGE_SAVE_SUCCESS", "LA_INPUT_MESSAGE_SAVE_SUCCESS");
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

    }




}
