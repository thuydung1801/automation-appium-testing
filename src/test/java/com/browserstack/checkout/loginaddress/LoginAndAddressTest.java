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
    @Test
//            (priority = 1, description = "Login customer with Email not exist or Password invalid from checkout page")
    public void testCase_LA_02() throws InterruptedException {
        logger.info("testCase_LA_02");
        objLogin.acceptAllCookies();
        objLoginAdrPage.loginCustomerWithEmailNotExistPassword();
    }
//    @Test(priority = 2, description = "Input invalid email on the Login and Address tab")
    public void testCase_LA_03() throws InterruptedException {
        logger.info("testCase_LA_03");
        objLoginAdrPage.invalidEmailOnTheLogin();
    }
//    @Test(priority = 3, description = "Leave blank Email and Password")
    public void testCase_LA_04() throws InterruptedException {
        logger.info("testCase_LA_04");
        objLoginAdrPage.leaveBlankEmailAndPassword();
    }
//    @Test(priority = 4, description = "Check link forgot password")
    public void testCase_LA_05() throws InterruptedException {
        logger.info("testCase_LA_05");
        objLoginAdrPage.checkLinkForgotPassword();
    }
//    @Test(priority = 5, description = "Next to Payment page successfully with Guest option and Ship to this address is yes")
    public void testCase_LA_08() throws InterruptedException {
        logger.info("testCase_LA_08");
        objLoginAdrPage.nextToPaymentPageWithAddress();
    }
//    @Test(priority = 6, description = "Input Email/Email Confirm form invalid data")
    public void testCase_LA_09() throws InterruptedException {
        logger.info("testCase_LA_09");
        objLoginAdrPage.emailInvalid();
    }
    //pendding fix issue copy paste
//    @Test(priority = 7, description = "Using copy/paste email for Email confirm form")
    public void testCase_LA_10() throws InterruptedException {
        logger.info("testCase_LA_010");
        objLoginAdrPage.checkNotCopyPasteEmail();
    }
    @Test
//            (priority = 8, description = "Next to Payment page with leave blank with the required form")
    public void testCase_LA_11() throws InterruptedException {
        logger.info("testCase_LA_011");
        objLoginAdrPage.leaveBlankRequiredForm();

    }
    @Test
//            (priority = 9, description = "Input invalid phone on the Login and Address tab")
    public void testCase_LA_12() throws InterruptedException {
        logger.info("testCase_LA_012");
        objLoginAdrPage.enterInvalidPhone();
    }

    @Test
//            (priority = 10, description = "Next to Payment page successfully with Guest option and Ship to this address is yes")
    public void testCase_LA_06() throws InterruptedException {
        logger.info("testCase_LA_06");
        objLoginAdrPage.nextToPaymentSuccess();
    }





}
