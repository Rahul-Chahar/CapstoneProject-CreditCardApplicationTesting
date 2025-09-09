//package com.project.tests.stepdefinitions;
//
//import com.project.framework.base.ExtentManager;
//import com.project.framework.base.DriverFactory;
//import io.cucumber.java.en.*;
//
//import org.openqa.selenium.WebDriver;
//
//public class CheckoutSteps {
//    WebDriver driver = DriverFactory.getDriver();
//
//    @When("a product is in the cart")
//    public void productInCart() {
//        try { ExtentManager.getTest().info("Step: a product is in the cart"); } catch(Exception e) { }
//        // implement add to cart via POM
//    }
//
//    @When("the user proceeds to checkout and applies a valid credit card")
//    public void proceedToCheckoutWithValidCard() {
//        try { ExtentManager.getTest().info("Step: the user proceeds to checkout and applies a valid credit card"); } catch(Exception e) { }
//        // implement payment with valid card; pull data from excel
//    }
//
//    @When("the user attempts payment with an invalid card number")
//    public void checkoutWithInvalidCard() {
//        try { ExtentManager.getTest().info("Step: the user attempts payment with an invalid card number"); } catch(Exception e) { }
//        // negative payment attempt
//    }
//
//    @Then("payment should be processed and order confirmed")
//    public void verifyPaymentSuccess() {
//        try { ExtentManager.getTest().info("Step: payment should be processed and order confirmed"); } catch(Exception e) { }
//    }
//
//    @Then("the payment should be rejected and an error shown")
//    public void verifyPaymentRejected() {
//        try { ExtentManager.getTest().info("Step: the payment should be rejected and an error shown"); } catch(Exception e) { }
//    }
//}
