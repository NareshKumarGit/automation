package com.bt.og.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bt.og.base.TestBase;

public class CheckOut extends TestBase{
	@FindBy(id = "DTPSummary")
	public WebElement orderSummary;
	
	@FindBy(id = "DTPreason")
	public WebElement businessjustificationTextArea;
		
	@FindBy(xpath = ".//button[@type='submit']")
	public WebElement continueCheckoutButton;
	
	
	public CheckOut() {
		PageFactory.initElements(driver, this);
	}
	
	public void typeOrderSummary(String summary)
	{
		type(orderSummary, summary);
		
		
	}
	
	public void typeBusinessJustification(String justification)
	{
		type(businessjustificationTextArea, justification);
		
		
	}
	
	
	public DeliveryAddressPage clickOnContinueCheckout()
	{
		click(continueCheckoutButton);
		return new DeliveryAddressPage();
		
	}
	
	
	
	
	
	
	
}
