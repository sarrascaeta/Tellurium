package com.taucetisoftware.tellurium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Sergio on 6/2/2016.
 */
public class Selector {

    public static By css(String cssSelector) {
	return By.cssSelector(cssSelector);
    }

    public static By xpath(String xpath) {
	return By.xpath(xpath);
    }

    public static By id(String id) {
        return By.id(id);
    }

    public static By name(String name) {
        return By.id(name);
    }


    public WebDriver css(String cssSelector) {
        By css = By.cssSelector("cssSelector");

        waitFor(css);
    }

    private void waitFor(By css) {

    }
}
