package com.merkosun.base;

import com.merkosun.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseTest {

    @BeforeSuite
    public void setupSuite() {
        Properties props = new Properties();
        props.setProperty("Project", "Octopus Test Automation");
        props.setProperty("Environment", System.getProperty("headless", "false").equals("true") ? "Docker/Headless" : "Local/Headful");
        props.setProperty("Browser", "Chromium/Chrome");
        props.setProperty("OS", System.getProperty("os.name"));
        props.setProperty("Architecture", System.getProperty("os.arch"));

        try {
            File resultsDir = new File("target/allure-results");
            if (!resultsDir.exists()) resultsDir.mkdirs();
            FileOutputStream out = new FileOutputStream("target/allure-results/environment.properties");
            props.store(out, "Allure Environment Properties");
            out.close();
        } catch (IOException e) {
            System.err.println("Could not write Allure environment properties: " + e.getMessage());
        }
    }

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @BeforeMethod
    public void setup() {
        // Driver is initialized on first call to getDriver() in test
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

            registerPage = new com.merkosun.pages.parabank.RegisterPage(getDriver());
            registerPage.navigateTo();
            registerPage.register(user);

            success = registerPage.isRegistrationSuccessful();
            if (!success) {
                System.out.println("Registration attempt " + attempt + " failed for user: " + user.getUsername() + ". Retrying...");
            }
        } while (!success && attempt < maxRetries);

        Assert.assertTrue(success, "Registration failed after " + maxRetries + " attempts for user: " + user.getUsername());

        registerPage.logout();

        try {
            new org.openqa.selenium.support.ui.WebDriverWait(getDriver(), java.time.Duration.ofSeconds(5))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("index.htm"));
        } catch (Exception ignored) {}

        return user;
    }
}
