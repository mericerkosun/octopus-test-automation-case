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

    private SearchContext getShadowRoot() {
        WebElement host = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(shadowHost));
        return host.getShadowRoot();
    }

    public void clickGenerate() {
        getShadowRoot().findElement(generateButton).click();
    }

    public void clickCopy() {
        getShadowRoot().findElement(copyButton).click();
    }

    public String getGuidValue() {
        return getShadowRoot().findElement(editField).getAttribute("value");
    }
}
