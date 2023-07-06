package com.browserstack.productDetail;

import com.browserstack.common.LoginPage;
import com.browserstack.returnForm.ReturnFormPage;
import core.BaseTest;
import core.PropertiesFile;
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
        objProductDetail.setUp();
        objProductDetail.optionDimensionGuide();
    }
//    @Test (priority = 11,description = "- Click in REQUEST A FREE RING SIZER to enable poup")
//    public void testCase_DG_03() throws InterruptedException {
//        objProductDetail.orderAFreeRingSize("PRODUCT_DETAIL_DATA_CODE",
//                "PRODUCT_DETAIL_DATA_ADDRESS", "PRODUCT_DETAIL_DATA_CITY",true);
//    }
    @Test (priority = 12,description = "Add to cart a single Ring item - selected ring size")
    public void testCase_RS_01() throws InterruptedException {
        objProductDetail.selectRingSize("PRODUCT_DETAIL_CHECKBOX_SIZE");
    }
    @Test (priority = 13,description = "Add to cart a single Ring item - don't choose ring size")
    public void testCase_RS_02() throws InterruptedException {
        objProductDetail.selectRingSize(null);
    }
    @Test (priority = 14,description = "Add to cart a Ring item- Send My Ring in Average Size")
    public void testCase_RS_03() throws InterruptedException {
        objProductDetail.selectRingSize("PRODUCT_DETAIL_CHECKBOX_SIZE_AVG");
    }
    @Test (priority = 16,description = "ORDER A FREE RING SIZER - do not enter a value")
    public void testCase_RS_form_02() throws InterruptedException {
        objProductDetail.orderAFreeRingSize(null,null,null,false);
    }
//    @Test (priority = 18, description = "ORDER A FREE RING SIZER - enter correct values")
//    public void testCase_RS_form_01() throws InterruptedException {
//        objProductDetail.orderAFreeRingSize(Integer.parseInt(PropertiesFile.getPropValue("PRODUCT_DETAIL_DATA_CODE")),
//                "PRODUCT_DETAIL_DATA_ADDRESS", "PRODUCT_DETAIL_DATA_CITY",true);
//    }
}

