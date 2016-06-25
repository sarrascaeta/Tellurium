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

    public static void take(WebDriver driver) {
        take(driver, generateScreenshotFilepath(driver));
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

    public static void take(WebDriver driver, String screenshotLocation) {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        screenshotLocation = addExtensionIfNeeded(screenshotLocation);

        saveScreenshotToFile(screenshotLocation, scrFile);

        lastScreenshotFilepath = screenshotLocation;
        System.out.println("Screenshot saved! " + screenshotLocation);
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

    public static String getDefaultScreenshotPath() {
        return Util.getDesktopPath() + "/Selenium Screenshots/";
    }

    public static String getLastScreenshotFilepath() {
        return lastScreenshotFilepath;
    }

    public static String getAutomaticName(WebDriver driver) {
        return generateName(driver.getCurrentUrl());
    }
}
