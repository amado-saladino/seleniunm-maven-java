package tests;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import utilities.ExcelReader;
import utilities.Faker;
import utilities.Helper;
import utilities.PropertyReader;

public class TestBase extends AbstractTestNGCucumberTests {
	
	protected static WebDriver driver;
	protected Faker faker = new Faker();
	static PropertyReader propertyReader = new PropertyReader("Config\\env.properties");

	private static String downloadPath = System.getProperty("user.dir") + "\\Downloads";

	private static FirefoxOptions firefoxOption() {
		
		System.setProperty("webdriver.gecko.driver", propertyReader.getProperty("GECKO_DRIVER"));
		FirefoxOptions option = new FirefoxOptions();
		option.addPreference("browser.download.folderList", 2);
		option.addPreference("browser.download.dir", downloadPath);
		option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
		option.addPreference("browser.download.manager.showWhenStarting", false);
		option.addPreference("pdfjs.disabled", true);
		return option;
	}

	private static ChromeOptions chromeOption() {
		
		System.setProperty("webdriver.chrome.driver", propertyReader.getProperty("CHROME_DRIVER"));
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default.content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		options.setExperimentalOption("prefs", chromePrefs);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.addArguments("--disable-infobars");
		return options;
	}

	@BeforeSuite
	@Parameters({"browser"})
	public void startDriver(@Optional("chrome") String browserName) 
	{
		PropertyReader propertyReader = new PropertyReader("Config\\env.properties");
		browserName = browserName.toLowerCase();
		
		switch (browserName) 
		{
		case "chrome":			
			driver = new ChromeDriver(chromeOption());
			break;
		case "firefox":			
			driver = new FirefoxDriver(firefoxOption());
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", propertyReader.getProperty("IE_DRIVER"));
			driver = new InternetExplorerDriver(); 
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		case "phantomjs":
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, propertyReader.getProperty("PHANTOMJS_DRIVER"));			
			String[] phantomJsArgs = {"--web-security=no","--ignore-ssl-errors=yes"};
			capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS, phantomJsArgs);
			driver = new PhantomJSDriver(capabilities);
			break;
		case "chrome-headless":			
			ChromeOptions options = chromeOption();			
			options.setHeadless(true);			
			//options.addArguments("headless");
			options.addArguments("window-size=1920,1080");
			driver = new ChromeDriver(options);
			break;
		case "firefox-headless":
			
			FirefoxOptions firefoxOptions = firefoxOption();
			firefoxOptions.setHeadless(true);
			driver = new FirefoxDriver(firefoxOptions);
			break;
			default:
				System.out.println("Invalid browser option");
				System.exit(1);				
		}
		
		
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.navigate().to(propertyReader.getProperty("BASE_URL"));
	}

	@AfterSuite
	public void stopDriver() {
		driver.quit();
	}

	// take screenshot when test case fail and add it in the Screenshot folder
	@AfterMethod
	public void screenshotOnFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("Failed!");
			System.out.println("Taking Screenshot....");
			Helper.captureScreenshot(driver, result.getName());
		}
	}

	@DataProvider(name = "ExcelDataProvider")
	public Object[][] getDatainSheet(ITestNGMethod testMethod) {

		return ExcelReader.loadTestData("data\\TestData.xlsx", testMethod.getMethodName());
	}

}
