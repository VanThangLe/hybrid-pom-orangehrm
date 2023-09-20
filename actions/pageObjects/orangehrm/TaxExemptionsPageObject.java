package pageObjects.orangehrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.orangehrm.BasePageUI;

public class TaxExemptionsPageObject extends BasePage {
	private WebDriver driver;

	public TaxExemptionsPageObject(WebDriver _driver) {
		this.driver = _driver;
	}
	
	public void enterToExemtionsField(String titleForm, String label, String value) {
		waitForElementVisible(driver, BasePageUI.STATUS_EXEMTIONS_BY_LABEL, titleForm, label);
		sendkeyToElement(driver, BasePageUI.STATUS_EXEMTIONS_BY_LABEL, value, titleForm, label);
	}
	
	public String getValueExemtionsField(String titleForm, String label) {
		waitForElementVisible(driver, BasePageUI.STATUS_EXEMTIONS_BY_LABEL, titleForm, label);
		return getElementText(driver, BasePageUI.STATUS_EXEMTIONS_BY_LABEL, titleForm, label);
	}
	
	public void selectStatusField(String titleForm, String label, String value) {
		waitForElementClickAble(driver, BasePageUI.STATUS_EXEMTIONS_BY_LABEL, titleForm, label);
		selectItemInCustomDropdown(driver, BasePageUI.STATUS_EXEMTIONS_BY_LABEL, value, titleForm, label);
	}
	
	public String getValueStatusField(String titleForm, String label) {
		waitForElementVisible(driver, BasePageUI.STATUS_EXEMTIONS_BY_LABEL, titleForm, label);
		return getElementText(driver, BasePageUI.STATUS_EXEMTIONS_BY_LABEL, titleForm, label);
	}
}
