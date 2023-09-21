package pageObjects.orangehrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.orangehrm.BasePageUI;

public class EmployeeListPageObject extends BasePage {
	private WebDriver driver;

	public EmployeeListPageObject(WebDriver _driver) {
		this.driver = _driver;
	}
	
	public void clickToConfirmDelete(String labelButton) {
		driver.getWindowHandle();
		waitForElementClickAble(driver, BasePageUI.BUTTON_BY_LABEL, labelButton);
		clickToElement(driver, BasePageUI.BUTTON_BY_LABEL, labelButton);
	}
}
