package com.example.listeners;

import com.example.base.TestBase;
import com.example.utilities.SendEmailSMTP_TLSAuthentication;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportListener extends TestBase implements ITestListener, ISuiteListener {

    // reports file location
    private String reportsPath = System.getProperty("user.dir") + "\\src\\test\\resources\\reports\\";
    private String screenshotsPath = System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\";

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
            FileUtils.copyFile(screenshotFile, new File(screenshotsPath + result.getMethod().getMethodName() + ".png"));
            String screenCapture = extentTest.addScreenCapture(screenshotsPath + result.getMethod().getMethodName() + ".png");
            extentTest.log(LogStatus.FAIL, "Test is failed: " + result.getMethod().getMethodName() + ".png", screenCapture);
            extentTest.log(LogStatus.FAIL, result.getThrowable());
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
        extentReports = new ExtentReports(reportsPath + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()) + "_reports.html");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentTest.log(LogStatus.INFO, "Test is finished.");
        extentReports.endTest(extentTest);
        extentReports.flush();
    }

    @Override
    public void onStart(ISuite iSuite) {

    }

    @Override
    public void onFinish(ISuite iSuite) {
        SendEmailSMTP_TLSAuthentication sendEmail = new SendEmailSMTP_TLSAuthentication();
        sendEmail.sendEmail();
    }
}