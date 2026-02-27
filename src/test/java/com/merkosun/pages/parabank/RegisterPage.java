package com.merkosun.pages.parabank;

import com.merkosun.base.BasePage;
import com.merkosun.config.ConfigManager;
import com.merkosun.model.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private static final By FIRST_NAME      = By.id("customer.firstName");
    private static final By LAST_NAME       = By.id("customer.lastName");
    private static final By ADDRESS         = By.id("customer.address.street");
    private static final By CITY            = By.id("customer.address.city");
    private static final By STATE           = By.id("customer.address.state");
    private static final By ZIP_CODE        = By.id("customer.address.zipCode");
    private static final By PHONE           = By.id("customer.phoneNumber");
    private static final By SSN             = By.id("customer.ssn");
    private static final By USERNAME        = By.id("customer.username");
    private static final By PASSWORD        = By.id("customer.password");
    private static final By CONFIRM_PASSWORD = By.id("repeatedPassword");
    private static final By REGISTER_BUTTON = By.cssSelector("input[type='submit'][value='Register']");
    private static final By SUCCESS_MESSAGE = By.cssSelector(".title");
    private static final By ERROR_MESSAGE   = By.cssSelector(".error");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public RegisterPage navigateTo() {
        driver.get(ConfigManager.getInstance().parabankBaseUrl() + "/register.htm");
        return this;
    }

    public void register(UserData user) {
        typeToElement(FIRST_NAME, user.getFirstName());
        typeToElement(LAST_NAME, user.getLastName());
        typeToElement(ADDRESS, user.getAddress());
        typeToElement(CITY, user.getCity());
        typeToElement(STATE, user.getState());
        typeToElement(ZIP_CODE, user.getZipCode());
        typeToElement(PHONE, user.getPhone());
        typeToElement(SSN, user.getSsn());
        typeToElement(USERNAME, user.getUsername());
        typeToElement(PASSWORD, user.getPassword());
        typeToElement(CONFIRM_PASSWORD, user.getPassword());
        clickToElement(REGISTER_BUTTON);
    }

    public boolean isRegistrationSuccessful() {
        return getTextFromElement(SUCCESS_MESSAGE).contains("Welcome");
    }

    public boolean isErrorMessageVisible() {
        return isElementVisible(ERROR_MESSAGE);
    }
}
