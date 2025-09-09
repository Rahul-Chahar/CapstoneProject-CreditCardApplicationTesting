package com.project.tests.stepdefinitions;

import com.project.framework.base.ExtentManager;
import com.project.framework.base.DriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

public class RegistrationSteps {
    WebDriver driver = DriverFactory.getDriver();

    @When("user registers with valid details")
    public void userRegisters() {
        try { ExtentManager.getTest().info("Step: user registers with valid details"); } catch(Exception e) { }
        // implementation...
    }

    @Then("registration should be successful")
    public void registrationSuccessful() {
        try { ExtentManager.getTest().info("Step: registration should be successful"); } catch(Exception e) { }
        // implementation...
    }
}
