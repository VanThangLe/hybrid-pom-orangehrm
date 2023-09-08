package pageObjects.orangehrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.orangehrm.MyInfoPageUI;

public class MyInfoPageObject extends BasePage {
	private WebDriver driver;

	public MyInfoPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickToChangePhotoImage() {
		waitForElementClickAble(driver, MyInfoPageUI.AVATAR_IMAGE);
		clickToElement(driver, MyInfoPageUI.AVATAR_IMAGE);
	}

	public boolean isNewAvatarImageDisplayed() {
		waitForElementVisible(driver, MyInfoPageUI.AVATAR_IMAGE);
		int imageWidth = Integer.parseInt(getAttributeValue(driver, MyInfoPageUI.AVATAR_IMAGE, "width"));
		int imageHeight = Integer.parseInt(getAttributeValue(driver, MyInfoPageUI.AVATAR_IMAGE, "height"));
		return (imageWidth != 200) || (imageHeight != 200);
	}
	
	public void openTabAtSideBarByName(String tabName) {
		waitForElementClickAble(driver, MyInfoPageUI.TAB_LINK_AT_SIDEBAR, tabName);
		clickToElement(driver, MyInfoPageUI.TAB_LINK_AT_SIDEBAR, tabName);
	}
}
