package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;

public class AddProductToShoppingCartTest extends TestBase 
{
	SearchPage searchPage;
	ProductDetailsPage productDetailsPage;
	ShoppingCartPage cartPage ; 
	String productName = "Apple MacBook Pro 13-inch";

	@Test(priority=1)
	public void UserCanSearchForProductsWithAutoSuggest() {
		searchPage = new SearchPage(driver);
		searchPage.ProductSearchUsingAutoSuggest("MacB");
		productDetailsPage = new ProductDetailsPage(driver);
		Assert.assertTrue(productDetailsPage.productNamebreadCrumb.getText().contains(productName));
	}

	@Test(priority=2)
	public void UserCanAddProductToShoppingCart() {
		productDetailsPage = new ProductDetailsPage(driver);
		productDetailsPage.AddToCart();
		productDetailsPage.gotoShoppingCart();
		
		cartPage = new ShoppingCartPage(driver);
		Assert.assertTrue(cartPage.totalLbl.getText().contains("3,600"));
	}

	@Test(priority=3)
	public void UserCanRemoveProductFromCart() {
		
		cartPage.RemoveProductFromCart();
	}
}
