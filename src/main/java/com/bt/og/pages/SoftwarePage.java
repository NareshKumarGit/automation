package com.bt.og.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bt.og.base.TestBase;

public class SoftwarePage extends TestBase {

	@FindBy(linkText = "Provide new software")
	WebElement provideNewSoftwareBtn;

	@FindBy(linkText = "Remove unneeded software")
	WebElement RemoveUnneededSoftwareBtn;

	@FindBy(linkText = "Non-standard software")
	WebElement NonStandardSoftwareBtn;

	@FindBy(name = "Search")
	public WebElement searchText;

	@FindBy(xpath = ".//button[@class='button' and @onclick='return validateComplete(this.form)']")
	public WebElement searchBtn;

	public SoftwarePage() {
		PageFactory.initElements(driver, this);
	}

	public ProductListPage clickOnProvideNewSoftBtn() {
		click(provideNewSoftwareBtn);
		return new ProductListPage();
	}

	public ProductListPage clickOnRemoveUneededSoft() {
		click(RemoveUnneededSoftwareBtn);
		return new ProductListPage();
	}

	public ProductListPage clickOnNonStandardSoft() {
		click(NonStandardSoftwareBtn);
		return new ProductListPage();

	}

	public ProductListPage searchProduct(String productName) {
		type(searchText, productName);
		click(searchBtn);
		return new ProductListPage();

	}

	public void checkAllAToZLink() {
		clickLink("A");
		clickBackButton();
		clickLink("B");
		clickBackButton();
		clickLink("C");
		clickBackButton();
		clickLink("D");
		clickBackButton();
		clickLink("E");
		clickBackButton();
		clickLink("F");
		clickBackButton();
		clickLink("G");
		clickBackButton();
		clickLink("H");
		clickBackButton();
		clickLink("I");
		clickBackButton();
		clickLink("J");
		clickBackButton();
		clickLink("K");
		clickBackButton();
		clickLink("L");
		clickBackButton();
		clickLink("M");
		clickBackButton();
		clickLink("N");
		clickBackButton();
		clickLink("O");
		clickBackButton();
		clickLink("P");
		clickBackButton();
		clickLink("Q");
		clickBackButton();
		clickLink("R");
		clickBackButton();
		clickLink("S");
		clickBackButton();
		clickLink("T");
		clickBackButton();
		clickLink("U");
		clickBackButton();
		clickLink("V");
		clickBackButton();
		clickLink("W");
		clickBackButton();
		clickLink("X");
		clickBackButton();
		clickLink("Y");
		clickBackButton();
		clickLink("Z");
		clickBackButton();

	}

	// Return of AtoZBlock
	public void clickBackButton() {
		waitForElementToClickable(driver.findElement(By.xpath(".//input[@name='btn_Back']")), 10);
		click(driver.findElement(By.xpath(".//input[@name='btn_Back']")));

	}

}
