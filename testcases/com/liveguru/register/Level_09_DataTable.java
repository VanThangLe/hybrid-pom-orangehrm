package com.liveguru.register;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.liveGuru.AdminLoginPageObject;
import pageObjects.liveGuru.AdminManageCustomerPageObject;
import pageObjects.liveGuru.PageGenerator;
import pageObjects.liveGuru.UserDashboardPageObject;
import pageObjects.liveGuru.UserHomePageObject;
import pageObjects.liveGuru.UserLoginPageObject;
import pageObjects.liveGuru.UserRegisterPageObject;

public class Level_09_DataTable extends BaseTest {
	WebDriver driver;
	UserHomePageObject userHomePage;
	UserDashboardPageObject userDashboardPage;
	UserLoginPageObject userLoginPage;
	UserRegisterPageObject userRegisterPage;
	AdminLoginPageObject adminLoginPage;
	AdminManageCustomerPageObject adminManageCustomerPage;
	String firstName, lastName, emailAddress, password, fullName;
	
	@Parameters({"browser", "url"} )
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		userHomePage = PageGenerator.getUserHomePage(driver);
		firstName = "Automation";
		lastName = "FC";
		fullName = firstName + " " + lastName;
		emailAddress = "afc" + getRandomNumber() + "@hotmail.net";
		password = "P@ssWord";
	}
	
	@Test
	public void TC_01_Register_User_At_User_Page() {
		userLoginPage = userHomePage.clickToMyAccountPage();
		userRegisterPage = userLoginPage.clickCreateAnAcountBUtton();
		userRegisterPage.enterToFirstNameTextbox(firstName);
		userRegisterPage.enterToLastNameTextbox(lastName);
		userRegisterPage.enterToEmailTextbox(emailAddress);
		userRegisterPage.enterToPasswordTextbox(password);
		userRegisterPage.enterToConfirmPasswordTextbox(password);
		userDashboardPage = userRegisterPage.clickToRegisterButton();
		Assert.assertTrue(userDashboardPage.isUserRegisteredSuccessMessageDisplayed());
	}
	
	@Test
	public void TC_01_Search_User_At_Admin_Page() {
		adminLoginPage = userDashboardPage.openAdminPage();
		adminLoginPage.enterToUsernameTextbox(GlobalConstants.ADMIN_USER);
		adminLoginPage.enterToPasswordTextbox(GlobalConstants.ADMIN_PASSWORD);
		adminManageCustomerPage = adminLoginPage.clickToLoginButton();
		adminManageCustomerPage.closePopupWindow();
		adminManageCustomerPage.enterToTextboxAtColumnName("Email", "");
		adminManageCustomerPage.clickSearchButton();
		Assert.assertFalse(adminManageCustomerPage.isLoadingIconDisappear());
		Assert.assertTrue(adminManageCustomerPage.isUserInforDisplayedInTable(fullName, emailAddress));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
