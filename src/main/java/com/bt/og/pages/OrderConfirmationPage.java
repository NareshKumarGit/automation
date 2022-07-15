package com.bt.og.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bt.og.base.TestBase;

public class OrderConfirmationPage extends TestBase{

	@FindBy(xpath = ".//button[@type='button']")
	public WebElement printConfirmationBtn;
	
	@FindBy(xpath = ".//button[@name='orderagain']")
	public WebElement placeAnotherorderBtn;
	
	@FindBy(id="orderID")
	public WebElement orderId;
	
	
	public OrderConfirmationPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnPlaceAnotherOrder()
	{
		click(placeAnotherorderBtn);
		
		
	}
	
	public void clickOnPrintBtn()
	{
		click(printConfirmationBtn);
	}
	
	public String getOrderId()
	{
		orderId.getText();
		return orderId.getText();
		
	}
	
	public boolean checkAllButtonsDisplayed()
	{
		boolean found=false;
		try {
			if(printConfirmationBtn.isDisplayed() && orderId.isDisplayed())
			{
				found=true;
			}
		} catch (Exception e) {
			found=false;
		}
		
		return found;
		
	}
}
