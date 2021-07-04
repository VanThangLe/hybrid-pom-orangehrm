package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPageObject extends BasePageFactory {
	public MyAccountPageObject(WebDriver driver) {
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
	
	public boolean isGenderMaleRadioSelected() {
		waitForElementVisible(genderMaleRadio);
		return isElementSelected(genderMaleRadio);
	}

	public String getFirstNameTextboxValue() {
		waitForElementVisible(firstNameTextbox);
		return getAttributeValue(firstNameTextbox, "value");
	}

	public String getLastNameTextboxValue() {
		waitForElementVisible(lastNameTextbox);
		return getAttributeValue(lastNameTextbox, "value");
	}

	public String getCompanyTextboxValue() {
		waitForElementVisible(companyTextbox);
		return getAttributeValue(companyTextbox, "value");
	}

	public String getEmailTextboxValue() {
		waitForElementVisible(emailTextbox);
		return getAttributeValue(emailTextbox, "value");
	}

	public String getDayDropdownValue() {
		waitForElementVisible(dayDropdown);
		return getFirstSelectedItemInDefaultDropdown(dayDropdown);
	}

	public String getMonthDropdownValue() {
		waitForElementVisible(monthDropdown);
		return getFirstSelectedItemInDefaultDropdown(monthDropdown);
	}

	public String getYearDropdownValue() {
		waitForElementVisible(yearDropdown);
		return getFirstSelectedItemInDefaultDropdown(yearDropdown);
	}
	
}
