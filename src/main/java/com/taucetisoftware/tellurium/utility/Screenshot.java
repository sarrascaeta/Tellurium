package com.taucetisoftware.tellurium.utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

/**
 * Created by Sergio on 6/25/2016.
 */
public class Screenshot {
    private static String lastScreenshotFilepath;

    /**
     * Takes a screenshot and saves it to your destop, in a "Selenium Screenshots" directory.
     * The filename is automatically generated from the URL and the timestamp
     *
     * @param driver the webdriver
     */
    public static void take(WebDriver driver) {
        take(driver, generateScreenshotFilepath(driver));
    }

    /**
     * Takes a screenshot and saves it to the specified filepath
     *
     * @param driver the webdriver
     * @param screenshotLocation the filepath of the screenshot. You must provide
     *                           the path and the filename.
     */
    public static void take(WebDriver driver, String screenshotLocation) {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        screenshotLocation = addExtensionIfNeeded(screenshotLocation);
        saveScreenshotToFile(screenshotLocation, scrFile);

        lastScreenshotFilepath = screenshotLocation;
        System.out.println("Screenshot saved! " + screenshotLocation);
    }

    private static String generateScreenshotFilepath(WebDriver driver) {
        String path = getDefaultScreenshotPath();
        String name = generateName(driver.getCurrentUrl());

        name = shortenNameIfNeeded(name);
        return path + name;
    }

    private static String shortenNameIfNeeded(String name) {
        if (name.length() > 20) {
            name = name.substring(0, 20);
        }

        return name;
    }

    private static void saveScreenshotToFile(String screenshotLocation, File scrFile) {
        try {
            FileUtils.copyFile(scrFile, new File(screenshotLocation));
        } catch (IOException e) {
            System.err.println("Error taking screenshot: " + e.getLocalizedMessage());
        }
    }

    private static String addExtensionIfNeeded(String screenshotLocation) {
        screenshotLocation = removeTrailingPeriod(screenshotLocation);

        if (!screenshotLocation.endsWith(".jpg") || !screenshotLocation.endsWith(".png"))
            screenshotLocation += ".jpg";

        return screenshotLocation;
    }

    private static String removeTrailingPeriod(String screenshotLocation) {
        if (screenshotLocation.endsWith("."))
            screenshotLocation = screenshotLocation.replaceFirst("\\.$", "");

        return screenshotLocation;
    }

    private static String generateName(String url){
        String uniqueName = url.replace("://", "_").replace(".", "_").replace("/", "_");

        // Replace last UNDERSCORE with a DOT
        uniqueName = uniqueName.substring(0,uniqueName.lastIndexOf('_'))
                +"."+uniqueName.substring(uniqueName.lastIndexOf('_')+1,uniqueName.length());

        return uniqueName + Util.getTimestamp();
    }

    /**
     * Returns the default path where screenshots are saved to, when a path is not specified
     *
     * @return the default path of where screenshots are saved
     */
    public static String getDefaultScreenshotPath() {
        return Util.getDesktopPath() + "/Selenium Screenshots/";
    }

    /**
     * Returns the full filepath of the last screenshot taken.
     *
     * @return the full filepath of the last screenshot.
     */
    public static String getLastScreenshotFilepath() {
        return lastScreenshotFilepath;
    }

    /**
     * Returns an automatically generated screenshot name, based on the current URL and
     * time. This is useful for when you want to specify your own filepath, but
     * want a unique name generated automatically.
     *
     *
     * @param driver the driver
     * @return the generated name for the screenshot, based on the URL and time
     */
    public static String getAutomaticName(WebDriver driver) {
        return generateName(driver.getCurrentUrl());
    }
}
