package com.browserstack.signup;

import com.browserstack.home.LoginPage;
import core.BaseTest;
import core.LogHelper;
import org.slf4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class SignUpTest extends BaseTest {
    private SignUpPage signUpPage;
    private LoginPage loginPage;

    public SignUpTest() {
        super();
        signUpPage = new SignUpPage(this.keyword);
        loginPage = new LoginPage(this.keyword);
    }
    public void startCreateAccount(String baseUrl) throws InterruptedException {
        if(baseUrl.equals("https://stage.glamira.com/")){
            keyword.navigateToUrl(baseUrl);
        }
        //loginPage.acceptAllCookies();
        signUpPage.goToSignUp(baseUrl);
    }
    @Test (priority = 1, description = "Enter wrong code and create new customer successfully with store enable email confirm")
    public void testCase_NSU1_12() throws InterruptedException {
        startCreateAccount(" ");
        signUpPage.isSignUpSuccessOrFail("successfullyWithEmail");
    }
    @Test(priority = 2, description = "Create new customer and leave with blank form for required form")
    public void testCase_NSU2() throws InterruptedException {
        //startCreateAccount("");
        signUpPage.goToSignUp("");
        signUpPage.isSignUpSuccessOrFail("fullBlankField");
    }
    @Test(priority = 3, description = "Create new customer and input invalid data for email form")
    public void testCase_NSU3() throws InterruptedException {
        //startCreateAccount("");
        signUpPage.isSignUpSuccessOrFail( "emailInvalid");
    }
    @Test(priority = 4, description = "Create new customer and input confirm email not same email form")
    public void testCase_NSU4() throws InterruptedException {
        //startCreateAccount("");
        signUpPage.isSignUpSuccessOrFail( "emailNotEqualEmailConfirm");
    }
    @Test(priority = 5, description = "Create new customer and input email exist on database")
    public void testCase_NSU5() throws InterruptedException {
        //startCreateAccount("");
        signUpPage.isSignUpSuccessOrFail("emailValidButExist");
    }
    @Test(priority = 6, description = "Create an account with the same password as the register email")
    public void testCase_NSU6() throws InterruptedException {
      //startCreateAccount("");
        signUpPage.isSignUpSuccessOrFail("emailEqualPassword");
    }
    @Test(priority = 7, description = "Check error fields in password and resend code when sign up with email")
    public void testCase_NSU7_8_9_10_11_13() throws InterruptedException {
        //startCreateAccount("");
        signUpPage.checkInputErrorPassWord("email");
    }

    @Test(priority = 8, description = "Input invalid code and create new customer successfully with store enable phone number confirm")
    @Parameters("baseUrl")
    public void testCase_NSU14_23(String baseUrl) throws InterruptedException {
        startCreateAccount(baseUrl);
        signUpPage.isSignUpSuccessOrFail("successfullyWithPhoneNumber");
    }

    @Test(priority = 9, description = "Create new customer and leave with blank form for required form")
    @Parameters("baseUrl")
    public void testCase_NSU15(String baseUrl) throws InterruptedException {
        startCreateAccount(baseUrl);
//        keyword.navigateToUrl("BASE_URL");
        signUpPage.isSignUpSuccessOrFail("fullBlankField");
    }

    @Test(priority = 10, description = "Invalid phone number entered")
    @Parameters("baseUrl")
    public void testCase_NSU16(String baseUrl) throws InterruptedException {
        //startCreateAccount(baseUrl);
        signUpPage.isSignUpSuccessOrFail("invalidPhoneNumber");
    }

    @Test(priority = 11, description = "Register an account with the phone number already in the system")
    @Parameters("baseUrl")
    public void testCase_NSU17(String baseUrl) throws InterruptedException {
        //startCreateAccount(baseUrl);
        signUpPage.isSignUpSuccessOrFail("validPhoneNumber");
    }
    @Test(priority = 12, description = "Input valid email but exist when sign up with phone number")
    @Parameters("baseUrl")
    public void testCase_NSU24(String baseUrl) throws InterruptedException {
        //startCreateAccount(baseUrl);
        signUpPage.isSignUpSuccessOrFail("emailValidButExist");
    }

    @Test(priority = 13, description = "Check error fields in password when sign up with phone number")
    @Parameters("baseUrl")
    public void testCase_NSU18_19_20_21_22(String baseUrl) throws InterruptedException {
        //startCreateAccount(baseUrl);
        signUpPage.checkInputErrorPassWord("phone");
    }
}
