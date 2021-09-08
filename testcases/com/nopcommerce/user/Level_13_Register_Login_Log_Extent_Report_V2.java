package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import commons.BaseTest;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.PageGenerator;
import pageObjects.nopCommerce.RegisterPageObject;
import reportConfig.V2ExtentTestManager;

public class Level_13_Register_Login_Log_Extent_Report_V2 extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	String emailAddress, password;
	
	@Parameters({"browser", "url"} )
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		emailAddress = "automation" + getRandomNumber() + "@mail.net";
		password = "123456";
	}
	
	@Test
	public void User_01_Register(Method method) {
		V2ExtentTestManager.startTest(method.getName(), "User_01_Register");
		
		V2ExtentTestManager.getTest().log(LogStatus.INFO, "NewCustomer - Step 01: Open 'New Customer' page");
		homePage = PageGenerator.getHomePage(driver);
		
		V2ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 02: Click to Register link");
		registerPage = homePage.clickToRegisterLink();
		
		V2ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 03: Click to Male radio button");
		registerPage.clickToGenderMaleRadio();
		
		V2ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 04: Enter to Firstname textbox");
		registerPage.enterToFirstNameTextbox("John");
		
		V2ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 05: Enter to Lastname textbox");
		registerPage.enterToLastNameTextbox("Terry");
		
		V2ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 06: Enter to Email textbox");
		registerPage.enterToEmailTextbox(emailAddress);
		
		V2ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 07: Enter to Password textbox");
		registerPage.enterToPasswordTextbox(password);
		
		V2ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 08: Enter to Confirmpassword textbox");
		registerPage.enterToConfirmPasswordTextbox(password);
		
		V2ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 09: Click to Register button");
		registerPage.clickToRegisterButton();
		
		V2ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 11: Verify success message is displayed");
		verifyFalse(registerPage.isRegisterSuccessMessageDisplayed());
		
		V2ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 12: Click to Logout button");
		homePage = registerPage.clickToLogoutLink();
		
		V2ExtentTestManager.endTest();
	}
	
	@Test
	public void User_02_Login(Method method) {
		V2ExtentTestManager.startTest(method.getName(), "User_02_Login");
		
		V2ExtentTestManager.getTest().log(LogStatus.INFO, "User_02_Login - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		V2ExtentTestManager.getTest().log(LogStatus.INFO, "User_02_Login - Step 02: Click to Email textbox");
		loginPage.enterToEmailTextbox(emailAddress);
		
		V2ExtentTestManager.getTest().log(LogStatus.INFO, "User_02_Login - Step 03: Click to Password textbox");
		loginPage.enterToPasswordTextbox(password);
		
		V2ExtentTestManager.getTest().log(LogStatus.INFO, "User_02_Login - Step 04: Click to Login button");
		homePage = loginPage.clickToLoginButton();
		
		V2ExtentTestManager.endTest();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
