package com.bt.og.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.bt.og.base.TestBase;

public class DeliveryAddressPage extends TestBase {

	@FindBy(id = "placeOrder")
	public WebElement placeOrder;
	
	@FindBy(xpath = ".//input[@type='submit' and value='Use this address']")
	public WebElement useThisAddress;

	@FindBy(xpath = ".//input[@value='Use another address']")
	public WebElement useAnotherAddress;

	public DeliveryAddressPage() {
		PageFactory.initElements(driver, this);
	}

	public void clickOnUseThisAddress() {
		click(useThisAddress);

	}

	public void clickOnUseAnotherAddress() {
		click(useAnotherAddress);

	}

	public void typeSelectdDeliveryTo(String type) {
		Select select = new Select(driver.findElement(By.id("Selectoption")));
		select.selectByVisibleText(type);

	}
	
	public void typeDeliveryUIN(String UIN)
	{
		type(driver.findElement(By.xpath(".//input[@id='DeliveryEIN']")), UIN);
		
	}
	
	

	public void typeDeliverySecondContactName() {
		type(driver.findElement(By.id("Delivery2ndContactName")), "Testuser");
		type(driver.findElement(By.id("Delivery2ndContactTel")), "990014718");

	}
	
	public void clickOnContinue()
	{
		click(driver.findElement(By.xpath(".//input[@value='Continue']")));
	}
	
	public void clickOnAltContinue()
	{
		click(driver.findElement(By.xpath(".//input[@id='B123']")));
	}
	

	public void clickOnFinalPlaceOrder()
	{
		switchToFramePFFrame();
		click(placeOrder);
		
	}
	
	
}
