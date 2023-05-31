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
    public void loginOnReturnFormSuccess() throws InterruptedException{
        keyword.sendKeys("EMAIL_TEXT_BOX","EMAIL");
        keyword.click("NEXT_BTN");
        Thread.sleep(7000);
        keyword.verifyElementPresent("PASSWORD_FORM");
        keyword.sendKeys("PASSWORD_TEXT_BOX","PASSWORD");
        keyword.click("LOGIN_BTN");
    }
    public boolean inputEmailWithSpace(){
        keyword.sendKeys("EMAIL_TEXT_BOX","EMAIL_HAVE_SPACE");
        String email = keyword.getText("EMAIL_TEXT_BOX");
        keyword.click("NEXT_BTN");
        if(!email.contains(" ") && keyword.verifyElementPresent("PASSWORD_FORM")){
            return true;
        }
        else {return false;}
    }

    public boolean isReTurnFormPresent() throws InterruptedException{
        Thread.sleep(7000);
        keyword.verifyElementPresent("SELECT_ORDER");
        keyword.click("RETURN_BTN");
        Thread.sleep(7000);
        if(keyword.verifyElementPresent("STEP_1/3")){
            return true;
        }
        else{ return false;}
    }
    public boolean isLoginAccWithoutReturn() throws InterruptedException {
        keyword.sendKeys("EMAIL_TEXT_BOX","EMAIL_WITHOUT_RETURN");
        keyword.click("NEXT_BTN");
        Thread.sleep(7000);
//        keyword.verifyElementPresent("NOT_ORDER_MESSAGE");
        if(keyword.getText("NOT_ORDER_MESSAGE").equals("Your email does not match any order")){
            return true;
        }
        else {return false;}
    }

}
