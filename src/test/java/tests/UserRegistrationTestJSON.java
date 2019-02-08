package tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;
import utilities.Helper;
import utilities.JSONReader;

public class UserRegistrationTestJSON extends TestBase {
	HomePage homePage;
	UserRegistrationPage registerPage;
	LoginPage loginPage;
	String randomEmail;
	String password;
	JSONArray users;

	@BeforeClass
	void setupClass() {

		JSONReader jsonReader = new JSONReader();
		try {
			String contents = jsonReader.readFileAsString("data/users.json");

			users = jsonReader.getJsonArrayFromString(contents, "users");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test(priority = 1, alwaysRun = true)
	public void UserCanRegisterSuccssfully() {

		for (Object user : users) {
			
			String firstname =((JSONObject) user).get("firstname").toString();
			String lastname = ((JSONObject) user).get("lastname").toString();
			password = ((JSONObject) user).get("password").toString();
			String day = ((JSONObject) user).get("day").toString();
			String month = ((JSONObject) user).get("month").toString();
			String year = ((JSONObject) user).get("year").toString();
			
			randomEmail = "any_random_mail" + Helper.generateRandomNumber() + "@server.com";

			homePage = new HomePage(driver);
			homePage.openRegistrationPage();
			registerPage = new UserRegistrationPage(driver);
			registerPage.userRegistration(firstname , lastname, randomEmail, password, day, month, year);
			Assert.assertTrue(registerPage.successMessage.getText().contains("Your registration completed"));
			RegisteredUserCanLogout();
			RegisteredUserCanLogin();
			RegisteredUserCanLogout();
		}
	}

	@Test(dependsOnMethods = { "UserCanRegisterSuccssfully" }, enabled = false)
	public void RegisteredUserCanLogout() {
		registerPage.userLogout();
	}

	@Test(dependsOnMethods = { "RegisteredUserCanLogout" }, enabled = false)
	public void RegisteredUserCanLogin() {
		homePage.openLoginPage();
		loginPage = new LoginPage(driver);
		loginPage.UserLogin(randomEmail, password);
		Assert.assertTrue(registerPage.logoutLink.isDisplayed());
	}
}
