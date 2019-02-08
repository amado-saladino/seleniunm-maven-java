package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.EmailPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.UserRegistrationPage;

public class EmailFriendTestParallel extends TestBaseRemote
{
	HomePage homePage ; 
	UserRegistrationPage registerPage ; 
	LoginPage loginPage ; 
	String productName = "Apple MacBook Pro 13-inch"; 
	SearchPage searchPage ; 
	ProductDetailsPage detailsPage ;
	EmailPage emailPage ;
	String randomEmail;
	String password;

	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccssfully() 
	{
		randomEmail = faker.getEmail();
		this.password=faker.getPassword();
		faker.generateLocalDate();
		
		homePage = new HomePage(getDriver()); 
		homePage.openRegistrationPage();
		registerPage = new UserRegistrationPage(getDriver());
		registerPage.userRegistration(faker.getMaleFirstName(), faker.getMaleLastName(), randomEmail, password, faker.getDayOfMonth(), faker.getMonth(), faker.getYear());		
		Assert.assertTrue(registerPage.successMessage.getText().contains("Your registration completed"));
	}
	
	@Test(priority=2)
	public void UserCanSearchWithAutoSuggest() 
	{
		try {
			searchPage = new SearchPage(getDriver()); 
			searchPage.ProductSearchUsingAutoSuggest("MacB");
			detailsPage = new ProductDetailsPage(getDriver()); 
			Assert.assertEquals(detailsPage.productNamebreadCrumb.getText(), productName);
		} catch (Exception e) {
			System.out.println("Error occurred  " + e.getMessage());
		}
	}
	
	
	@Test(priority=3)
	public void RegisteredUserCanSendProductToFriend() 
	{
		detailsPage.openSendEmail();
		emailPage = new EmailPage(getDriver()); 
		emailPage.SendEmailToFriend("aaa@tte.com", "Hello my friend , check this product");
		Assert.assertTrue(emailPage.messageNotification.getText().contains("Your message has been sent."));
	}
	
	
	@Test(priority=4)
	public void RegisteredUserCanLogout() 
	{
		registerPage.userLogout();
	}
	
}
