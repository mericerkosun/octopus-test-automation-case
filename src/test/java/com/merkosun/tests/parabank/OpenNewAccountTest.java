package com.merkosun.tests.parabank;

import com.merkosun.base.BaseTest;
import com.merkosun.pages.parabank.LoginPage;
import com.merkosun.pages.parabank.OpenNewAccountPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * OpenNewAccountTest: Yeni banka hesabı açma akışını doğrular.
 * Pozitif (Checking): Giriş → Checking hesabı aç → Hesap ID üretildi.
 * Pozitif (Savings): Giriş → Savings hesabı aç → Hesap ID üretildi.
 * Negatif: Sayfa açılmadan doğruca hesap ID kontrolü → hiçbir hesap ID görünmemeli.
 */
public class OpenNewAccountTest extends BaseTest {

    @Test(description = "Checking account should be successfully opened and a new ID generated")
    public void testOpenNewCheckingAccount() {
        com.merkosun.model.UserData user = registerUniqueUser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");

        OpenNewAccountPage openNewAccountPage = new OpenNewAccountPage(driver);
        openNewAccountPage.navigateToOpenAccount();
        openNewAccountPage.openAccount("CHECKING");

        Assert.assertTrue(openNewAccountPage.isNewAccountCreated(),
                "New Checking account ID should be displayed");
        String newAccountId = openNewAccountPage.getNewAccountId();
        Assert.assertFalse(newAccountId.isEmpty(),
                "New account ID should not be empty");
    }

    @Test(description = "Savings account should be successfully opened and a new ID generated")
    public void testOpenNewSavingsAccount() {
        com.merkosun.model.UserData user = registerUniqueUser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");

        OpenNewAccountPage openNewAccountPage = new OpenNewAccountPage(driver);
        openNewAccountPage.navigateToOpenAccount();
        openNewAccountPage.openAccount("SAVINGS");

        Assert.assertTrue(openNewAccountPage.isNewAccountCreated(),
                "New Savings account ID should be displayed");
        String newAccountId = openNewAccountPage.getNewAccountId();
        Assert.assertFalse(newAccountId.isEmpty(),
                "New account ID should not be empty");
    }

    @Test(description = "New account ID should not be visible before submitting the account form")
    public void testNewAccountIdNotVisibleBeforeSubmit() {
        com.merkosun.model.UserData user = registerUniqueUser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");

        OpenNewAccountPage openNewAccountPage = new OpenNewAccountPage(driver);
        openNewAccountPage.navigateToOpenAccount();

        // Formu göndermeden önce newAccountId görünmemeli
        Assert.assertFalse(openNewAccountPage.isNewAccountCreated(),
                "New account ID should NOT be visible before the form is submitted");
    }
}
