package displayAndUndisplay;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facebook.PageGenerator;
import pageObjects.facebook.RegisterPageObject;

public class Level_12_Register_Login_Assert_Verify extends BaseTest{
	WebDriver driver;
	RegisterPageObject registerPage;

	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		registerPage = PageGenerator.getRegisterPage(driver);
	}
	
	@Test
	public void Register_01_Verify() {
		//Fail 1
		verifyFalse(registerPage.isEmailTextboxDisplayed());
		registerPage.enterToEmailTextbox("dam@gmail.com");
		registerPage.sleepInSecond(3);
		//Fail 2
		verifyFalse(registerPage.isConfirmEmailTextboxDisplayed());
		registerPage.enterToEmailTextbox("");
		registerPage.sleepInSecond(3);
		verifyTrue(registerPage.isConfirmEmailTextboxDisplayed());
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}