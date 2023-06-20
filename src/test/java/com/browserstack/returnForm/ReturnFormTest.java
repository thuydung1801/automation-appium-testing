package com.browserstack.returnForm;

import com.browserstack.common.LoginPage;
import core.BaseTest;
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
    //go to return form and login with phone number
    public void commonReturnForm1() throws InterruptedException {
        keyword.deleteAllCookies();
        keyword.navigateToUrl("https://stage.glamira.com/return/");
        objLogin.acceptAllCookies();
        keyword.click("RETURN_FORM_BTN_PHONE_NUMBER");
    }
    @Test(description = "Submit Return form, Next step and not input Email")
    public void testCase_LS_06() throws InterruptedException {
        commonReturnForm();
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_EMP_EMAIL","RETURN_FORM_DATA_INVALID_EMAIL",false,"RETURN_FORM_MESS_EMPTY_EMAIL_LOGIN");
    }
    @Test(description = "Next step with customer invalid")
    public void testCase_LS_04() throws InterruptedException {
        //commonReturnForm();
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_INVALID_EMAIL","RETURN_FORM_DATA_INVALID_PASSWORD",false,"RETURN_FORM_MESS_INVALID_EMAIL_LOGIN");
    }
    @Test(description = "Next step with customer valid but don't have any order return available")
    public void testCase_LS_05() throws InterruptedException {
        //commonReturnForm();
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_EMAIL_NO_ORDER","RETURN_FORM_DATA_INVALID_PASSWORD",false,"RETURN_FORM_MESS_INVALID_EMAIL_LOGIN");
    }
    @Test(description = "Input email or order having space")
    public void testCase_LS_03() throws InterruptedException {
       objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_SPACE_EMAIL","RETURN_FORM_DATA_INVALID_PASSWORD",true,"RETURN_FORM_MESS_INVALID_PASSWORD_LOGIN");
    }
    @Test(description = "Next step return form, login with password not matching email customer")
    public void testCase_LS_02() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_FORM");
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_EMAIL","RETURN_FORM_DATA_INVALID_PASSWORD",true,"RETURN_FORM_MESS_INVALID_PASSWORD_LOGIN");
    }
    @Test(description = "Submit Return form, login successfully")
    public void testCase_LS_01() throws InterruptedException {
       keyword.navigateToUrl("URL_RETURN_FORM");
       objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_EMAIL","RETURN_FORM_DATA_PASSWORD",true,"RETURN_FORM_LBL_NEW_RETURN_REQUEST");
    }

    @Test(description = "Return order with the order haven't the item available resizing")
    public void testCase_LS_08() throws InterruptedException {
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_NO_RESIZE",true,"RETURN_FORM_SELECT_TYPE_WITHDRAWAL","RETURN_FORM_SELECT_TYPE_RESIZING");
    }
    @Test(description = "Return order with the order haven't the item available engraving")
    public void testCase_LS_09() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_NO_RESIZE",true,"RETURN_FORM_SELECT_TYPE_WITHDRAWAL","RETURN_FORM_SELECT_TYPE_ENGRAVING");
    }
    @Test(description = "Return order with the item has returned")
    public void testCase_LS_10() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_HAS_RETURNED",true,"RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
    }
    @Test(description = "Return order with the order > 60days")
    public void testCase_LS_07() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,"RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
    }
    @Test(description = "Select Return type and  checked \"I checked my address and I confirm it.\" form")
    public void testCase_SC_07() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,"RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
    }
    @Test(description = "Select Return type and not checked \"I checked my address and I confirm it.\" form")
    public void testCase_SC_08() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_NO_RESIZE",false,"RETURN_FORM_SELECT_TYPE_WITHDRAWAL","RETURN_FORM_SELECT_TYPE_RESIZING");
    }
    @Test(description = "Submit return request successfully with 1 normal item")
    public void testCase_SC_09() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,"RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_FREE");
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Submit return request successfully with multiple items")
    public void testCase_SC_10() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MULTIPLE_ITEM",true,"RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_FREE");
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }

    @Test(description = "Submit return request successfully with item has returned")
    public void testCase_SC_11() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_HAS_RETURNED",true,"RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_FREE");
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Submit return request successfully with \"I will ship myself\" options")
    public void testCase_SC_14() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,"RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_MYSELF");
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Submit return request successfully for Resize type with same size option")
    public void testCase_SC_18() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,"RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_FREE");
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Submit return request successfully for Resize type with different size option")
    public void testCase_SC_19() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,"RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_FREE");
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Submit return request for Resize type and didn't choose all required field")
    public void testCase_SC_20() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,"RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(false,"RETURN_FORM_CHECKTEXT_SHIP_FREE");
        objReturnForm.step3In3Screen(false);
    }
    @Test(description = "Submit return request successfully for Resize type and the order > 60 days")
    public void testCase_SC_21() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,"RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_FREE");
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }

    @Test(description = "Add tracking information successfully")
    public void testCase_MR_04() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.addTrackingInformation("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "not add any tracking information")
    public void testCase_MR_05() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.notAddAnyTrackingInformation("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Edit tracking information successfully")
    public void testCase_MR_06() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.editTrackingInformation("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }

    @Test(description = "View Detail return,Cancel My Return successfully")
    public void testCase_MR_02_MR_03() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,"RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RETURN_FORM_TXT_SIZE_L","RETURN_FORM_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RETURN_FORM_CHECKTEXT_SHIP_FREE");
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RETURN_FORM_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Reorder return successfully")
    public void testCase_MR_07() throws InterruptedException {
        objReturnForm.clickReopen();
    }
    @Test(description = "Next to Return form successfully")
    public void testCase_MR_01() throws InterruptedException {
        keyword.deleteAllCookies();
        keyword.navigateToUrl("https://stage.glamira.co.uk/");
        objLogin.login("RETURN_FORM_DATA_EMAIL", "RETURN_FORM_DATA_PASSWORD");
        objReturnForm.goToMyReturnOnMyAccount();
        objReturnForm.selectOrderReturn("RETURN_FORM_TXT_ORDER_MORE_60DAY",true,"RETURN_FORM_SELECT_TYPE_RESIZING","RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
    }
    @Test(description = "Next step when enter wrong email adress")
    public void testCase_LOS_11() throws InterruptedException {
        keyword.navigateToUrl("https://stage.glamira.com/return/");
        objLogin.acceptAllCookies();
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_EMAIL_NO_ORDER","RETURN_FORM_DATA_INVALID_PASSWORD",false,"RETURN_FORM_MESS_INVALID_EMAIL_LOGIN");
    }
    @Test(description = "Next step when enter wrong password ")
    public void testCase_LOS_12() throws InterruptedException {
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_EMAIL","RETURN_FORM_DATA_INVALID_PASSWORD",true,"RETURN_FORM_MESS_INVALID_PASSWORD_LOGIN");
    }
    @Test(description = "Next step is successful with correct email address and password")
    public void testCase_LOS_10() throws InterruptedException {
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_EMAIL","RETURN_FORM_DATA_PASSWORD",true,"RETURN_FORM_LBL_NEW_RETURN_REQUEST");
    }
    @Test(description = "Next step when enter wrong mobile number")
    public void testCase_LOS_14() throws InterruptedException {
        commonReturnForm1();
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_INVALID_PHONE_NUMBER","RETURN_FORM_DATA_INVALID_PASSWORD",false,"RETURN_FORM_MESS_INVALID_PHONE_NUMBER");
    }
    @Test(description = "Next step when enter wrong password ")
    public void testCase_LOS_15() throws InterruptedException {
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_PHONE_NUMBER","RETURN_FORM_DATA_INVALID_PASSWORD",true,"RETURN_FORM_MESS_INVALID_PASSWORD_LOGIN");
    }
    @Test(description = " Next step is successful with correct mobile number and password")
    public void testCase_LOS_13() throws InterruptedException {
        objReturnForm.inputDataReturnForm("RETURN_FORM_DATA_PHONE_NUMBER","RETURN_FORM_DATA_PASSWORD",true,"RETURN_FORM_LBL_NEW_RETURN_REQUEST");
    }
}

