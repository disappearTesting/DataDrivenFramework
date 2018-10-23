package com.example.utilities;

import com.example.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class TestUtil extends TestBase {
    public static String screenshotPath = "\\target\\surefire-reports\\html\\";
    public static String screenshotName;

    public static void captureScreenshot() {
        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        screenshotName = "error.jpg";
        try {
            // /will save the screenshot in the drive
            FileUtils.copyFile(screenshotFile, new File(System.getProperty("user.dir") + screenshotPath + screenshotName));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}