package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ComparePage extends PageBase
{

	public ComparePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "a.clear-list")
    private WebElement clearListLink;
	
    @FindBy(css = "div.no-data")
    public WebElement noDataLbl;
    
    @FindBy(css = "table.compare-products-table")
    private WebElement compareTable;
    
    @FindBy(tagName = "tr")
    public List<WebElement> allRows;
    
    @FindBy(tagName = "td")
    public List<WebElement> allCol;
    
    @FindBy(linkText="Asus N551JK-XO076H Laptop")
    public WebElement firstProductName;
    
    @FindBy(linkText="Apple MacBook Pro 13-inch")
    public WebElement secondProductName ; 
   
    public void clearCompareList() 
    {
    	clickButton(clearListLink);
    }
    
    public void CompareProducts() 
    {
    	System.out.println(allRows.size());
    	
    	for(WebElement col : allCol) 
		{
			System.out.println(col.getText()+"\t");
		}
    	
    	
    	/*for(WebElement row : allRows) 
    	{
    		System.out.println(row.getText() + "\t");
    		
    	}*/
    }
   
}
