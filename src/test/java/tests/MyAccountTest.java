package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.UserRegistrationPage;
import utilities.Helper;

public class MyAccountTest extends TestBase
{
	HomePage homePage; 
	UserRegistrationPage registerPage; 
	LoginPage loginPage; 
	MyAccountPage myAccountPage; 
	String oldPassword = "12345678" ; 
	String newPassword = "123456" ; 
	String firstName = "Ahmed" ; 
	String lastName = "Salah" ; 
	private String randomEmail; 
	

	@Test(priority=1,dataProvider="ExcelDataProvider")
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
	
	//You can use priority or 'dependsOnMethods'
	@Test(/*priority=2*/dependsOnMethods ={"UserCanRegisterSuccssfully"})
	public void RegisteredUserCanChangePassword() 
	{
		myAccountPage = new MyAccountPage(driver);		
		registerPage.openMyAccountPage();
		myAccountPage.openChangePasswordPage();
		myAccountPage.ChangePassword(oldPassword, newPassword);
		Assert.assertTrue(myAccountPage.resultLbl.getText().contains("Password was changed"));		
	}
	
	@Test(priority=3)
	public void RegisteredUserCanLogout() 
	{
		registerPage.userLogout();
	}
	
	@Test(priority=4)
	public void RegisteredUserCanLogin() 
	{
		homePage.openLoginPage();
		loginPage = new LoginPage(driver); 
		loginPage.UserLogin(randomEmail, newPassword);
		Assert.assertTrue(registerPage.logoutLink.getText().contains("Log out"));
	}
	
	@Test(priority=5)
	public void UserLogout() 
	{
		registerPage.userLogout();
	}
	
}
