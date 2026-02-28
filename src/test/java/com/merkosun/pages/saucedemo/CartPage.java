package com.merkosun.pages.saucedemo;

import com.merkosun.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private static final By CART_ITEMS = By.className("cart_item");
    private static final By ITEM_NAME = By.className("inventory_item_name");
    private static final By REMOVE_BUTTON = By.cssSelector("button[id^='remove-']");
    private static final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    private static final By CHECKOUT_BUTTON = By.id("checkout");
    private static final By CART_BADGE = By.className("shopping_cart_badge");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getCartItemCount() {
        return driver.findElements(CART_ITEMS).size();
    }

    public List<String> getCartItemNames() {
        List<WebElement> itemElements = driver.findElements(ITEM_NAME);
        List<String> itemNames = new ArrayList<>();
        for (WebElement el : itemElements) {
            itemNames.add(el.getText());
        }
        return itemNames;
    }

    public void removeFirstItem() {
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy(REMOVE_BUTTON));
        List<WebElement> removeButtons = driver.findElements(REMOVE_BUTTON);
        if (!removeButtons.isEmpty()) {
            int currentCount = getCartItemCount();
            removeButtons.getFirst().click();
            wait.until(d -> getCartItemCount() < currentCount);
        }
    }

    public int getCartBadgeCount() {
        List<WebElement> badges = driver.findElements(CART_BADGE);
        if (badges.isEmpty()) return 0;
        return Integer.parseInt(badges.getFirst().getText());
    }

    public void clickCheckout() {
        scrollIntoView(CHECKOUT_BUTTON);
        clickToElement(CHECKOUT_BUTTON);
    }

    public void clickContinueShopping() {
        clickToElement(CONTINUE_SHOPPING_BUTTON);
    }
}
