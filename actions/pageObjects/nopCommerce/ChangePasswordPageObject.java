package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class ChangePasswordPageObject extends BasePage{
	WebDriver driver;
	
	public ChangePasswordPageObject(WebDriver _driver) {
		this.driver = _driver;
	}
}
