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

public class Level_15_Register_Login_Pattern_Object extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	String emailAddress, password, day, month, year;
	
	@Parameters({"browser", "url"} )
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		log.info("Pre-condition - Open browser '" + browserName + "'and navigate to '" + url + "'");
		driver = getBrowserDriver(browserName, url);
		emailAddress = "automation" + getRandomNumber() + "@mail.net";
		password = "123456";
		day = "20";
		month = "July";
		year = "1994";
	}
	
	@Test
	public void User_01_Register() {
		log.info("User_01_Register - Step 01: Verify Home Page is displayed");
		homePage = PageGenerator.getHomePage(driver);
		
		log.info("User_01_Register - Step 02: Click to Register link");
		homePage.openHeaderPageByPageName(driver, "Register");
		registerPage = PageGenerator.getRegisterPage(driver);
		
		log.info("User_01_Register - Step 03: Click to Male radio button");
		registerPage.clickToRadioButtonByLabel(driver, "Male");
		
		log.info("User_01_Register - Step 04: Enter to Firstname textbox");
		registerPage.enterToTextboxByID(driver, "FirstName", "John");
		
		log.info("User_01_Register - Step 05: Enter to Lastname textbox");
		registerPage.enterToTextboxByID(driver, "LastName", "Terry");
		
		log.info("User_01_Register - Step 06: Enter to Email textbox");
		registerPage.enterToTextboxByID(driver, "Email", emailAddress);
		
		log.info("User_01_Register - Step 07: Select item in Day dropdown");
		registerPage.selectDropdownByName(driver, "DateOfBirthDay", day);
		
		log.info("User_01_Register - Step 08: Select item in Month dropdown");
		registerPage.selectDropdownByName(driver, "DateOfBirthMonth", month);
		
		log.info("User_01_Register - Step 09: Select item in Year dropdown");
		registerPage.selectDropdownByName(driver, "DateOfBirthYear", year);
		
		log.info("User_01_Register - Step 10: Enter to Password textbox");
		registerPage.enterToTextboxByID(driver, "Password", password);
		
		log.info("User_01_Register - Step 11: Enter to Confirmpassword textbox");
		registerPage.enterToTextboxByID(driver, "ComfirmPassword", password);
		
		log.info("User_01_Register - Step 12: Click to Register button");
		registerPage.clickToButtonByText(driver, "Register");
		
		log.info("User_01_Register - Step 13: Verify success message is displayed");
		verifyFalse(registerPage.isRegisterSuccessMessageDisplayed());
		
		log.info("User_01_Register - Step 14: Click to Logout button");
		registerPage.openHeaderPageByPageName(driver, "Log out");
		homePage = PageGenerator.getHomePage(driver);
	}
	
	@Test
	public void User_02_Login() {
		log.info("User_02_Login - Step 01: Click to Login link");
		homePage.openHeaderPageByPageName(driver, "Log in");
		loginPage = PageGenerator.getLoginPage(driver);
	
		log.info("User_02_Login - Step 02: Click to Email textbox");
		loginPage.enterToTextboxByID(driver, "Email", emailAddress);
		
		log.info("User_02_Login - Step 03: Click to Password textbox");
		loginPage.enterToTextboxByID(driver, "Password", password);
		
		log.info("User_02_Login - Step 04: Click to Login button");
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGenerator.getHomePage(driver);
	}
	
	@Parameters({"browser"})
	@AfterClass (alwaysRun = true)
	public void afterClass(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
