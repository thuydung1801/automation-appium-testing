package com.browserstack.myAccount;

import com.browserstack.common.LoginPage;
import org.slf4j.Logger;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import static core.BaseTest.driver;
import static core.BaseTest.jse;
import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import core.PropertiesFile;
import static core.BaseTest.driver;
import static core.BaseTest.jse;
public class MyAccountPage extends BasePage{
    private static Logger logger = LogHelper.getLogger();
    private LoginPage objLogin ;
    public MyAccountPage() {
        super();
    }

    public MyAccountPage(KeywordWeb key) {
        super(key);
        objLogin = new LoginPage();

    }
    public void inpFullName(String firstName, String lastName) throws InterruptedException {
        keyword.untilJqueryIsDone(60L);
        keyword.waitForElementNotVisible(60,"//div[@class='loading-mask']");
        Thread.sleep(5000);
        boolean check;
        keyword.clearText("MAC_INP_FIRST_NAME");
        keyword.sendKeys("MAC_INP_FIRST_NAME",firstName);
        keyword.clearText("MAC_INP_LAST_NAME");
        keyword.sendKeys("MAC_INP_LAST_NAME",lastName);

        keyword.click("MAC_BTN_SAVE_1");
        Thread.sleep(2000);
        keyword.untilJqueryIsDone(60L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
    }
    public void checkVerifyChangeSuccess(String element, String message, String change, String eleChange, String verify) throws InterruptedException {

        if(keyword.verifyElementVisible(element)){
            boolean test ;
            keyword.assertEquals(message,element);
            keyword.untilJqueryIsDone(60L);
            keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
            switch (change){
                case "name" :
                    String firstName = PropertiesFile.getPropValue("MAC_DATA_FIRST_NAME_STAGE");
                    String lastName = PropertiesFile.getPropValue("MAC_DATA_LAST_NAME_STAGE");
                    String name = firstName +" "+ lastName;
                    String text = keyword.getText(eleChange);
                    if(text.contains(name)){
                        test = true;
                    }
                    else {
                        test = false;
                    }
                    logger.info("check change name....");
                    Assert.assertEquals(test,true);
                    jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

                    break;
                case "email":
                    keyword.scrollToPositionByScript("window.scrollBy(0,500)");
                    String textEle = keyword.getText(eleChange);
                    if(textEle.contains(PropertiesFile.getPropValue(verify))){
                        test = true;
                    }
                    else {
                        test = false;
                    }
                    logger.info("check change email....");
                    Assert.assertEquals(test,true);
                    jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

                    break;
                case "pass":
//                    logOutLogIn();
                    break;
                case "null":
                    logger.info("NULL....");
                    break;
                default:
                    logger.info("NULL....");
                    break;
            }
        } else {
            logger.info("Erorr....");
        }
    }
    public void changeFullnameWithData() throws InterruptedException {
        inpFullName("MAC_DATA_FIRST_NAME_STAGE","MAC_DATA_LAST_NAME_STAGE");
        checkVerifyChangeSuccess("CUS_VERIFY_NEWSLETTER_UNSUBSCRIBE","MAC_VERIFY_DATA_FULLNAME","name","MAC_VERIFY_NAME",null);

    }
    public void commonPersonalInf(String checkBox) throws InterruptedException {
        keyword.untilJqueryIsDone(70L);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        keyword.click("MAC_PERSONAL_INF_MOBILE");
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        if (checkBox != null) {
            keyword.click(checkBox);
        }
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
    }
    public void checkVerifyInputNull(){
        keyword.waitForElementNotVisible(60, "//div[@class='loading-mask']");
        if(keyword.verifyElementVisible("COM_TEXT_ERROR")){
            keyword.assertEquals("COM_DATA_MESSAGES_NULL",
                    "COM_TEXT_ERROR");
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");

        }
    }
    public void changeFullNameWithDataNUll() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(60, "//div[@class='loading-mask']");
        keyword.click("BTN_MYACCOUNT_ON_MOBILE");
        commonPersonalInf(null);
        inpFullName("COM_DATA_NULL", "COM_DATA_NULL");
        checkVerifyInputNull();
    }
    public void inputChangeMail() throws InterruptedException {
//        commonPersonalInfOnMobile("MAC_CLICK_CHECKBOX_EMAIL");
        keyword.click("MAC_CLICK_CHECKBOX_EMAIL");
        String timestamp = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String mail = "dung"+timestamp+"@gmail.com";
//        PropertiesFile.serPropValue("COM_INP_DATA_EMAIL_MOBILE", mail);
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("MAC_INP_EMAIL_NEW", mail);
        keyword.sendKeys("MAC_INP_PASS_CURENT_1", "COM_INP_DATA_PASS_STAGE");
        keyword.click("MAC_BTN_SAVE_2");
        keyword.untilJqueryIsDone(60L);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
//        PropertiesFile.serPropValue("COM_INP_DATA_EMAIL_STAGE",mail);
        Thread.sleep(2000);
        keyword.untilJqueryIsDone(60L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        PropertiesFile.serPropValue("COM_INP_DATA_EMAIL_STAGE",mail);
    }
    public void changeEmail() throws InterruptedException {
        inputChangeMail();
        checkVerifyChangeSuccess("CUS_VERIFY_NEWSLETTER_UNSUBSCRIBE", "MAC_VERIFY_DATA_FULLNAME", "email", "MAC_VERIFY_EMAIL_CHANGE", "COM_INP_DATA_EMAIL_STAGE");

    }

}
