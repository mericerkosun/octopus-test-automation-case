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
        com.merkosun.model.UserData user;
        com.merkosun.pages.parabank.RegisterPage registerPage;
        boolean success = false;
        int maxRetries = 2;
        int attempt = 0;

        do {
            attempt++;
            user = new com.merkosun.model.UserData.Builder()
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

            registerPage = new com.merkosun.pages.parabank.RegisterPage(driver);
            registerPage.navigateTo();
            registerPage.register(user);

            success = registerPage.isRegistrationSuccessful();
            if (!success) {
                System.out.println("Registration attempt " + attempt + " failed for user: " + user.getUsername() + ". Retrying...");
            }
        } while (!success && attempt < maxRetries);

        Assert.assertTrue(success, "Registration failed after " + maxRetries + " attempts for user: " + user.getUsername());

        registerPage.logout();

        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        return user;
    }
}
