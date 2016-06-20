package com.taucetisoftware.tellurium;

import com.taucetisoftware.tellurium.utility.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void go(String url) {
        driver.get(url);
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void goBack() {
        driver.navigate().back();
    }

    public void goForward() {
        driver.navigate().forward();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void executeJavascript(String jsCode) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript(jsCode);
        } else {
            throw new IllegalStateException("This driver does not support JavaScript!");
        }
    }

    public void click(By elementToClick) {
        WebElement webElement = createWebElement(elementToClick);
        webElement.click();
    }

    public void sendTo(By elementToSendTo, boolean isClearing, CharSequence... charSequence) {
        WebElement webElement = createWebElement(elementToSendTo);

        if (isClearing)
            webElement.clear();

        webElement.sendKeys(charSequence);
    }

    public void sendTo(By elementToSendTo, CharSequence... charSequence) {
        sendTo(elementToSendTo, true, charSequence);
    }


    private WebElement createWebElement(By elementToClick) {
        waitFor(elementToClick);

        if (driver == null) {
            throw new RuntimeException("WebDriver is null! Create a webdriver and assign it with: setDriver()");
        }

        return driver.findElement(elementToClick);
    }

    public void waitFor(By elementToWaitFor) {
        WebDriverWait wait = new WebDriverWait(driver, globalWaitTime);
        wait.until(ExpectedConditions.elementToBeClickable(elementToWaitFor));
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void sleep(int sleepTime) {
	    Util.sleep(sleepTime);
    }
}
