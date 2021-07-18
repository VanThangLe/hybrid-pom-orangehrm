package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
	WebDriver driver;
	
	public UserLoginPageObject(WebDriver _driver) {
		this.driver = _driver;
	}

	public UserRegisterPageObject clickCreateAnAcountBUtton() {
		waitForElementClickAble(driver, UserLoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
		clickToElement(driver, UserLoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
		return PageGenerator.getUserRegisterPage(driver);
	}

}
