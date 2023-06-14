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
    @Test(description = "Submit Return form, Next step and not input Email")
    public void testCase_LS_06() throws InterruptedException {
        commonReturnForm();
        objReturnForm.inputDataReturnForm("RF_DATA_EMP_EMAIL","RF_DATA_INVALID_EMAIL",false,"RF_MES_EMPTY_EMAIL_LOGIN");
    }
    @Test(description = "Next step with customer invalid")
    public void testCase_LS_04() throws InterruptedException {
        //commonReturnForm();
        objReturnForm.inputDataReturnForm("RF_DATA_INVALID_EMAIL","RF_DATA_INVALID_PASSWORD",false,"RF_MES_INVALID_EMAIL_LOGIN");
    }
    @Test(description = "Next step with customer valid but don't have any order return avaiable")
    public void testCase_LS_05() throws InterruptedException {
        //commonReturnForm();
        objReturnForm.inputDataReturnForm("RF_DATA_EMAIL_NO_ORDER","RF_DATA_INVALID_PASSWORD",false,"RF_MES_INVALID_EMAIL_LOGIN");
    }
    @Test(description = "Input email or order having space")
    public void testCase_LS_03() throws InterruptedException {
       objReturnForm.inputDataReturnForm("RF_DATA_SPACE_EMAIL","RF_DATA_INVALID_PASSWORD",true,"RF_MES_INVALID_PASSWORD_LOGIN");
    }
    @Test(description = "Next step return form, login with password not matching email customer")
    public void testCase_LS_02() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_FORM");
        objReturnForm.inputDataReturnForm("RF_DATA_EMAIL","RF_DATA_INVALID_PASSWORD",true,"RF_MES_INVALID_PASSWORD_LOGIN");
    }
    @Test(description = "Submit Return form, login successfully")
    public void testCase_LS_01() throws InterruptedException {
       keyword.navigateToUrl("URL_RETURN_FORM");
       objReturnForm.inputDataReturnForm("RF_DATA_EMAIL","RF_DATA_PASSWORD",true,"RF_LBL_NEW_RETURN_REQUEST");
    }

    @Test(description = "Return order with the order haven't the item available resizing")
    public void testCase_LS_08() throws InterruptedException {
        objReturnForm.selectOrderReturn("RF_TXT_ORDER_NO_RESIZE",true,"RF_SELECT_TYPE_WITHDRAWAL","RF_SELECT_TYPE_RESIZING");
    }
    @Test(description = "Return order with the order haven't the item available engraving")
    public void testCase_LS_09() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RF_TXT_ORDER_NO_RESIZE",true,"RF_SELECT_TYPE_WITHDRAWAL","RF_SELECT_TYPE_ENGRAVING");
    }
    @Test(description = "Return order with the item has returned")
    public void testCase_LS_10() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RF_TXT_ORDER_HAS_RETURNED",true,"RF_SELECT_TYPE_RESIZING","RF_SELECT_TYPE_WITHDRAWAL");
    }
    @Test(description = "Return order with the order > 60days")
    public void testCase_LS_07() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RF_TXT_ORDER_MORE_60DAY",true,"RF_SELECT_TYPE_RESIZING","RF_SELECT_TYPE_WITHDRAWAL");
    }
    @Test(description = "Select Return type and  checked \"I checked my address and I confirm it.\" form")
    public void testCase_SC_07() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RF_TXT_ORDER_MORE_60DAY",true,"RF_SELECT_TYPE_RESIZING","RF_SELECT_TYPE_WITHDRAWAL");
    }
    @Test(description = "Select Return type and not checked \"I checked my address and I confirm it.\" form")
    public void testCase_SC_08() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RF_TXT_ORDER_NO_RESIZE",false,"RF_SELECT_TYPE_WITHDRAWAL","RF_SELECT_TYPE_RESIZING");
    }
    @Test(description = "Submit return request successfully with 1 normal item")
    public void testCase_SC_09() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RF_TXT_ORDER_MORE_60DAY",true,"RF_SELECT_TYPE_RESIZING","RF_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RF_TXT_SIZE","RF_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RF_CHECKTEXT_SHIP_FREE");
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RF_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Submit return request successfully with \"I will ship myself\" options")
    public void testCase_SC_14() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RF_TXT_ORDER_MORE_60DAY",true,"RF_SELECT_TYPE_RESIZING","RF_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RF_TXT_SIZE","RF_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RF_CHECKTEXT_SHIP_MYSELF");
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RF_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Submit return request successfully for Resize type with same size option")
    public void testCase_SC_18() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RF_TXT_ORDER_MORE_60DAY",true,"RF_SELECT_TYPE_RESIZING","RF_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RF_TXT_SIZE","RF_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RF_CHECKTEXT_SHIP_FREE");
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RF_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Submit return request successfully for Resize type with different size option")
    public void testCase_SC_19() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RF_TXT_ORDER_MORE_60DAY",true,"RF_SELECT_TYPE_RESIZING","RF_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RF_TXT_SIZE","RF_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RF_CHECKTEXT_SHIP_FREE");
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RF_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Submit return request for Resize type and didn't choose all required field")
    public void testCase_SC_20() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RF_TXT_ORDER_MORE_60DAY",true,"RF_SELECT_TYPE_RESIZING","RF_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RF_TXT_SIZE","RF_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(false,"RF_CHECKTEXT_SHIP_FREE");
        objReturnForm.step3In3Screen(false);
    }
    @Test(description = "Add tracking infomation successfully")
    public void testCase_MR_04() throws InterruptedException {
        objReturnForm.addTrackingInformation("RF_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "not add any tracking infomation")
    public void testCase_MR_05() throws InterruptedException {
        objReturnForm.notAddAnyTrackingInformation("RF_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Edit tracking information successfully")
    public void testCase_MR_06() throws InterruptedException {
        objReturnForm.editTrackingInformation("RF_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Next to Return form,View Detail return,Cancel My Return successfully")
    public void testCase_MR_02_MR_03_MR_07() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RF_TXT_ORDER_MORE_60DAY",true,"RF_SELECT_TYPE_RESIZING","RF_SELECT_TYPE_WITHDRAWAL");
        objReturnForm.updateTypeResizingOrder("RF_TXT_SIZE_L","RF_TXT_RING_SIZE");
        objReturnForm.step2In3Screen(true,"RF_CHECKTEXT_SHIP_FREE");
        objReturnForm.step3In3Screen(true);
        objReturnForm.cancelOrderReturn("RF_ICON_VIEW_DETAIL_ORDER");
    }
}

