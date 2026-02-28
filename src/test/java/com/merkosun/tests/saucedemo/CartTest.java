package com.merkosun.tests.saucedemo;

import com.merkosun.base.BaseTest;
import com.merkosun.config.ConfigManager;
import com.merkosun.pages.saucedemo.CartPage;
import com.merkosun.pages.saucedemo.LoginPage;
import com.merkosun.pages.saucedemo.ProductsPage;
import com.merkosun.pages.saucedemo.SauceConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CartTest extends BaseTest {

    @Test
    public void addedItemShouldBeListedInCartTest() {
        new LoginPage(getDriver())
                .navigateTo()
                .login(SauceConstants.STANDARD_USER, SauceConstants.SECRET_SAUCE);
        ProductsPage productsPage = new ProductsPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());

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
        new LoginPage(getDriver())
                .navigateTo()
                .login(SauceConstants.STANDARD_USER, SauceConstants.SECRET_SAUCE);
        ProductsPage productsPage = new ProductsPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());

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
        new LoginPage(getDriver())
                .navigateTo()
                .login(SauceConstants.STANDARD_USER, SauceConstants.SECRET_SAUCE);
        ProductsPage productsPage = new ProductsPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());

        productsPage.goToCart();
        cartPage.clickContinueShopping();

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertNotNull(currentUrl, "Current URL is null");
        Assert.assertTrue(currentUrl.contains(SauceConstants.INVENTORY_URL_PART),
                "Continue Shopping button should redirect to Products page");

    }


    @Test
    public void emptyCartCheckoutShouldNavigateToCheckoutStepOneTest() {
        new LoginPage(getDriver())
                .navigateTo()
                .login(SauceConstants.STANDARD_USER, SauceConstants.SECRET_SAUCE);
        ProductsPage productsPage = new ProductsPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());

        productsPage.goToCart();

        Assert.assertEquals(cartPage.getCartItemCount(), 0, "Cart should be empty before checkout");

        cartPage.clickCheckout();

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertNotNull(currentUrl, "Current URL is null");
        Assert.assertTrue(currentUrl.contains(SauceConstants.CHECKOUT_STEP_ONE_URL_PART),
                "Checkout button did not navigate to Checkout Step One page");

    }


}
