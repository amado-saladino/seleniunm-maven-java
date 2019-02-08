package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;

public class ProductHoverMenuTest extends TestBase
{
	HomePage homePage ; 
	
	@Test
	public void UserCanSelectSubCategoryFromMainMenu() 
	{
		homePage = new HomePage(driver); 
		homePage.selectNotebooksMenu();
		Assert.assertTrue(driver.getCurrentUrl().contains("notebooks"));
	}
}
