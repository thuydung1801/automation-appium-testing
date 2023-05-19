package com.browserstack.SignUp;

import com.browserstack.CheckoutPage;
import core.BaseTest;
import core.LogHelper;
import org.slf4j.Logger;
import org.testng.annotations.Test;

public class SignUpTest extends BaseTest {
    private CheckoutPage objCheckout;
    private SignUpPage objSignup;

    public SignUpTest() {
        super();
        objCheckout = new CheckoutPage(this.keyword);
        objSignup = new SignUpPage(this.keyword);
    }

    public void setup() throws InterruptedException {
        objCheckout.acceptAllCookies();
        objSignup.goToFormSignup(false,"ICON_LOGIN");
    }

    @Test(priority = 1, description = "Create new customer and input email exist on database")
    public void NSU_02() throws Exception {
        setup();
        objSignup.createCustomerExistData();
    }

    @Test(priority = 2, description = "Create new customer and input invalid data for email form")
    public void NSU_03() throws Exception {
//        setup();
        objSignup.createCustomerEmailInvalid();
    }

    @Test(priority = 3, description = "Create new customer and input confirm email not same email form")
    public void NSU_04() throws Exception {
//        setup();
        objSignup.createCustomerEmailNotSame();
    }

    @Test(priority = 4, description = "Create new customer and input email exist on database")
    public void NSU_05() throws Exception {
//        setup();
        objSignup.createCustomerEmailExist();
    }

    @Test(priority = 5, description = "\"Create a new account with password # email register and has:\n")
    public void NSU_07() throws Exception {
//        setup();
        objSignup.passwordAtLeast8character();
    }

    @Test(priority = 6, description = "\"Create a new account with password # email register and has:\n")
    public void NSU_08() throws Exception {
//        setup();
        objSignup.passwordAtLeastNumber();
    }

    @Test(priority = 7, description = "\"Create a new account with password # email register and has:\n")
    public void NSU_09() throws Exception {
//        setup();
        objSignup.confirmPasswordEntryConditionLowerLetter();
    }

    @Test(priority = 8, description = "\"Create a new account with password # email register and has:\n")
    public void NSU_010() throws Exception {
//        setup();
        objSignup.confirmPasswordEntryConditionLowerUpper();
    }

    @Test(priority = 9, description = "\"Create a new account with password # email register and has:\n")
    public void NSU_011() throws Exception {
//        setup();
        objSignup.confirmPasswordEntryConditionCharactersLike();
    }

    @Test(priority = 10, description = "Create an account with the same password as the register email")
    public void NSU_016() throws Exception {
//        setup();
        objSignup.createCustomerPasswordSameEmail();
    }

    @Test(priority = 11, description = "Enter the wrong code sent to the email")
    public void NSU_012() throws Exception {
//        setup();
        objSignup.wrongCodeSendEmail();
    }

    @Test(priority = 12, description = "Resend the code to email")
    public void NSU_013_01() throws Exception {
//        setup();
        objSignup.resendCodeAndCreateAccountSuccess();
    }
    //        SIGNUP WITH MOBILE

    @Test(priority = 12, description = "Sign up with your phone number")
    public void NSU_014() throws Exception {
        setup();

    }
    //        SIGNUP WITH MOBILE
    @Test(priority = 13, description = "Create new customer and leave with blank form for required form")
    public void testCase_SU015() throws Exception {
        objSignup.goToFormSignup(true,"ICON_LOGIN_MOBILE");
        objSignup.verifyRequiredFieldWithMobile();
    }

        @Test(priority = 14, description = "Invalid phone number entered")
    public void testCase_SU016() throws Exception {
        objSignup.enterDataSignUpWithMobile();
    }

    //    @Test(priority = 15, description = "Register an account with the phone number already in the system")
    public void testCase_SU017() throws Exception {
        objSignup.enterPhoneNumberAlreadyInSystem();
    }

    //    @Test(priority = 16, description = "Create a new account with password # Phone register and has:\\n\" + \"+ At least < 8 characters\\n\" + \"+ At least 1 number\\n\" + \"+ At least 1 lower case letter\\n\" + \"+ At least 1 upper case letter\\n\" + \"+ At least 1 characters like: # & $ ( ) * + , - . : , . = ? @ { } ~\\\"\")\n")
    public void testCase_SU018() throws Exception {
        objSignup.passwordLessThan8Characters();
    }

    //    @Test(priority = 17, description = "\"Create a new account with password # email register and has:\n" + "characters < 8\"")
    public void testCase_SU019() throws Exception {
        objSignup.checkConditionAtLeast1Number();
    }

    //    @Test(priority = 18, description = "Create a new account with password # email register and missing lower case lette")
    public void testCase_SU020() throws Exception {
        objSignup.checkConditionAtLeast1LowerLetter();
    }

    //    @Test(priority = 19, description = "Create a new account with password # email register and missing upper case letter")
    public void testCase_SU021() throws Exception {
        objSignup.checkConditionAtLeast1UpperLetter();
    }

    //    @Test(priority = 20, description = "Create a new account with password # email register and missing special character")
    public void testCase_SU022() throws Exception {
        objSignup.checkConditionAtLeast1CharactersLike();
    }

    //    @Test(priority = 21, description = "Register an account with the email already in the system")
    public void testCase_SU024() throws Exception {
        objSignup.checkConditionAEmailExisted();
    }
    //    @Test(priority = 22, description = "Create new customer successfully with store enable phone number confirm")
    public void testCase_SU023_Case_14() throws Exception {

        objSignup.createNewCustomerSuccessfullyWithPhone();
        objSignup.getActivationCode();
        objSignup.getCodeBE("SIGNUP_SWITCH_TO_TAB_CHECK2","感谢注册。","SIGNUP_CODE_RESEND");
    }
}
