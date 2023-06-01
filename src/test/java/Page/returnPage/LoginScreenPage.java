package Page.returnPage;

import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import org.slf4j.Logger;
import org.testng.Assert;

public class LoginScreenPage extends BasePage {
    private static final Logger logger = LogHelper.getLogger();
    public LoginScreenPage(KeywordWeb keywordWeb){
        super(keywordWeb);
    }

    public void goToReturn(){
        keyword.navigateToUrl("https://stage.glamira.co.uk/return");
    }
    public void openNewTabs() throws InterruptedException {
        keyword.executeJavaScript("window.open()");
        keyword.switchToTab(1);
        keyword.maximizeWindow();
        keyword.navigateToUrl("ADMIN_URL");
    }
    public void loginAdmin() throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.sendKeys("LOGIN_FORM_USER_NAME_BACKEND", "ACCOUNT_BE");
        keyword.sendKeys("LOGIN_FORM_PASSWORD_BACKEND", "PASS_BE");
        keyword.click("LOGIN_FORM_BTN_SUBMIT_BACKEND");
    }
    public void loginOnReturnFormSuccess() throws InterruptedException{
        keyword.sendKeys("EMAIL_TEXT_BOX","EMAIL");
        keyword.click("NEXT_BTN");
        Thread.sleep(3000);
        keyword.verifyElementPresent("PASSWORD_FORM");
        keyword.sendKeys("PASSWORD_TEXT_BOX","PASSWORD");
        keyword.click("LOGIN_BTN");
    }
    public boolean inputEmailWithSpace(){
        keyword.sendKeys("EMAIL_TEXT_BOX","EMAIL_HAVE_SPACE");
//        String email = keyword.getText("EMAIL_TEXT_BOX");
        keyword.click("NEXT_BTN");
        if(keyword.verifyElementPresent("PASSWORD_FORM")){
            return true;
        }
        else {return false;}
    }

    public boolean isReTurnFormPresent() throws InterruptedException{
        Thread.sleep(5000);
        keyword.verifyElementPresent("SELECT_ORDER");
        keyword.click("RETURN_BTN");
        Thread.sleep(5000);
        if(keyword.verifyElementPresent("STEP_1/3")){
            return true;
        }
        else{ return false;}
    }
    public boolean isLoginAccWithoutReturn() throws InterruptedException {
        keyword.sendKeys("EMAIL_TEXT_BOX","EMAIL_WITHOUT_RETURN");
        keyword.click("NEXT_BTN");
        Thread.sleep(5000);
        if(keyword.getText("NOT_ORDER_MESSAGE").equals("Your email does not match any order")){
            return true;
        }
        else {return false;}
    }

}
