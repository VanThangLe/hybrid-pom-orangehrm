package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageObject extends BasePageFactory {
	WebDriverWait explicitWait;
	
	public HomePageObject(WebDriver driver) {
		super(driver);
		explicitWait = new WebDriverWait(driver, 30);
		
		//Init element
		PageFactory.initElements(driver, this);
	}
	
	//Page UI: Locator
	@CacheLookup
	@FindBy(xpath = "//a[@class='ico-register']")
	private WebElement registerLink;
	
	@CacheLookup
	@FindBy(css = "//a[@class='ico-login']")
	private WebElement loginLink;
	
	@CacheLookup
	@FindBy(css = "//a[@class='ico-account']")
	private WebElement myAccountLink;
	
	//Page Object: Action
	public void clickToRegisterLink() {
		waitForElementClickAble(registerLink);
		clickToElement(registerLink);
	}

	public void clickToLoginLink() {
		waitForElementClickAble(loginLink);
		clickToElement(loginLink);
	}

	public void clickToMyAccountLink() {
		waitForElementClickAble(myAccountLink);
		clickToElement(myAccountLink);
	}
}
