package com.jquery;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.HomePageObject;
import pageObjects.jQuery.PageGenerator;

public class Level_09_DataTable extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	
	@Parameters({"browser", "url"} )
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		homePage = PageGenerator.getHomePage(driver);
	}
	
	@Test
	public void TC_01_Paging() {
		homePage.openPagingPageByName("15");
		Assert.assertTrue(homePage.isPageActiveByName("15"));
	}
	
	@Test
	public void TC_02_Search() {
		homePage.enterToHeaderTextboxByName("Country", "Argentina");
		homePage.enterToHeaderTextboxByName("Males", "349238");
		homePage.enterToHeaderTextboxByName("Total", "687522");
		homePage.enterToHeaderTextboxByName("Females", "338282");
	}
	
	@Test
	public void TC_03_Verify_Row() {
		homePage.enterToHeaderTextboxByName("Females", "338282");
		homePage.enterToHeaderTextboxByName("Country", "Argentina");
		homePage.enterToHeaderTextboxByName("Males", "349238");
		homePage.enterToHeaderTextboxByName("Total", "687522");
		Assert.assertTrue(homePage.isRowValuesDisplayed("338282", "Argentina", "349238", "687522"));
	}
	
	@Test
	public void TC_04_Action() {
		homePage.clickToRowActionByCountry("Albania","remove");
		homePage.clickToRowActionByCountry("Albania","edit");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
