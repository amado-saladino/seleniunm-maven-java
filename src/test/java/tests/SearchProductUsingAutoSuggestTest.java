package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;
import utilities.Helper;

public class SearchProductUsingAutoSuggestTest extends TestBase {
	String productName;
	SearchPage searchPage;
	ProductDetailsPage detailsPage;
	String keyword;
	
	@BeforeClass
	void setupClass() {
		productName = settingsProperties.getProperty("product_name");
		keyword = Helper.generateProductKeyword(productName);
	}

	@Test
	public void UserCanSearchWithAutoSuggest() {
		searchPage = new SearchPage(driver);
		searchPage.ProductSearchUsingAutoSuggest(keyword);
		detailsPage = new ProductDetailsPage(driver);		
		Assert.assertTrue(detailsPage.productNamebreadCrumb.getText().contains(productName));
	}
}