package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

	protected WebDriver driver; 
	protected JavascriptExecutor jsRunner;
	
	public Actions action;

	public Page(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
		jsRunner = (JavascriptExecutor) driver;
		action = new Actions(driver);
	}
	
	@FindBy(linkText="Computers")
	WebElement ComputerMenu;
	
	@FindBy(linkText="Notebooks")
	WebElement NotbooksMenu;
	
	@FindBy(id="customerCurrency")
	public WebElement comboChangeCurrency;
	
	
	public WebElement getElement(By selector)
	{
		Wait<WebDriver> wait = 
				new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);
		
		return wait.until(
				webdriver-> {
					return webdriver.findElement(selector);
				}
				);
		
	}
	
	protected Boolean waitForPageLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver webdriver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState").equals("complete");				
			}
			
		};
		
		return wait.until(jsLoad);
	}
	
	void waitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10); 
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	protected void clickButton(WebElement button) 
	{
		button.click();
	}
	
	protected void setTextElementText(WebElement textElement , String value) 
	{
		textElement.sendKeys(value);
	}
	
	protected void selectComboItemByText(WebElement comboElement, String textValue){		
		new Select(comboElement).selectByVisibleText(textValue);		
	}
	
	public WebElement getComboSelectedItem(WebElement comboBox) {		
		return new Select(comboBox).getFirstSelectedOption();
	}
	
	protected void scrollToBottom()	
	{
		jsRunner.executeScript("scrollTo(0, document.body.scrollHeight)");
	}
	
	public void clearText(WebElement element) 
	{
		element.clear();
	}
	
}
