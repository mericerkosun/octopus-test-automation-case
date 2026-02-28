package com.merkosun.tests.herokuapp;

import com.merkosun.base.BaseTest;
import com.merkosun.pages.herokuapp.HerokuAppConstants;
import com.merkosun.pages.herokuapp.HerokuFloatingMenuPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FloatingMenuTest extends BaseTest {

    @Test
    public void testFloatingMenuVisibilityOnScroll() {
        HerokuFloatingMenuPage floatingMenuPage = new HerokuFloatingMenuPage(getDriver()).navigateTo();

        Assert.assertTrue(floatingMenuPage.isMenuDisplayed(), "Menu should be visible at the top");

        floatingMenuPage.scrollToBottom();
        Assert.assertTrue(floatingMenuPage.isMenuDisplayed(), "Menu should still be visible after scrolling to bottom");
    }

    @Test
    public void testMenuLinksNavigation() {
        HerokuFloatingMenuPage floatingMenuPage = new HerokuFloatingMenuPage(getDriver()).navigateTo();

        floatingMenuPage.clickNews();
        Assert.assertTrue(getDriver().getCurrentUrl().contains(HerokuAppConstants.MENU_NEWS_FRAGMENT), "Should navigate to news section");

        floatingMenuPage.scrollToBottom();
        floatingMenuPage.clickContact();
        Assert.assertTrue(getDriver().getCurrentUrl().contains(HerokuAppConstants.MENU_CONTACT_FRAGMENT), "Should navigate to contact section even from bottom");
    }

    @Test
    public void testMenuVisibilityOnResize() {
        HerokuFloatingMenuPage floatingMenuPage = new HerokuFloatingMenuPage(getDriver()).navigateTo();

        getDriver().manage().window().setSize(new org.openqa.selenium.Dimension(800, 600));
        Assert.assertTrue(floatingMenuPage.isMenuDisplayed(), "Menu should be visible on smaller screen");

        floatingMenuPage.scrollToBottom();
        Assert.assertTrue(floatingMenuPage.isMenuDisplayed(), "Menu should stay fixed on smaller screen after scroll");

        getDriver().manage().window().maximize();
    }

}
