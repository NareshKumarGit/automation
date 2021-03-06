package com.bt.og.pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.bt.og.base.TestBase;

public class SystemAccessPage extends TestBase {
	ShoppingBasketPage shoppingBasket;

	@FindBy(name = "SearchString")
	public WebElement searchText;

	@FindBy(name = "btn_Back")
	public WebElement backBtn;

	@FindBy(xpath = ".//button[@class='button' and @onclick='return fnSearch()']")
	public WebElement searchBtn;

	public SystemAccessPage() {
		PageFactory.initElements(driver, this);
		shoppingBasket = new ShoppingBasketPage();
	}

	public void searchproduct(String productName) {
		switchToFramePFFrame();
		type(searchText, productName);
		click(searchBtn);
		waitForElementToBeVisible(backBtn, 5);
		clickLink(productName);

	}

	public void CSSProduction() {
		searchproduct("CSS Production");
		waitForElementToBeVisible(backBtn, 5);
		clickLink("CSS Production");
		List<WebElement> list = driver.findElements(By.name("ProductCodeID"));
		for (int i = 0; i < list.size(); i++) {
			click(list.get(i));
			click(list.get(i));
			Assert.assertTrue(list.get(i).isEnabled());
			if (i == list.size() - 1) {
				click(list.get(i));
			}
		}
		list.clear();
		WebElement submitBtn = driver.findElement(By.name("btn_Submit"));
		WebElement backBtn = driver.findElement(By.name("btn_Back"));
		WebElement selectAllbtn = driver.findElement(By.name("btn_All"));
		if (submitBtn.isDisplayed() && backBtn.isDisplayed() && selectAllbtn.isDisplayed()) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false, "All buttons not displayed.");
		}
		click(submitBtn);
		WebElement submitBtn2 = driver.findElement(By.name("btn_Submit"));
		waitForElementToBeVisible(submitBtn2, 10);
		list = driver.findElements(By.xpath(".//input[@type='radio']"));
		for (WebElement eL : list) {
			click(eL);
		}
		list.clear();
		WebElement actn = driver.findElement(By.name("UserID"));
		click(actn);
		WebElement profile = driver.findElement(By.id("Profile"));
		Select select = new Select(profile);
		list = select.getOptions();
		for (WebElement eL : list) {
			select.selectByVisibleText(eL.getText());
		}
		WebElement textArea = driver.findElement(By.id("QID2324"));
		type(textArea, "Test order");

		click(submitBtn2);
		switchToFramePFFrame();
		waitForElementToBeVisible(shoppingBasket.emptyBasketBtn, 10);
		boolean result = shoppingBasket.searchForProductInTable("WESTWARD CSS");
		Assert.assertTrue(result, "Product not added to basket.");
		shoppingBasket.emptyBasket();
	}

	public void IUSERDomainGroupConnection() {
		searchproduct("IUSER Domain (Group Connection)");
		waitForElementToBeVisible(backBtn, 5);
		clickLink("IUSER Domain (Group Connection)");
		List<WebElement> list = driver.findElements(By.name("ProductCodeID"));
		for (int i = 0; i < list.size(); i++) {
			click(list.get(i));
			click(list.get(i));
			Assert.assertTrue(list.get(i).isEnabled());
			if (i == list.size() - 1) {
				click(list.get(i));
			}
		}
		list.clear();
		WebElement submitBtn = driver.findElement(By.name("btn_Submit"));
		WebElement backBtn = driver.findElement(By.name("btn_Back"));
		if (submitBtn.isDisplayed() && backBtn.isDisplayed()) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false, "All buttons not displayed.");
		}
		click(submitBtn);
		WebElement submitBtn2 = driver.findElement(By.name("btn_Submit"));
		waitForElementToBeVisible(submitBtn2, 10);
		list = driver.findElements(By.xpath(".//input[@type='radio']"));
		for (WebElement eL : list) {
			click(eL);
		}
		list.clear();
		String parent = driver.getWindowHandle();

		// Please check the box to confirm application help has been read before
		// ordering
		WebElement confirmChkbox = driver.findElement(By.id("QID2223_0"));
		confirmChkbox.click();
		WebElement groupfield1 = driver.findElement(By.name("QID3284"));
		type(groupfield1, "apd - Enterprise Nerve Centre User KUL");
		WebElement searchBtn = driver.findElement(By.xpath(".//div[@id='QID3284']//a[@class='button']"));

		click(searchBtn);
		wait(5000);
		Set<String> child = driver.getWindowHandles();
		for (String window : child) {
			if (!parent.equals(window)) {
				try {
					driver.switchTo().window(window);
				} catch (Exception e) {
					// TODO: handle exception
				}
				// Select select = new Select(driver.findElement(By.id("SelectGroup")));
				// select.selectByIndex(2);
				clickButton("btn_SelectOk");
			}
		}
		driver.switchTo().window(parent);
		switchToFramePFFrame();

		click(submitBtn2);
		switchToFramePFFrame();
		waitForElementToBeVisible(shoppingBasket.emptyBasketBtn, 10);
		boolean result = shoppingBasket.searchForProductInTable("IUSER Domain(Group Connection)");
		Assert.assertTrue(result, "Product not added to basket.");
		shoppingBasket.emptyBasket();
	}

	public void RemoteAccessAuthenticationTokenActivIdentity() {
		searchproduct("Remote Access-Authentication Token(ActivIdentity)");
		waitForElementToBeVisible(backBtn, 5);
		clickLink("Remote Access-Authentication Token(ActivIdentity)");
		List<WebElement> list = driver.findElements(By.name("ProductCodeID"));
		for (int i = 0; i < list.size(); i++) {
			click(list.get(i));
			click(list.get(i));
			Assert.assertTrue(list.get(i).isEnabled());
			if (i == list.size() - 1) {
				click(list.get(i));
			}
		}
		list.clear();
		WebElement submitBtn = driver.findElement(By.name("btn_Submit"));
		WebElement backBtn = driver.findElement(By.name("btn_Back"));
		if (submitBtn.isDisplayed() && backBtn.isDisplayed()) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false, "All buttons not displayed.");
		}
		click(submitBtn);
		driver.findElement(By.name("btn_Submit"));
		WebElement dropdownrequire = driver.findElement(By.id("QID3764"));
		Select select = new Select(dropdownrequire);
		List<WebElement> lists = select.getOptions();
		for (WebElement webElement : lists) {
			select.selectByVisibleText(webElement.getText());
		}
		select.selectByVisibleText("New");
		List<WebElement> chkbox = driver.findElements(By.xpath(".//input[@type='checkbox']"));
		for (WebElement webElement : chkbox) {
			if (!webElement.isSelected()) {
				webElement.click();
			}
		}
		try {

			checkRailoError();
			click(driver.findElement(By.id("QID181")));
			type(driver.findElement(By.id("QID181")), "Electronic city");
			type(driver.findElement(By.id("QID182")), "Electronic city");
			type(driver.findElement(By.id("QID183")), "Electronic city");
			type(driver.findElement(By.id("QID184")), "Electronic city");
			type(driver.findElement(By.id("QID185")), "Electronic city");
			type(driver.findElement(By.id("QID186")), "EC1051600");
			type(driver.findElement(By.id("QID4203")), "9900194719");
			click(driver.findElement(By.name("btn_Submit")));

		} catch (Exception e) {
			Assert.assertTrue(false, "Remote Access-Authentication Token(ActivIdentity) all field s not displayed.");
		}
		switchToFramePFFrame();
		waitForElementToBeVisible(shoppingBasket.emptyBasketBtn, 10);
		boolean result = shoppingBasket.searchForProductInTable("Authentication for BT Health System");
		Assert.assertTrue(result, "Authentication for BT Health System is not added to basket");
		shoppingBasket.emptyBasket();
	}

	public void OneViewChangesToProfile() {
		searchproduct("OneView - Changes to Profile");
		waitForElementToBeVisible(backBtn, 5);
		clickLink("OneView - Changes to Profile");
		List<WebElement> list = driver.findElements(By.name("ProductCodeID"));
		for (int i = 0; i < list.size(); i++) {
			click(list.get(i));
			click(list.get(i));
			Assert.assertTrue(list.get(i).isEnabled());
			if (i == list.size() - 1) {
				click(list.get(i));
			}
		}
		list.clear();
		wait(2000);
		WebElement submitBtn = driver.findElement(By.name("btn_Submit"));
		WebElement backBtn = driver.findElement(By.name("btn_Back"));
		if (submitBtn.isDisplayed() && backBtn.isDisplayed()) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false, "All buttons not displayed.");
		}
		click(submitBtn);

		Select select = new Select(driver.findElement(By.id("lob")));
		select.selectByVisibleText("BT Retail");
		select = new Select(driver.findElement(By.id("BusinessUnit")));
		select.selectByVisibleText("BT Business");
		select = new Select(driver.findElement(By.id("Directorate")));
		select.selectByVisibleText("Back Office");
		select = new Select(driver.findElement(By.id("UserType")));
		select.selectByVisibleText("Agent");
		WebElement submitBtnNxt = driver.findElement(By.name("btn_next"));
		wait.until(ExpectedConditions.elementToBeClickable(submitBtnNxt));
		click(submitBtnNxt);
		checkRailoError();
		select = new Select(driver.findElement(By.id("QID2581")));
		select.selectByVisibleText("Yes");
		select = new Select(driver.findElement(By.id("QID2591")));
		select.selectByVisibleText("Yes");
		type(driver.findElement(By.id("QID2592")), "612092646");
		select = new Select(driver.findElement(By.id("QID2583")));
		select.selectByVisibleText("Yes");
		type(driver.findElement(By.id("QID2582")), "Test");
		select = new Select(driver.findElement(By.id("QID2587")));
		select.selectByVisibleText("No");

	}

}
