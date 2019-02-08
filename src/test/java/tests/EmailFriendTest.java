package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.EmailPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.UserRegistrationPage;
import utilities.Helper;

public class EmailFriendTest extends TestBase
{
	HomePage homePage ; 
	UserRegistrationPage registerPage ; 
	LoginPage loginPage ; 
	String productName = "Apple MacBook Pro 13-inch"; 
	SearchPage searchPage ; 
	ProductDetailsPage detailsPage ;
	EmailPage emailPage ;
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
		RegisteredUserCanSendProductToFriend();
		RegisteredUserCanLogout();
	}
	
	
	@Test(priority=2,enabled=false)
	public void UserCanSearchWithAutoSuggest() 
	{
		try {
			searchPage = new SearchPage(driver); 
			searchPage.ProductSearchUsingAutoSuggest("MacB");
			detailsPage = new ProductDetailsPage(driver); 
			Assert.assertEquals(detailsPage.productNamebreadCrumb.getText(), productName);
		} catch (Exception e) {
			System.out.println("Error occurred  " + e.getMessage());
		}
	}
	
	
	@Test(priority=3,enabled=false)
	public void RegisteredUserCanSendProductToFriend() 
	{
		detailsPage.openSendEmail();
		emailPage = new EmailPage(driver); 
		emailPage.SendEmailToFriend("aaa@tte.com", "Hello my friend , check this product");
		Assert.assertTrue(emailPage.messageNotification.getText().contains("Your message has been sent."));
	}
	
	
	@Test(priority=4,enabled=false)
	public void RegisteredUserCanLogout() 
	{
		registerPage.userLogout();
	}
	
}
