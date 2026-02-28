package com.merkosun.tests.herokuapp;

import com.merkosun.base.BaseTest;
import com.merkosun.pages.herokuapp.HerokuUploadPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class FileUploadTest extends BaseTest {

    @Test
    public void testFileUpload() {
        HerokuUploadPage uploadPage = new HerokuUploadPage(getDriver()).navigateTo();

        String fileName = "testfile.txt";
        String filePath = new File("src/test/resources/testdata/" + fileName).getAbsolutePath();

        uploadPage.uploadFile(filePath);

        Assert.assertEquals(uploadPage.getSuccessMessage(), "File Uploaded!", "Success message mismatch");
        Assert.assertEquals(uploadPage.getUploadedFileName(), fileName, "Uploaded file name mismatch");
    }

    @Test
    public void testFileUploadWithImage() {
        HerokuUploadPage uploadPage = new HerokuUploadPage(getDriver()).navigateTo();

        String fileName = "test_image.png";
        String filePath = new File("src/test/resources/testdata/" + fileName).getAbsolutePath();

        uploadPage.uploadFile(filePath);

        Assert.assertEquals(uploadPage.getSuccessMessage(), "File Uploaded!", "Success image upload message mismatch");
        Assert.assertEquals(uploadPage.getUploadedFileName(), fileName, "Uploaded image name mismatch");
    }

    @Test
    public void testFileUploadWithSpecialCharacters() {
        HerokuUploadPage uploadPage = new HerokuUploadPage(getDriver()).navigateTo();

        String fileName = "special!@#file.txt";
        String filePath = new File("src/test/resources/testdata/" + fileName).getAbsolutePath();

        uploadPage.uploadFile(filePath);

        Assert.assertEquals(uploadPage.getSuccessMessage(), "File Uploaded!", "Success special file upload message mismatch");
        Assert.assertEquals(uploadPage.getUploadedFileName(), fileName, "Special filename mismatch");
    }

    @Test
    public void testUploadWithoutFileSelection() {
        HerokuUploadPage uploadPage = new HerokuUploadPage(getDriver()).navigateTo();

        uploadPage.clickUploadWithoutFile();
        
        Assert.assertTrue(getDriver().getPageSource().contains("Internal Server Error") || uploadPage.isDragDropAreaVisible(),
                "Page should handle empty upload gracefully or show error");
    }

}
