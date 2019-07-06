package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends PageBase {
	public ProductDetailsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "strong.current-item")
	public WebElement productNamebreadCrumb;

	@FindBy(css = "input.button-2.email-a-friend-button")
	WebElement buttonEmailFriend;

	@FindBy(css = "span.price-value-4")
	public WebElement labelProductPrice;

	@FindBy(id = "add-to-wishlist-button-4")
	WebElement buttonAddToWishlist;

	@FindBy(linkText = "Add your review")
	WebElement addReviewLink;

	@FindBy(css = "input.button-2.add-to-compare-list-button")
	WebElement addToCompareBtn;

	@FindBy(id = "add-to-cart-button-4")
	WebElement addToCartBtn;

	public String getSelectedCurrency() {
		return getComboSelectedItem(comboChangeCurrency).getText();
	}

	public boolean isAtProductPage(String productName) {
		return productNamebreadCrumb.isDisplayed() &&
		productNamebreadCrumb.getText().equals(productName);
	} 

	public void openSendEmail() {
		clickButton(buttonEmailFriend);
	}

	public void openAddReviewPage() {
		clickButton(addReviewLink);
	}

	public void AddProductToWishlist() {
		clickButton(buttonAddToWishlist);
		WebElement element = getElement(By.linkText("wishlist"));		
		clickButton(element);
	}

	public void AddProductToCompare() {
		clickButton(addToCompareBtn);		
	}
	
	public void gotoComparePage() {
		WebElement element = getElement(By.linkText("product comparison"));		
		clickButton(element);
	}

	public void AddToCart() {
		clickButton(addToCartBtn);
	}
	
	public void gotoShoppingCart(){
		WebElement element = getElement(By.linkText("shopping cart"));		
		clickButton(element);
	}

}
