package complete;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class tt {
WebDriver driver;

	
	@BeforeTest
	public void setup() {   // /home/garikipatij/Downloads/chrome-linux64 (1)/chrome-linux64/chrome
		System.setProperty("Webdriver.chrome.driver", "/home/garikipatij/Downloads/chromedriver-linux64/chromedriver");	
		driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();				
		String url = "https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php";
		driver.get(url);
		
		
	}
	
	
	@DataProvider (name="user details")
	public Object[][] userdata(){
		return new Object[][] {
			{"mainline","mainline@gmail.com"},
			{"indepth","indepth@gmail.com"},
			{"coreos","coreos@gmail.com"}
		};
	} 
	
	
	
	@Test (dataProvider="user details")
	public void homepage(String user,String gmail)  {
				
		WebElement username =driver.findElement(By.cssSelector("input#name"));
		username.clear();
		
		WebElement usergmail=driver.findElement(By.cssSelector("input[type='email']"));
		usergmail.clear();
		
		username.sendKeys(user); 
		usergmail.sendKeys(gmail);  //tag & attribute
		
		
		
		
		
	}
	
	
	/*
	 * @AfterTest public void teardown() { driver.close(); }
	 */
}



