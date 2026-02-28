package com.merkosun.tests.saucedemo;

import com.merkosun.base.BaseTest;
import com.merkosun.config.ConfigManager;
import com.merkosun.pages.saucedemo.CartPage;
import com.merkosun.pages.saucedemo.CheckoutPage;
import com.merkosun.pages.saucedemo.LoginPage;
import com.merkosun.pages.saucedemo.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test
    public void successfulEndToEndCheckoutTest() {
        ConfigManager config = ConfigManager.getInstance();
        new LoginPage(getDriver())
                .navigateTo(config.saucedemoBaseUrl())
                .login("standard_user", "secret_sauce");
        ProductsPage productsPage = new ProductsPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());

        productsPage.addToCartByIndex(0);
        productsPage.goToCart();
        cartPage.clickCheckout();

        checkoutPage.fillPersonalInformation("Meric", "Erkosun", "34000");
        checkoutPage.clickContinue();

        checkoutPage.clickFinish();

        String headerText = checkoutPage.getCompleteHeaderText();
        Assert.assertEquals(headerText, "Checkout: Complete!", "Checkout did not complete successfully");

    }


    @Test
    public void emptyInformationShouldShowErrorTest() {
        ConfigManager config = ConfigManager.getInstance();
        new LoginPage(getDriver())
                .navigateTo(config.saucedemoBaseUrl())
                .login("standard_user", "secret_sauce");
        ProductsPage productsPage = new ProductsPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());

        productsPage.goToCart();
        cartPage.clickCheckout();

        checkoutPage.clickContinue();

        String errorMessage = checkoutPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Error: First Name is required", "Empty form error message mismatch");

    }


    @Test
    public void cancelCheckoutShouldNavigateToCartTest() {
        ConfigManager config = ConfigManager.getInstance();
        new LoginPage(getDriver())
                .navigateTo(config.saucedemoBaseUrl())
                .login("standard_user", "secret_sauce");
        ProductsPage productsPage = new ProductsPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());

        productsPage.goToCart();
        cartPage.clickCheckout();

        checkoutPage.clickCancel();

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertNotNull(currentUrl, "Current URL is null");
        Assert.assertTrue(currentUrl.contains("cart.html"), "Cancel button did not redirect to Cart page");
    }

}
