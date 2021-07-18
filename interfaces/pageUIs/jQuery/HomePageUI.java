package pageUIs.jQuery;

public class HomePageUI {
	public static final String PAGING_LINK_BY_NUMBER = "//li[@class='qgrd-pagination-page']/a[text()='%s']";
	public static final String PAGING_LINK_ACTIVE_BY_NUMBER = "//li[@class='qgrd-pagination-page']/a[contains(@class,'active') and text()='%s']";
	public static final String HEARDER_TEXTBOX_BY_NAME = "//div[@class='qgrd-header-text' and text()='%s']/parent::div/folowing-sibling::input";
	public static final String ROW_BY_ALL_VALUES = "//td[@data-key='females' and text()='%s']/following-sibling::td[@data-key='country' and text()='%s']/following-sibling::td[@data-key='males' and text()='%s']/following-sibling::td[@data-key='total' and text()='%s']";
	public static final String ROW_ACTION_BY_COUNTRY_AND_NAME = "//td[@data-key='country' and text()='%s']/preceding-sibling::td[@class='qgrd-actions']/button[contains(@class,'%s')]";
	public static final String COLUMN_NAME_POSITION = "//th[text()='%s']/preceding-sibling::th";
	public static final String TEXTBOX_AT_COLUMN_NAME_AND_ROW_NUMBER = "//tbody//tr[%s]/td[%s]/input";
	public static final String DROPDOWN_AT_COLUMN_NAME_AND_ROW_NUMBER = "//tbody//tr[%s]/td[%s]//select";
	public static final String ICON_ACTION_AT_ROW = "//tbody//tr[1]//td//button[contains(@title,'%s')]";
}
