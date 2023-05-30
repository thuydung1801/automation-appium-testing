package com.browserstack.home;

import com.browserstack.CheckoutPage;
import core.BaseTest;
import org.testng.annotations.Test;

public class Login extends BaseTest {
    private CheckoutPage objCheckout;
    private LoginPage objLogin;

    public Login() {
        super();
        objCheckout = new CheckoutPage(this.keyword);
        objLogin = new LoginPage(this.keyword);
    }

    @Test
    public void login() throws InterruptedException {
        objLogin.chooseLanguages();
        objLogin.acceptAllCookies();
        objLogin.login("LOGIN_DATA_EMAIL", "LOGIN_DATA_PASSWORD");
    }

}
