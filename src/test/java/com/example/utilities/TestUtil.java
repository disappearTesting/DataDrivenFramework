package com.example.utilities;

import com.example.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtil extends TestBase {
    private static String screenshotPath = "\\target\\surefire-reports\\html\\";
    public static String screenshotName;

    public static void captureScreenshot() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        screenshotName = dateFormat.format(currentDate) + ".jpg";
        try {
            // /will save the screenshot in the drive
            FileUtils.copyFile(screenshotFile, new File(System.getProperty("user.dir") + screenshotPath + screenshotName));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}