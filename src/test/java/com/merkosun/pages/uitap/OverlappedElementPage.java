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

    public OverlappedElementPage navigateTo() {
        driver.get(com.merkosun.config.ConfigManager.getInstance().uitapBaseUrl() + 
                com.merkosun.config.ConfigManager.getInstance().uitapOverlappedPath());
        return this;
    }

    public void enterName(String name) {
        scrollIntoView(nameInput);
        typeToElement(nameInput, name);
    }

    public String getNameValue() {
        return wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(nameInput)).getAttribute("value");
    }
}
