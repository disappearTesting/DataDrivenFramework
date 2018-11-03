package com.example.listeners;

import com.example.base.TestBase;
import com.example.utilities.SendEmailSMTP_TLSAuthentication;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import java.io.File;
import java.io.IOException;

public class ExtentReportListener extends TestBase implements ITestListener, ISuiteListener {

    // screenshots file location
    private static final String PATH_SCREENSHOTS = System.getProperty("user.dir") + "\\src\\test\\resources\\com.example\\screenshots\\";

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReports.startTest(result.getMethod().getMethodName());
        extentTest.log(LogStatus.INFO, "Test has Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(LogStatus.PASS, "Test is Pass: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(LogStatus.FAIL, "Failure. Test is Fail: " + result.getMethod().getMethodName());

        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        try {
            // /will save the screenshot in the drive
            FileUtils.copyFile(screenshotFile, new File(PATH_SCREENSHOTS + result.getMethod().getMethodName() + ".png"));
            String screenCapture = extentTest.addScreenCapture(PATH_SCREENSHOTS + result.getMethod().getMethodName() + ".png");
            extentTest.log(LogStatus.FAIL, "Failure. Test is Fail: " + result.getMethod().getMethodName() + ".png", screenCapture);
            extentTest.log(LogStatus.FAIL, result.getThrowable());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(LogStatus.SKIP, "Test is Skipped" + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        extentTest.log(LogStatus.INFO, "Test is Finished.");
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