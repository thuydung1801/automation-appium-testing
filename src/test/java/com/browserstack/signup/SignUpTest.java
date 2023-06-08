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
        signUpPage = new SignUpPage(this.keyword);
        loginPage = new LoginPage(this.keyword);
    }
    public void startCreateAccount() throws InterruptedException {
        loginPage.acceptAllCookies();
        signUpPage.goToSignUp();
    }


//    @Test (priority = 0, description = "Create new customer successfully with store enable email confirm")
//    public void testCase_NSU1() throws InterruptedException {
//        startCreateAccount();
//        signUpPage.isSignUpSuccessOrFail("successfullyWithEmail","email");
//    }

    @Test(priority = 1, description = "Create new customer and leave with blank form for required form")
    public void testCase_NSU2() throws InterruptedException {
        startCreateAccount();
        signUpPage.isSignUpSuccessOrFail("fullBlankField","email");
    }
    @Test(priority = 2, description = "Create new customer and input invalid data for email form")
    public void testCase_NSU3() throws InterruptedException {
//        startCreateAccount();
        signUpPage.isSignUpSuccessOrFail( "emailInvalid","email");
    }
    @Test(priority = 3, description = "Create new customer and input confirm email not same email form")
    public void testCase_NSU4() throws InterruptedException {
       // startCreateAccount();
        signUpPage.isSignUpSuccessOrFail( "emailNotEqualEmailConfirm","email");
    }
    @Test(priority = 4, description = "Create new customer and input email exist on database")
    public void testCase_NSU5() throws InterruptedException {
       // startCreateAccount();
        signUpPage.isSignUpSuccessOrFail("emailValidButExist","email");
    }
    @Test(priority = 5, description = "Create an account with the same password as the register email")
    public void testCase_NSU6() throws InterruptedException {
      //startCreateAccount();
        signUpPage.isSignUpSuccessOrFail("emailEqualPassword","email");
    }

    @Test(priority = 6, description = "Check error fields in password when sign up with email")
    public void testCase_NSU7_8_9_10_11() throws InterruptedException {
        //startCreateAccount();
        signUpPage.inputErrorPassWord("1fieldErrorPassWord","email");
    }

//    @Test(priority = 7, description = "Create account with password without number")
//    public void testCase_NSU8() throws InterruptedException {
//        //startCreateAccount();
//        signUpPage.inputErrorPassWord("passWordWithoutNumber","email");
//    }
//
//    @Test(priority = 8, description = "Create account with password without lowercase")
//    public void testCase_NSU9() throws InterruptedException {
//        //startCreateAccount();
//        signUpPage.inputErrorPassWord("passWordWithoutLowerCase","email");
//    }
//
//    @Test(priority = 9, description = "Create account with password without uppercase")
//    public void testCase_NSU10() throws InterruptedException {
//        //startCreateAccount();
//        signUpPage.inputErrorPassWord("passWordWithoutUpperCase","email");
//    }
//    @Test(priority = 10, description = "Create account with password without special character")
//    public void testCase_NSU11() throws InterruptedException {
//        //startCreateAccount();
//        signUpPage.inputErrorPassWord("1fieldErrorPassWord","email");
//    }

    @Test(priority = 11, description = "Enter the wrong code sent to the email")
    public void testCase_NSU12() throws InterruptedException {
        //startCreateAccount();
        signUpPage.enterErrorVerifyCodeOrResend("Error verify code","email");
    }

    @Test(priority = 12, description = "Resend the code to email")
    public void testCase_NSU13() throws InterruptedException {
        //startCreateAccount();
        signUpPage.enterErrorVerifyCodeOrResend("Resend code","email");
    }
    @Test(priority = 13, description = "Create new customer successfully with store enable phone number confirm")
    public void testCase_NSU14() throws InterruptedException {
        //startCreateAccount();
        signUpPage.isSignUpSuccessOrFail("successfullyWithPhoneNumber","phoneNumber");
    }

    @Test(priority = 14, description = "Create new customer and leave with blank form for required form")
    public void testCase_NSU15() throws InterruptedException {
        //startCreateAccount();
        signUpPage.isSignUpSuccessOrFail("fullBlankField","phone");
    }

    @Test(priority = 15, description = "Invalid phone number entered")
    public void testCase_NSU16() throws InterruptedException {
        //startCreateAccount();
        signUpPage.isSignUpSuccessOrFail("invalidPhoneNumber","phone");
    }

    @Test(priority = 16, description = "Register an account with the phone number already in the system")
    public void testCase_NSU17() throws InterruptedException {
       // startCreateAccount();
        signUpPage.isSignUpSuccessOrFail("validPhoneNumber","phone");
    }

        @Test(priority = 17, description = "Check error fields in password when sign up with phone number")
    public void testCase_NSU18_19_20_21_22() throws InterruptedException {
        //startCreateAccount();
        signUpPage.inputErrorPassWord("1fieldErrorPassWord","phone");
    }
    @Test(priority = 18, description = "Enter the wrong code sent to the Phone")
    public void testCase_NSU23() throws InterruptedException {
        //startCreateAccount();
        signUpPage.enterErrorVerifyCodeOrResend("Error verify code", "phone");
    }
    @Test(priority = 19, description = "Input valid email but exist when sign up with phone number")
    public void testCase_NSU24() throws InterruptedException {
        //startCreateAccount();
        signUpPage.isSignUpSuccessOrFail("emailValidButExist", "phone");
    }


}
