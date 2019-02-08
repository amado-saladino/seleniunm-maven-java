package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CheckoutPage;
import pages.HomePage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;

public class GuestCheckoutProductFromCartTest extends TestBase {
	SearchPage searchPage;
	HomePage homePage;
	ProductDetailsPage productDetails;
	ShoppingCartPage cartPage;
	CheckoutPage checkoutPage;
	OrderDetailsPage orderPage;
	String productName = "Apple MacBook Pro 13-inch";

	@Test(priority=1)
	public void UserCanSearchForProductsWithAutoSuggest() {
		searchPage = new SearchPage(driver);
		searchPage.ProductSearchUsingAutoSuggest("MacB");
		productDetails = new ProductDetailsPage(driver);
		Assert.assertTrue(productDetails.productNamebreadCrumb.getText().contains(productName));
	}

	@Test(priority=2)
	public void UserCanAddProductToShoppingCart() {
		cartPage = new ShoppingCartPage(driver);
		productDetails.AddToCart();
		productDetails.gotoShoppingCart();
			
		cartPage = new ShoppingCartPage(driver);
		Assert.assertTrue(cartPage.totalLbl.getText().contains("3,600"));	
	}

	@Test(priority=3)
	public void UserCanCheckoutProduct() {
		checkoutPage = new CheckoutPage(driver);
		cartPage.openCheckoutPageAsGuest();
				
		checkoutPage.fillPersonalData("test", "user", "testuser1@test.com");
		checkoutPage.fillAddress("Egypt", "test address", "123456", "32445566677", "Cairo");
		checkoutPage.FillOrderOptions(1, "Money");
		
		Assert.assertTrue(checkoutPage.prodcutName.isDisplayed());
		Assert.assertTrue(checkoutPage.prodcutName.getText().contains(productName));
		checkoutPage.confirmOrder();
		Assert.assertTrue(checkoutPage.ThankYoulbl.isDisplayed());
	}

	@Test(priority=4)
	public void UserCanViewOrderDetails() {
		
		orderPage = new OrderDetailsPage(driver);
		checkoutPage.viewOrderDetails();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
		orderPage.DownloadPDFInvoice();		
		orderPage.PrintOrderDetails();
	}

}
