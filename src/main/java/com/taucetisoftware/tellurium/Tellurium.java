package com.taucetisoftware.tellurium;

import com.taucetisoftware.tellurium.utility.Util;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sergio on 6/2/2016.
 */
public class Tellurium extends Selector {
    private int globalWaitTime = 30;

    protected WebDriver driver;

    public Tellurium() {
        //TODO implicit wait is not normally being set
    }

    public Tellurium(WebDriver driver) {
        this.driver = driver;
        setImplicitWait();
    }

    private void setImplicitWait() {
        driver.manage().timeouts().implicitlyWait(globalWaitTime, TimeUnit.SECONDS);
    }

    public void setGlobalWaitTime(int globalWaitInSeconds) {
        globalWaitTime = globalWaitInSeconds;
        setImplicitWait();
    }

	/**
     * Goes to a specified URL
     *
     * @param url the URL to goTo to
     */
    public void goTo(String url) {
        driver.get(url);
    }

	/**
     * Retrieves the current page URL
     *
     * @return the current page URL
     */
    public String getUrl() {
        return driver.getCurrentUrl();
    }

	/**
     * Returns the current page title
     *
     * @return the page title
     */
    public String getTitle() {
        return driver.getTitle();
    }

	/**
     * Returns the current page source. Note that this source is the entire generated DOM,
     * not just the HTML of the current page
     *
     * @return the page source
     */
    public String getPageSource() {
        return driver.getPageSource();
    }

	/**
     * Navigates back in the browser
     */
    public void goBack() {
        driver.navigate().back();
    }

	/**
     * Navigates forward in the browser
     */
    public void goForward() {
        driver.navigate().forward();
    }

	/**
     * Refreshes the page
     */
    public void refresh() {
        driver.navigate().refresh();
    }

	/**
     * Executes the specified javascript
     *
     * @param jsCode the javascript code to execute
     */
    public void executeJavascript(String jsCode) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript(jsCode);
        } else {
            throw new IllegalStateException("This driver does not support JavaScript!");
        }
    }

	/**
     * Clicks on the specified element. For example, you would use: click(css("Your.CSS.here"))
     * Webdriver will wait for the element to be clickable, for global wait time amount
     *
     * @param elementToClick the locator of the element to click
     */
    public void click(By elementToClick) {
        WebElement webElement = findElement(elementToClick);
        webElement.click();
    }

	/**
     * Sends the specified text/keys to the specified element.
     *
     * @param elementToSendTo the element to send the text/keys to
     * @param isClearing whether or not to clear the element before sending keys to
     * @param charSequence the text/keys to send. {@see selenium.webdriver.common.keys.Keys}
     */
    public void sendTo(By elementToSendTo, boolean isClearing, CharSequence... charSequence) {
        WebElement webElement = findElement(elementToSendTo);

        if (isClearing)
            webElement.clear();

        webElement.sendKeys(charSequence);
    }

	/**
     * Like {@link #sendTo(By, boolean, CharSequence...)} but will clear the element's text automatically
     * @param elementToSendTo
     * @param charSequence
     */
    public void sendTo(By elementToSendTo, CharSequence... charSequence) {
        sendTo(elementToSendTo, true, charSequence);
    }


    public WebElement findElement(By locator) {
        waitFor(locator);

        checkDriver();

        return driver.findElement(locator);
    }


    public List<WebElement> findElements(By locator) {
        waitFor(locator);

        checkDriver();

        return driver.findElements(locator);
    }


    private void checkDriver() {
        if (driver == null) {
            throw new RuntimeException("WebDriver is null! Create a webdriver and assign it with: setDriver()");
        }
    }

	/**
     * Like {@link #waitFor(By, int)} however it will default to the global wait time.
     *
     * @param elementToWaitFor
     */
    public void waitFor(By elementToWaitFor) {
        waitFor(elementToWaitFor, globalWaitTime);
    }

	/**
     * Waits for the specified element to be clickable
     *
     * @param elementToWaitFor the element to wait for
     * @param secondsToWait how long to wait, in seconds
     */
    public void waitFor(By elementToWaitFor, int secondsToWait) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, secondsToWait);
            wait.until(ExpectedConditions.visibilityOfElementLocated(elementToWaitFor));
        } catch (Exception e) {

        }
    }

	/**
     * Sets the webdriver.
     *
     * @param driver the webdriver to use for everything
     */
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

	/**
     * Wait for the specified amount of time, in milliseconds. This just calls the sleep method in the
     * {@see com.taucetisoftware.utility.Util} class, however it's included here for convenience in writing
     * webdriver scripts.
     *
     * @param sleepTime how long to wait, in milliseconds
     */
    public void sleep(int sleepTime) {
	    Util.sleep(sleepTime);
    }

    public boolean elementExists(By locator) {
        if (findElements(locator).size() != 0)
            return true;
        else
            return false;
    }

    public void select(By locator, int index) {
        waitFor(locator);

        WebElement e = findElement(locator);
        new Select(e).selectByIndex(index);
    }

    public void select(By locator, String valueOrText) {
        waitFor(locator);

        WebElement we = findElement(locator);

        try {
            new Select(we).selectByValue(valueOrText);
        } catch (Exception e) {
            new Select(we).selectByVisibleText(valueOrText);
        }
    }

    public String getTextFrom(By locator) {
        WebElement element = findElement(locator);

        return element.getText();
    }

    public void verifyElement(By locator) {
        waitFor(locator);

        Assert.assertTrue("Element " + locator + " was not found.", elementExists(locator));
    }

    public void verifyElementText(By locator, String textToCheck) {
        waitFor(locator);

        String s = getTextFrom(locator);

        Assert.assertTrue(
                "The text \"" + textToCheck +"\" was not found in " + locator.toString(),
                textToCheck.trim().toLowerCase().equals(s.toLowerCase().trim())
                );
    }

    public void verifyElementContains(By locator, String textToCheck) {
        waitFor(locator);

        String s = getTextFrom(locator);

        Assert.assertTrue(
                "The text \"" + textToCheck +"\" was not found in " + locator.toString(),
                s.trim().toLowerCase().contains(textToCheck.trim().toLowerCase())
        );
    }

    public void verifyUrlContains(String textInUrl) {
        String s = getUrl();

        Assert.assertTrue(
                "The text \"" + textInUrl + "\" was not found in URL " + s,
                s.contains(textInUrl.trim().toLowerCase())
                );
    }

    public void verifyTitle(String titleToCheck) {
        String s = getTitle();

        Assert.assertTrue(
                "The text \"" + titleToCheck + "\" was not found in the page title \"" + s + "\"",
                s.trim().toLowerCase().equals(titleToCheck.trim().toLowerCase())

        );
    }

    public void verifyTitleContains(String textToCheck) {
        String s = getTitle();

        Assert.assertTrue(
                "The text \"" + textToCheck + "\" was not found in the page title \"" + s + "\"",
                s.trim().toLowerCase().contains(textToCheck.trim().toLowerCase())
                );
    }

    public void verifyElementNotPresent(By locator) {
        Assert.assertTrue(
                "The element " + locator.toString() + " was found on the page",
                driver.findElements(locator).size() == 0
                );
    }
}
