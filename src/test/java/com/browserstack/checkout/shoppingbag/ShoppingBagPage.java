package com.browserstack.checkout.shoppingbag;

import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;

import static core.BaseTest.driver;

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
        Thread.sleep(6000);

//        keyword.clickAction(typeOfProduct, 366, 294);
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
    public void viewDetail(String typeOfProduct) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click(typeOfProduct);

    }
    public void inputEngravingwithSingleRing(String data, String btnAdd, String engraving) throws InterruptedException {
        keyword.webDriverWaitForElementPresent("CHECKOUT_LBL_VIEWDETAIL",5);
        keyword.untilJqueryIsDone(30L);
        keyword.verifyElementPresent(btnAdd);
        keyword.click(btnAdd);
        keyword.imWait(30);
        keyword.webDriverWaitForElementPresent("CHECKOUT_TITLE_ENGRAVING",5);
        keyword.imWait(30);
        keyword.clearText("CHECKOUT_TXT_ENGRAVING");
        Thread.sleep(2000);
        keyword.untilJqueryIsDone(30L);
        keyword.sendKeys("CHECKOUT_TXT_ENGRAVING", data);
        keyword.untilJqueryIsDone(30L);
        keyword.click("CHECKOUT_VIEWDETAIL_BTN_SAVE");
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        viewDetail("CHECKOUT_BTN_VIEWDETAIL_GLAMIRARING_MOBILE");
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.assertEquals(data, engraving);
    }
    public void changeQty(String qty){
        keyword.imWait(5);
        keyword.selectDropDownListByName("CHECKOUT_DDL_QTY",
                qty);
    }


}
