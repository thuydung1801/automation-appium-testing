package com.browserstack.signup;

import com.browserstack.home.LoginPage;
import core.BaseTest;
import core.LogHelper;
import org.slf4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Date;

public class SignUpTest extends BaseTest {
    private SignUpPage signUpPage;
    private LoginPage loginPage;
    private static final Logger logger = LogHelper.getLogger();
    public SignUpTest() {
        super();
        signUpPage = new SignUpPage();
        loginPage = new LoginPage(this.keyword);
    }
    public void startCreateAccount() throws InterruptedException {
        loginPage.acceptAllCookies();
        signUpPage.goToSignUp();
    }

//    @Test(priority = 1, description = "Create new customer successfully with store enable email confirm")
//    public void testCase_NSU1() throws InterruptedException {
//        String email = signUpPage.createNewEmail();
//        startCreateAccount();
//        signUpPage.signUpSuccessful("FIRST_NAME","LAST_NAME",email,email);
//    }
    @Test(priority = 2, description = "Create new customer and leave with blank form for required form")
    @Parameters("baseURL")
    public void testCase_NSU2(String baseURL) throws InterruptedException {
        startCreateAccount();
        signUpPage.signUpWithInputErrorData(baseURL + "customer/account/create/" ,"fullBlankField"," "," "," "," ",
                " ");
    }
    @Test(priority = 3, description = "Create new customer and input invalid data for email form")
    @Parameters("baseURL")
    public void testCase_NSU3(String baseURL) throws InterruptedException {
//        startCreateAccount();
        signUpPage.signUpWithInputErrorData(baseURL + "customer/account/create/", "emailInvalid","FIRST_NAME","LAST_NAME",
                "abc"," "," ");
    }
    @Test(priority = 4, description = "Create new customer and input confirm email not same email form")
    @Parameters("baseURL")
    public void testCase_NSU4(String baseURL) throws InterruptedException {
//        startCreateAccount();
        signUpPage.signUpWithInputErrorData(baseURL + "customer/account/create/","emailNotEqualEmailConfirm","FIRST_NAME","LAST_NAME",
                "EMAIL_VALID", "EMAIL_INVALID"," ");
    }
//    @Test(priority = 5, description = "Create new customer successfully with store enable email confirm")
//    @Parameters("baseURL")
//    public void testCase_NSU5(String baseURL) throws InterruptedException {
////        startCreateAccount();
//        signUpPage.signUpWithInputErrorData(baseURL + "customer/account/create/", "emailValidButExist","FIRST_NAME","LAST_NAME","EMAIL_VALID","EMAIL_VALID", " ");
//    }
//
//    @Test(priority = 6, description = "Create an account with the same password as the register email")
//    @Parameters("baseURL")
//    public void testCase_NSU6(String baseURL) throws InterruptedException {
//        //startCreateAccount();
//        signUpPage.signUpWithInputErrorData(baseURL + "customer/account/create/" ,"emailEqualPassword","FIRST_NAME","LAST_NAME","EMAIL_VALID","EMAIL_VALID", " ");
//    }
}
