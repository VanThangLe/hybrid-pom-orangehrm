package pageObjects.alada;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.alada.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;

	public LoginPageObject(WebDriver _driver) {
		this.driver = _driver;
	}

	public void enterToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public void clickLoginButton() {
		waitForElementClickAble(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}
	
	public void loginToSystem(String emailAddress, String password) {
		enterToEmailTextbox(emailAddress);
		enterToPasswordTextbox(password);
		clickLoginButton();
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getErrorMessageAtPasswordTextbox() {
		waitForElementVisible(driver, LoginPageUI.LOGIN_FORM_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.LOGIN_FORM_ERROR_MESSAGE);
	}

	public String getErrorMessageAtLoginForm() {
		waitForElementVisible(driver, LoginPageUI.LOGIN_FORM_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.LOGIN_FORM_ERROR_MESSAGE);
	}
	
}
