package pageObjects.jQuery;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.jQuery.HomePageUI;

public class HomePageObject extends BasePage {
	WebDriver driver;
	
	public HomePageObject(WebDriver _driver) {
		this.driver = _driver;
	}

	public void openPagingPageByName(String pageName) {
		waitForElementClickAble(driver, HomePageUI.PAGING_LINK_BY_NUMBER, pageName);
		clickToElement(driver, HomePageUI.PAGING_LINK_BY_NUMBER, pageName);
	}

	public boolean isPageActiveByName(String pageName) {
		waitForElementVisible(driver, HomePageUI.PAGING_LINK_ACTIVE_BY_NUMBER, pageName);
		return isElementDisplayed(driver, HomePageUI.PAGING_LINK_ACTIVE_BY_NUMBER, pageName);
	}

	public void enterToHeaderTextboxByName(String headerName, String value) {
		waitForElementVisible(driver, HomePageUI.HEARDER_TEXTBOX_BY_NAME, headerName);
		sendkeyToElement(driver, HomePageUI.HEARDER_TEXTBOX_BY_NAME, value, headerName);
		sendkeyBoardToElement(driver, HomePageUI.HEARDER_TEXTBOX_BY_NAME, Keys.ENTER, headerName);
	}

	public boolean isRowValuesDisplayed(String female, String country, String male, String total) {
		waitForElementVisible(driver, HomePageUI.ROW_BY_ALL_VALUES, female, country, male, total);
		return isElementDisplayed(driver, HomePageUI.ROW_BY_ALL_VALUES, female, country, male, total);
	}

	public void clickToRowActionByCountry(String country, String actionName) {
		waitForElementClickAble(driver, HomePageUI.ROW_ACTION_BY_COUNTRY_AND_NAME, country, actionName);
		clickToElement(driver, HomePageUI.ROW_ACTION_BY_COUNTRY_AND_NAME, country, actionName);
	}
}
