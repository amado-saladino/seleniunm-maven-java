package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.WishlistPage;

public class AddProductToWishListTest extends TestBase
{
	SearchPage searchPage;
	ProductDetailsPage productDetails;
	WishlistPage wishlistPage;
	String productName = "Apple MacBook Pro 13-inch";

	@Test(priority=1)
	public void UserCanSearchForProductsWithAutoSuggest(){
		searchPage = new SearchPage(driver);
		searchPage.ProductSearchUsingAutoSuggest("MacB");
		productDetails = new ProductDetailsPage(driver);
		Assert.assertTrue(productDetails.productNamebreadCrumb.getText().contains(productName));
	}

	@Test(priority=2)
	public void UserCanAddProductToWishlist() {
		
		productDetails.AddProductToWishlist();		
		wishlistPage = new WishlistPage(driver); 
		Assert.assertTrue(wishlistPage.wishlistHeader.isDisplayed());
		Assert.assertTrue(wishlistPage.productCell.getText().contains(productName));
	}

	@Test(priority=3)
	public void UserCanRemoveProductFromCart() {
		wishlistPage = new WishlistPage(driver); 
		wishlistPage.removeProductFromWishlist();
		Assert.assertTrue(wishlistPage.EmptyCartLbl.getText().contains("The wishlist is empty!"));
	}

}
