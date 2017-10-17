package javaFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Utility {

	static XSSFWorkbook wb;
	static XSSFSheet sheet1;
	WebDriver driver;
	static String data;
	ExtentHtmlReporter htmlreporter;
	ExtentReports report;
	ExtentTest reporter;
	String screenshot_path;
	String image;

	public static String[][] ExcelDataConfig(String excelpath) throws Exception {
		String[][] tabArray = null;
		try {
			
			FileInputStream fis = new FileInputStream("F:\\JavaProj\\Automation\\src\\scripts\\InputData.xlsx");
			wb = new XSSFWorkbook(fis);
			int startRow = 1;
			int startCol = 0;
			int totalCols = 1;
			int totalRows = 0;
			int ci,cj;
			totalRows = sheet1.getLastRowNum();
			tabArray = new String[totalRows][totalCols];
			
			ci=0;
			for (int i=startRow;i<=totalRows;i++, ci++) {           	   

			cj=0;

				for (int j=startCol;j<=totalCols;j++, cj++){

					tabArray[ci][cj]=getCellData(i,j);

					System.out.println(tabArray[ci][cj]);  

				}

			}		
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return tabArray;

	}

	public static String getCellData(int RowNum, int ColNum){

		try {
			data = sheet1.getRow(RowNum).getCell(ColNum).getStringCellValue(); 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	public static WebElement isElementPresnt(WebDriver driver,String xpath,int time) {

		WebElement ele = null;

		for(int i=0;i<time;i++) {
			try{
				ele=driver.findElement(By.xpath(xpath));
				break;
			}
			catch(Exception e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					System.out.println("Waiting for element to appear on DOM");
				}
			}
		}
		return ele;
	}
	
	public static void captureScreenshot(WebDriver driver,String screenshotName) {

		try {

			TakesScreenshot ts=(TakesScreenshot)driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./Screenshots/"+screenshotName+".png"));
			System.out.println("Screenshot taken");
		} 
		catch (Exception e) {
			System.out.println("Exception while taking screenshot "+e.getMessage());
		} 
	}
	
	@Test(testName = "prepare report test")
	public void verifyBlogTitle() {

		htmlreporter = new ExtentHtmlReporter("F:\\src\\Reports\\htmlReport.html");
		htmlreporter.getStartTime();
		
		report = new ExtentReports();

		reporter = report.createTest("VerifyBlogTitle");

		reporter.log(Status.INFO, "Browser started ");

		driver.get("http://www.learn-automation.com");

		reporter.log(Status.INFO, "Application is up and running");

		String title=driver.getTitle();

		Assert.assertTrue(title.contains("Google")); 

		reporter.log(Status.PASS, "Title verified");
	}


	@AfterMethod
	public void tearDown(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{

			captureScreenshot(driver, result.getTestName());
			reporter.log(Status.FAIL, "Title verification");

		}

		report.removeTest(reporter);
		report.flush();

		driver.get("F:\\src\\Reports\\htmlReport.html");
	}

}
