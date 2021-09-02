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
import reportConfig.ExtentTestManager;

public class Level_13_Register_Login_Log_Extent_Report extends BaseTest {
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
		ExtentTestManager.startTest(method.getName(), "User_01_Register");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "NewCustomer - Step 01: Open 'New Customer' page");
		homePage = PageGenerator.getHomePage(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 02: Click to Register link");
		registerPage = homePage.clickToRegisterLink();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 03: Click to Male radio button");
		registerPage.clickToGenderMaleRadio();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 04: Enter to Firstname textbox");
		registerPage.enterToFirstNameTextbox("John");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 05: Enter to Lastname textbox");
		registerPage.enterToLastNameTextbox("Terry");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 06: Enter to Email textbox");
		registerPage.enterToEmailTextbox(emailAddress);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 07: Enter to Password textbox");
		registerPage.enterToPasswordTextbox(password);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 08: Enter to Confirmpassword textbox");
		registerPage.enterToConfirmPasswordTextbox(password);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 09: Click to Register button");
		registerPage.clickToRegisterButton();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 11: Verify success message is displayed");
		verifyFalse(registerPage.isRegisterSuccessMessageDisplayed());
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 12: Click to Logout button");
		homePage = registerPage.clickToLogoutLink();
		
		ExtentTestManager.endTest();
	}
	
	@Test
	public void User_02_Login(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_02_Login");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "User_02_Login - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "User_02_Login - Step 02: Click to Email textbox");
		loginPage.enterToEmailTextbox(emailAddress);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "User_02_Login - Step 03: Click to Password textbox");
		loginPage.enterToPasswordTextbox(password);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "User_02_Login - Step 04: Click to Login button");
		homePage = loginPage.clickToLoginButton();
		
		ExtentTestManager.endTest();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
