package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class User_01_Register_Login_Repeat_Yourself {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demo.nopcommerce.com/");
	}
	
	@Test
	public void TC_01() {
		
	}
	
	@Test
	public void TC_02() {
		
	}
	
	@Test
	public void TC_03() {
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
