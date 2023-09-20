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
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem, String... values) {
		getWebElement(driver, castRestParameter(parentLocator, values)).click();
		sleepInSecond(1);

		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(castRestParameter(childItemLocator, values))));

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
	
	public void openMenuByPageName(WebDriver driver, String menuPageName) {
		waitForElementClickAble(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
	}
	
	public void openTabMenuByPageName(WebDriver driver, String tabMenuPageName) {
		waitForElementClickAble(driver, BasePageUI.TAB_MENU_BY_PAGE_NAME, tabMenuPageName);
		clickToElement(driver, BasePageUI.TAB_MENU_BY_PAGE_NAME, tabMenuPageName);
	}
	
	public boolean isTitleFormDisplayed(WebDriver driver, String titleForm) {
		waitForElementClickAble(driver, BasePageUI.TITLE_FORM, titleForm);
		return isElementDisplayed(driver, BasePageUI.TITLE_FORM, titleForm);
	}
	
	public String getValueByRowBodyNumberAndColumnBodyNumber(WebDriver driver, String rowBodyNumber, String columnBodyNumber) {
		waitForElementVisible(driver, BasePageUI.VALUE_BY_ROW_BODY_NUMBER_AND_COLUMN_BODY_NUMBER, rowBodyNumber, columnBodyNumber);
		return getElementText(driver, BasePageUI.VALUE_BY_ROW_BODY_NUMBER_AND_COLUMN_BODY_NUMBER, rowBodyNumber, columnBodyNumber);
	}
	
	public void clickToButtonByLabel(WebDriver driver, String buttonLabel) {
		waitForElementClickAble(driver, BasePageUI.BUTTON_BY_LABEL, buttonLabel);
		clickToElement(driver, BasePageUI.BUTTON_BY_LABEL, buttonLabel);
	}
	
	public void clickToAddButtonByTitleForm(WebDriver driver, String titleForm) {
		waitForElementClickAble(driver, BasePageUI.ADD_BUTTON_BY_TITLE_FORM, titleForm);
		clickToElement(driver, BasePageUI.ADD_BUTTON_BY_TITLE_FORM, titleForm);
	}
	
	public void enterToTextboxByIDName(WebDriver driver, String textboxIDName, String value) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID_NAME, textboxIDName);
		sendkeyToElement(driver, BasePageUI.TEXTBOX_BY_ID_NAME, value, textboxIDName);
	}
	
	public String getTextboxValueByIDName(WebDriver driver, String textboxIDName) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID_NAME, textboxIDName);
		return getElementText(driver, BasePageUI.TEXTBOX_BY_ID_NAME, textboxIDName);
	}
	
	public void enterToTextboxByLabel(WebDriver driver, String textboxLabel, String value) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_LABEL, textboxLabel);
		sendkeyToElement(driver, BasePageUI.TEXTBOX_BY_ID_NAME, value, textboxLabel);
	}
	
	public String getTextboxValueByLabel(WebDriver driver, String textboxLabel) {
		waitForElementVisible(driver, BasePageUI.BUTTON_BY_LABEL);
		return getElementText(driver, BasePageUI.BUTTON_BY_LABEL);
	}
	
	public void uploadAvatar(WebDriver driver, String fileName) {
		uploadOneFile(driver, BasePageUI.UPLOAD_FILE, fileName);
	}
	
	public boolean isSuccessToastMessageDisplayed(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.SUCCESS_TOAST_MESSAGE);
		return isElementDisplayed(driver, BasePageUI.SUCCESS_TOAST_MESSAGE);
	}
	
	public void clickToSwitchCheckbox(WebDriver driver) {
		waitForElementClickAble(driver, BasePageUI.SWITCH_CHECKBOX);
		clickToElement(driver, BasePageUI.SWITCH_CHECKBOX);
	}
	
	public void clickToIconActionInList(WebDriver driver, String icon) {
		waitForElementClickAble(driver, BasePageUI.ICON_IN_TABLE, icon);
		clickToElement(driver, BasePageUI.ICON_IN_TABLE, icon);
	}
	
	public void clickToRadioByLabel(WebDriver driver, String radioLabel) {
		waitForElementClickAble(driver, BasePageUI.RADIO_BY_LABEL, radioLabel);
		checkToCheckboxOrRadio(driver, BasePageUI.RADIO_BY_LABEL, radioLabel);
	}
	
	public boolean isRadioButtonSelectedByLabel(WebDriver driver, String radioLabel) {
		waitForElementVisible(driver, BasePageUI.RADIO_BY_LABEL, radioLabel);
		return isElementSelected(driver, BasePageUI.RADIO_BY_LABEL, radioLabel);
	}
	
	public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabel) {
		waitForElementClickAble(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabel);
		checkToCheckboxOrRadio(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabel);
	}
	
	public boolean isCheckboxButtonSelectedByLabel(WebDriver driver, String checkboxLabel) {
		waitForElementVisible(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabel);
		return isElementSelected(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabel);
	}
	
	public void selectValueInCustomDropdownByLabel(WebDriver driver, String dropdownLabel, String value) {
		waitForElementClickAble(driver, BasePageUI.PARENT_DROPDOWN_BY_LABEL, dropdownLabel);
		selectItemInCustomDropdown(driver, BasePageUI.PARENT_DROPDOWN_BY_LABEL, BasePageUI.CHILD_DROPDOWN_BY_LABEL, value, dropdownLabel);
	}
	
	public String getValueInCustomDropdownByLabel(WebDriver driver, String dropdownLabel) {
		waitForElementVisible(driver, BasePageUI.PARENT_DROPDOWN_BY_LABEL, dropdownLabel);
		return getElementText(driver, BasePageUI.PARENT_DROPDOWN_BY_LABEL, dropdownLabel);
	}
}