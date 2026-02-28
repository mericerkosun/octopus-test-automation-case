package com.merkosun.tests.saucedemo;

import com.merkosun.base.BaseTest;
import com.merkosun.config.ConfigManager;
import com.merkosun.pages.saucedemo.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void successfulLoginTest() {
        ConfigManager config = ConfigManager.getInstance();
        LoginPage loginPage = new LoginPage(getDriver()).navigateTo(config.saucedemoBaseUrl());
        loginPage.login("standard_user", "secret_sauce");


        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertNotNull(currentUrl, "URL returned null, page could not be loaded");
        Assert.assertTrue(
                currentUrl.contains("inventory"),
                "Expected to be redirected to inventory page after login. Actual URL: " + currentUrl
        );
    }

    @Test
    public void lockedOutUserLoginTest() {
        ConfigManager config = ConfigManager.getInstance();
        LoginPage loginPage = new LoginPage(getDriver()).navigateTo(config.saucedemoBaseUrl());
        loginPage.login("locked_out_user", "secret_sauce");


        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for locked out user"
        );
        Assert.assertTrue(
                loginPage.getErrorMessage().contains("locked out"),
                "Error message should mention 'locked out'. Actual: " + loginPage.getErrorMessage()
        );
    }

    @Test
    public void invalidPasswordLoginTest() {
        ConfigManager config = ConfigManager.getInstance();
        LoginPage loginPage = new LoginPage(getDriver()).navigateTo(config.saucedemoBaseUrl());
        loginPage.login("standard_user", "wrong_password");


        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for invalid password"
        );
    }

    @Test
    public void emptyFieldsLoginTest() {
        ConfigManager config = ConfigManager.getInstance();
        LoginPage loginPage = new LoginPage(getDriver()).navigateTo(config.saucedemoBaseUrl());
        loginPage.login("", "");


        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed when fields are empty"
        );
    }



}
