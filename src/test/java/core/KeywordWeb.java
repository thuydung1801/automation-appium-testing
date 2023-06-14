package core;

import lombok.var;
import org.im4java.core.IM4JavaException;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.TouchAction;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.*;

import org.slf4j.Logger;
import org.testng.Assert;
import org.openqa.selenium.remote.LocalFileDetector;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static core.BaseTest.driver;
import static core.BaseTest.jse;
import static java.awt.SystemColor.window;
import static java.sql.DriverManager.getDriver;
import static java.sql.DriverManager.getDrivers;
import static org.openqa.selenium.Keys.SHIFT;
import static org.openqa.selenium.Keys.TAB;


public class KeywordWeb {
    private static Logger logger = LogHelper.getLogger();

    public KeywordWeb() {

    }


    public void clearText(String element) {

        logger.info("clearText");
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        driver.findElement(By.xpath(xPathElement)).clear();
    }

    public void click(String element) {
        logger.info("click" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        driver.findElement(By.xpath(xPathElement)).click();
    }

    public void clickByCss(String element) {
        logger.info("click" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        driver.findElement(By.cssSelector(xPathElement)).click();
    }

    public void randomElement(String element) {
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        List<WebElement> weblist = driver.findElements(By.xpath(xPathElement));
        int size = weblist.size();
        int randnMumber = ThreadLocalRandom.current().nextInt(0, size);
        weblist.get(randnMumber).click();

    }

    public void randomConcatElement(String element, int num) throws InterruptedException {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int randomNumber = random.nextInt(1, num);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        String ele = xPathElement + "[" + randomNumber + "]";
        Thread.sleep(1000);
        driver.findElement(By.xpath(ele)).click();
    }

    public String getText(String element) {
        logger.info("get Text of" + element);
        String text = PropertiesFile.getPropValue(element);
        if (text == null) {
            text = element;
        }
        return driver.findElement(By.xpath(text)).getText();
    }

    public void sendKeys(String element, String content) {
        logger.info("send keys" + element + "with " + content);
        String xPathElement1 = PropertiesFile.getPropValue(element);
        String xPathElement2 = PropertiesFile.getPropValue(content);
        if (xPathElement1 == null) {
            xPathElement1 = element;
        }
        if (xPathElement2 == null) {
            xPathElement2 = content;

        }
        driver.findElement(By.xpath(xPathElement1)).sendKeys(xPathElement2);
    }

    public String getTextWithOutCharacters(String element, String oldChar) {
        logger.info("getText of " + element + " without " + oldChar);
        String xPathElement1 = PropertiesFile.getPropValue(element);
        String xPathElement2 = PropertiesFile.getPropValue(oldChar);
        if (xPathElement1 == null) {
            xPathElement1 = element;
        }
        if (xPathElement2 == null) {
            xPathElement2 = oldChar;

        }
        return driver.findElement(By.xpath(xPathElement1)).getText().replace(xPathElement2, "").replace(",","");
    }


    public String removeLastChar(String str) {
        return str.isEmpty() ? "" : str.substring(0, str.length() - Character.charCount(str.codePointBefore(str.length())));
    }

    public void doubleClick(String element) {
        logger.info("double click" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        Actions builder = new Actions(driver);
        WebElement elementRep = driver.findElement(By.xpath(xPathElement));
        builder.doubleClick(elementRep).perform();
    }

    public void dragAndDropToObj(String startElement, String endElement) {
        logger.info("drag from" + startElement + " to" + endElement);
        String xPathElement1 = PropertiesFile.getPropValue(startElement);
        if (xPathElement1 == null) {
            xPathElement1 = startElement;
        }
        String xPathElement2 = PropertiesFile.getPropValue(endElement);
        if (xPathElement2 == null) {
            xPathElement2 = endElement;
        }
        Actions builder = new Actions(driver);
        WebElement source = driver.findElement(By.xpath(xPathElement1));
        WebElement target = driver.findElement(By.xpath(xPathElement2));
        builder.dragAndDrop(source, target).perform();
    }

    public void rightClick(String element, String menuItem) {
        logger.info("rightClick" + element);
        String xPathElement1 = PropertiesFile.getPropValue(element);
        if (xPathElement1 == null) {
            xPathElement1 = element;
        }
        String xPathElement2 = PropertiesFile.getPropValue(menuItem);
        if (xPathElement2 == null) {
            xPathElement2 = menuItem;
        }
        Actions builder = new Actions(driver);
        WebElement clickMe = driver.findElement(By.xpath(xPathElement1));
        WebElement editMenuItem = driver.findElement(By.xpath(xPathElement2));
        builder.contextClick(clickMe).moveToElement(editMenuItem).click().perform();
    }

    public void hoverAndClick(String element) {
        logger.info("Move To Element" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        Actions action = new Actions(driver);
        WebElement elementRep = driver.findElement(By.xpath(xPathElement));
        action.moveToElement(elementRep).perform();
    }

    public void hoverAndClicks(String element) {
        logger.info("Move To Element" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        Actions action = new Actions(driver);
        WebElement elementRep = driver.findElement(By.xpath(xPathElement));
        action.moveToElement(elementRep).clickAndHold();
    }

    public void executeJavaScript(String command) {
        logger.info("Executing JavaScript");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(command);
    }


    public void takeScreenshot(String imgformat, String srcpath) throws
            IOException, InterruptedException, AWTException {
        logger.info("Taking screenshot save to:" + srcpath);
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(image, imgformat, new File(srcpath));
    }

    public void maximizeWindow() {
        logger.info("Maximizing browser window...");
        driver.manage().window().maximize();
    }

    public void back() {
        logger.info("Back window...");
        driver.navigate().back();
    }

    public void reLoadPage() {
        logger.info("ReLoad Page...");
        driver.navigate().refresh();
    }

    public void navigateToUrl(String url) {
        logger.info("Navigating to URL..." + url);
        String xPathElement = PropertiesFile.getPropValue(url);
        if (xPathElement == null) {
            xPathElement = url;
        }
        driver.navigate().to(xPathElement);
    }

    public void getCurrentPageUrl() {
        logger.info("Navigating to URL...");
        driver.getCurrentUrl();
    }

    public void acceptAlert() {
        logger.info("Accepting alert...");
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public String getAlertText() {
        logger.info("Getting alert text...");
        Alert alert = driver.switchTo().alert();
        return alert.getText();

    }

    public void dismissAlert() {
        logger.info("Dismissing alert...");
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public void setAlertText(String alertText) {
        logger.info("Setting alert text...");
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(alertText);
        alert.accept();
    }

    //send username and password to alert login
    public void handleLoginPopup(String username, String password, String authenUrl) {
        logger.info("login by authen link with" + username + " " + password);
        String xPathElement1 = PropertiesFile.getPropValue(username);
        if (xPathElement1 == null) {
            xPathElement1 = username;
        }
        String xPathElement2 = PropertiesFile.getPropValue(password);
        if (xPathElement2 == null) {
            xPathElement2 = password;
        }
        String xPathElement3 = PropertiesFile.getPropValue(authenUrl);
        if (xPathElement3 == null) {
            xPathElement3 = authenUrl;
        }
        String url = "https://" + xPathElement1 + ":" + xPathElement2 + "@" + xPathElement3;
        driver.navigate().to(url);
    }


    public void switchToFrame(String frame) {
        logger.info("Switching to frame...");
        driver.switchTo().frame(frame);
    }

    public void switchToFrameByIndex(int index) {
        logger.info("Switching to frame by index...");
        driver.switchTo().frame(index);
    }

    public void switchToIFrame() {
        logger.info("Switching to Iframe");
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);
    }

    public void listWindowID() {
        for (String windowid : driver.getWindowHandles()) {
            logger.info("Listing window ID..." + windowid);
        }
    }

    public void switchToIFrameByXpath(String element) {
        logger.info("Switching to Iframe");
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebElement iframe = driver.findElement(By.xpath(xPathElement));
        driver.switchTo().frame(iframe);
    }

    public void switchToWindow(String window) {
        logger.info("Switching to Window");
        driver.switchTo().window(window);
    }

    public void switchToWindowByIndex(int index) {
        logger.info("switchToWindowByIndex");
        driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(index));
    }

    public void getTitleWindowByIndex(int index) {
        logger.info("switchToWindowByIndex");
        driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(index)).getTitle();
    }

    public void switchToParentWindow() {
        logger.info("switchToParentWindow");
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);
    }

    public void simpleAssertEquals(String expected, String actual) {
        logger.info("compare from " + expected + " with " + actual);
        String xPathElement1 = PropertiesFile.getPropValue(expected);
        if (xPathElement1 == null) {
            xPathElement1 = expected;
        }
        String xPathElement2 = PropertiesFile.getPropValue(actual);
        if (xPathElement2 == null) {
            xPathElement2 = actual;
        }
        Assert.assertEquals(xPathElement2, xPathElement1);

    }

    public void switchToTab(int tabNum) {
        logger.info("switchToTab");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabNum));
    }

    public void closeWindowByIndex(int index) {
        logger.info("closeWindowByIndex");
        switchToWindowByIndex(index);
        driver.close();
    }

    public void closeTab(int tabNum) {
        switchToTab(tabNum);
        driver.close();
    }

    public void closeWindowTitle(String title) {
        for (String windowid : driver.getWindowHandles()) {
            String windowTitle = driver.switchTo().window(windowid).getTitle();
            if (windowTitle.equals(title)) {
                driver.close();
                break;
            }
        }
    }

    public void scrollDownToElement(String xPath) {
        logger.info("scrollDownToElement" + xPath);
        String xPathElement = PropertiesFile.getPropValue(xPath);
        if (xPathElement == null) {
            xPathElement = xPath;
        }
        WebElement element = driver.findElement(By.xpath(xPathElement));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public void scrollDownToElementByCss(String element) {
        logger.info("scroll to element");
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebElement elements = driver.findElement(By.cssSelector(xPathElement));
        Actions actions = new Actions(driver);
        actions.moveToElement(elements);
        actions.perform();
    }

    public void scrollToPosition() {
        logger.info(" scrolling to position ");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,5000)");
    }

    public void scrollToPositionByScript(String jsScript) {
        logger.info(" scrolling to position ");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(jsScript);
    }


    public void switchToDefaultContent() {
        logger.info("SwitchTODefaultContent");
        driver.switchTo().defaultContent();
    }

    public void recaptchaClick() {
        logger.info("click recaptcha");
        new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha/api2/anchor?ar=1')]")));
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-border"))).click();
    }
//    public void recaptchaClick() {
//        logger.info("click recaptcha");
//        new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha/api2/anchor?ar=1')]")));
//
//        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-border"))).click();
//    }

    //verify keyword
    public boolean verifyElementPresent(String element) {
        logger.info("verifyElementPresent" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        try {
            driver.findElement(By.xpath(xPathElement));
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void selectDropDownListByName(String ddlPath, String itemName) {
        logger.info("select item by visibe text");
        String xPathElement1 = PropertiesFile.getPropValue(ddlPath);
        if (xPathElement1 == null) {
            xPathElement1 = ddlPath;
        }
        String xPathElement2 = PropertiesFile.getPropValue(itemName);
        if (xPathElement2 == null) {
            xPathElement2 = itemName;
        }
        Select dropDownList = new Select(driver.findElement(By.xpath(xPathElement1)));
        dropDownList.selectByVisibleText(xPathElement2);
    }

    public void switchToCurrentTab() {
        logger.info("switchToCurrentTabSuccess");
        String currentTab = driver.getWindowHandle();
        Set<String> tabs = driver.getWindowHandles();

        for (String tab : tabs) {
            if (!currentTab.equals(tab)) {
                driver.switchTo().window(tab);
            }
        }
    }

    public boolean verifyElementVisible(String element) {
        logger.info("verifyElementVisible" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        try {
            driver.findElement(By.xpath(xPathElement)).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    //if element is not displayed, testcase will keep running, otherwise it will stop
    public void checkElementIsNotDisplayed(String element) {
        logger.info("checkElementVisibleOrNot" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        try {
            driver.findElement(By.xpath(xPathElement));
            //I want to fail the test here if above element is found
            Assert.assertTrue(false);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            //pass the test if element is not found in try statement
            Assert.assertTrue(true);
        }
    }

    //if element is displayed, testcase will keep running, otherwise it will stop
    public void checkElementIsDisplayed(String element) {
        logger.info("checkElementVisibleOrNot" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        try {
            driver.findElement(By.xpath(xPathElement));
            //I want to pass the test here if above element is found
            Assert.assertTrue(true);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            //fail the test if element is not found in try statement
            Assert.assertTrue(false);
        }
    }

    public boolean CheckIsDisplayElement(String element) {
        logger.info("Check status element btn radio");
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        boolean stt = driver.findElement(By.xpath(xPathElement)).isDisplayed();
        if (!stt) {
            System.out.println("Not selected");
        } else {
            driver.navigate().back();
            System.out.println("Checkbox selected");
        }
        return stt;
    }

    public void deleteAllCookies() {
        logger.info("deleteAllCookies");
        driver.manage().deleteAllCookies();
    }

    public boolean checkStatusIsDisplay(String element) {
        logger.info("Check status ");
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        boolean status = driver.findElement(By.xpath(xPathElement)).isDisplayed();
        if (status) {
            System.out.println("Is Display" + "\t" + element);
        } else {
            System.out.println("Is not Display" + "\t" + element);
        }
        return status;

    }

    public Integer countNumberOfElement(String element) {
        logger.info("count the number of element " + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        int xpathCount = driver.findElements(By.xpath(xPathElement)).size();
        return xpathCount + 1;
    }

    public void chooseFile(String element, String path){
        logger.info("choose file from " + path);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebElement chooseFile = driver.findElement(By.xpath(xPathElement));
        //path is absolute path to link to file that you want to choose
        chooseFile.sendKeys(path);
    }
    // wait keywords

    public void imWait(long timeout) {
        logger.info("implicitlyWait");
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    public void webDriverWaitForElementPresent(String element, long timeout) {
        logger.info("webDriverWaitForElementPresent" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPathElement)));
    }

    public String splitEnterCharacters(String text, Integer index) {
        String[] words = text.split("\n");
        return words[index];
    }

    public void waitForAjaxToFinish() throws InterruptedException {
        logger.info("waitForAjaxToFinish");

        WebDriverWait wait = new WebDriverWait(driver, 3000);

        wait.until((ExpectedCondition<Boolean>) wdriver -> ((JavascriptExecutor) driver).executeScript(
                "return !!window.jQuery && !!window.jQuery.active == 0;").equals(true));
        Thread.sleep(150);
    }

    private static void until(Function<WebDriver, Boolean> waitCondition, Long timeoutInSeconds) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
//        webDriverWait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);
        //webDriverWait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);
        try {
            webDriverWait.until(waitCondition);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public void untilJqueryIsDone(Long timeoutInSeconds) throws InterruptedException {
        until((d) ->
        {
            Boolean isJqueryCallDone = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
            if (!isJqueryCallDone) System.out.println("JQuery call is in Progress");
            return isJqueryCallDone;
        }, timeoutInSeconds);
        Thread.sleep(1000);
    }

    public String waitForElementNotVisible(int timeOutInSeconds, String elementXPath) {
        logger.info("elemnt " + elementXPath + " not visible");
        if ((driver == null) || (elementXPath == null) || elementXPath.isEmpty()) {

            return "Wrong usage of WaitforElementNotVisible()";
        }
        try {
            (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.invisibilityOfElementLocated(By
                    .xpath(elementXPath)));
            return null;
        } catch (TimeoutException e) {
            return "Build your own errormessage...";
        }
    }

    public void webDriverWaitForElementPresentByCss(String element, long timeout) {
        logger.info("webDriverWaitForElementPresentByCss");
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(xPathElement)));
    }

    public void fluentWaitForElementPresent(String element, Duration polling, Duration timeout) {
        logger.info("fluentWaitForElementPresent");
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(timeout)
                .pollingEvery(polling)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
    }

    public void deleteInput() {
        ((JavascriptExecutor) driver).executeScript("document.getElementByClass('input-box').reset()");
        System.out.printf("clear.....");
    }

    public void assertEquals(String expected, String actual) {
        logger.info("compare from " + expected + " with " + actual);
        String xPathElement1 = PropertiesFile.getPropValue(expected);
        String xPathElement2 = PropertiesFile.getPropValue(actual);
        if (xPathElement1 == null) {
            xPathElement1 = expected;
        }
        if (xPathElement2 == null) {
            xPathElement2 = actual;
        }
        String actualText = driver.findElement(By.xpath(xPathElement2)).getText();
        Assert.assertEquals(actualText, xPathElement1);

    }

    public void openNewTabFromTabBase(int tabNum, String url) {
        logger.info("open new tab from tab base");
        String xPathElement1 = PropertiesFile.getPropValue(url);
        if (xPathElement1 == null) {
            xPathElement1 = url;
        }
        executeJavaScript("window.open()");
//        switchToTab(tabNum);
        navigateToUrl(xPathElement1);
    }
    public void openNewTab(String url) {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        // driver.navigate().to(url);
        String URL = PropertiesFile.getPropValue(url);
        if (URL == null) {
            URL = url;
        }
        navigateToUrl(URL);
    }

    public void recaptchaClickSubmit() {
        logger.info("click recaptcha");
        new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha/api2/anchor?ar=1')]")));

        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-border"))).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("#footer_newsletter_recaptcha > div.box-recatpcha-actions > button"))).click();

    }

    public void copyPaste(String element1, String element2, String content) throws InterruptedException {
        String xPathElement1 = PropertiesFile.getPropValue(element1);
        String xPathElement2 = PropertiesFile.getPropValue(element2);
        String xPathContent = PropertiesFile.getPropValue(content);
        if (xPathElement1 == null) {
            xPathElement1 = element1;
        }
        if (xPathElement2 == null) {
            xPathElement2 = element2;
        }
        if (xPathContent == null) {
            xPathContent = content;
        }
        Actions act = new Actions(driver);
        WebElement ele1 = driver.findElement(By.xpath(xPathElement1));
        WebElement ele2 = driver.findElement(By.xpath(xPathElement2));

        act.moveToElement(ele1).click().sendKeys(xPathContent);
        act.keyDown(Keys.CONTROL).sendKeys("a");
        act.sendKeys("c");
        ele2.clear();
        untilJqueryIsDone(30L);
        act.moveToElement(ele2).click().keyDown(Keys.CONTROL).sendKeys("v");
        act.keyUp(Keys.CONTROL).build().perform();
    }
    public void clickAction(String ele, int x, int y){
//        logger.info("click" + ele);
//        Actions builder = new Actions(driver);
//        builder.moveByOffset(x, y).click().build().perform();
//        logger.info("click" + ele);
        String xPathElement = PropertiesFile.getPropValue(ele);
        if (xPathElement == null) {
            xPathElement = ele;
        }
        WebElement element = driver.findElement(By.xpath(xPathElement));
        Actions builder = new Actions(driver);
//        builder.moveToElement(element, x, y).click().build().perform();
        builder.moveToElement(element).build().perform();
    }

    public String getAttribute(String element) {
        logger.info("get Attribute of" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebElement b = driver.findElement(By.xpath(xPathElement));
        String c = b.getAttribute("style");
        logger.info(c);
        return c;
    }

    public String getAttributeWithValue(String element) {
        logger.info("get Attribute of" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebElement b = driver.findElement(By.xpath(xPathElement));
        String c = b.getAttribute("value");
        logger.info(c);
        return c;
    }

    public void keysBoardWithDOWN(String element1) throws InterruptedException {
        String xPathElement1 = PropertiesFile.getPropValue(element1);

        if (xPathElement1 == null) {
            xPathElement1 = element1;
        }
        WebElement tutorial = driver.findElement(By.xpath(xPathElement1));
        Actions act = new Actions(driver);
        act.moveToElement(tutorial).build().perform();
        imWait(2);
        act.contextClick(tutorial).sendKeys(Keys.ARROW_DOWN).build().perform();
    }

    public void verifyAttributeValues(String expect, String elementGetValue) {
        // getAttribute() to get value as displayed in GUI // no value attribute for the field in the DOM.
        String xPathElement1 = PropertiesFile.getPropValue(elementGetValue);
        String xPathElement2 = PropertiesFile.getPropValue(expect);
        if (xPathElement1 == null) {
            xPathElement1 = elementGetValue;
        }
        if (xPathElement2 == null) {
            xPathElement2 = expect;
        }
        String valueElement = driver.findElement(By.xpath(xPathElement1)).getAttribute("value");
        Assert.assertEquals(valueElement, xPathElement2);
    }

    public void checkNotCopyPastesKeyboardEvents(String element1, String dataSendKey) throws InterruptedException {
        String xPathElement1 = PropertiesFile.getPropValue(element1);
        String xPathContent = PropertiesFile.getPropValue(dataSendKey);
        if (xPathElement1 == null) {
            xPathElement1 = element1;
        }
        if (xPathContent == null) {
            xPathContent = dataSendKey;
        }
        Actions actions = new Actions(driver);
        // Enter the Current Address
        WebElement currentAddress = driver.findElement(By.xpath(xPathElement1));
        currentAddress.sendKeys(xPathContent);
        // Select the Current Address using CTRL + A
        actions.keyDown(Keys.CONTROL);
        actions.sendKeys("a");
        actions.keyUp(Keys.CONTROL);
        actions.build().perform();
        // Copy the Current Address using CTRL + C
        actions.keyDown(Keys.CONTROL);
        actions.sendKeys("c");
        actions.keyUp(Keys.CONTROL);
        actions.build().perform();
        //Press the TAB Key to Switch Focus to Permanent Address
        actions.sendKeys(TAB);
        actions.build().perform();
        //Paste the Address in the Permanent Address field using CTRL + V
        Thread.sleep(1000);
        actions.keyDown(Keys.CONTROL);
        actions.sendKeys("v");
        actions.keyUp(Keys.CONTROL);
        actions.build().perform();
    }

    public void clearLocalStorage() {
        logger.info("clearLocalStorage");
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("localStorage.removeItem(\"mage-cache-storage\")");
    }

    public String numberOnly(String element) {
        logger.info("get Text of" + element);
        String text = PropertiesFile.getPropValue(element);
        if (text == null) {
            text = element;
        }
        return driver.findElement(By.xpath(text)).getText().replaceAll("[^0-9]", "");
    }

    public void assertEqualsAfterCutting(String expected, String actual, int indexStart, int indexEnd) {
        logger.info("compare from " + expected + " with " + actual);
        String xPathElement1 = PropertiesFile.getPropValue(expected);
        String xPathElement2 = PropertiesFile.getPropValue(actual);
        if (xPathElement1 == null) {
            xPathElement1 = expected;
        }
        if (xPathElement2 == null) {
            xPathElement2 = actual;
        }
        String actualText = driver.findElement(By.xpath(xPathElement2)).getText().substring(indexStart, indexEnd);
        Assert.assertEquals(actualText, xPathElement1);
    }

    public void scrollToTheBottomPage() {
        logger.info("scrollDownToElementWithJavaExecutor");
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void compareTheValueOfStrings(String expected, String actual, int indexStart, int indexEnd) {
        logger.info("compare from " + expected + " with " + actual);
        String xPathElement1 = PropertiesFile.getPropValue(expected);
        String xPathElement2 = PropertiesFile.getPropValue(actual);
        if (xPathElement1 == null) {
            xPathElement1 = expected;
        }
        if (xPathElement2 == null) {
            xPathElement2 = actual;
        }
        String actualText = driver.findElement(By.xpath(xPathElement2)).getAttribute("style").substring(indexStart, indexEnd);
        Assert.assertEquals(actualText, xPathElement1);
    }

    public void pressEnter() {
        logger.info("press enter");
        Actions actions = new Actions(driver);
        // Enter the Current Address
        actions.sendKeys(Keys.ENTER);

    }
    public void resizeBrowser(int width, int height) {
        Dimension d = new Dimension(width, height);
        //Resize the current window to the given dimension
        driver.manage().window().setSize(d);
    }
    public void selectByText(String xpath, String text){
        Select singleSelect = new Select(driver.findElement(By.xpath(xpath)));
        singleSelect.selectByVisibleText(text);
    }
    public void senKeyByJavascript(String data,String type){
        String xPathElement1 = PropertiesFile.getPropValue(data);
        if (xPathElement1 == null) {
            xPathElement1 = data;
        }

        switch (xPathElement1){
            case "linh":
                if(type.equals("couple")){
                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('input-field-editable ')[1].textContent = 'linh'");
                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('product-custom-option hidden-nospace error-anchor')[1].value='linh';");

                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('input-field-editable ')[2].textContent = 'linh'");
                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('product-custom-option hidden-nospace error-anchor')[2].value='linh';");

                }else{
                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('input-field-editable ')[1].textContent = 'linh'");
                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('product-custom-option hidden-nospace error-anchor')[1].value='linh';");

                }
                  break;
            case "test":
                if(type.equals("couple")){
                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('input-field-editable ')[1].textContent = 'test'");
                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('product-custom-option hidden-nospace error-anchor')[1].value='test';");

                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('input-field-editable ')[2].textContent = 'test'");
                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('product-custom-option hidden-nospace error-anchor')[2].value='test';");

                }else{
                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('input-field-editable ')[0].textContent = 'test'");
                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('product-custom-option hidden-nospace error-anchor')[0].value='test';");

                }
                 break;
            case "sophie test limit 25 chara":
                if(type.equals("couple")){
                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('input-field-editable ')[1].textContent = 'sophie test limit 25 chara'");
                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('product-custom-option hidden-nospace error-anchor')[1].value='sophie test limit 25 chara';");

                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('input-field-editable ')[2].textContent = 'sophie test limit 25 chara'");
                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('product-custom-option hidden-nospace error-anchor')[2].value='sophie test limit 25 chara';");

                }else{
                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('input-field-editable ')[0].textContent = 'sophie test limit 25 chara'");
                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('product-custom-option hidden-nospace error-anchor')[0].value='sophie test limit 25 chara';");

                }
                break;
        }
//        if(data.equals("linh")){
//            ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('input-field-editable ')[0].textContent = 'linh'");
//            ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('product-custom-option hidden-nospace error-anchor')[0].value='linh';");
//        }

//        String valueElement = driver.findElement(By.xpath("class=\"product-custom-option hidden-nospace error-anchor\"")).getAttribute("value");
//        logger.info(valueElement);
//        ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('product-custom-option hidden-nospace error-anchor').value='linh';");
//        ((JavascriptExecutor) driver).executeScript("arguments[0].value='linh';",xPathElement1 );
//        ((JavascriptExecutor)getDrivers()).executeScript("$('." + "input-field-editable  " + "').val('" + "text" + "');");
//        jse.executeScript("document.getElementById('engraving-777').setAttribute('value', 'dung')");
    }



}


