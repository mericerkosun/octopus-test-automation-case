package com.merkosun.pages.demoblaze;

import com.merkosun.base.BasePage;
import com.merkosun.config.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Navbar extends BasePage {

    private final String baseUrl;

    private static final By BRAND_LOGO = By.id("nava");
    private static final By HOME_LINK = By.xpath("//a[contains(text(),'Home')]");
    private static final By CONTACT_LINK = By.xpath("//a[text()='Contact']");
    private static final By ABOUT_US_LINK = By.xpath("//a[text()='About us']");
    private static final By CART_LINK = By.id("cartur");
    private static final By LOGIN_LINK = By.id("login2");
    private static final By SIGNUP_LINK = By.id("signin2");
    private static final By LOGOUT_LINK = By.id("logout2");
    private static final By WELCOME_USER = By.id("nameofuser");

    public Navbar(WebDriver driver) {
        super(driver);
        this.baseUrl = ConfigManager.getInstance().demoblazeBaseUrl();
    }

    public void navigateTo() {
        driver.get(baseUrl);
    }

    public void clickHome() {
        clickToElement(HOME_LINK);
    }

    public void clickContact() {
        clickToElement(CONTACT_LINK);
    }

    public void clickAboutUs() {
        clickToElement(ABOUT_US_LINK);
    }

    public void clickCart() {
        clickToElement(CART_LINK);
    }

    public void clickLogin() {
        clickToElement(LOGIN_LINK);
    }

    public void clickSignup() {
        clickToElement(SIGNUP_LINK);
    }

    public void logout() {
        clickToElement(LOGOUT_LINK);
    }

    public boolean isLoginLinkVisible() {
        return isElementVisible(LOGIN_LINK);
    }

    public boolean isLogoutLinkVisible() {
        return isElementVisible(LOGOUT_LINK);
    }

    public String getWelcomeMessage() {
        waitForTextToAppear(WELCOME_USER, "Welcome");
        return getTextFromElement(WELCOME_USER);
    }
}
