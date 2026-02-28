package com.merkosun.tests.saucedemo;

import com.merkosun.base.BaseTest;
import com.merkosun.config.ConfigManager;
import com.merkosun.pages.saucedemo.LoginPage;
import com.merkosun.pages.saucedemo.SauceConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void successfulLoginTest() {
        LoginPage loginPage = new LoginPage(getDriver()).navigateTo();
        loginPage.login(SauceConstants.STANDARD_USER, SauceConstants.SECRET_SAUCE);

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertNotNull(currentUrl, "URL returned null, page could not be loaded");
        Assert.assertTrue(
                currentUrl.contains(SauceConstants.INVENTORY_URL_PART),
                "Expected to be redirected to inventory page after login. Actual URL: " + currentUrl
        );
    }

    @Test
    public void lockedOutUserLoginTest() {
        LoginPage loginPage = new LoginPage(getDriver()).navigateTo();
        loginPage.login(SauceConstants.LOCKED_OUT_USER, SauceConstants.SECRET_SAUCE);

        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for locked out user"
        );
        Assert.assertTrue(
                loginPage.getErrorMessage().contains(SauceConstants.LOCKED_OUT_ERROR),
                "Error message should mention 'locked out'. Actual: " + loginPage.getErrorMessage()
        );
    }

    @Test
    public void invalidPasswordLoginTest() {
        ConfigManager config = ConfigManager.getInstance();
        LoginPage loginPage = new LoginPage(getDriver()).navigateTo();
        loginPage.login("standard_user", SauceConstants.INVALID_PASSWORD);


        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for invalid password"
        );
    }

    @Test
    public void emptyFieldsLoginTest() {
        ConfigManager config = ConfigManager.getInstance();
        LoginPage loginPage = new LoginPage(getDriver()).navigateTo();
        loginPage.login("", "");


        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed when fields are empty"
        );
    }



}
