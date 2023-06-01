package com.browserstack.home;

import core.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private LoginPage objLogin;

    public LoginTest() {
        super();

    }

    @Test
    public void login() throws InterruptedException {
        objLogin = new LoginPage(this.keyword);
        objLogin.acceptAllCookies();
        objLogin.login("LOGIN_DATA_EMAIL", "LOGIN_DATA_PASSWORD");
    }

}
