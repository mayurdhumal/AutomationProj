/*To store all locators*/
package javaFiles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FacebookLoginPage {

	WebDriver driver;
	
	public void LoginPage(WebDriver localDriver){
		
		this.driver = localDriver;
	}
	
	@FindBy(how= How.XPATH, using ="//input[@id='email']")
	//@CacheLookup
	WebElement username;
	
	@FindBy(how= How.XPATH, using= "//input[@id='pass']")
	//@CacheLookup
	WebElement password;
	
	@FindBy(how= How.XPATH, using= "//label[@id='loginbutton']")
	//@CacheLookup
	WebElement submit_button;

	
	public void login_fb(String uid, String pass) throws Exception{
		
		username.sendKeys(uid);
		Thread.sleep(2000);
		password.sendKeys(pass);
		Thread.sleep(2000);
		submit_button.click();
	}
}
