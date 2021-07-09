package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class MyProductReviewsPageObject extends BasePage{
	WebDriver driver;
	
	public MyProductReviewsPageObject(WebDriver _driver) {
		this.driver = _driver;
	}
}
