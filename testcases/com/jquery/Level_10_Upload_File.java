package com.jquery;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BaseTest;
import pageObjects.jQuery.PageGenerator;
import pageObjects.jQuery.UploadPageObject;

public class Level_10_Upload_File extends BaseTest {
	WebDriver driver;
	UploadPageObject uploadPage;
	String aName = "a.jpg";
	String bName = "b.jpg";
	String cName = "c.jpg";
	
	@Parameters({"browser", "url"} )
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		uploadPage = PageGenerator.getUploadPage(driver);
	}
	
	@Test
	public void TC_01_Upload_One_File() {
		uploadPage.uploadFiles(aName);
		Assert.assertTrue(uploadPage.isFileNameLoadedSuccess(aName));
		uploadPage.clickToStartButtonByFileName(aName);
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(aName));
	}
	
	@Test
	public void TC_02_Upload_Multiple_Files() {
		uploadPage.refreshCurrentPage(driver);
		uploadPage.uploadFiles(aName, bName, cName);
		Assert.assertTrue(uploadPage.isFileNameLoadedSuccess(aName));
		Assert.assertTrue(uploadPage.isFileNameLoadedSuccess(bName));
		Assert.assertTrue(uploadPage.isFileNameLoadedSuccess(cName));
		uploadPage.clickToStartButtonByFileName(aName);
		uploadPage.clickToStartButtonByFileName(bName);
		uploadPage.clickToStartButtonByFileName(cName);
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(aName));
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(bName));
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(cName));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
