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
        objLogin.chooseLanguages();
        objLogin.acceptAllCookies();
        objLoginReturn.goToReturnForm();
    }
    @Test(priority = 1, description = "Next step return form, login with password not matching email customer")
    public void testCase_LS_02() throws InterruptedException {
        commonReturnForm();
        objLoginReturn.inputEmailReturnForm("RETURN_FORM_DATA_EMAIL_RETURN");
        objLoginReturn.inputPasswordReturnForm("RETURN_FORM_DATA_INVALID_PASSWORD_RETURN");
        objLoginReturn.confirmMessage("RETURN_FORM_MESSAGE_EXPECTED_INVALID_LOGIN", "RETURN_FORM_MESSAGE_ACTUAL_INVALID_LOGIN");
    }
    @Test(priority = 2, description = "Next step with customer invalid")
    public void testCase_LS_04() throws InterruptedException {
        commonReturnForm();
        objLoginReturn.inputEmailReturnForm("RETURN_FORM_DATA_INVALID_EMAIL_RETURN");
        objLoginReturn.confirmMessage("RETURN_FORM_MESSAGE_EXPECTED_INVALID_EMAIL_LOGIN", "RETURN_FORM_MESSAGE_ACTUAL_INVALID_LOGIN");
    }
    @Test(priority = 3, description = "Submit Return form, Next step and not input Email")
    public void testCase_LS_06() throws InterruptedException {
        commonReturnForm();
        objLoginReturn.inputEmailReturnForm("RETURN_FORM_DATA_EMPTY_EMAIL_RETURN");
        objLoginReturn.confirmMessage("RETURN_FORM_MESSAGE_EXPECTED_EMPTY_EMAIL_LOGIN", "RETURN_FORM_MESSAGE_ACTUAL_EMPTY_EMAIL_LOGIN");
    }
    @Test(priority = 4, description = "Return order with the order haven't the item available resizing")
    public void testCase_LS_08() throws InterruptedException {
        commonReturnForm();
        objLoginReturn.inputEmailReturnForm("RETURN_FORM_DATA_EMAIL_RETURN");
        objLoginReturn.inputPasswordReturnForm("RETURN_FORM_DATA_PASSWORD_RETURN");
        objLoginReturn.createNewReturnOrder("RETURN_FORM_SELECT_OPTION_WITH_ORDER_NO_RESIZE","RETURN_FORM_SELECT_OPTION_TYPE_RETURN",false);
    }
}

