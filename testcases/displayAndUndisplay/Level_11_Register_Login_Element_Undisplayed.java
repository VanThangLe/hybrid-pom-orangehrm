package displayAndUndisplay;

import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_11_Register_Login_Element_Undisplayed {
	WebDriver driver;
	WebDriverWait explicitWait;
	long shortTimeout = 5;
	long longTimeout = 30;

	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, longTimeout);
		driver.get("https://www.facebook.com");
		driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_Check_Element_Displayed() {
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		driver.findElement(By.name("reg_email__")).sendKeys("automation@gmail.com");
		//Element displayed/ visible
		//1. Xuất hiện ở trên UI: người dùng nhìn thấy và thao tác được
		//2. Có ở trong DOME (HTML)
		Assert.assertTrue(isElementDisplayed("//input[@name='reg_mail_confirmation__']"));
	}
	
	@Test
	public void TC_02_Check_Element_UnDisplayed() {
		driver.navigate().refresh();
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		//Element undisplayed/ invisible
		//1. Không xuất hiện ở trên UI: người dùng không nhìn thấy và không thao tác được
		//2. Vẫn có ở trong DOME (HTML)
		//Hàm isDisplayed trả về = true
		Assert.assertFalse(isElementDisplayed("//input[@name='reg_mail_confirmation__']"));
	}
	
	@Test
	public void TC_03_Check_Element_UnDisplayed() {
		driver.navigate().refresh();
		waitElementInvisible("//input[@name='reg_mail_confirmation__']");
		//Element undisplayed/ invisible
		//1. Không xuất hiện ở trên UI: người dùng không nhìn thấy và không thao tác được
		//2. Không có ở trong DOME (HTML)
		//Hàm isDisplayed chưa hề được chạy qua
		//Hàm findElement:
		//Nó đang apply timeout của implicitWait = 10s
		//Nếu như tìm thấy element thì không cần chờ hết timeout
		//Tìm cho đến khi nào hết timeout thì thôi
		//Sau khi hết timeout thì ném ra 1 ngoại lệ: NoSuchElementException
		//Đánh fail testcase tại step đó luôn - không chạy các step còn lại nữa
		Assert.assertTrue(isElementUndisplayed("//input[@name='reg_mail_confirmation__']"));
		//Kiểm tra 1 popup không hiển thị
	}
	
	@Test
	public void TC_04_Check_Element_UnDisplayed() {
		driver.navigate().refresh();
		waitElementInvisible("//input[@name='reg_mail_confirmation__']");
		Assert.assertTrue(isElementInvisible("//input[@name='reg_mail_confirmation__']"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	//1 - 2
	//Element có xuất hiện/ không xuất hiện
	public boolean isElementDisplayed(String locator) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	//2 - 3
	public boolean isElementUndisplayed(String locator) {
		try {
			overideTimeout(shortTimeout);
			//2
			WebElement element = driver.findElement(By.xpath(locator));
			return element.isDisplayed();
		} catch (Exception e) {
			//3
			return true;
		} finally {
			overideTimeout(longTimeout);
		}
	}
	
	//Xử lý 2 + 3
	public void waitElementInvisible(String locator) {
		try {
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			overideTimeout(longTimeout);
		}
	}
	
	public boolean isElementInvisible(String locator) {
		overideTimeout(shortTimeout);
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		overideTimeout(longTimeout);
		if(elements.size() == 0) {
			//3. Element không có trong DOM + không có trên UI
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			//2. Element có trong DOM nhưng không có trên UI (!elements.get(0).isDisplayed())
			return true;
		} else {
			//1. Element có trong DOM + hiển thị trên UI
			return false;
		}
	}
	
	public void overideTimeout(long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
}