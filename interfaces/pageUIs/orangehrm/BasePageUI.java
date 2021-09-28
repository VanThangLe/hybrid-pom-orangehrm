package pageUIs.orangehrm;

public class BasePageUI {
	public static final String DYNAMIC_SIDE_BAR_PAGE_BY_NAME = "//div[@class='listbox']//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "//input[@id='%s']";
	public static final String DYNAMIC_RADIO_BY_LABEL = "//label[text()='%s']/preceding-sibling::input";
	public static final String DYNAMIC_PAGE_FOOTER = "//div[@class='footer']//a[text()='%s']";
	public static final String DYNAMIC_PAGE_HEADER = "//div[@class='header']//a[text()='%s']";
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "//select[@name='%s']";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "//button[text()='%s']";
}
