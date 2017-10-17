//Author :- Mayur Dhumal
//TestSuite :- All the TestNG tests are based in this script
//This script demonstrates the Select class method for dropdown selection.
package scripts;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import javaFiles.FacebookLoginPage;
import javaFiles.Utility;

public class TestSuite {

	WebDriver driver;
	String child_window;
	FacebookLoginPage login_page;
	
	@Parameters({"browserName"})
	@BeforeMethod
	public void kickStart(@Optional("firefox") String browserName){
		//this will launch browser

		if (browserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Java\\jdk\\lib\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\jdk\\lib\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("IE")){
			driver = new InternetExplorerDriver();
		}

	}
	
	@Test(testName = "Check if user is valid", enabled = true)
	public void checkValidUser() throws Exception{
		//BrowserFactory.startBrowser("firefox");
		//created page object using page factory
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://www.fb.com");
		login_page = PageFactory.initElements(driver, FacebookLoginPage.class);
		//call method
		login_page.login_fb("admin", "admin");
		Assert.assertTrue(driver.getTitle().contains("Twitter"));

	}

	@Test(dataProvider = "facebookTest", enabled = true)
	public void loginToFB(String username,String password) throws Exception{
		System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Java\\jdk\\lib\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/login/");
		System.out.println("Title of the webpage: " + driver.getTitle());
		driver.findElement(By.id("email")).sendKeys(username);
		Thread.sleep(2000);
		driver.findElement(By.id("pass")).sendKeys(password);
		Thread.sleep(2000);
		driver.findElement(By.id("loginbutton")).click();
		System.out.printf("username is: " + username + "and password is: " + password);

	}
	
	@DataProvider(name="facebookTest")
	public String[][] dp(String username,String password) throws Exception {
		
		String[][] testObjArray = Utility.ExcelDataConfig("F:\\JavaProj\\Automation\\src\\scripts\\InputData.xlsx");
		
		
		return testObjArray;
	}

	@Test(testName="Dropdown Test", enabled = true)
	public void FacebookSignUpPage(){

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://www.facebook.com");
		System.out.println("Title of the webpage: " + driver.getTitle());
		//Day Dropdown actions
		WebElement day = driver.findElement(By.xpath("//select[@id='day']"));
		Select day1 = new Select(day);
		
		List<WebElement> day_dropdown = day1.getOptions();
		
		for(int i=0; i < day_dropdown.size(); i++){
			String day_values = day_dropdown.get(i).getText();
			System.out.println("dropdown values of day are " + day_values);
		}
		
		System.out.println("First selected day is: " + day1.getFirstSelectedOption().getText());
		
		day1.selectByIndex(7);
		System.out.println("Date selected is: 7"); 
		
		//Month Dropdown actions
		WebElement month = driver.findElement(By.xpath("//select[@id='month']"));
		Select month1 = new Select(month);
		
		List<WebElement> month_dropdown = month1.getOptions();
		
		for(int i=0; i < month_dropdown.size(); i++){
			String month_values = month_dropdown.get(i).getText();
			System.out.println("dropdown values of months are " + month_values);
		}
		
		System.out.println("First selected month is: " + month1.getFirstSelectedOption().getText());
		
		month1.selectByVisibleText("Nov");
		System.out.println("Month selected is: November"); 
		
		//Year Dropdown actions
		WebElement year = driver.findElement(By.xpath("//select[@id='year']"));
		Select year1 = new Select(year);
		
		List<WebElement> year_dropdown = year1.getOptions();
		
		for(int i=0; i < year_dropdown.size(); i++){
			String year_values = year_dropdown.get(i).getText();
			System.out.println("dropdown values of years are " + year_values);
		}
		
		System.out.println("First selected year is: " + year1.getFirstSelectedOption().getText());
		
		year1.selectByValue("2015");
		System.out.println("Year selected is: 2015");
	}
	
	@Test(testName="Window handles(Pop-up), Naukari page Test", enabled = true)
	public void testPopUp() throws Exception{

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://www.naukri.com");

		String parent = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();

		while(i1.hasNext()){

			child_window = i1.next();
		}

		if(!parent.equals(child_window)){

			//driver.switchTo().window(child_window);
			System.out.println(driver.switchTo().window(child_window).getTitle());
			
		}

		//driver.switchTo().window(parent).close();

	}
	
	@Test(testName = "Bootstrap dropdown test", enabled = true)
	public void Bootstrap_dropdown() throws Exception {

		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.get("http://seleniumpractise.blogspot.in/2016/08/bootstrap-dropdown-example-for-selenium.html");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='menu1']")).click();

		List<WebElement> list = driver.findElements(By.xpath("//div[@class='dropdown open']/ul/li/a"));

		for (WebElement ele : list){
			// for every elements it will print the name using innerHTML
			System.out.println("Dropdown elements are: " + ele.getText());
			break;
		}
	}
	
	@Test(testName = "Calender manage Test", enabled = true)
	public void CalenderHandling() throws Exception {
		
		driver.get("http://seleniumpractise.blogspot.in/2016/08/how-to-handle-calendar-in-selenium.html");
		driver.findElement(By.id("datepicker")).click();
		//for year 2015, January
		Thread.sleep(2000);
		for (int i=0; i<=31; i++){
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-w']")).click();
		}

		Thread.sleep(800);	
		List<WebElement> dates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td"));
		for(WebElement items : dates ){

			String date = items.getText();
			//String month = items.getText();
			//String year = items.getText();

			if (date.equalsIgnoreCase("7")){

				items.click();
				break;
			}
		}
		System.out.println("Date selected: 7 jan, 2015");
	}
	
	@AfterTest
	public void Finish(){

		driver.close();
		driver.quit();
	}
	
}


