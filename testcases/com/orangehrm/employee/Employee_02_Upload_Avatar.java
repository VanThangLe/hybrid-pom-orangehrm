package com.orangehrm.employee;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.orangehrm.DashboardPO;
import pageObjects.orangehrm.LoginPO;
import pageObjects.orangehrm.PageGenerator;
import pageObjects.orangehrm.PersonalDetailPO;

public class Employee_02_Upload_Avatar extends BaseTest {
	WebDriver driver;
	LoginPO loginPage;
	DashboardPO dashboardPage;
	PersonalDetailPO personalDetailPage;
	String avatarFilePath = GlobalConstants.UPLOAD_FOLDER_PATH + "a.jpg";
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-condition: Step 01 - Open browser '" + browserName + "'and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
	}
	
	@Test
	public void Upload_Avatar() {
		log.info("Upload_Avatar_02 - Step 01: Login with Employee role");
		dashboardPage = loginPage.loginToSystem(driver, Employee_01_Add_Employee.empUserName , Employee_01_Add_Employee.empPassword);
		
		log.info("Upload_Avatar_02 - Step 02: Open Personal Detail page");
		dashboardPage.openMenuPage(driver, "My Info");
		personalDetailPage = PageGenerator.getPersonalDetailPage(driver);
		
		log.info("Upload_Avatar_02 - Step 03: Click to Change Photo image");
		personalDetailPage.clickToChangePhotoImage();
		
		log.info("Upload_Avatar_02 - Step 04: Upload new Avatar image");
		personalDetailPage.uploadImage(driver, avatarFilePath);
		
		log.info("Upload_Avatar_02 - Step 05: Click to Upload button");
		personalDetailPage.clickToButtonByID(driver, "btnSave");
		
		log.info("Upload_Avatar_02 - Step 06: Verify Success message is displayed");
		verifyTrue(personalDetailPage.isUploadAvaterSuccessMessageDisplayed());
		
		log.info("Upload_Avatar_02 - Step 07: Verify new Avatar image is displayed");
		verifyTrue(personalDetailPage.isNewAvatarImageDisplayed());
	}
	
	@Parameters({"browser"})
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanExcutableDriver();
	}
}
