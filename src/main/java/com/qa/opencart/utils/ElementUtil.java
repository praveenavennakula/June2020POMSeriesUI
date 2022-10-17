package com.qa.opencart.utils;

import com.qa.opencart.factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ElementUtil {


    private WebDriver driver;
    private Actions act;
    private JavaScriptUtil jsUtil;
    private static final String ELEMENT_NOT_FOUND_ERROR = "element is not available on the page : ";

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
        act = new Actions(driver);
        jsUtil=new JavaScriptUtil(driver);

    }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public WebElement getElement(By locator) {
        WebElement ele=driver.findElement(locator);
        if(Boolean.parseBoolean(DriverFactory.highlight)){
            jsUtil.flash(ele);

        }
        return ele;
    }

    public void doSendKeys(By locator, String value) {
        getElement(locator).clear();
        getElement(locator).sendKeys(value);
    }


    public void doClick(By locator) {
        getElement(locator).click();
    }

    public String doGetText(By locator) {
        return getElement(locator).getText();
    }

    public String doGetAttribute(By locator, String attrName) {
        return getElement(locator).getAttribute(attrName);
    }

    public boolean doEleIsDisplayed(By locator) {
        return getElement(locator).isDisplayed();
    }

    public void isSingleElementPresent(By locator) {
        List<WebElement> list = getElements(locator);
        System.out.println(list.size());
        if (list.size() == 1) {
            System.out.println("single element is present on the page");
        } else {
            System.out.println("no such element or multiple elements are present");
        }
    }

    public int getElementsCount(By locator) {
        return getElements(locator).size();
    }

    public ArrayList<String> getElementsTextList(By locator) {
        List<WebElement> eleList = getElements(locator);
        ArrayList<String> eleTextList = new ArrayList<String>();

        for (WebElement e : eleList) {
            String text = e.getText();
            if (text.length() != 0) {
                eleTextList.add(text);
            }
        }
        return eleTextList;
    }


    // ******************Drop Down utils*****************//
    // only when html tag = <select>

    public void doSelectDropDownByVisibleText(By locator, String text) {
        Select sel = new Select(getElement(locator));
        sel.selectByVisibleText(text);


    }

    public void doSelectDropDownByIndex(By locator, int index) {
        Select sel = new Select(getElement(locator));
        sel.selectByIndex(6);


    }

    public void doSelectDropDownByValue(By locator, String value) {
        Select sel = new Select(getElement(locator));
        sel.selectByValue(value);


    }


    public List<WebElement> getDropDownOptions(By locator) {
        Select sel = new Select(getElement(locator));
        return sel.getOptions();

    }

    public int getDropDownOptionsCount(By locator) {
        return getDropDownOptions(locator).size();
    }

    //can be used with or without select html tag
    public void doSelectValueFromDropDown(By locator, String value) {
        List<WebElement> optionsList = getElements(locator);
        System.out.println(optionsList.size());
        for (WebElement e : optionsList) {
            String text = e.getText();
            System.out.println(text);
            if (text.equals(value)) {
                e.click();
                break;
            }
        }
    }

    public void dosearch(By searchLocator, String searchKey, By sugglocator, String value) throws InterruptedException {
        getElement(searchLocator).sendKeys(searchKey);
        Thread.sleep(10000);
        List<WebElement> sugglist = getElements(sugglocator);
        System.out.println(sugglist.size());
        for (WebElement e : sugglist) {
            String text = e.getText();
            System.out.println(text);
            if (text.equals(value)) {
                e.click();
                break;
            }
        }
    }

    public void dosearch(String tagName, String text) {
        By suggLocator = By.xpath("//" + tagName + "[text()='" + text + "']");
        getElement(suggLocator).click();

    }

    public boolean checkElementIsMandatory(String jsScript) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String man_field = jse.executeScript(jsScript).toString();
        System.out.println(man_field);
        if (man_field.contains("*")) {
            System.out.println("Element is Mandatory ");
            return true;
        } else {
            System.out.println("Element is not Mandatory");
            return false;
        }
    }

    //************************Actions Utils *****************************//

    public void handleLevel1MenuItems(By parentLocator, By childLocator) throws InterruptedException {
        // Actions act=new Actions(driver);//created a instance var at class level
        act.moveToElement(getElement(parentLocator)).build().perform();
        Thread.sleep(3000);
        //  getElement(childLocator).click();
        doClick(childLocator);
        Thread.sleep(3000);
    }

    public void handleLevel2MenuItems(By parent1Locator, By parent2Locator, By childLocator) throws InterruptedException {
        //  Actions act=new Actions(driver);//created a instance var at class level
        act.moveToElement(getElement(parent1Locator)).build().perform();
        Thread.sleep(3000);
        act.moveToElement(getElement(parent2Locator)).build().perform();
        //  getElement(childLocator).click();
        doClick(childLocator);
        Thread.sleep(3000);
    }

    public void handleLevel3MenuItems(By parent1Locator, By parent2Locator, By parent3Locator, By childLocator) throws InterruptedException {
        //  Actions act=new Actions(driver);//created a instance var at class level
        act.moveToElement(getElement(parent1Locator)).build().perform();
        Thread.sleep(3000);
        act.moveToElement(getElement(parent2Locator)).build().perform();
        Thread.sleep(3000);
        act.moveToElement(getElement(parent3Locator)).build().perform();
        //  getElement(childLocator).click();
        doClick(childLocator);
        Thread.sleep(3000);
    }

    public void handleLevel4MenuItems(By parent1Locator, By parent2Locator, By parent3Locator, By parent4Locator, By childLocator) throws InterruptedException {
        //  Actions act=new Actions(driver);//created a instance var at class level
        act.moveToElement(getElement(parent1Locator)).build().perform();
        Thread.sleep(3000);
        act.moveToElement(getElement(parent2Locator)).build().perform();
        Thread.sleep(3000);
        act.moveToElement(getElement(parent3Locator)).build().perform();
        Thread.sleep(3000);
        act.moveToElement(getElement(parent4Locator)).build().perform();
        //  getElement(childLocator).click();
        doClick(childLocator);
        Thread.sleep(3000);
    }

    public void dragAndDrop(By sourceEle, By targetEle) {
        act.dragAndDrop(getElement(sourceEle), getElement(targetEle)).build().perform();
    }

    public void doActionsClick(By locator) {
        act.click(getElement(locator)).build().perform();
    }

    public void doActionsSendKeys(By locator, String text) {
        act.sendKeys(getElement(locator), text).build().perform();
    }

    // *********************wait utils**********************//

    /**
     * An expectation for checking that an element is present on the DOM of a page.
     * This does not necessarily mean that the element is visible.
     *
     * @param locator
     * @param timeOut
     * @return
     */
    public WebElement waitForElementPresence(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void doSendKeysWithWait(By locator, int timeOut, String value) {
        waitForElementPresence(locator, timeOut).sendKeys(value);

    }

    public String getElementTextWithWait(By locator, int timeOut) {
        return waitForElementPresence(locator, timeOut).getText();

    }

    /**
     * An expectation for checking that an element is present on the DOM of a page
     * and visible. Visibility means that the element is not only displayed but also
     * has a height and width that is greater than 0.
     * Default polling time = 500 ms
     *
     * @param locator
     * @param timeOut
     * @return
     */

    public WebElement waitForElementVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * An expectation for checking that an element is present on the DOM of a page
     * and visible. Visibility means that the element is not only displayed but also
     * has a height and width that is greater than 0.
     * Default polling time = customized
     *
     * @param locator
     * @param timeOut
     * @return
     */
    public WebElement waitForElementVisible(By locator, int timeOut, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(pollingTime));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * An expectation for checking that all elements present on the web page that match the locator are visible.
     * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
     *
     * @param locator
     * @param timeOut
     * @return
     */
    public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void doSendKeysWithVisibleElement(By locator, int timeOut, String value) {
        waitForElementVisible(locator, timeOut).sendKeys(value);
    }

    public void doClickwithVisibleElement(By locator, int timeOut) {
        waitForElementVisible(locator, timeOut).click();
    }

    public String getElementTextWithVisibleElement(By locator, int timeOut) {
        String text = waitForElementVisible(locator, timeOut).getText();
        return text;
    }

    public void clickWhenReady(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }


    public Alert waitForAlert(int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public String getAlerttext(int timeOut) {
        return waitForAlert(timeOut).getText();
    }

    public void acceptAlert(int timeOut) {
        waitForAlert(timeOut).accept();
    }

    public void dismissAlert(int timeOut) {
        waitForAlert(timeOut).dismiss();
    }

    public void alertSendKeys(int timeOut, String value) {
        waitForAlert(timeOut).sendKeys(value);
    }

    public String waitForTitleIs(int timeOut, String titleValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        if (wait.until(ExpectedConditions.titleIs(titleValue))) {
            return driver.getTitle();
        } else {
            return null;
        }
    }

    public String waitForURLContains(int timeOut, String urlFraction) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
            return driver.getCurrentUrl();
        } else {
            return null;
        }
    }

    public String waitForURLValue(int timeOut, String urlValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        if (wait.until(ExpectedConditions.urlToBe(urlValue))) {
            return driver.getCurrentUrl();
        } else {
            return null;
        }
    }

    public void waitForFrame(int timeOut, int frameIndex) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
    }

    public void waitForFrame(int timeOut, String framenameOrID) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(framenameOrID));
    }

    public void waitForFrame(int timeOut, WebElement frameElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }

    public void waitForFrame(int timeOut, By frameLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    public WebElement waitForElementToBeVisibleWithFluentWait(By locator, int timeOut, int pollingTime) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(java.util.NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .withMessage(ElementUtil.ELEMENT_NOT_FOUND_ERROR + locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * retrying for an element without wait
     */

    public WebElement retryingElement(By locator, int timeOut) {
        WebElement element = null;
        int attempts = 0;
        while (attempts < timeOut) {
            try {
                element = getElement(locator);
                System.out.println("element is found in the attempt: " + attempts);
            } catch (java.util.NoSuchElementException e) {
                System.out.println("element is not found in attempt" + attempts + ":" + "for" + locator);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
            attempts++;
        }
        if (element == null) {
            try {
                throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
            } catch (Exception e) {
                System.out.println("element is not found exception ...tried for : " + timeOut + " secs"
                        + " with the interval of : " + 500 + " ms");

            }
        }
        return element;
    }

    public WebElement retryingElement(By locator, int timeOut, int pollingTime) {

        WebElement element = null;

        int attempts = 0;

        while (attempts < timeOut) {

            try {
                element = getElement(locator);
                System.out.println("element is found in attempt: " + attempts);
                break;
            } catch (NoSuchElementException e) {
                System.out.println("element is not found in attempt : " + attempts + " : " + " for " + locator);

                try {
                    Thread.sleep(pollingTime);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            attempts++;

        }

        if (element == null) {
            try {
                throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
            } catch (Exception e) {
                System.out.println("element is not found exception ...tried for : " + timeOut + " secs"
                        + " with the interval of : " + pollingTime + " ms");

            }

        }

        return element;

    }


}
