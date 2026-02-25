package com.merkosun.base;

import com.merkosun.driver.DriverManager;
import org.openqa.selenium.WebDriver;
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
}
