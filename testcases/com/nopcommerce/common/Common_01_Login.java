package com.nopcommerce.common;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.PageGenerator;
import pageObjects.nopCommerce.RegisterPageObject;

public class Common_01_Login extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	String emailAddress, password;
	public static Set<Cookie> loginPageCookie;
	
	@Parameters({"browser", "url"} )
	@BeforeTest
	public void beforeTest(String browserName, String url) {
		log.info("Pre-condition - Open browser '" + browserName + "'and navigate to '" + url + "'");
		driver = getBrowserDriver(browserName, url);
		emailAddress = "automation" + getRandomNumber() + "@mail.net";
		password = "123456";

		log.info("Common_01 - Step 01: Verify Home Page is displayed");
		homePage = PageGenerator.getHomePage(driver);
		
		log.info("Common_01 - Step 02: Click to Register link");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Common_01 - Step 03: Click to Male radio button");
		registerPage.clickToGenderMaleRadio();
		
		log.info("Common_01 - Step 04: Enter to Firstname textbox");
		registerPage.enterToFirstNameTextbox("John");
		
		log.info("Common_01 - Step 05: Enter to Lastname textbox");
		registerPage.enterToLastNameTextbox("Terry");
		
		log.info("Common_01 - Step 06: Enter to Email textbox");
		registerPage.enterToEmailTextbox(emailAddress);
		
		log.info("Common_01 - Step 07: Enter to Password textbox");
		registerPage.enterToPasswordTextbox(password);
		
		log.info("Common_01 - Step 08: Enter to Confirmpassword textbox");
		registerPage.enterToConfirmPasswordTextbox(password);
		
		log.info("Common_01 - Step 09: Click to Register button");
		registerPage.clickToRegisterButton();
		
		log.info("Common_01 - Step 10: Verify success message is displayed");
		verifyFalse(registerPage.isRegisterSuccessMessageDisplayed());
		
		log.info("Common_01 - Step 11: Click to Logout button");
		homePage = registerPage.clickToLogoutLink();

		log.info("Common_01 - Step 12: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Common_01 - Step 13: Click to Email textbox");
		loginPage.enterToEmailTextbox(emailAddress);
		
		log.info("Common_01 - Step 14: Click to Password textbox");
		loginPage.enterToPasswordTextbox(password);
		
		log.info("Common_01 - Step 15: Click to Login button");
		homePage = loginPage.clickToLoginButton();
		
		log.info("Common_01 - Step 16: Get all login page cookies");
		loginPageCookie = homePage.getAllCookies(driver);
		
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
