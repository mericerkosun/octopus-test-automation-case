package com.merkosun.pages.parabank;

import com.merkosun.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OpenNewAccountPage extends BasePage {

    private static final By OPEN_ACCOUNT_LINK  = By.linkText("Open New Account");
    private static final By ACCOUNT_TYPE       = By.id("type");
    private static final By FROM_ACCOUNT       = By.id("fromAccountId");
    // HTML'de type='button', type='submit' değil!
    private static final By OPEN_BUTTON        = By.cssSelector("input[type='button'][value='Open New Account']");
    private static final By NEW_ACCOUNT_ID     = By.id("newAccountId");
    // fromAccountId AJAX ile dolduğu için option'ların gelmesini beklemek için
    private static final By FROM_ACCOUNT_OPTION = By.cssSelector("#fromAccountId option");

    public OpenNewAccountPage(WebDriver driver) {
        super(driver);
    }

    public OpenNewAccountPage navigateToOpenAccount() {
        clickToElement(OPEN_ACCOUNT_LINK);
        return this;
    }

    public void openAccount(String accountType) {
        selectOptionByVisibleText(ACCOUNT_TYPE, accountType);
        // fromAccountId dropdown'u AJAX ile dolduğundan, en az 1 option gelene kadar bekle
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(FROM_ACCOUNT_OPTION, 0));
        selectOptionByIndex(FROM_ACCOUNT, 0);
        clickToElement(OPEN_BUTTON);
    }

    public String getNewAccountId() {
        return getTextFromElement(NEW_ACCOUNT_ID).trim();
    }

    public boolean isNewAccountCreated() {
        return isElementVisible(NEW_ACCOUNT_ID);
    }
}
