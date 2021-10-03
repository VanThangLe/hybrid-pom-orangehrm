package com.orangehrm.employee;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
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
	String empFirstName, empLastName, employeeID, statusValue, empFullName, empUserName, empPassword;
	public static String USERNAME, PASSWORD;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-condition: Step 01 - Open browser '" + browserName + "'and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGenerator.getLoginPage(driver);
		
		statusValue = "Enabled";
		empFirstName = "Automation";
		empLastName = "FC";
		empFullName = empFirstName + " " + empLastName;
		empUserName = "automationfc";
		empPassword = "automation123";
		USERNAME = empUserName;
		PASSWORD = empPassword;
		
		log.info("Pre-condition: Step 02 - Login with Admin role");
		dashboardPage = loginPage.loginToSystem(driver, GlobalConstants.ADMIN_USERNAME, GlobalConstants.ADMIN_PASSWORD);
	}
	
	@Test
	public void Add_New_Employee() {
		log.info("Add_New_01 - Step 01: Open 'Employee List' page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		
		log.info("Add_New_01 - Step 02: Click to 'Add' button");
		employeeListPage.clickToButtonByID(driver, "btnAdd");
		addEmployeePage = PageGenerator.getAddEmployeePage(driver);
		
		log.info("Add_New_01 - Step 03: Enter valid info to 'First Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "firstName", empFirstName);
		
		log.info("Add_New_01 - Step 04: Enter valid info to 'Last Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "lastName", empLastName);
		
		log.info("Add_New_01 - Step 05: Get value of 'Employee ID'");
		employeeID = addEmployeePage.getTextboxValueByID(driver, "employeeId");
		
		log.info("Add_New_01 - Step 06: Click to 'Create Login Details' checkbox");
		addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");;
		
		log.info("Add_New_01 - Step 07: Enter valid info to 'User Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "user_name", empUserName);
		
		log.info("Add_New_01 - Step 08: Enter valid info to 'Password' textbox");
		addEmployeePage.enterToTextboxByID(driver, "user_password", empPassword);
		
		log.info("Add_New_01 - Step 09: Enter valid info to 'Confirm Password' textbox");
		addEmployeePage.enterToTextboxByID(driver, "re_password", empPassword);
		
		log.info("Add_New_01 - Step 10: Select '" + statusValue + "' value in 'Status' dropdown");
		addEmployeePage.selectItemInDropdownByID(driver, "status", statusValue);
		
		log.info("Add_New_01 - Step 11: Click to 'Save' button");
		addEmployeePage.clickToButtonByID(driver, "btnSave");
		personalDetailPage = PageGenerator.getPersonalDetailPage(driver);
		
		log.info("Add_New_01 - Step 12: Open 'Employee List' page");
		personalDetailPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		
		log.info("Add_New_01 - Step 13: Enter valid info to 'Employee Name' textbox");
		verifyTrue(employeeListPage.isJQueryAJAXLoadedSuccess(driver));
		employeeListPage.enterToTextboxByID(driver, "empsearch_employee_name_empName", empFullName);
		verifyTrue(employeeListPage.isJQueryAJAXLoadedSuccess(driver));
		
		log.info("Add_New_01 - Step 14: Click to 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		verifyTrue(employeeListPage.isJQueryAJAXLoadedSuccess(driver));
		
		log.info("Add_New_01 - Step 15: Verify Employee Information displayed at 'Result Table'");
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Id", "1"), employeeID);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1"), empFirstName);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), empLastName);
	
		log.info("Add_New_01 - Step 16: Logout to System");
		employeeListPage.logoutToSystem(driver);
	}
	
	@Parameters({"browser"})
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
