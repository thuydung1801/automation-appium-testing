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
    public void inputDataReturnForm(String dataEmail,String dataPassword,boolean checkEmail, boolean checkPassword) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(10000);
        keyword.untilJqueryIsDone(50L);
        keyword.untilJqueryIsDone(50L);
        if (dataEmail==null){
            keyword.click("RETURN_FORM_BTN_SUBMIT_RETURN_FORM");
            keyword.untilJqueryIsDone(50L);
            keyword.assertEquals("RETURN_FORM_MESSAGE_EXPECTED_EMPTY_EMAIL_LOGIN", "RETURN_FORM_MESSAGE_ACTUAL_EMPTY_EMAIL_LOGIN");
        }
        else {
            keyword.sendKeys("RETURN_FORM_INPUT_EMAIL_FORM", dataEmail);
            keyword.untilJqueryIsDone(50L);
            keyword.click("RETURN_FORM_BTN_SUBMIT_RETURN_FORM");
            keyword.untilJqueryIsDone(50L);
            if(checkEmail ==true) {
                keyword.untilJqueryIsDone(50L);
                keyword.untilJqueryIsDone(20L);
                keyword.sendKeys("RETURN_FORM_INPUT_PASSWORD_FORM", dataPassword);
                keyword.click("RETURN_FORM_BTN_SUBMIT_RETURN_FORM");
                keyword.untilJqueryIsDone(50L);
                if(checkPassword == true) {
                    keyword.untilJqueryIsDone(50L);
                    keyword.waitForElementNotVisible(10, "RETURN_FORM_LBL_NEW_RETURN_REQUEST");
                }
                else {
                    keyword.assertEquals("RETURN_FORM_MESSAGE_EXPECTED_INVALID_LOGIN", "RETURN_FORM_MESSAGE_ACTUAL_INVALID_LOGIN");
                }
            }
            else {
                    keyword.assertEquals("RETURN_FORM_MESSAGE_EXPECTED_INVALID_EMAIL_LOGIN", "RETURN_FORM_MESSAGE_ACTUAL_INVALID_LOGIN");
            }

        }

    }
    public void selectOrderReturn(String orderSelected) throws InterruptedException {
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
        Thread.sleep(10000);
        String idOrder=keyword.getText("RETURN_FORM_ID_ORDER_SELECTED");
        getIdOrder.contains(idOrder);
    }
    //Go to step 1/3 screen
    public void selectTypeReturn(String typeReturn,boolean clickConfirmAddress,boolean checkTypeReturn) throws InterruptedException {
        keyword.webDriverWaitForElementPresent("RETURN_FORM_LBL_STEP_1/3",60);
        if(clickConfirmAddress== true){
            keyword.click("RETURN_FORM_CONFIRM_ADDRESS");
            keyword.untilJqueryIsDone(50L);
            keyword.click("RETURN_FORM_SELECT_TYPE_RETURN");
            keyword.untilJqueryIsDone(30L);
            if (keyword.verifyElementVisible(typeReturn) == checkTypeReturn) {
                System.out.println("pass");
                if (checkTypeReturn==true) {
                    keyword.click(typeReturn);
                }
                else {
                    System.out.println("--------");
                }
            }
            else {
                System.out.println("fail");
            }
        }
        else {
            keyword.untilJqueryIsDone(50L);
            keyword.click("RETURN_FORM_SELECT_TYPE_RETURN");
            keyword.untilJqueryIsDone(30L);
            keyword.assertEquals("RETURN_FORM_MESSAGE_EXPECTED_NOTE_CONFIRM_ADDRESS","RETURN_FORM_MESSAGE_ACTUAL_NOTE_CONFIRM_ADDRESS");
        }
    }
    public void editShippingAddress(String stress,String city) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(10000);
        keyword.click("RETURN_FORM_BTN_EDIT_SHIPPING_ADDRESS");
        Thread.sleep(5000);
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
        Thread.sleep(10000);
        keyword.assertEquals(stress,"RETURN_FORM_TXT_ACTUAL_EDIT_ADDRESS_STRESS");
        keyword.untilJqueryIsDone(50L);
        keyword.assertEquals(city,"RETURN_FORM_TXT_ACTUAL_EDIT_ADDRESS_CITY");
    }
    public void confirmAfterEditShippingAddress() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.checkElementIsSelected("RETURN_FORM_CONFIRM_ADDRESS");
        keyword.CheckIsDisplayElement("RETURN_FORM_BTN_SUBMIT_RETURN_FORM_ORDER");
    }

}


