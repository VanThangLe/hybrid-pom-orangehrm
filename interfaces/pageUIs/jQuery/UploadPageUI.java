package pageUIs.jQuery;

public class UploadPageUI {
	public static final String UPLOAD_FILE = "//input[@name='files[]']";
	public static final String FILE_NAME_LOADED_TEXT = "//p[@class='name' and text()='%s']";
	public static final String START_BUTTON_BY_FILE_NAME = "//p[@class='name' and text()='%s']/parent::td/following-sibling::td/button[contains(@class,'start')]";
	public static final String FILE_NAME_UPLOADED_TEXT = "//p[@class='name']/a[text()='%s']";
}
