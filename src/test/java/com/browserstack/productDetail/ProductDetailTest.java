package com.browserstack.productDetail;

import com.browserstack.common.LoginPage;
import com.browserstack.returnForm.ReturnFormPage;
import core.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class ProductDetailTest extends BaseTest {
    private LoginPage objLogin;
    private ProductDetailPage objProductDetail;

    public ProductDetailTest() {
        super();
        objLogin = new LoginPage(this.keyword);
        objProductDetail = new ProductDetailPage(this.keyword);
    }
    @Test (priority = 10,description = "Check the link to the ring-size-guide page from product view ")
    public void testCase_DG_01() throws InterruptedException {
        objProductDetail.optionDimensionGuide();
    }
    @Test (priority = 11,description = "- Click in REQUEST A FREE RING SIZER to enable poup")
    public void testCase_DG_03() throws InterruptedException {
        objProductDetail.optionFindSizeWithInpSuccess();
    }
    @Test (priority = 12,description = "Add to cart a single Ring item - selected ring size")
    public void testCase_RS_01() throws InterruptedException {
        objProductDetail.ringSize1();
    }
//    @Test (priority = 13,description = "Add to cart a single Ring item - don't choose ring size")
//    public void testCase_RS_02() throws InterruptedException {
//        objProductDetail.ringSize2();
//    }
//    @Test (priority = 14,description = "Add to cart a Ring item- Send My Ring in Average Size")
//    public void testCase_RS_03() throws InterruptedException {
//        objProductDetail.ringSize3();
//    }
//    //  @Test (priority = 16,description = "ORDER A FREE RING SIZER - do not enter a value")
//    public void testCase_RS_form_02() throws InterruptedException {
//        objProductDetail.optionFindSizeWithInpNull();
//    }
//    //   @Test (priority = 17, description = "ORDER A FREE RING SIZER - enter wrong email format")
//    public void testCase_RS_form_03() throws InterruptedException {
//        objProductDetail.optionFindSizeWithInpEmailError();
//    }
//    //  @Test (priority = 18, description = "ORDER A FREE RING SIZER - enter correct values")
//    public void testCase_RS_form_01() throws InterruptedException {
//        objProductDetail.optionFindSizeWithInpSuccess();
//    }
    //NEW DESIGN - Product Page

}

