package com.project.tests.stepdefinitions;

import com.project.framework.base.ExtentManager;
import com.project.framework.base.DriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    WebDriver driver = DriverFactory.getDriver();

    @Given("user navigates to login page")
    public void navigateToLogin() {
        try { ExtentManager.getTest().info("Step: user navigates to login page"); } catch(Exception e) { }
        // implementation...
    }

    @When("user logs in with valid credentials")
    public void loginWithValidCredentials() {
        try { ExtentManager.getTest().info("Step: user logs in with valid credentials"); } catch(Exception e) { }
        // implementation...
    }

    @Then("user should be logged in")
    public void userShouldBeLoggedIn() {
        try { ExtentManager.getTest().info("Step: user should be logged in"); } catch(Exception e) { }
        // implementation...
    }
}
