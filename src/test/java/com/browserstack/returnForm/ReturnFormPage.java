package com.browserstack.returnForm;


import core.BasePage;
public class ReturnFormPage extends BasePage {
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
        keyword.waitForElementNotVisible(10, "RETURN_FORM_LBL_RETURN");
        keyword.click("RETURN_FORM_BTN_RETURN_FORM");
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementVisible("RETURN_FORM_INP_EMAIL_FORM");
    }
    public void inputDataReturnForm(String dataEmail,String dataPassword,boolean checkEmail, String message) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.reLoadPage();
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("RETURN_FORM_INP_EMAIL_FORM", dataEmail);
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_BTN_SUBMIT_RETURN_FORM");
        if(checkEmail ==true) {
            keyword.untilJqueryIsDone(50L);
            keyword.sendKeys("RETURN_FORM_INP_PASSWORD_FORM", dataPassword);
            keyword.untilJqueryIsDone(50L);
            keyword.click("RETURN_FORM_BTN_SUBMIT_RETURN_FORM");
        }
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementVisible(message);
    }
    public void selectOrderReturn(String orderSelected,boolean clickConfirmAddress,String typeSelect,String typeNotShow) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        keyword.selectDropDownListByName("RETURN_FORM_DDL_ORDER",orderSelected);
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_BTN_RETURN_ORDER");
        keyword.untilJqueryIsDone(50L);
        keyword.webDriverWaitForElementPresent("RETURN_FORM_LBL_STEP_1/3",60);
        keyword.selectDropDownListByNameNotDisplayed("RETURN_FORM_DDL_TYPE_RETURN",typeNotShow);
        keyword.reLoadPage();
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        keyword.selectDropDownListByName("RETURN_FORM_DDL_TYPE_RETURN",typeSelect);
        if(!clickConfirmAddress) {
            keyword.untilJqueryIsDone(50L);
            keyword.verifyElementVisible("RETURN_FORM_MES_NOTE_CONFIRM_ADDRESS");
        }
        keyword.click("RETURN_FORM_CHECKBOX_CONFIRM_ADDRESS");
    }
    public void editShippingAddress(String stress,String city) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        keyword.scrollToPositionByScript("window.scrollBy(0,-300)");
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
    public void updateOrder() throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.click("RETURN_FORM_CHECKBOX_CONFIRM_ORDER");
        keyword.untilJqueryIsDone(20L);
        //if select sizering
        if (keyword.verifyElementPresent("RETURN_FORM_LBL_RING_SIZE")) {
            keyword.untilJqueryIsDone(30L);
            keyword.click("RETURN_FORM_DRD_SELECT_SIZE");
            keyword.untilJqueryIsDone(30L);
            keyword.click("RETURN_FORM_CHECKBOX_SIZE");
            keyword.untilJqueryIsDone(30L);
        }
        //select Withdrawal
        else {

        }
        keyword.click("RETURN_FORM_CHECKTEXT_SHIPPING_FREE");
        keyword.untilJqueryIsDone(30L);
        keyword.click("RETURN_FORM_CHECKBOX_TERM_CONDITION");
        keyword.untilJqueryIsDone(30L);
        keyword.click("RETURN_FORM_BTN_SUBMIT");
    }
    public void cancelOrderReturn(String cancelReturn) throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.navigateToUrl("URL_RETURN_ORDER");
        keyword.untilJqueryIsDone(30L);
        keyword.click(cancelReturn);
        keyword.untilJqueryIsDone(30L);
        keyword.click("RETURN_FORM_BTN_CANCEL_RETURN");
    }

}


