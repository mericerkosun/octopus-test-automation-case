package com.merkosun.pages.herokuapp;

import com.merkosun.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HerokuUploadPage extends BasePage {

    private static final By FILE_INPUT = By.id("file-upload");
    private static final By UPLOAD_BUTTON = By.id("file-submit");
    private static final By SUCCESS_HEADER = By.cssSelector("div#content h3");
    private static final By UPLOADED_FILENAME = By.id("uploaded-files");
    private static final By DRAG_DROP_AREA = By.id("drag-drop-upload");


    public HerokuUploadPage(WebDriver driver) {
        super(driver);
    }

    public HerokuUploadPage navigateTo() {
        driver.get(com.merkosun.config.ConfigManager.getInstance().theinternetUploadUrl());
        return this;
    }

    public void uploadFile(String absolutePath) {
        uploadFileToElement(FILE_INPUT, absolutePath);
        clickToElement(UPLOAD_BUTTON);
    }

    public String getSuccessMessage() {
        return getTextFromElement(SUCCESS_HEADER);
    }

    public String getUploadedFileName() {
        return getTextFromElement(UPLOADED_FILENAME);
    }

    public boolean isDragDropAreaVisible() {
        return isElementVisible(DRAG_DROP_AREA);
    }

    public void clickUploadWithoutFile() {
        clickToElement(UPLOAD_BUTTON);
    }
}
