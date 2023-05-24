package com.browserstack.checkout.loginaddress;

import com.browserstack.checkout.shoppingbag.ShoppingBagPage;
import core.AllureTestListener;
import core.BaseTest;
import core.LogHelper;
import org.slf4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



@Listeners({ AllureTestListener.class })
public class LoginAddressTest extends BaseTest {
    private static Logger logger = LogHelper.getLogger();
    public ShoppingBagPage objShoppingBagPage;
    private  LoginAddressPage objLoginAddress;

    public LoginAddressTest() {
        super();
    }

    public void customerNotLogin() throws InterruptedException {
        objShoppingBagPage = new ShoppingBagPage(this.keyword);
        objLoginAddress = new LoginAddressPage(this.keyword);
        objShoppingBagPage.acceptAllCookies();

    }

    public void customerLogin(String devices) throws InterruptedException {
        objShoppingBagPage.acceptAllCookies();
        objShoppingBagPage.login("COM_INP_DATA_EMAIL_STAGE", "COM_INP_DATA_PASS_STAGE");
        keyword.untilJqueryIsDone(50L);
    }
}
