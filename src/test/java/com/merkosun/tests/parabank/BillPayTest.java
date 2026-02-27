package com.merkosun.tests.parabank;

import com.merkosun.base.BaseTest;
import com.merkosun.model.BillData;
import com.merkosun.pages.parabank.BillPayPage;
import com.merkosun.pages.parabank.LoginPage;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * BillPayTest: Fatura ödeme akışını uçtan uca doğrular.
 * Pozitif: DataFaker ile rastgele alacaklı bilgisi oluşturarak başarılı fatura ödeme.
 * Negatif: Boş alacaklı adıyla ödeme denemesi → hata mesajı doğrulama.
 */
public class BillPayTest extends BaseTest {

    private static final Faker faker = new Faker();

    @Test(description = "Bill payment should complete successfully")
    public void testBillPayment() {
        com.merkosun.model.UserData user = registerUniqueUser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");

        BillData bill = new BillData.Builder()
                .payeeName(faker.name().fullName())
                .address(faker.address().streetAddress())
                .city(faker.address().city())
                .state(faker.address().stateAbbr())
                .zipCode(faker.address().zipCode().substring(0, 5))
                .phone(faker.phoneNumber().subscriberNumber(10))
                .account("12345")
                .amount("50")
                .build();

        BillPayPage billPayPage = new BillPayPage(driver);
        billPayPage.navigateToBillPay();
        billPayPage.payBill(bill);

        Assert.assertTrue(billPayPage.isBillPaid(),
                "'Bill Payment Complete' title should be displayed");
    }

    @Test(description = "Bill payment with empty payee name should show a validation error")
    public void testBillPaymentWithEmptyPayeeName() {
        com.merkosun.model.UserData user = registerUniqueUser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");

        BillData emptyPayee = new BillData.Builder()
                .payeeName("")
                .address(faker.address().streetAddress())
                .city(faker.address().city())
                .state(faker.address().stateAbbr())
                .zipCode(faker.address().zipCode().substring(0, 5))
                .phone(faker.phoneNumber().subscriberNumber(10))
                .account("12345")
                .amount("50")
                .build();

        BillPayPage billPayPage = new BillPayPage(driver);
        billPayPage.navigateToBillPay();
        billPayPage.payBill(emptyPayee);

        Assert.assertFalse(billPayPage.isBillPaid(),
                "Bill payment should NOT complete when payee name is empty");
    }
}
