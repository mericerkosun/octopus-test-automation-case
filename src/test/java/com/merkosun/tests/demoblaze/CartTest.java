package com.merkosun.tests.demoblaze;

import com.merkosun.base.BaseTest;
import com.merkosun.pages.demoblaze.CartPage;
import com.merkosun.pages.demoblaze.HomePage;
import com.merkosun.pages.demoblaze.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeMethod
    public void localSetup() {
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        homePage.navigateTo();
    }

    @Test(description = "Verify adding items to cart")
    public void testAddToCart() {
        String p1 = "Samsung galaxy s6";
        String p2 = "Nokia lumia 1520";

        homePage.filterByPhones();
        homePage.selectProductByName(p1);
        productPage.addToCart();
        productPage.getAlertTextAndAccept();
        
        homePage.clickHome();

        homePage.filterByPhones();
        homePage.selectProductByName(p2);
        productPage.addToCart();
        productPage.getAlertTextAndAccept();

        homePage.clickCart();

        Assert.assertEquals(cartPage.getCartItemCount(), 2, "Cart item count mismatch!");
    }

    @Test(description = "Verify removing items from cart")
    public void testRemoveFromCart() {
        homePage.filterByPhones();
        homePage.selectProductByName("Samsung galaxy s6");
        productPage.addToCart();
        productPage.getAlertTextAndAccept();
        
        homePage.clickCart();
        int initialCount = cartPage.getCartItemCount();
        
        cartPage.deleteFirstItem();
        int finalCount = cartPage.getCartItemCount();
        
        Assert.assertEquals(finalCount, initialCount - 1, "Item count should decrease after deletion!");
    }

    @Test(description = "Verify total price validation in cart")
    public void testCartTotalPrice() {
        homePage.filterByPhones();
        homePage.selectProductByName("Samsung galaxy s6");
        productPage.addToCart();
        productPage.getAlertTextAndAccept();

        homePage.clickCart();
        
        String totalPrice = cartPage.getTotalPrice();
        Assert.assertFalse(totalPrice.isEmpty() || totalPrice.equals("0"), "Total price should be displayed!");
    }
}
