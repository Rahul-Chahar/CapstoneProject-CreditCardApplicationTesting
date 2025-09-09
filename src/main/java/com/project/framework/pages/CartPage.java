package com.project.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private By cartLabel = By.cssSelector("span.cart-label");
    private By termsCheckbox = By.id("termsofservice");
    private By checkoutButton = By.id("checkout");

    public boolean isOnCartPage() {
        try {
            return driver.findElement(cartLabel).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void agreeToTerms() {
        if (!driver.findElement(termsCheckbox).isSelected()) {
            driver.findElement(termsCheckbox).click();
        }
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
