package StepDefination;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.Feature;




public class Smoke_test {
	
	static String obj_type;
	
	public static String get_fieldvalue(String fieldname){
		String field_value=Excel_data.or_properties.get(fieldname);
		String[] field_value_parts = field_value.split(":");
		obj_type=field_value_parts[0];
		return field_value_parts[1];
	}
	@Then("^I am on \"(.*?)\" page$")
	public void i_am_on_page(String pagename) throws Throwable {
		Excel_data.getproperty(pagename);
	}
	
	@Then("^I enter \"(.*?)\" in the \"(.*?)\"$")
	public void i_enter_in_the(String text, String element) throws Throwable {

		System.out.print(get_fieldvalue(element)+":");
		System.out.println(obj_type);
		
	}
	
	
	@After
	public void tearDown(Scenario scenario) {
		//below block is to connect to ALM and update the status.
		
		driver.close();
		
	}

	WebDriver driver;
	@Given("^I launch \"([^\"]*)\" browser$")
	public void i_launch_browser(String browser) throws Throwable {
		//driver=new FirefoxDriver();
		try{
		if(browser.equals("Firefox")){
			System.out.println("there");
	    	driver=new FirefoxDriver();
		}
	    else if (browser.equals("Chrome"))
	    {
	    	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	    	driver=new ChromeDriver();
	    }
	    
	    	driver.manage().window().maximize();
		}
		catch(Exception e){
			System.out.println(e);
			driver.close();
		}
	}
	@Then("^I navigate to \"([^\"]*)\" URL$")
	public void i_navigate_to_URL(String URL) throws Throwable {
		try{
	    driver.get(URL);
	
		}
		catch(Exception e){
			System.out.println(e);
			driver.close();
		}
	}

	@Given("^I select \"([^\"]*)\" from \"([^\"]*)\" dropdown$")
	public void i_select_from_dropdown(String option, String dropdown) throws Throwable {
		WebElement dr=driver.findElement(By.id(dropdown));
		Select s=new Select(dr);
		s.selectByIndex(Integer.parseInt(option));
	}
	@Then ("^I close browser$")
	public void close(){
		driver.close();
	}
	@Then("^I wait for (\\d+) Seconds$")
	public void i_wait_for_Seconds(int arg1) throws Throwable {
		TimeUnit.SECONDS.sleep(arg1);
	}
	@Then("^I click the \"(.*?)\"$")
	public void i_click_the(String xml_path) throws Throwable {
	   
		By by=Testdata.getElement(xml_path);
		System.out.println(by);
		WebElement element = driver.findElement(by);
	}
	
	
}
