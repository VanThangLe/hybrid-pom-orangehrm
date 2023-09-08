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
}
