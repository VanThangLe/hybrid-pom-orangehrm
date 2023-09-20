package pageUIs.orangehrm;

public class BasePageUI {
	public static final String BUTTON_BY_LABEL = "//button[contains(.,'%s')]";
	public static final String TEXTBOX_BY_ID_NAME = "//input[@name='%s']";
	public static final String TITLE_FORM = "//h6[contains(normalize-space(),'%s')]";
	public static final String TAB_MENU_BY_PAGE_NAME = "//a[contains(text(),'%s')]";
	public static final String MENU_BY_PAGE_NAME = "//span[starts-with(@class,'oxd-text')][normalize-space()='%s']";
	public static final String TEXTBOX_BY_LABEL = "//label[contains(text(),'%s')]/parent::div/following-sibling::div";
	public static final String VALUE_BY_ROW_BODY_NUMBER_AND_COLUMN_BODY_NUMBER = "//div[@class='oxd-table-header']/following-sibling::div/div[%s]//div[%s]";
	public static final String ICON_IN_TABLE = "//div[@class='oxd-table-body']/div[1]//button/i[@class='%s']";
	public static final String RADIO_BY_LABEL = "//input[@type='radio']/parent::label[normalize-space()='%s']";
	public static final String CHECKBOX_BY_LABEL = "//input[@type='checkbox']/parent::label[normalize-space()='%s']";
	public static final String PARENT_DROPDOWN_BY_LABEL = "//label[contains(text(),'%s')]/parent::div/following-sibling::div";
	public static final String ADD_BUTTON_BY_TITLE_FORM = "//h6[contains(normalize-space(),'%s')]/following-sibling::button";
	public static final String CHILD_DROPDOWN_BY_LABEL = "//label[contains(text(),'%s')]/parent::div/following-sibling::div/descendant::div[@role='listbox']";
	
	public static final String UPLOAD_FILE = "//input[@type='file']";
	public static final String SUCCESS_TOAST_MESSAGE = "//div[starts-with(@class,'oxd-toast ')]";
	public static final String SWITCH_CHECKBOX = "//span[starts-with(@class,'oxd-switch-input')]";
	
	public static final String STATUS_EXEMTIONS_BY_LABEL = "//h6[normalize-space()='%s']/following-sibling::div[1]//label[contains(text(),'%s')]";
	public static final String SECOND_SAVE = "//div[@class='orangehrm-custom-fields']//button[contains(.,'Save')]";
	public static final String FIRST_SAVE = "//div[starts-with(@class,'orangehrm-horizontal-padding')]//button[contains(.,'Save')]";
}
