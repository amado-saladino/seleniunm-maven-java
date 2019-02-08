package steps;

import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.UserRegistrationPage;
import tests.TestBase;

public class UserRegistration extends TestBase  {

	HomePage homePage; 
	UserRegistrationPage registerPage;
	String randomEmail;
	String password;
	
	@Given("^The user is at the home page$")
	public void the_user_is_at_the_home_page() {
	    
		homePage = new HomePage(driver);
		homePage.openRegistrationPage();
	}
	
	@When("^User clicks register link$")
	public void user_clicks_register_link() {
	   
		Assert.assertTrue(driver.getCurrentUrl().contains("register"));
		registerPage = new UserRegistrationPage(driver);
	}

	@When(("^User entered \"([^\"]*)\" , \"([^\"]*)\"$"))
	public void user_entered(String firstname, String lastname)  {
	    
		randomEmail = faker.getEmail();
		this.password=faker.getPassword();
		faker.generateLocalDate();
		registerPage = new UserRegistrationPage(driver);
		registerPage.userRegistration(firstname, lastname, randomEmail, password, faker.getDayOfMonth(), faker.getMonth(), faker.getYear());
	}

	@Then("^User should see Registration success page$")
	public void user_should_see_Registration_success_page() {
	    
		Assert.assertTrue(registerPage.logoutLink.isDisplayed());
		Assert.assertTrue(registerPage.successMessage.getText().contains("Your registration completed"));
		registerPage.userLogout();
	}
}
