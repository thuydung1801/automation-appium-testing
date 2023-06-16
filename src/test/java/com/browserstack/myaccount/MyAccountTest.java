package com.browserstack.myaccount;

import com.browserstack.home.LoginPage;
import core.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MyAccountTest extends BaseTest {
    private MyAccountPage myAccountPage;

    private LoginPage loginPage;

    public MyAccountTest() {
        super();
        loginPage = new LoginPage(this.keyword);
        myAccountPage = new MyAccountPage(this.keyword);
    }
    public void setUp(String baseUrl) throws InterruptedException {
        if(baseUrl.equals("https://stage.glamira.com/")){
            keyword.navigateToUrl(baseUrl);
        }
        loginPage.acceptAllCookies();
        loginPage.login("CURRENT_EMAIL","CURRENT_PASSWORD");
        keyword.click("MY_ACCOUNT_BTN");
    }
    @Test(priority = 2, description = "Change PERSONAL INFORMATION ")
    public void testCase_PI_1() throws InterruptedException {
        //setUp(" ");
        myAccountPage.isChangePersonalInform("successfully");
    }
    @Test(priority = 1, description = "Change PERSONAL INFORMATION: forgot enter value first/last name")
    public void testCase_PI_2() throws InterruptedException {
        setUp(" ");
        myAccountPage.isChangePersonalInform("blank");
    }
    @Test(priority = 3, description = "Change email ")
    public void testCase_PI_3() throws InterruptedException {
//        setUp(" ");
        myAccountPage.isChangePersonalInform("successfullyWithEmail");
    }
    @Test(priority = 4, description = "Change password")
    public void testCase_PI_4() throws InterruptedException {
//        setUp(" ");
        myAccountPage.isChangePersonalInform("successfullyWithPass");
    }
    @Test(priority = 5, description = "Delete my account success")
    public void testCase_PI_5() throws InterruptedException {
//        setUp(" ");
        myAccountPage.isDeleteAccount("deleteAccountSuccess");
    }
    @Test(priority = 6, description = "Delete my account unsuccessfully")
    public void testCase_PI_6() throws InterruptedException {   // login with account have orders
        setUp(" ");
        myAccountPage.isDeleteAccount("");

    }
    @Test(priority = 7, description = "Disable mobile text box")
    @Parameters("baseUrl")
    public void testCase_PI_7(String baseUrl) throws InterruptedException {
        setUp(baseUrl);
        myAccountPage.isDisablePhoneNumberTxt();
    }
}
