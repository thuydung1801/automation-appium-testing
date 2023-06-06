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
        objLoginAddress = new LoginAddressPage(this.keyword);
    }

    public void customerNotLogin() throws InterruptedException {
        objShoppingBagPage = new ShoppingBagPage(this.keyword);
        objLoginAddress = new LoginAddressPage(this.keyword);
        objShoppingBagPage.acceptAllCookies();

    }

    public void customerLogin() throws InterruptedException {
        objShoppingBagPage.acceptAllCookies();
        objShoppingBagPage.login("COM_INP_DATA_EMAIL_STAGE", "COM_INP_DATA_PASS_STAGE");
        keyword.untilJqueryIsDone(50L);
    }
    @Test
//            (priority = 1,description = "Add new billing address with all valid data and next page successfully")

    public void NLA_01_02() throws InterruptedException {
        logger.info("NLA_02");
        customerNotLogin();
        objShoppingBagPage.addProductWithOutOptions("https://stage.glamira.co.uk/glamira-bracelet-tanel.html?alloy=white_red-375&stone1=diamond-Brillant");
        objShoppingBagPage.clickShoppingBagPage();
        objLoginAddress.moveToPagecheckOut();
        objLoginAddress.moveToAddressPage();
        objLoginAddress.fillContactInformation(false, "CHECKOUT_LA_DATA_STREET_1",
                "CHECKOUT_LA_DATA_CODE_1","CHECKOUT_LA_DATA_CITY_1");
        objLoginAddress.verifyMelissa();

    }
    @Test
    //(priority = 2, description = "Add new billing address using suggest address with store no state")


    public void NLA_03_04() throws InterruptedException {
        logger.info("NLA_03");
        //customerNotLogin();
        objLoginAddress.resetForNewCase();
        objShoppingBagPage.addProductWithOutOptions("https://stage.glamira.co.uk/glamira-bracelet-tanel.html?alloy=white_red-375&stone1=diamond-Brillant");
        objShoppingBagPage.clickShoppingBagPage();
        objLoginAddress.moveToPagecheckOut();
        objLoginAddress.moveToAddressPage();
        objLoginAddress.fillContactInformation(true, null, null, null);
        objLoginAddress.verifyMelissa();
        objLoginAddress.compareAddress("CHECKOUT_DATA_EXPECT_DATA_4","CHECKOUT_LBL_ADDRESS_INFO");

    }

    @Test
    //(priority = 5, description = "Add new billing address and continue with your input options")

    public void NLA_05() throws InterruptedException {
        logger.info("NLA_05");
//        customerNotLogin();
        objLoginAddress.resetForNewCase();
        objShoppingBagPage.addProductWithOutOptions("https://stage.glamira.co.uk/glamira-bracelet-tanel.html?alloy=white_red-375&stone1=diamond-Brillant");
        objShoppingBagPage.clickShoppingBagPage();
        objLoginAddress.moveToPagecheckOut();
        objLoginAddress.moveToAddressPage();
        objLoginAddress.fillContactInformation(false, "CHECKOUT_LA_DATA_STREET_3",
                "CHECKOUT_LA_DATA_CODE_2", "CHECKOUT_LA_DATA_CITY_2");
        objLoginAddress.chooseAddressOnValidation(false,"CHECKOUT_LA_BTN_APPLY_ADDRESS");
        objLoginAddress.verifyMelissa();
        objLoginAddress.compareAddress("CHECKOUT_DATA_EXPECT_DATA_5","CHECKOUT_LBL_ADDRESS_INFO");
    }
    @Test
    //(priority = 3, description = "Add new billing address and continue with We Suggested option")

    public void NLA_06() throws InterruptedException {
        logger.info("NLA_06");
        objLoginAddress.resetForNewCase();
//        customerNotLogin();
        objShoppingBagPage.addProductWithOutOptions("https://stage.glamira.co.uk/glamira-bracelet-tanel.html?alloy=white_red-375&stone1=diamond-Brillant");
        objShoppingBagPage.clickShoppingBagPage();
        objLoginAddress.moveToPagecheckOut();
        objLoginAddress.moveToAddressPage();
        objLoginAddress.fillContactInformation(false, "CHECKOUT_LA_DATA_STREET_3",
                "CHECKOUT_LA_DATA_CODE_2", "CHECKOUT_LA_DATA_CITY_2");
        objLoginAddress.chooseAddressOnValidation(true,"CHECKOUT_LA_BTN_APPLY_ADDRESS");
        objLoginAddress.verifyMelissa();
        objLoginAddress.compareAddress("CHECKOUT_DATA_EXPECT_DATA_2","CHECKOUT_LBL_ADDRESS_INFO");
    }

    @Test
    //(priority = 4, description = "Add new another shipping address")

    public void NLA_08() throws InterruptedException {
        logger.info("NLA_08");
        objLoginAddress.addNewAddress(true,null,null,null,"CHECKOUT_HPL_NEW_ADDRESS");
    }

    @Test
    //(priority = 6, description = "Add new another shipping address")

    public void NLA_07() throws InterruptedException {
        logger.info("NLA_07");
        objLoginAddress.addNewAddress(false,"CHECKOUT_LA_DATA_STREET_4",
                "CHECKOUT_LA_DATA_CODE_2","CHECKOUT_LA_DATA_CITY_2","CHECKOUT_HPL_NEW_ADDRESS");
    }
    @Test
    //(priority = 7, description = "Add new shipping address with invalid data and using Your input option")

    public void NLA_10() throws InterruptedException {
        logger.info("NLA_10");
        //customerNotLogin();
        objLoginAddress.resetForNewCase();
        objShoppingBagPage.addProductWithOutOptions("https://stage.glamira.co.uk/glamira-bracelet-tanel.html?alloy=white_red-375&stone1=diamond-Brillant");
        objShoppingBagPage.clickShoppingBagPage();
        objLoginAddress.moveToPagecheckOut();
        objLoginAddress.moveToAddressPage();
        objLoginAddress.fillContactInformation(false, "CHECKOUT_LA_DATA_STREET_3",
                "CHECKOUT_LA_DATA_CODE_2", "CHECKOUT_LA_DATA_CITY_2");
        objLoginAddress.chooseAddressOnValidation(true,"CHECKOUT_LA_BTN_APPLY_ADDRESS");
        objLoginAddress.addNewAddress(false, "CHECKOUT_LA_DATA_STREET_3",
                "CHECKOUT_LA_DATA_CODE_2", "CHECKOUT_LA_DATA_CITY_2","CHECKOUT_HPL_NEW_ADDRESS");
        objLoginAddress.chooseAddressOnValidation(true,"CHECKOUT_LA_BTN_APPLY_ADDRESS_2");
        objLoginAddress.isAddNewAddress();
    }
    @Test
    //(priority = 8, description = "Add new shipping address with invalid data and using Your input option")

    public void NLA_11() throws InterruptedException {
        logger.info("NLA_11");
//        customerNotLogin();
        objLoginAddress.resetForNewCase();
        objShoppingBagPage.addProductWithOutOptions("https://stage.glamira.co.uk/glamira-bracelet-tanel.html?alloy=white_red-375&stone1=diamond-Brillant");
        objShoppingBagPage.clickShoppingBagPage();
        objLoginAddress.moveToPagecheckOut();
        objLoginAddress.moveToAddressPage();
        objLoginAddress.fillContactInformation(false, "CHECKOUT_LA_DATA_STREET_3",
                "CHECKOUT_LA_DATA_CODE_2", "CHECKOUT_LA_DATA_CITY_2");
        objLoginAddress.chooseAddressOnValidation(true,"CHECKOUT_LA_BTN_APPLY_ADDRESS");
        objLoginAddress.addNewAddress(false, "CHECKOUT_LA_DATA_STREET_3",
                "CHECKOUT_LA_DATA_CODE_2", "CHECKOUT_LA_DATA_CITY_2","CHECKOUT_HPL_NEW_ADDRESS");
        objLoginAddress.chooseAddressOnValidation(false,"CHECKOUT_LA_BTN_APPLY_ADDRESS_2");
        objLoginAddress.isAddNewAddress();
    }
    @Test
    //(priority = 9, description = "Edit shipping address same as billing")
//    @Parameters("baseURL")
    public void NLA_12_16() throws InterruptedException {
        objLoginAddress.editAddress("CHECKOUT_BTN_EDIT_ADDRESS");
        objLoginAddress.compareAddress("CHECKOUT_DATA_EXPECT_DATA_12","CHECKOUT_LBL_ADDRESS_INFO_2");
        objLoginAddress.chooseAddressOnValidation(false,"CHECKOUT_LA_BTN_APPLY_ADDRESS_2");
        objLoginAddress.goBack("CHECKOUT_BTN_CONTINUE_GUEST");
        objLoginAddress.moveToAddressPage();
        //objLoginAddress.checkOutNotLogin();

    }
    @Test
    //(priority = 1, description = "Edit billing address ")
//    @Parameters("baseURL")
    public void NLA_13_14_15() throws InterruptedException {
        objLoginAddress.editBillingAddress();
    }
    @Test
    //(priority = 10, description = "Login successfully with customer email on checkout page")
//    @Parameters("baseURL")
    public void NLA_17() throws InterruptedException {
        //customerNotLogin();
        objLoginAddress.resetForNewCase();
        objShoppingBagPage.addProductWithOutOptions("https://stage.glamira.co.uk/glamira-bracelet-tanel.html?alloy=white_red-375&stone1=diamond-Brillant");
        objShoppingBagPage.clickShoppingBagPage();
        objLoginAddress.moveToPagecheckOut();
        objLoginAddress.loginOnLAPage();
    }

    @Test
    //(priority = 11, description = "Leave blank Email/Phone or Password")
//    @Parameters("baseURL")
    public void NLA_19() throws InterruptedException {
//        customerNotLogin();
        objLoginAddress.resetForNewCase();
        objShoppingBagPage.addProductWithOutOptions("https://stage.glamira.co.uk/glamira-bracelet-tanel.html?alloy=white_red-375&stone1=diamond-Brillant");
        objShoppingBagPage.clickShoppingBagPage();
        objShoppingBagPage.moveToPagecheckOut();
        objLoginAddress.loginFailed("noEmail");
    }
    @Test
    //(priority = 12, description = "Continue with Customer login and input email or phone not matching password")
//    @Parameters("baseURL")
    public void NLA_20() throws InterruptedException {
        objLoginAddress.loginFailed("wrongPass");
    }
    @Test
    //(priority = 12, description = "Continue with Customer login and input email invalid")
    public void NLA_21() throws InterruptedException {
        objLoginAddress.loginFailed("invalidEmail");
    }
    @Test
    //(priority = 12, description = "Add new billing address with all valid data and next page successfully")
//    @Parameters({"baseURL","devices"})
    public void NLA_35() throws InterruptedException {
//        customerNotLogin();
        customerLogin();
        objShoppingBagPage.addProductWithOutOptions("https://stage.glamira.co.uk/glamira-bracelet-tanel.html?alloy=white_red-375&stone1=diamond-Brillant");
        objShoppingBagPage.clickShoppingBagPage();
        objShoppingBagPage.moveToPagecheckOut();
        objLoginAddress.addNewBillingAddress(false, "CHECKOUT_LA_DATA_STREET_1",
                "CHECKOUT_LA_DATA_CODE_1", "CHECKOUT_LA_DATA_CITY_1");
        objLoginAddress.verifyMelissa();
    }


}
