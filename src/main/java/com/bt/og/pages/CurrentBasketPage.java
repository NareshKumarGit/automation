package com.bt.og.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.bt.og.base.TestBase;
import com.bt.og.utility.ExcelUtility;

public class CurrentBasketPage extends TestBase {
	public ExcelUtility excelUtility = new ExcelUtility("ProductsPage");
	@FindBy(id = "addToBasket")
	public WebElement addToBasketBtn;

	@FindBy(linkText = "Cancel, and continue shopping")
	public WebElement CancelAndContinue;

	@FindBy(id = "checkout")
	public WebElement checkoutBtn;

	public CurrentBasketPage() {
		PageFactory.initElements(driver, this);
	}

	public void clickOnAddToBasket() {
		waitForElementToBeVisible(addToBasketBtn, 5);
		click(addToBasketBtn);
	}

	public void clickOnCancleAndContinue() {
		waitForElementToBeVisible(CancelAndContinue, 5);
		click(CancelAndContinue);

	}

	public ShoppingBasketPage clickOnCheckOut() {
		waitForElementToBeVisible(checkoutBtn, 5);
		click(checkoutBtn);
		return new ShoppingBasketPage();
	}

	public boolean checkAllButtonsDisplayed() {
		try {
			waitForElementToBeVisible(addToBasketBtn, 5);
			addToBasketBtn.isDisplayed();
			CancelAndContinue.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void fillCurrentBasket(String productName) {
		Object[][] testdata = excelUtility.getTestData(productName);
		for (int i = 0; i < testdata.length; i++) {
			String locatorName = testdata[i][0].toString().trim();
			String webElementType = testdata[i][1].toString().trim();
			String locatorPath = testdata[i][2].toString().trim();
			String text = testdata[i][3].toString().trim();
			fillData(locatorName, webElementType, locatorPath, text);
		}
	}

	public void fillData(String locatorName, String webElementType, String locatorPath, String text) {
		System.out
				.println("Locator name : " + locatorName + " WebElement Type " + webElementType + " id " + locatorPath);
		if (!locatorName.equals("NIL") && !webElementType.equals("NIL")) {
			try {
				WebElement element;
				String locatorType = locatorName.toLowerCase();

				if (locatorType.equals("id")) {
					if (webElementType.equalsIgnoreCase("text")) {
						try {
							type(driver.findElement(By.id(locatorPath)), text);
						} catch (Exception e) {
							alertAccept();
						}

						if (isAlertPresent()) {
							alertAccept();
						}
					} else if (webElementType.equalsIgnoreCase("radio")) {
						click(driver.findElement(By.id(locatorPath)));
						// driver.findElement(By.id(locatorPath)).click();

					} else if (webElementType.equalsIgnoreCase("checkbox")) {
						click(driver.findElement(By.id(locatorPath)));
						// driver.findElement(By.id(locatorPath)).click();
					} else if (webElementType.equalsIgnoreCase("dropdown")) {
						selectByTextID(locatorPath, text);
						/*
						 * element = driver.findElement(By.id(locatorPath)); Select select = new
						 * Select(element); select.selectByVisibleText(text);
						 */
					} else if (webElementType.equalsIgnoreCase("date")) {
						WebElement elementdate = driver.findElement(By.id(locatorPath));
						// ((JavascriptExecutor)driver).executeScript("document.getElementById('"+locatorPath+"').value='"+text+"';");
						elementdate.sendKeys("09/09/2021");
						wait(2000);
						elementdate.sendKeys(Keys.ESCAPE);
					} else if (webElementType.equalsIgnoreCase("alert")) {
						
						if (isAlertPresent()) {
							driver.findElement(By.tagName("th")).click();
							alertAccept();
						}

					} else {
						System.out.println("Locator type is not correct");

					}
				} else if (locatorType.equals("name")) {
					if (webElementType.equalsIgnoreCase("text")) {
						try {
							driver.findElement(By.name(locatorPath)).sendKeys(text);
						} catch (Exception e) {
							alertAccept();
						}

						if (isAlertPresent()) {
							alertAccept();
						}

					} else if (webElementType.equalsIgnoreCase("radio")) {
						// driver.findElement(By.name(locatorPath)).click();
						click(driver.findElement(By.name(locatorPath)));

					} else if (webElementType.equalsIgnoreCase("checkbox")) {
						// driver.findElement(By.name(locatorPath)).click();
						click(driver.findElement(By.name(locatorPath)));
					} else if (webElementType.equalsIgnoreCase("dropdown")) {
						element = driver.findElement(By.name(locatorPath));
						Select select = new Select(element);
						select.selectByVisibleText(text);
						selectByText(locatorPath, text);
					} else if (webElementType.equalsIgnoreCase("date")) {
						WebElement elementdate = driver.findElement(By.name(locatorPath));
						elementdate.sendKeys("06/08/2020");
						wait(5000);
						elementdate.sendKeys(Keys.ESCAPE);
					} else if (webElementType.equalsIgnoreCase("alert")) {
						if (isAlertPresent()) {
							alertAccept();
						}
					} else {
						System.out.println("Locator type is not correct");
					}
				}

			} catch (Exception e) {
				System.out.println("Unable to fill data");

			}
		} else {
			System.out.println("There are no questions.");

		}

	}

	public String getErrorDateText() {
		WebElement error = driver.findElement(By.id("errorRD543display"));
		return error.getText();
		

	}

}
