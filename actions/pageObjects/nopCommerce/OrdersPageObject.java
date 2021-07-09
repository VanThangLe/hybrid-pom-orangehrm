package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class OrdersPageObject extends BasePage{
	WebDriver driver;
	
	public OrdersPageObject(WebDriver _driver) {
		this.driver = _driver;
	}
}
