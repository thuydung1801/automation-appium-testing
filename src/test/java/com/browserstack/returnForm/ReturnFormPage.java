package com.browserstack.returnForm;


import com.browserstack.checkout.shoppingbag.ShoppingBagPage;
import com.microsoft.playwright.S;
import core.BasePage;


import static core.BaseTest.driver;


public class ReturnFormPage extends BasePage {
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
    public void inputDataReturnForm(String dataEmail,String dataPassword,boolean checkEmail, String message) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.reLoadPage();
        keyword.untilJqueryIsDone(50L);
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(10000);
        keyword.waitForElementNotVisible(60,"//div[@class='loading-mask']");
        keyword.sendKeys("RETURN_FORM_INP_EMAIL_FORM", dataEmail);
        Thread.sleep(5000);
        keyword.click("RETURN_FORM_BTN_SUBMIT_RETURN_FORM");
        keyword.untilJqueryIsDone(50L);
        if(checkEmail ==true) {
            keyword.untilJqueryIsDone(50L);
            keyword.sendKeys("RETURN_FORM_INP_PASSWORD_FORM", dataPassword);
            keyword.untilJqueryIsDone(50L);
            keyword.click("RETURN_FORM_BTN_SUBMIT_RETURN_FORM");
        }
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementVisible(message);
    }
    //select type return in DDL
    public void selectOrderReturn(String orderSelected,boolean clickConfirmAddress,String typeSelect,String typeNotShow) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(50,"//div[@class='loading-mask']");
        keyword.selectDropDownListByName("RETURN_FORM_DDL_ORDER",orderSelected);
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_BTN_RETURN_ORDER");
        keyword.untilJqueryIsDone(50L);
        //STEP 1/3
        keyword.webDriverWaitForElementPresent("RETURN_FORM_LBL_STEP_1/3",60);
        if(!clickConfirmAddress) {
            keyword.verifyElementVisible("RETURN_FORM_MESS_NOTE_CONFIRM_ADDRESS");
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
            keyword.verifyElementVisible("RETURN_FORM_MESS_SELECTED_SAME");
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
        getCodeReturn();
    }
    //STEP 2 / 3
    public void step2In3Screen(boolean clickBtnShipFree,String methodShip) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.scrollToPositionByScript("window.scrollBy(0,300)");
        keyword.waitForElementNotVisible(10,"//div[@class='loading-mask']");
        keyword.webDriverWaitForElementPresent("RETURN_FORM_CHECKTEXT_SHIP_FREE",60);
        clickConfirmConditions(clickBtnShipFree,"RETURN_FORM_MESS_SELECT_SHIP_FREE",methodShip);
        keyword.untilJqueryIsDone(30L);
    }
    //STEP 3 / 3
    public void step3In3Screen(boolean clickConfirm) throws InterruptedException {
        clickConfirmConditions(clickConfirm,"RETURN_FORM_MESS_SELECT_CONDITIONS","RETURN_FORM_CHECKBOX_TERM_CONDITION");
        keyword.untilJqueryIsDone(30L);
        keyword.click("RETURN_FORM_BTN_SUBMIT");
    }
    public void clickConfirmConditions(boolean clickConfirmConditions, String message, String clickCondition) {
        if(!clickConfirmConditions) {
            keyword.verifyElementPresent(message);
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
        keyword.verifyElementVisible("RETURN_FORM_MESS_ERROR_CARRIER");
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
        keyword.verifyElementVisible("RETURN_FORM_MESS_SAVE_CARRIER_SUCCESS");
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
    public void getCodeReturn() throws InterruptedException {
        String getOrderId = keyword.numberOnly("RETURN_FORM_TXT_ORDER_NUMBER");
        keyword.untilJqueryIsDone(50L);
        String originalWindow = driver.getWindowHandle();
        //open a new tab to login on admin site
        keyword.openNewTab("BE_URL");
        keyword.waitForElementNotVisible(10, "//div[@class='loading-mask']");
        Thread.sleep(5000);
        objShoppingBagPage.loginAdmin("LOGIN_DATA_USER_NAME_LY", "LOGIN_DATA_PASS_WORD_LY");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        keyword.navigateToUrl("https://stage.glamira.com/secured2021/production/manage_request/");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        if (keyword.verifyElementPresent("BE_CHECK_SHOW_INCOMING_MODAL")) {
            keyword.click("BE_BTN_CLOSE_INCOMING");
        }
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(10000);
        keyword.sendKeys("BE_TBX_SEARCH_ORDER_ID_PRODUCT", getOrderId + "\n");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        String getCode = keyword.getText("BE_TXT_CODE_RETURN_PRODUCT");
        Thread.sleep(5000);
        driver.switchTo().defaultContent();
        //keyword.switchToWindowByIndex(1);
        //keyword.switchToTab(1);
//        Set<String> contextView = driver.getWindowHandles();
//        ArrayList<String> s = new ArrayList<String>(contextView);
//        driver.get(s.get(contextView.size()-1));

        Thread.sleep(10000);
        keyword.sendKeys("RETURN_FORM_TBX_INP_CODE_RETURN", getCode);
        keyword.untilJqueryIsDone(50L);
    }


}


