package com.project.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By loginLink = By.linkText("Log in");
    private By emailField = By.id("Email");
    private By passwordField = By.id("Password");
    private By loginButton = By.cssSelector("button.login-button");
    private By logoutLink = By.linkText("Log out");

    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    public void openLoginPage() {
        // click login link and wait for email field
        driver.findElement(loginLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
    }

    public void login(String email, String password) {
        // ensure we are on login page
        try {
            if (!driver.getCurrentUrl().contains("/login")) {
                openLoginPage();
            } else {
                wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
            }
        } catch (Exception e) {
            openLoginPage();
        }

        wait.until(ExpectedConditions.elementToBeClickable(emailField)).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        // wait for either logout link (success) or validation message (failure)
        wait.until(ExpectedConditions.or(
            ExpectedConditions.visibilityOfElementLocated(logoutLink),
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.message-error, div.validation-summary-errors"))
        ));
    }

    public boolean isLoggedIn() {
        try {
            return driver.findElement(logoutLink).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
