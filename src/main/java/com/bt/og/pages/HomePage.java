package com.bt.og.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.bt.og.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(linkText = "Basket")
	WebElement BasketTab;
	
	@FindBy(linkText = "Email & IM")
	WebElement EmailIMTab;

	@FindBy(linkText = "Phone")
	WebElement PhoneTab;

	@FindBy(linkText = "Phones (US)")
	WebElement PhoneUSTab;

	@FindBy(linkText = "IT equipment")
	WebElement ITEquipmentTab;

	@FindBy(linkText = "IT equipment (Non-UK)")
	WebElement ITEquipmentNonUKTab;

	@FindBy(linkText = "System access")
	WebElement SystemAccessTab;

	@FindBy(linkText = "New employee")
	WebElement NewEmployeeTab;

	@FindBy(linkText = "Software")
	WebElement SoftwareTab;

	@FindBy(id = "footer-mastfoot")

	private WebElement footer;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public ShoppingBasketPage clickBasketTab() {
		switchToNavigationMenu();
		click(BasketTab);
		switchToFramePFFrame();
		return new ShoppingBasketPage();
	}
	
	public EmailAndIMPage clickEmailIMTab() {
		switchToNavigationMenu();
		click(EmailIMTab);
		switchToFramePFFrame();
		return new EmailAndIMPage();
	}

	public PhonePage clickPhoneTab() {
		switchToNavigationMenu();
		click(PhoneTab);
		switchToFramePFFrame();
		return new PhonePage();

	}

	public PhonePage clickPhoneUSTab() {
		switchToNavigationMenu();
		click(PhoneUSTab);
		switchToFramePFFrame();
		return new PhonePage();

	}

	public ITEquipmentPage clickITEquipmentTab() {
		switchToNavigationMenu();
		click(ITEquipmentTab);
		switchToFramePFFrame();
		return new ITEquipmentPage();

	}

	public ITEquipmentPage clickITEquipmentNonUKTab() {
		switchToNavigationMenu();
		click(ITEquipmentNonUKTab);
		switchToFramePFFrame();
		return new ITEquipmentPage();

	}

	public SystemAccessPage clickOnSystemAccessTab() {
		switchToNavigationMenu();
		click(SystemAccessTab);
		switchToFramePFFrame();
		return new SystemAccessPage();

	}

	public NewEmployeePage clickOnNewEmployeeTab() {
		switchToNavigationMenu();
		click(NewEmployeeTab);
		switchToFramePFFrame();
		return new NewEmployeePage();

	}

	public SoftwarePage clickOnsoftwareTab() {
		switchToNavigationMenu();
		click(SoftwareTab);
		switchToFramePFFrame();
		return new SoftwarePage();

	}

	public void verfiyAllTabsAreDisplayed() {
		switchToNavigationMenu();
		List<WebElement> navMenuList = driver.findElements(By.tagName("a"));
		{
			for (int i = 1; i < navMenuList.size(); i++) {
				switchToNavigationMenu();
				navMenuList.get(i).click();
				switchToFramePFFrame();
				WebElement footerfound = wait.until(ExpectedConditions.visibilityOf(footer));
				if (!footerfound.isDisplayed()) {
					Assert.assertTrue(false, "Tab is not displayed.[" + navMenuList.get(i).getText() + "]");
				}

			}

		}

	}

}
