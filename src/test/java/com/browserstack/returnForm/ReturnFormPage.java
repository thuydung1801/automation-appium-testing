package com.browserstack.returnForm;


import com.browserstack.checkout.shoppingbag.ShoppingBagPage;
import com.browserstack.common.LoginPage;
import com.microsoft.playwright.S;
import core.BasePage;


import static core.BaseTest.driver;


public class ReturnFormPage extends BasePage {
    private LoginPage objLogin = new LoginPage(this.keyword);
    private ShoppingBagPage objShoppingBagPage = new ShoppingBagPage(this.keyword);
    public ReturnFormPage() {
        super();
    }

    public void goToReturnForm() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_BTN_RETURNS");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.waitForElementNotVisible(10, "RETURN_FORM_LBL_RETURN");
        keyword.click("RETURN_FORM_BTN_RETURN_FORM");
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementVisible("RETURN_FORM_INP_EMAIL_FORM");
    }
    public void inputDataReturnForm(String dataEmail,String dataPassword,boolean checkEmail, String messExpected, String messActual) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(10000);
        keyword.waitForElementNotVisible(60,"//div[@class='loading-mask']");
        keyword.clearText("RETURN_FORM_INP_EMAIL_FORM");
        keyword.sendKeys("RETURN_FORM_INP_EMAIL_FORM", dataEmail);
        Thread.sleep(5000);
        keyword.click("RETURN_FORM_BTN_SUBMIT_RETURN_FORM");
        keyword.untilJqueryIsDone(50L);
        if(checkEmail ==true) {
            keyword.untilJqueryIsDone(50L);
            keyword.clearText("RETURN_FORM_INP_PASSWORD_FORM");
            keyword.sendKeys("RETURN_FORM_INP_PASSWORD_FORM", dataPassword);
            keyword.untilJqueryIsDone(50L);
            keyword.click("RETURN_FORM_BTN_SUBMIT_RETURN_FORM");
        }
        Thread.sleep(5000);
        keyword.untilJqueryIsDone(50L);
        keyword.assertEquals(messExpected, messActual);
    }
    //select type return in DDL
    public void selectOrderReturn(String orderSelected,boolean clickConfirmAddress,String typeSelect,String typeNotShow) throws InterruptedException {
        Thread.sleep(10000);
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(50,"//div[@class='loading-mask']");
        keyword.selectDropDownListByName("RETURN_FORM_DDL_ORDER",orderSelected);
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_BTN_RETURN_ORDER");
        keyword.untilJqueryIsDone(50L);
        //STEP 1/3
        keyword.webDriverWaitForElementPresent("RETURN_FORM_LBL_STEP_1/3",60);
        if(!clickConfirmAddress) {
            keyword.assertEquals("RETURN_FORM_MESSAGE_EXPECTED_NOTE_CONFIRM_ADDRESS","RETURN_FORM_MESSAGE_ACTUAL_NOTE_CONFIRM_ADDRESS");
            keyword.untilJqueryIsDone(50L);
        }
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_CHECKBOX_CONFIRM_ADDRESS");
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementPresent(typeNotShow);
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.selectDropDownListByName("RETURN_FORM_DDL_TYPE_RETURN",typeSelect);
        keyword.untilJqueryIsDone(50L);
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.scrollToPositionByScript("window.scrollBy(0,300)");
        keyword.click("RETURN_FORM_CHECKBOX_CONFIRM_ORDER");
        keyword.untilJqueryIsDone(50L);
    }

    public void updateTypeResizingOrder(String sizeReturn,String sizeOriginal ) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        keyword.click("RETURN_FORM_DRD_SELECT_SIZE");
        keyword.untilJqueryIsDone(30L);
        keyword.click("RETURN_FORM_CHECKBOX_SIZE");
        keyword.scrollToPositionByScript("window.scrollBy(0,-100)");
        String text = keyword.getText(sizeReturn);
        keyword.untilJqueryIsDone(30L);
        String size = keyword.getText(sizeOriginal);
        if (size.contains(text)) {
            keyword.assertEquals("RETURN_FORM_MESS_SELECTED_SAME","RETURN_FORM_MESS_ACTUAL_SELECTED_SAME");
        }
        keyword.untilJqueryIsDone(30L);
    }
    public void updateTypeServiceWarrantyOrder() throws InterruptedException {
        keyword.verifyElementPresent("RETURN_FORM_TXT_DESCRIPTION");
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        keyword.untilJqueryIsDone(30L);
        keyword.sendKeys("RETURN_FORM_TXB_COMMENT", "RETURN_FORM_DATA_COMMENT");
        keyword.untilJqueryIsDone(30L);
        keyword.chooseFile("RETURN_FORM_CHOOSE_IMAGE1", "https://cdn-media.glamira.com/media/product/newgeneration/view/2/sku/MEN6/diamond/diamond-Brillant_AAA/alloycolour/white.jpg?width=800&height=800");
        keyword.untilJqueryIsDone(30L);
        keyword.chooseFile("RETURN_FORM_CHOOSE_IMAGE2", "https://cdn-media.glamira.com/media/product/newgeneration/view/2/sku/MEN6/diamond/diamond-Brillant_AAA/alloycolour/white.jpg?width=800&height=800");
        keyword.untilJqueryIsDone(30L);
        keyword.chooseFile("RETURN_FORM_CHOOSE_IMAGE3", "https://cdn-media.glamira.com/media/product/newgeneration/view/2/sku/MEN6/diamond/diamond-Brillant_AAA/alloycolour/white.jpg?width=800&height=800");
    }
    public void updateTypeWithdrawalOrder() throws InterruptedException {
        keyword.verifyElementPresent("RETURN_FORM_TXT_REASON");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.scrollToPositionByScript("window.scrollBy(0,300)");
        keyword.selectDropDownListByName("RETURN_FORM_DDL_REASON_RETURN", "RETURN_FORM_TXT_REASON_RETURN");
        keyword.untilJqueryIsDone(30L);
        keyword.selectDropDownListByName("RETURN_FORM_DDL_METHOD_PAYMENT", "RETURN_FORM_TXT_METHOD_OPTION");
        keyword.untilJqueryIsDone(50L);
        //getCodeReturn();
    }
    //STEP 2 / 3
    public void step2In3Screen(boolean clickBtnShipFree,String methodShip,boolean clickShipNotRefund) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.scrollToPositionByScript("window.scrollBy(0,300)");
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        clickConfirmConditions(clickBtnShipFree,"RETURN_FORM_MESS_SELECT_SHIP","RETURN_FORM_MESS_ACTUAL_SELECT_SHIP",methodShip);
        keyword.untilJqueryIsDone(50L);
        if(keyword.verifyElementPresent("RETURN_FORM_CHECKBOX_SHIP_FEE")) {
            clickConfirmConditions(clickShipNotRefund, "RETURN_FORM_MESS_SELECT_SHIP","RETURN_FORM_MESS_ACTUAL_SELECT_SHIP" ,"RETURN_FORM_CHECKBOX_SHIP_FEE");
        }
        keyword.untilJqueryIsDone(30L);
    }
    //STEP 3 / 3
    public void step3In3Screen(boolean clickConfirm) throws InterruptedException {
        clickConfirmConditions(clickConfirm,"RETURN_FORM_MESS_SELECT_CONDITIONS","RETURN_FORM_MESS_ACTUAL_SELECT_CONDITIONS","RETURN_FORM_CHECKBOX_TERM_CONDITION");
        keyword.untilJqueryIsDone(30L);
        keyword.click("RETURN_FORM_BTN_SUBMIT");
    }
    public void clickConfirmConditions(boolean clickConfirmConditions, String messExpected, String messActual, String clickCondition) {
        if(!clickConfirmConditions) {
            keyword.assertEquals(messExpected,messActual);

        }
        else {
            keyword.click(clickCondition);
        }
    }
    //cancel my return
    public void cancelOrderReturn(String viewDetail) throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        if (keyword.verifyElementPresent("RETURN_FORM_LINK_CANCEL_ORDER")) {
            keyword.click("RETURN_FORM_LINK_CANCEL_ORDER");
            keyword.untilJqueryIsDone(30L);
        }
        else {
            keyword.untilJqueryIsDone(30L);
            keyword.navigateToUrl("URL_RETURN_ORDER");
            keyword.untilJqueryIsDone(30L);
            keyword.click(viewDetail);
        }
        Thread.sleep(3000);
        keyword.untilJqueryIsDone(30L);
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        keyword.untilJqueryIsDone(50L);
        keyword.scrollToPositionByScript("window.scrollBy(0,500)");
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_BTN_CANCEL_RETURN");
        keyword.untilJqueryIsDone(50L);
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_BTN_OK_CANCEL");
        keyword.untilJqueryIsDone(50L);
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementVisible("RETURN_FORM_TXT_CANCELED");
        keyword.untilJqueryIsDone(30L);
        Thread.sleep(3000);
    }
    public void clickReopen() throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.webDriverWaitForElementPresent("RETURN_FORM_TXT_REOPEN",60);
        keyword.click("RETURN_FORM_TXT_REOPEN");
        keyword.untilJqueryIsDone(30L);
        keyword.webDriverWaitForElementPresent("RETURN_FORM_LBL_STEP_1/3",60);

    }
    public void notAddAnyTrackingInformation(String viewDetailOrder) throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.navigateToUrl("URL_RETURN_ORDER");
        keyword.untilJqueryIsDone(30L);
        keyword.click(viewDetailOrder);
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        keyword.scrollToPositionByScript("window.scrollBy(0,300)");
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_BTN_SAVE_INFO_COURIER");
        keyword.untilJqueryIsDone(50L);
        keyword.assertEquals("RETURN_FORM_MESS_ERROR_CARRIER","RETURN_FORM_MESS_ACTUAL_ERROR_CARRIER");
        keyword.untilJqueryIsDone(50L);
    }
    public void addTrackingInformation(String viewDetailOrder) throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.navigateToUrl("URL_RETURN_ORDER");
        keyword.untilJqueryIsDone(30L);
        keyword.click(viewDetailOrder);
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        keyword.scrollToPositionByScript("window.scrollBy(0,300)");
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementVisible("RETURN_FORM_ICON_INACTIVE_EDIT");
        inputTrackingInformation("RETURN_FORM_TXT_CARRIER_CODE1", "RETURN_FORM_TXB_NUMBER_TRACKING","RETURN_FORM_TXT_NUMBER_TRACKING");
    }
    public void editTrackingInformation(String viewDetailOrder) throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.navigateToUrl("URL_RETURN_ORDER");
        keyword.untilJqueryIsDone(30L);
        keyword.click(viewDetailOrder);
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        keyword.scrollToPositionByScript("window.scrollBy(0,300)");
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_ICON_EDIT_TRACKING_INFO");
        inputTrackingInformation("RETURN_FORM_TXT_CARRIER_CODE2", "RETURN_FORM_TXB_EDIT_NUMBER_TRACKING","RETURN_FORM_TXT_NUMBER_TRACKING");
    }

    public void inputTrackingInformation(String codeCarrier,String inputNumberTracking, String numberTracking) throws InterruptedException {
        keyword.selectDropDownListByName("RETURN_FORM_DDL_CARRIER_CODE", codeCarrier);
        keyword.untilJqueryIsDone(50L);
        keyword.clearText(inputNumberTracking);
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys(inputNumberTracking, numberTracking);
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_BTN_SAVE_INFO_COURIER");
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.assertEquals("RETURN_FORM_MESS_SAVE_CARRIER_SUCCESS","RETURN_FORM_MESS_ACTUAL_ERROR_CARRIER");
    }
    public void editShippingAddress(String stress,String city) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.scrollToPositionByScript("window.scrollBy(0,-300)");
        keyword.waitForElementNotVisible(10, "RETURN_FORM_ICON_EDIT_SHIPPING_ADDRESS");
        keyword.click("RETURN_FORM_ICON_EDIT_SHIPPING_ADDRESS");
        keyword.untilJqueryIsDone(50L);
        keyword.clearText("RETURN_FORM_INP_ADDRESS_STRESS");
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("RETURN_FORM_INP_ADDRESS_STRESS", stress);
        keyword.untilJqueryIsDone(50L);
        keyword.clearText("RETURN_FORM_INP_ADDRESS_CITY");
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("RETURN_FORM_INP_ADDRESS_CITY", city);
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_BTN_SAVE_SHIPPING_ADDRESS");
        keyword.untilJqueryIsDone(50L);
        keyword.assertEquals(stress,"RETURN_FORM_TXT_ACTUAL_INP_ADDRESS_STRESS");
        keyword.untilJqueryIsDone(50L);
        keyword.assertEquals(city,"RETURN_FORM_TXT_ACTUAL_INP_ADDRESS_CITY");
        keyword.untilJqueryIsDone(50L);
        keyword.checkElementIsDisplayed("RETURN_FORM_CHECKBOX_CONFIRM_ADDRESS");
        keyword.checkElementIsNotDisplayed("RETURN_FORM_BTN_SUBMIT_RETURN_FORM_ORDER");
    }
    //Going my return on My account
    public void goToMyReturnOnMyAccount() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_TXT_MY_ACCOUNT");
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_HREF_RETURN_ORDER");
        keyword.untilJqueryIsDone(50L);
    }
    // return form on stage.com
    public void commonReturnFormWithPhoneNumber(String url,String typeLogin) throws InterruptedException {
        keyword.deleteAllCookies();
        keyword.navigateToUrl(url);
        objLogin.acceptAllCookies();
        keyword.click(typeLogin);
    }
    //change shipping label fee(with SKU= G100620)-> free(SKU=Courier)
    public void changeShippingLabel(String sku) throws InterruptedException {
        openNewTab();
        keyword.navigateToUrl("https://stage.glamira.com/secured2021/admin/system_config/edit/section/return/");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        keyword.clearText("BE_TBX_SKU_COURIER");
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("BE_TBX_SKU_COURIER",sku);
        keyword.untilJqueryIsDone(50L);
        keyword.click("BE_BTN_SAVE_CONFIG");
        Thread.sleep(10000);
    }
    //login admin BackEnd
    public void loginAdmin(String userName, String passWord) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("LOGIN_FORM_USER_NAME_BACKEND", userName);
        keyword.sendKeys("LOGIN_FORM_PASSWORD_BACKEND", passWord);
        keyword.click("LOGIN_FORM_BTN_SUBMIT_BACKEND");
    }
    public void openNewTab() throws InterruptedException {
        keyword.openNewTab("BE_URL");
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        Thread.sleep(5000);
        loginAdmin("LOGIN_DATA_USER_NAME_LY","LOGIN_DATA_PASS_WORD_LY");
        Thread.sleep(5000);
    }
    // Change the option Login email <-> phone number( after the error message is displayed)
    public void changeOptionLogin(String dataEmail,String dataPhone,String messEmail, String messActualEmail,String messPhone, String messActualPhone) throws InterruptedException {
        inputDataReturnForm(dataEmail,"RETURN_FORM_DATA_PASSWORD",false,messEmail, messActualEmail);
        keyword.click("RETURN_FORM_BTN_PHONE_NUMBER");
        inputDataReturnForm(dataPhone,"RETURN_FORM_DATA_PASSWORD",false,messPhone, messActualPhone);
    }

}


