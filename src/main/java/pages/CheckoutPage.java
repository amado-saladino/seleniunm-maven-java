package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends PageBase {
	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "input.button-1.checkout-as-guest-button")
	private WebElement guestBtn;

	@FindBy(id = "BillingNewAddress_FirstName")
	private WebElement fnTxt;

	@FindBy(id = "BillingNewAddress_LastName")
	private WebElement lnTxt;

	@FindBy(id = "BillingNewAddress_Email")
	private WebElement emailTxt;

	@FindBy(id = "BillingNewAddress_CountryId")
	private WebElement countryList;

	@FindBy(id = "BillingNewAddress_PhoneNumber")
	private WebElement phoneTxt;

	@FindBy(id = "BillingNewAddress_City")
	private WebElement cityTxt;

	@FindBy(id = "BillingNewAddress_Address1")
	private WebElement addressTxt;

	@FindBy(id = "BillingNewAddress_ZipPostalCode")
	private WebElement postCodeTxt;

	@FindBy(xpath = "//*[@id=\'billing-buttons-container\']/input")
	private WebElement continueBtn;

	@FindBy(id = "shippingoption_1")
	private WebElement shippingMethodRdo;

	@FindBy(xpath = "//*[@id=\'shipping-method-buttons-container\']/input")
	private WebElement continueShippingBtn;

	@FindBy(xpath = "//*[@id=\'payment-method-buttons-container\']/input")
	private WebElement continuePaymentBtn;

	@FindBy(css = "#payment-info-buttons-container > input")
	private WebElement continueInfoBtn;

	@FindBy(css = "a.product-name")
	public WebElement prodcutName;

	@FindBy(css = "input.button-1.confirm-order-next-step-button")
	private WebElement confirmBtn;

	@FindBy(css = "h1")
	public WebElement ThankYoulbl;

	@FindBy(css = "div.title")	
	private WebElement successMessage;

	@FindBy(linkText = "Click here for order details.")
	private WebElement orderDetailsLink;

	@FindBy(name = "shippingoption")
	List<WebElement> shippingMethods;

	@FindBy(name = "paymentmethod")
	List<WebElement> paymentMethods;

	@FindBy(xpath="//input/following::label[contains(.,'Money')]")
	WebElement PayByMoney;

	public void fillAddress(String countryName, String address, String postcode, String phone, String city) {

		selectComboItemByText(countryList, countryName);
		setTextElementText(cityTxt, city);
		setTextElementText(addressTxt, address);
		setTextElementText(postCodeTxt, postcode);
		setTextElementText(phoneTxt, phone);
		clickButton(continueBtn);
	}

	public void FillOrderOptions(int shippingOption, String paymentOption) {

		// first shipping method
		getElement(By.name("shippingoption"));
		selectShippingMethod(shippingOption);

		clickButton(continueShippingBtn);

		// first payment method
		
		WebElement payByMoney = getElement(By.xpath("//input/following::label[contains(.,'"+ paymentOption +"')]"));
		clickButton(payByMoney);
		
		//This one works too
		//1. find by Explicit wait, this method works on element declared in elements sections 'FindBy' 
		//waitForElement(PayByMoney);
		//clickButton(PayByMoney);
		
		//2. Find by Fluent wait, paymentOption here must be int value
		//This is also valid code
		//getElement(By.name("paymentmethod"));
		//selectPaymentMethod(paymentOption);

		clickButton(continuePaymentBtn);

		waitForElement(continueInfoBtn);
		clickButton(continueInfoBtn);

		waitForElement(prodcutName);
	}

	
	//The commented code above uses this method
	/*private void selectPaymentMethod(int paymentOption) {

		clickButton(paymentMethods.get(paymentOption));
	}*/

	private void selectShippingMethod(int option) {

		clickButton(shippingMethods.get(option));
	}

	public void confirmOrder() {
		clickButton(confirmBtn);		
	}

	public void viewOrderDetails() {
		waitForElement(orderDetailsLink);
		clickButton(orderDetailsLink);
	}

	
	public void fillPersonalData(String firstName, String lastName,String email){
		
		setTextElementText(fnTxt, firstName);
		setTextElementText(lnTxt, lastName);
		setTextElementText(emailTxt, email);
	}
	
	
}
