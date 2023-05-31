package com.browserstack.returnForm;

import com.browserstack.home.LoginPage;
import core.BaseTest;
import core.LogHelper;
import org.slf4j.Logger;
import org.testng.annotations.Test;


public class ReturnFormTest extends BaseTest {
    private static Logger logger = LogHelper.getLogger();
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
//    @Test(priority = 1, description = "Next step with customer invalid")
//    public void testCase_LS_04() throws InterruptedException {
//        setUpLogin();
//        objLoginReturn.dataFormLoginReturnForm("DATA_EMAIL_INVALID", "", true, "DATA_EMAIL_FAIL", "EMAIL_ERROR", "", true);
//    }
//    @Test(priority = 3, description = "Submit Return form, Next step and not input Email")
//    public void testCase_LS_06() throws InterruptedException {
//        objLoginReturn.dataFormLoginReturnForm("", "", true, "SIGNUP_DATA_VERIFY_MESSAGE", "EMAIL_ERROR", "", true);
//    }

    @Test(priority = 1, description = "Next step return form, login with password not matching email customer")
    public void testCase_LS_02() throws InterruptedException {
        commonReturnForm();
        objLoginReturn.inputEmailReturnForm("RETURN_FORM_DATA_EMAIL_RETURN");
        objLoginReturn.inputPasswordReturnForm("RETURN_FORM_DATA_INVALID_PASSWORD_RETURN");
        objLoginReturn.confirmMessage("RETURN_FORM_MESSAGE_EXPECTED_INVALID_LOGIN","RETURN_FORM_MESSAGE-ACTUAL_INVALID_LOGIN");
    }

//    @Test(priority = 5, description = "Submit Return form, login successfully + Input email or order having space")
//    public void testCase_LS_01_03() throws InterruptedException {
////        setUpLogin();
//        objLoginReturn.dataFormLoginReturnForm("DATA_EMAIL_RETURN", "DATA_PASSWORD_LOGIN_RETURN", false, "DATA_LABLE", "STEP_LABLE", "SELECT_ORER_RETURN", true);
//    }
//
//    @Test(priority = 6, description = "The order having order date before >60days")
//    public void testCase_LS_07() throws InterruptedException {
//        setUpLogin();
//        objLoginReturn.orderDate();
//    }
//    @Test(priority = 7, description = "Return order with the order haven't the item avaiable resizing")
//    public void testCase_LS_08() throws InterruptedException {
////        setUpLogin();
//        objLoginReturn.orderNotResize();
//    }
//    @Test(priority = 8, description = "Return order with the order haven't the item avaiable engraving")
//    public void testCase_LS_09() throws InterruptedException {
////        setUpLogin();
//        objLoginReturn.orderNotEngraving();
//    }
//    @Test(priority = 9, description = "Return order with the item has returned")
//    public void testCase_LS_10() throws InterruptedException {
////        setUpLogin();
//        objLoginReturn.orderReturned();
//    }
////    MY RETURN
//    @Test(priority = 10, description = "View Detail return successfully & Cancel My Return successfully on viewDetail")
//    public void testCase_MR_02_O3() throws InterruptedException {
////        setUpLogin();
//        objLoginReturn.CancelMyReturn(true);
//    }
//    @Test(priority = 11, description = "Add tracking infomation successfully")
//    public void testCase_MR_O4() throws InterruptedException {
////        setUpLogin();
//        objLoginReturn.CancelMyReturn(true);
//    }
//    @Test(priority = 12, description = "Click Save button on the return detail and not add any tracking infomation")
//    public void testCase_MR_O5() throws InterruptedException {
////        setUpLogin();
//        objLoginReturn.CancelMyReturn(true);
//    }
//    @Test(priority = 13, description = "Edit tracking infomation successfully")
//    public void testCase_MR_O6() throws InterruptedException {
////        setUpLogin();
//        objLoginReturn.CancelMyReturn(true);
//    }
//
//    @Test(priority = 14, description = "Return form issue with site has login with mobile")
//    public void testCase_Login_return_issue() throws InterruptedException {
////        setUpLogin();
//        objLoginReturn.returnFormIssue();
//    }

}
