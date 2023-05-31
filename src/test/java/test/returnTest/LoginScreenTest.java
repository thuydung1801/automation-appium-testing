package test.returnTest;

import Page.homePage.LoginPage;
import Page.returnPage.LoginScreenPage;
import core.BaseTest;
import core.KeywordWeb;
import core.LogHelper;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginScreenTest extends BaseTest {

    private LoginScreenPage loginScreen;
    private LoginPage objLogin;
    private static final Logger logger = LogHelper.getLogger();
    public LoginScreenTest(){
        super();
    }
    public void setReturnLoginScreen() throws InterruptedException {
        loginScreen = new LoginScreenPage(this.keyword);
        objLogin = new LoginPage(this.keyword);
        loginScreen.goToReturn();
        objLogin.acceptAllCookies();
    }
    public void login() throws InterruptedException {
        objLogin = new LoginPage(this.keyword);
        objLogin.chooseLanguages();
        objLogin.acceptAllCookies();
        objLogin.login("EMAIL","PASSWORD");
    }

    @Test(priority = 1, description = "Submit return form, login successfully")
    public void TestCase_LS01() throws InterruptedException {
        setReturnLoginScreen();
        loginScreen.loginOnReturnFormSuccess();
        Assert.assertTrue(loginScreen.isReTurnFormPresent(),"Return form not display ");
    }

    @Test(priority = 2, description = "Input email or order having space")
    public void TestCase_LS03() throws InterruptedException {
        setReturnLoginScreen();
        Assert.assertTrue(loginScreen.inputEmailWithSpace(),"Input email with space unsuccessfully");
    }

    @Test(priority = 3, description = "Customer valid but don't have any order")
    public void TestCase_LS05() throws InterruptedException {
        setReturnLoginScreen();
        Assert.assertTrue(loginScreen.isLoginAccWithoutReturn(),"Don't display message when input account without return order ");
    }
//    @Test(description = "Return order with order > 60 day")
//    public void TestCase_LS07() throws InterruptedException {
//        setReturnLoginScreen();
//
//    }
//    @Test(description = "Return order with the order haven't the item avaiable engraving")
//    public void TestCase_LS09() throws InterruptedException {
//        setReturnLoginScreen();
//
//    }

}
