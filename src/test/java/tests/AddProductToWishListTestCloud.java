package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.WishlistPage;

public class AddProductToWishListTestCloud extends TestBaseCloud
{
	SearchPage searchPage;
	ProductDetailsPage productDetails;
	WishlistPage wishlistPage;
	String productName = "Apple MacBook Pro 13-inch";

	@Test(priority=1)
	public void UserCanSearchForProductsWithAutoSuggest(){
		
		searchPage = new SearchPage(getDriver());
		searchPage.ProductSearchUsingAutoSuggest("MacB");
		productDetails = new ProductDetailsPage(getDriver());
		Assert.assertTrue(productDetails.productNamebreadCrumb.getText().contains(productName));
	}

	@Test(priority=2)
	public void UserCanAddProductToWishlist() {
		
		productDetails.AddProductToWishlist();		
		wishlistPage = new WishlistPage(getDriver()); 
		Assert.assertTrue(wishlistPage.wishlistHeader.isDisplayed());
		Assert.assertTrue(wishlistPage.productCell.getText().contains(productName));
	}

	@Test(priority=3)
	public void UserCanRemoveProductFromCart() {
		wishlistPage = new WishlistPage(getDriver()); 
		wishlistPage.removeProductFromWishlist();
		Assert.assertTrue(wishlistPage.EmptyCartLbl.getText().contains("The wishlist is empty!"));
	}

}
