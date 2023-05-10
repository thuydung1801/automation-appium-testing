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

}
