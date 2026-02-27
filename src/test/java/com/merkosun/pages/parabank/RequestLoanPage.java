package com.merkosun.pages.parabank;

import com.merkosun.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RequestLoanPage extends BasePage {

    private static final By REQUEST_LOAN_LINK = By.linkText("Request Loan");
    private static final By LOAN_AMOUNT       = By.id("amount");
    private static final By DOWN_PAYMENT      = By.id("downPayment");
    private static final By FROM_ACCOUNT      = By.id("fromAccountId");
    private static final By APPLY_BUTTON      = By.cssSelector("input[type='button'][value='Apply Now']");
    private static final By LOAN_STATUS       = By.id("loanStatus");
    private static final By RESULT_SECTION    = By.id("requestLoanResult");

    public RequestLoanPage(WebDriver driver) {
        super(driver);
    }

    public RequestLoanPage navigateToRequestLoan() {
        clickToElement(REQUEST_LOAN_LINK);
        return this;
    }

    public void requestLoan(String amount, String downPayment) {
        typeToElement(LOAN_AMOUNT,  amount);
        typeToElement(DOWN_PAYMENT, downPayment);
        selectOptionByIndex(FROM_ACCOUNT, 0);
        clickToElement(APPLY_BUTTON);
    }

    public String getLoanStatus() {
        return getTextFromElement(LOAN_STATUS).trim();
    }

    public boolean isResultVisible() {
        return isElementVisible(RESULT_SECTION);
    }
}
