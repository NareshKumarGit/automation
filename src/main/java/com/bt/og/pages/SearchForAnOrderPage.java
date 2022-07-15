package com.bt.og.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.bt.og.base.TestBase;


public class SearchForAnOrderPage extends TestBase {
	@FindBy(xpath = ".//h2")
	WebElement title;

	@FindBy(xpath = ".//div[@id='OrderByRef']//input")
	WebElement orderByRefText;

	@FindBy(name = "btn_search2")
	WebElement orderRefSearchBtn;

	@FindBy(name = "se_custEIN")
	WebElement UINText;

	@FindBy(name = "se_custOUC")
	WebElement OUCText;

	@FindBy(name = "btn_search")
	WebElement btn_search;

	@FindBy(name = "btn_Return")
	WebElement btn_Return;

	@FindBy(xpath = ".//a[@class='order-ref-link']")
	List<WebElement> order_ref_link;

	public SearchForAnOrderPage() {
		PageFactory.initElements(driver, this);
	}

	public String getPageTitle() {
		String url = driver.getCurrentUrl();
		System.out.println(url);
		if (url.equals("http://ogsys.dev.nat.bt.com/OrderSearch.cfm")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		return title.getText();
	}

	public void typeOrderRef(String id) {
		type(orderByRefText, id);

	}

	public void clickOnOrderRefSearchBtn() {
		click(orderRefSearchBtn);
	}

	public void typeUIN(String UIN) {
		type(UINText, UIN);

	}

	public void typeOUC(String OUC) {
		type(OUCText, OUC);
	}

	public void clickOnSearchBtn() {
		click(btn_search);

	}

	public void clickOnRetrunOGBtn() {
		click(btn_Return);

	}

	public void clickOnBackToSearchBtn() {
		click(btn_Return);
	}

	public String searchOrder(String UIN, String OUC) {
		String titleText = "";
		String orderid = "";
		typeUIN(UIN);
		typeOUC(OUC);
		clickOnSearchBtn();
		if (order_ref_link.size() > 0) {
			try {
				checkRailoError();
				orderid = order_ref_link.get(0).getText();
				System.out.println("Searching order id : [" + orderid + "]");
				click(order_ref_link.get(0));
				wait(3000);
				titleText = driver.findElement(By.tagName("h2")).getText();
				Assert.assertTrue(titleText.contains(orderid), "Order id is not matching.");
				checkRailoError();
				clickOnRetrunOGBtn();
			} catch (StaleElementReferenceException e) {
				Assert.assertTrue(titleText.contains(orderid), "Order id is not matching.");
			}			
			
			
		} else {
			System.out.println("There are no orders in this UIN : " + UIN);
		}
		return orderid;
	}

	public void searchByOrderId(String id) {
		typeOrderRef(id);
		clickOnOrderRefSearchBtn();
		wait(2000);
		click(driver.findElement(By.linkText(id)));
		wait(2000);
		String titleText = driver.findElement(By.tagName("h2")).getText();
		checkRailoError();
		Assert.assertTrue(titleText.contains(id), "Order id is not matching.");
	}

}
