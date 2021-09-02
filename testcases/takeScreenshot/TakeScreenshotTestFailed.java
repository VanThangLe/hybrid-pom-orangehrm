package takeScreenshot;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class TakeScreenshotTestFailed {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir") + "/screenshotImg/";
	String username, password, loginPageUrl;

	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/v4/index.php");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		loginPageUrl = driver.getCurrentUrl();
	}

	@Test
	public void TC_01_Register() throws IOException {
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@name='emailid']"))
				.sendKeys("thanglv" + getRandomNumber() + "@hotmail.com");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		username = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	}

	@Test
	public void TC_02_Login() throws IOException {
		driver.get(loginPageUrl);
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		String welcomeMessage = driver.findElement(By.cssSelector("marquee.heading3")).getText();
		Assert.assertEquals(welcomeMessage, "Welcome To Manager's Page of Guru99 Bank.");
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
	
	public WebDriver getWebDriver() {
		return driver;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
