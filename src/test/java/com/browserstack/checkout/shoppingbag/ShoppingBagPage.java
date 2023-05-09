package com.browserstack.checkout.shoppingbag;

import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import org.slf4j.Logger;

public class ShoppingBagPage extends BasePage {
    private static final Logger logger = LogHelper.getLogger();

    public ShoppingBagPage(KeywordWeb keywordWeb){
        super(keywordWeb);
    }


    public void login(String email, String password) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click("LOGIN_MENULEFT");
        Thread.sleep(2000);
        keyword.untilJqueryIsDone(50L);
        keyword.click("MOBILE_BTN_LOGIN");
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("LOGIN_TXT_EMAIL", email);
        Thread.sleep(2000);
        keyword.sendKeys("LOGIN_TXT_PASSWORD", password);
        Thread.sleep(2000);
        keyword.click("LOGIN_BTN_SUBMITLOGIN");
        keyword.untilJqueryIsDone(50L);

    }

    public void acceptAllCookies() throws InterruptedException {
        keyword.untilJqueryIsDone(60L);
        keyword.scrollToPositionByScript("window.scrollBy(0,300)");
        keyword.untilJqueryIsDone(60L);
        chooseLanguages();
        keyword.webDriverWaitForElementPresent("BTN_COOKIES", 50);
        if (keyword.verifyElementPresent("BTN_COOKIES")) {
            keyword.untilJqueryIsDone(60L);
            Thread.sleep(5000);
            keyword.click("BTN_COOKIES");
        }
    }

    public void chooseLanguages() throws InterruptedException {
        logger.info("choose language");
        Thread.sleep(7000);
        if (keyword.verifyElementPresent("LOGIN_BTN_LANGUAGE")) {
            Thread.sleep(7000);
            keyword.click("LOGIN_BTN_LANGUAGE");
        }
    }
    public void addProduct(String url) throws InterruptedException {
        Thread.sleep(5000);
        keyword.navigateToUrl(url);
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.scrollToPositionByScript("window.scrollBy(0,500)");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click("CHECKOUT_ADDPRODUCT_BTN_ADD");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click("CHECKOUT_ADDPRODUCT_CHECKBOX_SIZE_H");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        //keyword.click("CHECKOUT_ADDPRODUCT_BTN_CLOSE");
        keyword.click("CHECKOUT_ADDPRODUCT_BTN_ADD_SIZE");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
    }
    public void clickShoppingBagPage() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        Thread.sleep(5000);
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
//        keyword.scrollDownToElement("CHECKOUT_BTN_MINICART");
        keyword.click("CHECKOUT_BTN_MINICART");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        Thread.sleep(5000);
        keyword.click("CHECKOUT_MINICART_VIEWALL");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(2000);
    }
    public void removeProduct(String typeOfProduct) throws InterruptedException {
        Thread.sleep(2000);
        keyword.click(typeOfProduct);
        keyword.webDriverWaitForElementPresent("CHECKOUT_LBL_REMOVE",10);
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click("CHECKOUT_BTN_CONFIRM_REMOVE");
        if(!keyword.verifyElementPresent(typeOfProduct)){
            logger.info("Passed");
        }
    }
    public void confirmMessage(String messages) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.imWait(10);
        keyword.verifyElementVisible(messages);
    }
    public void addProductWithGift(String url) throws InterruptedException {

        Thread.sleep(5000);
        keyword.navigateToUrl(url);
        keyword.scrollToPositionByScript("window.scrollBy(0,300)");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click("CHECKOUT_ADDPRODUCT_BTN_ADD");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click("CHECKOUT_CHECKBOX_WOMENRING_GIFT");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click("CHECKOUT_CHECKBOX_MENRING_GIFT");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click("CHECKOUT_ADDPRODUCT_BTN_CLOSE");
        keyword.click("CHECKOUT_ADDPRODUCT_BTN_ADD");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        Thread.sleep(5000);
    }



}
