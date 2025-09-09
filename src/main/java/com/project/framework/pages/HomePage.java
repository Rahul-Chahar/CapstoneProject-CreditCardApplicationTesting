package com.project.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By registerLink = By.linkText("Register");
    private By loginLink = By.linkText("Log in");
    private By booksCategory = By.linkText("Books");
    private By cartLabel = By.cssSelector("span.cart-label");

    public void openHomePage(String url) {
        driver.get(url);
    }

    public void clickLogin() {
        driver.findElement(loginLink).click();
    }

    public void clickRegister() {
        driver.findElement(registerLink).click();
    }

    public void clickBooksCategory() {
        driver.findElement(booksCategory).click();
    }

    public void clickCartLabel() {
        driver.findElement(cartLabel).click();
    }
}
