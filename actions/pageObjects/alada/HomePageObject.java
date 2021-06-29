package pageObjects.alada;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.alada.HomePageUI;

public class HomePageObject extends BasePage{
	private WebDriver driver;

	public HomePageObject(WebDriver _driver) {
		this.driver = _driver;
	}

	public boolean isMyCourseDisplayed() {
		waitForElementVisible(driver, HomePageUI.MY_COURSE_LINK);
		return isElementDisplayed(driver, HomePageUI.MY_COURSE_LINK);
	}
	
}
