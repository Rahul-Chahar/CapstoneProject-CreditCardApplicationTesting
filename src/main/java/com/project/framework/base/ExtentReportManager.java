package com.project.framework.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            String ts = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportFile = System.getProperty("user.dir") + "/output/ExtentReport_" + ts + ".html";
            new File(System.getProperty("user.dir") + "/output").mkdirs();
            ExtentSparkReporter spark = new ExtentSparkReporter(reportFile);
            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("Capstone Automation Results");
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Project", "Capstone Credit Card Application");
            extent.setSystemInfo("Author", "Tester");
        }
        return extent;
    }
}
