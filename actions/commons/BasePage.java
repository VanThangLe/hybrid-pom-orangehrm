package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.orangehrm.DashboardPO;
import pageObjects.orangehrm.LoginPO;
import pageObjects.orangehrm.PageGenerator;
import pageUIs.orangehrm.BasePageUI;

public class BasePage {
	private Alert alert;
	private Select select;
	private Actions action;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
	
	public static BasePage getBasePage() {
		return new BasePage();
	}
	
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void setAllCookies(WebDriver driver, Set<Cookie> allCookies) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		alert = waitForAlertPresence(driver);
		alert.accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		alert = waitForAlertPresence(driver);
		alert.dismiss();
	}
	
	public void sendkeyToAlert(WebDriver driver, String value) {
		alert = waitForAlertPresence(driver);
		alert.sendKeys(value);
	}
	
	public String getTextInAlert(WebDriver driver, String value) {
		alert = waitForAlertPresence(driver);
		return alert.getText();
	}
	
	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}
	
	public String castRestParameter(String locator, String... values) {
		return String.format(locator, (Object[]) values);
	}
	
	public By getByXpath(String locator, String... values) {
		return By.xpath(castRestParameter(locator, values));
	}
	
	public By getByXpath(String locator) {
		return By.xpath(locator);
	}
	
	public WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}
	
	public List<WebElement> getWebElements(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
	}
	
	public void clickToElement(WebDriver driver, String locator) {
		waitForElementClickAble(driver, locator).click();
	}
	
	public void clickToElement(WebDriver driver, String locator, String... values) {
		waitForElementClickAble(driver, castRestParameter(locator, values)).click();
	}
	
	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		getWebElement(driver, locator).clear();
		getWebElement(driver, locator).sendKeys(value);
	}
	
	public void sendkeyToElement(WebDriver driver, String locator, String value, String... values) {
		getWebElement(driver, castRestParameter(locator, values)).clear();
		getWebElement(driver, castRestParameter(locator, values)).sendKeys(value);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemText) {
		select = new Select(getWebElement(driver, locator));
		select.selectByVisibleText(itemText);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemText, String... values) {
		select = new Select(getWebElement(driver, castRestParameter(locator, values)));
		select.selectByVisibleText(itemText);
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		select = new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}
	
	public String getFirstSelectedItemInDefaultDropdown(WebDriver driver, String locator) {
		select = new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getFirstSelectedItemInDefaultDropdown(WebDriver driver, String locator, String... values) {
		select = new Select(getWebElement(driver, castRestParameter(locator, values)));
		return select.getFirstSelectedOption().getText();
	}
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(1);

		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}
	
	public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}
	
	public String getAttributeValue(WebDriver driver, String locator, String attributeName, String... values) {
		return getWebElement(driver, castRestParameter(locator, values)).getAttribute(attributeName);
	}
	
	public String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText().trim();
	}
	
	public String getElementText(WebDriver driver, String locator, String... values) {
		return getWebElement(driver, castRestParameter(locator, values)).getText().trim();
	}
	
	public String getElementCssValue(WebDriver driver, String locator, String propertyName) {
		return getWebElement(driver, locator).getCssValue(propertyName);
	}
	
	public int getElementSize(WebDriver driver, String locator) {
		return getWebElements(driver, locator).size();
	}
	
	public int getElementSize(WebDriver driver, String locator, String... values) {
		return getWebElements(driver, castRestParameter(locator, values)).size();
	}
	
	public void checkToCheckboxOrRadio(WebDriver driver, String locator) {
		if(!isElementSelected(driver, locator)) {
			getWebElement(driver, locator).click();
		}
	}
	
	public void checkToCheckboxOrRadio(WebDriver driver, String locator, String... values) {
		if(!isElementSelected(driver, castRestParameter(locator, values))) {
			getWebElement(driver, castRestParameter(locator, values)).click();
		}
	}
	
	public void uncheckToCheckbox(WebDriver driver, String locator) {
		if(isElementSelected(driver, locator)) {
			getWebElement(driver, locator).click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isDisplayed();
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
		return getWebElement(driver, castRestParameter(locator, values)).isDisplayed();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator, String... values) {
		return getWebElement(driver, castRestParameter(locator, values)).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator, String... values) {
		return getWebElement(driver, castRestParameter(locator, values)).isSelected();
	}
	
	public void switchToFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}
	
	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(getWebElement(driver, locator)).perform();
	}
	
	public void hoverToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locator)).perform();
	}
	
	public void hoverToElement(WebDriver driver, String locator, String... values) {
		action = new Actions(driver);
		action.moveToElement(getWebElement(driver, castRestParameter(locator, values))).perform();
	}
	
	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(getWebElement(driver, locator)).perform();
	}
	
	public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
	}
	
	public void sendkeyBoardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locator), key).perform();
	}
	
	public void sendkeyBoardToElement(WebDriver driver, String locator, Keys key, String... values) {
		action = new Actions(driver);
		action.sendKeys(getWebElement(driver, castRestParameter(locator, values)), key).perform();
	}
	
	public String convertRgbaToHexa(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public void uploadOneFile(WebDriver driver, String locator, String fileName) {
		getWebElement(driver, locator).sendKeys(fileName);
	}
	
	public void uploadMultipleFiles(WebDriver driver, String locator, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FOLDER_PATH;
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, locator).sendKeys(fullFileName);
	}
	
	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}
	
	public void highlightElement(WebDriver driver, String locator, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, castRestParameter(locator, values));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
	}
	
	public void clickToElementByJS(WebDriver driver, String locator, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, castRestParameter(locator, values)));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
	}

	public boolean isJQueryAJAXLoadedSuccess(WebDriver driver){
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}
	
	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof "
				+ "arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public WebElement waitForElementVisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator, values)));
	}
	
	public WebElement waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}
	
	public WebElement waitForElementClickAble(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		return explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}
	
	public WebElement waitForElementClickAble(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		return explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator, values)));
	}
	
	public boolean waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	}
	
	public boolean waitForElementInvisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator, values)));
	}
	
	public void openMenuPage(WebDriver driver, String menuPageName) {
		waitForElementClickAble(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
	}
	
	public void openSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName) {
		waitForElementClickAble(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		
		waitForElementClickAble(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		
		isJQueryAJAXLoadedSuccess(driver);
	}
	
	public void openChildSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName, String childSubMenuPageName) {
		waitForElementClickAble(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		
		waitForElementVisible(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		hoverToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		
		waitForElementClickAble(driver, BasePageUI.MENU_BY_PAGE_NAME, childSubMenuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, childSubMenuPageName);
		
		isJQueryAJAXLoadedSuccess(driver);
	}
	
	public void clickToButtonByID(WebDriver driver, String buttonIDName) {
		waitForElementClickAble(driver, BasePageUI.BUTTON_BY_ID, buttonIDName);
		clickToElement(driver, BasePageUI.BUTTON_BY_ID, buttonIDName);
	}
	
	public void enterToTextboxByID(WebDriver driver, String textboxIDName, String value) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxIDName);
		sendkeyToElement(driver, BasePageUI.TEXTBOX_BY_ID, value, textboxIDName);
	}
	
	public String getTextboxValueByID(WebDriver driver, String textboxIDName) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxIDName);
		return getAttributeValue(driver, BasePageUI.TEXTBOX_BY_ID, "value", textboxIDName);
	}
	
	public void selectItemInDropdownByID(WebDriver driver, String dropdownID, String valueItem) {
		waitForElementClickAble(driver, BasePageUI.DROPDOWN_BY_ID, dropdownID);
		selectItemInDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_ID, valueItem, dropdownID);
	}
	
	public String getSelectedValueInDropdownByID(WebDriver driver, String dropdownID) {
		waitForElementVisible(driver, BasePageUI.DROPDOWN_BY_ID, dropdownID);
		return getFirstSelectedItemInDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_ID, dropdownID);
	}
	
	public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabelName) {
		waitForElementClickAble(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabelName);
		checkToCheckboxOrRadio(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabelName);
	}
	
	public boolean isCheckboxButtonSelectedByLabel(WebDriver driver, String checkboxLabelName) {
		waitForElementVisible(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabelName);
		return isElementSelected(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabelName);
	}
	
	public void clickToRadioByLabel(WebDriver driver, String radioLabelName) {
		waitForElementClickAble(driver, BasePageUI.RADIO_BY_LABEL, radioLabelName);
		checkToCheckboxOrRadio(driver, BasePageUI.RADIO_BY_LABEL, radioLabelName);
	}
	
	public boolean isRadioButtonSelectedByLabel(WebDriver driver, String radioLabelName) {
		waitForElementVisible(driver, BasePageUI.RADIO_BY_LABEL, radioLabelName);
		return isElementSelected(driver, BasePageUI.RADIO_BY_LABEL, radioLabelName);
	}
	
	public String getValueInTableIDAtColumnNameAndRowIndex(WebDriver driver, String tableID, String headerName, String rowIndex) {
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_HEADER_BY_ID_AND_NAME, tableID, headerName) + 1;
		waitForElementVisible(driver, BasePageUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID, rowIndex, String.valueOf(columnIndex));
		return getElementText(driver, BasePageUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID, rowIndex, String.valueOf(columnIndex));
	}
	
	public LoginPO logoutToSystem(WebDriver driver) {
		waitForElementClickAble(driver, BasePageUI.WELCOME_USER_LINK);
		clickToElement(driver, BasePageUI.WELCOME_USER_LINK);
		waitForElementClickAble(driver, BasePageUI.LOGOUT_LINK);
		clickToElement(driver, BasePageUI.LOGOUT_LINK);
		return PageGenerator.getLoginPage(driver);
	}
	
	public DashboardPO loginToSystem(WebDriver driver, String username, String password) {
		waitForElementVisible(driver, BasePageUI.USERNAME_LOGIN_TEXTBOX);
		sendkeyToElement(driver, BasePageUI.USERNAME_LOGIN_TEXTBOX, username);
		sendkeyToElement(driver, BasePageUI.PASSWORD_LOGIN_TEXTBOX, password);
		clickToElement(driver, BasePageUI.LOGIN_BUTTON);
		return PageGenerator.getDashboardPage(driver);
	}
	
	public void uploadImage(WebDriver driver, String filePath) {
		getWebElement(driver, BasePageUI.UPLOAD_FILE).sendKeys(filePath);
	}
	
	public boolean isSuccessMessageDisplayed(WebDriver driver, String messageValue) {
		waitForElementVisible(driver, BasePageUI.SUCCESS_MESSAGE_VALUE, messageValue);
		return isElementDisplayed(driver, BasePageUI.SUCCESS_MESSAGE_VALUE, messageValue);
	}
	
	public boolean isFieldEnabledByID(WebDriver driver, String fieldID) {
		waitForElementVisible(driver, BasePageUI.ANY_FIELD_BY_ID, fieldID);
		return isElementEnabled(driver, BasePageUI.ANY_FIELD_BY_ID, fieldID);
	}
	
	public void clickToDN(WebDriver driver) {
		waitForElementClickAble(driver, BasePageUI.A, "THAY ĐỔI NGÔN NGỮ");
		clickToElement(driver, BasePageUI.A, "THAY ĐỔI NGÔN NGỮ");
	}
}