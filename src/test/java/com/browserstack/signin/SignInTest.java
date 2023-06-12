package com.browserstack.signin;

import com.browserstack.home.LoginPage;
import com.browserstack.signup.SignUpPage;
import core.BaseTest;
import core.LogHelper;
import org.slf4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest {

    private SignInPage signInPage;
    private LoginPage loginPage;
    private static final Logger logger = LogHelper.getLogger();
    public SignInTest() {
        super();
        signInPage = new SignInPage(this.keyword);
        loginPage = new LoginPage(this.keyword);
    }
    public void startLogin(String baseUrl) throws InterruptedException {
        if(baseUrl.equals("https://stage.glamira.com/")){
            keyword.navigateToUrl(baseUrl);
        }
        loginPage.acceptAllCookies();
        keyword.untilJqueryIsDone(50L);
        keyword.click("LOGIN_MENU_LEFT");
        keyword.untilJqueryIsDone(50L);
        keyword.click("MOBILE_BTN_LOGIN");
        keyword.untilJqueryIsDone(50L);
    }
    @Test(priority = 7, description = "Login success with email")
    public void testCase_SNI_1() throws InterruptedException {
        //startLogin(" ");
        signInPage.isSignInSuccessOrFail("successfullyWithEmail","email");
    }
    @Test(priority = 1, description = "Login with email full blank fields")
    public void testCase_SNI_2() throws InterruptedException {
        startLogin(" ");
        signInPage.isSignInSuccessOrFail("fullBlankField","email");
    }
    @Test(priority = 2, description = "Login with email full blank password")
    public void testCase_SNI_3() throws InterruptedException {
        //startLogin(" ");
        signInPage.isSignInSuccessOrFail("blankPassWord","email");
    }
    @Test(priority = 3, description = "Login with email blank")
    public void testCase_SNI_4() throws InterruptedException {
        //startLogin(" ");
        signInPage.isSignInSuccessOrFail("blankEmail","email");
    }
    @Test(priority = 4, description = "Login wrong email - password")
    public void testCase_SNI_5() throws InterruptedException {
        //startLogin(" ");
        signInPage.isSignInSuccessOrFail("wrongEmailPassword","email");
    }
    @Test(priority = 5, description = "Login with wrong password")
    public void testCase_SNI_6() throws InterruptedException {
        //startLogin(" ");
        signInPage.isSignInSuccessOrFail("wrongPassWord","email");
    }
    @Test(priority = 6, description = "Enter the wrong email format")
    public void testCase_SNI_7() throws InterruptedException {
        //startLogin(" ");
        signInPage.isSignInSuccessOrFail("wrongFormat","email");
    }
    @Test(priority = 12, description = "Login success with phone number")
    @Parameters("baseUrl")
    public void testCase_SNI_8(String baseUrl) throws InterruptedException {
        //startLogin(baseUrl);
        signInPage.isSignInSuccessOrFail("successfullyWitPhoneNumber","phone");
    }

    @Test(priority = 8, description = "Login with invalid phone number")
    @Parameters("baseUrl")
    public void testCase_SNI_9(String baseUrl) throws InterruptedException {
        startLogin(baseUrl);
        signInPage.isSignInSuccessOrFail("invalidPhone","phone");
    }
    @Test(priority = 9, description = "Login with invalid phone number")
    @Parameters("baseUrl")
    public void testCase_SNI_10(String baseUrl) throws InterruptedException {
        //startLogin(baseUrl);
        signInPage.isSignInSuccessOrFail("wrongPhone","phone");
    }
    @Test(priority = 10, description = "Login with invalid phone number")
    @Parameters("baseUrl")
    public void testCase_SNI_11(String baseUrl) throws InterruptedException {
        //startLogin(baseUrl);
        signInPage.isSignInSuccessOrFail("wrongPassWord","phone");
    }
    @Test(priority = 11, description = "Login with invalid phone number")
    @Parameters("baseUrl")
    public void testCase_SNI_12(String baseUrl) throws InterruptedException {
       // startLogin(baseUrl);
        signInPage.isSignInSuccessOrFail("fullBlankField","phone");
    }
    @Test(priority = 12, description = "Forgot password success with email")
    @Parameters("baseUrl")
    public void testCase_SNI_13(String baseUrl) throws InterruptedException {
        startLogin(baseUrl);
        signInPage.forgotPassWord("email","success");
    }

}
