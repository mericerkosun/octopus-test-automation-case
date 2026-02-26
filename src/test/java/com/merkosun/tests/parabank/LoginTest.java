package com.merkosun.tests.parabank;

import com.merkosun.base.BaseTest;
import com.merkosun.config.ConfigManager;
import com.merkosun.pages.parabank.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setupPage() {
        driver.get(ConfigManager.getInstance().parabankBaseUrl());
        loginPage = new LoginPage(driver);
    }

    @Test
    public void successfulLoginTest() {
        loginPage.login("john", "demo");
        Assert.assertTrue(loginPage.isLoginSuccessful(),
                "Accounts Overview header should be visible after successful login.");
    }

    // Not: Parabank demo ortami, rastgele girilen hatali sifreleri de gecerli
    // kabul edip iceri almaktadir. Bu yuzden, hatali giris validation (hata mesaji) testini
    // dogru yapabilmek adina negatif senaryo olarak "Bos Birakilan (Empty)" alanlar test edilmistir.
    @Test
    public void emptyCredentialsShouldShowErrorTest() {
        loginPage.login("", "");
        String expectedError = "Please enter a username and password.";
        Assert.assertEquals(loginPage.getErrorMessage(), expectedError,
                "Error message mismatch after empty login attempt.");
    }
}
