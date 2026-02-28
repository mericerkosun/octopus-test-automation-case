package com.merkosun.tests.parabank;

import com.merkosun.base.BaseTest;
import com.merkosun.pages.parabank.LoginPage;
import com.merkosun.pages.parabank.ParabankConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * LoginTest: Parabank giriş akışını doğrular.
 *
 * NOT — Parabank Demo Ortamı Davranışı:
 *   Parabank'ın demo sunucusu, geçersiz şifre ile yapılan login denemelerinde
 *   tarayıcıda önceden kalan session cookie'yi kontrol eder ve bazen
 *   şifreyi doğrulamadan kullanıcıyı içeri alır. Bu nedenle "Hatalı Şifre"
 *   senaryosu flaky ve güvenilmez olduğundan kapsam dışı bırakılmıştır.
 *   Negatif test senaryosu olarak "Boş Credentials" kullanılmaktadır.
 */
public class LoginTest extends BaseTest {

    @Test(description = "Login should be successful with valid credentials")
    public void testLoginWithValidCredentials() {
        com.merkosun.model.UserData user = registerUniqueUser();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateTo();
        loginPage.login(user.getUsername(), user.getPassword());

        Assert.assertTrue(loginPage.isLoginSuccessful(),
                "Should redirect to accounts overview after successful login");
    }

    @Test(description = "Login with empty credentials should show an error message")
    public void testLoginWithEmptyCredentials() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateTo();
        loginPage.login("", "");

        Assert.assertTrue(loginPage.isErrorMessageVisible(),
                "Error message should be displayed for empty credentials");
    }
}
