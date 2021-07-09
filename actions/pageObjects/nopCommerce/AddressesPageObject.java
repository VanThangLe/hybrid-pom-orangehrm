package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class AddressesPageObject extends BasePage{
	WebDriver driver;
	
	public AddressesPageObject(WebDriver _driver) {
		this.driver = _driver;
	}
}
