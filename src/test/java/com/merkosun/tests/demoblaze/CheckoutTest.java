package com.merkosun.tests.demoblaze;

import com.merkosun.base.BaseTest;
import com.merkosun.pages.demoblaze.CartPage;
import com.merkosun.pages.demoblaze.HomePage;
import com.merkosun.pages.demoblaze.ProductPage;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    private static final Faker faker = new Faker();

    @Test(description = "Verify successful product purchase flow")
    public void testSuccessfulPurchase() {
        HomePage homePage = new HomePage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        homePage.navigateTo();

        homePage.filterByPhones();
        homePage.selectProductByName("Samsung galaxy s6");
        productPage.addToCart();
        productPage.getAlertTextAndAccept();

        homePage.clickCart();
        cartPage.clickPlaceOrder();

        cartPage.fillPurchaseForm(
                faker.name().fullName(),
                faker.address().country(),
                faker.address().city(),
                faker.finance().creditCard(),
                "12",
                "2026"
        );

        Assert.assertTrue(cartPage.isPurchaseSuccessful(), "Purchase should be successful!");
        cartPage.clickOkOnConfirmation();
        
        Assert.assertTrue(homePage.isLoginLinkVisible() || homePage.isLogoutLinkVisible(), "Should be back on a valid page state");
    }

    @Test(description = "Verify purchase form validation (Negative case)")
    public void testPurchaseWithMissingInfo() {
        HomePage homePage = new HomePage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        homePage.navigateTo();

        homePage.filterByPhones();
        homePage.selectProductByName("Samsung galaxy s6");
        productPage.addToCart();
        productPage.getAlertTextAndAccept();

        homePage.clickCart();
        cartPage.clickPlaceOrder();

        cartPage.fillPurchaseForm("", "", "", "", "", "");
        
        String alertText = cartPage.getAlertText();
        Assert.assertTrue(alertText.contains("Please fill out Name and Creditcard"), "Validation message mismatch! Found: " + alertText);
        cartPage.acceptAlert();
    }
}
