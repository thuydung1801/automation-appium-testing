package com.browserstack.checkout.loginaddress;

import com.browserstack.checkout.shoppingbag.ShoppingBagPage;
import com.browserstack.common.LoginPage;
import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import org.slf4j.Logger;

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
    }
    //Leave blank Email and Password
    public void leaveBlankEmailAndPassword() throws InterruptedException {
        keyword.clearText("LA_INPUT_EMAIL");
        keyword.clearText("LA_INPUT_PASSWORD");
        keyword.click("CHECKOUT_LA_BTN_LOGIN");
        keyword.assertEquals("LA_LIST_MESSAGE_REQUIRED_FIELD", "CHECKOUT_LA_LBL_ERROR_MAIL");
        keyword.assertEquals("LA_LIST_MESSAGE_REQUIRED_FIELD", "LA_ERROR_PASSWORD");
    }


}
