package com.bt.og.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bt.og.base.TestBase;

public class NewEmployeePage extends TestBase {

	@FindBy(tagName = "h2")
	WebElement title;

	@FindBy(linkText = "Browse the new employee bundles")
	WebElement browseNewBundleBtn;

	public NewEmployeePage() {
		PageFactory.initElements(driver, this);

	}

	public String gettitle() {
		return title.getText();
	}

	public ProductListPage clickOnNewBundleBtn() {
		click(browseNewBundleBtn);
		return new ProductListPage();
	}

	public boolean searchForProductInTable(String productName) {
		boolean found = false;
		List<WebElement> totalItems = driver.findElements(By.xpath(".//table[@class='scroll']/tbody//tr"));
		for (int i = 1; i <= totalItems.size(); i++) {
			WebElement eL = driver
					.findElement(By.xpath(".//table[@class='scroll']/tbody/tr[" + i + "]/td[@class='productTitle']"));
			System.out.println(eL.getText());
			if (eL.getText().contains(productName)) {
				System.out.println("Found " + eL.getText());
				found = true;
				break;
			}
		}
		return found;
	}

	
}
