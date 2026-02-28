package com.merkosun.tests.demoblaze;

import com.merkosun.base.BaseTest;
import com.merkosun.pages.demoblaze.HomePage;
import com.merkosun.pages.demoblaze.ProductPage;
import com.merkosun.pages.demoblaze.DemoBlazeConstants;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ProductTest extends BaseTest {


    @Test(description = "Verify product filtering by categories")
    public void testCategoryFiltering() {
        HomePage homePage = new HomePage(getDriver());
        homePage.navigateTo();

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
        HomePage homePage = new HomePage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());
        homePage.navigateTo();

        String expectedProductName = DemoBlazeConstants.DEFAULT_PRODUCT_PHONE;
        
        homePage.filterByPhones();
        homePage.selectProductByName(expectedProductName);
 
        Assert.assertEquals(productPage.getProductName(), expectedProductName, "Product name mismatch in detail page!");
        Assert.assertTrue(productPage.getProductPrice().contains(DemoBlazeConstants.PRODUCT_S6_PRICE), "Product price mismatch!");
        Assert.assertFalse(productPage.getProductDescription().isEmpty(), "Product description should not be empty!");
    }
}
