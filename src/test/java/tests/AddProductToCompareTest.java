package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ComparePage;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchPage;

public class AddProductToCompareTest extends TestBase
{
    String firstProductName = "Apple MacBook Pro 13-inch";
    String secondProductName = "Asus N551JK-XO076H Laptop"; 

	ProductDetailsPage detailsPage ; 
	HomePage homePage ; 
	ComparePage comparePage ; 
	SearchPage searchPage ; 

	@Test(priority = 1)
	public void UserCanCompareProducts() {
		searchPage = new SearchPage(driver);
		detailsPage = new ProductDetailsPage(driver);
		comparePage = new ComparePage(driver);

		searchPage.ProductSearchUsingAutoSuggest("MacB");
		Assert.assertTrue(detailsPage.productNamebreadCrumb.getText().contains(firstProductName));
		detailsPage.AddProductToCompare();

		searchPage.ProductSearchUsingAutoSuggest("Asus");
		Assert.assertTrue(detailsPage.productNamebreadCrumb.getText().contains(secondProductName));
		detailsPage.AddProductToCompare();
		detailsPage.gotoComparePage();
				
		comparePage.CompareProducts();
		Assert.assertTrue(comparePage.firstProductName.getText().equals("Asus N551JK-XO076H Laptop"));
		Assert.assertTrue(comparePage.secondProductName.getText().equals("Apple MacBook Pro 13-inch"));			
	}

	@Test(priority=2)
	public void UserCanClearCompareList() {
		comparePage.clearCompareList();
		Assert.assertTrue(comparePage.noDataLbl.getText().contains("You have no items to compare."));
	}
}
