package com.merkosun.tests.parabank;

import com.merkosun.base.BaseTest;
import com.merkosun.pages.parabank.AccountOverviewPage;
import com.merkosun.pages.parabank.LoginPage;
import com.merkosun.pages.parabank.TransferPage;
import com.merkosun.pages.parabank.ParabankConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TransferTest: Para transferi akışını uçtan uca doğrular.
 * E2E Pozitif: Giriş → Transfer → "Transfer Complete!" mesajı.
 * E2E + Validation: Transfer sonrası Accounts Overview'da işlem doğrulama.
 */
public class TransferTest extends BaseTest {

    private static final String TRANSFER_AMOUNT = ParabankConstants.TRANSFER_AMOUNT_DEFAULT;

    @Test(description = "Fund transfer should complete successfully")
    public void testTransferFunds() {
        com.merkosun.model.UserData user = registerUniqueUser();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateTo();
        loginPage.login(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");

        TransferPage transferPage = new TransferPage(getDriver());
        transferPage.navigateToTransferFunds();
        transferPage.transferFunds(TRANSFER_AMOUNT);

        Assert.assertTrue(transferPage.isTransferComplete(),
                "'Transfer Complete!' title should be displayed");
    }

    @Test(description = "Transaction should appear in Account Activity after transfer")
    public void testTransferAppearsInAccountActivity() {
        com.merkosun.model.UserData user = registerUniqueUser();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateTo();
        loginPage.login(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");

        TransferPage transferPage = new TransferPage(getDriver());
        transferPage.navigateToTransferFunds();
        transferPage.transferFunds(TRANSFER_AMOUNT);
        Assert.assertTrue(transferPage.isTransferComplete(),
                "Transfer should complete first");

        AccountOverviewPage overviewPage = new AccountOverviewPage(getDriver());
        overviewPage.navigateTo();
        overviewPage.clickFirstAccount();

        Assert.assertTrue(overviewPage.verifyLastTransaction(ParabankConstants.TRANSACTION_TYPE_DEBIT, TRANSFER_AMOUNT),
                "Last transaction should be Debit type with correct amount");
    }
}
