package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ContactUsPage;
import pages.HomePage;

public class ContactUsTestParallel extends TestBaseRemote
{
	HomePage homePage;
	ContactUsPage contactPage;

	String email = "test@test.com";
	String fullName = "Test User";
	String enquiry = "Hello Admin , this is for test";

	@Test
	public void UserCanUseContactUs() {
		homePage = new HomePage(getDriver());
		homePage.openContactUsPage();
		contactPage = new ContactUsPage(getDriver());
		contactPage.ContactUs(fullName, email, enquiry);
		
		 Assert.assertTrue(contactPage.successMessage.getText()
				 .contains("Your enquiry has been successfully sent to the store owner."));
	}

}
