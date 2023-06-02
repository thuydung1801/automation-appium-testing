package com.browserstack.returnForm;

import com.browserstack.home.LoginPage;
import core.BaseTest;
import org.testng.annotations.Test;


public class ReturnFormTest extends BaseTest {
    private LoginPage objLogin;
    private ReturnFormPage objLoginReturn;

    public ReturnFormTest() {
        super();
        objLogin = new LoginPage(this.keyword);
        objLoginReturn = new ReturnFormPage();

    }

    public void commonReturnForm() throws InterruptedException {

        objLogin.acceptAllCookies();
        objLoginReturn.goToReturnForm();
    }
    @Test( description = "Submit Return form, Next step and not input Email")
    public void testCase_LS_06() throws InterruptedException {
        commonReturnForm();
        objLoginReturn.inputDataReturnForm("RETURN_FORM_DATA_EMP_EMAIL","RETURN_FORM_DATA_INVALID_EMAIL",false,"RETURN_FORM_MESSAGE_EMPTY_EMAIL_LOGIN");
    }
    @Test(description = "Next step with customer invalid")
    public void testCase_LS_04() throws InterruptedException {
        //commonReturnForm();
        objLoginReturn.inputDataReturnForm("RETURN_FORM_DATA_INVALID_EMAIL","RETURN_FORM_DATA_INVALID_PASSWORD",false,"RETURN_FORM_MESSAGE_INVALID_EMAIL_LOGIN");
    }
    @Test(description = "Next step with customer valid but don't have any order return avaiable")
    public void testCase_LS_05() throws InterruptedException {
        //commonReturnForm();
        objLoginReturn.inputDataReturnForm("RETURN_FORM_DATA_EMAIL_NO_ORDER","RETURN_FORM_DATA_INVALID_PASSWORD",false,"RETURN_FORM_MESSAGE_INVALID_EMAIL_LOGIN");
    }
   @Test(description = "Input email or order having space")
    public void testCase_LS_03() throws InterruptedException {
        objLoginReturn.inputDataReturnForm("RETURN_FORM_DATA_SPACE_EMAIL","RETURN_FORM_DATA_INVALID_PASSWORD",true,"RETURN_FORM_MESSAGE_INVALID_PASSWORD_LOGIN");
    }
    @Test(description = "Next step return form, login with password not matching email customer")
    public void testCase_LS_02() throws InterruptedException {
        objLoginReturn.navigateToReturnForm();
        objLoginReturn.inputDataReturnForm("RETURN_FORM_DATA_EMAIL","RETURN_FORM_DATA_INVALID_PASSWORD",true,"RETURN_FORM_MESSAGE_INVALID_PASSWORD_LOGIN");
    }
   @Test(description = "Next step return form, login with password not matching email customer")
    public void testCase_LS_01() throws InterruptedException {
        objLoginReturn.navigateToReturnForm();
        objLoginReturn.inputDataReturnForm("RETURN_FORM_DATA_EMAIL","RETURN_FORM_DATA_PASSWORD",true,"RETURN_FORM_LBL_NEW_RETURN_REQUEST");
    }

    @Test( description = "Return order with the order haven't the item available resizing")
    public void testCase_LS_08() throws InterruptedException {
        objLoginReturn.selectOrderReturn("RETURN_FORM_SELECT_ORDER_NO_RESIZE",true,"RETURN_FORM_SELECT_TYPE_RESIZING");

    }
    @Test(description = "Return order with the item has returned")
    public void testCase_LS_10() throws InterruptedException {
        objLoginReturn.selectOrderReturn("RETURN_FORM_SELECT_ORDER_NO_RESIZE",true,"RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
    }
    @Test( description = "Edit Shipping Address with the country haven't state successfully")
    public void testCase_SC_04() throws InterruptedException {
        objLoginReturn.selectOrderReturn("RETURN_FORM_SELECT_ORDER_NO_RESIZE",true,"RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objLoginReturn.editShippingAddress("RETURN_FORM_TXT_EDIT_ADDRESS_STRESS","RETURN_FORM_TXT_EDIT_ADDRESS_CITY");
    }
    @Test( description = "Edit Shipping Address after select return item")
    public void testCase_SC_06() throws InterruptedException {
        objLoginReturn.selectOrderReturn("RETURN_FORM_SELECT_ORDER_NO_RESIZE",true,"RETURN_FORM_SELECT_TYPE_WITHDRAWAL");
        objLoginReturn.editShippingAddress("RETURN_FORM_TXT_EDIT_ADDRESS_STRESS","RETURN_FORM_TXT_EDIT_ADDRESS_CITY");
        objLoginReturn.confirmAfterEditShippingAddress();
    }
    @Test( description = "Select Return type and not checked \"I checked my address and I confirm it.\" form")
    public void testCase_SC_08() throws InterruptedException {
        objLoginReturn.selectOrderReturn("RETURN_FORM_SELECT_ORDER_NO_RESIZE",false,"RETURN_FORM_SELECT_TYPE_WITHDRAWAL");

    }
}

