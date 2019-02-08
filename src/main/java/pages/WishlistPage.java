package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishlistPage extends PageBase
{
	public WishlistPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "td.product")
    public WebElement productCell;
    
    @FindBy(css = "h1")
    public WebElement wishlistHeader;
   
    @FindBy(name = "updatecart")
    private WebElement updateWishlistBtn;
    
    @FindBy(name = "removefromcart")
    private WebElement removefromCartCheck;
   
    @FindBy(css = "div.no-data")
    public WebElement EmptyCartLbl;
   
    public void removeProductFromWishlist() {
        clickButton(removefromCartCheck);
        clickButton(updateWishlistBtn);
    }
}
