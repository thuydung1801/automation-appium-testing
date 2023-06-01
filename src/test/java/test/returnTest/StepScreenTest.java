package test.returnTest;

import Page.homePage.LoginPage;
import Page.returnPage.LoginScreenPage;
import Page.returnPage.MyReturnPage;
import Page.returnPage.ReturnFormPage;
import core.BaseTest;
import core.LogHelper;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StepScreenTest extends BaseTest {
    private LoginScreenPage loginScreen;
    private LoginPage objLogin;
    private MyReturnPage myReturn;

    private ReturnFormPage returnForm;
   // private static final Logger logger = LogHelper.getLogger();

    public StepScreenTest() {
        super();
    }
    public void setReturnLoginScreen() throws InterruptedException {
        loginScreen = new LoginScreenPage(this.keyword);
        objLogin = new LoginPage(this.keyword);
        myReturn = new MyReturnPage(this.keyword);
        returnForm = new ReturnFormPage(this.keyword);
        loginScreen.goToReturn();
        objLogin.acceptAllCookies();
    }
    public void login() throws InterruptedException{
        setReturnLoginScreen();
        loginScreen.loginOnReturnFormSuccess();
    }
    @Test(priority = 1,description = "Edit shipping address successful")
    public void TestCase_SC01_03() throws InterruptedException {
        login();
        myReturn.goToReturnFormPage();
        Assert.assertEquals(returnForm.getInformOnReturnForm(),returnForm.defaultInformBeforeEdit());
        returnForm.editInformation("input");
        Assert.assertTrue(returnForm.isAddressFormSameAfterEdit(),"Incorrect information after edit");
    }

    @Test(priority = 2,description = "Edit Shipping Address and leave with a blank on the required form")
    public void TestCase_SC05() throws InterruptedException {
        login();
        myReturn.goToReturnFormPage();
        Assert.assertEquals(returnForm.getInformOnReturnForm(),returnForm.defaultInformBeforeEdit());
        returnForm.editInformation("blank");
        Assert.assertTrue(returnForm.isShowWarningMessage(),"Don't display error message");
    }

}
