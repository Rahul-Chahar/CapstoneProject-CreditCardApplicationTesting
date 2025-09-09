package com.project.framework.base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.Scenario;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    // Capture screenshot and save, return absolute path for Extent to attach
    public static String captureForExtent(String name) {
        try {
            WebDriver driver = DriverFactory.getDriver();
            if (driver == null) return null;

            byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            String dir = System.getProperty("user.dir") + File.separator + "output" + File.separator + "screenshots";
            Files.createDirectories(Paths.get(dir));
            String safeName = name.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
            String filename = dir + File.separator + safeName + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".png";
            try (FileOutputStream fos = new FileOutputStream(new File(filename))) {
                fos.write(bytes);
            }
            return filename;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // existing method used by cucumber to embed screenshot in cucumber report; keep for compatibility
    public static void captureForScenario(Scenario scenario) {
        try {
            WebDriver driver = DriverFactory.getDriver();
            if (driver == null) return;
            byte[] bytes = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(bytes, "image/png", scenario.getName());
            // also save to output folder for Extent
            captureForExtent(scenario.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
