package com.bt.og.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.bt.og.base.TestBase;

public class LoginPage extends TestBase {
	@FindBy(xpath = ".//form[@name='CustEINForm']/section/h2[.='Place an order']")
	public WebElement titlePlaceAnOrder;

	@FindBy(name = "txtUniqueIdentifier")
	public WebElement einTxtBox;

	@FindBy(name = "SuperUser")
	public WebElement superUserChkBox;

	@FindBy(xpath = ".//form[@name='CustEINForm']//input[@name='btnSubmit']")
	public WebElement continueBtn;

	@FindBy(xpath = ".//input[@value='Search for an order']")
	public WebElement searchForAnOrderBtn;

	@FindBy(xpath = ".//a[contains(text(),'My approvals')]")
	public WebElement myApprovalsBtn;

	@FindBy(xpath = ".//input[@value='Bulk upload']")
	public WebElement bulkupLoadBtn;

	
	@FindBy(xpath = ".//input[@value='My delegates']")
	public WebElement delegateBTn;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void typeEIN(String ein) {
		clear(einTxtBox);
		type(einTxtBox, ein);

	}

	public CustomerDetailsPage clickOnContinue() {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtn);
		} catch (UnhandledAlertException e) {
			e.printStackTrace();
		}
		return new CustomerDetailsPage();
	}

	public CustomerDetailsPage doLogin(String ein) {
		typeEIN(ein);
		scrollPage(0, 200);
		clickOnContinue();

		if (driver.getCurrentUrl().contains("CFTOKEN=0&msg=1001")) {
			Assert.assertTrue(false, "EIN does not exists.");

		}
		switchToFramePFFrame();
		return new CustomerDetailsPage();

	}
	
	public DelegatePage clickOnDelegateBtn() {
		click(delegateBTn);
		return new DelegatePage();

	}
	

	public void clickOnSuperUser() {
		click(superUserChkBox);

	}

	public SearchForAnOrderPage clickOnSearchForOrder() {
		click(searchForAnOrderBtn);
		return new SearchForAnOrderPage();

	}

	public void clickOnApprovals() {
		click(myApprovalsBtn);

	}

	public void clickOnBulkUpload() {
		click(bulkupLoadBtn);

	}

}
