package Page.returnPage;

import core.BasePage;
import core.KeywordWeb;
import core.LogHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.util.List;

import static core.BaseTest.driver;

public class MyReturnPage extends BasePage {
    private static final Logger logger = LogHelper.getLogger();
    public MyReturnPage(KeywordWeb keywordWeb){
        super(keywordWeb);
    }

    public void goToReturnFormPage()throws InterruptedException{
        Thread.sleep(6000);
        keyword.click("RETURN_BTN");
    }
    public void chooseReturnNotEngraving() throws InterruptedException{
        Thread.sleep(5000);
        keyword.untilJqueryIsDone(50L);
        keyword.click("SELECT_ORDER");
        Thread.sleep(5000);
        keyword.click("ID_PRODUCT_NOT_ENGRAVING");
        Thread.sleep(5000);
        keyword.click("RETURN_BTN");
    }
    public void chooseReturnOderThan60Day() throws InterruptedException{
        Thread.sleep(5000);
        keyword.untilJqueryIsDone(50L);
        keyword.click("SELECT_ORDER");
        Thread.sleep(5000);
        keyword.click("ID_PRODUCT_HAVE_ENGRAVING");
        keyword.click("RETURN_BTN");
    }
}
