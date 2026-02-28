package com.merkosun.pages.demoblaze;

import com.merkosun.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Modals extends BasePage {

    private static final By SIGNUP_USERNAME = By.id("sign-username");
    private static final By SIGNUP_PASSWORD = By.id("sign-password");
    private static final By SIGNUP_BUTTON = By.xpath("//button[text()='Sign up']");

    private static final By LOGIN_USERNAME = By.id("loginusername");
    private static final By LOGIN_PASSWORD = By.id("loginpassword");
    private static final By LOGIN_BUTTON = By.xpath("//button[text()='Log in']");

    private static final By CONTACT_EMAIL = By.id("recipient-email");
    private static final By CONTACT_NAME = By.id("recipient-name");
    private static final By CONTACT_MESSAGE = By.id("message-text");
    private static final By SEND_MESSAGE_BUTTON = By.xpath("//button[text()='Send message']");

    private static final By SIGNUP_CLOSE_BUTTON = By.cssSelector("#signInModal button.btn-secondary");
    private static final By LOGIN_CLOSE_BUTTON = By.cssSelector("#logInModal button.btn-secondary");
    private static final By CONTACT_CLOSE_BUTTON = By.cssSelector("#exampleModal button.btn-secondary");

    private static final By SIGNUP_MODAL_CONTENT = By.xpath("//div[@id='signInModal']//div[@class='modal-content']");
    private static final By LOGIN_MODAL_CONTENT = By.xpath("//div[@id='logInModal']//div[@class='modal-content']");
    private static final By CONTACT_MODAL_CONTENT = By.xpath("//div[@id='exampleModal']//div[@class='modal-content']");

    public Modals(WebDriver driver) {
        super(driver);
    }

    public void register(String username, String password) {
        isElementVisible(SIGNUP_MODAL_CONTENT);
        typeToElement(SIGNUP_USERNAME, username);
        typeToElement(SIGNUP_PASSWORD, password);
        clickToElement(SIGNUP_BUTTON);
    }

    public void login(String username, String password) {
        isElementVisible(LOGIN_MODAL_CONTENT);
        typeToElement(LOGIN_USERNAME, username);
        typeToElement(LOGIN_PASSWORD, password);
        clickToElement(LOGIN_BUTTON);
    }

    public void sendContact(String email, String name, String message) {
        isElementVisible(CONTACT_MODAL_CONTENT);
        typeToElement(CONTACT_EMAIL, email);
        typeToElement(CONTACT_NAME, name);
        typeToElement(CONTACT_MESSAGE, message);
        clickToElement(SEND_MESSAGE_BUTTON);
    }

    public void clickCloseSignup() {
        clickToElement(SIGNUP_CLOSE_BUTTON);
    }

    public void clickCloseLogin() {
        clickToElement(LOGIN_CLOSE_BUTTON);
    }

    public void clickCloseContact() {
        clickToElement(CONTACT_CLOSE_BUTTON);
    }

    public void waitForSignupModalToClose() {
        isElementNotVisible(SIGNUP_MODAL_CONTENT);
        waitForModalBackdropToDisappear();
    }

    public void waitForLoginModalToClose() {
        isElementNotVisible(LOGIN_MODAL_CONTENT);
        waitForModalBackdropToDisappear();
    }

}
