package com.merkosun.pages.demoblaze;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends Navbar {

    private static final By CART_ITEMS = By.xpath("//tbody[@id='tbodyid']/tr");
    private static final By DELETE_BUTTONS = By.xpath("//a[text()='Delete']");
    private static final By TOTAL_PRICE = By.id("totalp");
    private static final By PLACE_ORDER_BUTTON = By.xpath("//button[text()='Place Order']");

    private static final By NAME_INPUT = By.id("name");
    private static final By COUNTRY_INPUT = By.id("country");
    private static final By CITY_INPUT = By.id("city");
    private static final By CARD_INPUT = By.id("card");
    private static final By MONTH_INPUT = By.id("month");
    private static final By YEAR_INPUT = By.id("year");
    private static final By PURCHASE_BUTTON = By.xpath("//button[text()='Purchase']");

    private static final By CONFIRMATION_HEADER = By.xpath("//h2[text()='Thank you for your purchase!']");
    private static final By OK_BUTTON = By.xpath("//button[text()='OK']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getCartItemCount() {
        try {
            List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CART_ITEMS));
            return items.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void deleteFirstItem() {
        clickToElement(DELETE_BUTTONS);
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }

    public String getTotalPrice() {
        return getTextFromElement(TOTAL_PRICE);
    }

    public void clickPlaceOrder() {
        clickToElement(PLACE_ORDER_BUTTON);
    }

    public void fillPurchaseForm(String name, String country, String city, String card, String month, String year) {
        typeToElement(NAME_INPUT, name);
        typeToElement(COUNTRY_INPUT, country);
        typeToElement(CITY_INPUT, city);
        typeToElement(CARD_INPUT, card);
        typeToElement(MONTH_INPUT, month);
        typeToElement(YEAR_INPUT, year);
        clickToElement(PURCHASE_BUTTON);
    }

    public boolean isPurchaseSuccessful() {
        return isElementVisible(CONFIRMATION_HEADER);
    }

    public void clickOkOnConfirmation() {
        clickToElement(OK_BUTTON);
    }
}
