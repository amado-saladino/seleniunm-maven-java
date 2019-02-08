package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import pages.*;
import tests.TestBase;

public class E2ETests extends TestBase {
	
	SearchPage searchPage;
	ProductDetailsPage productDetails;
	ShoppingCartPage shoppingCartpage;
	CheckoutPage checkoutPage;
	OrderDetailsPage orderDetailsPage;
	String productName = "Apple MacBook Pro 13-inch";

	@Given("^user is on Home Page$")
	public void user_is_on_Home_Page() {
		Assert.assertTrue(driver.getCurrentUrl().contains("demo.nopcommerce.com"));
	}

	@When("^he search for \"([^\"]*)\"$")
	public void he_search_for(String productName) {
		searchPage = new SearchPage(driver);
		searchPage.ProductSearchUsingAutoSuggest(productName);
		productDetails = new ProductDetailsPage(driver);
		Assert.assertTrue(productDetails.productNamebreadCrumb.getText().contains(productName));
	}

	@When("^choose to buy Two items$")
	public void choose_to_buy_Two_items() {
		
		shoppingCartpage = new ShoppingCartPage(driver);
		productDetails.AddToCart();
		productDetails.gotoShoppingCart();
	}

	@When("^moves to checkout cart and enter personal details on checkout page and place the order$")
	public void moves_to_checkout_cart_and_enter_personal_details_on_checkout_page_and_place_the_order() {
		
		checkoutPage = new CheckoutPage(driver);
		shoppingCartpage.openCheckoutPageAsGuest();
		
		checkoutPage.fillPersonalData("test", "user", "testuser1@test.com");
		checkoutPage.fillAddress("Egypt", "test address", "123456", "32445566677", "Cairo");
		checkoutPage.FillOrderOptions(1, "Money");
		
		Assert.assertTrue(checkoutPage.prodcutName.isDisplayed());
		Assert.assertTrue(checkoutPage.prodcutName.getText().contains(productName));
		checkoutPage.confirmOrder();
		Assert.assertTrue(checkoutPage.ThankYoulbl.isDisplayed());
		
	}

	@Then("^he can view the order and download the invoice$")
	public void he_can_view_the_order_and_download_the_invoice() {
		
		orderDetailsPage = new OrderDetailsPage(driver);
		checkoutPage.viewOrderDetails();
		Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
		orderDetailsPage.DownloadPDFInvoice();		
		orderDetailsPage.PrintOrderDetails();
	}
}
