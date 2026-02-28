package com.merkosun.base;

import com.merkosun.config.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final By LOGOUT_LINK = By.linkText("Log Out");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        ConfigManager config = ConfigManager.getInstance();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(config.explicitWait()));
    }

    protected void clickToElement(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            waitForModalBackdropToDisappear();
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        }
    }

    public void waitForModalBackdropToDisappear() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-backdrop")));
        } catch (Exception ignored) {
            // If backdrop is not present, we can proceed
        }
    }

    protected void typeToElement(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    protected String getTextFromElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }

    protected String getTextFromElement(By locator, Duration timeout) {
        WebDriverWait customWait = new WebDriverWait(driver, timeout);
        WebElement element = customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }

    protected boolean isElementVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementNotVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean waitForTextToAppear(By locator, String text) {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (Exception e) {
            return false;
        }
    }


    protected void selectOptionByIndex(By locator, int index) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        new Select(element).selectByIndex(index);
    }

    protected void selectOptionByVisibleText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        new Select(element).selectByVisibleText(text);
    }

    public void logout() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_LINK)).click();
        } catch (Exception e) {
            // Ignore if not present or clickable
        }
    }

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }

    public void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    public Object executeJavaScript(String script, Object... args) {
        return ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(script, args);
    }

    public void scrollToBottom() {
        executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    protected boolean waitForUrlContains(String urlPart) {
        try {
            return wait.until(ExpectedConditions.urlContains(urlPart));
        } catch (Exception e) {
            return false;
        }
    }

    protected void waitForElementsToBePresent(By locator) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
    }

    protected java.util.List<WebElement> waitForElementsVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public String getAlertTextAndAccept() {
        String text = getAlertText();
        acceptAlert();
        return text;
    }

    protected void waitForStaleness(WebElement element) {
        try {
            wait.until(ExpectedConditions.stalenessOf(element));
        } catch (Exception ignored) {
            // If already stale or timeout, we can proceed
        }
    }

    protected void uploadFileToElement(By locator, String absolutePath) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        element.sendKeys(absolutePath);
    }

    protected void scrollIntoView(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        executeJavaScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    protected org.openqa.selenium.SearchContext getShadowRoot(By shadowHost) {
        WebElement host = wait.until(ExpectedConditions.presenceOfElementLocated(shadowHost));
        return host.getShadowRoot();
    }

    protected void clickToShadowElement(By shadowHost, By locator) {
        getShadowRoot(shadowHost).findElement(locator).click();
    }

    protected String getShadowElementAttribute(By shadowHost, By locator, String attribute) {
        return getShadowRoot(shadowHost).findElement(locator).getAttribute(attribute);
    }

}
