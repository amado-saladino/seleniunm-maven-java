package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductReviewPage extends PageBase
{

	public ProductReviewPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "AddProductReview_Title")
	WebElement reviewTitleTxt;

	@FindBy(id = "AddProductReview_ReviewText")
	WebElement reviewText;
	
	@FindBy(name="AddProductReview.Rating")
	List<WebElement> ratingOptions;

	@FindBy(id = "addproductrating_4")
	WebElement rating4RdoBtn;

	@FindBy(name = "add-review")
	WebElement submitReviewBtn;

	@FindBy(css = "div.result")
	public  WebElement reviewNotification;
	
	void selectRating(int rate){
		clickButton(ratingOptions.get(rate));
	}

	public void AddProductReview(String reviewTitle, String reviewMessage,int rate) {
		setTextElementText(reviewTitleTxt, reviewTitle);
		setTextElementText(reviewText, reviewMessage);
		//clickButton(rating4RdoBtn);
		selectRating(rate);
		clickButton(submitReviewBtn);
	}
}
