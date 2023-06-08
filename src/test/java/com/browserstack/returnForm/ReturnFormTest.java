package com.browserstack.returnForm;

import com.browserstack.home.LoginPage;
import com.browserstack.home.LoginTest;
import core.BaseTest;
import org.testng.annotations.Test;


public class ReturnFormTest extends BaseTest {
    private LoginPage objLogin;
    private ReturnFormPage objReturnForm;
    private LoginTest objLoginTest;

    public ReturnFormTest() {
        super();
        objLogin = new LoginPage(this.keyword);
        objReturnForm = new ReturnFormPage();

    }

    public void commonReturnForm() throws InterruptedException {

        objLogin.acceptAllCookies();
        objReturnForm.goToReturnForm();
    }
    @Test(priority = 1,description = "Submit Return form, Next step and not input Email")
    public void testCase_LS_06() throws InterruptedException {
        commonReturnForm();
        objReturnForm.inputDataReturnForm("RF_DATA_EMP_EMAIL","RF_DATA_INVALID_EMAIL",false,"RF_MES_EMPTY_EMAIL_LOGIN");
    }
    @Test(priority = 2,description = "Next step with customer invalid")
    public void testCase_LS_04() throws InterruptedException {
        //commonReturnForm();
        objReturnForm.inputDataReturnForm("RF_DATA_INVALID_EMAIL","RF_DATA_INVALID_PASSWORD",false,"RF_MES_INVALID_EMAIL_LOGIN");
    }
    @Test(priority = 3,description = "Next step with customer valid but don't have any order return avaiable")
    public void testCase_LS_05() throws InterruptedException {
        //commonReturnForm();
        objReturnForm.inputDataReturnForm("RF_DATA_EMAIL_NO_ORDER","RF_DATA_INVALID_PASSWORD",false,"RF_MES_INVALID_EMAIL_LOGIN");
    }
   @Test(priority = 4,description = "Input email or order having space")
    public void testCase_LS_03() throws InterruptedException {
       objReturnForm.inputDataReturnForm("RF_DATA_SPACE_EMAIL","RF_DATA_INVALID_PASSWORD",true,"RF_MES_INVALID_PASSWORD_LOGIN");
    }
    @Test(priority = 5,description = "Next step return form, login with password not matching email customer")
    public void testCase_LS_02() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_FORM");
        objReturnForm.inputDataReturnForm("RF_DATA_EMAIL","RF_DATA_INVALID_PASSWORD",true,"RF_MES_INVALID_PASSWORD_LOGIN");
    }
   @Test(priority = 6,description = "Next step return form, login with password not matching email customer")
    public void testCase_LS_01() throws InterruptedException {
       keyword.navigateToUrl("URL_RETURN_FORM");
       objReturnForm.inputDataReturnForm("RF_DATA_EMAIL","RF_DATA_PASSWORD",true,"RF_LBL_NEW_RETURN_REQUEST");
    }

    @Test(priority = 7, description = "Return order with the order haven't the item available resizing")
    public void testCase_LS_SC_08() throws InterruptedException {
        objReturnForm.selectOrderReturn("RF_TXT_ORDER_NO_RESIZE",false,"RF_SELECT_TYPE_WITHDRAWAL","RF_SELECT_TYPE_RESIZING");

    }
    @Test(priority = 8, description = "Return order with the order haven't the item available engraving")
    public void testCase_LS_09() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RF_TXT_ORDER_NO_RESIZE",true,"RF_SELECT_TYPE_WITHDRAWAL","RF_SELECT_TYPE_ENGRAVING");
    }
    @Test(description = "Return order with the item has returned")
    public void testCase_LS_10() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RF_TXT_ORDER_HAS_RETURNED",true,"RF_SELECT_TYPE_RESIZING","RF_SELECT_TYPE_WITHDRAWAL");
    }
    @Test( description = "Edit Shipping Address with the country haven't state successfully")
    public void testCase_SC_04() throws InterruptedException {
        //objReturnForm.selectOrderReturn("RF_TXT_ORDER_NO_RESIZE",true,"RF_SELECT_TYPE_WITHDRAWAL","RF_SELECT_TYPE_RESIZING");
        objReturnForm.editShippingAddress("RF_TXT_INP_ADDRESS_STRESS","RF_TXT_INP_ADDRESS_CITY");
    }
    @Test( priority = 9,description = "Return order with the order > 60days")
    public void testCase_LS_SC_07() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RF_TXT_ORDER_MORE_60DAY",true,"RF_SELECT_TYPE_RESIZING","RF_SELECT_TYPE_WITHDRAWAL");
    }
    @Test( priority = 9,description = "Return order with the order > 60days")
    public void testCase_SC_23() throws InterruptedException {
        keyword.navigateToUrl("URL_RETURN_ORDER");
        objReturnForm.selectOrderReturn("RF_TXT_ORDER_MORE_60DAY",true,"RF_SELECT_TYPE_WARRANTY","RF_SELECT_TYPE_WITHDRAWAL");
    }
    @Test(priority = 10, description = "Submit return request successfully for Resize type")
    public void testCase_SC_09_SC_18_SC_19() throws InterruptedException {
        objReturnForm.updateTypeOrder();
        objReturnForm.step2In3Screen(true);
        objReturnForm.step3In3Screen(true);
    }
    @Test(priority = 10, description = "Submit return request for Resize type and didn't choose all required field")
    public void testCase_SC_20() throws InterruptedException {
        objReturnForm.updateTypeOrder();
        objReturnForm.step2In3Screen(false);
        objReturnForm.step3In3Screen(false);
    }
    @Test(priority = 11, description = "Cancel My Return successfully")
    public void testCase_MR_02_MR_03_MR_07() throws InterruptedException {
        objReturnForm.cancelOrderReturn("RF_ICON_VIEW_DETAIL_ORDER");
    }
    @Test(description = "Next to Return form successfully")
    public void testCase_MR_01() throws InterruptedException {
        objLoginTest.login();
        objReturnForm.goToReturnOrder();
        objReturnForm.selectOrderReturn("RF_TXT_ORDER_NO_RESIZE",true,"RF_SELECT_TYPE_WITHDRAWAL","RF_SELECT_TYPE_RESIZING");
    }

}

