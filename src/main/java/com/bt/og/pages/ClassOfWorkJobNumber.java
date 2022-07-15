package com.bt.og.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bt.og.base.TestBase;

public class ClassOfWorkJobNumber extends TestBase{

	
	@FindBy(xpath = ".//input[@value='Continue checkout']")
	public WebElement continueCheckOutBtn;

	
	public ClassOfWorkJobNumber() {
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickOnContinueCheckOut()
	{
		click(continueCheckOutBtn);
		
		
	}
	
}
