package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;

public class ProductHoverMenuTestParallel extends TestBaseRemote
{
	HomePage homePage ; 
	
	@Test
	public void UserCanSelectSubCategoryFromMainMenu() 
	{
		homePage = new HomePage(getDriver()); 
		homePage.selectNotebooksMenu();
		Assert.assertTrue(getDriver().getCurrentUrl().contains("notebooks"));
	}
}
