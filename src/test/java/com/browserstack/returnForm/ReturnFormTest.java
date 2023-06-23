package com.browserstack.returnForm;

import com.browserstack.common.LoginPage;
import core.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class ReturnFormTest extends BaseTest {
    private LoginPage objLogin ;
    private ReturnFormPage objReturnForm;

    public ReturnFormTest() {
        super();
        objLogin = new LoginPage(this.keyword);
        objReturnForm = new ReturnFormPage();
    }

    public void commonReturnForm() throws InterruptedException {
        objLogin.acceptAllCookies();
        objReturnForm.goToReturnForm();
    }
    @Test(description = "Submit Return form, Next step and not input Email")
    @Parameters("baseURL")
    public void testCase_LS_06() throws InterruptedException {
        commonReturnForm();
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_EMP_EMAIL","RETURN_FORM_DATA_INVALID_EMAIL",
                false,"RETURN_FORM_MESS_EXPECT_EMPTY_EMAIL_LOGIN","RETURN_FORM_MESS_ACTUAL_ERROR_LOGIN");
    }
    @Test(description = "Next step with customer invalid")
    @Parameters("baseURL")
    public void testCase_LS_04() throws InterruptedException {
        //commonReturnForm();
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_INVALID_EMAIL","RETURN_FORM_DATA_INVALID_PASSWORD",
                false,"RETURN_FORM_MESS_EXPECT_INVALID_EMAIL_LOGIN","RETURN_FORM_MESS_ACTUAL_INVALID_LOGIN");
    }
    @Test(description = "Next step with customer valid but don't have any order return available")
    @Parameters("baseURL")
    public void testCase_LS_05() throws InterruptedException {
        //commonReturnForm();
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_EMAIL_NO_ORDER","RETURN_FORM_DATA_INVALID_PASSWORD",
                false,"RETURN_FORM_MESS_EXPECT_INVALID_EMAIL_LOGIN","RETURN_FORM_MESS_ACTUAL_INVALID_LOGIN");
    }
    @Test(description = "Input email or order having space")
    @Parameters("baseURL")
    public void testCase_LS_03() throws InterruptedException {
       objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_SPACE_EMAIL","RETURN_FORM_DATA_INVALID_PASSWORD",
               true,"RETURN_FORM_MESS_INVALID_PASSWORD_LOGIN","RETURN_FORM_MESS_ACTUAL_INVALID_LOGIN");
    }
    @Test(description = "Next step return form, login with password not matching email customer")
    @Parameters("baseURL")
    public void testCase_LS_02(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL);
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_EMAIL","RETURN_FORM_DATA_INVALID_PASSWORD",
                true,"RETURN_FORM_MESS_INVALID_PASSWORD_LOGIN","RETURN_FORM_MESS_ACTUAL_INVALID_LOGIN");
    }
    @Test(description = "Submit Return form, login successfully")
    @Parameters("baseURL")
    public void testCase_LS_01(String baseURL) throws InterruptedException {
       keyword.navigateToUrl(baseURL);
       objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_EMAIL","RETURN_FORM_DATA_PASSWORD",true,
               "RETURN_FORM_LBL_EXPECT_NEW_RETURN_REQUEST","RETURN_FORM_LBL_ACTUAL_NEW_RETURN_REQUEST");
    }

    @Test(description = "Return order with the order haven't the item available resizing")
    @Parameters("baseURL")
    public void testCase_LS_08() throws InterruptedException {
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_NO_RESIZE",true,
                "RETURN_FORM_SELECT_TYPE_WITHDRAWAL","RETURN_FORM_SELECT_TYPE_RESIZING");
    }
    @Test(description = "Return order with the order haven't the item available engraving")
    @Parameters("baseURL")
    public void testCase_LS_09(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_NO_RESIZE",true,
                "RETURN_FORM_SELECT_TYPE_WITHDRAWAL","RETURN_FORM_SELECT_TYPE_ENGRAVING");
    }
    @Test(description = "Return order with the item has returned")
    @Parameters("baseURL")
    public void testCase_LS_10(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_HAS_RETURNED",true,
                "RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
    }
    @Test(description = "Return order with the order > 60days")
    @Parameters("baseURL")
    public void testCase_LS_07(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,
                "RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
    }
    @Test(description = "Select Return type and  checked \"I checked my address and I confirm it.\" form")
    @Parameters("baseURL")
    public void testCase_SC_07(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,
                "RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
    }
    @Test(description = "Select Return type and not checked \"I checked my address and I confirm it.\" form")
    @Parameters("baseURL")
    public void testCase_SC_08(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_NO_RESIZE",false,
                "RETURN_FORM_SELECT_TYPE_WITHDRAWAL","RETURN_FORM_SELECT_TYPE_RESIZING");
    }
    @Test(description = "Submit return request successfully with \"Shipping label\" option, with store having shipping free")
    @Parameters("baseURL")
    public void testCase_SC_17(String baseURL) throws InterruptedException {
        objReturnForm.changeShippingLabel("Courier");
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,
                "RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE_L","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_FREE",true);
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Submit return request successfully with \"Shipping label\" option, with store having shipping fee")
    @Parameters("baseURL")
    public void testCase_SC_15(String baseURL) throws InterruptedException {
        objReturnForm.changeShippingLabel("G100620");
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,
                "RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE_L","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_FEE",true);
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Submit return request with Shipping label option and didn't checkbox The shipment fee will not be refunded. , with having shipping fee")
    @Parameters("baseURL")
    public void testCase_SC_16(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,
                "RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE_L","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_FEE",false);
        objReturnForm.step3In3Screen(true);
    }
    @Test(description = "Submit return request successfully with 1 normal item")
    @Parameters("baseURL")
    public void testCase_SC_09(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,
                "RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_FEE",true);
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Submit return request successfully with multiple items")
    @Parameters("baseURL")
    public void testCase_SC_10(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MULTIPLE_ITEM",true,
                "RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_FEE",true);
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }

    @Test(description = "Submit return request successfully with item has returned")
    @Parameters("baseURL")
    public void testCase_SC_11(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_HAS_RETURNED",true,
                "RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_FEE",true);
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Submit return request successfully with item instock")
    @Parameters("baseURL")
    public void testCase_SC_12(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_HAS_RETURNED",true,
                "RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_FEE",true);
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Submit return request successfully with a chirld product of bridal set, an item has return")
    @Parameters("baseURL")
    public void testCase_SC_13(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_SET_ITEM",true,
                "RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_FEE",true);
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Submit return request successfully with \"I will ship myself\" options")
    @Parameters("baseURL")
    public void testCase_SC_14(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,
                "RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_MYSELF",true);
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Submit return request successfully for Resize type with same size option")
    @Parameters("baseURL")
    public void testCase_SC_18(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,
                "RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_FEE",true);
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Submit return request successfully for Resize type with different size option")
    @Parameters("baseURL")
    public void testCase_SC_19(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,
                "RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_FEE",true);
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Submit return request for Resize type and didn't choose all required field")
    @Parameters("baseURL")
    public void testCase_SC_20(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,
                "RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(false,"RETURN_FORM_CHECKTEXT_SHIP_FEE",true);
        objReturnForm.step3In3Screen(false);
    }
    @Test(description = "Submit return request successfully for Resize type and the order > 60 days")
    @Parameters("baseURL")
    public void testCase_SC_21(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,
                "RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_MYSELF",true);
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }

    @Test(description = "Add tracking information successfully")
    @Parameters("baseURL")
    public void testCase_MR_04(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.addTrackingInformation("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "not add any tracking information")
    @Parameters("baseURL")
    public void testCase_MR_05(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.notAddAnyTrackingInformation("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Edit tracking information successfully")
    @Parameters("baseURL")
    public void testCase_MR_06(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.editTrackingInformation("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }

    @Test(description = "View Detail return,Cancel My Return successfully")
    @Parameters("baseURL")
    public void testCase_MR_02_MR_03(String baseURL) throws InterruptedException {
        keyword.navigateToUrl(baseURL+"order/index/");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,
                "RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE_L","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_FEE",true);
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Reorder return successfully")
    @Parameters("baseURL")
    public void testCase_MR_07() throws InterruptedException {
        objReturnForm.clickReopen();
    }
    @Test(description = "Next to Return form successfully")
    @Parameters("baseURL")
    public void testCase_MR_01(String baseURL) throws InterruptedException {
        keyword.deleteAllCookies();
        keyword.navigateToUrl(baseURL);
        objLogin.login("RETURN_FORM_DATA_EMAIL", "RETURN_FORM_DATA_PASSWORD");
        objReturnForm.goToMyReturnOnMyAccount();
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,
                "RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
    }
    //Login on stage.com With email
    @Test(description = "Next step when enter wrong email adress")
    @Parameters("baseURL")
    public void testCase_LOS_11(String baseURL) throws InterruptedException {
        objReturnForm.commonReturnFormWithPhoneNumber(baseURL,"RETURN_FORM_BTN_EMAIL_USER");
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_EMAIL_NO_ORDER","RETURN_FORM_DATA_INVALID_PASSWORD",
                false,"RETURN_FORM_MESS_EXPECT_INVALID_EMAIL_LOGIN","RETURN_FORM_MESS_ACTUAL_INVALID_LOGIN");
    }
    @Test(description = "Next step when enter wrong password ")
    @Parameters("baseURL")
    public void testCase_LOS_12() throws InterruptedException {
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_EMAIL","RETURN_FORM_DATA_INVALID_PASSWORD",
                true,"RETURN_FORM_MESS_INVALID_PASSWORD_LOGIN","RETURN_FORM_MESS_ACTUAL_INVALID_LOGIN");
    }
    @Test(description = "Next step is successful with correct email address and password")
    @Parameters("baseURL")
    public void testCase_LOS_10() throws InterruptedException {
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_EMAIL","RETURN_FORM_DATA_PASSWORD",
                true,"RETURN_FORM_LBL_EXPECT_NEW_RETURN_REQUEST","RETURN_FORM_LBL_ACTUAL_NEW_RETURN_REQUEST");
    }
    //Login on stage.com With Mobile Number
    @Test(description = "Next step when enter wrong mobile number")
    @Parameters("baseURL")
    public void testCase_LOS_14(String baseURL) throws InterruptedException {
        objReturnForm.commonReturnFormWithPhoneNumber(baseURL,"RETURN_FORM_BTN_PHONE_NUMBER");
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_INVALID_PHONE_NUMBER","RETURN_FORM_DATA_INVALID_PASSWORD",
                false,"RETURN_FORM_MESS_INVALID_PHONE_NUMBER","RETURN_FORM_MESS_ACTUAL_ERROR_LOGIN");
    }
    @Test(description = "Next step when enter wrong password ")
    @Parameters("baseURL")
    public void testCase_LOS_15() throws InterruptedException {
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_PHONE_NUMBER","RETURN_FORM_DATA_INVALID_PASSWORD",
                true,"RETURN_FORM_MESS_INVALID_PASSWORD_LOGIN","RETURN_FORM_MESS_ACTUAL_INVALID_LOGIN");
    }
    @Test(description = " Next step is successful with correct mobile number and password")
    @Parameters("baseURL")
    public void testCase_LOS_13() throws InterruptedException {
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_PHONE_NUMBER","RETURN_FORM_DATA_PASSWORD",
                true,"RETURN_FORM_LBL_EXPECT_NEW_RETURN_REQUEST","RETURN_FORM_LBL_ACTUAL_NEW_RETURN_REQUEST");
    }

    //Login -The store check is different from the store that creates the order
    @Test(description = "Next step when entering  email value")
    @Parameters("baseURL")
    public void testCase_LOS_33(String baseURL) throws InterruptedException {
        objReturnForm.commonReturnFormWithPhoneNumber(baseURL,"RETURN_FORM_BTN_EMAIL_USER");
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_EMAIL2","RETURN_FORM_DATA_PASSWORD",
                false,"RETURN_FORM_MESS_EXPECT_INVALID_EMAIL_LOGIN","RETURN_FORM_MESS_ACTUAL_INVALID_LOGIN");
    }
    @Test(description = " Next step when entering mobile number valid")
    @Parameters("baseURL")
    public void testCase_LOS_34(String baseURL) throws InterruptedException {
        objReturnForm.commonReturnFormWithPhoneNumber(baseURL,"RETURN_FORM_BTN_PHONE_NUMBER");
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_INVALID_PHONE_NUMBER","RETURN_FORM_DATA_PASSWORD",
                false,"RETURN_FORM_MESS_INVALID_PHONE_NUMBER","RETURN_FORM_MESS_ACTUAL_ERROR_LOGIN");
    }
    @Test(description = " Change the option after the error message is displayed")
    @Parameters("baseURL")
    public void testCase_LOS_20(String baseURL) throws InterruptedException {
        objReturnForm.commonReturnFormWithPhoneNumber(baseURL,"RETURN_FORM_BTN_EMAIL_USER");
        objReturnForm.changeOptionLogin("RETURN_FORM_DATA_EMAIL2","RETURN_FORM_DATA_INVALID_PHONE_NUMBER",
                "RETURN_FORM_MESS_EXPECT_INVALID_EMAIL_LOGIN","RETURN_FORM_MESS_ACTUAL_ERROR_LOGIN",
                "RETURN_FORM_MESS_INVALID_PHONE_NUMBER", "RETURN_FORM_MESS_ACTUAL_ERROR_LOGIN");
    }
}

