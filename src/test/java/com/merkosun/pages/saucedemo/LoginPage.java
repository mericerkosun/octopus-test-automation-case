package com.merkosun.pages.saucedemo;

import org.openqa.selenium.support.ui.ExpectedConditions;
import com.merkosun.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By USERNAME_INPUT = By.id("user-name");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR_MESSAGE = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage navigateTo(String url) {
        driver.get(url);
        return this;
    }

    public void login(String username, String password) {
        typeToElement(USERNAME_INPUT, username);
        typeToElement(PASSWORD_INPUT, password);
        clickToElement(LOGIN_BUTTON);
    }

    public String getErrorMessage() {
        return getTextFromElement(ERROR_MESSAGE);
    }

    public boolean isErrorMessageDisplayed() {
        return isElementVisible(ERROR_MESSAGE);
    }

}
