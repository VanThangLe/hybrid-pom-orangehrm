package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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

import pageObjects.nopCommerce.AddressesPageObject;
import pageObjects.nopCommerce.BackInStockSubcriptionsPageObject;
import pageObjects.nopCommerce.ChangePasswordPageObject;
import pageObjects.nopCommerce.CustomerInforPageObject;
import pageObjects.nopCommerce.DownloadableProductsPageObject;
import pageObjects.nopCommerce.MyProductReviewsPageObject;
import pageObjects.nopCommerce.OrdersPageObject;
import pageObjects.nopCommerce.PageGenerator;
import pageObjects.nopCommerce.RewardPointsPageObject;
import pageUIs.nopCommerce.BasePageUI;

public class BasePage {
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
		explicitWait = new WebDriverWait(driver, longTimeout);
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

		explicitWait = new WebDriverWait(driver, longTimeout);
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
	
	public String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText();
	}
	
	public String getElementText(WebDriver driver, String locator, String... values) {
		return getWebElement(driver, castRestParameter(locator, values)).getText();
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
	
	public void checkToCheckboxRadio(WebDriver driver, String locator) {
		if(!isElementSelected(driver, locator)) {
			waitForElementClickAble(driver, locator).click();
		}
	}
	
	public void uncheckToCheckbox(WebDriver driver, String locator) {
		if(isElementSelected(driver, locator)) {
			waitForElementClickAble(driver, locator).click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator) {
		return waitForElementVisible(driver, locator).isDisplayed();
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
		return waitForElementVisible(driver, castRestParameter(locator, values)).isDisplayed();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator) {
		return waitForElementVisible(driver, locator).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}
	
	public void switchToFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}
	
	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(getWebElement(driver, locator)).perform();
	}
	
	public void moveMouseToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locator)).perform();
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
	
	public void uploadToElement(WebDriver driver, String locator, String filePath) {
		getWebElement(driver, locator).sendKeys(filePath);
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

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
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
		explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator, values)));
	}
	
	public WebElement waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}
	
	public WebElement waitForElementClickAble(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}
	
	public WebElement waitForElementClickAble(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator, values)));
	}
	
	public boolean waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	}
	
	public boolean waitForElementInvisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator, values)));
	}
	
	public CustomerInforPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementClickAble(driver, BasePageUI.CUSTOMER_INFOR_PAGE_LINK);
		clickToElement(driver, BasePageUI.CUSTOMER_INFOR_PAGE_LINK);
		return PageGenerator.getCustomerInforPage(driver);
	}
	
	public AddressesPageObject openAddressesPage(WebDriver driver) {
		waitForElementClickAble(driver, BasePageUI.ADDRESSES_PAGE_LINK);
		clickToElement(driver, BasePageUI.ADDRESSES_PAGE_LINK);
		return PageGenerator.getAddressesPage(driver);
	}
	
	public OrdersPageObject openOrdersPage(WebDriver driver) {
		waitForElementClickAble(driver, BasePageUI.ORDERS_PAGE_LINK);
		clickToElement(driver, BasePageUI.ORDERS_PAGE_LINK);
		return PageGenerator.getOrdersPage(driver);
	}
	
	public DownloadableProductsPageObject openDownloadableProductsPage(WebDriver driver) {
		waitForElementClickAble(driver, BasePageUI.DOWNLOADABLE_PRODUCTS_PAGE_LINK);
		clickToElement(driver, BasePageUI.DOWNLOADABLE_PRODUCTS_PAGE_LINK);
		return PageGenerator.getDownloadableProductsPage(driver);
	}
	
	public BackInStockSubcriptionsPageObject openBackInStockSubcriptionsPage(WebDriver driver) {
		waitForElementClickAble(driver, BasePageUI.BACK_IN_STOCK_SUBCRIPTIONS_PAGE_LINK);
		clickToElement(driver, BasePageUI.BACK_IN_STOCK_SUBCRIPTIONS_PAGE_LINK);
		return PageGenerator.getBackInStockSubcriptionsPage(driver);
	}
	
	public RewardPointsPageObject openRewardPointsPage(WebDriver driver) {
		waitForElementClickAble(driver, BasePageUI.REWARD_POINTS_PAGE_LINK);
		clickToElement(driver, BasePageUI.REWARD_POINTS_PAGE_LINK);
		return PageGenerator.getRewardsPointsPage(driver);
	}
	
	public ChangePasswordPageObject openChangePasswordPage(WebDriver driver) {
		waitForElementClickAble(driver, BasePageUI.CHANGE_PASSWORD_PAGE_LINK);
		clickToElement(driver, BasePageUI.CHANGE_PASSWORD_PAGE_LINK);
		return PageGenerator.getChangePasswordPage(driver);
	}
	
	public MyProductReviewsPageObject openMyProductReviewsPage(WebDriver driver) {
		waitForElementClickAble(driver, BasePageUI.MY_PRODUCT_REVIEWS_PAGE_LINK);
		clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEWS_PAGE_LINK);
		return PageGenerator.getMyProductReviewsPage(driver);
	}
	
	public BasePage openSiderBarPageByName(WebDriver driver, String pageName) {
		waitForElementClickAble(driver, BasePageUI.DYNAMIC_SIDE_BAR_PAGE_LINK, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_SIDE_BAR_PAGE_LINK, pageName);
		if(pageName.equals("Customer info")) {
			return PageGenerator.getCustomerInforPage(driver);
		} else if(pageName.equals("Addresses")) {
			return PageGenerator.getAddressesPage(driver);
		} else if(pageName.equals("Orders")) {
			return PageGenerator.getOrdersPage(driver);
		} else if(pageName.equals("Downloadable products")) {
			return PageGenerator.getDownloadableProductsPage(driver);
		} else if(pageName.equals("Back in stock subscriptions")) {
			return PageGenerator.getBackInStockSubcriptionsPage(driver);
		} else if(pageName.equals("Reward points")) {
			return PageGenerator.getRegisterPage(driver);
		} else if(pageName.equals("Change password")) {
			return PageGenerator.getChangePasswordPage(driver);
		} else if(pageName.equals("My product reviews")) {
			return PageGenerator.getMyProductReviewsPage(driver);
		} else {
			return null;
		}
	}
	
	public void openSiderBarPageByPageName(WebDriver driver, String pageName) {
		waitForElementClickAble(driver, BasePageUI.DYNAMIC_SIDE_BAR_PAGE_LINK, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_SIDE_BAR_PAGE_LINK, pageName);
	}
	
	private Alert alert;
	private Select select;
	private Actions action;
	private long longTimeout = 30;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
}