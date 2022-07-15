package com.bt.og.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.bt.og.base.TestBase;


public class CustomerDetailsPage extends TestBase {
	@FindBy(id = "pageTitle")
	public WebElement pageTitle;

	@FindBy(linkText = "Return to the sign-in screen")
	private WebElement ReturnToTheSignInScreenBtn;

	@FindBy(linkText = "Continue")
	public WebElement continueBtn;

	@FindBy(xpath = ".//section[1]//p[1]")
	private WebElement name;

	@FindBy(xpath = ".//section[1]//p[2]")
	private WebElement ouc;

	@FindBy(xpath = ".//section[1]//p[3]")
	private WebElement phoneNumber;

	@FindBy(xpath = ".//section[1]//p[4]")
	private WebElement boatID;

	@FindBy(xpath = ".//section[1]//p[5]")
	private WebElement uin;

	@FindBy(xpath = ".//section[1]//p[6]")
	private WebElement companyIndicator;

	@FindBy(xpath = ".//section[1]//p[7]")
	private WebElement country;

	@FindBy(xpath = ".//section[1]//p[8]")
	private WebElement gfrCode;

	@FindBy(id = "pageTitle")
	private WebElement customerDetailsTitle;

	@FindBy(linkText = "Using the 'Back button' in your browser")
	private WebElement backButtonInYourbasketLink;

	@FindBy(xpath = ".//h1")
	private WebElement warningHelpText;
	

	public CustomerDetailsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getTitle() {
		return pageTitle.getText();
	}

	public String getName() {
		return name.getText();
	}

	public String getOUC() {
		return ouc.getText();
	}

	public String getPhoneNumber() {
		return phoneNumber.getText();
	}

	public String getBoatID() {
		return boatID.getText();
	}

	public String getUIN() {
		return uin.getText();
	}

	public String getComPanyIndicator() {
		return companyIndicator.getText();
	}

	public String getCountryCode() {
		return country.getText();
	}

	public ShoppingBasketPage clickOnContinueBtn() {
		click(continueBtn);
		return new ShoppingBasketPage();

	}

	public LoginPage clickOnReturnToTheSignInScreenBtn() {
		click(ReturnToTheSignInScreenBtn);
		return new LoginPage();
	}

	public void clickOnBackButtonInYourBrowserLink() {
		click(backButtonInYourbasketLink);
	}

	public String getWarningText() {
		return warningHelpText.getText();
	}


}
