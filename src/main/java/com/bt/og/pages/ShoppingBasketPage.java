package com.bt.og.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.bt.og.base.TestBase;

public class ShoppingBasketPage extends TestBase {

	@FindBy(xpath = ".//h2")
	public WebElement title;

	@FindBy(id = "checkout")
	public WebElement checkoutBtn;

	@FindBy(linkText = "Back to customer details")
	WebElement BackToCustomerDetailsBtn;

	@FindBy(linkText = "Return to the sign-in screen")
	WebElement returnToSignInBtn;

	@FindBy(id = "emptyBasket")
	public WebElement emptyBasketBtn;

	@FindBy(xpath = ".//table[@class='scroll']")
	public WebElement itemsList;

	public ShoppingBasketPage() {
		PageFactory.initElements(driver, this);
	}

	public String getPageTitle() {
		waitForElementToBeVisible(title, 5);
		return title.getText();
	}
	
	public void waitForShoppingBasketToDisplay()
	{
		switchToFramePFFrame();
		waitForElementToBeVisible(emptyBasketBtn, 10);
		
	}

	public void deleteProductFromTable(String productName) {
		switchToFramePFFrame();
		List<WebElement> totalItems = driver.findElements(By.xpath(".//table[@class='scroll']/tbody//tr"));
		for (int i = 1; i <= totalItems.size(); i++) {
			WebElement eL = driver
					.findElement(By.xpath(".//table[@class='scroll']/tbody/tr[" + i + "]/td[@class='productTitle']"));
			System.out.println(eL.getText());
			if (eL.getText().equalsIgnoreCase(productName)) {
				System.out.println("deleting " + eL.getText());
				WebElement remove = driver.findElement(By.xpath(
						".//table[@class='scroll']/tbody/tr[" + i + "]/td[6]/a[@title='Remove this Orderline']"));
				click(remove);
				wait(2000);
				break;
			}
		}
	}

	public boolean searchForProductInTable(String productName) {
		boolean found = false;
		List<WebElement> totalItems = driver.findElements(By.xpath(".//table[@class='scroll']/tbody//tr"));
		for (int i = 1; i <= totalItems.size(); i++) {
			WebElement eL = driver
					.findElement(By.xpath(".//table[@class='scroll']/tbody/tr[" + i + "]/td[@class='productTitle']"));
			System.out.println(eL.getText());
			if (eL.getText().equalsIgnoreCase(productName)) {
				System.out.println("Found " + eL.getText());
				found = true;
				break;
			}
		}
		return found;
	}

	public void emptyBasket() {
		switchToFramePFFrame();
		if (!checkIfBasketIsEmpty()) {
			WebElement emptyBasket = waitForElementToBeVisible(driver.findElement(By.id("emptyBasket")), 5);
			try {
				waitForElementToBeVisible(emptyBasket, 5);
				click(emptyBasket);
				driver.switchTo().alert().accept();
			} catch (UnhandledAlertException e) {
				System.out.println("Unhandled Alert exception.");
			}
		}
//		Assert.assertTrue(checkIfBasketIsEmpty(), "Basket is not empty.");
	}

	public boolean checkIfBasketIsEmpty() {
		switchToFramePFFrame();
		try {
			WebElement basketEmptyText = driver
					.findElement(By.xpath(".//section[@class='callout']/h3[.='Your basket is empty']"));
			basketEmptyText.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public CheckOut clickOnCheckOut()
	{
		click(checkoutBtn);
		return new CheckOut();
		
		
	}
	
	public LoginPage clickOnReturnToSignIn()
	{
		click(returnToSignInBtn);
		return new LoginPage();
		
	}
}
