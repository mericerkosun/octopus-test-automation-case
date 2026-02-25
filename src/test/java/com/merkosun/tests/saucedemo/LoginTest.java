package com.merkosun.tests.saucedemo;

import com.merkosun.base.BaseTest;
import com.merkosun.config.ConfigManager;
import com.merkosun.pages.saucedemo.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void navigateToLoginPage() {
        ConfigManager config = ConfigManager.getInstance();
        loginPage = new LoginPage(driver).navigateTo(config.saucedemoBaseUrl());
    }

    @Test
    public void successfulLoginTest() {
        loginPage.login("standard_user", "secret_sauce");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotNull(currentUrl, "URL returned null, page could not be loaded");
        Assert.assertTrue(
                currentUrl.contains("inventory"),
                "Expected to be redirected to inventory page after login. Actual URL: " + currentUrl
        );
    }

    @Test
    public void lockedOutUserLoginTest() {
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
        loginPage.login("standard_user", "wrong_password");

        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for invalid password"
        );
    }

    @Test
    public void emptyFieldsLoginTest() {
        loginPage.login("", "");

        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed when fields are empty"
        );
    }



}
