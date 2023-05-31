package com.browserstack.returnForm;

import com.browserstack.home.LoginPage;
import core.BasePage;
import core.LogHelper;
import org.slf4j.Logger;

public class ReturnFormPage extends BasePage {
    private static Logger logger = LogHelper.getLogger();
    public LoginPage objLogin;

    public ReturnFormPage() {
        super();
    }

    public void goToReturnForm() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_BTN_RETURNS");
        keyword.untilJqueryIsDone(50L);
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"RETURN_FORM_LBL_RETURN");
        keyword.click("RETURN_FORM_BTN_RETURN_FORM");
    }

    public void inputEmailReturnForm(String dataEmail) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.reLoadPage();
        keyword.untilJqueryIsDone(50L);
        keyword.clearText("RETURN_FORM_INPUT_EMAIL_FORM");
        keyword.sendKeys("RETURN_FORM_INPUT_EMAIL_FORM", dataEmail);
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_BTN_SUBMIT_RETURN_FORM");
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementVisible("RETURN_FORM_LBL_INPUT_PASSWORD");
    }
    public void inputPasswordReturnForm(String dataPassword) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.untilJqueryIsDone(20L);
        keyword.clearText("RETURN_FORM_INPUT_PASSWORD_FORM");
        keyword.sendKeys("RETURN_FORM_INPUT_PASSWORD_FORM", dataPassword);
        keyword.click("RETURN_FORM_BTN_SUBMIT_RETURN_FORM");
        keyword.untilJqueryIsDone(50L);
    }
    public void confirmMessage(String messageActual, String messageExpected) throws InterruptedException {
        keyword.assertEquals(messageActual,messageExpected);
    }

//    public void orderDate() throws InterruptedException {
//        orderReturn("DATA_EMAIL_RETURN2", "DATA_PASSWORD_LOGIN_RETURN", "CHECK_WITHDRAWAL", "OPTION_RESIZE", "SELECT_OPTION_ORDER_ITEM_RETURN_DATE");
//    }
//
//    public void orderNotResize() throws InterruptedException {
//        orderReturn("DATA_EMAIL_RETURN2", "DATA_PASSWORD_LOGIN_RETURN", "OPTION_RESIZE", "CHECK_WITHDRAWAL", "SELECT_OPTION_ORDER_ITEM_RETURN_NO_RESIZE");
//    }
//
//    public void orderNotEngraving() throws InterruptedException {
//        orderReturn("DATA_EMAIL_RETURN2", "DATA_PASSWORD_LOGIN_RETURN", "OPTION_ENGRAVING", "CHECK_WITHDRAWAL", "SELECT_OPTION_ORDER_ITEM_RETURN_NO_ENGRAVING");
//    }
//
//    public void orderReturned() throws InterruptedException {
//        orderReturn("DATA_EMAIL_RETURN2", "DATA_PASSWORD_LOGIN_RETURN", "OPTION_ENGRAVING", "CHECK_WITHDRAWAL", "SELECT_OPTION_ORDER_ITEM_RETURN");
//    }
//
//    public void CancelMyReturn(boolean checkCancel) throws InterruptedException {
////        reloadAdDeleteCookies();
////        goToFormLoginReturn();
////        dataFormLoginReturnForm("DATA_EMAIL_RETURN2", "DATA_PASSWORD_LOGIN_RETURN", false, "DATA_LABLE", "STEP_LABLE", "", false);
////        viewDetail();
////        if(checkCancel){
////            cancelOrderReturn();
////        }
//        orderReturn("DATA_EMAIL_RETURN2", "DATA_PASSWORD_LOGIN_RETURN", "OPTION_ENGRAVING", "CHECK_WITHDRAWAL", "SELECT_OPTION_ORDER_ITEM_RETURN");
//    }

//    login issue

//    public void returnFormIssue() throws InterruptedException {
//        keyword.navigateToUrl("https://stage.glamira.com/");
//        objRegist.acceptAllCookies();
//        goToFormLoginReturn();
//        if (keyword.verifyElementPresent("EMAIL_USER")) {
//            keyword.untilJqueryIsDone(50L);
//            dataFormLoginReturnForm("DATA_EMAIL_RETURN_PHONE", "DATA_PASSWORD_LOGIN_RETURN_PHONE", false, "DATA_MESSAGE_MISSING", "ACTUAL_MESSAGE_MISSING", "", false);
//        }
//
//    }
//
//    public void reloadAdDeleteCookies() throws InterruptedException {
//        keyword.reLoadPage();
//        keyword.deleteAllCookies();
//        keyword.untilJqueryIsDone(30L);
//        keyword.deleteAllCookies();
//        keyword.reLoadPage();
//        keyword.untilJqueryIsDone(50L);
//        objRegister.acceptAllCookies();
//        keyword.untilJqueryIsDone(30L);
//        if (keyword.verifyElementPresent("CHECK_FORM_LOGIN")) {
//            keyword.untilJqueryIsDone(50L);
//            keyword.click("BTN_CLOSE_MODAL_LOGIN");
//        }
//    }
//
//    public void viewDetail() throws InterruptedException {
//        keyword.untilJqueryIsDone(50L);
//        keyword.verifyElementVisible("NEW_RETURN");
//        String getText = keyword.getText("ORDER_ID_RETURN_SUCCESS");
//        keyword.verifyElementVisible("BTN_VIEW_DETAIL");
//        keyword.click("BTN_VIEW_DETAIL");
//        keyword.untilJqueryIsDone(50L);
//        keyword.verifyElementVisible("FORM_MY_RETURN_DETAIL");
//        keyword.assertEquals("#" + getText, "ORDER_ID_ACTUAL");
//    }
//
//    public void orderReturn(String dataEmail, String dataPassWord, String checkShow, String selectElement, String dataSelect) throws InterruptedException {
//        reloadAdDeleteCookies();
//        goToFormLoginReturn();
//        dataFormLoginReturnForm(dataEmail, dataPassWord, false, "DATA_LABLE", "STEP_LABLE", dataSelect, true);
//        checkShowTitle(checkShow, selectElement);
//    }
//
//    public void checkShowTitle(String checkShow, String selectElement) throws InterruptedException {
//        keyword.untilJqueryIsDone(50L);
//        keyword.click("MAC_MY_ORD_RETURN_STEP1_SELECT1");
//        keyword.untilJqueryIsDone(30L);
//        keyword.waitForElementNotVisible(30, checkShow);
//        keyword.untilJqueryIsDone(30L);
//        selectStep(selectElement);
//        keyword.untilJqueryIsDone(50L);
//        cancelOrderReturn();
//    }
//
//    public void selectStep(String selectElement) throws InterruptedException {
//        keyword.untilJqueryIsDone(50L);
//        keyword.click("MAC_MY_ORD_RETURN_STEP1_CHECKBOX1");
//        keyword.untilJqueryIsDone(30L);
//        keyword.click("MAC_MY_ORD_RETURN_STEP1_SELECT1");
//        keyword.untilJqueryIsDone(30L);
//        keyword.click(selectElement);
//        keyword.untilJqueryIsDone(20L);
//        keyword.click("SELECT_ITEM_RESIZE");
//        if (keyword.verifyElementPresent("MAC_MY_ORD_RETURN_STEP1_SELECT2")) {
//            keyword.untilJqueryIsDone(30L);
//            keyword.click("MAC_MY_ORD_RETURN_STEP1_SELECT2");
//            keyword.untilJqueryIsDone(30L);
//            keyword.click("SELECT_SIZE_RETURN");
//        } else {
//            keyword.untilJqueryIsDone(50L);
//            keyword.click("SELECT_REASON_ORDER_RETURN");
//            keyword.untilJqueryIsDone(30L);
//            keyword.click("ITEM_SELECT_REASON");
//            keyword.untilJqueryIsDone(50L);
//            getCodeReturn();
//            keyword.untilJqueryIsDone(50L);
//            keyword.click("SELECT_METHOD_PAYMENT");
//            keyword.untilJqueryIsDone(50L);
//            keyword.click("SELECT_METHOD_OPTION");
//        }
////        STEP 2 / 3
//        keyword.untilJqueryIsDone(50L);
//        keyword.scrollDownToElement("CHOSE_SHIPPING");
//        keyword.click("CHOSE_SHIPPING");
//        //        STEP3 / 3
//        keyword.untilJqueryIsDone(30L);
//        keyword.click("MAC_MY_ORD_RETURN_STEP3");
//        keyword.untilJqueryIsDone(30L);
//        keyword.click("MAC_MY_ORD_RETURN_SUBMIT");
//        keyword.untilJqueryIsDone(50L);
//        keyword.assertEquals("MESSAGE_RETURNSUCCESS", "INPUT_MESSAGE_SUCCESS");
//    }
//
//    public void cancelOrderReturn() throws InterruptedException {
//        keyword.untilJqueryIsDone(50L);
//        keyword.click("BTN_MYRETUREN");
//        keyword.untilJqueryIsDone(50L);
//        keyword.click("BTN_VIEW_DETAIL_ORDER_RETURN");
//        keyword.untilJqueryIsDone(50L);
//        if (keyword.verifyElementPresent("CLICK_LINK_HEAR")) {
//            keyword.click("CLICK_LINK_HEAR");
//        }
//        keyword.untilJqueryIsDone(30L);
//        keyword.click("BTN_CANCEL_MY_RETURN");
//        keyword.untilJqueryIsDone(30L);
//        keyword.click("BTN_OK_CANCEL");
//        keyword.untilJqueryIsDone(50L);
//        keyword.assertEquals("MESSAGE_CANCEL_SUCCESS", "SIGNUP_CODE_RESEND");
////        keyword.assertEquals("DATA_CANCEL","BTN_NOTE_CANCEL");
//    }
//
//    public void getCodeReturn() throws InterruptedException {
//        String getOrderId = keyword.numberOnly("ORDER_NUMBER");
//        System.out.println("-----------------------------" + getOrderId);
//        keyword.untilJqueryIsDone(50L);
//        objSignIn.openTabBE("ADMIN_URL");
//        keyword.deleteAllCookies();
//        keyword.deleteAllCookies();
//        keyword.reLoadPage();
//        objSignIn.loginAdmin(
//                "LOGIN_DATA_USER_NAME",
//                "LOGIN_DATA_PASS_WORD");
//        keyword.untilJqueryIsDone(30L);
//        if (keyword.verifyElementPresent("CHECK_SHOW_INCOMING_MODAL_BE")) {
//            keyword.click("CLOSE_BTN_INCOMING");
//        }
//        objSignIn.chooseItemCustomer(
//                "ITEM_INVENTORY",
//                "ITEM_INVENTORY",
//                "ITEM_INSTOCK_REQUEST",
//                "ITEM_INSTOCK_REQUEST",
//                "FOEM_INSTOCK"
//        );
//        keyword.untilJqueryIsDone(70L);
//        Thread.sleep(2000);
//        keyword.untilJqueryIsDone(50L);
//        keyword.sendKeys("SEARCH_ORDER_ID", getOrderId + "\n");
//        keyword.untilJqueryIsDone(50L);
//        if (keyword.verifyElementPresent("MESSAGE_NOT_FILTER")) {
//            objSignIn.chooseItemCustomer(
//                    "PRODUCT_OPTION",
//                    "PRODUCT_OPTION",
//                    "SELECT_OPTION_PRODUCT",
//                    "SELECT_OPTION_PRODUCT",
//                    "FORM_MANAGRE_PRODUCT"
//            );
//            keyword.untilJqueryIsDone(50L);
//            keyword.sendKeys("SEARCH_ORDER_ID_PRODUCT", getOrderId + "\n");
//            keyword.untilJqueryIsDone(70L);
//            String getCode = keyword.getText("GET_CODE_RETURN_PRODUCT");
//            System.out.println("-----------------------------" + getCode);
////            keyword.closeTab(1);
//            keyword.switchToTab(0);
//            keyword.untilJqueryIsDone(50L);
//            keyword.sendKeys("INPUT_SEND_CODE_RETURN", getCode);
//        } else {
//            keyword.untilJqueryIsDone(50L);
//            String getCode = keyword.getText("GET_CODE_RETURN");
//            keyword.closeTab(1);
//            keyword.switchToTab(0);
//            keyword.untilJqueryIsDone(50L);
//            keyword.sendKeys("INPUT_SEND_CODE_RETURN", getCode);
//        }
//    }
}
