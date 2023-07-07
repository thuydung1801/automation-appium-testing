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
    public void clickChooseSize(String size) throws InterruptedException {
        keyword.click("PRODUCT_DETAIL_DRD_OPTION_SIZE");
        keyword.untilJqueryIsDone(30L);
        keyword.randomElement(size);
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
    public void selectRingSize(String size) throws InterruptedException {
        keyword.reLoadPage();
        keyword.untilJqueryIsDone(30L);
        keyword.scrollToPositionByScript("window.scrollBy(0,500)");
        if(size!=null) {
            clickChooseSize(size);
            keyword.untilJqueryIsDone(30L);
            checkVerifySizeRing();
            keyword.click("PRODUCT_DETAIL_BTN_ADD_CART");
        }
        else {
            keyword.click("PRODUCT_DETAIL_BTN_ADD_CART");
            keyword.untilJqueryIsDone(30L);
            keyword.click("PRODUCT_DETAIL_BTN_CLOSE_POPUP_SIZE");
            keyword.untilJqueryIsDone(30L);
            checkVerifyInputNull();
        }
    }
    public void checkVerifyInputNull(){
        keyword.assertEquals("COM_DATA_MESS_NULL", "COM_TXT_MESS_ERROR");
    }
    public void orderAFreeRingSize(String code,String address,String city ,boolean clickRecaptcha) throws InterruptedException {
        commonFindSize();
        inputFindSize( code,address,city,clickRecaptcha);
        if(address==null || address==null || city==null || !clickRecaptcha ) {
            checkVerifyInputNull();
        }
    }
    public void optionDimensionGuide() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.navigateToUrl("URL_PRODUCT_DETAIL");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        optionSize();
        keyword.click("PRODUCT_DETAIL_FIND_SIZE");
        keyword.imWait(2);
        keyword.click("PRODUCT_DETAIL_LINK_SIZEGUIDE");
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.untilJqueryIsDone(30L);
        keyword.verifyElementVisible("PRODUCT_DETAIL_VERIFY_LINK");
        keyword.untilJqueryIsDone(30L);
    }
    public void inputFindSize(String code,String address, String city, boolean clickRecaptcha) throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        Thread.sleep(3000);
        checkInputField("PRODUCT_DETAIL_INP_ADDRESS",address);
        checkInputField("PRODUCT_DETAIL_INP_CITY", city);
        checkInputField("PRODUCT_DETAIL_INP_CODE",code);
        if(clickRecaptcha==true) {
            keyword.recaptchaClick();
        }
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.untilJqueryIsDone(30L);
        keyword.scrollToPositionByScript("window.scrollBy(0,200)");
        keyword.click("PRODUCT_DETAIL_BTN_SUBMIT");
    }
    public void checkInputField( String field,String content) {
        if(content!=null) {
            keyword.clearText(field);
            keyword.sendKeys(field, content);
        }
    }
}


