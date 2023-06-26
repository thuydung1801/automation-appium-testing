package com.browserstack.checkout.loginaddress;

import com.browserstack.common.LoginPage;
import core.BaseTest;
import core.LogHelper;
import org.slf4j.Logger;
import org.testng.annotations.Test;

public class LoginAndAddressTest extends BaseTest {
    private static Logger logger = LogHelper.getLogger();
    private  LoginAndAddressPage objLoginAdrPage;
    private LoginPage objLogin ;
    public LoginAndAddressTest() {
        super();
        objLoginAdrPage = new LoginAndAddressPage(this.keyword);
        objLogin = new LoginPage(this.keyword);
    }
    @Test(priority = 1, description = "Login customer with Email not exist or Password invalid from checkout page")
    public void testCase_LA_02() throws InterruptedException {
        logger.info("testCase_LA_02");
        objLogin.acceptAllCookies();
        objLoginAdrPage.loginCustomerWithEmailNotExistPassword();
    }
    @Test(priority = 2, description = "Input invalid email on the Login and Address tab")
    public void testCase_LA_03() throws InterruptedException {
        logger.info("testCase_LA_03");
        objLoginAdrPage.invalidEmailOnTheLogin();
    }
    @Test(priority = 3, description = "Leave blank Email and Password")
    public void testCase_LA_04() throws InterruptedException {
        logger.info("testCase_LA_04");
        objLoginAdrPage.leaveBlankEmailAndPassword();
    }




}
