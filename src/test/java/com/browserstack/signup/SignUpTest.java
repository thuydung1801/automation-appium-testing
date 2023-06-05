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

    /*
    @Test //(priority = 10, description = "Create new customer successfully with store enable email confirm")
    public void testCase_NSU1() throws InterruptedException {
//        startCreateAccount();
        signUpPage.signUpSuccessfulWithEmail();
    }

    @Test(priority = 1, description = "Create new customer and leave with blank form for required form")
    public void testCase_NSU2() throws InterruptedException {
        startCreateAccount();
        signUpPage.signUpWithInputErrorData("fullBlankField");
    }
    @Test(priority = 2, description = "Create new customer and input invalid data for email form")
    public void testCase_NSU3() throws InterruptedException {
//        startCreateAccount();
        signUpPage.signUpWithInputErrorData( "emailInvalid");
    }
    @Test(priority = 3, description = "Create new customer and input confirm email not same email form")
    public void testCase_NSU4() throws InterruptedException {
//        startCreateAccount();
        signUpPage.signUpWithInputErrorData( "emailNotEqualEmailConfirm");
    }
    @Test(priority = 4, description = "Create new customer and input email exist on database")
    public void testCase_NSU5() throws InterruptedException {
//        startCreateAccount();
        signUpPage.signUpWithInputErrorData("emailValidButExist");
    }
    @Test(priority = 5, description = "Create an account with the same password as the register email")
    public void testCase_NSU6() throws InterruptedException {
//        startCreateAccount();
        signUpPage.signUpWithInputErrorData("emailEqualPassword");
    }

    @Test(priority = 6, description = "Create account with password less 8 character")
    public void testCase_NSU7() throws InterruptedException {
//        startCreateAccount();
        signUpPage.inputErrorPassWord("passWordLess8Character");
    }
    @Test(priority = 7, description = "Create account with password without number")
    public void testCase_NSU8() throws InterruptedException {
        //startCreateAccount();
        signUpPage.inputErrorPassWord("passWordWithoutNumber");
    }
    @Test(priority = 8, description = "Create account with password without lowercase")
    public void testCase_NSU9() throws InterruptedException {
        //startCreateAccount();
        signUpPage.inputErrorPassWord("passWordWithoutLowerCase");
    }
    @Test(priority = 9, description = "Create account with password without uppercase")
    public void testCase_NSU10() throws InterruptedException {
        //startCreateAccount();
        signUpPage.inputErrorPassWord("passWordWithoutUpperCase");
    }
    @Test(priority = 10, description = "Create account with password without special character")
    public void testCase_NSU11() throws InterruptedException {
        //startCreateAccount();
        signUpPage.inputErrorPassWord("passWordWithoutSpecialCharacter");
    }
    @Test(priority = 11, description = "Enter the wrong code sent to the email")
    public void testCase_NSU12() throws InterruptedException {
//        startCreateAccount();
        signUpPage.enterErrorVerifyCodeOrResend("Error verify code");
    }
    @Test(priority = 12, description = "Enter the wrong code sent to the email")
    public void testCase_NSU13() throws InterruptedException {
        //startCreateAccount();
        signUpPage.enterErrorVerifyCodeOrResend("Resend code");
    }
    @Test(priority = 13, description = "Create new customer successfully with store enable phone number confirm")
    public void testCase_NSU14() throws InterruptedException {
        //startCreateAccount();
        signUpPage.signUpSuccessfulWithPhoneNumber();
    }
     */
//    @Test(priority = 14, description = "Create new customer and leave with blank form for required form")
//    public void testCase_NSU15() throws InterruptedException {
//        startCreateAccount();
//        signUpPage.signUpWithInputErrorData("fullBlankField");
//    }

//    @Test(priority = 15, description = "Invalid phone number entered")
//    public void testCase_NSU16() throws InterruptedException {
//        startCreateAccount();
//        signUpPage.signUpWithInputErrorData("invalidPhoneNumber");
//    }

    @Test(priority = 16, description = "Register an account with the phone number already in the system")
    public void testCase_NSU17() throws InterruptedException {
        startCreateAccount();
        signUpPage.signUpWithInputErrorData("validPhoneNumber");
    }
}
