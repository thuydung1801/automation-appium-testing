package com.browserstack.review;

import com.browserstack.common.LoginPage;
import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import core.PropertiesFile;
import org.slf4j.Logger;
import org.testng.Assert;

import java.util.Date;


public class ReviewPage extends BasePage {


    public LoginPage objLogin=new LoginPage(this.keyword);;

    private static Logger logger = LogHelper.getLogger();

    public ReviewPage() {
        super();
    }
    public ReviewPage(KeywordWeb key){
        super(key);
    }
    //check the order is displayed in the "ITEMS TO REVIEW" tab
    public void checkOrderDisplayInItemToReview(String orderComplete) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        if(orderComplete==null) {
            keyword.assertEquals("REVIEW_DATA_EMPTY_ORDER","REVIEW_TXT_EMPTY_ORDER");
        }
        else {
            keyword.click("REVIEW_BTN_ITEM_TO_REVIEW");
            keyword.untilJqueryIsDone(50L);
            keyword.verifyElementVisible(orderComplete);
            String getStone= keyword.getText("REVIEW_TXT_STONE_PRODUCT");
            String stone = getStone.substring(7,getStone.length());
            String getColor= keyword.getText("REVIEW_TXT_COLOR_PRODUCT");
            String color = getColor.substring(16,getColor.length());
            keyword.assertEquals("REVIEW_DATA_NAME_PRODUCT", "REVIEW_TXT_NAME_PRODUCT");
            logger.info("check verify stone product....");
            Assert.assertEquals(stone,PropertiesFile.getPropValue("REVIEW_DATA_STONE_PRODUCT_REVIEWED"));
            logger.info("check verify color product....");
            Assert.assertEquals(color,PropertiesFile.getPropValue("REVIEW_DATA_COLOR_PRODUCT_REVIEWED"));
        }
    }

    public void writeAReviewWithDataNUll(String order) throws InterruptedException {
        writeAReview(order,null, null, null,null,null,null,null);
        keyword.scrollToPositionByScript("window.scrollBy(0,300)");
        keyword.click("REVIEW_BTN_SUBMIT_REVIEW");
        keyword.untilJqueryIsDone(50L);
        keyword.assertEquals("REVIEW_DATA_MESS_CHOOSE_RATING","REVIEW_MESS_CHOOSE_RATING");
        keyword.assertEquals("REVIEW_DATA_MESS_CHOOSE","REVIEW_MESS_INPUT_TITLE");
        keyword.assertEquals("REVIEW_DATA_MESS_CHOOSE","REVIEW_MESS_INPUT_REVIEW");
        keyword.assertEquals("REVIEW_DATA_MESS_CHOOSE","REVIEW_MESS_CHOOSE_TERM");
        keyword.assertEquals("REVIEW_DATA_MESS_CHOOSE","REVIEW_MESS_CHOOSE_POLICY");
        keyword.assertEquals("REVIEW_DATA_MESS_CHOOSE","REVIEW_MESS_CHOOSE_RECAPTCHA");
    }
    public void writeAReviewSuccessfully(String order) throws InterruptedException {
        writeAReview(order,"REVIEW_INP_SCORE", "REVIEW_DATA_TITLE","REVIEW_DATA_REVIEW",
                "C:\\Users\\Admin\\Desktop\\linh tinh\\anh.jpeg",
                "REVIEW_INP_AGREE_TERM","REVIEW_INP_AGREE_POLICY","REVIEW_CHECKBOX_RECAPTCHA");
        keyword.scrollToPositionByScript("window.scrollBy(0,300)");
        keyword.click("REVIEW_BTN_SUBMIT_REVIEW");
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(3000);
    }
    //check the order is displayed in the "MY REVIEW" tab
    public void checkOrderDisplayInMyReview(String order) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.click("REVIEW_BTN_MY_REVIEW");
        keyword.untilJqueryIsDone(50L);
        if(order==null) {
            keyword.assertEquals("REVIEW_DATA_EMPTY_REVIEW","REVIEW_TXT_EMPTY_REVIEW");
        }
        else {
            keyword.untilJqueryIsDone(50L);
            keyword.verifyElementVisible(order);
            keyword.verifyElementVisible("REVIEW_PROP_RATING_VALUE");
            keyword.assertEquals("REVIEW_DATA_NAME_PRODUCT", "REVIEW_TXT_NAME_PRODUCT_REVIEWED");
            keyword.assertEquals("REVIEW_DATA_STONE_PRODUCT_REVIEWED", "REVIEW_TXT_STONE_PRODUCT_REVIEWED");
            keyword.assertEquals("REVIEW_DATA_COLOR_PRODUCT_REVIEWED", "REVIEW_TXT_COLOR_PRODUCT_REVIEWED");
        }
    }
    //Check the display of the created review in the product view
//    public void checkAReviewCreatedDisplay(String order) throws InterruptedException {
//        keyword.click("REVIEW_BTN_MY_REVIEW");
//        String getDate =keyword.getText("REVIEW_TXT_DATE_MY_REVIEW");
//        System.out.println(getDate);
//        Thread.sleep(3000);
//        approveAReview();
//        checkRedirectLinkToTheProductView(order);
//        keyword.assertEquals(getDate,"REVIEW_TXT_DATE_REVIEW_PRODUCT_PAGE");
//
//    }
    //go to BE Approve review
//    public void approveAReview() throws InterruptedException {
//        objLogin.openTabBE( "URL_BE_STAGE");
//        objLogin.loginAdmin("LOGIN_DATA_USER_NAME_LY","LOGIN_DATA_PASS_WORD_DUNG");
//        keyword.navigateToUrl("https://stage.glamira.com/secured2021/review/product/index/");
//        keyword.untilJqueryIsDone(50L);
//        Thread.sleep(3000);
//        keyword.clearText("BE_TBX_SEARCH_TITLE_REVIEW");
//        keyword.sendKeys("BE_TBX_SEARCH_TITLE_REVIEW","REVIEW_DATA_TITLE");
//        keyword.pressEnter();
//        keyword.untilJqueryIsDone(50L);
//        Thread.sleep(3000);
//        keyword.click("BE_BTN_EDIT");
//        keyword.untilJqueryIsDone(50L);
//        Thread.sleep(3000);
//        keyword.selectDropDownListByName("BE_DRP_STATUS_REVIEW","BE_TXT_STATUS_APPROVE_REVIEW");
//        keyword.click("BE_BTN_SAVE_REVIEW");
//        keyword.closeWindowByIndex(1);
//        keyword.switchToTab(0);
//    }

    public void writeAReviewNotSubmit(String order) throws InterruptedException {
        writeAReview(order,"REVIEW_INP_SCORE", "REVIEW_DATA_TITLE","REVIEW_DATA_REVIEW",
                "C:\\Users\\Admin\\Desktop\\linh tinh\\anh.jpeg",
                "REVIEW_INP_AGREE_TERM","REVIEW_INP_AGREE_POLICY","REVIEW_CHECKBOX_RECAPTCHA");
        keyword.click("REVIEW_BTN_CLOSE_REVIEW");
        keyword.untilJqueryIsDone(50L);
        keyword.click(order);
        keyword.untilJqueryIsDone(50L);
        keyword.assertEquals("REVIEW_DATA_EMPTY","REVIEW_INP_TITLE");
        keyword.assertEquals("REVIEW_DATA_EMPTY","REVIEW_INP_REVIEW");
    }
    public void writeAReview(String order,String chooseRating,String dataTitle,String dataReview,
                             String pathPhoto,String chooseTerm,String choosePolicy, String chooseRecaptcha) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.click(order);
        keyword.untilJqueryIsDone(50L);
        keyword.verifyElementPresent("REVIEW_LBL_LEAVE_A_FEEDBACK");
        checkClickField(chooseRating);
        String timestamp = new java.text.SimpleDateFormat("ddHHmmss").format(new Date());
        dataTitle = "beautiful" +timestamp ;
        keyword.untilJqueryIsDone(10L);
        PropertiesFile.serPropValue("REVIEW_DATA_TITLE", dataTitle);
        checkInputField("REVIEW_INP_TITLE", dataTitle);
        checkInputField("REVIEW_INP_REVIEW",dataReview);
        checkChooseFile("REVIEW_CHOOSE_IMAGE1", pathPhoto);
        checkChooseFile("REVIEW_CHOOSE_IMAGE2", pathPhoto);
        checkChooseFile("REVIEW_CHOOSE_IMAGE3",pathPhoto);
        checkClickField(chooseTerm);
        checkClickField(choosePolicy);
        keyword.switchToIFrameByXpath("REVIEW_IFRAME_RECAPTCHA");
        checkClickField(chooseRecaptcha);
        keyword.switchToDefaultContent();
    }
    public void checkClickField (String field) {
        if(field!=null) {
            keyword.click(field);
        }
    }
    public void checkInputField ( String field,String content) {
        if(content!=null) {
            keyword.clearText(field);
            keyword.sendKeys(field, content);
        }
    }
    public void checkChooseFile( String field,String xpath) {
        if(xpath!=null) {
            keyword.chooseFile(field,xpath);
        }
    }

    public void checkRedirectLinkToTheProductView(String order) throws InterruptedException {
        keyword.untilJqueryIsDone(50L);
        keyword.click(order);
        keyword.untilJqueryIsDone(50L);
        keyword.assertEquals("REVIEW_DATA_NAME_PRODUCT","REVIEW_TXT_TITLE_PRODUCT_PAGE");
        String getStone= keyword.getText("REVIEW_TXT_STONE_PRODUCT_PAGE");
        String stone = getStone.substring(1,getStone.length()-6);
        logger.info("check verify stone product....");
        Assert.assertEquals(stone,PropertiesFile.getPropValue("REVIEW_DATA_STONE_PRODUCT_REVIEWED"));
        String getColor= keyword.getText("REVIEW_TXT_COLOR_PRODUCT_PAGE");
        String color = getColor.substring(1,getColor.length()-1);
        logger.info("check verify color product....");
        Assert.assertEquals(color,PropertiesFile.getPropValue("REVIEW_DATA_COLOR_PRODUCT_REVIEWED"));
    }
    public void goToReviewInProductPage(String urlProduct) throws InterruptedException {
        keyword.navigateToUrl(urlProduct);
        objLogin.acceptAllCookies();
        keyword.untilJqueryIsDone(50L);
        keyword.scrollToPositionByScript("window.scrollBy(0,9000)");
        Thread.sleep(10000);
        keyword.verifyElementPresent("REVIEW_BTN_WRITE_A_REVIEW");
    }
    public void checkWriteAReviewInProductPage(String urlProduct,String condition) throws InterruptedException {
        goToReviewInProductPage(urlProduct);
        keyword.click("REVIEW_BTN_WRITE_A_REVIEW");
        keyword.untilJqueryIsDone(50L);
        switch (condition) {
            case "NotLogin":
                Thread.sleep(5000);
                keyword.assertEquals("LOGIN_DATA_LBL_LOGIN","LOGIN_LBL_LOGIN");
                break;
            case "LoginWithItemNotInReview":
                keyword.assertEquals("REVIEW_DATA_MY_PRODUCT_REVIEW", "REVIEW_TXT_MY_PRODUCT_REVIEW");
                break;
            case "LoginWithItemInReview":
                keyword.assertEquals("REVIEW_DATA_LEAVE_A_FEEDBACK", "REVIEW_LBL_LEAVE_A_FEEDBACK");
                break;
        }
    }
    public void checkNumberReviewDisplay(String urlProduct) throws InterruptedException {
        goToReviewInProductPage(urlProduct);
        int count =keyword.countNumberOfElement("REVIEW_TXT_TITLE_REVIEW");
        boolean page1 = (count > 3) ?false:true;
        logger.info("check verify number review display than 3....");
        Assert.assertEquals(page1,true);
        if(keyword.verifyElementPresent("REVIEW_BTN_SHOW_MORE")) {
            keyword.click("REVIEW_BTN_SHOW_MORE");
            keyword.untilJqueryIsDone(50L);
            int count2 =keyword.countNumberOfElement("REVIEW_TXT_TITLE_REVIEW");
            boolean page2 = (count2 > 10) ?false:true;
            logger.info("check verify number review display than 10....");
            Assert.assertEquals(page2,true);
        }
    }
    public void checkFunctionTranslation(String urlProduct) throws InterruptedException {
        goToReviewInProductPage(urlProduct);
        String text= keyword.getText("REVIEW_TXT_TRANSLATION");
        if(text.equalsIgnoreCase("Show original language")){
            keyword.click("REVIEW_BTN_TRANSLATION");
            Thread.sleep(5000);
            keyword.assertEquals("REVIEW_DATA_TRANSLATE_REVIEW","REVIEW_TXT_TRANSLATION");
        }
        else {
            keyword.click("REVIEW_BTN_TRANSLATION");
            Thread.sleep(10000);
            keyword.assertEquals("REVIEW_DATA_SHOW_ORIGINAL_LANGUAGE","REVIEW_TXT_TRANSLATION");
        }
    }
    public void checkLazyLoadTranslation(String urlProduct) throws InterruptedException {
        goToReviewInProductPage(urlProduct);
        logger.info("compare text Review display Translate review!");
        Assert.assertEquals(keyword.getTextRandomElement("REVIEW_TXT_TRANSLATION"),PropertiesFile.getPropValue("REVIEW_DATA_TRANSLATE_REVIEW"));
        keyword.scrollToPositionByScript("window.scrollBy(0,-100)");
        keyword.scrollToPositionByScript("window.scrollBy(0,100)");
        logger.info("compare text Review display show original language!");
        Assert.assertEquals(keyword.getTextRandomElement("REVIEW_TXT_TRANSLATION"),PropertiesFile.getPropValue("REVIEW_DATA_SHOW_ORIGINAL_LANGUAGE"));
    }
    public void checkFunctionShowMoreOrLessReview(String urlProduct) throws InterruptedException {
        goToReviewInProductPage(urlProduct);
        keyword.verifyElementPresent("REVIEW_BTN_SHOW_MORE_TEXT");
        keyword.click("REVIEW_BTN_SHOW_MORE_TEXT");
        keyword.verifyElementPresent("REVIEW_BTN_SHOW_LESS_TEXT");
        keyword.click("REVIEW_BTN_SHOW_LESS_TEXT");
    }
    public void checkTheHelpfulFunction(String urlProduct,String icon, boolean login) throws InterruptedException {
        goToReviewInProductPage(urlProduct);
        int count = Integer.parseInt(keyword.getText(icon));
        System.out.println(count);
        keyword.click(icon);
        keyword.untilJqueryIsDone(50L);
        Thread.sleep(5000);
        if(!login) {
            int count2= Integer.parseInt(keyword.getText(icon));
            logger.info("compare count before and after click like!");
            Assert.assertEquals(count,count2);
            Thread.sleep(5000);
            keyword.assertEquals("REVIEW_DATA_PLEASE_LOGIN", "REVIEW_POPUP_PLEASE_LOGIN");
        }
        else {
            int count2= Integer.parseInt(keyword.getText(icon));
            logger.info("compare count before and after click like!");
            Assert.assertEquals(count,count2-1);
        }
    }
}
