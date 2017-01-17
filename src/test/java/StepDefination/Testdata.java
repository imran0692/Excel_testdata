package StepDefination;

import java.lang.reflect.Method;

import org.apache.commons.configuration.XMLConfiguration;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

public class Testdata {

	public static By getElement(String identifier) {
		WebElement element = null;
		XMLConfiguration config = null;
		By by=null;
		try {
			config = new XMLConfiguration("resources/testdata/object-config.xml");
			Method method = By.class.getMethod(config.getString(identifier + "[@locator]"), String.class);
			System.out.println(config.getString(identifier));
			System.out.println(config.getString(identifier+"[@locator]"));
			by = (By) method.invoke(By.class, config.getString(identifier));
			System.out.println(By.class);
			//element = DriverFactory.getDriver().findElement(by);
		} catch (Exception e) {
			
			System.err.println("Object Not found. Please verify if "+identifier+ " is configered object-config.xml");
			e.printStackTrace();
		} finally{
			config.clear();
		}
		return by;
	}
	public static String getobject_property(String idenrifer){
		
		return "s";
	}
}
