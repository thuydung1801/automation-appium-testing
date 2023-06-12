package com.browserstack.checkout.shoppingbag;

import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import core.PropertiesFile;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Set;

import static core.BaseTest.driver;
import static core.BaseTest.jse;


public class ShoppingBagPage extends BasePage {
    private static final Logger logger = LogHelper.getLogger();

    public ShoppingBagPage(KeywordWeb keywordWeb){
        super(keywordWeb);
    }


    public void addProduct(String url) throws InterruptedException {
        Thread.sleep(5000);
        keyword.navigateToUrl(url);
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        Thread.sleep(5000);
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
        keyword.scrollToPositionByScript("window.scrollBy(0,500)");
        keyword.click("CHECKOUT_BTN_MINICART");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        Thread.sleep(5000);
        keyword.click("CHECKOUT_MINICART_VIEWALL");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(2000);
    }
    public void removeProduct(String typeOfProduct) throws InterruptedException {
        Thread.sleep(7000);
//        keyword.clickAction(typeOfProduct, 366, 294);
        keyword.click(typeOfProduct);
        keyword.webDriverWaitForElementPresent("CHECKOUT_LBL_REMOVE",10);
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click("CHECKOUT_BTN_CONFIRM_REMOVE");
//        if(!keyword.verifyElementPresent(typeOfProduct)){
//            logger.info("Passed");
//        }
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
    }
    public void confirmMessage(String messages) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.imWait(10);
        Thread.sleep(3000);
//        keyword.verifyElementVisible(messages);
        if(keyword.verifyElementVisible(messages)){
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
        }else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Results not found\"}}");
        }
    }
    public void addProductWithGift(String url) throws InterruptedException {

        Thread.sleep(5000);
        keyword.navigateToUrl(url);
        Thread.sleep(5000);
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
        keyword.waitForElementNotVisible(60,"//div[@class='loading-mask']");
        Thread.sleep(2000);
        keyword.click(typeOfProduct);

    }
    public void inputEngravingwithSingleRing(String data, String btnAdd, String engraving) throws InterruptedException {
        keyword.webDriverWaitForElementPresent("CHECKOUT_LBL_VIEWDETAIL",5);
        keyword.untilJqueryIsDone(60L);
        keyword.verifyElementPresent(btnAdd);
        keyword.click(btnAdd);
        keyword.imWait(30);
//        keyword.webDriverWaitForElementPresent("CHECKOUT_TITLE_ENGRAVING",5);
//        keyword.imWait(30);
        keyword.clearText("CHECKOUT_TXT_ENGRAVING");
        Thread.sleep(2000);
        keyword.untilJqueryIsDone(30L);
//        keyword.click("CHECKOUT_TXT_ENGRAVING");

        Thread.sleep(5000);
        keyword.untilJqueryIsDone(50L);
        keyword.senKeyByJavascript(data,"single");
//        keyword.sendKeys("CHECKOUT_TXT_ENGRAVING", data);
        logger.info("send key done...");
        keyword.untilJqueryIsDone(30L);
        Thread.sleep(5000);
        keyword.click("CHECKOUT_VIEWDETAIL_BTN_SAVE");
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        viewDetail("CHECKOUT_BTN_VIEWDETAIL_GLAMIRARING_MOBILE");
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.assertEquals(data, engraving);
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
    }
    public void changeQty(String qty){
        keyword.imWait(5);
        keyword.selectDropDownListByName("CHECKOUT_DDL_QTY",
                qty);
    }
    public void backPage(String url){
        keyword.navigateToUrl(url+"checkout/cart/");
    }
    //input engraving of couple rings
    public void inputEngravingwithCoupleRing(String data, String btnAdd, String txtWomen,
                                             String txtMen ) throws InterruptedException {
        keyword.webDriverWaitForElementPresent("CHECKOUT_LBL_VIEWDETAIL",5);
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click(btnAdd);
        keyword.imWait(10);
        keyword.webDriverWaitForElementPresent("CHECKOUT_TITLE_ENGRAVING",5);
        keyword.imWait(10);
        keyword.clearText(txtWomen);
        keyword.imWait(3);
        Thread.sleep(5000);
        keyword.senKeyByJavascript(data,"couple");
//        keyword.sendKeys(txtWomen, data);
//        keyword.imWait(20);
//        Thread.sleep(5000);
//        keyword.clearText(txtMen);
//        keyword.senKeyByJavascript(data,"couple");
//        keyword.sendKeys(txtMen, data);
        Thread.sleep(2000);

    }
    public void inputCorrectly(String data, String engraving) throws InterruptedException {
        keyword.click("CHECKOUT_VIEWDETAIL_BTN_SAVE");
        Thread.sleep(10000);
        viewDetail("CHECKOUT_BTN_VIEWDETAIL_COUPLERING_MOBILE");
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");

        String actual = keyword.getText(engraving);
        String expect = PropertiesFile.getPropValue(data);
        keyword.simpleAssertEquals(expect+ " - "+ expect
                , actual);
        keyword.click("PRD_BTN_CLOSE_VIEWDETAIL");
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
    }
    public void inputError(String lblErrorMessage1, String lblErrorMessage2, String dataExpected, String engraving, boolean flag) throws InterruptedException {
        keyword.pressEnter();
        Thread.sleep(1000);
        keyword.verifyElementPresent(lblErrorMessage1);
        keyword.verifyElementPresent(lblErrorMessage2);
        if(flag) {
            keyword.click("CHECKOUT_VIEWDETAIL_BTN_SAVE");
            Thread.sleep(10000);
            String expect = PropertiesFile.getPropValue(dataExpected);
            viewDetail("CHECKOUT_BTN_VIEWDETAIL_COUPLERING_MOBILE");
            keyword.untilJqueryIsDone(30L);
            keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
            keyword.assertEquals(expect + " - " + expect, engraving);
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
        }
    }
    //add product without any option
    public void addProductWithOutOptions(String url) throws InterruptedException {
        Thread.sleep(5000);
        keyword.navigateToUrl(url);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        Thread.sleep(5000);
        keyword.scrollToPositionByScript("window.scrollBy(0,500)");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        Thread.sleep(5000);
        keyword.click("CHECKOUT_ADDPRODUCT_BTN_ADD");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");

    }
    //click button Edit depends on the type of product
    public void clickEdit(String btnEdit) throws InterruptedException {
        keyword.webDriverWaitForElementPresent("CHECKOUT_LBL_HEADER",10);
        Thread.sleep(2000);
        keyword.click(btnEdit);
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(60,"//div[@class='loading-mask']");
    }
    //edit option of ring to check update case
    public void editOptions() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        if(keyword.verifyElementVisible("CHECKOUT_CBX_STONE")){
            keyword.click("CHECKOUT_CBX_STONE");
            keyword.untilJqueryIsDone(50L);
        }
        keyword.click("CHECKOUT_CBX_BLACKDIAMOND");
        keyword.untilJqueryIsDone(30L);
        keyword.click("CHECKOUT_LBL_METAL");
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.webDriverWaitForElementPresent("CHECKOUT_CBX_METAL",10);
        keyword.scrollToPositionByScript("window.scrollBy(0,500)");
        keyword.click("CHECKOUT_CBX_METAL");
        keyword.click("CHECKOUT_BTN_UPDATE");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
    }
    //edit couple ring is already in the cart
    public void editCoupleRings() throws InterruptedException {
        Thread.sleep(5000);
        keyword.waitForElementNotVisible(60,"//div[@class='loading-mask']");
        keyword.click("CHECKOUT_LBL_STONE");
        keyword.webDriverWaitForElementPresent("CHECKOUT_CBX_DIAMOND",10);
        keyword.click("CHECKOUT_CBX_DIAMOND");
        keyword.scrollToPositionByScript("window.scrollBy(0,400)");
        keyword.click("CHECKOUT_LBL_PROFILE");
        keyword.webDriverWaitForElementPresent("CHECKOUT_CBX_PROFILE_B",10);
        keyword.click("CHECKOUT_CBX_PROFILE_B");
        keyword.webDriverWaitForElementPresent("CHECKOUT_DDL_SIZE",5);
        keyword.click("CHECKOUT_DDL_SIZE");
        keyword.click("CHECKOUT_CBX_SIZE_L");
        keyword.scrollToPositionByScript("window.scrollBy(0,500)");
        keyword.click("CHECKOUT_BTN_UPDATE");
        keyword.waitForElementNotVisible(60,"//div[@class='loading-mask']");
        Thread.sleep(3000);
//        keyword.webDriverWaitForElementPresent("CHECKOUT_MESSAGES_UPDATE_23",60);
    }
//    public void compareValueData(String expect, String actual) throws InterruptedException {
//        keyword.untilJqueryIsDone(60L);
//        keyword.waitForElementNotVisible(60,"//div[@class='loading-mask']");
//        Thread.sleep(1000);
//        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
//        keyword.assertEquals(expect, actual);
//    }
    public void compareData(String expect, String actual) throws InterruptedException {
        keyword.waitForElementNotVisible(60,"//div[@class='loading-mask']");
        Thread.sleep(1000);
        keyword.assertEquals(expect, actual);
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
    }
    public void missingFillSize() throws InterruptedException {
        Thread.sleep(5000);
        keyword.scrollToPositionByScript("window.scrollBy(0,500)");
        Thread.sleep(2000);
        keyword.click("CHECKOUT_DDL_SIZE");
        keyword.scrollToPositionByScript("window.scrollBy(0,300)");
        keyword.click("CHECKOUT_CBX_SIZE_CUSTOM");
        keyword.click("CHECKOUT_BTN_UPDATE");
    }
    public void moveToPagecheckOut() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        Thread.sleep(2000);
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.verifyElementPresent("CHECKOUT_BTN_CHECKOUT");
        keyword.click("CHECKOUT_BTN_CHECKOUT");
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        Thread.sleep(3000);
//        keyword.webDriverWaitForElementPresent("CHECKOUT_LBL_CHECKOUT",20);
    }
    public void checkOut() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.click("CHECKOUT_BTN_CHECKOUT_ADDRESS");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.webDriverWaitForElementPresent("CHECKOUT_BTN_CHECKOUT_SHIPMENT",5);
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.scrollToPositionByScript("window.scrollBy(0,400)");
//        keyword.scrollDownToElement("CHECKOUT_BTN_CHECKOUT_SHIPMENT");
        keyword.click("CHECKOUT_BTN_CHECKOUT_SHIPMENT");
        //keyword.webDriverWaitForElementPresent("CHECKOUT_LBL_CHECKOUT_PAYMENT",10);
    }
    public void checkOutWithVisa(String flag,String label) throws InterruptedException {
        keyword.webDriverWaitForElementPresent("CHECKOUT_CBX_CHECKOUT_VISA",10);
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click("CHECKOUT_CBX_CHECKOUT_VISA");
        Thread.sleep(3000);
        switch (flag) {
            //input valid information
            case "success":
                keyword.untilJqueryIsDone(50L);
                keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
                Thread.sleep(2000);

//                keyword.switchToIFrameByXpath("iframe");
                keyword.switchToIFrameByXpath("CHECKOUT_IFRAME_CHECKOUT_VISA");
                keyword.switchToIFrameByCss();
                logger.info("doneeee");
                keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
//                Thread.sleep(2000);
                keyword.sendKeys("CHECKOUT_TBX_CHECKOUT_VISA", "CHECKOUT_DATA_CHECKOUT_VISA");
                keyword.switchToDefaultContent();

//                keyword.switchToIFrameByXpath("CHECKOUT_IFRAME_CHECKOUT_EXPIRYDATE");
                keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
//                Thread.sleep(2000);
                keyword.sendKeys("CHECKOUT_TBX_CHECKOUT_EXPIRYDATE", "CHECKOUT_DATA_CHECKOUT_EXPIRYDATE");
//                keyword.switchToDefaultContent();

//                keyword.switchToIFrameByXpath("CHECKOUT_IFRAME_CHECKOUT_CVC");
                keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
//                Thread.sleep(2000);
                keyword.sendKeys("CHECKOUT_TBX_CHECKOUT_CVC", "CHECKOUT_DATA_CHECKOUT_CVC");
//                keyword.switchToDefaultContent();
                keyword.sendKeys("CHECKOUT_TBX_CHECKOUT_NAME", "CHECKOUT_DATA_CHECKOUT_NAME");
//                keyword.scrollDownToElement("CHECKOUT_BTN_ORDER");
                keyword.scrollToPositionByScript("window.scrollBy(0,400)");
                keyword.click("CHECKOUT_BTN_ORDER");

                keyword.untilJqueryIsDone(50L);
                keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
                if(label.equalsIgnoreCase("stage")){
                    keyword.switchToIFrameByXpath("CHECKOUT_IFRAME_CHECKOUT_VISA_COMF");
                    keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
                    keyword.sendKeys("CHECKOUT_TBX_CHECKOUT_CODE", "CHECKOUT_DATA_CHECKOUT_CODE");
                    keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
                    keyword.click("CHECKOUT_DATA_CHECKOUT_SUBMIT_COMF");
                    keyword.switchToDefaultContent();
                    keyword.untilJqueryIsDone(50L);
                    keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
                    jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
                }

                keyword.webDriverWaitForElementPresent("CHECKOUT_SUCCESSPAGE", 160);
                if(keyword.verifyElementPresent("CHECKOUT_SUCCESSPAGE")){
                    jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
                }
                break;

            //missing input all of fields
            case "emptyCardNumber":
                keyword.click("CHECKOUT_BTN_ORDER");
                keyword.assertEquals("CHECKOUT_DATA_MESSAGES_VISA_CARD_NUM", "CHECKOUT_MESSAGES_VISA");
                jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
                break;
            case "emptyExpirationDate":
                keyword.switchToIFrameByXpath("CHECKOUT_IFRAME_CHECKOUT_VISA");
                keyword.sendKeys("CHECKOUT_TBX_CHECKOUT_VISA", "CHECKOUT_DATA_CHECKOUT_VISA");
                keyword.switchToDefaultContent();
                keyword.click("CHECKOUT_BTN_ORDER");
                keyword.assertEquals("CHECKOUT_DATA_MESSAGES_VISA_CARD_EXPIRYDATE", "CHECKOUT_MESSAGES_EXPIRYDATE");
                jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
                break;
            case "emptyCVC":
                keyword.switchToIFrameByXpath("CHECKOUT_IFRAME_CHECKOUT_EXPIRYDATE");
                keyword.sendKeys("CHECKOUT_TBX_CHECKOUT_EXPIRYDATE", "CHECKOUT_DATA_CHECKOUT_EXPIRYDATE");
                keyword.switchToDefaultContent();
                keyword.click("CHECKOUT_BTN_ORDER");
                keyword.assertEquals("CHECKOUT_DATA_MESSAGES_VISA_CARD_CVC", "CHECKOUT_MESSAGES_CVC");
                jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
                break;

            //input defuse card
            case "failByCard":
                keyword.switchToIFrameByXpath("CHECKOUT_IFRAME_CHECKOUT_VISA");
                keyword.sendKeys("CHECKOUT_TBX_CHECKOUT_VISA", "CHECKOUT_DATA_CHECKOUT_VISA_2");
                keyword.switchToDefaultContent();
                keyword.switchToIFrameByXpath("CHECKOUT_IFRAME_CHECKOUT_EXPIRYDATE");
                keyword.sendKeys("CHECKOUT_TBX_CHECKOUT_EXPIRYDATE", "CHECKOUT_DATA_CHECKOUT_EXPIRYDATE_2");
                keyword.switchToDefaultContent();
                keyword.switchToIFrameByXpath("CHECKOUT_IFRAME_CHECKOUT_CVC");
                keyword.sendKeys("CHECKOUT_TBX_CHECKOUT_CVC", "CHECKOUT_DATA_CHECKOUT_CVC_2");
                keyword.switchToDefaultContent();
//                keyword.sendKeys("CHECKOUT_TBX_CHECKOUT_NAME", "CHECKOUT_DATA_CHECKOUT_NAME");
                keyword.click("CHECKOUT_BTN_ORDER");
                Thread.sleep(5000);
                keyword.verifyElementVisible("CHECKOUT_MESSAGES_VISA_2");
                jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
                break;
        }
    }
    //check out with paypal payment method
    public void checkOutWithPayPal() throws InterruptedException {
        Thread.sleep(5000);
        keyword.click("CHECKOUT_CBX_CHECKOUT_PAYPAL");
        Thread.sleep(2000);
        keyword.click("CHECKOUT_BTN_ORDER");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");

        keyword.clearText("PAYPAL_EMAIL");
        keyword.sendKeys("PAYPAL_EMAIL", "PAYPAL_DATA_EMAIL");
        keyword.sendKeys("PAYPAL_PASSWORD", "PAYPAL_DATA_PASSWORD");
        keyword.click("PAYPAL_BTN_LOGIN");
        keyword.webDriverWaitForElementPresent("PAYPAL_BTN_COMPLETE",10);
        keyword.click("PAYPAL_BTN_COMPLETE");
        keyword.webDriverWaitForElementPresent("CHECKOUT_SUCCESSPAGE", 60);
        Thread.sleep(5000);
        if(keyword.verifyElementPresent("CHECKOUT_SUCCESSPAGE")){
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
        }
    }
    public Float calculateMoney(float total, float storeCredit){
        return total - storeCredit;
    }
    //calculate the discount
    public void discount(boolean flag) throws InterruptedException {
        if (flag){
            Float rawPrice = Float.valueOf(keyword.getTextWithOutCharacters("CHECKOUT_LBL_PRICE","£"));
            logger.info(String.valueOf(rawPrice));
            String totalPrice = String.valueOf(calculateMoney(rawPrice, 1));
            logger.info("totalPrice==="+totalPrice);
            keyword.untilJqueryIsDone(50L);
            keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
            String actualPrice = keyword.getTextWithOutCharacters("CHECKOUT_LBL_TOTAL_PRICE","£");
            logger.info(actualPrice);
            String lastPrice = keyword.removeLastChar(actualPrice);
            PropertiesFile.serPropValue("CHECKOUT_TOTAL_AMOUNT",actualPrice);
            keyword.simpleAssertEquals(totalPrice, lastPrice);
        }else {
            keyword.untilJqueryIsDone(50L);
            keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
            String actualPrice = keyword.getTextWithOutCharacters("CHECKOUT_LBL_TOTAL_PRICE", "£");
            String lastPrice = keyword.removeLastChar(actualPrice);
            PropertiesFile.serPropValue("CHECKOUT_TOTAL_AMOUNT",actualPrice);
            keyword.untilJqueryIsDone(50L);
            keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
            keyword.simpleAssertEquals("0.0", lastPrice);
        }
    }
    public void applyCoupon(String couponCode, boolean flag) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        if(keyword.verifyElementVisible("CHECKOUT_BTN_SHOW_COUPON")){
            keyword.click("CHECKOUT_BTN_SHOW_COUPON");
        }
        Thread.sleep(5000);
        keyword.click("CHECKOUT_LBL_COUPON");
        keyword.sendKeys("CHECKOUT_TBX_COUPON",couponCode);
        keyword.click("CHECKOUT_BTN_COUPON");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        Thread.sleep(5000);
        discount(flag);
        if (flag){
            checkOutWithPayPal();
        }else {
            keyword.click("CHECKOUT_BTN_ORDER");
            keyword.webDriverWaitForElementPresent("CHECKOUT_SUCCESSPAGE", 20);
            keyword.verifyElementPresent("CHECKOUT_SUCCESSPAGE");
        }


    }
    //get order number when the order is completed
    public void getOrderNumber(){
        String text = keyword.getTextWithOutCharacters("CHECKOUT_ORDER_NUMBER","Your order number is ");
        logger.info("textIDorder====" + text);
        PropertiesFile.serPropValue("CHECKOUT_DATA_ORDER_NUMBER",text);
    }
    //login admin BackEnd (shared functions)
    public void loginAdmin(String userName, String passWord) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("LOGIN_FORM_USER_NAME_BACKEND", userName);
        keyword.sendKeys("LOGIN_FORM_PASSWORD_BACKEND", passWord);
        keyword.click("LOGIN_FORM_BTN_SUBMIT_BACKEND");
    }
    //open a new tab to login on admin site
    public void openNewTab() throws InterruptedException {
//        Set<String> contextView = ((AppiumDriver)driver).getContextHandles();
//        ArrayList<String> s = new ArrayList<String>(contextView);
//        ((AppiumDriver)driver).context(s.get(contextView.size()-1));
//        driver.close();
//        keyword.openNewTabFromTabBase(1,"BE_URL");
//        keyword.maximizeWindow();
//        keyword.navigateToUrl("BE_URL");
        keyword.openNewTab("BE_URL");
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        Thread.sleep(5000);
        loginAdmin("LOGIN_DATA_USER_NAME_DUNG","LOGIN_DATA_PASS_WORD_DUNG");
        Thread.sleep(5000);
    }
    //go to BE and verify order's status

    public void verifyOrderStatus(String status) throws InterruptedException {
        //go to BE
        //https://dev3.glamira.com/
        keyword.navigateToUrl("https://stage.glamira.com/secured2021/sales/order/");
        //verify status
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        Thread.sleep(7000);
        keyword.webDriverWaitForElementPresent("BE_ORDER_TBX_SEARCH",60);
        keyword.clearText("BE_ORDER_TBX_SEARCH");
        keyword.sendKeys("BE_ORDER_TBX_SEARCH","CHECKOUT_DATA_ORDER_NUMBER");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        Thread.sleep(2000);
        keyword.click("BE_ORDER_BTN_SEARCH");
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.webDriverWaitForElementPresent("BE_ORDER_GRV",60);
        Thread.sleep(5000);
        logger.info("status====expected "+ PropertiesFile.getPropValue(status));
        logger.info("actual===="+ keyword.getText("BE_ORDER_GRV_STATUS"));
        keyword.assertEquals(status,"BE_ORDER_GRV_STATUS");

        Thread.sleep(5000);
//        driver.switchTo().window(driver.handles[1]);
        Set<String> contextView = driver.getWindowHandles();
        ArrayList<String> s = new ArrayList<String>(contextView);
        driver.get(s.get(contextView.size()-1));
        driver.close();
    }
    //check invoices for an order
    public void checkInvoices() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click("BE_ORDER_GRV_STATUS");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.webDriverWaitForElementPresent("BE_ORDER_BTN_INVOICE",10);
        keyword.click("BE_ORDER_BTN_INVOICE");
        Thread.sleep(2000);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        String amount = keyword.getTextWithOutCharacters("BE_ORDER_STATUS_AMOUNT","£");
//        keyword.simpleAssertEquals("0.0",amount);

//        Thread.sleep(5000);
//        Set<String> contextView = driver.getWindowHandles();
//        ArrayList<String> s = new ArrayList<String>(contextView);
//        ((AppiumDriver)driver).context(s.get(contextView.size()-1));
//        driver.close();
    }
    //check giftcard's status of a giftcode, that this giftcode's status is used
    public void checkGiftCardStatus(String code) throws InterruptedException {
        //go to giftcard screen
        keyword.navigateToUrl("https://stage.glamira.com/secured2021/amgcard/account/");
        //check status by searching code
        keyword.webDriverWaitForElementPresent("GIFTCARD_HEADER",10);
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click("GIFTCARD_BTN_FILTER");
        keyword.webDriverWaitForElementPresent("GIFTCARD_TBX_CODE",10);
        keyword.clearText("GIFTCARD_TBX_CODE");
        keyword.sendKeys("GIFTCARD_TBX_CODE",code);
        keyword.click("GIFTCARD_BTN_APPLY");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.assertEquals("Used", "GIFTCARD_LBL_STATUS");
    }
    //apply the coupon is used
    public void applyUsedCoupon(String couponCode) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        if(keyword.verifyElementVisible("CHECKOUT_BTN_SHOW_COUPON")){
            keyword.click("CHECKOUT_BTN_SHOW_COUPON");
        }
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click("CHECKOUT_LBL_COUPON");
        keyword.sendKeys("CHECKOUT_TBX_COUPON",couponCode);
        keyword.click("CHECKOUT_BTN_COUPON");
        keyword.webDriverWaitForElementPresent("CHECKOUT_MESSAGES_COUPON_ERROR",10);
    }
    //common checkout
    public void checkOutLbl() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.click("CHECKOUT_BTN_CHECKOUT_ADDRESS");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        Thread.sleep(2000);
        keyword.click("CHECKOUT_BTN_ORDER");
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.webDriverWaitForElementPresent("CHECKOUT_SUCCESSPAGE", 20);
        keyword.verifyElementPresent("CHECKOUT_SUCCESSPAGE");
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

    }
    public void addShippingLabel(String url,Boolean flag) throws InterruptedException {
        //https://dev3.glamira.com/glgb/
        keyword.navigateToUrl(url+"catalog/product/view/id/103896");
        Thread.sleep(3000);
        keyword.click("CHECKOUT_ADDPRODUCT_BTN_ADD");
        clickShoppingBagPage();
        moveToPagecheckOut();
        if(flag){
            checkOutLbl();

        }
        else{
            checkOut();
            checkOutWithPayPal();
        }

    }
    public void notAccpectConditions() throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.click("CHECKOUT_BTN_ACCEPT_CONDITIONS");
        keyword.untilJqueryIsDone(30L);
//        keyword.webDriverWaitForElementPresent("CHECKOUT_CBX_CHECKOUT_BANK",10);
//        keyword.click("CHECKOUT_CBX_CHECKOUT_BANK");
        keyword.click("CHECKOUT_CBX_CHECKOUT_PAYPAL");
        keyword.untilJqueryIsDone(30L);
        keyword.click("CHECKOUT_BTN_ORDER");
        keyword.webDriverWaitForElementPresent("CHECKOUT_MESSAGES_ACCEPT_CONDITIONS", 60);
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

    }

    public void stepReturn() throws InterruptedException {

//        viewReturn();
        boolean check;
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        String s = keyword.getText("MAC_MY_ORD_VERIFY_RETURN_GET_ID_ORDER2");
        String id=s.substring(1,s.length());
        System.out.printf("ID : " + id +"\n");
        if(id.equalsIgnoreCase(PropertiesFile.getPropValue("KEY_ID_ORDER"))){
            logger.info("Failed by id:" + id);
            check=false;
        }
        else{
            //PropertiesFile.serPropValue("KEY_ID_ORDER",id);
            check= true;
        }

        //create new return
        if(check){
            keyword.untilJqueryIsDone(60L);
            keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
            keyword.click("MAC_MY_ORD_RETURN_STEP1_CHECKBOX1");
            keyword.keysBoardWithDOWN("MAC_MY_ORD_RETURN_STEP1_SELECT1");
            keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
            keyword.click("MAC_MY_ORD_RETURN_STEP1_CHECKBOX2");

            if(keyword.verifyElementVisible("MAC_MY_ORD_RETURN_STEP1_TEXTAREA")){
                keyword.sendKeys("MAC_MY_ORD_RETURN_STEP1_TEXTAREA","MAC_MY_ORD_RETURN_STEP1_DATA_TEXTAREA");
                keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
                keyword.sendKeys("MAC_MY_ORD_RETURN_STEP1_UPLOAD_IMG1","C:\\Users\\Thuy Dung\\Documents\\Kiến trúc và thiết kế phần mềm\\Assigntment45\\media\\images\\products\\main\\kemhop.jpg");
                keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
                keyword.sendKeys("MAC_MY_ORD_RETURN_STEP1_UPLOAD_IMG2","C:\\Users\\Thuy Dung\\Documents\\Kiến trúc và thiết kế phần mềm\\Assigntment45\\media\\images\\products\\main\\kemly.jpg");
                keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
                keyword.sendKeys("MAC_MY_ORD_RETURN_STEP1_UPLOAD_IMG3","C:\\Users\\Thuy Dung\\Documents\\Kiến trúc và thiết kế phần mềm\\Assigntment45\\media\\images\\products\\main\\kemque.jpg");

            }
            else{
                keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
                keyword.click("MAC_MY_ORD_RETURN_STEP1_SELECT2");
                keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
                keyword.doubleClick("MAC_MY_ORD_RETURN_STEP1_SELECT2_OPTION");
                keyword.untilJqueryIsDone(60L);
                keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
                keyword.doubleClick("//div[@class='column main']");
                keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
            }

//            keyword.scrollDownToElement("MAC_MY_ORD_RETURN_STEP2");
            keyword.scrollToPositionByScript("window.scrollBy(0,500)");
            keyword.doubleClick("MAC_MY_ORD_RETURN_STEP2");
            keyword.untilJqueryIsDone(60L);
            keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
//            keyword.click("MAC_MY_ORD_RETURN_STEP2_CHECKBOX");
            keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
            keyword.click("MAC_MY_ORD_RETURN_STEP3");
            keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
            keyword.click("MAC_MY_ORD_RETURN_SUBMIT");
            logger.info("done");
            keyword.untilJqueryIsDone(60L);
            keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");

            keyword.verifyElementVisible("MAC_MY_ORD_RETURN_SUCCESS");
            PropertiesFile.serPropValue("KEY_ID_ORDER",id);
        }else {
            Assert.assertTrue(false);}
    }




}
