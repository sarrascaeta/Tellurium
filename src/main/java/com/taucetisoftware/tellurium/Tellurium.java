package com.taucetisoftware.tellurium;

import com.taucetisoftware.tellurium.utility.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Sergio on 6/2/2016.
 */
public class Tellurium extends Selector {

    private WebDriver driver;

    public Tellurium() {
        //do nothing
    }

    public Tellurium(WebDriver driver) {
        this.driver = driver;
    }

    public void click(By elementToClick) {
        WebElement webElement = createWebElement(elementToClick);
        webElement.click();
    }

    private WebElement createWebElement(By elementToClick) {
        //TODO explicit wait?

        if (driver == null) {
            throw new RuntimeException("WebDriver is null! Create a webdriver and assign it with: setDriver()");
        }

        return driver.findElement(elementToClick);
    }


    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void sleep(int sleepTime) {
	Util.sleep(sleepTime);
    }
}
