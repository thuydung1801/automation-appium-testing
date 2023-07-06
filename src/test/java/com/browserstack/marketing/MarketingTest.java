package com.browserstack.marketing;

import com.browserstack.common.LoginPage;
import core.BaseTest;
import org.testng.annotations.Test;

public class MarketingTest extends BaseTest {
    private MarketingPage testMarketing;
    private LoginPage loginPage;
    public MarketingTest() {
        super();
        testMarketing = new MarketingPage(this.keyword);
        loginPage = new LoginPage(this.keyword);
    }
    public void common() throws InterruptedException {
        loginPage.acceptAllCookies();
    }
    @Test
//            (priority = 1, description = "Add to cart a new Gift card ")
    public void testCase_GC_03() throws InterruptedException {
        testMarketing.giftCardInpWithNull();
    }
    @Test
//            (priority = 2)
    public void testCase_GC_04() throws InterruptedException {
        testMarketing.giftCardInpWithEmailAndAmountError();
    }
    @Test
//            (priority = 3)
    public void testCase_GC_01() throws InterruptedException {
        testMarketing.giftCardWithAmount();
    }
    @Test
//            (priority = 4)
    public void testCase_GC_02() throws InterruptedException {
        testMarketing.giftCardWithOtherAmount();
    }
    @Test
//            (priority = 5, description = "Create New Grift Cetificate")
    public void testCase_GCE_01() throws InterruptedException {
        testMarketing.createNewGriftCetificateFormMyOrder();
    }
    @Test
//            (priority = 6)
    public void testCase_GCE_06() throws InterruptedException {
        testMarketing.createNewCetificateWithOptionNO();
    }
    @Test(priority = 7)
    public void testCase_GCE_05() throws InterruptedException {
        testMarketing.createNewCetificateWithOptionYES();
    }
}
