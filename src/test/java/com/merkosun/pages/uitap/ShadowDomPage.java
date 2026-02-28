package com.merkosun.pages.uitap;

import com.merkosun.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShadowDomPage extends BasePage {

    private final By shadowHost = By.tagName("guid-generator");
    private final By generateButton = By.id("buttonGenerate");
    private final By copyButton = By.id("buttonCopy");
    private final By editField = By.id("editField");

    public ShadowDomPage(WebDriver driver) {
        super(driver);
    }

    public ShadowDomPage navigateTo() {
        driver.get(com.merkosun.config.ConfigManager.getInstance().uitapBaseUrl() + "/shadowdom");
        return this;
    }

    public void clickGenerate() {
        clickToShadowElement(shadowHost, generateButton);
    }

    public void clickCopy() {
        clickToShadowElement(shadowHost, copyButton);
    }

    public String getGuidValue() {
        return getShadowElementAttribute(shadowHost, editField, "value");
    }
}
