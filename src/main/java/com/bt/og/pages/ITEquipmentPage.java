package com.bt.og.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.bt.og.base.TestBase;
import com.bt.og.utility.VerifyLink;

public class ITEquipmentPage extends TestBase {

	@FindBy(xpath = ".//button[@class='button']")
	public WebElement enterBtn;
	
	@FindBy(tagName = "h2")
	public WebElement title;

	@FindBy(name = "Search")
	public WebElement searchText;

	@FindBy(xpath = ".//button[@class='button' and @onclick='return validateComplete(this.form)']")
	public WebElement searchBtn;

	public ITEquipmentPage() {
		PageFactory.initElements(driver, this);
	}

	public ProductListPage clickOnEnterBtn() {
		click(enterBtn);
		return new ProductListPage();

	}

	public void checkITUKAllItemsDisplayed() {
		VerifyLink.checkBrokenLinks();
		List<String> list = new ArrayList<String>();
		list.add("Desktop PCs");
		list.add("Desktop memory");
		list.add("Portable PCs");
		list.add("Portable memory");
		list.add("Docking options");
		list.add("Power & batteries");
		list.add("Monitors");
		list.add("Peripherals & accessories");
		list.add("Modems & network");
		list.add("Phone accessories");
		list.add("Headsets & accessories");
		list.add("Printers & scanners");
		list.add("Printer accessories");
		list.add("Information and ordering");
		list.add("Advice & consultancy");
		list.add("Desktop install services");
		list.add("Delivery options");
		list.add("Remote access");
		list.add("Disposal");
		list.add("Other services");
		list.add("Servers");
		List<WebElement> actualLinks = driver.findElements(By.tagName("a"));

		for (int i = 0; i < actualLinks.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				String itemName = actualLinks.get(i).getText().trim();
				if (itemName.contains(list.get(j))) {
					list.remove(j);
				}
			}
		}
		if (list.size() > 0) {
			for (int j = 0; j < list.size(); j++) {
				System.out.println(list.get(j) + " Not displayed");
			}
			Assert.assertTrue(false, "All ITEquipmentLinks items are not displayed.");
		}
	}

	public ProductListPage clicOnProduct(String catalogueItem) {
		clickLink(catalogueItem);
		return new ProductListPage();
	}

	public ProductListPage searchproduct(String productName) {
		switchToFramePFFrame();
		type(searchText, productName);
		click(searchBtn);
		return new ProductListPage();

	}

}
