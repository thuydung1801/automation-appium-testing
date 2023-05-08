package com.browserstack;

import core.BaseTest;
import core.LogHelper;
import org.slf4j.Logger;
import org.testng.annotations.Test;

public class BStackDemoTest extends BaseTest {
    private CheckoutPage objCheckout = new CheckoutPage(this.keyword);
    public BStackDemoTest(){
        super();
    }
    @Test
    public void addProductToCart() throws Exception {
        // navigate to bstackdemo
        //keyword.navigateToUrl("https://www.glamira.co.uk/glamira-pendant-elsie.html?alloy=red_white-585&stone1=diamond-Brillant");
        driver.get("https://www.glamira.co.uk/glamira-pendant-elsie.html?alloy=red_white-585&stone1=diamond-Brillant");
        objCheckout.acceptAllCookies();


    }
}
