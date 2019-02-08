package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserRegistrationPage extends PageBase 
{
	public UserRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="gender-male")
	WebElement genderRdoBtn ; 
	
	@FindBy(id="FirstName")
	WebElement fnTxtBox; 
	
	@FindBy(id="LastName")
	WebElement lnTxtBox ;
	
	@FindBy(name="DateOfBirthDay")
	WebElement SelectDay;
	
	@FindBy(name="DateOfBirthMonth")
	WebElement SelectMonth;
	
	@FindBy(name="DateOfBirthYear")
	WebElement SelectYear;
	
	@FindBy(id="Email")
	WebElement emailTxtBox ; 
	
	@FindBy(id="Password")
	WebElement passwordTxtBox ; 
	
	@FindBy(id="ConfirmPassword")
	WebElement confirmPasswordTxtBox ; 
	
	@FindBy(id="register-button")
	WebElement registerBtn ; 
	
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
		clickButton(genderRdoBtn);
		setTextElementText(fnTxtBox, firstName);
		setTextElementText(lnTxtBox, lastName);	
		setTextElementText(emailTxtBox, email);
		selectBirthDate(day, month, year);
		setTextElementText(passwordTxtBox, password);
		setTextElementText(confirmPasswordTxtBox, password);
		clickButton(registerBtn);
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
