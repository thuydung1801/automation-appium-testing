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
}


