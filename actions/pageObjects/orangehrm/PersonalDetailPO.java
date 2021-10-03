package pageObjects.orangehrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.orangehrm.PersonalDetailPageUI;

public class PersonalDetailPO extends BasePage {
	private WebDriver driver;

	public PersonalDetailPO(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickToChangePhotoImage() {
		waitForElementClickAble(driver, PersonalDetailPageUI.AVATAR_IMAGE);
		clickToElement(driver, PersonalDetailPageUI.AVATAR_IMAGE);
	}

	public boolean isUploadAvaterSuccessMessageDisplayed() {
		waitForElementVisible(driver, PersonalDetailPageUI.UPLOAD_AVATAR_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, PersonalDetailPageUI.UPLOAD_AVATAR_SUCCESS_MESSAGE);
	}

	public boolean isNewAvatarImageDisplayed() {
		waitForElementVisible(driver, PersonalDetailPageUI.AVATAR_IMAGE);
		int imageWidth = Integer.parseInt(getAttributeValue(driver, PersonalDetailPageUI.AVATAR_IMAGE, "width"));
		int imageHeight = Integer.parseInt(getAttributeValue(driver, PersonalDetailPageUI.AVATAR_IMAGE, "height"));
		return (imageWidth != 200) || (imageHeight != 200);
	}
}
