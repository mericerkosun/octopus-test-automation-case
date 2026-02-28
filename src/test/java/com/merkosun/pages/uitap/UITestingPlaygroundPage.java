package com.merkosun.pages.uitap;

import com.merkosun.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UITestingPlaygroundPage extends BasePage {

    private final By ajaxDataButton = By.id("ajaxButton");
    private final By dynamicIdButton = By.xpath("//button[text()='Button with Dynamic ID']");
    private final By ajaxContent = By.className("bg-success");
    private final By clientSideDelayButton = By.id("ajaxButton"); 

    public UITestingPlaygroundPage(WebDriver driver) {
        super(driver);
    }

    public UITestingPlaygroundPage navigateTo(String path) {
        driver.get(com.merkosun.config.ConfigManager.getInstance().uitapBaseUrl() + path);
        return this;
    }

    public void clickAjaxButton() {
        clickToElement(ajaxDataButton);
    }

    public void clickDynamicIdButton() {
        clickToElement(dynamicIdButton);
    }

    public void clickClientSideDelayButton() {
        clickToElement(clientSideDelayButton);
    }

    public String getAjaxContentText() {
        // Use long wait from config for AJAX/Client Side Delay scenarios
        return getTextFromElement(ajaxContent, java.time.Duration.ofSeconds(
                com.merkosun.config.ConfigManager.getInstance().uitapLongWait()));
    }
}
