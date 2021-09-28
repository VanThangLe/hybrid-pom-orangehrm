package pageObjects.orangehrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class EmployeeListPO extends BasePage {
	private WebDriver driver;

	public EmployeeListPO(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public AddEmployeePO clickToAddButton() {
		// TODO Auto-generated method stub
		return null;
	}

	public void enterToEmployeeNameTextbox(String string) {
		// TODO Auto-generated method stub
		
	}

	public void clickToSearchButton() {
		// TODO Auto-generated method stub
		
	}

	public boolean isEmployeeInfoDisplayedAtTable(String string, String string2, String string3) {
		// TODO Auto-generated method stub
		return false;
	}
}
