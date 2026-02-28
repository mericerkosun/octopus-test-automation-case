package com.merkosun.tests.demoblaze;

import com.merkosun.base.BaseTest;
import com.merkosun.pages.demoblaze.HomePage;
import com.merkosun.pages.demoblaze.ProductPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ProductTest extends BaseTest {

    private HomePage homePage;
    private ProductPage productPage;

    @BeforeMethod
    public void localSetup() {
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        homePage.navigateTo();
    }

    @Test(description = "Verify product filtering by categories")
    public void testCategoryFiltering() {
        homePage.filterByPhones();
        List<WebElement> phones = homePage.getProductElements();
        Assert.assertFalse(phones.isEmpty(), "Phones list should not be empty!");
        
        homePage.filterByLaptops();
        List<WebElement> laptops = homePage.getProductElements();
        Assert.assertFalse(laptops.isEmpty(), "Laptops list should not be empty!");

        homePage.filterByMonitors();
        List<WebElement> monitors = homePage.getProductElements();
        Assert.assertFalse(monitors.isEmpty(), "Monitors list should not be empty!");
    }

    @Test(description = "Verify product detail page information")
    public void testProductDetails() {
        String expectedProductName = "Samsung galaxy s6";
        
        homePage.filterByPhones();
        homePage.selectProductByName(expectedProductName);

        Assert.assertEquals(productPage.getProductName(), expectedProductName, "Product name mismatch in detail page!");
        Assert.assertTrue(productPage.getProductPrice().contains("$360"), "Product price mismatch!");
        Assert.assertFalse(productPage.getProductDescription().isEmpty(), "Product description should not be empty!");
    }
}
