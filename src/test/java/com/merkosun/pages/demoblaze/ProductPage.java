package com.merkosun.pages.demoblaze;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends Navbar {

    private static final By PRODUCT_NAME = By.className("name");
    private static final By PRODUCT_PRICE = By.className("price-container");
    private static final By PRODUCT_DESCRIPTION = By.id("more-information");
    private static final By ADD_TO_CART_BUTTON = By.xpath("//a[text()='Add to cart']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return getTextFromElement(PRODUCT_NAME);
    }

    public String getProductPrice() {
        return getTextFromElement(PRODUCT_PRICE);
    }

    public String getProductDescription() {
        return getTextFromElement(PRODUCT_DESCRIPTION);
    }

    public void addToCart() {
        clickToElement(ADD_TO_CART_BUTTON);
    }

}
