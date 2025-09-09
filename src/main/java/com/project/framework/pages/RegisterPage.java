package com.project.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    private By linkRegister = By.linkText("Register");
    private By genderMale = By.id("gender-male");
    private By firstName = By.id("FirstName");
    private By lastName = By.id("LastName");
    private By emailField = By.id("Email");
    private By passwordField = By.id("Password");
    private By confirmPasswordField = By.id("ConfirmPassword");
    private By registerButton = By.id("register-button");
    private By resultMessage = By.cssSelector("div.result");

    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    public void openRegisterPage() {
        // click register link (reliable) and wait for the form
        driver.findElement(linkRegister).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
    }

    public void registerUser(String email, String password) {
        openRegisterPage();
        wait.until(ExpectedConditions.elementToBeClickable(genderMale)).click();
        wait.until(ExpectedConditions.elementToBeClickable(firstName)).clear();
        driver.findElement(firstName).sendKeys("Test");
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys("User");
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).clear();
        driver.findElement(confirmPasswordField).sendKeys(password);
        driver.findElement(registerButton).click();
        // wait for success or validation message
        wait.until(ExpectedConditions.or(
            ExpectedConditions.visibilityOfElementLocated(resultMessage),
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.validation-summary-errors, div.message-error"))
        ));
    }

    public String getResultMessage() {
        try {
            return driver.findElement(resultMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
