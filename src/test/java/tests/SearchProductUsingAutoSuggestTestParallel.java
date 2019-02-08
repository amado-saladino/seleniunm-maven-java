package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;

public class SearchProductUsingAutoSuggestTestParallel extends TestBaseRemote {
	String productName = "Apple MacBook Pro 13-inch";
	SearchPage searchPage;
	ProductDetailsPage detailsPage;

	@Test
	public void UserCanSearchWithAutoSuggest() {

		searchPage = new SearchPage(getDriver());
		searchPage.ProductSearchUsingAutoSuggest("MacB");
		detailsPage = new ProductDetailsPage(getDriver());		
		Assert.assertTrue(detailsPage.productNamebreadCrumb.getText().contains(productName));
	}
}