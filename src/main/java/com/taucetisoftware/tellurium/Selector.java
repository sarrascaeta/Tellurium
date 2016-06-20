package com.taucetisoftware.tellurium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Sergio on 6/2/2016.
 */
public class Selector {

	/**
     * Returns a CSS locator
     *
     * @param cssSelector the CSS selector of the desired web element
     * @return the CSS locator
     */
    public static By css(String cssSelector) {
	    return By.cssSelector(cssSelector);
    }

	/**
     * Returns an xpath locator
     *
     * @param xpath the xpath of the desired web element
     * @return the xpath locator
     */
    public static By xpath(String xpath) {
	    return By.xpath(xpath);
    }

	/**
     * Returns an ID locator
     *
     * @param id the id of the desired web element
     * @return the ID selector
     */
    public static By id(String id) {
        return By.id(id);
    }

	/**
     * Returns a name locator
     *
     * @param name the name of the desired element
     * @return the name locator
     */
    public static By name(String name) {
        return By.name(name);
    }
}
