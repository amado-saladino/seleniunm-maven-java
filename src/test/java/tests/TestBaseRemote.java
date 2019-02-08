package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.ExcelReader;
import utilities.Faker;
import utilities.Helper;
import utilities.PropertyReader;

public class TestBaseRemote {

	protected ThreadLocal<RemoteWebDriver> driver = null; 
	protected Faker faker = new Faker();

	@BeforeClass
	@Parameters({"browser"})
	void setup(@Optional("chrome") String browserName) {

		driver = new ThreadLocal<>();
		PropertyReader propertyReader = new PropertyReader("Config\\env.properties");		
		DesiredCapabilities capabilities = new DesiredCapabilities();
	
		capabilities.setCapability("browserName", browserName);
		//capabilities.setBrowserName(browserName);		

		try {
			driver.set(new RemoteWebDriver(new URL(propertyReader.getProperty("GRID_HUB")), capabilities));			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		getDriver().manage().window().maximize();
		getDriver().navigate().to(propertyReader.getProperty("BASE_URL"));
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

	@DataProvider(name = "ExcelDataProvider")
	public Object[][] getDatainSheet(ITestNGMethod testMethod) {

		return ExcelReader.loadTestData("data\\TestData.xlsx", testMethod.getMethodName());
	}
	
	
	public WebDriver getDriver() 
	{
		return driver.get();
	}


}
