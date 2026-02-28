package com.merkosun.tests.parabank;

import com.merkosun.base.BaseTest;
import com.merkosun.model.UserData;
import com.merkosun.pages.parabank.RegisterPage;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * RegisterTest: Parabank kullanıcı kayıt akışını doğrular.
 * Pozitif: DataFaker ile benzersiz kullanıcı oluşturarak başarılı kayıt.
 * Negatif: Aynı kullanıcı adıyla tekrar kayıt — hata mesajı doğrulama.
 */
public class RegisterTest extends BaseTest {

    private static final Faker faker = new Faker();

    @Test(description = "New user registration should complete successfully")
    public void testSuccessfulRegistration() {
        UserData user = new UserData.Builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .address(faker.address().streetAddress())
                .city(faker.address().city())
                .state(faker.address().stateAbbr())
                .zipCode(faker.address().zipCode().substring(0, 5))
                .phone(faker.phoneNumber().subscriberNumber(10))
                .ssn(faker.idNumber().ssnValid())
                .username(faker.internet().username() + faker.number().digits(4))
                .password("Test@1234")
                .build();

        RegisterPage registerPage = new RegisterPage(getDriver());
        registerPage.navigateTo();
        registerPage.register(user);

        Assert.assertTrue(registerPage.isRegistrationSuccessful(),
                "Registration success message (Welcome) should be displayed");
    }

    @Test(description = "Registering with an existing username should show an error message")
    public void testDuplicateUsernameRegistration() {

        UserData user = new UserData.Builder()
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .city("Springfield")
                .state("IL")
                .zipCode("62701")
                .phone("5551234567")
                .ssn("123-45-6789")
                .username("john")
                .password("demo")
                .build();

        RegisterPage registerPage = new RegisterPage(getDriver());
        registerPage.navigateTo();
        registerPage.register(user);

        Assert.assertTrue(registerPage.isErrorMessageVisible(),
                "Error message should be displayed for duplicate username");
    }
}
