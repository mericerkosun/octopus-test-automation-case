package com.merkosun.pages.parabank;

import com.merkosun.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TransferPage extends BasePage {

    private static final By TRANSFER_LINK       = By.linkText("Transfer Funds");
    private static final By AMOUNT_INPUT        = By.id("amount");
    private static final By FROM_ACCOUNT        = By.id("fromAccountId");
    private static final By TO_ACCOUNT          = By.id("toAccountId");
    private static final By TRANSFER_BUTTON     = By.cssSelector("input[type='submit'][value='Transfer']");
    private static final By SHOW_RESULT_DIV     = By.id("showResult");
    private static final By FROM_ACCOUNT_OPTION = By.cssSelector("#fromAccountId option");
    private static final By TO_ACCOUNT_OPTION   = By.cssSelector("#toAccountId option");

    public TransferPage(WebDriver driver) {
        super(driver);
    }

    public TransferPage navigateToTransferFunds() {
        clickToElement(TRANSFER_LINK);
        return this;
    }

    public void transferFunds(String amount) {
        typeToElement(AMOUNT_INPUT, amount);
        waitForElementsToBePresent(FROM_ACCOUNT_OPTION);
        waitForElementsToBePresent(TO_ACCOUNT_OPTION);
        selectOptionByIndex(FROM_ACCOUNT, 0);
        selectOptionByIndex(TO_ACCOUNT, 0);
        clickToElement(TRANSFER_BUTTON);
    }

    public boolean isTransferComplete() {
        return isElementVisible(SHOW_RESULT_DIV);
    }
}
