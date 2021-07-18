package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.UserHomePageUI;

public class UserHomePageObject extends BasePage {
	WebDriver driver;
	
	public UserHomePageObject(WebDriver _driver) {
		this.driver = _driver;
	}

	public UserLoginPageObject clickToMyAccountPage() {
		waitForElementClickAble(driver, UserHomePageUI.MY_ACCOUNT_LINK_AT_FOOTER);
		clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK_AT_FOOTER);
		return PageGenerator.getUserLoginPage(driver);
	}

}
