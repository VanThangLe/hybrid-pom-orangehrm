package pageObjects.facebook;

import org.openqa.selenium.WebDriver;

public class PageGenerator {
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
}
