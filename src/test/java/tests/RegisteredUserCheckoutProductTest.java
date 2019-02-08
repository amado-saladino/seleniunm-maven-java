package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;
import pages.UserRegistrationPage;
import utilities.Helper;

public class RegisteredUserCheckoutProductTest extends TestBase
{
	HomePage homePage ; 
	UserRegistrationPage registerPage ; 
	LoginPage loginPage ; 
	String productName = "Apple MacBook Pro 13-inch"; 
	SearchPage searchPage ; 
	ProductDetailsPage detailsPage ;
	ShoppingCartPage shoppingCartPage ; 
	CheckoutPage checkoutPage ; 
	OrderDetailsPage orderPage ;
	private String randomEmail; 

	@Test(priority=1,alwaysRun=true,dataProvider="ExcelDataProvider")
	public void UserCanRegisterSuccssfully(String firstName,String lastName
			,String password,String day,String month,String year) 
	{
		randomEmail = "any_random_mail" + Helper.generateRandomNumber() + "@server.com";
		
		homePage = new HomePage(driver); 
		homePage.openRegistrationPage();
		registerPage = new UserRegistrationPage(driver); 
		registerPage.userRegistration(firstName, lastName, randomEmail, password, day, month, year);
		Assert.assertTrue(registerPage.successMessage.getText().contains("Your registration completed"));
	}

	@Test(priority=2)
	public void UserCanSearchWithAutoSuggest() 
	{
		try {
			searchPage = new SearchPage(driver); 
			searchPage.ProductSearchUsingAutoSuggest("MacB");
			detailsPage = new ProductDetailsPage(driver); 
			Assert.assertEquals(detailsPage.productNamebreadCrumb.getText(), productName);
		} catch (Exception e) {
			System.out.println("Error occurred  " + e.getMessage());
		} catch (Error error) {
			System.out.println("Error occurred  " + error.getMessage());
		}
	}

	@Test(priority=3)
	public void UserCanAddProductToShoppingCart() {
		detailsPage = new ProductDetailsPage(driver);
		detailsPage.AddToCart();
		detailsPage.gotoShoppingCart();
		
		shoppingCartPage = new ShoppingCartPage(driver);
		Assert.assertTrue(shoppingCartPage.totalLbl.getText().contains("3,600"));
	}

	@Test(priority=4)
	public void UserCanCheckoutProduct() {
		checkoutPage = new CheckoutPage(driver);
		shoppingCartPage.openCheckoutPage();
		
		checkoutPage.fillAddress("Egypt", "test address", "123456", "32445566677", "Cairo");
		checkoutPage.FillOrderOptions(0, "Money");
		
		Assert.assertTrue(checkoutPage.prodcutName.isDisplayed());
		Assert.assertTrue(checkoutPage.prodcutName.getText().contains(productName));
		
		checkoutPage.confirmOrder();
		Assert.assertTrue(checkoutPage.ThankYoulbl.isDisplayed());
		checkoutPage.viewOrderDetails();
		Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
		
		orderPage = new OrderDetailsPage(driver); 
		orderPage.DownloadPDFInvoice();
		orderPage.PrintOrderDetails();
	}

	@Test(priority=5)
	public void RegisteredUserCanLogout() 
	{
		registerPage.userLogout();
	}
}
