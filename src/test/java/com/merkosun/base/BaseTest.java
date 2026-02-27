package com.merkosun.base;

import com.merkosun.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

    protected com.merkosun.model.UserData registerUniqueUser() {
        com.merkosun.model.UserData user = new com.merkosun.model.UserData.Builder()
                .firstName("Test")
                .lastName("User")
                .address("123 Test St")
                .city("TestCity")
                .state("TS")
                .zipCode("12345")
                .phone("1234567890")
                .ssn("999-99-9999")
                .username("user" + System.currentTimeMillis() + (int)(Math.random() * 1000))
                .password("password123")
                .build();

        com.merkosun.pages.parabank.RegisterPage registerPage = new com.merkosun.pages.parabank.RegisterPage(driver);
        registerPage.navigateTo();
        registerPage.register(user);

        // Wait for registration to finish and verify
        Assert.assertTrue(registerPage.isRegistrationSuccessful(),
                "Registration failed for user: " + user.getUsername());

        // Logout after registration to let the test start from Login page
        registerPage.logout();

        // A small delay to ensure the session/account is ready on the server-side
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        return user;
    }
}
