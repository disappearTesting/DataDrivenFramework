package com.example.listeners;

import com.example.base.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportListener extends TestBase implements ITestListener {

    //public static ExtentReports extentReports;
    //public static ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReports.startTest(result.getMethod().getMethodName());
        extentTest.log(LogStatus.INFO, "Test is started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(LogStatus.PASS, "Test is passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(LogStatus.FAIL, "Test is failed: " + result.getMethod().getMethodName());

        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        try {
            // /will save the screenshot in the drive
            FileUtils.copyFile(screenshotFile, new File(System.getProperty("user.dir") + result.getMethod().getMethodName() + ".png"));
            String screenCapture = extentTest.addScreenCapture(System.getProperty("user.dir") + result.getMethod().getMethodName() + ".png");
            extentTest.log(LogStatus.FAIL, "Test is failed: " + result.getMethod().getMethodName() + ".png", screenCapture);
            extentTest.log(LogStatus.FAIL, result.getMethod().getMethodName());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(LogStatus.SKIP, "Test is skipped" + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        extentReports = new ExtentReports(new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()) + "reports.html");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.endTest(extentTest);
        extentReports.flush();
    }
}
