package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserRegistrationPage extends Page 
{
	public UserRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="gender-male")
	WebElement radioMale; 
	
	@FindBy(id="FirstName")
	WebElement textFirstname; 
	
	@FindBy(id="LastName")
	WebElement textLastname;
	
	@FindBy(name="DateOfBirthDay")
	WebElement SelectDay;
	
	@FindBy(name="DateOfBirthMonth")
	WebElement SelectMonth;
	
	@FindBy(name="DateOfBirthYear")
	WebElement SelectYear;
	
	@FindBy(id="Email")
	WebElement textEmail; 
	
	@FindBy(id="Password")
	WebElement textPassword; 
	
	@FindBy(id="ConfirmPassword")
	WebElement textConfirmPassword; 
	
	@FindBy(id="register-button")
	WebElement buttonRegister; 
	
	@FindBy(css="div.result")
	public WebElement successMessage;
	
	@FindBy(linkText="Log out")
	public WebElement logoutLink; 
	
	@FindBy(linkText="My account")
	WebElement myAccountLink; 
	
	public void userRegistration(String firstName , String lastName
			, String email , String password
			, String day, String month, String year) 
	{
		clickButton(radioMale);
		setTextElementText(textFirstname, firstName);
		setTextElementText(textLastname, lastName);	
		setTextElementText(textEmail, email);
		selectBirthDate(day, month, year);
		setTextElementText(textPassword, password);
		setTextElementText(textConfirmPassword, password);
		clickButton(buttonRegister);
	}
	
	public void selectBirthDate(String day,String month,String year){
		selectComboItemByText(SelectDay,day);
		selectComboItemByText(SelectMonth, month);
		selectComboItemByText(SelectYear, year);
	}
	
	public void userLogout() 
	{
		clickButton(logoutLink);
	}
	
	public void openMyAccountPage() 
	{
		clickButton(myAccountLink);
	}
	
}
