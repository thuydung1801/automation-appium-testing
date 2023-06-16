package com.browserstack.myAccount;

import com.browserstack.checkout.loginaddress.LoginAddressPage;
import com.browserstack.checkout.shoppingbag.ShoppingBagPage;
import com.browserstack.common.LoginPage;
import core.BaseTest;
import core.LogHelper;
import org.slf4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MyAccountTest extends BaseTest {
    private static Logger logger = LogHelper.getLogger();
    private LoginPage objLogin ;
    private MyAccountPage testMyAccount;

    public MyAccountTest() {
        super();
        objLogin = new LoginPage(this.keyword);
        testMyAccount = new MyAccountPage(this.keyword);
    }
    public void commonMyAccount() throws InterruptedException {
        objLogin.acceptAllCookies();
        objLogin.login("COM_INP_DATA_EMAIL_STAGE","COM_INP_DATA_PASS_STAGE");
        keyword.untilJqueryIsDone(30L);
        keyword.navigateToUrl("https://stage.glamira.co.uk/customer/account/edit/");
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");

    }
    @Test(priority = 1, description = "Change PERSONAL INFORMATION ")
//    @Parameters("baseURL")
    public void testCase_PI_01() throws InterruptedException {
        commonMyAccount();
        testMyAccount.changeFullnameWithData();
    }
    @Test(priority = 2, description = "Change PERSONAL INFORMATION : forgot enter value First/last name  ")
    public void testCase_PI_02() throws InterruptedException {
        commonMyAccount();
        testMyAccount.changeFullNameWithDataNUll();
    }

    @Test(priority = 3, description = "Change email ")
    public void testCase_PI_03() throws InterruptedException {
        testMyAccount.changeEmail();
    }
    @Test(priority = 4,description = "CHANGE PASSWORD")
    public void testCase_PI_04_LO_01() throws InterruptedException {
        commonMyAccount();
        testMyAccount.changePassword();
    }


}
