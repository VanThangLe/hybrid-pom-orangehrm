package com.orangehrm.employee;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.orangehrm.AddEmployeePO;
import pageObjects.orangehrm.DashboardPO;
import pageObjects.orangehrm.EmployeeListPO;
import pageObjects.orangehrm.LoginPO;
import pageObjects.orangehrm.PageGenerator;
import pageObjects.orangehrm.PersonalDetailPO;

public class Employee_01_Add_Employee extends BaseTest {
	WebDriver driver;
	LoginPO loginPage;
	AddEmployeePO addEmployeePage;
	DashboardPO dashboardPage;
	EmployeeListPO employeeListPage;
	PersonalDetailPO personalDetailPage;
	String employeeID, statusValue;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-condition: Step 01 - Open browser '" + browserName + "'and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGenerator.getLoginPage(driver);
		
		statusValue = "Enabled";
		
		log.info("Pre-condition: Step 02 - Login with Admin role");
		dashboardPage = loginPage.loginToSystem("Admin", "admin123");
	}
	
	@Test
	public void Employee_01_Add_New_Employee() {
		log.info("Add_New_01 - Step 01: Open 'Employee List' page");
		employeeListPage = dashboardPage.openEmployeeListPage();
		
		log.info("Add_New_01 - Step 02: Click to 'Add' button");
		addEmployeePage = employeeListPage.clickToAddButton();
		
		log.info("Add_New_01 - Step 03: Enter valid info to 'First Name' textbox");
		addEmployeePage.enterToFirstNameTextbox("");
		
		log.info("Add_New_01 - Step 04: Enter valid info to 'Last Name' textbox");
		addEmployeePage.enterToLastNameTextbox("");
		
		log.info("Add_New_01 - Step 05: Get value of 'Employee ID'");
		employeeID = addEmployeePage.getEmployeeID();
		
		log.info("Add_New_01 - Step 06: Click to 'Create Login Details' checkbox");
		addEmployeePage.clickToCreateLoginDetailCheckbox();
		
		log.info("Add_New_01 - Step 07: Enter valid info to 'User Name' textbox");
		addEmployeePage.enterToUserNameTextbox("");
		
		log.info("Add_New_01 - Step 08: Enter valid info to 'Password' textbox");
		addEmployeePage.enterToPasswordTextbox("");

		log.info("Add_New_01 - Step 09: Enter valid info to 'Confirm Password' textbox");
		addEmployeePage.enterToConfirmPasswordTextbox("");
		
		log.info("Add_New_01 - Step 10: Select '" + statusValue + "' value in 'Status' dropdown");
		addEmployeePage.selectValueInStatusDropdown(statusValue);
		
		log.info("Add_New_01 - Step 11: Click to 'Save' button");
		personalDetailPage = addEmployeePage.clickToSaveButton();
		
		log.info("Add_New_01 - Step 12: Open 'Employee List' page");
		employeeListPage = personalDetailPage.openEmployeeListPage();
		
		log.info("Add_New_01 - Step 13: Enter valid info to 'Employee Name' textbox");
		employeeListPage.enterToEmployeeNameTextbox("");
		
		log.info("Add_New_01 - Step 14: Click to 'Search' button");
		employeeListPage.clickToSearchButton();
		
		log.info("Add_New_01 - Step 15: Verify Employee Information displayed at 'Result Table'");
		verifyTrue(employeeListPage.isEmployeeInfoDisplayedAtTable("", "", ""));
	}
	
	@Parameters({"browser"})
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanExcutableDriver();
	}
}
