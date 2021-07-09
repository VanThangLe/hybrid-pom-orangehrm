package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class DownloadableProductsPageObject extends BasePage{
	WebDriver driver;
	
	public DownloadableProductsPageObject(WebDriver _driver) {
		this.driver = _driver;
	}
}
