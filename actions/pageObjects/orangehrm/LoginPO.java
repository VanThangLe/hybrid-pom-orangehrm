package pageObjects.orangehrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class LoginPO extends BasePage {
	private WebDriver driver;

	public LoginPO(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public DashboardPO loginToSystem(String userName, String password) {
		
		return null;
	}
}
