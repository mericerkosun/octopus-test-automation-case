package com.merkosun.pages.parabank;

import com.merkosun.base.BasePage;
import com.merkosun.model.BillData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BillPayPage extends BasePage {

    private static final By BILL_PAY_LINK    = By.linkText("Bill Pay");
    private static final By PAYEE_NAME       = By.name("payee.name");
    private static final By ADDRESS          = By.name("payee.address.street");
    private static final By CITY             = By.name("payee.address.city");
    private static final By STATE            = By.name("payee.address.state");
    private static final By ZIP_CODE         = By.name("payee.address.zipCode");
    private static final By PHONE            = By.name("payee.phoneNumber");
    private static final By ACCOUNT          = By.name("payee.accountNumber");
    private static final By VERIFY_ACCOUNT   = By.name("verifyAccount");
    private static final By AMOUNT           = By.name("amount");
    private static final By FROM_ACCOUNT     = By.name("fromAccountId");
    private static final By SEND_BUTTON      = By.cssSelector("input[type='button'][value='Send Payment']");

    private static final By SUCCESS_TITLE    = By.cssSelector("div#billpayResult h1.title");

    public BillPayPage(WebDriver driver) {
        super(driver);
    }

    public BillPayPage navigateToBillPay() {
        clickToElement(BILL_PAY_LINK);
        return this;
    }

    public void payBill(BillData bill) {
        typeToElement(PAYEE_NAME,     bill.getPayeeName());
        typeToElement(ADDRESS,        bill.getAddress());
        typeToElement(CITY,           bill.getCity());
        typeToElement(STATE,          bill.getState());
        typeToElement(ZIP_CODE,       bill.getZipCode());
        typeToElement(PHONE,          bill.getPhone());
        typeToElement(ACCOUNT,        bill.getAccount());
        typeToElement(VERIFY_ACCOUNT, bill.getAccount());
        typeToElement(AMOUNT,         bill.getAmount());
        selectOptionByIndex(FROM_ACCOUNT, 0);
        clickToElement(SEND_BUTTON);
    }

    public boolean isBillPaid() {
        return isElementVisible(SUCCESS_TITLE) &&
               waitForTextToAppear(SUCCESS_TITLE, "Bill Payment Complete");
    }
}
