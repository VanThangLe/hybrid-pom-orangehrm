package com.orangehrm.employee;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;

public class Employee_01_Add_Employee extends BaseTest {
	WebDriver driver;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-condition: Step 01 - Open browser '" + browserName + "'and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		
		log.info("Pre-condition: Step 02 - Login with Admin role");
	}
	
	@Test
	public void Employee_01_Add_New_Employee() {
		log.info("Add_New_01 - Step 01: Open 'Employee List' page");
		
		
		log.info("Add_New_01 - Step 02: Click to 'Add' button");
		
		
		log.info("Add_New_01 - Step 03: Enter valid info to 'First Name' textbox");
		
		
		log.info("Add_New_01 - Step 04: Enter valid info to 'Last Name' textbox");
		
		
		log.info("Add_New_01 - Step 05: Get value of 'Employee ID'");
		
		
		log.info("Add_New_01 - Step 06: Click to 'Create Login Details' checkbox");
		
		
		log.info("Add_New_01 - Step 07: Enter valid info to 'User Name' textbox");
		
		
		log.info("Add_New_01 - Step 08: Enter valid info to 'Password' textbox");
		
		
		log.info("Add_New_01 - Step 09: Select 'Enabled' value in 'Status' dropdown");
		
		
		log.info("Add_New_01 - Step 10: Click to 'Save' button");
		
		
	}
	
	@Parameters({"browser"})
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanExcutableDriver();
	}
}
