package com.bt.og.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.bt.og.base.TestBase;

public class PhonePage extends TestBase {

	
	@FindBy(tagName = "h2")
	public WebElement title;

	@FindBy(name = "Search")
	public WebElement searchText;

	@FindBy(xpath = ".//button[@class='button' and @onclick='return validateComplete(this.form)']")
	public WebElement searchBtn;

	@FindBy(id = "New_Acc")
	public WebElement NewAccChkBox;

	public PhonePage() {
		PageFactory.initElements(driver, this);
	}

	public void checkAllProductItemDisplayed() {
		List<String> list = new ArrayList<String>();
		list.add("Phone products");
		list.add("Phone accessories");
		list.add("Answering machines");
		list.add("Facsimile");
		list.add("Accounts & subscription management");
		list.add("Phones");
		list.add("Pagers");
		list.add("Webtop phone");
		list.add("Smartphone/PDA");
		list.add("BT building/office extension (BTnet/PBX/IP)");
		list.add("CISCO IP phone");
		list.add("ACD extension (CSCnet)");
		list.add("Harrier iPACD");
		list.add("Direct exchange lines");
		list.add("Official residential services");
		list.add("Broadband");
		list.add("Business Highway, ISDN and private circuits");
		list.add("Skype for business");
		list.add("BT personal call router");
		list.add("Calling features");
		list.add("Inbound services");
		list.add("Featureline");
		list.add("Homeworker");
		list.add("Mobile services");
		list.add("SMS messaging");
		list.add("Audio conferencing");
		list.add("Video conferencing");
		list.add("Consultancy");
		List<WebElement> actualLinks = driver.findElements(By.tagName("a"));

		for (int i = 0; i < actualLinks.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				String itemName = actualLinks.get(i).getText().trim();
				if (itemName.contains(list.get(j))) {
					System.out.println("Found " + actualLinks.get(i).getText());
					list.remove(j);
				}
			}
		}
		if (list.size() > 0) {
			for (int j = 0; j < list.size(); j++) {
				System.out.println(list.get(j) + " Not displayed");
			}
			Assert.assertTrue(false, "All Phone items are not displayed.");
		}

	}

	public ProductListPage clicOnProduct(String catalogueItem) {
		clickLink(catalogueItem);
		return new ProductListPage();
	}

	public ProductListPage selectNewAccountChkBox() {
		waitForElementToBeVisible(NewAccChkBox, 5);
		click(NewAccChkBox);
		return new ProductListPage();
	}

	public boolean isNewAccountPageDisplayed() {
		List<WebElement> list = driver.findElements(By.id("New_Acc"));
		if (list.size() > 0) {
			return true;

		} else {
			return false;
		}
	}

	public ProductListPage searchproduct(String productName) {
		switchToFramePFFrame();
		type(searchText, productName);
		click(searchBtn);
		return new ProductListPage();

	}
}
