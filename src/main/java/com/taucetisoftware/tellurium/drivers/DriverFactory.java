package com.taucetisoftware.tellurium.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * Created by Sergio on 6/16/2016.
 */
public class DriverFactory {
    private static String chromeDriverLocation;

    /**
     * Creates a standard Firefox driver with a new profile
     *
     * @return Firefox driver
     */
    public static WebDriver createFirefoxDriver() {
        return createFirefoxDriver(null);
    }

    public static WebDriver createFirefoxDriver(FirefoxProfile profile) {
        if (profile == null) {
            profile = new FirefoxProfile();
        }

        return new FirefoxDriver(profile);
    }

    public static WebDriver createChromeDriver() {
        createChromeDriver(null);
    }

    public static WebDriver createChromeDriver(ChromeOptions options) {
        checkForDriverLocation();

        if (options == null) {
            options = new ChromeOptions();
        }

        return new ChromeDriver(options);
    }

    private static void checkForDriverLocation() {
        if (chromeDriverLocation.isEmpty()) {
            throw new RuntimeException("Location of chromedriver.exe has not been specified. " +
                    "\nSpecify file location with setChromeDriverLocation()");
        }
    }

    public void setChromeDriverLocation(String chromeDriverLocation) {
        this.chromeDriverLocation = chromeDriverLocation;
    }
}
