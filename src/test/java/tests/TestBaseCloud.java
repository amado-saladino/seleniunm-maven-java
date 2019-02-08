package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.Faker;
import utilities.Helper;
import utilities.PropertyReader;

public class TestBaseCloud {

	protected ThreadLocal<RemoteWebDriver> driver = null; 
	protected Faker faker = new Faker();
	PropertyReader propertyReader;

	@BeforeClass
	@Parameters({"browser","platform"})
	void setup(@Optional("chrome") String browserName, @Optional("WINDOWS") String platform) {

		driver = new ThreadLocal<>();
		propertyReader = new PropertyReader("Config\\env.properties"); 
		DesiredCapabilities capabilities = new DesiredCapabilities();		
			
		capabilities.setBrowserName(browserName);
		capabilities.setCapability("platform", platform);
		capabilities.setCapability("browserstack.debug", true);

		try {
			driver.set(new RemoteWebDriver(new URL(getURL()), capabilities));			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		getDriver().manage().window().maximize();
		getDriver().navigate().to(propertyReader.getProperty("BASE_URL"));
	}

	private String getURL() {
		
		return "https://" 
		+ propertyReader.getProperty("BROWSER_STACK_USERNAME") 
		+ ":" + propertyReader.getProperty("BROWSER_STACK_KEY")
		+ "@" + propertyReader.getProperty("BROWSER_STACK_HUB");
	}

	@AfterClass
	void teardown() {
		
		getDriver().quit();
		driver.remove();		
	}
	

	@AfterMethod
	public void screenshotOnFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("Failed!");
			System.out.println("Taking Screenshot....");
			Helper.captureScreenshot(getDriver(), result.getName());
		}
	}

	
	public WebDriver getDriver() 
	{
		return driver.get();
	}


}
