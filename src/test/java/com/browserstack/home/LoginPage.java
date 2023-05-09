package com.browserstack.home;

import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import core.PropertiesFile;
import org.slf4j.Logger;

public class LoginPage extends BasePage {
    private static Logger logger = LogHelper.getLogger();
    public LoginPage(){ super(); }
    public LoginPage(KeywordWeb key){
        super(key);
    }


    public void loginOnAlert(){
        keyword.handleLoginPopup(PropertiesFile.getPropValue("LOGIN_DATA_ALERT_USERNAME"),
                PropertiesFile.getPropValue("LOGIN_DATA_ALERT_PASSWORD"),
                PropertiesFile.getPropValue("LOGIN_AUTHEN_URL"));
    }

    public void loginOnWebsite(String email, String password, String phone,
                               String phonePassword, boolean byEmail) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.untilJqueryIsDone(50L);
        keyword.click("LOGIN_BTN_LOGIN");
        keyword.webDriverWaitForElementPresent("LOGIN_LBL_LOGIN",30);
        Thread.sleep(2000);
        boolean flag = true;
        if(keyword.verifyElementPresent("LOGIN_TXT_EMAIL_2")) {
            flag = false;
            if (byEmail) {
                keyword.sendKeys("LOGIN_TXT_EMAIL_2", email);
                Thread.sleep(2000);
                keyword.sendKeys("LOGIN_TXT_PASSWORD", password);
                Thread.sleep(2000);
            }else {
                keyword.sendKeys("LOGIN_TBX_PHONE_2", phone);
                Thread.sleep(2000);
                keyword.sendKeys("LOGIN_TXT_PASSWORD", phonePassword);
                Thread.sleep(2000);
            }
        }
        if(keyword.verifyElementPresent("LOGIN_TXT_EMAIL")){
            keyword.sendKeys("LOGIN_TXT_EMAIL", email);
            Thread.sleep(2000);
            keyword.sendKeys("LOGIN_TXT_PASSWORD", password);
            Thread.sleep(2000);
        }
        if(keyword.verifyElementPresent("LOGIN_TBX_PHONE") && flag){
            keyword.sendKeys("LOGIN_TBX_PHONE", phone);
            Thread.sleep(2000);
            keyword.sendKeys("LOGIN_TXT_PASSWORD", phonePassword);
            Thread.sleep(2000);
        }
        keyword.click("LOGIN_BTN_SUBMITLOGIN");
        keyword.untilJqueryIsDone(50L);

    }
    public void loginOnMobile(String email, String password) throws InterruptedException {
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
}
