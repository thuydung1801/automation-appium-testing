package com.browserstack.checkout.loginaddress;

import com.browserstack.checkout.shoppingbag.ShoppingBagPage;
import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import core.PropertiesFile;
import org.slf4j.Logger;


import java.util.Date;

public class LoginAddressPage extends BasePage {
    private static Logger logger = LogHelper.getLogger();

    public LoginAddressPage(KeywordWeb key) {
        super(key);
    }

    public LoginAddressPage() {
        super();
    }

    public ShoppingBagPage objShoppingBagPage;

    private LoginAddressPage objLoginAddress;

    public void moveToAddressPage() {
        keyword.webDriverWaitForElementPresent("CHECKOUT_BTN_CONTINUE_GUEST", 40);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        keyword.click("CHECKOUT_BTN_CONTINUE_GUEST");
    }

    public void moveToPagecheckOut() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        Thread.sleep(2000);
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        keyword.verifyElementPresent("CHECKOUT_BTN_CHECKOUT");
        keyword.click("CHECKOUT_BTN_CHECKOUT");
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        Thread.sleep(3000);
        keyword.webDriverWaitForElementPresent("CHECKOUT_LA_LBL_CHECKOUT", 20);
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");

    }
}
