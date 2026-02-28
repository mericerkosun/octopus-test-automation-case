package com.merkosun.tests.uitap;

import com.merkosun.base.BaseTest;
import com.merkosun.pages.uitap.OverlappedElementPage;
import com.merkosun.pages.uitap.ShadowDomPage;
import com.merkosun.pages.uitap.UITapConstants;
import com.merkosun.pages.uitap.UITestingPlaygroundPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class UITestingPlaygroundTest extends BaseTest {

    private final String BASE_URL = com.merkosun.config.ConfigManager.getInstance().uitapBaseUrl();

    @Test(description = "Verify AJAX delay handled with explicit wait")
    public void testAjaxData() {
        UITestingPlaygroundPage playgroundPage = new UITestingPlaygroundPage(getDriver())
                .navigateTo(com.merkosun.config.ConfigManager.getInstance().uitapAjaxPath());
        
        playgroundPage.clickAjaxButton();
        String text = playgroundPage.getAjaxContentText();
        
        Assert.assertEquals(text, UITapConstants.AJAX_SUCCESS_TEXT, "AJAX content should match expected text");
    }

    @Test(description = "Verify Client Side Delay is handled correctly")
    public void testClientSideDelay() {
        UITestingPlaygroundPage playgroundPage = new UITestingPlaygroundPage(getDriver())
                .navigateTo(com.merkosun.config.ConfigManager.getInstance().uitapClientDelayPath());
        
        playgroundPage.clickClientSideDelayButton();
        String text = playgroundPage.getAjaxContentText();
        
        Assert.assertEquals(text, UITapConstants.CLIENT_DELAY_SUCCESS_TEXT, "Client side delay content should match");
    }

    @Test(description = "Verify interaction with Shadow DOM elements")
    public void testShadowDom() throws IOException, UnsupportedFlavorException {
        ShadowDomPage shadowPage = new ShadowDomPage(getDriver()).navigateTo();
        
        shadowPage.clickGenerate();
        String guid = shadowPage.getGuidValue();
        Assert.assertFalse(guid.isEmpty(), "GUID should be generated");
        
        shadowPage.clickCopy();
    }

    @Test(description = "Verify clicking a button with dynamic ID using stable locator")
    public void testDynamicId() {
        UITestingPlaygroundPage playgroundPage = new UITestingPlaygroundPage(getDriver())
                .navigateTo(com.merkosun.config.ConfigManager.getInstance().uitapDynamicIdPath());
        
        playgroundPage.clickDynamicIdButton();
    }

    @Test(description = "Verify entering text in an overlapped element after scrolling")
    public void testOverlappedElement() {
        OverlappedElementPage overlappedPage = new OverlappedElementPage(getDriver()).navigateTo();
        
        String testName = new net.datafaker.Faker().name().fullName();
        overlappedPage.enterName(testName);
        
        Assert.assertEquals(overlappedPage.getNameValue(), testName, "Name should be correctly entered after scroll");
    }
}
