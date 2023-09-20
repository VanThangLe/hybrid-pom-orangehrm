package pageObjects.orangehrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.orangehrm.BasePageUI;

public class PersonalDetailsPageObject extends BasePage {
	private WebDriver driver;

	public PersonalDetailsPageObject(WebDriver _driver) {
		this.driver = _driver;
	}
	
	public void clickToFirstSaveButton() {
		waitForElementClickAble(driver, BasePageUI.FIRST_SAVE);
		clickToElement(driver, BasePageUI.FIRST_SAVE);
	}
	
	public void clickToSecondSaveButton() {
		waitForElementClickAble(driver, BasePageUI.SECOND_SAVE);
		clickToElement(driver, BasePageUI.SECOND_SAVE);
	}
}
