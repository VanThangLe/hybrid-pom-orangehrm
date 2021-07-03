package com.alada.login;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.alada.HomePageObject;
import pageObjects.alada.LoginPageObject;

public class User_03_Login_Page_Object_Pattern {
	WebDriver driver;
	LoginPageObject loginPage;
	HomePageObject homePage;
	
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("https://alada.vn/tai-khoan/dang-nhap.html");
		loginPage = new LoginPageObject(driver);
	}
	
	@Test
	public void TC_01_Login_With_Empty_Email_Password() {
		loginPage.enterToEmailTextbox("");
		loginPage.enterToPasswordTextbox("");
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Vui lòng nhập email");
		Assert.assertEquals(loginPage.getErrorMessageAtPasswordTextbox(), "Vui lòng nhập mật khẩu");
	}
	
	@Test
	public void TC_02_Login_With_Invalid_Email() {
		loginPage.enterToEmailTextbox("automation@&^*");
		loginPage.enterToPasswordTextbox("123456");
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Vui lòng nhập email hợp lệ");
		
		loginPage.enterToEmailTextbox("123456789");
		loginPage.enterToPasswordTextbox("123456");
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Vui lòng nhập email hợp lệ");
	}
	
	@Test
	public void TC_03_Login_With_Email_Not_Registed() {
		loginPage.enterToEmailTextbox("automation" + getRandomNumber() + "@hotmail.com");
		loginPage.enterToPasswordTextbox("123456");
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtLoginForm(), "Email này chưa được đăng ký.");
	}
	
	@Test
	public void TC_04_Login_Password_Invalid() {
		//Invalid Password
		loginPage.enterToEmailTextbox("automationfc.vn@hotmail.com");
		loginPage.enterToPasswordTextbox("123");
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtLoginForm(), "Mật khẩu sai.");
		
		//Incorrect Password
		loginPage.enterToEmailTextbox("automationfc.vn@hotmail.com");
		loginPage.enterToPasswordTextbox("123456789");
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtLoginForm(), "Mật khẩu sai.");
	}
	
	@Test
	public void TC_05_Login_Valid_Email_Password() {
		loginPage.loginToSystem("automationfc.vn@hotmail.com", "123456");
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isMyCourseDisplayed());
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
