package steps;

import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.ContactUsPage;
import pages.HomePage;
import tests.TestBase;

public class ContactUs extends TestBase {

	HomePage homePage;
	ContactUsPage contactPage;
	
	@Given("^The user opens home page$")
	public void the_user_opens_home_page() {
		
		homePage = new HomePage(driver);		
	}

	@When("^User clicks 'contact us' link$")
	public void user_clicks_contact_us_link() {
	    
		homePage.openContactUsPage();
	}

	@When("^User fills in inquiry form$")
	public void user_fills_in_inquiry_form() {
	    
		contactPage = new ContactUsPage(driver);
		contactPage.ContactUs(faker.getFullName(), faker.getEmail(), faker.getRandomMessage());
	}

	@Then("^User should see inquiry sent successfully$")
	public void user_should_see_inquiry_sent_successfully() {
	    
		Assert.assertTrue(contactPage.successMessage.getText()
				 .contains("Your enquiry has been successfully sent to the store owner."));
	}
	
}
