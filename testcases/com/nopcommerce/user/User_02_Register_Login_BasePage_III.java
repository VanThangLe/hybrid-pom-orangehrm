package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class User_02_Register_Login_BasePage_III extends BasePage {
	WebDriver driver;
	Select select;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, day, month, year, emailAddress, companyName, password;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		openPageUrl(driver, "http://demo.nopcommerce.com/");
		
		firstName = "Automation";
		lastName = "FC";
		day = "10";
		month = "May";
		year = "1999";
		emailAddress = "automation" + getRandomNumber() + "@mailinator.com";
		companyName = "Automation FC";
		password = "123456";
	}
	
	@Test
	public void TC_01_Register() {
		clickToElement(driver, "//a[@class='ico-register']");
		
		checkToCheckboxRadio(driver, "//input[@id='gender-male']");
		sendkeyToElement(driver, "//input[@id='FirstName']", firstName);
		sendkeyToElement(driver, "//input[@id='LastName']", lastName);
		
		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthDay']", day);
		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthMonth']", month);
		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthYear']", year);
		
		sendkeyToElement(driver, "//input[@id='Email']", lastName);
		sendkeyToElement(driver, "//input[@id='Company']", lastName);

		checkToCheckboxRadio(driver, "//input[@id='Newsletter']");
		
		sendkeyToElement(driver, "//input[@id='Password']", password);
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", password);
		
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
		
		clickToElement(driver, "//a[@class='ico-logout']");
	}
	
	@Test
	public void TC_02_Login() {
		clickToElement(driver, "//a[@class='ico-login']");
		
		sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
		sendkeyToElement(driver, "//input[@id='Password']", password);
		clickToElement(driver, "//button[text()='Log In']");
		
		Assert.assertTrue(isElementDisplayed(driver, "//a[@class='icon-account']"));
		Assert.assertTrue(isElementDisplayed(driver, "//a[@class='icon-logout']"));
	}
	
	@Test
	public void TC_03_My_Account() {
		clickToElement(driver, "//a[@class='ico-account']");
		
		Assert.assertEquals(getElementText(driver, "//h1"), "My account - Customer info");
		Assert.assertTrue(isElementSelected(driver, "//input[@id='gender-male']"));
		
		Assert.assertEquals(getAttributeValue(driver, "//input[@id='FirstName']", "value"), firstName);
		Assert.assertEquals(getAttributeValue(driver, "//input[@id='LastName']", "value"), lastName);
		Assert.assertEquals(getAttributeValue(driver, "//input[@id='Email']", "value"), emailAddress);
		Assert.assertEquals(getAttributeValue(driver, "//input[@id='Company']", "value"), companyName);
		
		Assert.assertEquals(getFirstSelectedItemInDefaultDropdown(driver, "//select[@name='DateOfBirthDay']"), day);
		Assert.assertEquals(getFirstSelectedItemInDefaultDropdown(driver, "//select[@name='DateOfBirthMonth']"), month);
		Assert.assertEquals(getFirstSelectedItemInDefaultDropdown(driver, "//select[@name='DateOfBirthYear']"), year);
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
