package com.merkosun.tests.demoblaze;

import com.merkosun.base.BaseTest;
import com.merkosun.pages.demoblaze.DemoBlazeConstants;
import com.merkosun.pages.demoblaze.HomePage;
import com.merkosun.pages.demoblaze.Modals;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthTest extends BaseTest {

    private static final Faker faker = new Faker();

    @Test(description = "Verify successful signup with a new user")
    public void testSuccessfulSignup() {
        HomePage homePage = new HomePage(getDriver());
        Modals modals = new Modals(getDriver());
        homePage.navigateTo();

        String username = "user_" + faker.number().digits(10);
        String password = DemoBlazeConstants.DEFAULT_PASSWORD;

        homePage.clickSignup();
        modals.register(username, password);
        
        String alertText = modals.getAlertTextAndAccept();
        modals.clickCloseSignup();
        modals.waitForSignupModalToClose();
        Assert.assertEquals(alertText, DemoBlazeConstants.SIGNUP_SUCCESS, "Signup success message mismatch!");
    }

    @Test(priority = 2, description = "Verify signup fails when using an existing username")
    public void testSignupWithExistingUser() {
        HomePage homePage = new HomePage(getDriver());
        Modals modals = new Modals(getDriver());
        homePage.navigateTo();

        String username = "user_" + faker.number().digits(10); 
        String password = DemoBlazeConstants.DEFAULT_PASSWORD;

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
        Assert.assertEquals(alertText, DemoBlazeConstants.USER_ALREADY_EXISTS, "Error message for existing user mismatch!");
    }

    @Test(priority = 3, description = "Verify successful login")
    public void testSuccessfulLogin() {
        HomePage homePage = new HomePage(getDriver());
        Modals modals = new Modals(getDriver());
        homePage.navigateTo();

        String username = "user_" + faker.number().digits(10);
        String password = DemoBlazeConstants.DEFAULT_PASSWORD;

        homePage.clickSignup();
        modals.register(username, password);
        modals.getAlertTextAndAccept();
        modals.clickCloseSignup();
        modals.waitForSignupModalToClose();

        homePage.clickLogin();
        modals.login(username, password);
        modals.waitForLoginModalToClose();
        
        String welcomeMsg = homePage.getWelcomeMessage();
        Assert.assertTrue(welcomeMsg.contains(DemoBlazeConstants.WELCOME_PREFIX + " " + username), "Welcome message should contain username");
    }

    @Test(description = "Verify login fails with incorrect password")
    public void testLoginWithInvalidCredentials() {
        HomePage homePage = new HomePage(getDriver());
        Modals modals = new Modals(getDriver());
        homePage.navigateTo();

        homePage.clickLogin();
        modals.login("non_existent_user_" + faker.number().digits(10), DemoBlazeConstants.DEFAULT_WRONG_PASSWORD);
        String alertText = modals.getAlertTextAndAccept();
        modals.clickCloseLogin();
        modals.waitForLoginModalToClose();
        Assert.assertEquals(alertText, DemoBlazeConstants.USER_DOES_NOT_EXIST, "Error message for non-existent user mismatch!");
    }

    @Test(priority = 4, description = "Verify successful logout")
    public void testLogout() {
        HomePage homePage = new HomePage(getDriver());
        Modals modals = new Modals(getDriver());
        homePage.navigateTo();

        String username = "user_" + faker.number().digits(10);
        String password = DemoBlazeConstants.DEFAULT_PASSWORD;

        homePage.clickSignup();
        modals.register(username, password);
        modals.getAlertTextAndAccept();
        modals.clickCloseSignup();
        modals.waitForSignupModalToClose();

        homePage.clickLogin();
        modals.login(username, password);
        modals.waitForLoginModalToClose();
        
        String welcomeMsg = homePage.getWelcomeMessage();
        Assert.assertTrue(welcomeMsg.contains(DemoBlazeConstants.WELCOME_PREFIX + " " + username), "Should be logged in first");
        
        Assert.assertTrue(homePage.isLogoutLinkVisible(), "Logout link should be visible after login");

        homePage.logout();
        Assert.assertTrue(homePage.isLoginLinkVisible(), "Login link should be visible after logout");
    }
}
