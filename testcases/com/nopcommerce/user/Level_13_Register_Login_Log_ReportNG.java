package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.PageGenerator;
import pageObjects.nopCommerce.RegisterPageObject;

public class Level_13_Register_Login_Log_ReportNG extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	String emailAddress, password;
	
	@Parameters({"browser", "url"} )
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		log.info("Pre-condition - Open browser '" + browserName + "'and navigate to '" + url + "'");
		driver = getBrowserDriver(browserName, url);
		emailAddress = "automation" + getRandomNumber() + "@mail.net";
		password = "123456";
	}
	
	@Test
	public void User_01_Register() {
		log.info("User_01_Register - Step 01: Verify Home Page is displayed");
		homePage = PageGenerator.getHomePage(driver);
		
		log.info("User_01_Register - Step 02: Click to Register link");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("User_01_Register - Step 03: Click to Male radio button");
		registerPage.clickToGenderMaleRadio();
		
		log.info("User_01_Register - Step 04: Enter to Firstname textbox");
		registerPage.enterToFirstNameTextbox("John");
		
		log.info("User_01_Register - Step 05: Enter to Lastname textbox");
		registerPage.enterToLastNameTextbox("Terry");
		
		log.info("User_01_Register - Step 06: Enter to Email textbox");
		registerPage.enterToEmailTextbox(emailAddress);
		
		log.info("User_01_Register - Step 07: Enter to Password textbox");
		registerPage.enterToPasswordTextbox(password);
		
		log.info("User_01_Register - Step 08: Enter to Confirmpassword textbox");
		registerPage.enterToConfirmPasswordTextbox(password);
		
		log.info("User_01_Register - Step 09: Click to Register button");
		registerPage.clickToRegisterButton();
		
		log.info("User_01_Register - Step 11: Verify success message is displayed");
		verifyFalse(registerPage.isRegisterSuccessMessageDisplayed());
		
		log.info("User_01_Register - Step 12: Click to Logout button");
		homePage = registerPage.clickToLogoutLink();
	}
	
	@Test
	public void User_02_Login() {
		log.info("User_02_Login - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("User_02_Login - Step 02: Click to Email textbox");
		loginPage.enterToEmailTextbox(emailAddress);
		
		log.info("User_02_Login - Step 03: Click to Password textbox");
		loginPage.enterToPasswordTextbox(password);
		
		log.info("User_02_Login - Step 04: Click to Login button");
		homePage = loginPage.clickToLoginButton();
	}
	
	@Parameters({"browser"})
	@AfterClass (alwaysRun = true)
	public void afterClass(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanBrowserAndDriver();
	}
}
