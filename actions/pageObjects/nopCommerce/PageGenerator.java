package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

public class PageGenerator {
	//Quản ký việc khởi tạo các page object class
	//Mỗi 1 page chỉ có 1 hàm để gọi khởi tạo
	//Tránh việc new ở nhiều chỗ khác nhau
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static MyAccountPageObject getMyAccountPage(WebDriver driver) {
		return new MyAccountPageObject(driver);
	}
}
