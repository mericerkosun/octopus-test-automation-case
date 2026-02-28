package com.merkosun.pages.herokuapp;

import com.merkosun.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HerokuFloatingMenuPage extends BasePage {

    private static final By MENU_CONTAINER = By.id("menu");
    private static final By HOME_LINK = By.linkText("Home");
    private static final By NEWS_LINK = By.linkText("News");
    private static final By CONTACT_LINK = By.linkText("Contact");
    private static final By ABOUT_LINK = By.linkText("About");

    public HerokuFloatingMenuPage(WebDriver driver) {
        super(driver);
    }

    public HerokuFloatingMenuPage navigateTo() {
        driver.get(com.merkosun.config.ConfigManager.getInstance().theinternetFloatingUrl());
        return this;
    }

    public boolean isMenuDisplayed() {
        return isElementVisible(MENU_CONTAINER) &&
               isElementVisible(HOME_LINK) &&
               isElementVisible(NEWS_LINK) &&
               isElementVisible(CONTACT_LINK) &&
               isElementVisible(ABOUT_LINK);
    }

    public void clickHome() { clickToElement(HOME_LINK); }
    public void clickNews() { clickToElement(NEWS_LINK); }
    public void clickContact() { clickToElement(CONTACT_LINK); }
    public void clickAbout() { clickToElement(ABOUT_LINK); }

}
