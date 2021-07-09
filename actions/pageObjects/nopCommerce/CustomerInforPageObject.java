package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.CustomerInfoPageUI;

public class CustomerInforPageObject extends BasePage {
	private WebDriver driver;
	
	public CustomerInforPageObject(WebDriver _driver) {
		this.driver = _driver;
	}

	public boolean isGenderMaleRadioSelected() {
		waitForElementVisible(driver, CustomerInfoPageUI.GENDER_MALE_RADIO);
		return isElementSelected(driver, CustomerInfoPageUI.GENDER_MALE_RADIO);
	}

	public String getFirstNameTextboxValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.FIRSTNAME_TEXTBOX);
		return getAttributeValue(driver, CustomerInfoPageUI.FIRSTNAME_TEXTBOX, "value");
	}

	public String getLastNameTextboxValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.LASTNAME_TEXTBOX);
		return getAttributeValue(driver, CustomerInfoPageUI.LASTNAME_TEXTBOX, "value");
	}

	public String getCompanyTextboxValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.COMPANY_TEXTBOX);
		return getAttributeValue(driver, CustomerInfoPageUI.COMPANY_TEXTBOX, "value");
	}

	public String getEmailTextboxValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.EMAIL_TEXTBOX);
		return getAttributeValue(driver, CustomerInfoPageUI.EMAIL_TEXTBOX, "value");
	}

	public String getDayDropdownValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.DAY_DROPDOWN);
		return getFirstSelectedItemInDefaultDropdown(driver, CustomerInfoPageUI.DAY_DROPDOWN);
	}

	public String getMonthDropdownValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.MONTH_DROPDOWN);
		return getFirstSelectedItemInDefaultDropdown(driver, CustomerInfoPageUI.MONTH_DROPDOWN);
	}

	public String getYearDropdownValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.YEAR_DROPDOWN);
		return getFirstSelectedItemInDefaultDropdown(driver, CustomerInfoPageUI.YEAR_DROPDOWN);
	}
}
