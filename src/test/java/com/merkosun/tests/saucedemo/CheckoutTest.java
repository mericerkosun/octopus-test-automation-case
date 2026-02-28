package com.merkosun.tests.saucedemo;

import com.merkosun.base.BaseTest;
import com.merkosun.config.ConfigManager;
import com.merkosun.pages.saucedemo.CartPage;
import com.merkosun.pages.saucedemo.CheckoutPage;
import com.merkosun.pages.saucedemo.LoginPage;
import com.merkosun.pages.saucedemo.ProductsPage;
import com.merkosun.pages.saucedemo.SauceConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {


    @Test
    public void emptyInformationShouldShowErrorTest() {
        new LoginPage(getDriver())
                .navigateTo()
                .login(SauceConstants.STANDARD_USER, SauceConstants.SECRET_SAUCE);
        ProductsPage productsPage = new ProductsPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());

        productsPage.addToCartByIndex(0);
        productsPage.goToCart();
        cartPage.clickCheckout();

        checkoutPage.clickContinue();

        String errorMessage = checkoutPage.getErrorMessage();
        Assert.assertEquals(errorMessage, SauceConstants.FIRST_NAME_REQUIRED_ERROR, "Empty form error message mismatch");

    }


    @Test
    public void cancelCheckoutShouldNavigateToCartTest() {
        new LoginPage(getDriver())
                .navigateTo()
                .login(SauceConstants.STANDARD_USER, SauceConstants.SECRET_SAUCE);
        ProductsPage productsPage = new ProductsPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());

        productsPage.addToCartByIndex(0);
        productsPage.goToCart();
        cartPage.clickCheckout();

        checkoutPage.clickCancel();

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertNotNull(currentUrl, "Current URL is null");
        Assert.assertTrue(currentUrl.contains(SauceConstants.CART_URL_PART), "Cancel button did not redirect to Cart page");
    }

}
