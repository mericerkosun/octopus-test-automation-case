package com.merkosun.tests.saucedemo;

import com.merkosun.base.BaseTest;
import com.merkosun.config.ConfigManager;
import com.merkosun.pages.saucedemo.LoginPage;
import com.merkosun.pages.saucedemo.ProductsPage;
import java.util.Collections;
import java.util.List;
import com.merkosun.pages.saucedemo.SauceConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductsTest extends BaseTest {

    @Test
    public void productsAreListedTest() {
        new LoginPage(getDriver())
                .navigateTo()
                .login(SauceConstants.STANDARD_USER, SauceConstants.SECRET_SAUCE);
        ProductsPage productsPage = new ProductsPage(getDriver());
        
        int count = productsPage.getProductCount();
        Assert.assertTrue(count > 0,
                "Expected at least one product on the inventory page, but found: " + count);
    }

    @Test
    public void addSingleItemToCartTest() {
        new LoginPage(getDriver())
                .navigateTo()
                .login(SauceConstants.STANDARD_USER, SauceConstants.SECRET_SAUCE);
        ProductsPage productsPage = new ProductsPage(getDriver());

        productsPage.addToCartByIndex(0);

        int badge = productsPage.getCartBadgeCount();
        Assert.assertEquals(badge, 1,
                "Cart badge should show 1 after adding one item, but showed: " + badge);
    }


    @Test
    public void addMultipleItemsToCartTest() {
        ConfigManager config = ConfigManager.getInstance();
        new LoginPage(getDriver())
                .navigateTo()
                .login(SauceConstants.STANDARD_USER, SauceConstants.SECRET_SAUCE);
        ProductsPage productsPage = new ProductsPage(getDriver());

        productsPage.addToCartByIndex(0);
        productsPage.addToCartByIndex(1);

        int badge = productsPage.getCartBadgeCount();
        Assert.assertEquals(badge, 2,
                "Cart badge should show 2 after adding two items, but showed: " + badge);
    }

    @Test
    public void sortByNameZtoATest() {
        new LoginPage(getDriver())
                .navigateTo()
                .login(SauceConstants.STANDARD_USER, SauceConstants.SECRET_SAUCE);
        ProductsPage productsPage = new ProductsPage(getDriver());

        List<String> expectedNames = productsPage.getAllProductNames();
        expectedNames.sort(Collections.reverseOrder());

        productsPage.sortBy(SauceConstants.SORT_Z_TO_A);

        List<String> actualNames = productsPage.getAllProductNames();

        Assert.assertEquals(actualNames, expectedNames, "Z to A sorting failed");
    }

    @Test
    public void sortByNameAtoZTest() {
        new LoginPage(getDriver())
                .navigateTo()
                .login(SauceConstants.STANDARD_USER, SauceConstants.SECRET_SAUCE);
        ProductsPage productsPage = new ProductsPage(getDriver());

        productsPage.sortBy(SauceConstants.SORT_Z_TO_A);

        List<String> expectedNames = productsPage.getAllProductNames();
        Collections.sort(expectedNames); 

        productsPage.sortBy(SauceConstants.SORT_A_TO_Z);
        List<String> actualNames = productsPage.getAllProductNames();

        Assert.assertEquals(actualNames, expectedNames, "A to Z sorting failed");
    }

    @Test
    public void sortByPriceLowToHighTest() {
        new LoginPage(getDriver())
                .navigateTo()
                .login(SauceConstants.STANDARD_USER, SauceConstants.SECRET_SAUCE);
        ProductsPage productsPage = new ProductsPage(getDriver());

        List<Double> expectedPrices = productsPage.getAllProductPrices();
        Collections.sort(expectedPrices);

        productsPage.sortBy(SauceConstants.SORT_LOW_TO_HIGH);
        List<Double> actualPrices = productsPage.getAllProductPrices();

        Assert.assertEquals(actualPrices, expectedPrices, "Price (Low to High) sorting failed");
    }

    @Test
    public void sortByPriceHighToLowTest() {
        new LoginPage(getDriver())
                .navigateTo()
                .login(SauceConstants.STANDARD_USER, SauceConstants.SECRET_SAUCE);
        ProductsPage productsPage = new ProductsPage(getDriver());

        List<Double> expectedPrices = productsPage.getAllProductPrices();
        expectedPrices.sort(Collections.reverseOrder());

        productsPage.sortBy(SauceConstants.SORT_HIGH_TO_LOW);
        List<Double> actualPrices = productsPage.getAllProductPrices();

        Assert.assertEquals(actualPrices, expectedPrices, "Price (High to Low) sorting failed");
    }


}
