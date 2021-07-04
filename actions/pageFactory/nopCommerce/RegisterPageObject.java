package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageObject extends BasePageFactory {
	public RegisterPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@CacheLookup
	@FindBy(id = "gender-male")
	private WebElement genderMaleRadio;
	
	@CacheLookup
	@FindBy(id = "FirstName")
	private WebElement firstNameTextbox;
	
	@CacheLookup
	@FindBy(id = "LastName")
	private WebElement lastNameTextbox;
	
	@CacheLookup
	@FindBy(name = "DateOfBirthDay")
	private WebElement dayDropdown;
	
	@CacheLookup
	@FindBy(name = "DateOfBirthMonth")
	private WebElement monthDropdown;
	
	@CacheLookup
	@FindBy(name = "DateOfBirthYear")
	private WebElement yearDropdown;
	
	@CacheLookup
	@FindBy(id = "Email")
	private WebElement emailTextbox;
	
	@CacheLookup
	@FindBy(id = "Company")
	private WebElement companyTextbox;
	
	@CacheLookup
	@FindBy(id = "Password")
	private WebElement passwordTextbox;
	
	@CacheLookup
	@FindBy(id = "ComfirmPassword")
	private WebElement confirmPasswordTextbox;
	
	@CacheLookup
	@FindBy(id = "register-button")
	private WebElement registerButton;
	
	@CacheLookup
	@FindBy(xpath = "//div[@class='result' and text()='Your registration completed']")
	private WebElement registeredSuccessMessage;
	
	@CacheLookup
	@FindBy(css = "ico-logout")
	private WebElement logoutLink;
	
	public void clickToGenderMaleRadio() {
		waitForElementClickAble(genderMaleRadio);
		clickToElement(genderMaleRadio);
	}

	public void enterToFirstNameTextbox(String firstName) {
		waitForElementVisible(firstNameTextbox);
		sendkeyToElement(firstNameTextbox, firstName);
	}

	public void enterToLastNameTextbox(String lastName) {
		waitForElementVisible(lastNameTextbox);
		sendkeyToElement(lastNameTextbox, lastName);
	}

	public void selectDayDropdown(String day) {
		waitForElementVisible(dayDropdown);
		selectItemInDefaultDropdown(dayDropdown, day);
	}

	public void selectMonthDropdown(String month) {
		waitForElementVisible(monthDropdown);
		selectItemInDefaultDropdown(monthDropdown, month);
	}

	public void selectYearDropdown(String year) {
		waitForElementVisible(yearDropdown);
		selectItemInDefaultDropdown(yearDropdown, year);
	}

	public void enterToEmailTextbox(String emailAddress) {
		waitForElementVisible(emailTextbox);
		sendkeyToElement(emailTextbox, emailAddress);
	}

	public void enterToCompanyTextbox(String companyName) {
		waitForElementVisible(companyTextbox);
		sendkeyToElement(companyTextbox, companyName);
	}

	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(passwordTextbox);
		sendkeyToElement(passwordTextbox, password);
	}

	public void enterToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(confirmPasswordTextbox);
		sendkeyToElement(confirmPasswordTextbox, confirmPassword);
	}

	public void clickToRegisterButton() {
		waitForElementClickAble(registerButton);
		clickToElement(registerButton);
	}

	public boolean isRegisterSuccessMessageDisplayed() {
		waitForElementVisible(registeredSuccessMessage);
		return isElementDisplayed(registeredSuccessMessage);
	}

	public void clickToLogoutLink() {
		waitForElementClickAble(logoutLink);
		clickToElement(logoutLink);
	}
}
