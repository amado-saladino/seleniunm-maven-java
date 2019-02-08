package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.ProductReviewPage;
import pages.SearchPage;
import pages.UserRegistrationPage;
import utilities.Helper;

public class AddProductReviewTest extends TestBase
{
	HomePage homePage ; 
	UserRegistrationPage registerPage ; 
	LoginPage loginPage ; 
	String productName = "Apple MacBook Pro 13-inch"; 
	SearchPage searchObject ; 
	ProductDetailsPage detailsPage ;
	ProductReviewPage reviewPage ;
	String randomEmail;

	
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
		
		UserCanSearchWithAutoSuggest();
		RegisteredUserCanReviewProduct();
		RegisteredUserCanLogout();
	}

	
	@Test(priority=2,enabled=false)
	public void UserCanSearchWithAutoSuggest() 
	{
		try {
			searchObject = new SearchPage(driver); 
			searchObject.ProductSearchUsingAutoSuggest("MacB");
			detailsPage = new ProductDetailsPage(driver); 
			Assert.assertEquals(detailsPage.productNamebreadCrumb.getText(), productName);
		} catch (Exception e) {
			System.out.println("Error occurred  " + e.getMessage());
		} catch (Error error) {
			System.out.println("Error occurred  " + error.getMessage());
		}
	}

	
	@Test(priority=3,enabled=false)
	public void RegisteredUserCanReviewProduct() 
	{
		detailsPage.openAddReviewPage();
		reviewPage = new ProductReviewPage(driver); 
		reviewPage.AddProductReview("new reivew", "the product is very good",2);
		Assert.assertTrue(reviewPage.reviewNotification.getText()
				.contains("Product review is successfully added."));
	}

	
	@Test(priority=4,enabled=false)
	public void RegisteredUserCanLogout() 
	{
		registerPage.userLogout();
	}

}
