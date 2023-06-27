package com.browserstack.checkout.loginaddress;

import com.browserstack.checkout.shoppingbag.ShoppingBagPage;
import com.browserstack.common.LoginPage;
import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import org.slf4j.Logger;

import static core.BaseTest.jse;

public class LoginAndAddressPage extends BasePage {
    private static Logger logger = LogHelper.getLogger();
    private LoginPage objLogin ;
    private ShoppingBagPage objShoppingBagPage;

    public LoginAndAddressPage(){
    }
    public LoginAndAddressPage(KeywordWeb keywordWeb){
        super(keywordWeb);
        objShoppingBagPage= new ShoppingBagPage(this.keyword);
    }
    //Login customer with Email not exist or Password invalid from checkout page
    public void loginCustomerWithEmailNotExistPassword() throws InterruptedException {
        objShoppingBagPage.addProduct("https://stage.glamira.co.uk/mens-ring-smart-queen-skup7013.html?alloy=white-375&utm_widget=recommendation");
        objShoppingBagPage.clickShoppingBagPage();
        objShoppingBagPage.moveToPagecheckOut();
        keyword.untilJqueryIsDone(100L);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        keyword.sendKeys("LA_INPUT_EMAIL", "LA_DATA_EMAIL");
        keyword.sendKeys("LA_INPUT_PASSWORD", "LA_DATA_PASSWORD");
        keyword.untilJqueryIsDone(10L);
        keyword.click("LA_BTN_LOGIN");
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
        keyword.sendKeys("LP_INPUT_FIRST_NAME", firstName);
        keyword.sendKeys("LP_INPUT_LAST_NAME", lastName);
        keyword.sendKeys("LP_INPUT_EMAIL", email);
        keyword.sendKeys("LP_INPUT_CONFIRM_EMAIL", confirmEmail);
        keyword.sendKeys("LP_INPUT_TELEPHONE", phone);
        keyword.sendKeys("LP_INPUT_COMPANY", company);
        keyword.sendKeys("LP_INPUT_STREET", street);
        keyword.sendKeys("LP_INPUT_POSTAL", zipCode);
        keyword.sendKeys("LP_INPUT_CITY", country);
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


}
