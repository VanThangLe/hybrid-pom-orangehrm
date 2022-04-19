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
	String editEmpFirstName, editEmpLastName, editEmpGender, editEmpMaritalStatus, editEmpNationality;
	String editEmpContactStreet1, editEmpContactStreet2, editEmpCity, editEmpProvince, editEmpZipCode, editEmpCountry, editEmpTelephone, editEmpMobile, editEmpWorkTelephone, editEmpWorkEmail, editEmpOthEmail;
	String empEmgContactsName, empEmgContactsRelationship, empEmgContactsHomeTelephone, empEmgContactsMobile, empEmgContactsWorkTelephone;
	String empDependentName, empDependentRelationship, empDependentDoB;
	String avatarFilePath = GlobalConstants.UPLOAD_FOLDER_PATH + "a.jpg";

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-condition: Step 01 - Open browser '" + browserName + "'and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGenerator.getLoginPage(driver);

		//Employee_01_Add_New_Employee
		statusValue = "Enabled";
		empFirstName = "Thang";
		empLastName = "Le";
		empFullName = empFirstName + " " + empLastName;
		empUserName = "thanglevan";
		empPassword = "12345678";
		
		//Employee_03_Personal_Details
		editEmpFirstName = "VanThang";
		editEmpLastName = "Le";
		editEmpGender = "Male";
		editEmpMaritalStatus = "Married";
		editEmpNationality = "Vietnamese";
		
		//Employee_04_Contact_Details
		editEmpContactStreet1 = "Duy Tan";
		editEmpContactStreet2 = "Xuan Thuy";
		editEmpCity = "Hanoi";
		editEmpProvince = "Hanoi";
		editEmpZipCode = "10000";
		editEmpCountry = "Vietnam";
		editEmpTelephone = "0973956843";
		editEmpMobile = "0973956843";
		editEmpWorkTelephone = "0973956843";
		editEmpWorkEmail = "thang.le.fc@gmail.com";
		editEmpOthEmail = "thanglv@a89.com.vn";
		
		//Employee_05_Emergency_Details
		empEmgContactsName = "Huyen";
		empEmgContactsRelationship = "Wife";
		empEmgContactsHomeTelephone = "0973956843";
		empEmgContactsMobile = "0973956843";
		empEmgContactsWorkTelephone = "0973956843";
		
		//Employee_06_Assigned_Dependents
		empDependentName = "Dang";
		empDependentRelationship = "Child";
		empDependentDoB = "2022-09-13";
		
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
		log.info("Employee_03 - Step 01: Open 'Personal Details' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Personal Details");
		
		log.info("Employee_03 - Step 02: Verify all fields at 'Personal Details' form are disabled");
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtEmpFirstName"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtEmpLastName"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtEmployeeId"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_optGender_1"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_optGender_2"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_cmbMarital"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_cmbNation"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_DOB"));
		
		log.info("Employee_03 - Step 03: Click to 'Edit' button at 'Personal Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");
		
		log.info("Employee_03 - Step 04: Verify 'Employee Id' textbox is disabled");
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtEmployeeId"));
		
		log.info("Employee_03 - Step 05: Verify 'Driver's License Number' textbox is disabled");
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtLicenNo"));
		
		log.info("Employee_03 - Step 06: Verify 'Date of Birth' textbox is disabled");
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_DOB"));
		
		log.info("Employee_03 - Step 07: Enter new value to 'First Name' textbox");
		myInfoPage.enterToTextboxByID(driver, "personal_txtEmpFirstName", editEmpFirstName);
		
		log.info("Employee_03 - Step 08: Enter new value to 'Last Name' textbox");
		myInfoPage.enterToTextboxByID(driver, "personal_txtEmpLastName", editEmpLastName);
		
		log.info("Employee_03 - Step 09: Enter new value to 'Gender' textbox");
		myInfoPage.clickToRadioByLabel(driver, editEmpGender);
		
		log.info("Employee_03 - Step 10: Select new value to 'Marital Single' radio button");
		myInfoPage.selectItemInDropdownByID(driver, "personal_cmbMarital", editEmpMaritalStatus);
		
		log.info("Employee_03 - Step 11: Select new value to 'Nationality' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "personal_cmbNation", editEmpNationality);
		
		log.info("Employee_03 - Step 12: Click to 'Save' button at 'Personal Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Employee_03 - Step 13: Verify Succcess message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Employee_03 - Step 14: Verify 'First Name' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpFirstName"), editEmpFirstName);
		
		log.info("Employee_03 - Step 15: Verify 'Last Name' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpLastName"), editEmpLastName);
		
		log.info("Employee_03 - Step 16: Verify 'Gender' radio button is updated success");
		verifyTrue(myInfoPage.isRadioButtonSelectedByLabel(driver, editEmpGender));
		
		log.info("Employee_03 - Step 17: Verify 'Marital Single' dropdown is updated success");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "personal_cmbMarital"), editEmpMaritalStatus);
		
		log.info("Employee_03 - Step 18: Verify 'Nationality' dropdown is updated success");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "personal_cmbNation"), editEmpNationality);
		
		log.info("Employee_03 - Step 19: Verify 'Empployee Id' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmployeeId"), employeeID);
	}

	@Test
	public void Employee_04_Contact_Details() {
		log.info("Employee_04 - Step 01: Open 'Contact Details' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Contact Details");
		
		log.info("Employee_04 - Step 02: Verify all fields at 'Contact Details' form are disabled");
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_street1"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_street2"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_city"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_province"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_emp_zipcode"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_country"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_emp_hm_telephone"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_emp_mobile"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_emp_work_telephone"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_emp_work_email"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_emp_oth_email"));
		
		log.info("Employee_04 - Step 03: Click to 'Edit' button at 'Contact Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Employee_04 - Step 04: Enter new value to 'Address Street 1' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_street1", editEmpContactStreet1);

		log.info("Employee_04 - Step 05: Enter new value to 'Address Street 2' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_street2", editEmpContactStreet2);
		
		log.info("Employee_04 - Step 06: Enter new value to 'City' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_city", editEmpCity);
		
		log.info("Employee_04 - Step 07: Enter new value to 'State/Province' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_province", editEmpProvince);
		
		log.info("Employee_04 - Step 08: Enter new value to 'Zip/Postal Code' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_zipcode", editEmpZipCode);
		
		log.info("Employee_04 - Step 09: Select new value to 'Country' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "contact_country", editEmpCountry);
		
		log.info("Employee_04 - Step 10: Enter new value to 'Home Telephone' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_hm_telephone", editEmpTelephone);
		
		log.info("Employee_04 - Step 11: Enter new value to 'Mobile' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_mobile", editEmpMobile);
		
		log.info("Employee_04 - Step 12: Enter new value to 'Work Telephone' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_work_telephone", editEmpWorkTelephone);
		
		log.info("Employee_04 - Step 13: Enter new value to 'Work Email' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_work_email", editEmpWorkEmail);
		
		log.info("Employee_04 - Step 14: Enter new value to 'Other Email' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_oth_email", editEmpOthEmail);
		
		log.info("Employee_04 - Step 15: Verify 'Address Street 1' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_street1"), editEmpContactStreet1);
		
		log.info("Employee_04 - Step 16: Verify 'Address Street 2' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_street2"), editEmpContactStreet2);
		
		log.info("Employee_04 - Step 17: Verify 'City' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_city"), editEmpCity);
		
		log.info("Employee_04 - Step 18: Verify 'State/Province' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_province"), editEmpProvince);
		
		log.info("Employee_04 - Step 19: Verify 'Zip/Postal Code' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_zipcode"), editEmpZipCode);
		
		log.info("Employee_04 - Step 20: Verify 'Country' dropdown value is correct");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "contact_country"), editEmpCountry);
		
		log.info("Employee_04 - Step 21: Verify 'Home Telephone' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_hm_telephone"), editEmpTelephone);
		
		log.info("Employee_04 - Step 22: Verify 'Mobile' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_mobile"), editEmpMobile);
		
		log.info("Employee_04 - Step 23: Verify 'Work Telephone' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_work_telephone"), editEmpWorkTelephone);
		
		log.info("Employee_04 - Step 24: Verify 'Work Email' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_work_email"), editEmpWorkEmail);
		
		log.info("Employee_04 - Step 25: Verify 'Other Email' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_oth_email"), editEmpOthEmail);
	}

	@Test
	public void Employee_05_Emergency_Contacts() {
		log.info("Employee_05 - Step 01: Open 'Emergency Contacts' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Emergency Contacts");

		log.info("Employee_05 - Step 02: Click to 'Add' button at 'Assigned Emergency Contacts' form");
		myInfoPage.clickToButtonByID(driver, "btnAddContact");

		log.info("Employee_05 - Step 03: Enter new value to 'Name' textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_name", empEmgContactsName);

		log.info("Employee_05 - Step 04: Enter new value to 'Relationship' textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_relationship", empEmgContactsRelationship);

		log.info("Employee_05 - Step 05: Enter new value to 'Home Telephone' textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_homePhone", empEmgContactsHomeTelephone);
		
		log.info("Employee_05 - Step 06: Enter new value to 'Mobile' textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_mobilePhone", empEmgContactsMobile);
		
		log.info("Employee_05 - Step 07: Enter new value to 'Work Telephone' textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_workPhone", empEmgContactsWorkTelephone);
		
		log.info("Employee_05 - Step 08: Click to 'Save' button");
		myInfoPage.clickToButtonByID(driver, "btnSaveEContact");
		
		log.info("Employee_05 - Step 09: Verify 'Name' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "emgcontacts_name"), empEmgContactsName);
		
		log.info("Employee_05 - Step 10: Verify 'Relationship' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "emgcontacts_relationship"), empEmgContactsRelationship);
		
		log.info("Employee_05 - Step 11: Verify 'Home Telephone' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "emgcontacts_homePhone"), empEmgContactsHomeTelephone);
		
		log.info("Employee_05 - Step 12: Verify 'Mobile' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "emgcontacts_mobilePhone"), empEmgContactsMobile);
		
		log.info("Employee_05 - Step 13: Verify 'Work Telephone' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "emgcontacts_workPhone"), empEmgContactsWorkTelephone);

	}

	@Test
	public void Employee_06_Assigned_Dependents() {
		log.info("Employee_06 - Step 01: Open 'Dependents' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Dependents");

		log.info("Employee_06 - Step 02: Click to 'Add' button at 'Assigned Dependents' form");
		myInfoPage.clickToButtonByID(driver, "btnAddDependent");

		log.info("Employee_06 - Step 03: Enter new value to 'Name' textbox");
		myInfoPage.enterToTextboxByID(driver, "dependent_name", empEmgContactsName);
		
		log.info("Employee_06 - Step 04: Select new value to 'Relationship' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "dependent_relationshipType", empDependentRelationship);
		
		log.info("Employee_06 - Step 05: Select new value to 'Date of Birth' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "dependent_dateOfBirth", empDependentDoB);
		
		

	}

	@Test
	public void Employee_07_Edit_View_Job() {
		log.info("Employee_07 - Step 01: ");

		log.info("Employee_07 - Step 02: ");

		log.info("Employee_07 - Step 03: ");

		log.info("Employee_07 - Step 01: ");

		log.info("Employee_07 - Step 01: ");

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
