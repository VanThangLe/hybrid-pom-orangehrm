package pageObjects.orangehrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.orangehrm.AddEmployeePageUI;

public class AddEmployeePageObject extends BasePage {
	private WebDriver driver;

	public AddEmployeePageObject(WebDriver _driver) {
		this.driver = _driver;
	}
	
	public void clickToCreateLoginDetails() {
		waitForElementClickAble(driver, AddEmployeePageUI.CREATE_LOGIN_DETAILS);
		clickToElement(driver, AddEmployeePageUI.CREATE_LOGIN_DETAILS);
	}
}
