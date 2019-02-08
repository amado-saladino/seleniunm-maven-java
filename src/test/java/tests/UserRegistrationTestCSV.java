package tests;


import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;
import utilities.Helper;

public class UserRegistrationTestCSV extends TestBase
{
	HomePage homePage; 
	UserRegistrationPage registerPage; 
	LoginPage loginPage;
	String randomEmail;
	String password;
	CSVReader csvReader;
	String[] csvRow;

	
	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccssfully() 
	{
		
		try {
			csvReader = new CSVReader(new FileReader("data/data.csv"));
			csvReader.skip(1);			
			
			while ((csvRow=csvReader.readNext())!= null) {
				
				randomEmail = "any_random_mail" + Helper.generateRandomNumber() + "@server.com";
				password = csvRow[2];
				
				homePage = new HomePage(driver);
				homePage.openRegistrationPage();
				registerPage = new UserRegistrationPage(driver);
				registerPage.userRegistration(csvRow[0], csvRow[1], randomEmail, csvRow[2], csvRow[3], csvRow[4], csvRow[5]);
				Assert.assertTrue(registerPage.successMessage.getText().contains("Your registration completed"));
				RegisteredUserCanLogout();
				RegisteredUserCanLogin();
				RegisteredUserCanLogout();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
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
