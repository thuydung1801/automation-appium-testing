package com.browserstack.checkout;

import com.browserstack.home.LoginPage;
import core.AllureTestListener;
import core.BaseTest;
import core.LogHelper;
import org.slf4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
@Listeners({ AllureTestListener.class })
public class ShoppingBagTest extends BaseTest {
    private static Logger logger = LogHelper.getLogger();
    private ShoppingBagPage objShoppingBagPage;
    private LoginPage objLogin;

    public ShoppingBagTest(){ super();
        objShoppingBagPage = new ShoppingBagPage(this.keyword);
        objLogin = new LoginPage(this.keyword);
    }
    public void login() throws InterruptedException {
        objLogin.chooseLanguages();
        objLogin.acceptAllCookies();
        objLogin.login("LOGIN_DATA_EMAIL", "LOGIN_DATA_PASSWORD");
    }
    @Test
            (priority = 1, description = "Remove item from cart successfully with the shopping bag having 1 item")
    @Parameters("baseURL")
    public void testCase_SP_02(String baseURL) throws InterruptedException{
        logger.info("testCase_SP_02");
        login();
        objShoppingBagPage.addProduct(baseURL+"mens-ring-smart-queen-skup7013.html?alloy=white-375&utm_widget=recommendation");
        objShoppingBagPage.clickShoppingBagPage();
        objShoppingBagPage.removeProduct("CHECKOUT_ICON_REMOVE_A_RING");
        objShoppingBagPage.confirmMessage("CHECKOUT_MESSAGES_EMPTY");
    }
    @Test
            (priority = 2, description="Remove item from cart successfully with the shopping bag having 1 normal item and 1 free gift")
    @Parameters("baseURL")
    public void testCase_SP_03(String baseURL) throws InterruptedException{
        logger.info("testCase_SP_03");
        //login();
        objShoppingBagPage.addProductWithGift(baseURL+"smart-ornament-skub7047.html?alloy=white-platin");
        objShoppingBagPage.clickShoppingBagPage();
        objShoppingBagPage.removeProduct("CHECKOUT_ICON_REMOVE_COUPLE_RING");
        objShoppingBagPage.confirmMessage("CHECKOUT_MESSAGES_EMPTY");

    }
    @Test
            (priority = 3, description = "Add engraving with single ring")
    @Parameters("baseURL")
    public void testCase_SP_15(String baseURL) throws InterruptedException {
        logger.info("testCase_SP_15");
       // login();
        objShoppingBagPage.addProduct(baseURL+"glamira-ring-zanessa.html?alloy=white-585&stone1=diamond-sapphire&stone2=diamond-sapphire");
        objShoppingBagPage.clickShoppingBagPage();
        objShoppingBagPage.viewDetail("CHECKOUT_BTN_VIEW_DETAIL_GLAMIRA_RING");
        objShoppingBagPage.inputEngravingWithSingleRing("CHECKOUT_DATA_ENGRAVING", "CHECKOUT_LBL_ENGRAVING_GLAMIRA");

    }
    @Test
            (priority = 4, description = "Remove item from cart successfully with the shopping bag having mutiple items")
    @Parameters("baseURL")
    public void testCase_SP_01(String baseURL) throws InterruptedException {
        logger.info("testCase_SP_01");
        //login();
        objShoppingBagPage.addProduct(baseURL+"womens-ring-smart-ornament-skub7047.html?alloy=white-silber");
        objShoppingBagPage.addProduct(baseURL+"mens-ring-smart-queen-skup7013.html?alloy=white-375&utm_widget=recommendation");
        objShoppingBagPage.clickShoppingBagPage();
        objShoppingBagPage.removeProduct("CHECKOUT_ICON_REMOVE_WOMEN_RING");
    }
}
