package com.merkosun.pages.parabank;

import com.merkosun.base.BasePage;
import com.merkosun.config.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class AccountOverviewPage extends BasePage {

    private static final By PAGE_TITLE       = By.cssSelector("h1.title");
    private static final By ACCOUNT_ROWS     = By.cssSelector("table#accountTable tbody tr");
    private static final By FIRST_ACCOUNT    = By.cssSelector("table#accountTable tbody tr:first-child td:first-child a");
    private static final By TRANSACTION_ROWS = By.cssSelector("table#transactionTable tbody tr");


    public AccountOverviewPage(WebDriver driver) {
        super(driver);
    }

    public AccountOverviewPage navigateTo() {
        driver.get(ConfigManager.getInstance().parabankBaseUrl() + ParabankConstants.OVERVIEW_URL_PART);
        return this;
    }

    public boolean isPageLoaded() {
        return waitForTextToAppear(PAGE_TITLE, ParabankConstants.ACCOUNTS_OVERVIEW_TITLE);
    }

    public String getFirstAccountId() {
        return getTextFromElement(FIRST_ACCOUNT).trim();
    }

    public void clickFirstAccount() {
        clickToElement(FIRST_ACCOUNT);
    }

    public boolean verifyLastTransaction(String expectedType, String expectedAmount) {
        try {
            waitForElementsToBePresent(TRANSACTION_ROWS);
        } catch (Exception e) {
            return false;
        }
        List<WebElement> rows = driver.findElements(TRANSACTION_ROWS);


        int columnIndex = expectedType.equalsIgnoreCase(ParabankConstants.TRANSACTION_TYPE_DEBIT) ? 2 : 3;

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > columnIndex) {
                String cellText = cells.get(columnIndex).getText().trim();

                if (!cellText.isEmpty() && cellText.contains(expectedAmount)) {
                    return true;
                }
            }
        }
        return false;
    }

}
