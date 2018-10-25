package com.example.utilities;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

public class ExtentReportManager {
    private static ExtentReports extentReports;

    public static ExtentReports getInstanceOfReport() {
        if (extentReports == null) {
            extentReports = new ExtentReports(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\extentReport.html", true, DisplayOrder.OLDEST_FIRST);
            extentReports.loadConfig(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\extentconfig\\ReportsConfig.xml"));
        }
        return extentReports;
    }
}
