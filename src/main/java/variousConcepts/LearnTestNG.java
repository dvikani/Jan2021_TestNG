package variousConcepts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearnTestNG {

	WebDriver driver;

	@BeforeMethod
	public void inti() {
		//System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		
		driver.get("https://techfios.com/billing/?ng=login/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@Test
	public void loginTest() {
		Assert.assertEquals(driver.getTitle(), "Login - iBilling", "==Wrong Page==");
		// Type name = value;===declaring the variable
		// Element library
		WebElement USER_NAME_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement SUBMIT_BUTTON_ELEMENT = driver.findElement(By.xpath("//button[@type='submit']"));

//		login data
		String logId = "demo@techfios.com";
		String password = "abc123";
		
		USER_NAME_ELEMENT.sendKeys(logId);
		PASSWORD_ELEMENT.sendKeys(password);
		SUBMIT_BUTTON_ELEMENT.click();
		
		WebElement DASHBORD_TITLE_ELEMENT = driver.findElement(By.xpath("//h2[contains(text(), 'Dashboard')]"));
		System.out.println("===========" + DASHBORD_TITLE_ELEMENT.getText() +"=============");
		Assert.assertEquals(DASHBORD_TITLE_ELEMENT.getText(), "Dashboard", "====Wrong Page====");
	
	}
//	@AfterMethod 
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
