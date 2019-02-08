package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;

public class SearchProductTest extends TestBase
{
	String productName = "Apple MacBook Pro 13-inch"; 
	SearchPage searchPage ; 
	ProductDetailsPage detailsPage ; 
	
	@Test
	public void UserCanSearchForProducts() 
	{
		searchPage  = new SearchPage(driver); 
		detailsPage = new ProductDetailsPage(driver); 
		searchPage.ProductSearch(productName);
		searchPage.OpenProductDetailsPage(productName);
		
		//The commented code below is the original one
		//searchPage.OpenProductDetailsPage();
		//Assert.assertEquals(detailsPage.productNamebreadCrumb.getText(), productName);
		Assert.assertTrue(detailsPage.productNamebreadCrumb.getText().contains(productName));
	}

}
