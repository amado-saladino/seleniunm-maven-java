package tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;
import utilities.Helper;

public class UserRegistrationTest extends TestBase
{
	HomePage homePage; 
	UserRegistrationPage registerPage; 
	LoginPage loginPage;
	String randomEmail;
	String password;

	
	@Test(priority=1,alwaysRun=true,dataProvider="ExcelDataProvider")
	public void UserCanRegisterSuccssfully(String firstName,String lastName
			,String password,String day,String month,String year) 
	{
		randomEmail = "any_random_mail" + Helper.generateRandomNumber() + "@server.com";
		this.password=password;
		
		homePage = new HomePage(driver); 
		homePage.openRegistrationPage();
		registerPage = new UserRegistrationPage(driver); 
		registerPage.userRegistration(firstName, lastName, randomEmail, password, day, month, year);		
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
