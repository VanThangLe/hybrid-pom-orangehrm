package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.AdminManageCustomerPageUI;

public class AdminManageCustomerPageObject extends BasePage {
	WebDriver driver;
	
	public AdminManageCustomerPageObject(WebDriver _driver) {
		this.driver = _driver;
	}

	public void enterToTextboxAtColumnName(String columnName, String textboxValue) {
		waitForElementVisible(driver, AdminManageCustomerPageUI.COLUMN_NAME_POSITION, columnName);
		String columnNamePosition = String.valueOf(getElementSize(driver, AdminManageCustomerPageUI.COLUMN_NAME_POSITION, columnName) + 1);
		waitForElementVisible(driver, AdminManageCustomerPageUI.TEXTBOX_BY_COLUMN_POSITION, columnNamePosition);
		sendkeyToElement(driver, AdminManageCustomerPageUI.TEXTBOX_BY_COLUMN_POSITION, textboxValue, columnNamePosition);
	}

	public void clickSearchButton() {
		waitForElementVisible(driver, AdminManageCustomerPageUI.SEARCH_BUTTON);
		clickToElement(driver, AdminManageCustomerPageUI.SEARCH_BUTTON);
	}

	public boolean isLoadingIconDisappear() {
		waitForElementInvisible(driver, AdminManageCustomerPageUI.LOADING_ICON);
		boolean status = isElementDisplayed(driver, AdminManageCustomerPageUI.LOADING_ICON);
		return status;
	}

	public boolean isUserInforDisplayedInTable(String fullName, String email) {
		waitForElementVisible(driver, AdminManageCustomerPageUI.FULLNAME_AND_EMAIL_IN_TABLE, fullName, email);
		return isElementDisplayed(driver, AdminManageCustomerPageUI.FULLNAME_AND_EMAIL_IN_TABLE, fullName, email);
	}

	public void closePopupWindow() {
		waitForElementVisible(driver, AdminManageCustomerPageUI.POPUP_WINDOW_CLOSE_BUTTON);
		clickToElement(driver, AdminManageCustomerPageUI.POPUP_WINDOW_CLOSE_BUTTON);
	}

}
