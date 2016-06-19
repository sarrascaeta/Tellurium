package com.taucetisoftware.tellurium.drivers;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.MarionetteDriverManager;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

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
        MarionetteDriverManager.getInstance().setup();

        if (profile == null) {
            profile = new FirefoxProfile();
        }

        DesiredCapabilities dc = DesiredCapabilities.firefox();
        dc.setCapability(FirefoxDriver.PROFILE, profile);

        return new MarionetteDriver(dc);
    }

    public static WebDriver createChromeDriver() {
        return createChromeDriver(null);
    }

    public static WebDriver createChromeDriver(ChromeOptions options) {
        ChromeDriverManager.getInstance().setup();

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

    //TODO add more browsers and configurable options

    public static WebDriver createPhantomDriver() {
        PhantomJsDriverManager.getInstance().setup();

        return new PhantomJSDriver();
    }
}
