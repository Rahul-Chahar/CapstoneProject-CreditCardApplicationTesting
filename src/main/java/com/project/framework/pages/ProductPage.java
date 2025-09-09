package com.project.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private By firstPrizePiesLink = By.cssSelector("a[href='/first-prize-pies']");
    private By addToCartButton = By.cssSelector("button#add-to-cart-button-40");
    private By addToCartMessage = By.cssSelector("p.content");
    private By cartLinkInMessage = By.cssSelector("p.content a[href='/cart']");

    public void openFirstPrizePies() {
        driver.findElement(firstPrizePiesLink).click();
    }

    public void clickAddToCart() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(addToCartButton));
        driver.findElement(addToCartButton).click();
    }

    public String getAddToCartMessage() {
        try {
            return driver.findElement(addToCartMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickCartFromMessage() {
        try {
            driver.findElement(cartLinkInMessage).click();
        } catch (Exception e) {
            // fallback
        }
    }
}
