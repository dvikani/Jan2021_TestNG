package variousConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearnTestNG {

	WebDriver driver;
	String browser= null;
	String url;
	
	 @BeforeClass
	public void readConfig() {
		//BuffereReader//InputStream//FileReader//Scanner---these are the 4 class that can read any kind of file
		
		Properties prop = new Properties();   // for java to understand the file system 
		try {
			InputStream input = new FileInputStream("./src/main/java/config/config.properties"); 
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Browser used: " + browser); 
			url = prop.getProperty("url");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeMethod
	public void inti() {
		
		
		if (browser == "chrome") {
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if (browser.equalsIgnoreCase("Firefox") ) {
			System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		
		
		driver.get("url");
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
	@AfterMethod 
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
