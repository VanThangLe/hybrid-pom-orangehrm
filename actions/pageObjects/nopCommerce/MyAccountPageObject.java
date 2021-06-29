package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.MyAccountPageUI;

public class MyAccountPageObject extends BasePage {
	private WebDriver driver;
	
	public MyAccountPageObject(WebDriver _driver) {
		this.driver = _driver;
	}

	public boolean isGenderMaleRadioSelected() {
		waitForElementVisible(driver, MyAccountPageUI.GENDER_MALE_RADIO);
		return isElementSelected(driver, MyAccountPageUI.GENDER_MALE_RADIO);
	}

	public String getFirstNameTextboxValue() {
		waitForElementVisible(driver, MyAccountPageUI.FIRSTNAME_TEXTBOX);
		return getAttributeValue(driver, MyAccountPageUI.FIRSTNAME_TEXTBOX, "value");
	}

	public String getLastNameTextboxValue() {
		waitForElementVisible(driver, MyAccountPageUI.LASTNAME_TEXTBOX);
		return getAttributeValue(driver, MyAccountPageUI.LASTNAME_TEXTBOX, "value");
	}

	public String getCompanyTextboxValue() {
		waitForElementVisible(driver, MyAccountPageUI.COMPANY_TEXTBOX);
		return getAttributeValue(driver, MyAccountPageUI.COMPANY_TEXTBOX, "value");
	}

	public String getEmailTextboxValue() {
		waitForElementVisible(driver, MyAccountPageUI.EMAIL_TEXTBOX);
		return getAttributeValue(driver, MyAccountPageUI.EMAIL_TEXTBOX, "value");
	}

	public String getDayDropdownValue() {
		waitForElementVisible(driver, MyAccountPageUI.DAY_DROPDOWN);
		return getFirstSelectedItemInDefaultDropdown(driver, MyAccountPageUI.DAY_DROPDOWN);
	}

	public String getMonthDropdownValue() {
		waitForElementVisible(driver, MyAccountPageUI.MONTH_DROPDOWN);
		return getFirstSelectedItemInDefaultDropdown(driver, MyAccountPageUI.MONTH_DROPDOWN);
	}

	public String getYearDropdownValue() {
		waitForElementVisible(driver, MyAccountPageUI.YEAR_DROPDOWN);
		return getFirstSelectedItemInDefaultDropdown(driver, MyAccountPageUI.YEAR_DROPDOWN);
	}

}
