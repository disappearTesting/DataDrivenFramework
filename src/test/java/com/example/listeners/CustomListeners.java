package com.example.listeners;

import com.example.base.TestBase;
import com.example.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class CustomListeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        TestBase.test = TestBase.report.startTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.setProperty("org.uncommons.reportng.escape-output","false");
        Reporter.log("Capturing screenshot.");
        Reporter.log("<a href="+TestUtil.screenshotName+">Screenshot</a>");

        TestBase.test.log(LogStatus.PASS, result.getName() + "Success. Test Pass.");
        TestBase.report.endTest(TestBase.test);
        TestBase.report.flush();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.setProperty("org.uncommons.reportng.escape-output","false");
        Reporter.log("Failure. Test fail.");
        TestUtil.captureScreenshot();
        Reporter.log("Capturing screenshot.");
        Reporter.log("<a href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+"></img></a>");

        TestBase.test.log(LogStatus.FAIL, result.getName() + "Failure. Test fail." + result.getThrowable().getMessage());
        TestBase.test.log(LogStatus.FAIL, TestBase.test.addScreenCapture(TestUtil.screenshotName));
        TestBase.report.endTest(TestBase.test);
        TestBase.report.flush();
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}