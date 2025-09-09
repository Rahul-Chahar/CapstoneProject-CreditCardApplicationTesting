package com.project.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private By firstName = By.id("BillingNewAddress_FirstName");
    private By lastName = By.id("BillingNewAddress_LastName");
    private By email = By.id("BillingNewAddress_Email");
    private By country = By.id("BillingNewAddress_CountryId");
    private By state = By.id("BillingNewAddress_StateProvinceId");
    private By city = By.id("BillingNewAddress_City");
    private By address1 = By.id("BillingNewAddress_Address1");
    private By zip = By.id("BillingNewAddress_ZipPostalCode");
    private By phone = By.id("BillingNewAddress_PhoneNumber");
    private By continueButton = By.cssSelector("div#billing-buttons-container input.button-1.new-address-next-step-button");

    public void fillBillingAddress(String fName, String lName, String mail, String countryValue,
                                   String stateValue, String cityValue, String addr1, String zipCode, String phoneNo) {
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(mail);

        new Select(driver.findElement(country)).selectByVisibleText(countryValue);
        new Select(driver.findElement(state)).selectByVisibleText(stateValue);

        driver.findElement(city).clear();
        driver.findElement(city).sendKeys(cityValue);
        driver.findElement(address1).clear();
        driver.findElement(address1).sendKeys(addr1);
        driver.findElement(zip).clear();
        driver.findElement(zip).sendKeys(zipCode);
        driver.findElement(phone).clear();
        driver.findElement(phone).sendKeys(phoneNo);
    }

    public boolean isContinueClickable() {
        try {
            return driver.findElement(continueButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
