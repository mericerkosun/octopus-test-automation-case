package com.merkosun.tests.demoblaze;

import com.merkosun.base.BaseTest;
import com.merkosun.pages.demoblaze.HomePage;
import com.merkosun.pages.demoblaze.Modals;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AuthTest extends BaseTest {

    private HomePage homePage;
    private Modals modals;
    private Faker faker;

    @BeforeMethod
    public void localSetup() {
        homePage = new HomePage(driver);
        modals = new Modals(driver);
        faker = new Faker();
        homePage.navigateTo();
    }

    @Test(description = "Verify successful signup with a new user")
    public void testSuccessfulSignup() {
        String username = "user_" + faker.number().digits(10);
        String password = "password123";

        homePage.clickSignup();
        modals.register(username, password);
        
        String alertText = modals.getAlertTextAndAccept();
        modals.clickCloseSignup();
        modals.waitForSignupModalToClose();
        Assert.assertEquals(alertText, "Sign up successful.", "Signup success message mismatch!");
    }

    @Test(priority = 2, description = "Verify signup fails when using an existing username")
    public void testSignupWithExistingUser() {
        String username = "user_" + faker.number().digits(10); 
        String password = "password123";

        homePage.clickSignup();
        modals.register(username, password);
        modals.getAlertTextAndAccept();
        modals.clickCloseSignup();
        modals.waitForSignupModalToClose();

        homePage.clickSignup();
        modals.register(username, password);
        
        String alertText = modals.getAlertTextAndAccept();
        modals.clickCloseSignup();
        modals.waitForSignupModalToClose();
        Assert.assertEquals(alertText, "This user already exist.", "Error message for existing user mismatch!");
    }

    @Test(priority = 3, description = "Verify successful login")
    public void testSuccessfulLogin() {
        String username = "user_" + faker.number().digits(10);
        String password = "password123";

        homePage.clickSignup();
        modals.register(username, password);
        modals.getAlertTextAndAccept();
        modals.clickCloseSignup();
        modals.waitForSignupModalToClose();

        homePage.clickLogin();
        modals.login(username, password);
        modals.waitForLoginModalToClose();
        
        String welcomeMsg = homePage.getWelcomeMessage();
        Assert.assertTrue(welcomeMsg.contains("Welcome " + username), "Welcome message should contain username");
    }

    @Test(description = "Verify login fails with incorrect password")
    public void testLoginWithInvalidCredentials() {
        homePage.clickLogin();
        modals.login("non_existent_user_" + faker.number().digits(10), "wrongpassword");
        String alertText = modals.getAlertTextAndAccept();
        modals.clickCloseLogin();
        modals.waitForLoginModalToClose();
        Assert.assertEquals(alertText, "User does not exist.", "Error message for non-existent user mismatch!");
    }

    @Test(priority = 4, description = "Verify successful logout")
    public void testLogout() {
        String username = "user_" + faker.number().digits(10);
        String password = "password123";

        homePage.clickSignup();
        modals.register(username, password);
        modals.getAlertTextAndAccept();
        modals.clickCloseSignup();
        modals.waitForSignupModalToClose();

        homePage.clickLogin();
        modals.login(username, password);
        modals.waitForLoginModalToClose();
        
        String welcomeMsg = homePage.getWelcomeMessage();
        Assert.assertTrue(welcomeMsg.contains("Welcome " + username), "Should be logged in first");
        
        Assert.assertTrue(homePage.isLogoutLinkVisible(), "Logout link should be visible after login");

        homePage.logout();
        Assert.assertTrue(homePage.isLoginLinkVisible(), "Login link should be visible after logout");
    }
}
