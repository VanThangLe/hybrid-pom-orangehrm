package pageObjects.orangehrm;

import org.openqa.selenium.WebDriver;

public class PageGenerator {
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static AddEmployeePageObject getAddEmployeePage(WebDriver driver) {
		return new AddEmployeePageObject(driver);
	}
	
	public static DashboardPageObject getDashboardPage(WebDriver driver) {
		return new DashboardPageObject(driver);
	}
	
	public static EmployeeListPageObject getEmployeeListPage(WebDriver driver) {
		return new EmployeeListPageObject(driver);
	}
	
	public static PersonalDetailsPageObject getPersonalDetailsPage(WebDriver driver) {
		return new PersonalDetailsPageObject(driver);
	}
	
	public static ContactDetailsPageObject getContactDetailsPage(WebDriver driver) {
		return new ContactDetailsPageObject(driver);
	}
	
	public static DependentsPageObject getDependentsPage(WebDriver driver) {
		return new DependentsPageObject(driver);
	}
	
	public static EmergencyContactsPageObject getEmergencyContactsPage(WebDriver driver) {
		return new EmergencyContactsPageObject(driver);
	}
	
	public static JobPageObject getJobPage(WebDriver driver) {
		return new JobPageObject(driver);
	}
	
	public static MembershipsPageObject getMembershipsPage(WebDriver driver) {
		return new MembershipsPageObject(driver);
	}
	
	public static QualificationsPageObject getQualificationsPage(WebDriver driver) {
		return new QualificationsPageObject(driver);
	}
	
	public static ReportToPageObject getReportToPage(WebDriver driver) {
		return new ReportToPageObject(driver);
	}
	
	public static SalaryPageObject getSalaryPage(WebDriver driver) {
		return new SalaryPageObject(driver);
	}
	
	public static TaxExemptionsPageObject getTaxExemptionsPage(WebDriver driver) {
		return new TaxExemptionsPageObject(driver);
	}
}
