package com.merkosun.pages.parabank;

import com.merkosun.base.BasePage;
import com.merkosun.config.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private static final By USERNAME_INPUT = By.name("username");
    private static final By PASSWORD_INPUT = By.name("password");
    private static final By LOGIN_BUTTON   = By.cssSelector("input[type='submit'][value='Log In']");
    private static final By ERROR_MESSAGE  = By.cssSelector("p.error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage navigateTo() {
        driver.get(ConfigManager.getInstance().parabankBaseUrl());
        return this;
    }

    public void login(String username, String password) {
        typeToElement(USERNAME_INPUT, username);
        typeToElement(PASSWORD_INPUT, password);
        clickToElement(LOGIN_BUTTON);
    }

    public boolean isLoginSuccessful() {
        return waitForUrlContains(ParabankConstants.OVERVIEW_URL_PART);
    }

    public boolean isErrorMessageVisible() {
        return isElementVisible(ERROR_MESSAGE);
    }

    public String getErrorMessage() {
        return getTextFromElement(ERROR_MESSAGE);
    }
}
