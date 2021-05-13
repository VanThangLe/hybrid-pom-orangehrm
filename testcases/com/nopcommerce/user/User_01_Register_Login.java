package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class User_01_Register_Login {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("http://demo.nopcommerce.com/");
	}
	
	@Test
	public void TC_01_Register_Empty_Data() {
		
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		
	}
	
	@Test
	public void TC_03_Register_Invalid_Password() {
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
