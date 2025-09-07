package com.project.framework.base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class AppHooks {
    @Before
    public void beforeScenario() {
        String browser = ConfigReader.get("browser");
        WebDriver driver = DriverFactory.initDriver(browser);
        driver.get(ConfigReader.get("base.url"));
    }

    @After
    public void afterScenario(io.cucumber.java.Scenario scenario) {
        // capture screenshot for report on failure or success
        ScreenshotUtils.captureForScenario(scenario);
        DriverFactory.quitDriver();
    }
}
