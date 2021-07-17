package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.OrdersPageObject;
import pageObjects.nopCommerce.AddressesPageObject;
import pageObjects.nopCommerce.CustomerInforPageObject;
import pageObjects.nopCommerce.PageGenerator;
import pageObjects.nopCommerce.RegisterPageObject;
import pageObjects.nopCommerce.RewardPointsPageObject;

public class Level_08_Dynamic_Locator extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	CustomerInforPageObject customerInforPage;
	AddressesPageObject addressesPage;
	OrdersPageObject ordersPage;
	RewardPointsPageObject rewardPointPage;
	String firstName, lastName, day, month, year, emailAddress, companyName, password;
	
	@Parameters({"browser", "url"} )
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		homePage = PageGenerator.getHomePage(driver);
		firstName = "Automation";
		lastName = "FC";
		day = "10";
		month = "May";
		year = "1960";
		emailAddress = "automation" + getRandomNumber() + "@mail.net";
		companyName = "Automation FC";
		password = "123456";
	}
	
	@Test
	public void TC_01_Register() {
		registerPage = homePage.clickToRegisterLink();
		registerPage.clickToGenderMaleRadio();
		registerPage.enterToFirstNameTextbox(firstName);
		registerPage.enterToLastNameTextbox(lastName);
		registerPage.selectDayDropdown(day);
		registerPage.selectMonthDropdown(month);
		registerPage.selectYearDropdown(year);
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToCompanyTextbox(companyName);
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isRegisterSuccessMessageDisplayed());
		homePage = registerPage.clickToLogoutLink();
	}
	
	@Test
	public void TC_02_Login() {
		loginPage = homePage.clickToLoginLink();
		loginPage.enterToEmailTextbox(emailAddress);
		loginPage.enterToPasswordTextbox(password);
		homePage = loginPage.clickToLoginButton();
	}
	
	@Test
	public void TC_03_My_Account() {
		customerInforPage = homePage.clickToMyAccountLink();
		Assert.assertTrue(customerInforPage.isGenderMaleRadioSelected());
		Assert.assertEquals(customerInforPage.getFirstNameTextboxValue(), firstName);
		Assert.assertEquals(customerInforPage.getLastNameTextboxValue(), lastName);
		Assert.assertEquals(customerInforPage.getEmailTextboxValue(), emailAddress);
		Assert.assertEquals(customerInforPage.getCompanyTextboxValue(), companyName);
		Assert.assertEquals(customerInforPage.getDayDropdownValue(), day);
		Assert.assertEquals(customerInforPage.getMonthDropdownValue(), month);
		Assert.assertEquals(customerInforPage.getYearDropdownValue(), year);
	}

	
	@Test
	public void TC_04_Switch_Page_Common() {
		ordersPage = (OrdersPageObject) customerInforPage.openSiderBarPageByName(driver, "Orders");
		rewardPointPage = (RewardPointsPageObject) ordersPage.openSiderBarPageByName(driver, "Reward points");
		addressesPage = (AddressesPageObject) rewardPointPage.openSiderBarPageByName(driver, "Addresses");
		customerInforPage = (CustomerInforPageObject) addressesPage.openSiderBarPageByName(driver, "Customer info");
		rewardPointPage = (RewardPointsPageObject) customerInforPage.openSiderBarPageByName(driver, "Reward points");
		ordersPage = (OrdersPageObject) rewardPointPage.openSiderBarPageByName(driver, "Orders");
		addressesPage = (AddressesPageObject) ordersPage.openSiderBarPageByName(driver, "Addresses");
	}
	
	@Test
	public void TC_05_Switch_Page_Common() {
		addressesPage.openSiderBarPageByPageName(driver, "Customer info");
		customerInforPage = PageGenerator.getCustomerInforPage(driver);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
