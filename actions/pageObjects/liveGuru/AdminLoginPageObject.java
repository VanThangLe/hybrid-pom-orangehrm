package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {
	WebDriver driver;
	
	public AdminLoginPageObject(WebDriver _driver) {
		this.driver = _driver;
	}

	public void enterToUsernameTextbox(String user) {
		waitForElementVisible(driver, AdminLoginPageUI.USERNAME_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.USERNAME_TEXTBOX, user);
	}

	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public AdminManageCustomerPageObject clickToLoginButton() {
		waitForElementClickAble(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return PageGenerator.getAdminManageCustomerPage(driver);
	}

}
