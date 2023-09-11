package com.orangehrm.employee;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orangehrm.data.Data;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.orangehrm.AddEmployeePageObject;
import pageObjects.orangehrm.DashboardPageObject;
import pageObjects.orangehrm.EmployeeListPageObject;
import pageObjects.orangehrm.LoginPageObject;
import pageObjects.orangehrm.PageGenerator;
import pageObjects.orangehrm.PersonalDetailsPageObject;

public class Employee extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	AddEmployeePageObject addEmployeePage;
	DashboardPageObject dashboardPage;
	EmployeeListPageObject employeeListPage;
	PersonalDetailsPageObject personalDetailsPage;

	@Parameters({ "browserName", "appUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-condition: Step 01 - Open browser '" + browserName + "'and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGenerator.getLoginPage(driver);
		
		log.info("Pre-condition: Step 02 - Login with Admin role");
		loginPage.enterToTextboxByIDName(driver, "username", GlobalConstants.ADMIN_USERNAME);
		loginPage.enterToTextboxByIDName(driver, "password", GlobalConstants.ADMIN_PASSWORD);
		loginPage.clickToButtonByLabel(driver, "Login");
	}

	@Test
	public void Employee_01_Add_New_Employee() {
		log.info("Employee_01 - Step 01: Open 'Employee List' page");
		dashboardPage.openMenuPage(driver, "PIM");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Employee_01 - Step 02: Click to add employee");
		employeeListPage.clickToButtonByLabel(driver, "Add");
		addEmployeePage = PageGenerator.getAddEmployeePage(driver);

		log.info("Employee_01 - Step 03: Input data to all fields");
		addEmployeePage.uploadAvatar(driver, Data.Employee_01_Add_New_Employee.AVATAR);
		addEmployeePage.enterToTextboxByIDName(driver, "firstName", Data.Employee_01_Add_New_Employee.EMP_FIRSTNAME);
		addEmployeePage.enterToTextboxByIDName(driver, "middleName", Data.Employee_01_Add_New_Employee.EMP_MIDDLENAME);
		addEmployeePage.enterToTextboxByIDName(driver, "lastName", Data.Employee_01_Add_New_Employee.EMP_LASTNAME);
		addEmployeePage.enterToTextboxByLabel(driver, "Employee Id", Data.Employee_01_Add_New_Employee.EMP_ID);
		addEmployeePage.clickToCreateLoginDetails();
		addEmployeePage.enterToTextboxByLabel(driver, "Username", Data.Employee_01_Add_New_Employee.EMP_USERNAME);
		addEmployeePage.enterToTextboxByLabel(driver, "Password", Data.Employee_01_Add_New_Employee.EMP_PASSWORD);
		addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password", Data.Employee_01_Add_New_Employee.EMP_PASSWORD);

		log.info("Employee_01 - Step 04: Click to save data");
		addEmployeePage.clickToButtonByLabel(driver, "Save");
		
		log.info("Employee_01 - Step 05: Verify message created successfully");
		personalDetailsPage = PageGenerator.getPersonalDetailsPage(driver);
		verifyTrue(personalDetailsPage.isSuccessToastMessageDisplayed(driver));
		
		log.info("Employee_01 - Step 06: Verify data of created employee");
		verifyEquals(personalDetailsPage.getTextboxValueByIDName(driver, "firstName"), Data.Employee_01_Add_New_Employee.EMP_FIRSTNAME);
		verifyEquals(personalDetailsPage.getTextboxValueByIDName(driver, "middleName"), Data.Employee_01_Add_New_Employee.EMP_MIDDLENAME);
		verifyEquals(personalDetailsPage.getTextboxValueByIDName(driver, "lastName"), Data.Employee_01_Add_New_Employee.EMP_LASTNAME);
		verifyEquals(personalDetailsPage.getTextboxValueByLabel(driver, "Employee Id"), Data.Employee_01_Add_New_Employee.EMP_ID);
	}

	@Test
	public void Employee_02_Personal_Details() {
		log.info("Employee_02 - Step 01: Search created employee");
		
		
		log.info("Employee_02 - Step 02: Verify result after searching");

		
		log.info("Employee_02 - Step 03: Click to edit employee");
		
		
		log.info("Employee_02 - Step 04: Update nationality, marital status, date of birth, gender");
		
		
		log.info("Employee_02 - Step 05: Click to save data");
		
		
		log.info("Employee_02 - Step 06: Verify data of personal details");
		
	}

	@Test
	public void Employee_03_Contact_Details() {
		log.info("Employee_03 - Step 01: Search created employee");
		
		
		log.info("Employee_03 - Step 02: Click to edit employee");
		
		
		log.info("Employee_03 - Step 03: Open contact details");
		
		
		log.info("Employee_03 - Step 04: Verify landed contact details");
		
		
		log.info("Employee_03 - Step 05: Update all data of contact details");
		
		
		log.info("Employee_03 - Step 06: Verify message toast and updated data");
		
	}

	@Test
	public void Employee_04_Emergency_Contacts() {
		log.info("Employee_04 - Step 01: Search created employee");
		
		
		log.info("Employee_04 - Step 02: Click to edit employee");
		
		
		log.info("Employee_04 - Step 03: Open emergency contacts");
		
		
		log.info("Employee_04 - Step 04: Verify landed emergency contacts");
		
		
		log.info("Employee_04 - Step 05: Click to add emergency contacts");
		
		
		log.info("Employee_04 - Step 06: Enter data for all fields");
		
		
		log.info("Employee_04 - Step 07: Click to save data");
		
		
		log.info("Employee_04 - Step 08: Verify data emergency contacts");
		
	}

	@Test
	public void Employee_05_Assigned_Dependents() {
		log.info("Employee_05 - Step 01: Search created employee");
		
		
		log.info("Employee_05 - Step 02: Click to edit employee");
		
		
		log.info("Employee_05 - Step 03: Open dependents");
		
		
		log.info("Employee_05 - Step 04: Verify landed dependents");
		
		
		log.info("Employee_05 - Step 05: Click to add dependents");
		
		
		log.info("Employee_05 - Step 06: Enter data for all fields");
		
		
		log.info("Employee_05 - Step 07: Click to save data");
		
		
		log.info("Employee_05 - Step 08: Verify data dependents");

	}
	
	@Test
	public void Employee_06_Immigration() {
		log.info("Employee_06 - Step 01: Search created employee");
		
		
		log.info("Employee_06 - Step 02: Click to edit employee");
		
		
		log.info("Employee_06 - Step 03: Open immigrations");
		
		
		log.info("Employee_06 - Step 04: Verify landed immigrations");
		
		
		log.info("Employee_06 - Step 05: Click to add assigned immigration records");
		
		
		log.info("Employee_06 - Step 06: Enter data for all fields");
		
		
		log.info("Employee_06 - Step 07: Click to save data");
		
		
		log.info("Employee_06 - Step 08: Verify data assigned immigration records");

	}

	@Test
	public void Employee_07_Job() {
		log.info("Employee_07 - Step 01: Search created employee");
		
		
		log.info("Employee_07 - Step 02: Click to edit employee");
		
		
		log.info("Employee_07 - Step 03: Open job");
		
		
		log.info("Employee_07 - Step 04: Verify landed job");
		
		
		log.info("Employee_07 - Step 05: Select filter");
		
		
		log.info("Employee_07 - Step 06: Click save filter");
		
		
		log.info("Employee_07 - Step 07: Verify filter");
		
	}

	@Test
	public void Employee_08_Salary() {
		log.info("Employee_08 - Step 01: Search created employee");
		
		
		log.info("Employee_08 - Step 02: Click to edit employee");
		
		
		log.info("Employee_08 - Step 03: Open salary");
		
		
		log.info("Employee_08 - Step 04: Verify landed salary");
		
		
		log.info("Employee_08 - Step 05: Click to add salary");
		
		
		log.info("Employee_08 - Step 06: Enter data for all fields");
		
		
		log.info("Employee_08 - Step 07: Click to save data");
		
		
		log.info("Employee_08 - Step 08: Verify data salary");

	}

	@Test
	public void Employee_09_Tax_Exemptions() {
		log.info("Employee_09 - Step 01: Search created employee");

		
		log.info("Employee_09 - Step 02: Click to edit employee");
		
		
		log.info("Employee_09 - Step 03: Open tax exemptions");
		
		
		log.info("Employee_09 - Step 04: Verify landed tax exemptions");
		
		
		log.info("Employee_09 - Step 05: Select filter");
		
		
		log.info("Employee_09 - Step 06: Click save filter");
		
		
		log.info("Employee_09 - Step 07: Verify filter");
		
	}
	
	@Test
	public void Employee_10_Report_To_Assigned_Supervisors() {
		log.info("Employee_10 - Step 01: Search created employee");
		
		
		log.info("Employee_10 - Step 02: Click to edit employee");
		
		
		log.info("Employee_10 - Step 03: Open report to");
		
		
		log.info("Employee_10 - Step 04: Verify landed report to");
		
		
		log.info("Employee_10 - Step 05: Click to add assigned supervisors");
		
		
		log.info("Employee_10 - Step 06: Enter data for all fields");
		
		
		log.info("Employee_10 - Step 07: Click to save data");
		
		
		log.info("Employee_10 - Step 08: Verify data assigned supervisors");

	}
	
	@Test
	public void Employee_11_Report_To_Assigned_Subordinates() {
		log.info("Employee_11 - Step 01: Search created employee");
		
		
		log.info("Employee_11 - Step 02: Click to edit employee");
		
		
		log.info("Employee_11 - Step 03: Open report to");
		
		
		log.info("Employee_11 - Step 04: Verify landed report to");
		
		
		log.info("Employee_11 - Step 05: Click to add assigned subordinates");
		
		
		log.info("Employee_11 - Step 06: Enter data for all fields");
		
		
		log.info("Employee_11 - Step 07: Click to save data");
		
		
		log.info("Employee_11 - Step 08: Verify data assigned subordinates");

	}

	@Test
	public void Employee_12_Qualifications_Work_Experience() {
		log.info("Employee_12 - Step 01: Search created employee");
		
		
		log.info("Employee_12 - Step 02: Click to edit employee");
		
		
		log.info("Employee_12 - Step 03: Open qualifications");
		
		
		log.info("Employee_12 - Step 04: Verify landed qualifications");
		
		
		log.info("Employee_12 - Step 05: Click to add work experience");
		
		
		log.info("Employee_12 - Step 06: Enter data for all fields");
		
		
		log.info("Employee_12 - Step 07: Click to save data");
		
		
		log.info("Employee_12 - Step 08: Verify data work experience");

	}
	
	@Test
	public void Employee_13_Qualifications_Education() {
		log.info("Employee_13 - Step 01: Search created employee");
		
		
		log.info("Employee_13 - Step 02: Click to edit employee");
		
		
		log.info("Employee_13 - Step 03: Open qualifications");
		
		
		log.info("Employee_13 - Step 04: Verify landed qualifications");
		
		
		log.info("Employee_13 - Step 05: Click to add education");
		
		
		log.info("Employee_13 - Step 06: Enter data for all fields");
		
		
		log.info("Employee_13 - Step 07: Click to save data");
		
		
		log.info("Employee_13 - Step 08: Verify data education");

	}
	
	@Test
	public void Employee_14_Qualifications_Skill() {
		log.info("Employee_14 - Step 01: Search created employee");
		
		
		log.info("Employee_14 - Step 02: Click to edit employee");
		
		
		log.info("Employee_14 - Step 03: Open qualifications");
		
		
		log.info("Employee_14 - Step 04: Verify landed qualifications");
		
		
		log.info("Employee_14 - Step 05: Click to add skill");
		
		
		log.info("Employee_14 - Step 06: Enter data for all fields");
		
		
		log.info("Employee_14 - Step 07: Click to save data");
		
		
		log.info("Employee_14 - Step 08: Verify data skill");

	}
	
	@Test
	public void Employee_15_Qualifications_Languages() {
		log.info("Employee_15 - Step 01: Search created employee");
		
		
		log.info("Employee_15 - Step 02: Click to edit employee");
		
		
		log.info("Employee_15 - Step 03: Open qualifications");
		
		
		log.info("Employee_15 - Step 04: Verify landed qualifications");
		
		
		log.info("Employee_15 - Step 05: Click to add languages");
		
		
		log.info("Employee_15 - Step 06: Enter data for all fields");
		
		
		log.info("Employee_15 - Step 07: Click to save data");
		
		
		log.info("Employee_15 - Step 08: Verify data languages");

	}
	
	@Test
	public void Employee_16_Qualifications_License() {
		log.info("Employee_16 - Step 01: Search created employee");
		
		
		log.info("Employee_16 - Step 02: Click to edit employee");
		
		
		log.info("Employee_16 - Step 03: Open qualifications");
		
		
		log.info("Employee_16 - Step 04: Verify landed qualifications");
		
		
		log.info("Employee_16 - Step 05: Click to add license");
		
		
		log.info("Employee_16 - Step 06: Enter data for all fields");
		
		
		log.info("Employee_16 - Step 07: Click to save data");
		
		
		log.info("Employee_16 - Step 08: Verify data license");

	}
	
	@Test
	public void Employee_17_Memberships() {
		log.info("Employee_17 - Step 01: Search created employee");
		
		
		log.info("Employee_17 - Step 02: Click to edit employee");
		
		
		log.info("Employee_17 - Step 03: Open memberships");
		
		
		log.info("Employee_17 - Step 04: Verify landed memberships");
		
		
		log.info("Employee_17 - Step 05: Click to add memberships");
		
		
		log.info("Employee_17 - Step 06: Enter data for all fields");
		
		
		log.info("Employee_17 - Step 07: Click to save data");
		
		
		log.info("Employee_17 - Step 08: Verify data memberships");

	}
	
	@Test
	public void Employee_18_Delete_Employee() {
		log.info("Employee_18 - Step 01: Search created employee");
		
		
		log.info("Employee_18 - Step 02: Click to delete employee");
		
		
		log.info("Employee_18 - Step 03: Confirm delete popup");
		
		
		log.info("Employee_18 - Step 04: Verify deleted employee");

	}

	@Parameters({ "browserName" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
