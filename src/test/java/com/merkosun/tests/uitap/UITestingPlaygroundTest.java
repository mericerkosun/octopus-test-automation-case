package com.merkosun.tests.uitap;

import com.merkosun.base.BaseTest;
import com.merkosun.pages.uitap.OverlappedElementPage;
import com.merkosun.pages.uitap.ShadowDomPage;
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
        getDriver().get(BASE_URL + "/ajax");
        UITestingPlaygroundPage playgroundPage = new UITestingPlaygroundPage(getDriver());
        
        playgroundPage.clickAjaxButton();
        String text = playgroundPage.getAjaxContentText();
        
        Assert.assertEquals(text, "Data loaded with AJAX get request.", "AJAX content should match expected text after delay");
    }

    @Test(description = "Verify Client Side Delay is handled correctly")
    public void testClientSideDelay() {
        getDriver().get(BASE_URL + "/clientdelay");
        UITestingPlaygroundPage playgroundPage = new UITestingPlaygroundPage(getDriver());
        
        playgroundPage.clickClientSideDelayButton();
        String text = playgroundPage.getAjaxContentText();
        
        Assert.assertEquals(text, "Data calculated on the client side.", "Client side delay content should match");
    }

    @Test(description = "Verify interaction with Shadow DOM elements")
    public void testShadowDom() throws IOException, UnsupportedFlavorException {
        getDriver().get(BASE_URL + "/shadowdom");
        ShadowDomPage shadowPage = new ShadowDomPage(getDriver());
        
        shadowPage.clickGenerate();
        String guid = shadowPage.getGuidValue();
        Assert.assertFalse(guid.isEmpty(), "GUID should be generated");
        
        shadowPage.clickCopy();
    }

    @Test(description = "Verify clicking a button with dynamic ID using stable locator")
    public void testDynamicId() {
        getDriver().get(BASE_URL + "/dynamicid");
        UITestingPlaygroundPage playgroundPage = new UITestingPlaygroundPage(getDriver());
        
        playgroundPage.clickDynamicIdButton();
    }

    @Test(description = "Verify entering text in an overlapped element after scrolling")
    public void testOverlappedElement() {
        getDriver().get(BASE_URL + "/overlapped");
        OverlappedElementPage overlappedPage = new OverlappedElementPage(getDriver());
        
        String testName = "Octopus Test";
        overlappedPage.enterName(testName);
        
        Assert.assertEquals(overlappedPage.getNameValue(), testName, "Name should be correctly entered after scroll");
    }
}
