package com.browserstack.review;

import com.browserstack.common.LoginPage;
import core.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ReviewTest extends BaseTest {
    private ReviewPage reviewPage ;
    private LoginPage objLogin;

    public ReviewTest() {
        super();
        reviewPage = new ReviewPage(this.keyword);
        objLogin = new LoginPage(this.keyword);
    }
    public void commonReview(String email, String password) throws InterruptedException {
        Thread.sleep(7000);
        objLogin.acceptAllCookies();
        objLogin.login(email,password);
        keyword.navigateToUrl("URL_REVIEW_ORDER");

    }
    @Parameters("baseURL")
    @Test(priority = 1,description = "Check the \"Write a review\" function when not login")
    public void testCase_WAR_02(String baseURL) throws InterruptedException {
        Thread.sleep(7000);
        objLogin.acceptAllCookies();
        reviewPage.checkWriteAReviewInProductPage(baseURL +"glamira-diamonds-ring-madison-skug100620.html?alloy=white-585&stone1=diamond-Brillant&stone2=diamond-Brillant","NotLogin");
    }
    @Parameters("baseURL")
    @Test(priority = 2,description = "Check the helpful/not helpful function when not login  ")
    public void testCase_MPD_08(String baseURL) throws InterruptedException {
        objLogin.acceptAllCookies();
        reviewPage.checkTheHelpfulFunction(baseURL +"glamira-diamonds-ring-madison-skug100620.html?alloy=white-585&stone1=diamond-Brillant&stone2=diamond-Brillant","REVIEW_ICON_DISLIKE",false);
    }
    @Test( priority = 3,description = "When \"ITEMS TO REVIEW\" tab doesn't have any item to review")
    public void testCase_MPR_05() throws InterruptedException {
        commonReview("LOGIN_DATA_EMAIL_NO_ORDER","LOGIN_DATA_PASSWORD_LY");
        reviewPage.checkOrderDisplayInItemToReview(null);
    }
    @Parameters("baseURL")
    @Test(priority = 4,description = "When \"MY REVIEW\" tab doesn't have any item  reviewed")
    public void testCase_MPR_06(String baseURL) throws InterruptedException {
//        keyword.deleteAllCookies();
//        keyword.openNewTab(baseURL);
        commonReview("LOGIN_DATA_EMAIL_NO_REVIEW","LOGIN_DATA_PASSWORD_LY");
        reviewPage.checkOrderDisplayInMyReview(null);
    }
    @Parameters("baseURL")
    @Test( priority = 5,description = "check if the order is displayed in the \"ITEMS TO REVIEW\" tab")
    public void testCase_MPR_01(String baseURL) throws InterruptedException {
//        keyword.deleteAllCookies();
//        keyword.openNewTab(baseURL);
        commonReview("LOGIN_DATA_EMAIL_LY","LOGIN_DATA_PASSWORD_LY");
        reviewPage.checkOrderDisplayInItemToReview("REVIEW_TXT_NAME_PRODUCT");
    }
    @Test( priority = 6,description = "Check function \"Write A Review\" - do not enter required values")
    public void testCase_MPR_03() throws InterruptedException {
        keyword.navigateToUrl("URL_REVIEW_ORDER");
        reviewPage.writeAReviewWithDataNUll("REVIEW_BTN_WRITE_REVIEW_PRODUCT");
    }
    @Test(priority = 7,description = "Check the entered values when the form is not submitted")
    public void testCase_MPR_04() throws InterruptedException {
        keyword.navigateToUrl("URL_REVIEW_ORDER");
        reviewPage.writeAReviewNotSubmit("REVIEW_BTN_WRITE_REVIEW_PRODUCT");
    }
//    @Test(priority = 8, description = "Check function \"Write A Review\" - Enter all required values for an order with multiple items")
//    public void testCase_MPR_02() throws InterruptedException {
//        keyword.navigateToUrl("URL_REVIEW_ORDER");
//        reviewPage.writeAReviewSuccessfully("REVIEW_BTN_WRITE_REVIEW_PRODUCT");
//    }
    @Test(priority = 9,description = "MY REVIEWS- item displayed after submitting the review form")
    public void testCase_MPR_07() throws InterruptedException {
        keyword.navigateToUrl("URL_REVIEW_ORDER");
        reviewPage.checkOrderDisplayInMyReview("REVIEW_TXT_NAME_PRODUCT_REVIEWED");
    }
//    @Test(priority = 10, description = "Check the display of the created review in the product view")
//    public void testCase_MPD_01() throws InterruptedException {
//        keyword.navigateToUrl("URL_REVIEW_ORDER");
//        reviewPage.checkAReviewCreatedDisplay("REVIEW_TXT_NAME_PRODUCT_REVIEWED");
//    }

    @Test(priority = 11,description = "Check the redirect link to the product view of that item")
    public void testCase_MPR_08() throws InterruptedException {
        reviewPage.checkRedirectLinkToTheProductView("REVIEW_TXT_NAME_PRODUCT_REVIEWED");
    }

    @Parameters("baseURL")
    @Test(priority = 12,description = "Check the \"Write a review\" function in the product view of the item that is not in the Item To review section")
    public void testCase_WAR_03(String baseURL) throws InterruptedException {
       // keyword.openNewTab(baseURL);
        //commonReview("LOGIN_DATA_EMAIL_LY","LOGIN_DATA_PASSWORD_LY");
        reviewPage.checkWriteAReviewInProductPage(baseURL +"glamira-diamonds-ring-olivia-skug100515.html?alloy=white-585&stone1=diamond-Brillant","LoginWithItemNotInReview");
    }
    @Parameters("baseURL")
    @Test(priority = 13,description = "Check the \"Write a review\" function in the product view of the item that in the Item To review section")
    public void testCase_WAR_01(String baseURL) throws InterruptedException {
        reviewPage.checkWriteAReviewInProductPage(baseURL +"glamira-diamonds-ring-madison-skug100620.html?alloy=white-585&stone1=diamond-Brillant&stone2=diamond-Brillant","LoginWithItemInReview");
    }
    @Parameters("baseURL")
    @Test(priority = 14,description = "Check function button SHOW MORE of pages ")
    public void testCase_MPD_03(String baseURL) throws InterruptedException {
        reviewPage.checkNumberReviewDisplay(baseURL +"glamira-diamonds-ring-madison-skug100620.html?alloy=white-585&stone1=diamond-Brillant&stone2=diamond-Brillant");
    }
    @Parameters("baseURL")
    @Test(priority = 15,description = "Check the review translation function ")
    public void testCase_MPD_04(String baseURL) throws InterruptedException {
        reviewPage.checkFunctionTranslation(baseURL +"glamira-diamonds-ring-madison-skug100620.html?alloy=white-585&stone1=diamond-Brillant&stone2=diamond-Brillant");
    }
    @Parameters("baseURL")
    @Test(priority = 16,description = "check lazy load of translation ")
    public void testCase_MPD_05(String baseURL) throws InterruptedException {
        reviewPage.checkLazyLoadTranslation(baseURL +"glamira-diamonds-ring-madison-skug100620.html?alloy=white-585&stone1=diamond-Brillant&stone2=diamond-Brillant");
    }
    @Parameters("baseURL")
    @Test(priority = 17,description = "Check the helpful function when login  ")
    public void testCase_MPD_06(String baseURL) throws InterruptedException {
        reviewPage.checkTheHelpfulFunction(baseURL +"glamira-diamonds-ring-madison-skug100620.html?alloy=white-585&stone1=diamond-Brillant&stone2=diamond-Brillant","REVIEW_ICON_LIKE",true);
    }
    @Parameters("baseURL")
    @Test(priority = 18,description = "Check the not helpful function when login  ")
    public void testCase_MPD_07(String baseURL) throws InterruptedException {
        reviewPage.checkTheHelpfulFunction(baseURL +"glamira-diamonds-ring-madison-skug100620.html?alloy=white-585&stone1=diamond-Brillant&stone2=diamond-Brillant","REVIEW_ICON_DISLIKE",true);
    }
    @Parameters("baseURL")
    @Test(priority = 19,description = "The review content has enough text to display the button  ")
    public void testCase_MPD_02(String baseURL) throws InterruptedException {
        reviewPage.checkFunctionShowMoreOrLessReview(baseURL +"glamira-ring-jane-skug100645.html?alloy=white-585&stone1=diamond-Brillant");
    }
}
