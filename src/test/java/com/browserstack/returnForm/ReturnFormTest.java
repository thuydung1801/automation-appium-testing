package com.browserstack.returnForm;

import com.browserstack.home.LoginPage;
import core.BaseTest;
import org.testng.annotations.Test;


public class ReturnFormTest extends BaseTest {
    private LoginPage objLogin;
    private ReturnFormPage objLoginReturn;

    public ReturnFormTest() {
        super();
    }

    public void commonReturnForm() throws InterruptedException {
        objLogin = new LoginPage(this.keyword);
        objLoginReturn = new ReturnFormPage();
        objLogin.acceptAllCookies();
        objLoginReturn.goToReturnForm();
    }
    @Test( description = "Next step return form, login with password not matching email customer")
    public void testCase_LS_02() throws InterruptedException {
        commonReturnForm();
        objLoginReturn.inputDataReturnForm("RETURN_FORM_DATA_EMAIL_RETURN","RETURN_FORM_DATA_INVALID_PASSWORD_RETURN",true,false);
    }
    @Test( description = "Next step with customer invalid")
    public void testCase_LS_04() throws InterruptedException {
        commonReturnForm();
        objLoginReturn.inputDataReturnForm("RETURN_FORM_DATA_INVALID_EMAIL_RETURN","RETURN_FORM_DATA_INVALID_PASSWORD_RETURN",false,false);
    }
    @Test( description = "Submit Return form, Next step and not input Email")
    public void testCase_LS_06() throws InterruptedException {
        commonReturnForm();
        objLoginReturn.inputDataReturnForm(null,"RETURN_FORM_DATA_INVALID_EMAIL_RETURN",false,false);
    }
    @Test( description = "Return order with the order haven't the item available resizing")
    public void testCase_LS_08() throws InterruptedException {
        commonReturnForm();
        objLoginReturn.inputDataReturnForm("RETURN_FORM_DATA_EMAIL_RETURN","RETURN_FORM_DATA_PASSWORD_RETURN",true,true);
        objLoginReturn.selectOrderReturn("RETURN_FORM_SELECT_OPTION_WITH_ORDER_NO_RESIZE");
        objLoginReturn.selectTypeReturn("RETURN_FORM_SELECT_OPTION_TYPE_RESIZING_RETURN",true,false);
    }
    @Test(description = "Return order with the item has returned")
    public void testCase_LS_10() throws InterruptedException {
        commonReturnForm();
        objLoginReturn.inputDataReturnForm("RETURN_FORM_DATA_EMAIL_RETURN","RETURN_FORM_DATA_PASSWORD_RETURN",true,true);
        objLoginReturn.selectOrderReturn("RETURN_FORM_SELECT_OPTION_WITH_ORDER_NO_RESIZE");
        objLoginReturn.selectTypeReturn("RETURN_FORM_SELECT_OPTION_TYPE_WITHDRAWAL_RETURN",true,false);
    }
    @Test( description = "Edit Shipping Address with the country haven't state successfully")
    public void testCase_SC_04() throws InterruptedException {
        commonReturnForm();
        objLoginReturn.inputDataReturnForm("RETURN_FORM_DATA_EMAIL_RETURN","RETURN_FORM_DATA_PASSWORD_RETURN",true,true);
        objLoginReturn.selectOrderReturn("RETURN_FORM_SELECT_OPTION_WITH_ORDER_NO_RESIZE");
        objLoginReturn.editShippingAddress("RETURN_FORM_TXT_EDIT_ADDRESS_STRESS","RETURN_FORM_TXT_EDIT_ADDRESS_CITY");
    }
    @Test( description = "Edit Shipping Address after select return item")
    public void testCase_SC_06() throws InterruptedException {
        commonReturnForm();
        objLoginReturn.inputDataReturnForm("RETURN_FORM_DATA_EMAIL_RETURN","RETURN_FORM_DATA_PASSWORD_RETURN",true,true);
        objLoginReturn.selectOrderReturn("RETURN_FORM_SELECT_OPTION_WITH_ORDER_NO_RESIZE");
        objLoginReturn.selectTypeReturn("RETURN_FORM_SELECT_OPTION_TYPE_WITHDRAWAL_RETURN",true,true);
        objLoginReturn.editShippingAddress("RETURN_FORM_TXT_EDIT_ADDRESS_STRESS","RETURN_FORM_TXT_EDIT_ADDRESS_CITY");
        objLoginReturn.confirmAfterEditShippingAddress();
    }
    @Test( description = "Select Return type and not checked \"I checked my address and I confirm it.\" form")
    public void testCase_SC_08() throws InterruptedException {
        commonReturnForm();
        objLoginReturn.inputDataReturnForm("RETURN_FORM_DATA_EMAIL_RETURN","RETURN_FORM_DATA_PASSWORD_RETURN",true,true);
        objLoginReturn.selectOrderReturn("RETURN_FORM_SELECT_OPTION_WITH_ORDER_NO_RESIZE");
        objLoginReturn.selectTypeReturn("RETURN_FORM_SELECT_OPTION_TYPE_WITHDRAWAL_RETURN",false,true);
    }
}

