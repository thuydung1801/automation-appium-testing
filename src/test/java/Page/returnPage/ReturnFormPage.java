package Page.returnPage;

import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import java.util.List;

import static core.BaseTest.driver;

public class ReturnFormPage extends BasePage {
    private static final Logger logger = LogHelper.getLogger();

    public ReturnFormPage(KeywordWeb keywordWeb) {
        super(keywordWeb);
    }

    public boolean isReTurnFormPresent() throws InterruptedException {
        Thread.sleep(5000);
        if (keyword.verifyElementPresent("STEP_1/3")) {
            return true;
        } else {
            return false;
        }
    }
    public String getInformOnReturnForm () throws InterruptedException{
        keyword.scrollToPositionByScript("window.scrollBy(0,100)");
        Thread.sleep(5000);
        keyword.untilJqueryIsDone(50L);
        String firstName = keyword.getText("ORDER_INFORMATION_FIRST_NAME"); //vu
        String lastName = keyword.getText("ORDER_INFORMATION_LAST_NAME"); //cuong
        String street = keyword.getText("ORDER_INFORMATION_STREET"); // GN
        String city = keyword.getText("ORDER_INFORMATION_CITY"); //HN
        String postCode = keyword.getText("ORDER_INFORMATION_POST_CODE"); //12348
        String phoneNumber = keyword.getText("ORDER_INFORMATION_PHONE_NUMBER"); //415-223-1234
        return firstName +","+ lastName +","+ street +","+ city +","+ postCode +","+ phoneNumber;
    }
    public String defaultInformBeforeEdit() throws InterruptedException{
        Thread.sleep(5000);
        keyword.click("EDIT_BTN");
        keyword.scrollToPositionByScript("window.scrollBy(0,100)");
        Thread.sleep(5000);
        keyword.untilJqueryIsDone(50L);
        String firstName = keyword.getText("EDIT_FIRST_NAME"); //vu
        String lastName = keyword.getText("EDIT_LAST_NAME"); //cuong
        String street = keyword.getText("EDIT_STREET"); // GN
        String city = keyword.getText("EDIT_CITY"); //HN
        String postCode = keyword.getText("EDIT_ZIP_CODE"); //12348
        String phoneNumber = keyword.getText("EDIT_TELEPHONE_NUMBER"); //415-223-1234
        Thread.sleep(5000);
        return firstName +","+ lastName +","+ street +","+ city +","+ postCode +","+ phoneNumber;
    }
    public void editInformation(String flag) throws InterruptedException{
        switch (flag) {
            case "input":
                Thread.sleep(5000);
                //keyword.click("EDIT_MR_BTN");
                keyword.sendKeys("EDIT_FIRST_NAME", "NEW_FIRST_NAME");
                keyword.sendKeys("EDIT_LAST_NAME", "NEW_LAST_NAME");
                keyword.sendKeys("EDIT_TELEPHONE_NUMBER", "NEW_TELEPHONE_NUMBER");
                keyword.sendKeys("EDIT_COMPANY", "NEW_COMPANY");
                keyword.sendKeys("EDIT_STREET", "NEW_STREET");
                keyword.scrollToPositionByScript("window.scrollBy(0,50)");
                keyword.untilJqueryIsDone(50L);
                keyword.sendKeys("EDIT_CITY", "NEW_CITY");
                keyword.sendKeys("EDIT_ZIP_CODE", "NEW_POST_CODE");
                keyword.click("SAVE_ADDRESS_BTN");
                break;
            case "blank":
                Thread.sleep(5000);
                //keyword.click("EDIT_MR_BTN");
                keyword.clearText("EDIT_FIRST_NAME");
                keyword.clearText("EDIT_LAST_NAME");
                keyword.clearText("EDIT_TELEPHONE_NUMBER");
                //keyword.clearText("EDIT_COMPANY");
                keyword.clearText("EDIT_STREET");
                keyword.scrollToPositionByScript("window.scrollBy(0,50)");
                keyword.untilJqueryIsDone(50L);
                keyword.clearText("EDIT_CITY");
                keyword.clearText("EDIT_ZIP_CODE");
                break;
        }
    }
    public boolean isShowWarningMessage() throws InterruptedException{
        Thread.sleep(5000);
        String errorFirstName = keyword.getText("WARNING_MESSAGE_FIRST_NAME");
        String errorLastName = keyword.getText("WARNING_MESSAGE_LAST_NAME");
        String errorPhoneNumber = keyword.getText("WARNING_MESSAGE_PHONE_NUMBER");
        String errorStreet = keyword.getText("WARNING_MESSAGE_STREET");
        String errorCity = keyword.getText("WARNING_MESSAGE_CITY");
        String errorZipCode = keyword.getText("WARNING_MESSAGE_ZIP_CODE");
        if((errorFirstName.equals("MESSAGE_ERROR"))&&(errorLastName.equals("MESSAGE_ERROR"))&&(errorPhoneNumber.equals("MESSAGE_ERROR"))
        &&(errorStreet.equals("MESSAGE_ERROR"))&&(errorCity.equals("MESSAGE_ERROR"))&&(errorZipCode.equals("MESSAGE_ERROR"))){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean isAddressFormSameAfterEdit() throws InterruptedException{
        Thread.sleep(5000);
        String firstName = keyword.getText("ORDER_INFORMATION_FIRST_NAME");
        String lastName = keyword.getText("ORDER_INFORMATION_LAST_NAME");
        String street = keyword.getText("ORDER_INFORMATION_STREET");
        String city = keyword.getText("ORDER_INFORMATION_CITY");
        String postCode = keyword.getText("ORDER_INFORMATION_POST_CODE");
        String phoneNumber = keyword.getText("ORDER_INFORMATION_PHONE_NUMBER");
        Thread.sleep(5000);
        if(firstName.equals("NEW_FIRST_NAME") && lastName.equals("NEW_LAST_NAME")
                && street.equals("NEW_STREET")&& city.equals("NEW_CITY") && postCode.equals("NEW_POST_CODE") && phoneNumber.equals("NEW_TELEPHONE_NUMBER")){
            return true;
        }
        else {
            return false;
        }
    }


    public boolean isTypeReturnWithoutEngraving() throws InterruptedException {
        int count = 0;
        Thread.sleep(5000);
        keyword.scrollToPositionByScript("window.scrollBy(0,100)");
        keyword.untilJqueryIsDone(50L);
        keyword.click("SELECT_YOUR_RETURN");
        List<WebElement> elements = keyword.findElements("OPTION_RETURN_TYPE");
        for(int i =1; i < elements.size(); i++){
            if (elements.get(i).getText().contains("Engraving")){ count ++; break;
            }
        }
        if(count == 0){
            return true;
        }
        else {return false;}
    }
    public boolean isTypeReturnWithoutWithdrawal() throws InterruptedException {
        int count = 0;
        Thread.sleep(3000);
        keyword.scrollToPositionByScript("window.scrollBy(0,100)");
        Thread.sleep(3000);
        keyword.click("SELECT_YOUR_RETURN");
        List<WebElement> elements = keyword.findElements("OPTION_RETURN_TYPE");
        for(int i =1; i < elements.size(); i++){
            if (elements.get(i).getText().contains("Withdrawal")){ count ++; break;
            }
        }
        if(count == 0){
            return true;
        }
        else {return false;}
    }

}
