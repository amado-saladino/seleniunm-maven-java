package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends PageBase 
{
	public SearchPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id="small-searchterms")
	WebElement searchTextBox ; 

	@FindBy(css="input.button-1.search-box-button")
	WebElement searchBtn ; 

	@FindBy(id="ui-id-1")
	WebElement ProductList ;
	
	//@FindBy(linkText="Apple MacBook Pro 13-inch")
	@FindBy(css="h2 > a")  //this one is generic
	WebElement productTitle;

	public void ProductSearch(String productName) 
	{
		setTextElementText(searchTextBox, productName);
		clickButton(searchBtn);
	}

	public void OpenProductDetailsPage() 
	{
		clickButton(productTitle);
	}
	
	
	public void OpenProductDetailsPage(String locator) 
	{
		WebElement productLink = getElement(By.linkText(locator)); 
		clickButton(productLink);
	}
	

	public void ProductSearchUsingAutoSuggest(String searchTxt) 
	{
		setTextElementText(searchTextBox, searchTxt);		
		WebElement selectedProduct = getElement(By.cssSelector("li.ui-menu-item"));
		clickButton(selectedProduct);	
	}

}
