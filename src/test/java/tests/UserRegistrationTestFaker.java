package tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTestFaker extends TestBase
{
	HomePage homePage; 
	UserRegistrationPage registerPage; 
	LoginPage loginPage;
	String randomEmail;
	String password;

	
	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccssfully() 
	{
		randomEmail = faker.getEmail();
		this.password=faker.getPassword();
		faker.generateLocalDate();
		
		homePage = new HomePage(driver); 
		homePage.openRegistrationPage();
		registerPage = new UserRegistrationPage(driver); 
		registerPage.userRegistration(faker.getMaleFirstName(), faker.getMaleLastName(), randomEmail, password, faker.getDayOfMonth(), faker.getMonth(), faker.getYear());		
		Assert.assertTrue(registerPage.successMessage.getText().contains("Your registration completed"));
		
		RegisteredUserCanLogout();
		RegisteredUserCanLogin();
		RegisteredUserCanLogout();
	}
	
	@Test(dependsOnMethods= {"UserCanRegisterSuccssfully"},enabled=false)
	public void RegisteredUserCanLogout() 
	{
		registerPage.userLogout();
	}
	
	@Test(dependsOnMethods= {"RegisteredUserCanLogout"},enabled=false)
	public void RegisteredUserCanLogin() 
	{
		homePage.openLoginPage();
		loginPage = new LoginPage(driver); 
		loginPage.UserLogin(randomEmail, password);		
		Assert.assertTrue(registerPage.logoutLink.isDisplayed());
	}
}
