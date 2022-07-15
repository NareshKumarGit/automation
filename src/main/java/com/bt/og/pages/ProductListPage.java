package com.bt.og.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bt.og.base.TestBase;

public class ProductListPage extends TestBase {

	@FindBy(linkText = "Return to the catalogue")
	
	public WebElement ReturnToCatalogueBtn;

	public ProductListPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "Search")
	WebElement searchText;

	@FindBy(xpath = ".//button[@class='button' and @onclick='return validateComplete(this.form)']")
	WebElement searchBtn;

	public void clickOnReturnToCatalogueBtn() {
		click(ReturnToCatalogueBtn);
	}

	public void selectFromCategory(String visibletext) {
		selectByText("category", visibletext);
	}

	public ProductPage clickOnProduct(String productName) {
		clickLink(productName);
		return new ProductPage();
	}

	public boolean searchProduct(String productName) {
		type(searchText, productName);
		click(searchBtn);
		waitForElementToBeVisible(ReturnToCatalogueBtn, 5);
		return isProductAvaialble(productName);

	}

	public boolean isProductAvaialble(String productName) {
		boolean found = false;
		List<WebElement> list = driver.findElements(By.tagName("a"));
		for (WebElement product : list) {
			if (product.getText().trim().equalsIgnoreCase(productName)) {
				found = true;
				break;
			}
		}
		return found;
	}
	


}
