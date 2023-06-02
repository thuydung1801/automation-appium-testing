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
        keyword.verifyElementVisible("RETURN_FORM_INPUT_EMAIL_FORM");
    }
    public void inputDataReturnForm(String dataEmail,String dataPassword,boolean checkEmail, String message) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.reLoadPage();
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("RETURN_FORM_INPUT_EMAIL_FORM", dataEmail);
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_BTN_SUBMIT_RETURN_FORM");
        if(checkEmail ==true) {
            keyword.untilJqueryIsDone(50L);
            keyword.sendKeys("RETURN_FORM_INPUT_PASSWORD_FORM", dataPassword);
            keyword.untilJqueryIsDone(50L);
            keyword.click("RETURN_FORM_BTN_SUBMIT_RETURN_FORM");
        }
        confirmMessage(message);
    }
    public void selectOrderReturn(String orderSelected,boolean clickConfirmAddress,String typeReturn) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        keyword.selectDropDownListByName("RETURN_FORM_SELECT_ORDER",orderSelected);
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_BTN_RETURN_ORDER");
        keyword.untilJqueryIsDone(50L);
        keyword.webDriverWaitForElementPresent("RETURN_FORM_LBL_STEP_1/3",60);
        keyword.selectDropDownListByName("RETURN_FORM_SELECT_TYPE_RETURN",typeReturn);
        if(clickConfirmAddress== false) {
            confirmMessage("RETURN_FORM_MESSAGE_NOTE_CONFIRM_ADDRESS");
        }
        keyword.click("RETURN_FORM_CONFIRM_ADDRESS");
    }


    public void editShippingAddress(String stress,String city) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        keyword.click("RETURN_FORM_BTN_EDIT_SHIPPING_ADDRESS");
        keyword.untilJqueryIsDone(50L);
        keyword.clearText("RETURN_FORM_EDIT_ADDRESS_STRESS");
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("RETURN_FORM_EDIT_ADDRESS_STRESS", stress);
        keyword.untilJqueryIsDone(50L);
        keyword.clearText("RETURN_FORM_EDIT_ADDRESS_CITY");
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("RETURN_FORM_EDIT_ADDRESS_CITY", city);
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_BTN_SAVE_SHIPPING_ADDRESS");
        keyword.untilJqueryIsDone(50L);
        keyword.assertEquals(stress,"RETURN_FORM_TXT_ACTUAL_EDIT_ADDRESS_STRESS");
        keyword.untilJqueryIsDone(50L);
        keyword.assertEquals(city,"RETURN_FORM_TXT_ACTUAL_EDIT_ADDRESS_CITY");
    }
    public void confirmAfterEditShippingAddress() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.checkElementIsSelected("RETURN_FORM_CONFIRM_ADDRESS");
        keyword.checkIsDisplayElement("RETURN_FORM_BTN_SUBMIT_RETURN_FORM_ORDER");
    }
    public void confirmMessage(String messages) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.imWait(10);
        keyword.verifyElementVisible(messages);
    }
    public void navigateToReturnForm(){
        keyword.navigateToUrl("URL_RETURN_FORM");
    }

}


