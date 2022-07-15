package com.bt.og.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bt.og.base.TestBase;

public class ProductPage extends TestBase {

	@FindBy(tagName = "h2")
	public WebElement productTitle;

	@FindBy(linkText = "Add to basket")
	public WebElement addtoBasketBtn;

	@FindBy(linkText = "View basket")
	public WebElement viewBasketBtn;

	@FindBy(id = "home")
	public WebElement homeBtn;

	@FindBy(linkText = "Previous page")
	public WebElement previousPageBtn;

	public ProductPage() {
		PageFactory.initElements(driver, this);
	}

	public String getproductName()
	{
		return productTitle.getText().trim();
		
	}
	public CurrentBasketPage clickOnAddTobasket() {
		click(addtoBasketBtn);
		switchToBasketContentFrame();
		return new CurrentBasketPage();
	}

	public void clickOnViewBasket() {
		click(viewBasketBtn);

	}

	public void clickOnHomeBtn() {
		click(homeBtn);

	}

	public void clickOnPreviousPage() {
		click(previousPageBtn);
	}

	public boolean checkProductName(String productName) {
		String product = productTitle.getText();
		if (product.equalsIgnoreCase(productName)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkAllButtonDisplayed() {
		try {
			addtoBasketBtn.isDisplayed();
			viewBasketBtn.isDisplayed();
			homeBtn.isDisplayed();
			previousPageBtn.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean checkAllButtonDisplayedNonUK() {
		try {
			addtoBasketBtn.isDisplayed();
			viewBasketBtn.isDisplayed();
			previousPageBtn.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}

	}

}
