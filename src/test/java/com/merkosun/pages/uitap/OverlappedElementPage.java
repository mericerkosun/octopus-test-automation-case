package com.merkosun.pages.uitap;

import com.merkosun.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OverlappedElementPage extends BasePage {

    private final By nameInput = By.id("name");
    private final By scrollContainer = By.cssSelector("div[style*='overflow-y: scroll']");

    public OverlappedElementPage(WebDriver driver) {
        super(driver);
    }

    public void enterName(String name) {
        WebElement input = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(nameInput));
        // Step 1: Scroll into view to ensure it's not overlapped by the absolute div
        executeJavaScript("arguments[0].scrollIntoView({block: 'center'});", input);
        // Step 2: Use typeToElement from BasePage
        typeToElement(nameInput, name);
    }

    public String getNameValue() {
        return wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(nameInput)).getAttribute("value");
    }
}
