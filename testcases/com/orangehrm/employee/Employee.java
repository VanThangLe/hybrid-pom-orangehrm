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
import pageObjects.orangehrm.MyInfoPO;

public class Employee extends BaseTest {
	WebDriver driver;
	LoginPO loginPage;
	AddEmployeePO addEmployeePage;
	DashboardPO dashboardPage;
	EmployeeListPO employeeListPage;
	MyInfoPO myInfoPage;
	String empFirstName, empLastName, employeeID, statusValue, empFullName, empUserName, empPassword;
	String avatarFilePath = GlobalConstants.UPLOAD_FOLDER_PATH + "a.jpg";

	@Parameters({ "browser", "url" })
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

		log.info("Pre-condition: Step 02 - Login with Admin role");
		dashboardPage = loginPage.loginToSystem(driver, GlobalConstants.ADMIN_USERNAME, GlobalConstants.ADMIN_PASSWORD);
	}

	@Test
	public void Employee_01_Add_New_Employee() {
		log.info("Employee_01 - Step 01: Open 'Employee List' page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Employee_01 - Step 02: Click to 'Add' button");
		employeeListPage.clickToButtonByID(driver, "btnAdd");
		addEmployeePage = PageGenerator.getAddEmployeePage(driver);

		log.info("Employee_01 - Step 03: Enter valid info to 'First Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "firstName", empFirstName);

		log.info("Employee_01 - Step 04: Enter valid info to 'Last Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "lastName", empLastName);

		log.info("Employee_01 - Step 05: Get value of 'Employee ID'");
		employeeID = addEmployeePage.getTextboxValueByID(driver, "employeeId");

		log.info("Employee_01 - Step 06: Click to 'Create Login Details' checkbox");
		addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");

		log.info("Employee_01 - Step 07: Enter valid info to 'User Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "user_name", empUserName);

		log.info("Employee_01 - Step 08: Enter valid info to 'Password' textbox");
		addEmployeePage.enterToTextboxByID(driver, "user_password", empPassword);

		log.info("Employee_01 - Step 09: Enter valid info to 'Confirm Password' textbox");
		addEmployeePage.enterToTextboxByID(driver, "re_password", empPassword);

		log.info("Employee_01 - Step 10: Select '" + statusValue + "' value in 'Status' dropdown");
		addEmployeePage.selectItemInDropdownByID(driver, "status", statusValue);

		log.info("Employee_01 - Step 11: Click to 'Save' button");
		addEmployeePage.clickToButtonByID(driver, "btnSave");
		myInfoPage = PageGenerator.getMyInfoPage(driver);

		log.info("Employee_01 - Step 12: Open 'Employee List' page");
		myInfoPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Employee_01 - Step 13: Enter valid info to 'Employee Name' textbox");
		verifyTrue(employeeListPage.isJQueryAJAXLoadedSuccess(driver));
		employeeListPage.enterToTextboxByID(driver, "empsearch_employee_name_empName", empFullName);
		verifyTrue(employeeListPage.isJQueryAJAXLoadedSuccess(driver));

		log.info("Employee_01 - Step 14: Click to 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		verifyTrue(employeeListPage.isJQueryAJAXLoadedSuccess(driver));

		log.info("Employee_01 - Step 15: Verify Employee Information displayed at 'Result Table'");
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Id", "1"), employeeID);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1"), empFirstName);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), empLastName);
	}

	@Test
	public void Employee_02_Upload_Avatar() {
		log.info("Employee_02 - Step 01: Login with Employee role");
		loginPage = employeeListPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, empUserName, empPassword);

		log.info("Employee_02 - Step 02: Open Personal Detail page");
		dashboardPage.openMenuPage(driver, "My Info");
		myInfoPage = PageGenerator.getMyInfoPage(driver);

		log.info("Employee_02 - Step 03: Click to Change Photo image");
		myInfoPage.clickToChangePhotoImage();

		log.info("Employee_02 - Step 04: Upload new Avatar image");
		myInfoPage.uploadImage(driver, avatarFilePath);

		log.info("Employee_02 - Step 05: Click to Upload button");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Employee_02 - Step 06: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Uploaded"));

		log.info("Employee_02 - Step 07: Verify new Avatar image is displayed");
		verifyTrue(myInfoPage.isNewAvatarImageDisplayed());
	}

	@Test
	public void Employee_03_Personal_Details() {
		log.info("Employee_03- Step 01: Open 'Personal Details' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Personal Details");
		
		log.info("Employee_03- Step 02: Verify all fields at 'Personal Details' tab are disabled");

		log.info("Employee_03- Step 03: Click to 'Edit' button at 'Personal Details' form");

		log.info("Employee_03- Step 04: Verify 'Employee Id' textbox is disabled");
		
		log.info("Employee_03- Step 05: Verify 'Driver's License Number' textbox is disabled");
		
		log.info("Employee_03- Step 06: Verify 'SSN Number' textbox is disabled");
		
		log.info("Employee_03- Step 07: Verify 'SIN Number' textbox is disabled");
		
		log.info("Employee_03- Step 08: Verify 'Date of Birth' textbox is disabled");

		log.info("Employee_03- Step 09: Enter new value to 'First Name' textbox");
		
		log.info("Employee_03- Step 10: Enter new value to 'Last Name' textbox");
		
		log.info("Employee_03- Step 11: Enter new value to 'Gender' textbox");
		
		log.info("Employee_03- Step 12: Select new value to 'Marital Single' radio button");
		
		log.info("Employee_03- Step 13: Select new value to 'Nationality' dropdown");
		
		log.info("Employee_03- Step 14: Click to 'Save' button at 'Personal Details' form");
		
		log.info("Employee_03- Step 15: Verify");
		
		log.info("Employee_03- Step 16: Click to 'Save' button at 'Personal Details' form");

	}

	@Test
	public void Employee_04_Contact_Details() {
		log.info("Employee_04- Step 01: ");

		log.info("Employee_04- Step 02: ");

		log.info("Employee_04- Step 03: ");

		log.info("Employee_04- Step 01: ");

		log.info("Employee_04- Step 01: ");

	}

	@Test
	public void Employee_05_Emergency_Details() {
		log.info("Employee_05- Step 01: ");

		log.info("Employee_05- Step 02: ");

		log.info("Employee_05- Step 03: ");

		log.info("Employee_05- Step 01: ");

		log.info("Employee_05- Step 01: ");

	}

	@Test
	public void Employee_06_Assigned_Dependents() {
		log.info("Employee_06- Step 01: ");

		log.info("Employee_06- Step 02: ");

		log.info("Employee_06- Step 03: ");

		log.info("Employee_06- Step 01: ");

		log.info("Employee_06- Step 01: ");

	}

	@Test
	public void Employee_07_Edit_View_Job() {
		log.info("Employee_07- Step 01: ");

		log.info("Employee_07- Step 02: ");

		log.info("Employee_07- Step 03: ");

		log.info("Employee_07- Step 01: ");

		log.info("Employee_07- Step 01: ");

	}

	@Test
	public void Employee_08_Edit_View_Salary() {
		log.info("Employee_08- Step 01: ");

		log.info("Employee_08- Step 02: ");

		log.info("Employee_08- Step 03: ");

		log.info("Employee_08- Step 01: ");

		log.info("Employee_08- Step 01: ");

	}

	@Test
	public void Employee_09_Edit_View_Tax() {
		log.info("Employee_09- Step 01: ");

		log.info("Employee_09- Step 02: ");

		log.info("Employee_09- Step 03: ");

		log.info("Employee_09- Step 01: ");

		log.info("Employee_09- Step 01: ");

	}

	@Test
	public void Employee_10_Qualifications() {
		log.info("Employee_10- Step 01: ");

		log.info("Employee_10- Step 02: ");

		log.info("Employee_10- Step 03: ");

		log.info("Employee_10- Step 01: ");

		log.info("Employee_10- Step 01: ");

	}

	@Test
	public void Employee_11_Search_Employee() {
		log.info("Employee_11- Step 01: ");

		log.info("Employee_11- Step 02: ");

		log.info("Employee_11- Step 01: ");

		log.info("Employee_11- Step 01: ");

		log.info("Employee_11- Step 01: ");

	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
