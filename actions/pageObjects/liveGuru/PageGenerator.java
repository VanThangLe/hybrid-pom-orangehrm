package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

public class PageGenerator {
	public static AdminManageCustomerPageObject getAdminManageCustomerPage(WebDriver driver) {
		return new AdminManageCustomerPageObject(driver);
	}
	
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}
	
	public static UserDashboardPageObject getUserDashboardPage(WebDriver driver) {
		return new UserDashboardPageObject(driver);
	}
	
	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}
	
	public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}
	
	public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}
	
}
