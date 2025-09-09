package com.project.tests.stepdefinitions;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.project.framework.base.ConfigReader;
import com.project.framework.base.DriverFactory;
import com.project.framework.base.ExtentManager;
import com.project.framework.pages.LoginPage;
import com.project.framework.pages.RegisterPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommonSteps {

    WebDriver driver = DriverFactory.getDriver();
    RegisterPage registerPage = new RegisterPage(driver);
    LoginPage loginPage = new LoginPage(driver);

    @Given("the user opens the application")
    public void the_user_opens_the_application() {
        try { ExtentManager.getTest().info("Step: the user opens the application"); } catch(Exception e){ }
        driver.get(ConfigReader.get("base.url"));
    }

    @When("the user registers with valid details")
    public void the_user_registers_with_valid_details() {
        try { ExtentManager.getTest().info("Step: the user registers with valid details"); } catch(Exception e){ }
        String unique = String.valueOf(Instant.now().toEpochMilli());
        String email = "autotest+" + unique + "@example.com";
        String password = ConfigReader.get("validUserPassword") != null ? ConfigReader.get("validUserPassword") : "Test@1234";
        registerPage.registerUser(email, password);
    }

    @When("the user registers with an existing email")
    public void the_user_registers_with_an_existing_email() {
        try { ExtentManager.getTest().info("Step: the user registers with an existing email"); } catch(Exception e){ }
        String email = ConfigReader.get("validUserEmail");
        String password = ConfigReader.get("validUserPassword") != null ? ConfigReader.get("validUserPassword") : "Test@1234";
        registerPage.registerUser(email, password);
    }

    @Then("the system should show an email already exists error")
    public void the_system_should_show_an_email_already_exists_error() {
        try { ExtentManager.getTest().info("Step: the system should show an email already exists error"); } catch(Exception e){}

        java.time.Duration timeout = java.time.Duration.ofSeconds(15);
        org.openqa.selenium.support.ui.WebDriverWait wait =
            new org.openqa.selenium.support.ui.WebDriverWait(driver, timeout);

        java.util.List<org.openqa.selenium.By> locators = java.util.Arrays.asList(
            org.openqa.selenium.By.cssSelector("div.validation-summary-errors"),
            org.openqa.selenium.By.cssSelector("div.message-error"),
            org.openqa.selenium.By.cssSelector("span.field-validation-error"),
            org.openqa.selenium.By.cssSelector("div.result"),
            org.openqa.selenium.By.cssSelector("div.validation-summary-errors ul li"),
            org.openqa.selenium.By.cssSelector("div.message-error li"),
            org.openqa.selenium.By.cssSelector("label[for='Email'] ~ span") // fallback near email field
        );

        // Wait briefly for any of these locators to appear (non-blocking if none appear)
        try {
            java.util.List<org.openqa.selenium.support.ui.ExpectedCondition<?>> conditions = new java.util.ArrayList<>();
            for (org.openqa.selenium.By b : locators) {
                conditions.add(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(b));
            }
            if (!conditions.isEmpty()) {
                wait.until(org.openqa.selenium.support.ui.ExpectedConditions.or(conditions.toArray(new org.openqa.selenium.support.ui.ExpectedCondition[0])));
            }
        } catch (Exception ignored) {
            // proceed to collect whatever is present
        }

        // collect visible texts from all candidate elements
        java.util.List<String> collected = new java.util.ArrayList<>();
        for (org.openqa.selenium.By b : locators) {
            try {
                java.util.List<org.openqa.selenium.WebElement> els = driver.findElements(b);
                for (org.openqa.selenium.WebElement el : els) {
                    if (el.isDisplayed()) {
                        String t = el.getText();
                        if (t != null && !t.trim().isEmpty()) collected.add(t.trim());
                    }
                }
            } catch (Exception ignored) {}
        }

        // fallback: check page source for common phrases
        String pageSrc = "";
        try { pageSrc = driver.getPageSource().toLowerCase(); } catch (Exception ignored) {}

        // log collected texts to Extent for debugging
        try {
            if (!collected.isEmpty()) {
                for (String s : collected) ExtentManager.getTest().info("Found message: " + s);
            } else {
                ExtentManager.getTest().info("No candidate message elements found; searching page source.");
            }
        } catch (Exception ignored) {}

        boolean found = false;
        java.util.List<String> keywords = java.util.Arrays.asList(
            "the specified email already exists",
            "email already exists",
            "email exists",
            "already registered",
            "email is already registered",
            "specified email already exists"
        );

        for (String text : collected) {
            String lower = text.toLowerCase();
            for (String kw : keywords) {
                if (lower.contains(kw)) {
                    found = true;
                    break;
                }
            }
            if (found) break;
        }

        if (!found) {
            for (String kw : keywords) {
                if (pageSrc.contains(kw)) {
                    found = true;
                    break;
                }
            }
        }

        // final assert with helpful debug message
        if (!found) {
            String debug = "Expected 'email already exists' not found. Collected texts: " + collected + "\nPage snippet: "
                    + (pageSrc.length() > 1000 ? pageSrc.substring(0, 1000) + "..." : pageSrc);
            ExtentManager.getTest().warning(debug);
            org.testng.Assert.fail(debug);
        } else {
            ExtentManager.getTest().pass("Email already exists message verified.");
        }
    }



    @Given("a registered user exists")
    public void a_registered_user_exists() {
        try { ExtentManager.getTest().info("Step: a registered user exists"); } catch(Exception e){ }
        String email = ConfigReader.get("validUserEmail");
        String password = ConfigReader.get("validUserPassword");
        if (email == null || password == null) {
            // nothing to do if config missing
            return;
        }
        // Try login — if login fails then try register (ignore errors)
     // in a_registered_user_exists()
        loginPage.openLoginPage();
        try {
            loginPage.login(email, password);
            if (loginPage.isLoggedIn()) {
                // logout to keep state consistent
                driver.findElement(By.linkText("Log out")).click();
            }
        } catch (Exception e) {
            // try register
            registerPage.openRegisterPage();
            registerPage.registerUser(email, password);
        }

    }

    @When("the user logs in with an incorrect password")
    public void the_user_logs_in_with_an_incorrect_password() {
        try { ExtentManager.getTest().info("Step: the user logs in with an incorrect password"); } catch(Exception e){ }
        String email = ConfigReader.get("validUserEmail");
        // use a wrong password intentionally
        String wrongPass = "wrongPassword123";
        loginPage.openLoginPage();
        loginPage.login(email, wrongPass);
    }

    @When("the user logs in with valid credentials")
    public void the_user_logs_in_with_valid_credentials() {
        try { ExtentManager.getTest().info("Step: the user logs in with valid credentials"); } catch(Exception e){ }
        String email = ConfigReader.get("validUserEmail");
        String pass = ConfigReader.get("validUserPassword");
        loginPage.openLoginPage();
        loginPage.login(email, pass);
    }

    @Then("login should succeed")
    public void login_should_succeed() {
        try { ExtentManager.getTest().info("Step: login should succeed"); } catch(Exception e){ }
        Assert.assertTrue(loginPage.isLoggedIn(), "Expected user to be logged in but was not.");
    }

    @Then("the system should show {string}")
    public void the_system_should_show(String expected) {
        try { ExtentManager.getTest().info("Step: the system should show " + expected); } catch(Exception e){ }
        String pageText = driver.getPageSource();
        boolean found = pageText.contains(expected) || driver.getTitle().contains(expected);
        // Try common message elements
        try {
            String err = driver.findElement(By.cssSelector("div.validation-summary-errors, div.message-error, div.result")).getText();
            if (err != null && err.contains(expected)) found = true;
        } catch (Exception ignored) {}
        Assert.assertTrue(found, "Expected message not found: " + expected);
    }
}
