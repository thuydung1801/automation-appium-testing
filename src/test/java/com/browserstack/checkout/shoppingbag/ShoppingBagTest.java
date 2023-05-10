package com.browserstack.checkout.shoppingbag;

import core.BaseTest;
import core.LogHelper;
import org.slf4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ShoppingBagTest extends BaseTest {
    private static Logger logger = LogHelper.getLogger();
    private ShoppingBagPage objShoppingBagPage = new ShoppingBagPage(this.keyword);
//    private ShoppingBagPage objCheckout = new ShoppingBagPage(this.keyword);
    public ShoppingBagTest(){
        super();
    }
    public void commonShopping() throws InterruptedException {
        objShoppingBagPage.acceptAllCookies();
        objShoppingBagPage.login("COM_INP_DATA_EMAIL_STAGE","COM_INP_DATA_PASS_STAGE");


    }
//    @Test
//    public void addProductToCart() throws Exception {
//        // navigate to bstackdemo
//        //keyword.navigateToUrl("https://www.glamira.co.uk/glamira-pendant-elsie.html?alloy=red_white-585&stone1=diamond-Brillant");
//        driver.get("https://www.glamira.co.uk/glamira-pendant-elsie.html?alloy=red_white-585&stone1=diamond-Brillant");
//        objCheckout.acceptAllCookies();
//
//
//    }
    @Test
    //(priority = 1, description = "Remove item from cart successfully with the shopping bag having 1 item")
//    @Parameters("baseURL")
    public void testCase_SP_02() throws Exception{
        logger.info("testCase_SP_02");
//        driver.get("https://stage.glamira.co.uk/mens-ring-smart-queen-skup7013.html?alloy=white-375&utm_widget=recommendation");
        commonShopping();
//        shoppingBagPageMobile.setup();
        objShoppingBagPage.addProduct("https://stage.glamira.co.uk/mens-ring-smart-queen-skup7013.html?alloy=white-375&utm_widget=recommendation");
        objShoppingBagPage.clickShoppingBagPage();
        objShoppingBagPage.removeProduct("CHECKOUT_ICON_REMOVE_MENRING");
        objShoppingBagPage.confirmMessage("CHECKOUT_MESSAGES_EMPTY");
    }

    @Test
//            (priority = 2, description="Remove item from cart successfully with the shopping bag having 1 normal item and 1 free gift")
//    @Parameters("baseURL")
    public void testCase_SP_03() throws InterruptedException{
        logger.info("testCase_SP_03");
//        commonShopping();
        //https://stage.glamira.co.uk/
        objShoppingBagPage.addProductWithGift("https://stage.glamira.co.uk/smart-ornament-skub7047.html?alloy=white-platin");
        objShoppingBagPage.clickShoppingBagPage();
        objShoppingBagPage.removeProduct("CHECKOUT_ICON_REMOVE_COUPLERING");
        objShoppingBagPage.confirmMessage("CHECKOUT_MESSAGES_EMPTY");
        //objShoppingBagPage.confirmMessage("CHECKOUT_MESSAGES_GIFT");

    }
    @Test
//            (priority = 3, description = "Add engraving with single ring")
//    @Parameters({"baseURL","devices"})
    public void testCase_SP_15() throws InterruptedException {
        logger.info("testCase_SP_15");
//        commonShopping();
        //https://stage.glamira.co.uk/
        objShoppingBagPage.addProduct("https://stage.glamira.co.uk/glamira-ring-zanessa.html?alloy=white-585&stone1=diamond-sapphire&stone2=diamond-sapphire");
        objShoppingBagPage.clickShoppingBagPage();
        objShoppingBagPage.viewDetail("CHECKOUT_BTN_VIEWDETAIL_GLAMIRARING_MOBILE");
        objShoppingBagPage.inputEngravingwithSingleRing("CHECKOUT_DATA_ENGRAVING",
                "CHECKOUT_HYPERLINK_ADD",
                "CHECKOUT_LBL_ENGRAVING_GLAMIRA_MOBILE");

    }
    @Test
//            (priority = 4, description = "Remove item from cart succcessfully with the shopping bag having mutiple items")
//    @Parameters("baseURL")
    public void testCase_SP_01() throws InterruptedException {
        logger.info("testCase_SP_01");
//        commonShopping();
        //https://stage.glamira.co.uk/
        objShoppingBagPage.addProduct("https://stage.glamira.co.uk/womens-ring-smart-ornament-skub7047.html?alloy=white-silber");
        objShoppingBagPage.addProduct("https://stage.glamira.co.uk/mens-ring-smart-queen-skup7013.html?alloy=white-375&utm_widget=recommendation");
        objShoppingBagPage.clickShoppingBagPage();
        objShoppingBagPage.removeProduct("CHECKOUT_ICON_REMOVE_WOMENRING");

    }
    //DONE SP04
    @Test
//            (priority = 5, description = "Update quantity using qty button successfully")
//    @Parameters("baseURL")
    public void testCase_SP_04() throws InterruptedException{
        logger.info("testCase_SP_04");
        //commonShoppingWithOutCookies();
        objShoppingBagPage.addProduct("https://stage.glamira.co.uk/mens-ring-smart-queen-skup7013.html?alloy=white-375&utm_widget=recommendation");
        objShoppingBagPage.clickShoppingBagPage();
        objShoppingBagPage.changeQty("CHECKOUT_DATA_QTY_2");


    }
    @Test
//            (priority = 6, description = "Checking situation that add more than 4 products with same type")
//    @Parameters("baseURL")
    public void testCase_SP_05() throws InterruptedException{
        logger.info("testCase_SP_05");
//        commonShopping();
//        commonShoppingWithOutCookies();
        objShoppingBagPage.clickShoppingBagPage();

        objShoppingBagPage.changeQty("CHECKOUT_DATA_QTY_MAX");
        objShoppingBagPage.confirmMessage("CHECKOUT_MESSAGES_GIFT_UPDATE");
        objShoppingBagPage.addProduct("https://stage.glamira.co.uk/mens-ring-smart-queen-skup7013.html?alloy=white-375&utm_widget=recommendation");
        objShoppingBagPage.confirmMessage("CHECKOUT_MESSAGES_ERROR");

    }
}
