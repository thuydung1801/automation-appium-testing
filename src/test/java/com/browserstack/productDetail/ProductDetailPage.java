package com.browserstack.productDetail;

import com.browserstack.common.LoginPage;
import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import core.PropertiesFile;
import org.slf4j.Logger;
import org.testng.Assert;

import java.util.ArrayList;


public class ProductDetailPage extends BasePage {
    private static Logger logger = LogHelper.getLogger();
    private LoginPage objLogin;

    public ProductDetailPage(KeywordWeb key) {
        super(key);
        objLogin = new LoginPage();
    }
    public void setUp() throws InterruptedException {
        keyword.untilJqueryIsDone(60L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        objLogin.acceptAllCookies();
        objLogin.login("PRODUCT_DETAIL_DATA_EMAIL","RETURN_FORM_DATA_PASSWORD");
    }

    public void clickChooseSize() throws InterruptedException {
        keyword.click("PRODUCT_DETAIL_DRD_OPTION_SIZE");
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.untilJqueryIsDone(30L);
        keyword.randomElement("PRODUCT_DETAIL_CLICK_OPTION_SIZE");
    }
    public void optionSize() throws InterruptedException {
        keyword.scrollToPositionByScript("window.scrollBy(0,500)");
        keyword.click("PRODUCT_DETAIL_DRD_OPTION_SIZE");
        keyword.imWait(2);
        keyword.click("PRODUCT_DETAIL_OPTION_SIZE");
    }
    public void commonFindSize() throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.navigateToUrl("URL_PRODUCT_DETAIL");
        keyword.untilJqueryIsDone(30L);
        optionSize();
        keyword.webDriverWaitForElementPresent("PRODUCT_DETAIL_REQUEST_FREE_SIZE",20);
        keyword.click("PRODUCT_DETAIL_REQUEST_FREE_SIZE");
    }
    public void checkVerifySizeRing() throws InterruptedException {
        String exp = keyword.getText("PRODUCT_DETAIL_DRD_OPTION_SIZE");
        String expected = exp.substring(0,2);
        String act = keyword.getText("PRODUCT_DETAIL_CHECK_SIZE");
        String actual = act.substring(0,2);
        System.out.printf("expect : " +expected + "\n" + "actual : " +actual+"\n");
        Assert.assertEquals(expected, actual);
        Thread.sleep(1000);
    }
    public void ringSize1() throws InterruptedException {
        keyword.reLoadPage();
        clickChooseSize();
        keyword.untilJqueryIsDone(30L);
        checkVerifySizeRing();
        keyword.click("PRD_BTN_ADDCART");

    }
    public void ringSize2() throws InterruptedException {
        keyword.reLoadPage();
        keyword.untilJqueryIsDone(30L);
        keyword.click("PRD_BTN_ADDCART");
        keyword.click("PRD_BTN_CLOSE_POPUP_SIZE");
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");

        checkVerifyInputNull();
    }
    public void ringSize3() throws InterruptedException {
        keyword.click("PRD_DROPDOWN");
        keyword.untilJqueryIsDone(30L);
        keyword.click("PRD_OPTION_SIZE_AVERAGE");
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");

        checkVerifySizeRing();
        keyword.click("PRD_BTN_ADDCART");
    }
    public void optionFindSizeWithInpSuccess() throws InterruptedException {
        commonFindSize();
        inputFindSize( Integer.parseInt(PropertiesFile.getPropValue("PRODUCT_DETAIL_DATA_CODE")),
                "PRODUCT_DETAIL_DATA_CITY");

    }
    public void optionFindSizeWithInpNull() throws InterruptedException{
        commonFindSize();
//        keyword.openNewTab(PropertiesFile.getPropValue("URL_PRODUCT_DETAIL"));
        inputFindSize( Integer.parseInt((PropertiesFile.getPropValue("PRD_DATA_CODE"))),
                "COM_DATA_NULL");
        checkVerifyInputNull();
    }
    public void optionFindSizeWithInpEmailError() throws InterruptedException{
        commonFindSize();
        inputFindSize( Integer.parseInt(PropertiesFile.getPropValue("PRD_DATA_CODE")),
                "PRD_DATA_CITY");
        checkVerifyInputWithEmailError();
    }
    public void optionDimensionGuide() throws InterruptedException {
        setUp();
        keyword.untilJqueryIsDone(50L);
        keyword.navigateToUrl("URL_PRODUCT_DETAIL");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        objLogin.acceptAllCookies();
        optionSize();
        keyword.click("PRODUCT_DETAIL_FIND_SIZE");
        keyword.imWait(2);
        keyword.click("PRODUCT_DETAIL_LINK_SIZEGUIDE");
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.untilJqueryIsDone(30L);
        keyword.verifyElementVisible("PRODUCT_DETAIL_VERIFY_LINK");
        keyword.untilJqueryIsDone(30L);
    }
    public void checkVerifyInputNull(){
        keyword.assertEquals("COM_DATA_MESSAGES_NULL",
                "COM_TEXT_ERROR");
    }
    public void checkVerifyInputWithEmailError(){
        keyword.assertEquals("COM_DATA_MESSAGES_EMAIL",
                "COM_TEXT_ERROR_EMAIL");
    }
    public void inputFindSize(int code, String city) throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        Thread.sleep(3000);
        keyword.sendKeys("PRODUCT_DETAIL_INP_CITY", city);
        keyword.sendKeys("PRODUCT_DETAIL_INP_CODE",String.valueOf(code) );
        keyword.switchToIFrameByXpath("PRODUCT_DETAIL_IFRAME_RECAPTCHA");
        keyword.click("PRODUCT_DETAIL_CHECKBOX_RECAPTCHA");
        keyword.switchToDefaultContent();

//        keyword.recaptchaClick();
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.untilJqueryIsDone(30L);

//        keyword.webDriverWaitForElementPresent(PropertiesFile.getPropValue("PRD_CHECK_VERYFI"), 10);
//        if(keyword.verifyElementVisible(PropertiesFile.getPropValue("PRD_CHECK_VERYFI"))){

        keyword.click("PRD_CHECK");
        keyword.click("PRD_BTN_SUBMIT");
    }
}


