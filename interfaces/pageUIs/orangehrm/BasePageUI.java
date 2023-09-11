package pageUIs.orangehrm;

public class BasePageUI {
	public static final String BUTTON_BY_LABEL = "//button[contains(.,'%s')]";
	public static final String TEXTBOX_BY_ID_NAME = "//input[@name='%s']";
	public static final String MENU_BY_PAGE_NAME = "//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='%s']";
	public static final String TEXTBOX_BY_LABEL = "//label[contains(text(),'%s')]/parent::div/following-sibling::div";
	public static final String UPLOAD_FILE = "//input[@type='file']";
	
	public static final String SUCCESS_TOAST_MESSAGE = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']";
	
	public static final String CREATE_LOGIN_DETAILS = "//span[starts-with(@class,'oxd-switch-input')]";
}
