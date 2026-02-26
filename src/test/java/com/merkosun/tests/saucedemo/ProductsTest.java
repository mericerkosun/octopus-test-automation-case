package com.merkosun.tests.saucedemo;

import com.merkosun.base.BaseTest;
import com.merkosun.config.ConfigManager;
import com.merkosun.pages.saucedemo.LoginPage;
import com.merkosun.pages.saucedemo.ProductsPage;
import java.util.Collections;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductsTest extends BaseTest {

    private ProductsPage productsPage;

    @BeforeMethod
    public void loginAndNavigate() {
        ConfigManager config = ConfigManager.getInstance();
        new LoginPage(driver)
                .navigateTo(config.saucedemoBaseUrl())
                .login("standard_user", "secret_sauce");
        productsPage = new ProductsPage(driver);
    }

    @Test
    public void productsAreListedTest() {
        int count = productsPage.getProductCount();
        Assert.assertTrue(count > 0,
                "Expected at least one product on the inventory page, but found: " + count);
    }

    @Test
    public void addSingleItemToCartTest() {
        productsPage.addToCartByIndex(0);

        int badge = productsPage.getCartBadgeCount();
        Assert.assertEquals(badge, 1,
                "Cart badge should show 1 after adding one item, but showed: " + badge);
    }

    @Test
    public void addMultipleItemsToCartTest() {
        productsPage.addToCartByIndex(0);
        productsPage.addToCartByIndex(1);

        int badge = productsPage.getCartBadgeCount();
        Assert.assertEquals(badge, 2,
                "Cart badge should show 2 after adding two items, but showed: " + badge);
    }

    @Test
    public void sortByNameZtoATest() {
        // 1. Sıralamadan önce sayfadaki listeyi alıp Java'da ters sıralıyoruz (Z-A)
        List<String> expectedNames = productsPage.getAllProductNames();
        expectedNames.sort(Collections.reverseOrder());

        // 2. Sayfada UI üzerinden sıralıyoruz
        productsPage.sortBy("Name (Z to A)");

        // 3. Sıralama sonrası sayfadaki yeni listeyi alıyoruz
        List<String> actualNames = productsPage.getAllProductNames();

        // 4. Doğrulama: Java'nın sıraladığı ile sayfanın sıraladığı aynı mı?
        Assert.assertEquals(actualNames, expectedNames, "Z to A sorting failed");
    }

    @Test
    public void sortByNameAtoZTest() {
        // Sayfa zaten ilk başta A-Z geliyor ama önce Z-A yapıp sonra tekrar A-Z yaparak test edelim
        productsPage.sortBy("Name (Z to A)");

        List<String> expectedNames = productsPage.getAllProductNames();
        Collections.sort(expectedNames); // Java'da A-Z sırala

        productsPage.sortBy("Name (A to Z)");
        List<String> actualNames = productsPage.getAllProductNames();

        Assert.assertEquals(actualNames, expectedNames, "A to Z sorting failed");
    }

    @Test
    public void sortByPriceLowToHighTest() {
        List<Double> expectedPrices = productsPage.getAllProductPrices();
        Collections.sort(expectedPrices);

        productsPage.sortBy("Price (low to high)");
        List<Double> actualPrices = productsPage.getAllProductPrices();

        Assert.assertEquals(actualPrices, expectedPrices, "Price (Low to High) sorting failed");
    }

    @Test
    public void sortByPriceHighToLowTest() {
        List<Double> expectedPrices = productsPage.getAllProductPrices();
        expectedPrices.sort(Collections.reverseOrder());

        productsPage.sortBy("Price (high to low)");
        List<Double> actualPrices = productsPage.getAllProductPrices();

        Assert.assertEquals(actualPrices, expectedPrices, "Price (High to Low) sorting failed");
    }

}
