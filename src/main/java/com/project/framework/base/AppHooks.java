package com.project.framework.base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.WebDriver;

public class AppHooks {
    private static final ExtentReports extent = ExtentReportManager.getInstance();

    @Before
    public void beforeScenario(Scenario scenario) {
        // Initialize driver
        String browser = ConfigReader.get("browser");
        WebDriver driver = DriverFactory.initDriver(browser);
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("base.url"));

        // Create a single Extent test per Cucumber scenario (this makes Extent show test cases)
        ExtentTest test = extent.createTest(scenario.getName());
        ExtentManager.setTest(test);
        test.info("Scenario started: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        ExtentTest test = ExtentManager.getTest();
        try {
            // Capture screenshot for Extent (always capture to show result in report)
            String screenshotPath = ScreenshotUtils.captureForExtent(scenario.getName());
            if (screenshotPath != null) {
                // attach screenshot to extent test (works with absolute or relative path)
                test.addScreenCaptureFromPath(screenshotPath);
            }

            if (scenario.isFailed()) {
                // log failure with cucumber's exception message if available
                test.fail("Scenario failed: " + scenario.getName());
            } else {
                test.pass("Scenario passed: " + scenario.getName());
            }
        } catch (Exception e) {
            // ensure we still attempt to quit driver and flush
            if (test != null) test.warning("Error while finalizing Extent for scenario: " + e.getMessage());
        } finally {
            // Quit WebDriver
            DriverFactory.quitDriver();

            // Flush ensures report file is updated. Flushing after every scenario is fine for small suites.
            extent.flush();
            ExtentManager.removeTest();
        }
    }
}
