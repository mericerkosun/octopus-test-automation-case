package com.merkosun.pages.demoblaze;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends Navbar {

    private static final By CATEGORY_PHONES = By.xpath("//a[text()='Phones']");
    private static final By CATEGORY_LAPTOPS = By.xpath("//a[text()='Laptops']");
    private static final By CATEGORY_MONITORS = By.xpath("//a[text()='Monitors']");
    private static final By PRODUCT_CARDS = By.className("card-block");
    private static final By PRODUCT_NAMES = By.className("hrefch");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private void waitForProductsToRefresh() {
        List<WebElement> currentProducts = driver.findElements(PRODUCT_NAMES);
        if (!currentProducts.isEmpty()) {
            waitForStaleness(currentProducts.get(0));
        }
        isElementVisible(PRODUCT_CARDS);
    }

    public void filterByPhones() {
        clickToElement(CATEGORY_PHONES);
        waitForProductsToRefresh();
    }

    public void filterByLaptops() {
        clickToElement(CATEGORY_LAPTOPS);
        waitForProductsToRefresh();
    }

    public void filterByMonitors() {
        clickToElement(CATEGORY_MONITORS);
        waitForProductsToRefresh();
    }

    public void selectProductByName(String productName) {
        By productLink = By.linkText(productName);
        clickToElement(productLink);
    }

    public List<WebElement> getProductElements() {
        return waitForElementsVisible(PRODUCT_NAMES);
    }

    public boolean isProductVisible(String productName) {
        try {
            return isElementVisible(By.linkText(productName));
        } catch (Exception e) {
            return false;
        }
    }
}
