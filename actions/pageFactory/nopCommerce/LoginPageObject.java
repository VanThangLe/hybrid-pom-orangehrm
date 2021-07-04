package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject extends BasePageFactory {
	public LoginPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@CacheLookup
	@FindBy(id = "Email")
	private WebElement emailTextbox;
	
	@CacheLookup
	@FindBy(id = "Password")
	private WebElement passwordTextbox;
	
	@CacheLookup
	@FindBy(xpath = "//button[text()='Log in']")
	private WebElement loginButton;
	
	public void enterToEmailTextbox(String emailAddress) {
		waitForElementVisible(emailTextbox);
		sendkeyToElement(emailTextbox, emailAddress);
	}

	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(passwordTextbox);
		sendkeyToElement(passwordTextbox, password);
	}

	public void clickToLoginButton() {
		waitForElementClickAble(loginButton);
		clickToElement(loginButton);
	}
}
