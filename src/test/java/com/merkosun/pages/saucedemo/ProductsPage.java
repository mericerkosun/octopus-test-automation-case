package com.merkosun.pages.saucedemo;

import com.merkosun.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;

import java.util.List;

public class ProductsPage extends BasePage {

    private static final By PRODUCT_LIST = By.className("inventory_item");
    private static final By CART_BADGE = By.className("shopping_cart_badge");
    private static final By CART_LINK = By.className("shopping_cart_link");
    private static final By SORT_DROPDOWN = By.className("product_sort_container");
    private static final By PRODUCT_NAME = By.className("inventory_item_name");
    private static final By PRODUCT_PRICE      = By.className("inventory_item_price");


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public int getProductCount() {
        List<WebElement> products = driver.findElements(PRODUCT_LIST);
        return products.size();
    }

    public void addToCartByIndex(int index) {
        List<WebElement> addButtons = driver.findElements(
                By.cssSelector(".inventory_item button")
        );
        addButtons.get(index).click();
    }

    public int getCartBadgeCount() {
        List<WebElement> badges = driver.findElements(CART_BADGE);
        if (badges.isEmpty()) return 0;
        return Integer.parseInt(badges.getFirst().getText());
    }

    public void goToCart() {
        clickToElement(CART_LINK);
    }

    public void sortBy(String visibleText) {
        selectOptionByVisibleText(SORT_DROPDOWN, visibleText);
    }

    public String getFirstProductName() {
        return getTextFromElement(PRODUCT_NAME);
    }

    public List<String> getAllProductNames() {
        List<WebElement> nameElements = driver.findElements(PRODUCT_NAME);
        List<String> names = new ArrayList<>();
        for (WebElement el : nameElements) {
            names.add(el.getText());
        }
        return names;
    }

    public List<Double> getAllProductPrices() {
        List<WebElement> priceElements = driver.findElements(PRODUCT_PRICE);
        List<Double> prices = new ArrayList<>();
        for (WebElement el : priceElements) {
            // "$29.99" şeklinde gelen metinden "$" işaretini atıp Double'a çeviriyoruz
            String priceText = el.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }

}
