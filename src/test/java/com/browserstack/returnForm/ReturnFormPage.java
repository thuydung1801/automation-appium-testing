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

    public void inputEmailReturnForm(String dataEmail) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(10000);
        keyword.untilJqueryIsDone(50L);
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("RETURN_FORM_INPUT_EMAIL_FORM", dataEmail);
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_BTN_SUBMIT_RETURN_FORM");
        keyword.untilJqueryIsDone(50L);
    }

    public void inputPasswordReturnForm(String dataPassword) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.untilJqueryIsDone(20L);
        keyword.sendKeys("RETURN_FORM_INPUT_PASSWORD_FORM", dataPassword);
        keyword.click("RETURN_FORM_BTN_SUBMIT_RETURN_FORM");
        keyword.untilJqueryIsDone(50L);
    }

    public void confirmMessage(String messageExpected, String messageActual) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.assertEquals(messageExpected,messageActual);
    }
    public void createNewReturnOrder(String orderSelected,String typeReturn,boolean check) throws InterruptedException{
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(10000);
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10, "RETURN_FORM_LBL_NEW_RETURN_REQUEST");
        keyword.click("RETURN_FORM_SELECT_ORDER_RETURN");
        keyword.click(orderSelected);
        keyword.untilJqueryIsDone(50L);
        String getIdOrder = keyword.getText("RETURN_FORM_SELECT_ORDER_RETURN");
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_BTN_RETURN_ORDER");
        keyword.untilJqueryIsDone(50L);
        keyword.webDriverWaitForElementPresent("RETURN_FORM_LBL_STEP_1/3",60);
        String idOrder=keyword.getText("RETURN_FORM_ID_ORDER_SELECTED");
        getIdOrder.contains(idOrder);
        keyword.click("RETURN_FORM_CONFIRM_ADDRESS");
        keyword.untilJqueryIsDone(50L);
        keyword.click("RETURN_FORM_SELECT_TYPE_RETURN");
        keyword.untilJqueryIsDone(30L);
        if (keyword.verifyElementVisible(typeReturn) == check) {
            System.out.println("pass");
        }
        else {
            System.out.println("fail");
        }


    }
}


