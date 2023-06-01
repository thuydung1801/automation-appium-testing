package com.browserstack.signup;

import com.browserstack.home.LoginPage;
import core.BaseTest;
import core.LogHelper;
import org.slf4j.Logger;
import org.testng.annotations.Test;

import java.util.Date;

public class SignUpTest extends BaseTest {
    private SignUpPage signUpPage;
    private LoginPage loginPage;
    private static final Logger logger = LogHelper.getLogger();
    public SignUpTest() {
        super();
        signUpPage = new SignUpPage(this.keyword);
        loginPage = new LoginPage(this.keyword);
    }
    public void startCreateAccount() throws InterruptedException {
        loginPage.acceptAllCookies();
        signUpPage.goToSignUp();
    }

    @Test(priority = 1, description = "Create new customer successfully with store enable email confirm")
    public void testCase_NSU1() throws InterruptedException {
        String email = signUpPage.createNewEmail();
        signUpPage.signUpSuccessful("FIRST_NAME","LAST_NAME",email,email);
    }
    @Test(priority = 2, description = "Create new customer and leave with blank form for required form")
    public void testCase_NSU2() throws InterruptedException {
        startCreateAccount();
        signUpPage.signUpWithBankField(" "," "," "," ");
    }
    @Test(priority = 3, description = "Create new customer and input invalid data for email form")
    public void testCase_NSU3() throws InterruptedException {
        startCreateAccount();
        signUpPage.signUpWithBankField("FIRST_NAME","LAST_NAME","abc"," ");
    }
    @Test(priority = 4, description = "Create new customer and input invalid data for email form")
    public void testCase_NSU4() throws InterruptedException {
        startCreateAccount();
        signUpPage.signUpWithBankField("FIRST_NAME","LAST_NAME","EMAIL1","EMAIL2");
    }
}
