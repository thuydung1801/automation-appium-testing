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
        objSignup.goToFormSignup();
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
        objSignup.passwordAtLeast8character ();
    }
    @Test(priority = 6, description = "\"Create a new account with password # email register and has:\n")
    public void NSU_08() throws Exception {
//        setup();
        objSignup.passwordAtLeastNumber ();
    }

    @Test(priority = 7, description = "\"Create a new account with password # email register and has:\n")
    public void NSU_09() throws Exception {
//        setup();
        objSignup.confirmPasswordEntryConditionLowerLetter ();
    }

    @Test(priority = 8, description = "\"Create a new account with password # email register and has:\n")
    public void NSU_010() throws Exception {
//        setup();
        objSignup.confirmPasswordEntryConditionLowerUpper ();
    }
    @Test(priority = 9, description = "\"Create a new account with password # email register and has:\n")
    public void NSU_011() throws Exception {
//        setup();
        objSignup.confirmPasswordEntryConditionCharactersLike ();
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
}
