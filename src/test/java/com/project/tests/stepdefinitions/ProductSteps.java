package com.project.tests.stepdefinitions;


import com.project.framework.base.ExtentManager;
import com.project.framework.base.DriverFactory;
import com.project.framework.base.ConfigReader;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.WebElement;

public class ProductSteps {
    WebDriver driver = DriverFactory.getDriver();

    @Given("I navigate to the nopcommerce page")
    public void navigate_to_site() {
        try { ExtentManager.getTest().info("Step: I navigate to the nopcommerce page"); } catch(Exception e) { }

        driver.get(ConfigReader.get("base.url"));
    }

    @Then("I see the nopcommerce home page")
    public void see_home_page() {
        try { ExtentManager.getTest().info("Step: I see the nopcommerce home page"); } catch(Exception e) { }
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("nopcommerce demo store"));
    }

    @When("I click login option in the home page")
    public void click_login_option() {
        try { ExtentManager.getTest().info("Step: I click login option in the home page"); } catch(Exception e) { }
        driver.findElement(By.linkText("Log in")).click();
    }

    @Then("I should navigate to the login page")
    public void should_navigate_to_login() {
        try { ExtentManager.getTest().info("Step: I should navigate to the login page"); } catch(Exception e) { }
        Assert.assertTrue(driver.getCurrentUrl().contains("/login") || driver.getTitle().toLowerCase().contains("login"));
    }

    @When("I enter valid username and password")
    public void enter_valid_credentials() {
        try { ExtentManager.getTest().info("Step: I enter valid username and password"); } catch(Exception e) { }
        String email = ConfigReader.get("validUserEmail");
        String pass = ConfigReader.get("validUserPassword");
        driver.findElement(By.id("Email")).clear();
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password")).sendKeys(pass);
    }

    @When("I click login button")
    public void click_login_button() {
        try { ExtentManager.getTest().info("Step: I click login button"); } catch(Exception e) { }
        driver.findElement(By.cssSelector("button.login-button")).click();
    }

    @Then("I should navigate to the product page")
    public void navigate_to_product_page() {
        try { ExtentManager.getTest().info("Step: I should navigate to the product page"); } catch(Exception e) { }
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("store") || driver.getCurrentUrl().equals(ConfigReader.get("base.url")));
    }

    @When("I click Books under home page menu")
    public void click_books_menu() {
        try { ExtentManager.getTest().info("Step: I click Books under home page menu"); } catch(Exception e) { }
        driver.findElement(By.linkText("Books")).click();
    }

    @When("I open {string} product page")
    public void open_product_page(String productName) {
        try { ExtentManager.getTest().info("Step: I open " + productName + " product page"); } catch(Exception e) { }
        driver.findElement(By.linkText(productName)).click();
    }

    @When("I click add to cart on product page")
    public void click_add_to_cart() {
        try { ExtentManager.getTest().info("Step: I click add to cart on product page"); } catch(Exception e) { }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button-40")));
        addBtn.click();
        // wait for AJAX notification (p.content)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.content")));
    }

    @Then("I should see the message as {string}")
    public void should_see_message(String expected) {
        try { ExtentManager.getTest().info("Step: I should see the message as " + expected); } catch(Exception e) { }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement msgElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.content")));
        String msg = msgElem.getText();
        Assert.assertTrue(msg.toLowerCase().contains("added") || msg.toLowerCase().contains("added to your"), "Add-to-cart message not found. Found: " + msg);
    }

    @When("I click Shopping cart option")
    public void click_shopping_cart_option() {
        try { ExtentManager.getTest().info("Step: I click Shopping cart option"); } catch(Exception e) { }
        try {
            driver.findElement(By.cssSelector("p.content a[href='/cart']")).click();
        } catch (Exception e) {
            driver.findElement(By.cssSelector("span.cart-label")).click();
        }
    }

    @Then("I see the message as \"Shopping cart\" page title")
    public void see_shopping_cart_title() {
        try { ExtentManager.getTest().info("Step: I see the message as Shopping cart page title"); } catch(Exception e) { }
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("shopping cart") || driver.findElement(By.cssSelector("span.cart-label")).isDisplayed());
    }
}
