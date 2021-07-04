package pageFactory.nopCommerce;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {
	private WebDriver driver;
	
	
	public BasePageFactory(WebDriver driver) {
		this.driver = driver;
	}
	
	public void openPageUrl(String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public String getPageSource() {
		return driver.getPageSource();
	}
	
	public void backToPage() {
		driver.navigate().back();
	}
	
	public void refreshCurrentPage() {
		driver.navigate().refresh();
	}
	
	public void forwardToPage() {
		driver.navigate().forward();
	}
	
	public Alert waitForAlertPresence() {
		explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert() {
		alert = waitForAlertPresence();
		alert.accept();
	}
	
	public void cancelAlert() {
		alert = waitForAlertPresence();
		alert.dismiss();
	}
	
	public void sendkeyToAlert(String value) {
		alert = waitForAlertPresence();
		alert.sendKeys(value);
	}
	
	public String getTextInAlert(String value) {
		alert = waitForAlertPresence();
		return alert.getText();
	}
	
	public void switchToWindowByID(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public void clickToElement(WebElement element) {
		waitForElementClickAble(element).click();
	}
	
	public WebElement waitForElementClickAble(WebElement element) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.elementToBeClickable((element)));
	}
	
	public void sendkeyToElement(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}
	
	public String getElementText(WebElement element) {
		return element.getText();
	}
	
	public boolean isElementDisplayed(WebElement element) {
		return waitForElementVisible(element).isDisplayed();
	}
	
	public WebElement waitForElementVisible(WebElement element) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public boolean isElementSelected(WebElement element) {
		return element.isSelected();
	}
	
	public void selectItemInDefaultDropdown(WebElement element, String itemText) {
		select = new Select(element);
		select.selectByVisibleText(itemText);
	}
	
	public String getFirstSelectedItemInDefaultDropdown( WebElement element) {
		select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}
	
	public String getAttributeValue(WebElement element, String attributeName) {
		return element.getAttribute(attributeName);
	}
	
	private Alert alert;
	private Select select;
	private long longTimeout = 30;
	private WebDriverWait explicitWait;
}