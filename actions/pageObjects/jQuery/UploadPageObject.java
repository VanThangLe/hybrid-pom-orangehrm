package pageObjects.jQuery;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.jQuery.UploadPageUI;

public class UploadPageObject extends BasePage {
	WebDriver driver;
	
	public UploadPageObject(WebDriver _driver) {
		this.driver = _driver;
	}

	public void uploadFiles(String... fileNames) {
		uploadMultipleFiles(driver, UploadPageUI.UPLOAD_FILE, fileNames);
	}

	public boolean isFileNameLoadedSuccess(String fileName) {
		waitForElementVisible(driver, UploadPageUI.FILE_NAME_LOADED_TEXT, fileName);
		return isElementDisplayed(driver, UploadPageUI.FILE_NAME_LOADED_TEXT, fileName);
	}

	public void clickToStartButtonByFileName(String fileName) {
		waitForElementClickAble(driver, UploadPageUI.START_BUTTON_BY_FILE_NAME, fileName);
		clickToElement(driver, UploadPageUI.START_BUTTON_BY_FILE_NAME, fileName);
		sleepInSecond(2);
	}

	public boolean isFileUploadedSuccess(String fileName) {
		waitForElementVisible(driver, UploadPageUI.FILE_NAME_UPLOADED_TEXT, fileName);
		return isElementDisplayed(driver, UploadPageUI.FILE_NAME_UPLOADED_TEXT, fileName);
	}
}
