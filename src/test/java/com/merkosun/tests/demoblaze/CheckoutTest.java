package com.merkosun.tests.demoblaze;

import com.merkosun.base.BaseTest;
import com.merkosun.pages.demoblaze.CartPage;
import com.merkosun.pages.demoblaze.HomePage;
import com.merkosun.pages.demoblaze.ProductPage;
import net.datafaker.Faker;
import com.merkosun.pages.demoblaze.DemoBlazeConstants;
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
        homePage.selectProductByName(DemoBlazeConstants.DEFAULT_PRODUCT_PHONE);
        productPage.addToCart();
        productPage.getAlertTextAndAccept();

        homePage.clickCart();
        cartPage.clickPlaceOrder();

        cartPage.fillPurchaseForm(
                faker.name().fullName(),
                faker.address().country(),
                faker.address().city(),
                faker.finance().creditCard(),
                DemoBlazeConstants.EXPIRY_MONTH,
                DemoBlazeConstants.EXPIRY_YEAR
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
        homePage.selectProductByName(DemoBlazeConstants.DEFAULT_PRODUCT_PHONE);
        productPage.addToCart();
        productPage.getAlertTextAndAccept();

        homePage.clickCart();
        cartPage.clickPlaceOrder();

        cartPage.fillPurchaseForm("", "", "", "", "", "");
        
        String alertText = cartPage.getAlertText();
        Assert.assertTrue(alertText.contains(DemoBlazeConstants.FILL_OUT_NAME_AND_CARD), "Validation message mismatch! Found: " + alertText);
        cartPage.acceptAlert();
    }
}
