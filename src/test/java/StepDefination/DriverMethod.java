package StepDefination;

import org.apache.commons.configuration.XMLConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverMethod {
private static WebDriver driver;
    
    private DriverMethod(){}
	public static WebDriver getdriver()
	{
	
		try{
        	XMLConfiguration config = new XMLConfiguration("configurations/object-config.xml");
        	String browserType = config.getString("browser");
        	
        	if(browserType != null && browserType.equalsIgnoreCase("chrome"))
        	{
        		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        		driver = new ChromeDriver();
        	}
        	
        	else{
        		driver = new FirefoxDriver();
        	}   
    		driver.manage().window().maximize();
		}catch(Exception e){
            throw new RuntimeException("Exception occured in creating driver object");
      }
    return driver;
	}
	
	public static void quit()
    {
    	if(!(driver==null || (driver != null && driver.toString().contains("null")))){
    		driver.quit();
    	}
    }
}
