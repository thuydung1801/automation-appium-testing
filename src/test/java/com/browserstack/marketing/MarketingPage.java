package com.browserstack.marketing;

import com.browserstack.common.LoginPage;
import core.BasePage;
import core.KeywordWeb;

import static core.BaseTest.jse;

public class MarketingPage extends BasePage {
    private LoginPage loginPage;
    public MarketingPage(){
        super();
    }
    public MarketingPage(KeywordWeb key){
        super(key);
        loginPage = new LoginPage(this.keyword);
    }
    public void ipDataGiftCard(String name, String email,String mess, String submit) throws InterruptedException {
        keyword.imWait(5);
        keyword.webDriverWaitForElementPresent("MRT_INP_NAME",8);
        keyword.sendKeys("MRT_INP_NAME", name);
        keyword.sendKeys("MRT_INP_EMAIL",email);
        keyword.sendKeys("MRT_INP_MESSAGE",mess);
        Thread.sleep(20000);
//        keyword.untilJqueryIsDone(30L);
//        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click(submit);

    }
    public void checkVerifyInputNull(){
        keyword.assertEquals("COM_DATA_MESSAGES_NULL",
                "COM_TEXT_ERROR");
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

    }
    public void checkVerifyInput(String expect, String actual){
        keyword.assertEquals("COM_DATA_MESSAGES_NULL",
                "COM_TEXT_ERROR");
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

    }
    public void giftCardInpWithNull() throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.navigateToUrl("https://stage.glamira.co.uk/gift-card/");
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        ipDataGiftCard("COM_DATA_NULL","COM_DATA_NULL","COM_DATA_NULL","MRT_SUBMIT");
        checkVerifyInputNull();

    }
    public void checkVerifyInputWithEmailAndAmountError() throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");

//        keyword.assertEquals("COM_DATA_MESSAGES_NUMBER",
//                "COM_TEXT_ERROR_NUMBER");
//        keyword.untilJqueryIsDone(30L);
//        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");

        keyword.assertEquals("COM_DATA_MESSAGES_EMAIL",
                "COM_TEXT_ERROR");
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");


    }
    public void giftCardInpWithEmailAndAmountError() throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");

        keyword.sendKeys("MRT_INP_AMOUNT", "MRT_INP_DATA_AMOUNT_ERROR");
        ipDataGiftCard("LA_DATA_LAST_NAME","COM_DATA_ERROR","COM_INP_DATA_MESSAGE","MRT_SUBMIT");
        checkVerifyInputWithEmailAndAmountError();

    }
    public void giftCardWithAmount() throws InterruptedException {
        Thread.sleep(5000);
        keyword.click( "MRT_AMOUNT");
        ipDataGiftCard("LA_DATA_LAST_NAME","COM_INP_DATA_EMAIL","COM_INP_DATA_MESSAGE","MRT_SUBMIT");

    }
    public void giftCardWithOtherAmount() throws InterruptedException {
//        keyword.openNewTab("MRT_URL");
        keyword.reLoadPage();
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        Thread.sleep(3000);
        keyword.sendKeys("MRT_INP_AMOUNT", "MRT_INP_DATA_AMOUNT");
        ipDataGiftCard("LA_DATA_LAST_NAME","COM_INP_DATA_EMAIL","COM_INP_DATA_MESSAGE","MRT_SUBMIT");

    }
    public void commonGirftCetificate() throws InterruptedException {

        keyword.click("CGE_CLICK_ORDER");
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.click("CGE_CLICK_ORDER_VIEW");
        keyword.untilJqueryIsDone(30L);
        keyword.scrollDownToElement("GCE_LINK_CREATE_GIRFT");
        keyword.imWait(2);
        keyword.click("GCE_LINK_CREATE_GIRFT");
        keyword.imWait(2);
    }
    public void ipDataGirftCetificate(String name, String email,String title,String message,String option, String submit) throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");

        keyword.sendKeys("GCE_INP_NAME", name);
        keyword.sendKeys("GCE_INP_MAIL", email);
        keyword.sendKeys("GCE_INP_TITLE", title);
        keyword.sendKeys("GCE_INP_MESSAGE", message);
        if(option != null) {
            keyword.click(option);
        }
        keyword.click(submit);

    }
    public void verifyGCEsuccess() throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.verifyElementVisible("(//div[@id='modal-content-0'])[1]");
    }
    public void createNewGriftCetificateFormMyOrder() throws InterruptedException {
       Thread.sleep(5000);
        loginPage.login("COM_INP_DATA_EMAIL_STAGE", "COM_INP_DATA_PASS_STAGE");
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
//        keyword.webDriverWaitForElementPresent("GCE_BTN_ACCOUNT",10);
//        keyword.click("GCE_BTN_ACCOUNT");
//        keyword.imWait(3);
        commonGirftCetificate();
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        ipDataGirftCetificate("LA_DATA_LAST_NAME","COM_INP_DATA_EMAIL","COM_DATA_TITLE","COM_INP_DATA_MESSAGE",null,"GCE_BTN_SUBMIT");
        verifyGCEsuccess();
    }
    public void createNewCetificateWithOptionNO() throws InterruptedException {
        Thread.sleep(3000);
        keyword.reLoadPage();
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        ipDataGirftCetificate("LA_DATA_LAST_NAME","COM_INP_DATA_EMAIL","COM_DATA_TITLE","COM_INP_DATA_MESSAGE","GCE_OPTION_NO","GCE_BTN_DOWNLOAD");

    }
    public void createNewCetificateWithOptionYES() throws InterruptedException {
        keyword.reLoadPage();
        ipDataGirftCetificate("LA_DATA_LAST_NAME","COM_INP_DATA_EMAIL","COM_DATA_TITLE","COM_INP_DATA_MESSAGE",null,"GCE_BTN_DOWNLOAD");

    }
    //go to section customer after login admin BE.
    public void chooseItemCustomer(String scrollToElement, String clickItem, String verifyItem, String clickItemSub, String verifyItemSub) throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.scrollToPosition();
        keyword.scrollDownToElement(scrollToElement);
        keyword.click(clickItem);
        keyword.webDriverWaitForElementPresent(verifyItem, 20);
        keyword.untilJqueryIsDone(50L);
        keyword.untilJqueryIsDone(50L);
        keyword.click(clickItemSub);
        keyword.webDriverWaitForElementPresent(verifyItemSub, 20);
    }
    public void createNewGriftCetificateFormEmailWithLogin() throws InterruptedException {
//        setUp();
//        loginPage.loginOnWebsite("COM_INP_DATA_EMAIL", "COM_INP_DATA_PASS",null,null,true);
//        keyword.untilJqueryIsDone(30L);
//        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");

//        keyword.click("GCE_BTN_ACCOUNT");
//        keyword.imWait(5);
//
        keyword.navigateToUrl("https://stage.glamira.co.uk/customer/account/index/");
//        keyword.back();
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");

        String getIdOrder = keyword.getText("GCE_TEXT_ORDER");
        String text = getIdOrder.substring(1,getIdOrder.length());
        keyword.navigateToUrl("BE_URL");
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");

        loginPage.loginAdmin("LOGIN_DATA_USER_NAME_DUNG", "LOGIN_DATA_PASS_WORD_DUNG");
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");

        chooseItemCustomer("LOGIN_BTN_CUSTOMER","LOGIN_BTN_CUSTOMER",
                "SIGNUP_VERIFY_CUSTOMER","LOGIN_BTN_EMAIL_LOG",
                "SIGNUP_VERIFY_EMAIL_LOG");
        adminChooseEmailLog(text);
        ipDataGirftCetificate("LA_DATA_LAST_NAME","COM_INP_DATA_EMAIL","COM_DATA_TITLE","COM_INP_DATA_MESSAGE",null,"GCE_BTN_SUBMIT");
        keyword.untilJqueryIsDone(30L);
    }
    public void adminChooseEmailLog(String text) throws InterruptedException {

//        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.untilJqueryIsDone(30L);
        keyword.webDriverWaitForElementPresent("ADMIN_CUS_EMAILLOG_BTN_FILTER",20);
        Thread.sleep(2000);
        keyword.click("ADMIN_CUS_EMAILLOG_BTN_FILTER");

        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");

        keyword.clearText("ADMIN_CUS_EMAILLOG_FILTER_INP_SUBJECT");
        keyword.sendKeys("ADMIN_CUS_EMAILLOG_FILTER_INP_SUBJECT",text);

        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");

        keyword.click("ADMIN_CUS_EMAILLOG_ENTER_FILTER");
        keyword.untilJqueryIsDone(30L);
        keyword.click("ADMIN_CUS_EMAILLOG_ACTION_SELECT");
        keyword.click("ADMIN_CUS_EMAILLOG_ACTION_SELECT_VIEW");

        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.switchToIFrameByXpath("//iframe");
        keyword.scrollDownToElement("ADMIN_CUS_EMAILLOG_ACTION_SELECT_LINK_CREATE");

        keyword.checkStatusIsDisplay("ADMIN_CUS_EMAILLOG_ACTION_SELECT_LINK_CREATE");
        System.out.printf("===========");
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");

//        keyword.scrollDownToElement("ADMIN_CUS_EMAILLOG_ACTION_SELECT_LINK_CREATE");
        keyword.click("ADMIN_CUS_EMAILLOG_ACTION_SELECT_LINK_CREATE");
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
    }
    public void createNewGriftCetificateWithDataNull() throws InterruptedException {
//        commonGirftCetificate();
        keyword.reLoadPage();
        ipDataGirftCetificate("COM_DATA_NULL", "COM_DATA_NULL","COM_DATA_NULL","COM_DATA_NULL",null,"GCE_BTN_SUBMIT");
        checkVerifyInputNull();
    }

}
