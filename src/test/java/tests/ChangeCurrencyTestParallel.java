package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchPage;

public class ChangeCurrencyTestParallel extends TestBaseRemote
{
	HomePage homePage ; 
	ProductDetailsPage detailsPage ; 
	String productName = "Apple MacBook Pro 13-inch"; 
	SearchPage searchPage ; 

	@Test(priority=1)
	public void UserCanCanChangeCurrency() 
	{
		homePage = new HomePage(getDriver()); 
		homePage.changeCurrency();
	}
	
	@Test(priority=2)
	public void UserCanSearchWithAutoSuggest() 
	{
		try {
			searchPage = new SearchPage(getDriver()); 
			searchPage.ProductSearchUsingAutoSuggest("MacB");
			detailsPage = new ProductDetailsPage(getDriver()); 
			Assert.assertEquals(detailsPage.productNamebreadCrumb.getText(), productName);
			Assert.assertTrue(detailsPage.productPricelbl.getText().contains("€"));
			Assert.assertEquals(detailsPage.getSelectedCurrency(), "Euro");
			System.out.println(detailsPage.productPricelbl.getText());
			System.out.println(detailsPage.getSelectedCurrency());
		} catch (Exception e) {
			System.out.println("Error occurred  " + e.getMessage());
		} catch (AssertionError error) {
			System.out.println("Error Occured at Assertion point: ");
			Assert.fail(error.getMessage());
		}
		
	}
	
	
}
