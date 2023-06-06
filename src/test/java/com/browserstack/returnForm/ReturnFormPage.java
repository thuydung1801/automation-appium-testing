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
        keyword.click("RF_BTN_RETURNS");
        keyword.untilJqueryIsDone(50L);
        keyword.untilJqueryIsDone(50L);
        keyword.waitForElementNotVisible(10, "RF_LBL_RETURN");
        keyword.click("RF_BTN_RETURN_FORM");
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementVisible("RF_INP_EMAIL_FORM");
    }
    public void inputDataReturnForm(String dataEmail,String dataPassword,boolean checkEmail, String message) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.reLoadPage();
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("RF_INP_EMAIL_FORM", dataEmail);
        keyword.untilJqueryIsDone(50L);
        keyword.click("RF_BTN_SUBMIT_RETURN_FORM");
        if(checkEmail ==true) {
            keyword.untilJqueryIsDone(50L);
            keyword.sendKeys("RF_INP_PASSWORD_FORM", dataPassword);
            keyword.untilJqueryIsDone(50L);
            keyword.click("RF_BTN_SUBMIT_RETURN_FORM");
        }
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementVisible(message);
    }
    public void selectOrderReturn(String orderSelected,boolean clickConfirmAddress,String typeSelect,String typeNotShow) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        keyword.selectDropDownListByName("RF_DDL_ORDER",orderSelected);
        keyword.untilJqueryIsDone(50L);
        keyword.click("RF_BTN_RETURN_ORDER");
        keyword.untilJqueryIsDone(50L);
        keyword.webDriverWaitForElementPresent("RF_LBL_STEP_1/3",60);
        keyword.selectDropDownListByNameNotDisplayed("RF_DDL_TYPE_RETURN",typeNotShow);
        keyword.reLoadPage();
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        keyword.selectDropDownListByName("RF_DDL_TYPE_RETURN",typeSelect);
        if(!clickConfirmAddress) {
            keyword.untilJqueryIsDone(50L);
            keyword.verifyElementVisible("RF_MES_NOTE_CONFIRM_ADDRESS");
        }
        keyword.click("RF_CHECKBOX_CONFIRM_ADDRESS");
    }
    public void editShippingAddress(String stress,String city) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        keyword.scrollToPositionByScript("window.scrollBy(0,-300)");
        keyword.click("RF_ICON_EDIT_SHIPPING_ADDRESS");
        keyword.untilJqueryIsDone(50L);
        keyword.clearText("RF_INP_ADDRESS_STRESS");
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("RF_INP_ADDRESS_STRESS", stress);
        keyword.untilJqueryIsDone(50L);
        keyword.clearText("RF_INP_ADDRESS_CITY");
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("RF_INP_ADDRESS_CITY", city);
        keyword.untilJqueryIsDone(50L);
        keyword.click("RF_BTN_SAVE_SHIPPING_ADDRESS");
        keyword.untilJqueryIsDone(50L);
        keyword.assertEquals(stress,"RF_TXT_ACTUAL_INP_ADDRESS_STRESS");
        keyword.untilJqueryIsDone(50L);
        keyword.assertEquals(city,"RF_TXT_ACTUAL_INP_ADDRESS_CITY");
        keyword.untilJqueryIsDone(50L);
        keyword.checkElementIsDisplayed("RF_CHECKBOX_CONFIRM_ADDRESS");
        keyword.checkElementIsNotDisplayed("RF_BTN_SUBMIT_RETURN_FORM_ORDER");
    }
    public void updateOrder() throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.click("RF_CHECKBOX_CONFIRM_ORDER");
        keyword.untilJqueryIsDone(20L);
        //if select sizering
        if (keyword.verifyElementPresent("RF_LBL_RING_SIZE")) {
            keyword.untilJqueryIsDone(30L);
            keyword.click("RF_DRD_SELECT_SIZE");
            keyword.untilJqueryIsDone(30L);
            keyword.click("RF_CHECKBOX_SIZE");
            String text = keyword.getText("RF_CHECKBOX_SIZE");
            String size = keyword.getText("RF_TXT_RING_SIZE");
            if (size.contains(text)) {
                keyword.verifyElementVisible("RF_MES_SELECTED_SAME");
            }
            keyword.untilJqueryIsDone(30L);
        }
        //select Service/Warranty
        else if (keyword.verifyElementPresent("RF_TXT_DESCRIPTION")) {
            keyword.untilJqueryIsDone(30L);
            keyword.sendKeys("RF_TXB_COMMENT", "RF_DATA_COMMENT");
            keyword.untilJqueryIsDone(30L);
            keyword.chooseFile("RF_CHOOSE_IMAGE1","C:\\Users\\ly\\anh");
            keyword.chooseFile("RF_CHOOSE_IMAGE2","C:\\Users\\ly\\anh");
            keyword.chooseFile("RF_CHOOSE_IMAGE3","C:\\Users\\ly\\anh");
        }
        //select Withdrawal
        else if (keyword.verifyElementPresent("RF_TXT_REASON")) {
            keyword.untilJqueryIsDone(50L);
            keyword.selectDropDownListByName("RF_DDL_REASON_RETURN","RF_TXT_REASON_RETURN");
            keyword.untilJqueryIsDone(30L);
            //getCodeReturn();
            keyword.untilJqueryIsDone(50L);
            keyword.selectDropDownListByName("RF_DDL_METHOD_PAYMENT","RF_TXT_METHOD_OPTION");
            keyword.untilJqueryIsDone(50L);
        }
        //STEP 2 / 3
        keyword.scrollDownToElement("RF_CHECKTEXT_SHIPPING_FREE");
        keyword.click("RF_CHECKTEXT_SHIPPING_FREE");
        keyword.untilJqueryIsDone(30L);
        //STEP 3 / 3
        keyword.click("RF_CHECKBOX_TERM_CONDITION");
        keyword.untilJqueryIsDone(30L);
        keyword.click("RF_BTN_SUBMIT");
    }
    public void cancelOrderReturn(String viewDetail) throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.navigateToUrl("URL_RETURN_ORDER");
        keyword.untilJqueryIsDone(30L);
        keyword.click(viewDetail);
        keyword.untilJqueryIsDone(30L);
        keyword.scrollToPositionByScript("window.scrollBy(0,300)");
        keyword.click("RF_BTN_CANCEL_RETURN");
        keyword.untilJqueryIsDone(30L);
        keyword.acceptAlert();
        keyword.untilJqueryIsDone(30L);
        keyword.verifyElementVisible("RF_TXT_CANCEL");
        keyword.verifyElementVisible("RF_TXT_REOPEN");
        keyword.untilJqueryIsDone(30L);
        keyword.click("RF_TXT_REOPEN");
        keyword.untilJqueryIsDone(30L);
        keyword.webDriverWaitForElementPresent("RF_LBL_STEP_1/3",60);
    }
    public void goToReturnOrder() throws InterruptedException {
        keyword.untilJqueryIsDone(30L);
        keyword.click("RF_TXT_MY_ACCOUNT");
        keyword.untilJqueryIsDone(30L);
        keyword.click("RF_HREF_RETURN_ORDER");
        keyword.untilJqueryIsDone(30L);
    }

}


