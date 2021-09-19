package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Login;

import commons.BaseTest;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.PageGenerator;

public class Level_14_Register_Login_Log_Share_State extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	LoginPageObject loginPage;
	
	@Parameters({"browser", "url"} )
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		log.info("Pre-condition - Open browser '" + browserName + "'and navigate to '" + url + "'");
		driver = getBrowserDriver(browserName, url);
		homePage = PageGenerator.getHomePage(driver);
		
		log.info("Pre-condition - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Pre-condition - Step 02: Set login page cookie");
		loginPage.setAllCookies(driver, Common_01_Login.loginPageCookie);
		loginPage.sleepInSecond(3);
		loginPage.refreshCurrentPage(driver);
		
		log.info("Pre-condition - Step 03: Click to HomePage image");
		loginPage.openHomePage();
	}
	
	@Test
	public void User_01_ABC() {
		
	}
	
	@Parameters({"browser"})
	@AfterClass (alwaysRun = true)
	public void afterClass(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
