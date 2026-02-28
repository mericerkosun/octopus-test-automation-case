package com.merkosun.tests.parabank;

import com.merkosun.base.BaseTest;
import com.merkosun.model.UserData;
import com.merkosun.pages.parabank.ParabankConstants;
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
                .password(ParabankConstants.DEFAULT_PASSWORD)
                .build();

        RegisterPage registerPage = new RegisterPage(getDriver());
        registerPage.navigateTo();
        registerPage.register(user);

        Assert.assertTrue(registerPage.isRegistrationSuccessful(),
                ParabankConstants.WELCOME_TEXT + " message should be displayed");
    }

    @Test(description = "Registering with an existing username should show an error message")
    public void testDuplicateUsernameRegistration() {
        String existingUsername = com.merkosun.config.ConfigManager.getInstance().parabankDuplicateUsername();
        UserData user = new UserData.Builder()
                .firstName(ParabankConstants.DEFAULT_FIRST_NAME)
                .lastName(ParabankConstants.DEFAULT_LAST_NAME)
                .address(ParabankConstants.DEFAULT_ADDRESS)
                .city(ParabankConstants.DEFAULT_CITY)
                .state(ParabankConstants.DEFAULT_STATE)
                .zipCode(ParabankConstants.DEFAULT_ZIP)
                .phone(ParabankConstants.DEFAULT_PHONE)
                .ssn(ParabankConstants.DEFAULT_SSN)
                .username(existingUsername)
                .password(ParabankConstants.DEFAULT_PASSWORD)
                .build();

        RegisterPage registerPage = new RegisterPage(getDriver());
        registerPage.navigateTo();
        registerPage.register(user);

        Assert.assertTrue(registerPage.isErrorMessageVisible(),
                "Error message should be displayed for duplicate username");
    }
}
