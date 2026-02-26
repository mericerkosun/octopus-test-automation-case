package com.merkosun.pages.parabank;

import com.merkosun.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By USERNAME_INPUT = By.name("username");
    private static final By PASSWORD_INPUT = By.name("password");
    private static final By LOGIN_BUTTON = By.xpath("//input[@value='Log In']");
    private static final By ERROR_MESSAGE = By.cssSelector("p.error");
    private static final By SUCCESS_HEADER = By.xpath("//h1[contains(text(), 'Accounts Overview')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        typeToElement(USERNAME_INPUT, username);
        typeToElement(PASSWORD_INPUT, password);
        clickToElement(LOGIN_BUTTON);
    }

    public String getErrorMessage() {
        return getTextFromElement(ERROR_MESSAGE);
    }

    public boolean isLoginSuccessful() {
        return isElementVisible(SUCCESS_HEADER);
    }
}
