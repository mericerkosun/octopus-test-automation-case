package com.merkosun.pages.saucedemo;

import com.merkosun.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage {

    private static final By FIRST_NAME_INPUT = By.id("first-name");
    private static final By LAST_NAME_INPUT = By.id("last-name");
    private static final By POSTAL_CODE_INPUT = By.id("postal-code");
    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");
    private static final By CANCEL_BUTTON = By.id("cancel");

    private static final By FINISH_BUTTON = By.cssSelector("[data-test='finish']");
    private static final By COMPLETE_HEADER = By.cssSelector("[data-test='title']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillPersonalInformation(String firstName, String lastName, String postalCode) {
        typeToElement(FIRST_NAME_INPUT, firstName);
        typeToElement(LAST_NAME_INPUT, lastName);
        typeToElement(POSTAL_CODE_INPUT, postalCode);
    }

    public void clickContinue() {
        scrollIntoView(CONTINUE_BUTTON);
        clickToElement(CONTINUE_BUTTON);
    }

    public void clickCancel() {
        scrollIntoView(CANCEL_BUTTON);
        clickToElement(CANCEL_BUTTON);
    }

    public void clickFinish() {
        WebElement finishBtn = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(FINISH_BUTTON));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", finishBtn);
        js.executeScript("arguments[0].click();", finishBtn);
    }

    public String getErrorMessage() {
        return getTextFromElement(ERROR_MESSAGE);
    }

    public String getCompleteHeaderText() {
        waitForTextToAppear(COMPLETE_HEADER, SauceConstants.CHECKOUT_COMPLETE_HEADER);
        return getTextFromElement(COMPLETE_HEADER);
    }
}
