package com.taucetisoftware.tellurium.drivers;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import io.github.bonigarcia.wdm.MarionetteDriverManager;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by Sergio on 6/16/2016.
 */
public class DriverFactory {
    private static String chromeDriverLocation;

    /**
     * Creates a standard Firefox webdriver
     *
     * @return Firefox driver
     */
    public static WebDriver createFirefoxDriver() {
        return createFirefoxDriver(null);
    }

	/**
     * Creates a Firefox driver with the specified Firefox profile
     *
     * @param profile the desired profile to use
     * @return Firefox WebDriver with the specified profile
     */
    public static WebDriver createFirefoxDriver(FirefoxProfile profile) {
        MarionetteDriverManager.getInstance().setup();

        if (profile == null) {
            profile = new FirefoxProfile();
        }

        DesiredCapabilities dc = DesiredCapabilities.firefox();
        dc.setCapability(FirefoxDriver.PROFILE, profile);

        return new MarionetteDriver(dc);
    }

	/**
     * Creates a standard Chrome webdriver
     *
     * @return Chrome webdriver
     */
    public static WebDriver createChromeDriver() {
        return createChromeDriver(null);
    }

	/**
     * Creates a Chrome driver with specified chrome options
     *
     * @param options the desired Chrome options
     * @return Chrome WebDriver with the specified chrome options
     */
    public static WebDriver createChromeDriver(ChromeOptions options) {
        ChromeDriverManager.getInstance().setup();

        if (options == null) {
            options = new ChromeOptions();
        }

        return new ChromeDriver(options);
    }

	/**
     * Creates a standard IE webdriver
     *
     * @return IE webdriver
     */
    public static WebDriver createIEDriver() {
        InternetExplorerDriverManager.getInstance().setup();

        return new InternetExplorerDriver();
    }


    //TODO add more browsers and configurable options


	/**
	 * Creates a PhantomJS webdriver. PhantomJS is a "headless" browser that runs in the background with no visible GUI
     *
     * @return PhantomJS webdriver
     */
    public static WebDriver createPhantomDriver() {
        PhantomJsDriverManager.getInstance().setup();

        return new PhantomJSDriver();
    }
}
