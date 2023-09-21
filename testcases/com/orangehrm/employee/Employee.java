package com.orangehrm.employee;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orangehrm.data.Data;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.orangehrm.*;

public class Employee extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	AddEmployeePageObject addEmployeePage;
	DashboardPageObject dashboardPage;
	EmployeeListPageObject employeeListPage;
	PersonalDetailsPageObject personalDetailsPage;
	ContactDetailsPageObject contactDetailsPage;
	DependentsPageObject dependentsPage;
	EmergencyContactsPageObject emergencyContactsPage;
	ImmigrationPageObject immigrationPage;
	JobPageObject jobPage;
	MembershipsPageObject membershipPage;
	QualificationsPageObject qualificationsPage;
	ReportToPageObject reportToPage;
	SalaryPageObject salaryPage;
	TaxExemptionsPageObject taxExemptionsPage;

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
		dashboardPage.openMenuByPageName(driver, "PIM");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Employee_01 - Step 02: Click to add employee");
		employeeListPage.clickToButtonByLabel(driver, "Add");
		addEmployeePage = PageGenerator.getAddEmployeePage(driver);

		log.info("Employee_01 - Step 03: Input data to all fields");
		addEmployeePage.uploadAvatar(driver, Data.Employee_01_Add_New_Employee.EMP_AVATAR);
		addEmployeePage.enterToTextboxByIDName(driver, "firstName", Data.Employee_01_Add_New_Employee.EMP_FIRSTNAME);
		addEmployeePage.enterToTextboxByIDName(driver, "middleName", Data.Employee_01_Add_New_Employee.EMP_MIDDLENAME);
		addEmployeePage.enterToTextboxByIDName(driver, "lastName", Data.Employee_01_Add_New_Employee.EMP_LASTNAME);
		addEmployeePage.enterToTextboxByLabel(driver, "Employee Id", Data.Employee_01_Add_New_Employee.EMP_ID);
		addEmployeePage.clickToSwitchCheckbox(driver);
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
		log.info("Employee_02 - Step 01: Search created employee by employee id");
		personalDetailsPage.openMenuByPageName(driver, "PIM");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		employeeListPage.enterToTextboxByLabel(driver, "Employee Id", Data.Employee_01_Add_New_Employee.EMP_ID);
		
		log.info("Employee_02 - Step 02: Click to search employee");
		employeeListPage.clickToButtonByLabel(driver, "Search");
		
		log.info("Employee_02 - Step 03: Verify result after searching");
		verifyEquals(employeeListPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "2"), Data.Employee_01_Add_New_Employee.EMP_ID);
		verifyEquals(employeeListPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "3"), Data.Employee_01_Add_New_Employee.EMP_FIRSTNAME + Data.Employee_01_Add_New_Employee.EMP_MIDDLENAME);
		verifyEquals(employeeListPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "4"), Data.Employee_01_Add_New_Employee.EMP_LASTNAME);
		
		log.info("Employee_02 - Step 04: Click to edit personal details");
		employeeListPage.clickToIconActionInList(driver, "oxd-icon bi-pencil-fill");
		personalDetailsPage = PageGenerator.getPersonalDetailsPage(driver);
		
		log.info("Employee_02 - Step 05: Update personal details");
		personalDetailsPage.enterToTextboxByLabel(driver, "Driver's License Number", Data.Employee_02_Personal_Details.EMP_DRIVER_LICENSE_NUMBER);
		personalDetailsPage.enterToTextboxByLabel(driver, "License Expiry Date", Data.Employee_02_Personal_Details.EMP_LICENSE_EXPIRY_DATE);
		personalDetailsPage.enterToTextboxByLabel(driver, "SSN Number", Data.Employee_02_Personal_Details.EMP_SSN_NUMBER);
		personalDetailsPage.enterToTextboxByLabel(driver, "SIN Number", Data.Employee_02_Personal_Details.EMP_SIN_NUMBER);
		personalDetailsPage.selectValueInCustomDropdownByLabel(driver, "Marital Status", Data.Employee_02_Personal_Details.EMP_MARITAL_STATUS);
		personalDetailsPage.selectValueInCustomDropdownByLabel(driver, "Nationality", Data.Employee_02_Personal_Details.EMP_NATIONALITY);
		personalDetailsPage.enterToTextboxByLabel(driver, "Date of Birth", Data.Employee_02_Personal_Details.EMP_DATE_OF_BIRTH);
		personalDetailsPage.clickToRadioByLabel(driver, "Male");
		personalDetailsPage.enterToTextboxByLabel(driver, "Military Service", Data.Employee_02_Personal_Details.EMP_MILITARY_SERVICE);
		personalDetailsPage.clickToCheckboxByLabel(driver, "Yes");
		personalDetailsPage.selectValueInCustomDropdownByLabel(driver, "Blood Type", Data.Employee_02_Personal_Details.EMP_BLOOD_TYPE);
		
		log.info("Employee_02 - Step 06: Click to save data");
		personalDetailsPage.clickToFirstSaveButton();
		personalDetailsPage.clickToSecondSaveButton();
		
		log.info("Employee_02 - Step 07: Verify message success and data of personal details");
		verifyTrue(personalDetailsPage.isSuccessToastMessageDisplayed(driver));
		verifyEquals(personalDetailsPage.getTextboxValueByLabel(driver, "Driver's License Number"), Data.Employee_02_Personal_Details.EMP_DRIVER_LICENSE_NUMBER);
		verifyEquals(personalDetailsPage.getTextboxValueByLabel(driver, "License Expiry Date"), Data.Employee_02_Personal_Details.EMP_LICENSE_EXPIRY_DATE);
		verifyEquals(personalDetailsPage.getTextboxValueByLabel(driver, "SSN Number"), Data.Employee_02_Personal_Details.EMP_SSN_NUMBER);
		verifyEquals(personalDetailsPage.getTextboxValueByLabel(driver, "SIN Number"), Data.Employee_02_Personal_Details.EMP_SIN_NUMBER);
		verifyEquals(personalDetailsPage.getTextboxValueByLabel(driver, "Date of Birth"), Data.Employee_02_Personal_Details.EMP_DATE_OF_BIRTH);
		verifyEquals(personalDetailsPage.getTextboxValueByLabel(driver, "Military Service"), Data.Employee_02_Personal_Details.EMP_MILITARY_SERVICE);
		verifyEquals(personalDetailsPage.getValueInCustomDropdownByLabel(driver, "Marital Status"), Data.Employee_02_Personal_Details.EMP_MARITAL_STATUS);
		verifyEquals(personalDetailsPage.getValueInCustomDropdownByLabel(driver, "Nationality"), Data.Employee_02_Personal_Details.EMP_NATIONALITY);
		verifyEquals(personalDetailsPage.getValueInCustomDropdownByLabel(driver, "Blood Type"), Data.Employee_02_Personal_Details.EMP_BLOOD_TYPE);
		verifyTrue(personalDetailsPage.isCheckboxButtonSelectedByLabel(driver, "Male"));
		verifyTrue(personalDetailsPage.isRadioButtonSelectedByLabel(driver, "Yes"));
	}

	@Test
	public void Employee_03_Contact_Details() {
		log.info("Employee_03 - Step 01: Search created employee");
		personalDetailsPage.openMenuByPageName(driver, "PIM");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		employeeListPage.enterToTextboxByLabel(driver, "Employee Id", Data.Employee_01_Add_New_Employee.EMP_ID);
		employeeListPage.clickToButtonByLabel(driver, "Search");
		
		log.info("Employee_03 - Step 02: Click to edit employee");
		employeeListPage.clickToIconActionInList(driver, "oxd-icon bi-pencil-fill");
		personalDetailsPage = PageGenerator.getPersonalDetailsPage(driver);
		
		log.info("Employee_03 - Step 03: Open contact details");
		personalDetailsPage.openTabMenuByPageName(driver, "Contact Details");
		contactDetailsPage = PageGenerator.getContactDetailsPage(driver);
		
		log.info("Employee_03 - Step 04: Verify landed contact details");
		verifyTrue(contactDetailsPage.isTitleFormDisplayed(driver, "Contact Details"));
		
		log.info("Employee_03 - Step 05: Update all data of contact details");
		contactDetailsPage.enterToTextboxByLabel(driver, "Street 1", Data.Employee_03_Contact_Details.EMP_STREET_1);
		contactDetailsPage.enterToTextboxByLabel(driver, "Street 2", Data.Employee_03_Contact_Details.EMP_STREET_1);
		contactDetailsPage.enterToTextboxByLabel(driver, "City", Data.Employee_03_Contact_Details.EMP_STREET_1);
		contactDetailsPage.enterToTextboxByLabel(driver, "State/Province", Data.Employee_03_Contact_Details.EMP_STREET_1);
		contactDetailsPage.enterToTextboxByLabel(driver, "Zip/Postal Code", Data.Employee_03_Contact_Details.EMP_STREET_1);
		contactDetailsPage.enterToTextboxByLabel(driver, "Home", Data.Employee_03_Contact_Details.EMP_STREET_1);
		contactDetailsPage.enterToTextboxByLabel(driver, "Mobile", Data.Employee_03_Contact_Details.EMP_STREET_1);
		contactDetailsPage.enterToTextboxByLabel(driver, "Work", Data.Employee_03_Contact_Details.EMP_STREET_1);
		contactDetailsPage.enterToTextboxByLabel(driver, "Work Email", Data.Employee_03_Contact_Details.EMP_STREET_1);
		contactDetailsPage.enterToTextboxByLabel(driver, "Other Email", Data.Employee_03_Contact_Details.EMP_STREET_1);
		contactDetailsPage.selectValueInCustomDropdownByLabel(driver, "Country", Data.Employee_03_Contact_Details.EMP_COUNTRY);
		
		log.info("Employee_03 - Step 06: Click to save data");
		contactDetailsPage.clickToButtonByLabel(driver, "Save");
		
		log.info("Employee_03 - Step 07: Verify message toast and updated data");
		verifyTrue(contactDetailsPage.isSuccessToastMessageDisplayed(driver));
		verifyEquals(contactDetailsPage.getTextboxValueByLabel(driver, "Street 1"), Data.Employee_03_Contact_Details.EMP_STREET_1);
		verifyEquals(contactDetailsPage.getTextboxValueByLabel(driver, "Street 2"), Data.Employee_03_Contact_Details.EMP_STREET_2);
		verifyEquals(contactDetailsPage.getTextboxValueByLabel(driver, "City"), Data.Employee_03_Contact_Details.EMP_CITY);
		verifyEquals(contactDetailsPage.getTextboxValueByLabel(driver, "State/Province"), Data.Employee_03_Contact_Details.EMP_STATE_PROVINCE);
		verifyEquals(contactDetailsPage.getTextboxValueByLabel(driver, "Zip/Postal Code"), Data.Employee_03_Contact_Details.EMP_ZIP_CODE);
		verifyEquals(contactDetailsPage.getTextboxValueByLabel(driver, "Home"), Data.Employee_03_Contact_Details.EMP_TELEPHONE_HOME);
		verifyEquals(contactDetailsPage.getTextboxValueByLabel(driver, "Mobile"), Data.Employee_03_Contact_Details.EMP_TELEPHONE_MOBILE);
		verifyEquals(contactDetailsPage.getTextboxValueByLabel(driver, "Work"), Data.Employee_03_Contact_Details.EMP_TELEPHONE_WORK);
		verifyEquals(contactDetailsPage.getTextboxValueByLabel(driver, "Work Email"), Data.Employee_03_Contact_Details.EMP_WORK_EMAIL);
		verifyEquals(contactDetailsPage.getTextboxValueByLabel(driver, "Other Email"), Data.Employee_03_Contact_Details.EMP_OTHER_EMAIL);
		verifyEquals(contactDetailsPage.getValueInCustomDropdownByLabel(driver, "Country"), Data.Employee_03_Contact_Details.EMP_COUNTRY);
	}

	@Test
	public void Employee_04_Emergency_Contacts() {
		log.info("Employee_04 - Step 01: Search created employee");
		contactDetailsPage.openMenuByPageName(driver, "PIM");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		employeeListPage.enterToTextboxByLabel(driver, "Employee Id", Data.Employee_01_Add_New_Employee.EMP_ID);
		employeeListPage.clickToButtonByLabel(driver, "Search");
		
		log.info("Employee_04 - Step 02: Click to edit employee");
		employeeListPage.clickToIconActionInList(driver, "oxd-icon bi-pencil-fill");
		personalDetailsPage = PageGenerator.getPersonalDetailsPage(driver);
		
		log.info("Employee_04 - Step 03: Open emergency contacts");
		personalDetailsPage.openTabMenuByPageName(driver, "Emergency Contacts");
		emergencyContactsPage = PageGenerator.getEmergencyContactsPage(driver);
		
		log.info("Employee_04 - Step 04: Verify landed emergency contacts");
		verifyTrue(emergencyContactsPage.isTitleFormDisplayed(driver, "Assigned Emergency Contacts"));
		
		log.info("Employee_04 - Step 05: Click to add emergency contacts");
		emergencyContactsPage.clickToAddButtonByTitleForm(driver, "Assigned Emergency Contacts");
		
		log.info("Employee_04 - Step 06: Enter data for all fields");
		emergencyContactsPage.enterToTextboxByLabel(driver, "Name", Data.Employee_04_Emergency_Contacts.EMERGENCY_NAME);
		emergencyContactsPage.enterToTextboxByLabel(driver, "Relationship", Data.Employee_04_Emergency_Contacts.EMERGENCY_RELATIONSHIP);
		emergencyContactsPage.enterToTextboxByLabel(driver, "Home Telephone", Data.Employee_04_Emergency_Contacts.EMERGENCY_HOME_TELEPHONE);
		emergencyContactsPage.enterToTextboxByLabel(driver, "Mobile", Data.Employee_04_Emergency_Contacts.EMERGENCY_MOBILE);
		emergencyContactsPage.enterToTextboxByLabel(driver, "Work Telephone", Data.Employee_04_Emergency_Contacts.EMERGENCY_WORK_TELEPHONE);
		
		log.info("Employee_04 - Step 07: Click to save data");
		emergencyContactsPage.clickToButtonByLabel(driver, "Save");
		
		log.info("Employee_04 - Step 08: Verify toast message and data emergency contacts");
		verifyTrue(emergencyContactsPage.isSuccessToastMessageDisplayed(driver));
		verifyEquals(emergencyContactsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "2"), Data.Employee_04_Emergency_Contacts.EMERGENCY_NAME);
		verifyEquals(emergencyContactsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "3"), Data.Employee_04_Emergency_Contacts.EMERGENCY_RELATIONSHIP);
		verifyEquals(emergencyContactsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "4"), Data.Employee_04_Emergency_Contacts.EMERGENCY_HOME_TELEPHONE);
		verifyEquals(emergencyContactsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "5"), Data.Employee_04_Emergency_Contacts.EMERGENCY_MOBILE);
		verifyEquals(emergencyContactsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "6"), Data.Employee_04_Emergency_Contacts.EMERGENCY_WORK_TELEPHONE);
	}

	@Test
	public void Employee_05_Assigned_Dependents() {
		log.info("Employee_05 - Step 01: Search created employee");
		emergencyContactsPage.openMenuByPageName(driver, "PIM");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		employeeListPage.enterToTextboxByLabel(driver, "Employee Id", Data.Employee_01_Add_New_Employee.EMP_ID);
		employeeListPage.clickToButtonByLabel(driver, "Search");
		
		log.info("Employee_05 - Step 02: Click to edit employee");
		employeeListPage.clickToIconActionInList(driver, "oxd-icon bi-pencil-fill");
		personalDetailsPage = PageGenerator.getPersonalDetailsPage(driver);
		
		log.info("Employee_05 - Step 03: Open dependents");
		personalDetailsPage.openTabMenuByPageName(driver, "Dependents");
		dependentsPage = PageGenerator.getDependentsPage(driver);
		
		log.info("Employee_05 - Step 04: Verify landed dependents");
		verifyTrue(dependentsPage.isTitleFormDisplayed(driver, "Assigned Dependents"));
		
		log.info("Employee_05 - Step 05: Click to add dependents");
		dependentsPage.clickToAddButtonByTitleForm(driver, "Assigned Dependents");
		
		log.info("Employee_05 - Step 06: Enter data for all fields");
		dependentsPage.enterToTextboxByLabel(driver, "Name", Data.Employee_05_Assigned_Dependents.DEPENDENTS_NAME);
		dependentsPage.enterToTextboxByLabel(driver, "Relationship", Data.Employee_05_Assigned_Dependents.DEPENDENTS_RELATIONSHIP);
		dependentsPage.enterToTextboxByLabel(driver, "Date of Birth", Data.Employee_05_Assigned_Dependents.DEPENDENTS_DATE_OF_BIRTH);
		
		log.info("Employee_05 - Step 07: Click to save data");
		dependentsPage.clickToButtonByLabel(driver, "Save");
		
		log.info("Employee_05 - Step 08: Verify toast message and data dependents");
		verifyTrue(dependentsPage.isSuccessToastMessageDisplayed(driver));
		verifyEquals(dependentsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "2"), Data.Employee_05_Assigned_Dependents.DEPENDENTS_NAME);
		verifyEquals(dependentsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "3"), Data.Employee_05_Assigned_Dependents.DEPENDENTS_RELATIONSHIP);
		verifyEquals(dependentsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "4"), Data.Employee_05_Assigned_Dependents.DEPENDENTS_DATE_OF_BIRTH);
	}

	@Test
	public void Employee_06_Job() {
		log.info("Employee_06 - Step 01: Search created employee");
		dependentsPage.openMenuByPageName(driver, "PIM");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		employeeListPage.enterToTextboxByLabel(driver, "Employee Id", Data.Employee_01_Add_New_Employee.EMP_ID);
		employeeListPage.clickToButtonByLabel(driver, "Search");
		
		log.info("Employee_06 - Step 02: Click to edit employee");
		employeeListPage.clickToIconActionInList(driver, "oxd-icon bi-pencil-fill");
		personalDetailsPage = PageGenerator.getPersonalDetailsPage(driver);
		
		log.info("Employee_06 - Step 03: Open job");
		personalDetailsPage.openTabMenuByPageName(driver, "Job");
		jobPage = PageGenerator.getJobPage(driver);
		
		log.info("Employee_06 - Step 04: Verify landed job");
		verifyTrue(jobPage.isTitleFormDisplayed(driver, "Job Details"));
		
		log.info("Employee_06 - Step 05: Select filter");
		jobPage.selectValueInCustomDropdownByLabel(driver, "Joined Date", Data.Employee_06_Job.JOINED_DATE);
		jobPage.selectValueInCustomDropdownByLabel(driver, "Job Title", Data.Employee_06_Job.JOB_TITLE);
		jobPage.selectValueInCustomDropdownByLabel(driver, "Job Category", Data.Employee_06_Job.JOB_CATEGORY);
		jobPage.selectValueInCustomDropdownByLabel(driver, "Sub Unit", Data.Employee_06_Job.SUB_UNIT);
		jobPage.selectValueInCustomDropdownByLabel(driver, "Location", Data.Employee_06_Job.LOCATION);
		jobPage.selectValueInCustomDropdownByLabel(driver, "Employment Status", Data.Employee_06_Job.EMPLOYMENT_STATUS);
		jobPage.clickToSwitchCheckbox(driver);
		jobPage.selectValueInCustomDropdownByLabel(driver, "Contract Start Date", Data.Employee_06_Job.CONTRACT_START_DATE);
		jobPage.selectValueInCustomDropdownByLabel(driver, "Contract End Date", Data.Employee_06_Job.CONTRACT_END_DATE);
		
		log.info("Employee_06 - Step 06: Click save filter");
		jobPage.clickToButtonByLabel(driver, "Save");
		
		log.info("Employee_06 - Step 07: Verify filter");
		verifyTrue(jobPage.isSuccessToastMessageDisplayed(driver));
		verifyEquals(jobPage.getValueInCustomDropdownByLabel(driver, "Joined Date"), Data.Employee_06_Job.JOINED_DATE);
		verifyEquals(jobPage.getValueInCustomDropdownByLabel(driver, "Job Title"), Data.Employee_06_Job.JOB_TITLE);
		verifyEquals(jobPage.getValueInCustomDropdownByLabel(driver, "Job Category"), Data.Employee_06_Job.JOB_CATEGORY);
		verifyEquals(jobPage.getValueInCustomDropdownByLabel(driver, "Sub Unit"), Data.Employee_06_Job.SUB_UNIT);
		verifyEquals(jobPage.getValueInCustomDropdownByLabel(driver, "Location"), Data.Employee_06_Job.LOCATION);
		verifyEquals(jobPage.getValueInCustomDropdownByLabel(driver, "Employment Status"), Data.Employee_06_Job.EMPLOYMENT_STATUS);
		verifyEquals(jobPage.getValueInCustomDropdownByLabel(driver, "Contract Start Date"), Data.Employee_06_Job.CONTRACT_START_DATE);
		verifyEquals(jobPage.getValueInCustomDropdownByLabel(driver, "Contract End Date"), Data.Employee_06_Job.CONTRACT_END_DATE);
	}

	@Test
	public void Employee_07_Salary() {
		log.info("Employee_07 - Step 01: Search created employee");
		jobPage.openMenuByPageName(driver, "PIM");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		employeeListPage.enterToTextboxByLabel(driver, "Employee Id", Data.Employee_01_Add_New_Employee.EMP_ID);
		employeeListPage.clickToButtonByLabel(driver, "Search");
		
		log.info("Employee_07 - Step 02: Click to edit employee");
		employeeListPage.clickToIconActionInList(driver, "oxd-icon bi-pencil-fill");
		personalDetailsPage = PageGenerator.getPersonalDetailsPage(driver);
		
		log.info("Employee_07 - Step 03: Open salary");
		personalDetailsPage.openTabMenuByPageName(driver, "Salary");
		salaryPage = PageGenerator.getSalaryPage(driver);
		
		log.info("Employee_07 - Step 04: Verify landed salary");
		verifyTrue(salaryPage.isTitleFormDisplayed(driver, "Assigned Salary Components"));
		
		log.info("Employee_07 - Step 05: Click to add salary");
		salaryPage.clickToAddButtonByTitleForm(driver, "Assigned Salary Components");
		
		log.info("Employee_07 - Step 06: Enter data for all fields");
		salaryPage.enterToTextboxByLabel(driver, "Salary Component", Data.Employee_07_Salary.SALARY_COMPONENT);
		salaryPage.selectValueInCustomDropdownByLabel(driver, "Pay Frequency", Data.Employee_07_Salary.PAY_FREQUENCY);
		salaryPage.selectValueInCustomDropdownByLabel(driver, "Currency", Data.Employee_07_Salary.CURRENCY);
		salaryPage.enterToTextboxByLabel(driver, "pim.amount", Data.Employee_07_Salary.PIM_AMMOUNT);
		
		log.info("Employee_07 - Step 07: Click to save data");
		salaryPage.clickToButtonByLabel(driver, "Save");
		
		log.info("Employee_07 - Step 08: Verify data salary");
		verifyTrue(salaryPage.isSuccessToastMessageDisplayed(driver));
		verifyEquals(salaryPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "2"), Data.Employee_07_Salary.SALARY_COMPONENT);
		verifyEquals(salaryPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "3"), Data.Employee_07_Salary.PIM_AMMOUNT);
		verifyEquals(salaryPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "4"), Data.Employee_07_Salary.CURRENCY);
		verifyEquals(salaryPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "5"), Data.Employee_07_Salary.PAY_FREQUENCY);
	}

	@Test
	public void Employee_08_Tax_Exemptions() {
		log.info("Employee_08 - Step 01: Search created employee");
		salaryPage.openMenuByPageName(driver, "PIM");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		employeeListPage.enterToTextboxByLabel(driver, "Employee Id", Data.Employee_01_Add_New_Employee.EMP_ID);
		employeeListPage.clickToButtonByLabel(driver, "Search");
		
		log.info("Employee_08 - Step 02: Click to edit employee");
		employeeListPage.clickToIconActionInList(driver, "oxd-icon bi-pencil-fill");
		personalDetailsPage = PageGenerator.getPersonalDetailsPage(driver);
		
		log.info("Employee_08 - Step 03: Open tax exemptions");
		personalDetailsPage.openTabMenuByPageName(driver, "Tax Exemptions");
		taxExemptionsPage = PageGenerator.getTaxExemptionsPage(driver);
		
		log.info("Employee_08 - Step 04: Verify landed tax exemptions");
		verifyTrue(taxExemptionsPage.isTitleFormDisplayed(driver, "Tax Exemptions"));
		
		log.info("Employee_08 - Step 05: Select filter");
		taxExemptionsPage.selectStatusField("Federal Income Tax", "Status", Data.Employee_08_Tax_Exemptions.FEDERAL_TAX_STATUS);
		taxExemptionsPage.enterToExemtionsField("Federal Income Tax", "Exemptions", Data.Employee_08_Tax_Exemptions.FEDERAL_TAX_EXEMPTIONS);
		taxExemptionsPage.selectValueInCustomDropdownByLabel(driver, "State", Data.Employee_08_Tax_Exemptions.STATE_TAX_STATE);
		taxExemptionsPage.selectStatusField("State Income Tax", "Status", Data.Employee_08_Tax_Exemptions.STATE_TAX_STATUS);
		taxExemptionsPage.enterToExemtionsField("State Income Tax", "Exemptions", Data.Employee_08_Tax_Exemptions.STATE_TAX_EXEMPTIONS);
		taxExemptionsPage.selectValueInCustomDropdownByLabel(driver, "Unemployment State", Data.Employee_08_Tax_Exemptions.STATE_TAX_UNEMPLOYMENT_STATE);
		taxExemptionsPage.selectValueInCustomDropdownByLabel(driver, "Work State", Data.Employee_08_Tax_Exemptions.STATE_TAX_WORK_STATE);
		
		log.info("Employee_08 - Step 06: Click save filter");
		taxExemptionsPage.clickToButtonByLabel(driver, "Save");
		
		log.info("Employee_08 - Step 07: Verify filter");
		verifyTrue(taxExemptionsPage.isSuccessToastMessageDisplayed(driver));
		verifyEquals(taxExemptionsPage.getValueStatusField("Federal Income Tax", "Status"), Data.Employee_08_Tax_Exemptions.FEDERAL_TAX_STATUS);
		verifyEquals(taxExemptionsPage.getValueExemtionsField("Federal Income Tax", "Exemptions"), Data.Employee_08_Tax_Exemptions.FEDERAL_TAX_EXEMPTIONS);
		verifyEquals(taxExemptionsPage.getValueInCustomDropdownByLabel(driver, "State"), Data.Employee_08_Tax_Exemptions.STATE_TAX_STATE);
		verifyEquals(taxExemptionsPage.getValueStatusField("State Income Tax", "Status"), Data.Employee_08_Tax_Exemptions.STATE_TAX_STATUS);
		verifyEquals(taxExemptionsPage.getValueExemtionsField("Federal Income Tax", "Exemptions"), Data.Employee_08_Tax_Exemptions.STATE_TAX_EXEMPTIONS);
		verifyEquals(taxExemptionsPage.getValueInCustomDropdownByLabel(driver, "Unemployment State"), Data.Employee_08_Tax_Exemptions.STATE_TAX_UNEMPLOYMENT_STATE);
		verifyEquals(taxExemptionsPage.getValueInCustomDropdownByLabel(driver, "Work State"), Data.Employee_08_Tax_Exemptions.STATE_TAX_WORK_STATE);
	}
	
	@Test
	public void Employee_09_Report_To_Assigned_Supervisors() {
		log.info("Employee_09 - Step 01: Search created employee");
		taxExemptionsPage.openMenuByPageName(driver, "PIM");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		employeeListPage.enterToTextboxByLabel(driver, "Employee Id", Data.Employee_01_Add_New_Employee.EMP_ID);
		employeeListPage.clickToButtonByLabel(driver, "Search");
		
		log.info("Employee_09 - Step 02: Click to edit employee");
		employeeListPage.clickToIconActionInList(driver, "oxd-icon bi-pencil-fill");
		personalDetailsPage = PageGenerator.getPersonalDetailsPage(driver);
		
		log.info("Employee_09 - Step 03: Open report to");
		personalDetailsPage.openTabMenuByPageName(driver, "Report-to");
		reportToPage = PageGenerator.getReportToPage(driver);
		
		log.info("Employee_09 - Step 04: Verify landed report to");
		verifyTrue(reportToPage.isTitleFormDisplayed(driver, "Report to"));
		
		log.info("Employee_09 - Step 05: Click to add assigned supervisors");
		reportToPage.clickToAddButtonByTitleForm(driver, "Assigned Supervisors");
		
		log.info("Employee_09 - Step 06: Enter data for all fields");
		reportToPage.enterToTextboxByLabel(driver, "Name", Data.Employee_09_Report_To_Assigned_Supervisors.SUPERVISOR_NAME);
		reportToPage.selectValueInCustomDropdownByLabel(driver, "Reporting Method", Data.Employee_09_Report_To_Assigned_Supervisors.SUPERVISOR_REPORTING_METHOD);
		
		log.info("Employee_09 - Step 07: Click to save data");
		reportToPage.clickToButtonByLabel(driver, "Save");
		
		log.info("Employee_09 - Step 08: Verify data assigned supervisors");
		verifyTrue(reportToPage.isSuccessToastMessageDisplayed(driver));
		verifyEquals(reportToPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "2"), Data.Employee_09_Report_To_Assigned_Supervisors.SUPERVISOR_NAME);
		verifyEquals(reportToPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "3"), Data.Employee_09_Report_To_Assigned_Supervisors.SUPERVISOR_REPORTING_METHOD);
	}
	
	@Test
	public void Employee_10_Report_To_Assigned_Subordinates() {
		log.info("Employee_10 - Step 01: Click to add assigned subordinates");
		reportToPage.clickToAddButtonByTitleForm(driver, "Assigned Supervisors");
		
		log.info("Employee_10 - Step 02: Enter data for all fields");
		reportToPage.enterToTextboxByLabel(driver, "Name", Data.Employee_10_Report_To_Assigned_Subordinates.SUBORDINATES_NAME);
		reportToPage.selectValueInCustomDropdownByLabel(driver, "Reporting Method", Data.Employee_10_Report_To_Assigned_Subordinates.SUBORDINATES_REPORTING_METHOD);
		
		log.info("Employee_10 - Step 03: Click to save data");
		reportToPage.clickToButtonByLabel(driver, "Save");
		
		log.info("Employee_10 - Step 04: Verify data assigned subordinates");
		verifyTrue(reportToPage.isSuccessToastMessageDisplayed(driver));
		verifyEquals(reportToPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "2"), Data.Employee_10_Report_To_Assigned_Subordinates.SUBORDINATES_NAME);
		verifyEquals(reportToPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "3"), Data.Employee_10_Report_To_Assigned_Subordinates.SUBORDINATES_REPORTING_METHOD);
	}

	@Test
	public void Employee_11_Qualifications_Work_Experience() {
		log.info("Employee_11 - Step 01: Search created employee");
		reportToPage.openMenuByPageName(driver, "PIM");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		employeeListPage.enterToTextboxByLabel(driver, "Employee Id", Data.Employee_01_Add_New_Employee.EMP_ID);
		employeeListPage.clickToButtonByLabel(driver, "Search");
		
		log.info("Employee_11 - Step 02: Click to edit employee");
		employeeListPage.clickToIconActionInList(driver, "oxd-icon bi-pencil-fill");
		personalDetailsPage = PageGenerator.getPersonalDetailsPage(driver);
		
		log.info("Employee_11 - Step 03: Open qualifications");
		personalDetailsPage.openTabMenuByPageName(driver, "Qualifications");
		qualificationsPage = PageGenerator.getQualificationsPage(driver);
		
		log.info("Employee_11 - Step 04: Verify landed qualifications");
		verifyTrue(qualificationsPage.isTitleFormDisplayed(driver, "Qualifications"));
		
		log.info("Employee_11 - Step 05: Click to add work experience");
		qualificationsPage.clickToAddButtonByTitleForm(driver, "Work Experience");
		
		log.info("Employee_11 - Step 06: Enter data for all fields");
		qualificationsPage.enterToTextboxByLabel(driver, "Company", Data.Employee_11_Qualifications_Work_Experience.COMPANY);
		qualificationsPage.enterToTextboxByLabel(driver, "Job Title", Data.Employee_11_Qualifications_Work_Experience.JOB_TITLE);
		qualificationsPage.enterToTextboxByLabel(driver, "From", Data.Employee_11_Qualifications_Work_Experience.FROM);
		qualificationsPage.enterToTextboxByLabel(driver, "To", Data.Employee_11_Qualifications_Work_Experience.TO);
		qualificationsPage.enterToTextboxByLabel(driver, "Comment", Data.Employee_11_Qualifications_Work_Experience.COMMENT);
		
		log.info("Employee_11 - Step 07: Click to save data");
		qualificationsPage.clickToButtonByLabel(driver, "Save");
		
		log.info("Employee_11 - Step 08: Verify data work experience");
		verifyTrue(qualificationsPage.isSuccessToastMessageDisplayed(driver));
		verifyEquals(qualificationsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "2"), Data.Employee_11_Qualifications_Work_Experience.COMPANY);
		verifyEquals(qualificationsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "3"), Data.Employee_11_Qualifications_Work_Experience.JOB_TITLE);
		verifyEquals(qualificationsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "4"), Data.Employee_11_Qualifications_Work_Experience.FROM);
		verifyEquals(qualificationsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "5"), Data.Employee_11_Qualifications_Work_Experience.TO);
		verifyEquals(qualificationsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "6"), Data.Employee_11_Qualifications_Work_Experience.COMMENT);
	}
	
	@Test
	public void Employee_12_Qualifications_Education() {
		log.info("Employee_12 - Step 01: Click to add education");
		qualificationsPage.clickToAddButtonByTitleForm(driver, "Work Experience");
		
		log.info("Employee_12 - Step 02: Enter data for all fields");
		qualificationsPage.selectValueInCustomDropdownByLabel(driver, "Level", Data.Employee_12_Qualifications_Education.LEVEL);
		qualificationsPage.enterToTextboxByLabel(driver, "Year", Data.Employee_12_Qualifications_Education.YEAR);
		qualificationsPage.enterToTextboxByLabel(driver, "GPA/Score", Data.Employee_12_Qualifications_Education.GPA_SCORE);
		
		log.info("Employee_12 - Step 03: Click to save data");
		qualificationsPage.clickToButtonByLabel(driver, "Save");
		
		log.info("Employee_12 - Step 04: Verify data education");
		verifyTrue(qualificationsPage.isSuccessToastMessageDisplayed(driver));
		verifyEquals(qualificationsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "2"), Data.Employee_12_Qualifications_Education.LEVEL);
		verifyEquals(qualificationsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "3"), Data.Employee_12_Qualifications_Education.YEAR);
		verifyEquals(qualificationsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "4"), Data.Employee_12_Qualifications_Education.GPA_SCORE);
	}
	
	@Test
	public void Employee_13_Qualifications_Skill() {
		log.info("Employee_13 - Step 01: Click to add skill");
		qualificationsPage.clickToAddButtonByTitleForm(driver, "Skills");
		
		log.info("Employee_13 - Step 02: Enter data for all fields");
		qualificationsPage.enterToTextboxByLabel(driver, "Skill", Data.Employee_13_Qualifications_Skill.SKILL);
		qualificationsPage.selectValueInCustomDropdownByLabel(driver, "Years of Experience", Data.Employee_13_Qualifications_Skill.YEARS_OF_EXPERIENCE);
		
		log.info("Employee_13 - Step 03: Click to save data");
		qualificationsPage.clickToButtonByLabel(driver, "Save");
		
		log.info("Employee_13 - Step 04: Verify data skill");
		verifyTrue(qualificationsPage.isSuccessToastMessageDisplayed(driver));
		verifyEquals(qualificationsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "2"), Data.Employee_13_Qualifications_Skill.SKILL);
		verifyEquals(qualificationsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "3"), Data.Employee_13_Qualifications_Skill.YEARS_OF_EXPERIENCE);
	}
	
	@Test
	public void Employee_14_Qualifications_Languages() {
		log.info("Employee_14 - Step 01: Click to add languages");
		qualificationsPage.clickToAddButtonByTitleForm(driver, "Languages");
		
		log.info("Employee_14 - Step 02: Enter data for all fields");
		qualificationsPage.selectValueInCustomDropdownByLabel(driver, "Language", Data.Employee_14_Qualifications_Languages.LANGUAGE);
		qualificationsPage.selectValueInCustomDropdownByLabel(driver, "Fluency", Data.Employee_14_Qualifications_Languages.FLUENCY);
		qualificationsPage.selectValueInCustomDropdownByLabel(driver, "Competency", Data.Employee_14_Qualifications_Languages.COMPETENCY);
		qualificationsPage.selectValueInCustomDropdownByLabel(driver, "Comments", Data.Employee_14_Qualifications_Languages.COMMENTS);
		
		log.info("Employee_14 - Step 03: Click to save data");
		qualificationsPage.clickToButtonByLabel(driver, "Save");
		
		log.info("Employee_14 - Step 04: Verify data languages");
		verifyTrue(qualificationsPage.isSuccessToastMessageDisplayed(driver));
		verifyEquals(qualificationsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "2"), Data.Employee_14_Qualifications_Languages.LANGUAGE);
		verifyEquals(qualificationsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "3"), Data.Employee_14_Qualifications_Languages.FLUENCY);
		verifyEquals(qualificationsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "4"), Data.Employee_14_Qualifications_Languages.COMPETENCY);
		verifyEquals(qualificationsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "5"), Data.Employee_14_Qualifications_Languages.COMMENTS);
	}
	
	@Test
	public void Employee_15_Qualifications_License() {
		log.info("Employee_15 - Step 01: Click to add license");
		qualificationsPage.clickToAddButtonByTitleForm(driver, "License");
		
		log.info("Employee_15 - Step 02: Enter data for all fields");
		qualificationsPage.selectValueInCustomDropdownByLabel(driver, "License Type", Data.Employee_15_Qualifications_License.LICENSE_TYPE);
		qualificationsPage.selectValueInCustomDropdownByLabel(driver, "Issued Date", Data.Employee_15_Qualifications_License.ISSUED_DATE);
		qualificationsPage.selectValueInCustomDropdownByLabel(driver, "Expiry Date", Data.Employee_15_Qualifications_License.EXPIRY_DATE);
		
		log.info("Employee_15 - Step 03: Click to save data");
		qualificationsPage.clickToButtonByLabel(driver, "Save");
		
		log.info("Employee_15 - Step 04: Verify data license");
		verifyTrue(qualificationsPage.isSuccessToastMessageDisplayed(driver));
		verifyEquals(qualificationsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "2"), Data.Employee_15_Qualifications_License.LICENSE_TYPE);
		verifyEquals(qualificationsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "3"), Data.Employee_15_Qualifications_License.ISSUED_DATE);
		verifyEquals(qualificationsPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "4"), Data.Employee_15_Qualifications_License.EXPIRY_DATE);
	}
	
	@Test
	public void Employee_16_Memberships() {
		log.info("Employee_16 - Step 01: Search created employee");
		qualificationsPage.openMenuByPageName(driver, "PIM");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		employeeListPage.enterToTextboxByLabel(driver, "Employee Id", Data.Employee_01_Add_New_Employee.EMP_ID);
		employeeListPage.clickToButtonByLabel(driver, "Search");
		
		log.info("Employee_16 - Step 02: Click to edit employee");
		employeeListPage.clickToIconActionInList(driver, "oxd-icon bi-pencil-fill");
		personalDetailsPage = PageGenerator.getPersonalDetailsPage(driver);
		
		log.info("Employee_16 - Step 03: Open memberships");
		personalDetailsPage.openTabMenuByPageName(driver, "Memberships");
		membershipPage = PageGenerator.getMembershipsPage(driver);
		
		log.info("Employee_16 - Step 04: Verify landed memberships");
		verifyTrue(membershipPage.isTitleFormDisplayed(driver, "Memberships"));
		
		log.info("Employee_16 - Step 05: Click to add memberships");
		membershipPage.clickToAddButtonByTitleForm(driver, "Memberships");
		
		log.info("Employee_16 - Step 06: Enter data for all fields");
		membershipPage.selectValueInCustomDropdownByLabel(driver, "Membership", Data.Employee_16_Memberships.MEMBERSHIP);
		membershipPage.selectValueInCustomDropdownByLabel(driver, "Subscription Paid By", Data.Employee_16_Memberships.SUBSCRIPTION_PAID_BY);
		membershipPage.enterToTextboxByLabel(driver, "Subscription Amount", Data.Employee_16_Memberships.SUBSCRIPTION_AMOUNT);
		membershipPage.selectValueInCustomDropdownByLabel(driver, "Currency", Data.Employee_16_Memberships.CURRENCY);
		membershipPage.selectValueInCustomDropdownByLabel(driver, "Subscription Commence Date", Data.Employee_16_Memberships.SUBSCRIPTION_COMMENCE_DATE);
		membershipPage.selectValueInCustomDropdownByLabel(driver, "Subscription Renewal Date", Data.Employee_16_Memberships.SUBSCRIPTION_RENEWAL_DATE);
		
		log.info("Employee_16 - Step 07: Click to save data");
		membershipPage.clickToButtonByLabel(driver, "Save");
		
		log.info("Employee_16 - Step 08: Verify data memberships");
		verifyTrue(membershipPage.isSuccessToastMessageDisplayed(driver));
		verifyEquals(membershipPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "2"), Data.Employee_16_Memberships.MEMBERSHIP);
		verifyEquals(membershipPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "3"), Data.Employee_16_Memberships.SUBSCRIPTION_PAID_BY);
		verifyEquals(membershipPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "4"),Data.Employee_16_Memberships.SUBSCRIPTION_AMOUNT);
		verifyEquals(membershipPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "5"), Data.Employee_16_Memberships.CURRENCY);
		verifyEquals(membershipPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "6"), Data.Employee_16_Memberships.SUBSCRIPTION_COMMENCE_DATE);
		verifyEquals(membershipPage.getValueByRowBodyNumberAndColumnBodyNumber(driver, "1", "7"), Data.Employee_16_Memberships.SUBSCRIPTION_RENEWAL_DATE);
	}
	
	@Test
	public void Employee_17_Delete_Employee() {
		log.info("Employee_17 - Step 01: Search created employee");
		membershipPage.openMenuByPageName(driver, "PIM");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		employeeListPage.enterToTextboxByLabel(driver, "Employee Id", Data.Employee_01_Add_New_Employee.EMP_ID);
		employeeListPage.clickToButtonByLabel(driver, "Search");
		
		log.info("Employee_17 - Step 02: Click to delete employee");
		employeeListPage.clickToIconActionInList(driver, "oxd-icon bi-trash");
		
		log.info("Employee_17 - Step 03: Confirm delete popup");
		employeeListPage.clickToConfirmDelete(" Yes, Delete ");
		
		log.info("Employee_17 - Step 04: Verify delete success");
		verifyTrue(employeeListPage.isSuccessToastMessageDisplayed(driver));
		
		log.info("Employee_17 - Step 05: Search deleted employee");
		employeeListPage.enterToTextboxByLabel(driver, "Employee Id", Data.Employee_01_Add_New_Employee.EMP_ID);
		employeeListPage.clickToButtonByLabel(driver, "Search");
		
		log.info("Employee_17 - Step 06: Verify search deleted employee");
		verifyTrue(employeeListPage.isSuccessToastMessageDisplayed(driver));
	}

	@Parameters({ "browserName" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
