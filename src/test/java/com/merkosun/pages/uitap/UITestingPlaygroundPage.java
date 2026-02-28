package com.merkosun.pages.uitap;

import com.merkosun.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UITestingPlaygroundPage extends BasePage {

    private final By ajaxDataButton = By.id("ajaxButton");
    private final By dynamicIdButton = By.xpath("//button[text()='Button with Dynamic ID']");
    private final By ajaxContent = By.className("bg-success");
    private final By clientSideDelayButton = By.id("ajaxButton"); // Same ID as AJAX but on a different page/context

    public UITestingPlaygroundPage(WebDriver driver) {
        super(driver);
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
        // Higher timeout for AJAX/Client Side Delay as per scenario (15s+)
        org.openqa.selenium.support.ui.WebDriverWait longWait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        return longWait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(ajaxContent)).getText();
    }
}
