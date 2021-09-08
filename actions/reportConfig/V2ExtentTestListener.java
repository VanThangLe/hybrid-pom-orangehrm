package reportConfig;

import com.relevantcodes.extentreports.LogStatus;
import commons.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class V2ExtentTestListener extends BaseTest implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
		context.setAttribute("WebDriver", this.getWebDriver());
	}

	@Override
	public void onFinish(ITestContext context) {
		V2ExtentTestManager.endTest();
		V2ExtentManager.getReporter().flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		V2ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("---------- " + result.getName() + " FAILED test ----------");

		Object testClass = result.getInstance();
		WebDriver webDriver = ((BaseTest) testClass).getWebDriver();
		String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
		V2ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed", V2ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		V2ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

}