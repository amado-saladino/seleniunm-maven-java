package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;

public class SearchProductUsingAutoSuggestTest extends TestBase {
	String productName = "Apple MacBook Pro 13-inch";
	SearchPage searchPage;
	ProductDetailsPage detailsPage;

	@Test
	public void UserCanSearchWithAutoSuggest() {

		searchPage = new SearchPage(driver);
		searchPage.ProductSearchUsingAutoSuggest("MacB");
		detailsPage = new ProductDetailsPage(driver);		
		Assert.assertTrue(detailsPage.productNamebreadCrumb.getText().contains(productName));
	}
}