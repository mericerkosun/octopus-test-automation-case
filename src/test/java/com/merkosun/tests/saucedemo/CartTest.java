package com.merkosun.tests.saucedemo;

import com.merkosun.base.BaseTest;
import com.merkosun.config.ConfigManager;
import com.merkosun.pages.saucedemo.CartPage;
import com.merkosun.pages.saucedemo.LoginPage;
import com.merkosun.pages.saucedemo.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CartTest extends BaseTest {

    private ProductsPage productsPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setupCartTests() {
        ConfigManager config = ConfigManager.getInstance();
        
        new LoginPage(driver)
                .navigateTo(config.saucedemoBaseUrl())
                .login("standard_user", "secret_sauce");
        
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void addedItemShouldBeListedInCartTest() {
        String expectedProductName = productsPage.getFirstProductName();
        productsPage.addToCartByIndex(0);
        
        productsPage.goToCart();
        
        List<String> cartItems = cartPage.getCartItemNames();
        
        Assert.assertEquals(cartPage.getCartItemCount(), 1, "There should be exactly 1 item in the cart");
        Assert.assertTrue(cartItems.contains(expectedProductName), 
                "Cart does not contain the expected product: " + expectedProductName);
    }

    @Test
    public void removedItemShouldDisappearFromCartTest() {
        productsPage.addToCartByIndex(0);
        productsPage.addToCartByIndex(1);
        productsPage.goToCart();
        
        Assert.assertEquals(cartPage.getCartItemCount(), 2, "Cart should have 2 items initially");
        
        cartPage.removeFirstItem();
        
        Assert.assertEquals(cartPage.getCartItemCount(), 1, "Cart item count should be 1 after removing an item");
        Assert.assertEquals(cartPage.getCartBadgeCount(), 1, "Cart badge should show 1 after removing an item");
    }

    @Test
    public void continueShoppingButtonShouldRedirectToProductsPageTest() {
        productsPage.goToCart();
        cartPage.clickContinueShopping();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotNull(currentUrl, "Current URL is null");
        Assert.assertTrue(currentUrl.contains("inventory.html"),
                "Continue Shopping button should redirect to Products page");

    }

    @Test
    public void emptyCartCheckoutShouldNavigateToCheckoutStepOneTest() {
        productsPage.goToCart();

        Assert.assertEquals(cartPage.getCartItemCount(), 0, "Cart should be empty before checkout");

        cartPage.clickCheckout();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotNull(currentUrl, "Current URL is null");
        Assert.assertTrue(currentUrl.contains("checkout-step-one.html"),
                "Checkout button did not navigate to Checkout Step One page");

    }

}
