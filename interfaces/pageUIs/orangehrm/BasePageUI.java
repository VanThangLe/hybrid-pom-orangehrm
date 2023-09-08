package pageUIs.orangehrm;

public class BasePageUI {
	public static final String BUTTON_BY_LABEL = "//button[contains(.,'%s')]";
	public static final String TEXTBOX_BY_ID_NAME = "//input[@name='%s']";
	public static final String MENU_BY_PAGE_NAME = "//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='%s']";
	public static final String TEXTBOX_BY_LABEL = "//label[contains(text(),'%s')]/parent::div/following-sibling::div";
}
