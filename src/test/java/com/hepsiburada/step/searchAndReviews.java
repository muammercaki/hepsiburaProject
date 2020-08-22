package com.hepsiburada.step;

import com.hepsiburada.utils.Driver;
import com.hepsiburada.helper.ElementHelper;
import com.hepsiburada.helper.StoreHelper;
import com.hepsiburada.model.ElementInfo;
import com.thoughtworks.gauge.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class searchAndReviews extends Driver {

    protected static Logger logger = LogManager.getLogger(searchAndReviews.class);

    @Step("Click to element <clickElement>")
    public void clicktoElement(String clickElement) {
        WebElement element = findElement(clickElement);
        jsclickElement(element);
    }

    @Step("<key> number of content in element")
    public void numberOfElement(String key) {
        List<WebElement> productList = findElements(key);
        logger.info("First Page product reviews Count Size: " + productList.size());
        if (productList.size() == 0) {
            logger.info("Product reviews Count Size = " + productList.size());
            closeDriver();
        }
    }

    @Step("Write value <text> to element <key>")
    public void sendKeys(String text, String key) {
        if (!key.equals("")) {
            findElement(key).clear();
            findElement(key).sendKeys(text);
        }
    }

    @Step("Wait <value> seconds")
    public void waitBySeconds(int seconds) {
        try {

            logger.info("Waited " + seconds + " Seconds");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Click to Random Element <key>")
    public void clickRandomElemet(String key) {
        List<WebElement> lastNewsTitle = findElements(key);
        logger.info("product list: " + lastNewsTitle.size());
        WebElement randomNews = (WebElement) getRandomContent(lastNewsTitle);
        scrollToElement(randomNews);
        waitBySeconds(1);
        jsclickElement(randomNews);
        logger.info("Current URL: " + webDriver.getCurrentUrl());
    }

    @Step("Scroll until  find the element <key>")
    public void scroll(String key) {
        WebElement element = findElement(key);
        scrollToElement(element);
    }

    @Step("Go To URL <url>")
    public void gotoURL(String url) {
        webDriver.get(url);
    }

    @Step("Element control <key>")
    public WebElement elementControl(String key) {

        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By by = ElementHelper.getElementInfoToBy(elementInfo);
        if (webDriver.findElements(by).size() > 0) {
            logger.info("Element Set Text: " + findElement(key).getText());
            return webDriver.findElement(by);
        }
        logger.info("Element Created.." + findElement(key).getText());
        return null;
    }


    private WebElement findElement(String key) {
        try {
            ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
            By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
            WebDriverWait webDriverWait = new WebDriverWait(webDriver, 5, 300);
            WebElement webElement = webDriverWait
                    .until(ExpectedConditions.presenceOfElementLocated(infoParam));
            ((JavascriptExecutor) webDriver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                    webElement);
            return webElement;

        } catch (TimeoutException e) {
            Assert.fail("Verilen Sürede Aranan Eleman : '" + key + "' Oluşmamıştır.");
            return null;
        }

    }

    private void jsclickElement(WebElement element) {
        javaScriptClicker(webDriver, element);
    }

    public void javaScriptClicker(WebDriver driver, WebElement element) {
        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("var evt = document.createEvent('MouseEvents');"
                + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
                + "arguments[0].dispatchEvent(evt);" + "arguments[0].style.border='6px dotted blue'", element);
    }

    private List<WebElement> findElements(String key) {
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        return webDriver.findElements(infoParam);
    }

    public void scrollToElement(WebElement element) {

        if (element != null) {
            scrollTo(element.getLocation().getX(), element.getLocation().getY() - 350);
            waitBySeconds(2);

        }
    }

    protected void scrollTo(int x, int y) {
        String jsStmt = String.format("window.scrollTo(%d, %d);", x, y);
        executeJS(jsStmt, true);

    }

    protected Object executeJS(String jsStmt, boolean wait) {
        return wait ? getJSExecutor().executeScript(jsStmt, "")
                : getJSExecutor().executeAsyncScript(jsStmt, "");
    }

    protected JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) webDriver;
    }

    public Object getRandomContent(List<?> contentList) {
        Random rand = new Random();
        int n = rand.nextInt(contentList.size());
        return contentList.get(n);
    }

}
