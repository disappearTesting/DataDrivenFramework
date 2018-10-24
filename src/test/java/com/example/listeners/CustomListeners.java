package com.example.listeners;

import com.example.utilities.TestUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class CustomListeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.setProperty("org.uncommons.reportng.escape-output","false");
        Reporter.log("Capturing screenshot.");
        Reporter.log("<a href="+TestUtil.screenshotName+">Screenshot</a>");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.setProperty("org.uncommons.reportng.escape-output","false");
        Reporter.log("Failure. Test fail.");
        TestUtil.captureScreenshot();
        Reporter.log("Capturing screenshot.");
        Reporter.log("<a href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+"></img></a>");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}