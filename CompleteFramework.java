package complete;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class CompleteFramework {
WebDriver driver;

	
	@BeforeMethod
	public void setup() {   // /home/garikipatij/Downloads/chrome-linux64 (1)/chrome-linux64/chrome
		//System.setProperty("webdriver.chrome.driver", "/home/garikipatij/Downloads/chromedriver-linux64 (1)/chromedriver-linux64/chromedriver");
		System.setProperty("Webdriver.chrome.driver", "/home/garikipatij/Downloads/chromedriver-linux64 (1)/chromedriver-linux64/chromedriver");
		driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();				
		String url = "https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php";
		driver.get(url);
		
	}
	
	@Test (description="Home page" ,enabled=true ,priority=1)
	public void homepage() throws InterruptedException {
		System.out.println(driver.getTitle());
		
		//elememts using different locators
		driver.findElement(By.cssSelector("input#name")).sendKeys("JAGATH");    // tag & id = tag#id
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("jahath@gmail.com");  //tag & attribute
		driver.findElement(By.xpath("//input[@id='gender']")).click();  //relative xpath
		driver.findElement(By.cssSelector("input.form-control[name='mobile']")).sendKeys("9876543210"); //tag.class[attribute]
		driver.findElement(By.xpath("//input[contains(@id,'dob')]")).sendKeys("01/01/2000");  //xpath  using contains
		driver.findElement(By.xpath("//input[@class='form-control' and@id='subjects']")).sendKeys("english"); //xpath using and
		
		WebElement checkbox=driver.findElement(By.xpath("(//input[@class='form-check-input mt-0'])[6]"));
		//javascript to click
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",checkbox);
		
		WebElement upload =driver.findElement(By.xpath("//input[@id='picture' or @type='file']"));
		upload.sendKeys("/home/garikipatij/Pictures/div.png");
		
		
		WebElement text=driver.findElement(By.xpath("//textarea[@id='picture']"));
		text.sendKeys("Hyderabad");
		text.sendKeys(Keys.ENTER);
		text.sendKeys("521131");
		
		
		//Dropdown
		
		WebElement DD1 = driver.findElement(By.xpath("//select[@id='state']"));	
		Select drop = new Select(DD1);
		
		List <WebElement> op=drop.getOptions();
		System.out.println(op.size());      //print size of the dropdown
		//orint all vlaues in dropdown
		for (int i=1 ; i<op.size();i++) {
		     System.out.println(op.get(i).getText());	
		}
		
		drop.selectByIndex(2);
		//prints the selected option
		WebElement option = drop.getFirstSelectedOption();
		System.out.println("Selected text in 1st dropdown" + option.getText());
		
		
		
		WebElement DD2 = driver.findElement(By.xpath("//select[@id='city']"));	
		Select drop2 = new Select(DD2);
		
		List<WebElement> op2= drop2.getOptions();
		
		for(int j=1;j<op2.size();j++) {
			System.out.println(op2.get(j).getText());
		}
		
		drop2.selectByIndex(2);
		System.out.println("Selected text in 2nd dropdown" + drop2.getFirstSelectedOption().getText());
		
		
	}
	
	
	@Test(description="checkbox and radio",enabled=true,priority=2)
	public void test2() throws InterruptedException {
		driver.findElement(By.xpath("//button[@class='accordion-button collapsed' and @aria-controls='collapseOne' ]")).click();
		//driver.findElement(By.xpath("(//a)[2]")).click();
		//WebDriverWait  wait= new WebDriverWait(driver, null);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@href,'check')]")));
		
		//checkbox
		driver.findElement(By.xpath("//*[contains(@href,'check')]")).click();
		driver.findElement(By.id("c_bs_1")).click();
		driver.findElement(By.xpath("(//span[@class='plus'])[1]")).click();
		
		WebElement checkbox = driver.findElement(By.id("c_bf_1"));
        System.out.println(checkbox.isSelected());
          
         String verify =(checkbox.isSelected() ? "Checkbox is selected" : "not selected");
         System.out.println(verify);
                
			/*
			 * if(checkbox.isSelected()) { System.out.println("Checkbox is selected"); }
			 * else { System.out.println("not selected"); }
			 */
         
         //radio buttons
         driver.findElement(By.xpath("//*[contains(@href,'radio')]")).click();
         driver.findElement(By.cssSelector("input[value='igotthree']")).click();
         
         String te=driver.findElement(By.cssSelector("div#check1")).getText();
         System.out.println(te);
         
        WebElement check3= driver.findElement(By.cssSelector("input[value='option3']"));
        System.out.println("The check  is enabled " + check3.isEnabled());
        
        
         
       
        
		//Thread.sleep(2000);
		}
	
	@Test(description="Elements",enabled=true,priority=3)
	public void test3() {
		driver.findElement(By.xpath("//button[@class='accordion-button collapsed' and @aria-controls='collapseOne' ]")).click();
		driver.findElement(By.xpath("//a[@href='buttons.php']")).click();
		
		Actions act= new Actions(driver);
		WebElement singleclick = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
		WebElement doubleeclick = driver.findElement(By.xpath("//button[@class='btn btn-success']"));
		WebElement click = driver.findElement(By.xpath("//button[@class='btn btn-secondary']"));
		
		System.out.println(click.isEnabled());
		act.moveToElement(singleclick).click().build().perform();
		act.moveToElement(doubleeclick).doubleClick().build().perform();
		
		System.out.println(driver.findElement(By.cssSelector("#welcomeDiv")).getText());
		System.out.println(driver.findElement(By.id("doublec")).getText());
		
		driver.findElement(By.xpath("//a[@href='links.php']")).click();
	    driver.findElement(By.linkText("Home")).click();
	   
	    
	    
	   // WebDriverWait wait = new WebDriverWait(driver);
	    
	    
	   String parent =driver.getWindowHandle();
	   System.out.println(parent);
	   
	  Set<String> child=driver.getWindowHandles();
	  System.out.println(child);
	  
	  driver.switchTo().window(parent);
	  
	  driver.findElement(By.linkText("Created")).click();	
	  driver.findElement(By.id("bad-request")).click();
	  String Expected="Link has responded with staus 400 and status text Bad Request";
	  String Actual= driver.findElement(By.className("brequest")).getText();
	  
	  String verify2=(Expected.equals(Actual) ? "Both text has same":"Both text has not same");
	  System.out.println(verify2);
	  Assert.assertEquals(Expected, Actual);
	  
	  driver.findElement(By.xpath("//a[@href='broken-links.php']")).click();
	  driver.findElement(By.xpath("//a[@href='broken-link.php']")).click();
	  driver.navigate().back();
	  
	  driver.findElement(By.xpath("//a[@href='dynamic-prop.php']")).click();
	  driver.findElement(By.id("colorChange")).click();
	  
	  WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(7));
      wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("visibleAfter"))));
      System.out.println(driver.findElement(By.id("visibleAfter")).getText());
      
      
      driver.findElement(By.xpath("//a[@href='webtables.php']")).click(); 
      WebElement table=driver.findElement(By.xpath("/html/body/main/div/div/div[2]/form/div[2]/table"));
      List<WebElement> tablerow = table.findElements(By.tagName("tr"));
      
      int rowindex=3;
      WebElement row = tablerow.get(rowindex);
      
      System.out.println("for specific row =3");
      List<WebElement> cells=row.findElements(By.tagName("td"));      
    
      for(WebElement c :cells) {
    	  String data=c.getText();
    	  System.out.println(data);
    	  
      }
      
      System.out.println("for all rows");
      List<WebElement> rows = table.findElements(By.xpath("//tr"));
		
		 for(WebElement r : rows) { 
			 List<WebElement> tabledata=r.findElements(By.xpath("//td")); 
			 for(int j=0;j<tabledata.size();j++) {
				 System.out.println(tabledata.get(j).getText());
			 }
			// System.out.println(r.getText());
				/*
				 * for(WebElement data:tabledata) { String value = data.getText();
				 * System.out.println(value);
				 */
		  
		  } }
		  
	@Test(description="alerts and window" , enabled=true,priority=4)
	public void windows() throws InterruptedException {
		driver.findElement(By.xpath("//button[@class='accordion-button collapsed' and @data-bs-target='#collapseThree']")).click();
		
		driver.findElement(By.linkText("Browser Windows")).click();
		String parent = driver.getWindowHandle();
		System.out.println(parent);
		driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[1]")).click();
		System.out.println(driver.getTitle());
		WebElement tt=driver.findElement(By.linkText("Selenium Tutorial"));
		Assert.assertTrue(tt.isDisplayed());
		
		ArrayList tab=new ArrayList( driver.getWindowHandles());
		System.out.println("tabs opened " + tab.size());
		driver.switchTo().window((String) tab.get(1));
		driver.close();
		
		driver.switchTo().window(parent);
		
		
		driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[2]")).click();
		ArrayList windows=new ArrayList(driver.getWindowHandles());
		
		System.out.println("no of windows"+windows.size());
		driver.switchTo().window((String)windows.get(1));
		//driver.manage().window().maximize();
		String text = driver.findElement(By.xpath("/html/body/main/div/div/h1")).getText();
		System.out.println((text.equals("New Window") ? "passed":"failed"));
		driver.close();
		
		driver.switchTo().window(parent);
		//driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[3]")).click();
		
		
		//driver.close();
		
		driver.findElement(By.xpath("//a[@href='alerts.php']")).click();
		driver.findElement(By.xpath("//button[@onclick='showAlert()']")).click();
		driver.switchTo().alert().accept();
		
		driver.findElement(By.xpath("//button[@onclick='myMessage()']")).click();
		WebDriverWait Wait1=new WebDriverWait(driver,Duration.ofSeconds(7));
		Wait1.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		
		
		driver.findElement(By.xpath("//button[@onclick='myDesk()']")).click();
		driver.switchTo().alert().dismiss();
		String checktext= driver.findElement(By.cssSelector("#desk")).getText();
		System.out.println(checktext);
		
		driver.findElement(By.xpath("//button[@onclick='myPromp()']")).click();
		driver.switchTo().alert().sendKeys("jagath");
		//Thread.sleep(5000);
		driver.switchTo().alert().accept();
		
		
		
		
		 
	}
	
		@Test (description="nested frames and dialog" , enabled=true,priority=5)
		public void frames() {
			//frames
			driver.findElement(By.xpath("//button[@class='accordion-button collapsed' and @data-bs-target='#collapseThree']")).click();
			driver.findElement(By.xpath("//a[@href='frames.php']")).click();
			List<WebElement> Noofframes = driver.findElements(By.tagName("iframe"));
			System.out.println("no of frames present " + Noofframes.size());
			
			
			
			driver.switchTo().frame(0);
			System.out.println(driver.findElement(By.xpath("/html/body/div/header/div[2]/h1")).getText());
			
			WebElement element= driver.findElement(By.xpath("/html/body/main/div/div/h1"));
			 System.out.println(element.getText());
			 JavascriptExecutor scroll = (JavascriptExecutor) driver; 
			 scroll.executeScript("arguments[0].scrollIntoView();",element);
			 
			 driver.switchTo().parentFrame();
			 WebElement frame2= driver.findElement(By.xpath("/html/body/main/div/div/div[2]/iframe[2]"));
			 
			 driver.switchTo().frame(frame2);
			 System.out.println("swutching to 2nd frame");
			 scroll.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("/html/body/main/div/div")));
			 
			 //nested frames
			 driver.switchTo().parentFrame();
			 driver.findElement(By.xpath("//a[@href='nestedframes.php']")).click();
			 List<WebElement> Nestedframes = driver.findElements(By.tagName("iframe"));
			System.out.println("no of frames present " + Nestedframes.size());
			driver.switchTo().frame(0);
			driver.findElement(By.xpath("/html/body/div/header/div[3]/a")).click();
			
			ArrayList wind= new ArrayList(driver.getWindowHandles());
			
			System.out.println(driver.getTitle());
			driver.switchTo().window((String)wind.get(1));
			System.out.println(driver.getTitle());
			driver.close();
			
			driver.switchTo().window((String)wind.get(0));
			
			
			//dialogs
			driver.findElement(By.xpath("//a[@href='modal-dialogs.php']")).click();
			driver.findElement(By.xpath("//button[@data-bs-toggle='modal' and @data-bs-target='#exampleModalSm']")).click();
			
			//driver.findElement(By.xpath("//button[@data-bs-target='#exampleModalToggle2']")).click();	
			WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(3));
			
			 WebElement dialog=driver.findElement(By.xpath("//*[@id=\"exampleModalSm\"]/div/div/div[3]/button"));
			 wait2.until(ExpectedConditions.elementToBeClickable(dialog));
			 dialog.click();
			  
			  //interactions 
			//  driver.findElement(By. xpath("//button[@class='accordion-button collapsed' and @data-bs-target='#collapseFive']"
			  //)).click(); driver.findElement(By.linkText("Sortable")).click();
			
		}
		 
		@Test(description="interactions" , enabled=true,priority=6)
		public void Interactions() {
			driver.findElement(By.xpath("//button[@class='accordion-button collapsed' and @data-bs-target='#collapseFive']")).click();
			driver.findElement(By.xpath("//a[@href='droppable.php']")).click();
			Actions a =new Actions(driver);
			
			//darg and drop
			WebElement drag=driver.findElement(By.xpath("//*[@id=\"draggable\"]"));
			WebElement drop=driver.findElement(By.id("droppable"));
			//a.clickAndHold(drag).moveByOffset(100, 0).build().perform();
		      a.dragAndDrop(drag, drop).build().perform();
			
			//multiselect
			driver.findElement(By.xpath("//a[@href='selectable.php']")).click();
			driver.findElement(By.xpath("//li[@class='list-group-li'][1]")).click();
			driver.findElement(By.xpath("//li[@class='list-group-li'][5]")).click();
			
			//draggable around
			driver.findElement(By.xpath("//a[@href='dragabble.php']")).click();
			WebElement draga= driver.findElement(By.id("draggables"));
			a.clickAndHold(draga).moveByOffset(100, 200).build().perform();
			
			
			//sortable
			 driver.findElement(By.xpath("//a[@href='sortable.php']")).click();
			 driver.findElement(By.id("nav-profile-tab")).click();
			 
			 WebElement first=driver.findElement(By.xpath("(//div[@class='col-sm-6 col-md-4'])[1]")); 
			 WebElement third=driver.findElement(By.xpath("(//div[@class='col-sm-6 col-md-4'])[3]"));
			 a.dragAndDrop(first, third).build().perform();
			// a.dragAndDrop(third,first).build().perform();
			 System.out.println("sortable complete");
			
			
			/*
			driver.findElement(By.xpath("//a[@href='resizable.php']")).click();
		   WebElement resize=	driver.findElement(By.xpath("//div[@class='both selector']"));
		   System.out.println(resize.getText());
		   a.doubleClick(resize).moveByOffset(100,200).build().perform();
			
			*/
			
			
 
		}
	  
	  @Test (description="widgets" , enabled=true,priority=7)
	  public void widgets() {
		  
		  driver.findElement(By.xpath("//button[@class='accordion-button collapsed' and @data-bs-target='#collapseFour']")).click();
		  
		  //accordion
			driver.findElement(By.xpath("//a[@href='accordion.php']")).click();
			driver.findElement(By.xpath("//*[@id=\"headingTwentyTwo\"]/button")).click();
			//Actions a =new Actions(driver);
			
		//Auto
			driver.findElement(By.xpath("//a[@href='auto-complete.php']")).click();
			driver.findElement(By.cssSelector("input#tags")).sendKeys("a");
			List<WebElement> alloptions=driver.findElements(By.xpath("//ul[@id='ui-id-1']//li"));
			System.out.println(alloptions.size());
			
			for(WebElement e :alloptions) {
				System.out.println(e.getText());
				String text=e.getText();
				System.out.println(text);
				if(text.contains("Apple")) {
					e.click();
					
				}
			}
			
			
			//Datepicker
			driver.findElement(By.xpath("//a[@href='date-picker.php']")).click();
			driver.findElement(By.id("datetimepicker1")).click();
			WebElement year=driver.findElement(By.xpath("(//input[@class='numInput cur-year'])[1]"));
			year.clear();
			year.sendKeys("1998");
			
			Select select = new Select(driver.findElement(By.xpath("(//select[@class='flatpickr-monthDropdown-months'])[1]")));
			List<WebElement> op=select.getOptions();
			System.out.println(op.size());
			for(WebElement o :op) {
				String te=o.getText();
				System.out.println(o.getText());
				if(te=="August") {
					select.selectByValue(te);
				}
			}
			select.selectByIndex(7);
			System.out.println(driver.findElement(By.id("datetimepicker1")).getText());
			
			driver.findElement(By.xpath("//span[@aria-label='August 7, 1998']")).click();
			WebElement hour=driver.findElement(By.xpath("(//input[@class='numInput flatpickr-hour'])[1]"));
			hour.clear();
			hour.sendKeys("5");
			WebElement min=driver.findElement(By.xpath("(//input[@class='numInput flatpickr-minute'])[1]"));
			min.sendKeys("30");
			driver.findElement(By.xpath("(//span[@class='flatpickr-am-pm'])[1]")).click();
			
			//Slider
			driver.findElement(By.xpath("//a[@href='slider.php']")).click();
			WebElement slider=driver.findElement(By.id("ageInputId"));
			Actions a = new Actions(driver);
			a.clickAndHold(slider).moveByOffset(250, 0).build().perform();	
			System.out.println("slider is enabled  " +driver.findElement(By.id("disabledRange")).isEnabled());
			
			//progreebar
			driver.findElement(By.xpath("//a[@href='progress-bar.php']")).click();
			driver.findElement(By.id("startProgressTimer")).click();
			WebElement progressbar=driver.findElement(By.xpath("//div[@role='progressbar']"));
			WebDriverWait wait= new WebDriverWait (driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.attributeContains(progressbar, "class", "progress-bar progress-bar-success"));
			
			//tool-tips and mouse hover
			driver.findElement(By.xpath("//a[@href='tool-tips.php']")).click();
			WebElement hover=driver.findElement(By.xpath("(//button[@class='btn btn-secondary'])[2]"));
			String tooltip=hover.getAttribute("title");
			System.out.println(tooltip);
			a.moveToElement(hover).build().perform();
			
			if(tooltip.equalsIgnoreCase("Tooltip on right")) {
				System.out.println("tooltip matching expected value");
				
			}
			else {
				System.out.println("tooltip doesnt matching expected value");
			}
			
			//menu
			
			
			
	  }
	  
@Test(description="multiselect",enabled=true,priority=8)
public void multi() {
	driver.findElement(By.xpath("//button[@class='accordion-button collapsed' and @data-bs-target='#collapseFour']")).click();
	System.out.println("collapse button is seected");
	
	driver.findElement(By.xpath("//a[@href='select-menu.php']")).click();
	WebElement dropdown=driver.findElement(By.xpath("//select[@id='demo-multiple-select']"));
	
	Select select2 = new Select(dropdown);
	Boolean b = select2.isMultiple();
	System.out.println(b);
	
	List<WebElement> multi=select2.getOptions();
	
	for(int k=0;k< multi.size();k++) {
		System.out.println(multi.get(k).getText());
	}
	
	
	for(WebElement ms : multi) {
		System.out.println("options are " + ms.getText());	
	}			
	//select2.selectByIndex(1);
	//select2.selectByIndex(4);

	
	Select single=new Select(driver.findElement(By.id("inputGroupSelect03")));
	List<WebElement> sop=single.getOptions();
	for(WebElement s:sop) {
		System.out.println(s.getText());
	}
	single.selectByIndex(1);
}

@Test(description="scroll",enabled=true,priority=9)
public void scrolling() throws InterruptedException {
	driver.findElement(By.xpath("//button[@class='accordion-button collapsed' and @data-bs-target='#collapseFour']")).click();
	driver.findElement(By.xpath("//a[@href='scroll-top.php']")).click();
	
	JavascriptExecutor script = (JavascriptExecutor)driver;
	WebElement bottom=driver.findElement(By.id("btn-back-to-top"));
	//script.executeScript("arguments[0].scrollIntoView(true);",bottom );
	script.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	Thread.sleep(3000);
	//script.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
	//script.executeScript("window.scrollTo(0,0)","");
	
	//scroll using actions
	Actions act = new Actions(driver);
	act.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).build().perform();
	act.keyUp(Keys.CONTROL);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("//a[@href='horizontal-scroll.php']")).click();
	script.executeScript("window.scrollTo(100,0)","");
	System.out.println("horizonatl scrol done");
	
	
	
}

	 @Test (description="Keyboardactions" , enabled=true,priority=10)
	 public void keyboardactions() {
		WebElement name= driver.findElement(By.cssSelector("input#name"));
		name.sendKeys("JAGATH");    // tag & id = tag#id
		
		name.sendKeys(Keys.CONTROL,"a");
		name.sendKeys(Keys.CONTROL,"c");
		
		WebElement mail=driver.findElement(By.cssSelector("input[type='email']"));
		mail.sendKeys("jahath@gmail.com"); 
		
		WebElement text=driver.findElement(By.xpath("//textarea[@id='picture']"));
		text.sendKeys(Keys.CONTROL,"v");
		
		WebElement mobile = driver.findElement(By.id("mobile"));
		
		Actions act = new Actions(driver);
		act.moveToElement(name).doubleClick().perform();
		act.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).build().perform();
		//act.keyUp(Keys.CONTROL);
		
		act.moveToElement(mobile).click().perform();
		act.keyDown(Keys.CONTROL).sendKeys("v").build().perform();
		
		
		
		
		
	 }
	  
	  
	  
	  
	  
	  
	  
	
	
	@AfterMethod(enabled=true)
	public void teardown() {
		driver.quit();
	}
	
}

