package com.merkosun.tests.parabank;

import com.merkosun.base.BaseTest;
import com.merkosun.pages.parabank.LoginPage;
import com.merkosun.pages.parabank.ParabankConstants;
import com.merkosun.pages.parabank.RequestLoanPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * RequestLoanTest: Kredi başvurusu akışını doğrular.
 * Approved Senaryo: Makul miktar + peşinat → Approved.
 * Denied Senaryo: Bakiyeyi çok aşan miktar → Denied.
 */
public class RequestLoanTest extends BaseTest {

    @Test(description = "Reasonable loan request should be approved")
    public void testLoanApproved() {
        com.merkosun.model.UserData user = registerUniqueUser();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateTo();
        loginPage.login(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");

        RequestLoanPage loanPage = new RequestLoanPage(getDriver());
        loanPage.navigateToRequestLoan();
        loanPage.requestLoan(ParabankConstants.LOAN_AMOUNT_REASONABLE, ParabankConstants.DOWN_PAYMENT_REASONABLE);

        Assert.assertTrue(loanPage.isResultVisible(),
                "Loan request result section should be displayed");
        Assert.assertEquals(loanPage.getLoanStatus(), ParabankConstants.LOAN_STATUS_APPROVED,
                "Reasonable loan request should be approved");
    }

    @Test(description = "Extremely high loan request should be denied")
    public void testLoanDenied() {
        com.merkosun.model.UserData user = registerUniqueUser();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateTo();
        loginPage.login(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");

        RequestLoanPage loanPage = new RequestLoanPage(getDriver());
        loanPage.navigateToRequestLoan();
        loanPage.requestLoan(ParabankConstants.LOAN_AMOUNT_HIGH, ParabankConstants.DOWN_PAYMENT_REASONABLE);

        Assert.assertTrue(loanPage.isResultVisible(),
                "Loan request result section should be displayed");
        Assert.assertEquals(loanPage.getLoanStatus(), ParabankConstants.LOAN_STATUS_DENIED,
                "Extremely high loan request should be denied");
    }
}
