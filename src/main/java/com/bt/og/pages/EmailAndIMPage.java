package com.bt.og.pages;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.bt.og.base.TestBase;

public class EmailAndIMPage extends TestBase {
	ShoppingBasketPage shoppingBasketPage = new ShoppingBasketPage();
	HomePage homePage = new HomePage();

	@FindBy(xpath = ".//h2")
	public WebElement title;

	@FindBy(linkText = "Standard email account")
	public WebElement StandardEmailAccount;

	@FindBy(linkText = "Mailbox expansion and reduction")
	public WebElement MailboxExpansionAndReduction;

	@FindBy(linkText = "Functional accounts")
	public WebElement FunctionalAccounts;

	@FindBy(linkText = "Enterprise Messenger")
	public WebElement EnterpriseMessenger;

	@FindBy(linkText = "Incoming fax")
	public WebElement IncomingFax;

	@FindBy(linkText = "Email address changes")
	public WebElement EmailAddressChanges;

	@FindBy(linkText = "Secure email (digital signature & encryption)")
	public WebElement SecureEmail;

	@FindBy(linkText = "Register third party email address on Desktop Directory")
	public WebElement RegisterThirdPartyEmail;

	@FindBy(linkText = "TLS encryption services")
	public WebElement TLSEncryptionServices;

	public EmailAndIMPage() {
		PageFactory.initElements(driver, this);
	}

	public void clickOnStandardEmailAccount() {
		click(StandardEmailAccount);
	}

	public void clickOnMailboxExpansionAndReduction() {
		click(MailboxExpansionAndReduction);
	}

	public void clickOnFunctionalAccounts() {
		click(FunctionalAccounts);
	}

	public void clickOnEnterpriseMessenger() {
		click(EnterpriseMessenger);
	}

	public void clickOnIncomingFax() {
		click(IncomingFax);
	}

	public void clickOnEmailAddressChanges() {
		click(EmailAddressChanges);
	}

	public void clickOnSecureEmail() {
		click(SecureEmail);
	}

	public void clickOnRegisterThirdPartyEmail() {
		click(RegisterThirdPartyEmail);
	}

	public void clickOnTLSEncryptionServices() {
		click(TLSEncryptionServices);

	}

	public boolean verfiyStandardEmailAccount() {
		boolean productFound = false;
		click(StandardEmailAccount);
		String title = driver.findElement(By.tagName("h2")).getText();
		Assert.assertEquals(title, "Standard email account");
		WebElement emailActn = driver.findElement(By.id("Emailacc"));
		List<String> expectedoptions = new LinkedList<String>();

		expectedoptions.add("Select an option...");
		expectedoptions.add("New account required");
		expectedoptions.add("Delete my personal mailbox");
		expectedoptions.add("Re-enable my existing email account");
		Select select = new Select(emailActn);
		List<WebElement> list = select.getOptions();

		boolean found = false;
		for (int i = 0; i < expectedoptions.size(); i++) {
			found = false;
			for (int j = 0; j < list.size(); j++) {
				if (!found && list.get(j).getText().contains(expectedoptions.get(i))) {
					found = true;
				}
			}
			if (!found) {
				Assert.assertTrue(false, expectedoptions.get(i) + " option are not present");
			}

		}
		// Adding RE-ENABLE PERSONAL MAILBOX to basket.
		select.selectByVisibleText(expectedoptions.get(3));
		WebElement continueBtn = driver.findElement(By.xpath(".//input[@type='submit' and @value='Continue']"));
		click(continueBtn);
		WebElement InterchangeAccAgree = driver.findElement(By.id("InterchangeAccAgree"));
		WebElement addToBasket = driver.findElement(By.xpath(".//input[@type='submit' and @value='Add to basket']"));
		wait.until(ExpectedConditions.visibilityOf(InterchangeAccAgree));
		click(InterchangeAccAgree);
		click(addToBasket);
		switchToFramePFFrame();
		waitForElementToBeVisible(shoppingBasketPage.emptyBasketBtn, 5);
		productFound = shoppingBasketPage.searchForProductInTable("RE-ENABLE PERSONAL MAILBOX");
		Assert.assertTrue(productFound, expectedoptions.get(3) + " is not added to shopping basket");
		return true;

	}

	public boolean verfiyFunctionalAccount() {
		boolean productFound = false;
		click(FunctionalAccounts);
		WebElement orderAccount = driver.findElement(By.name("Order"));
		driver.findElement(By.name("Amend"));
		driver.findElement(By.name("CANCEL"));
		boolean found = false;
		if (!found) {
			waitForElementToBeVisible(orderAccount, 10);
			click(orderAccount);
			waitForElementToBeVisible(driver.findElement(By.id("Acc_Type")), 5);
			Select select = new Select(driver.findElement(By.id("Acc_Type")));
			List<WebElement> dropdown = select.getOptions();
			for (int i = 0; i < dropdown.size(); i++) {
				select.selectByVisibleText(dropdown.get(i).getText());

			}
			
			
			click(driver.findElement(By.xpath(".//button[@name='Btn_Add']")));
			waitForElementToBeVisible(driver.findElement(By.xpath(".//button[@name='Btn_Add']")), 10);
			click(driver.findElement(By.xpath(".//button[@name='Btn_Add']")));
			
			WebElement exdirect = driver.findElement(By.id("exdirect"));
			waitForElementToBeVisible(exdirect, 10);
			Select option = new Select(exdirect);
			option.selectByVisibleText("YES");
			WebElement prefBoatID123 = driver.findElement(By.id("prefBoatID123"));
			prefBoatID123.sendKeys("kxxzzz67");
			WebElement prefGAL123 = driver.findElement(By.id("prefGAL123"));
			prefGAL123.sendKeys("abcdefgh");
			WebElement domainName = driver.findElement(By.id("domainName"));
			domainName.sendKeys("@bt.com");
			click(driver.findElement(By.xpath(".//button[@name='Btn_Add']")));
			WebElement EIN1 = driver.findElement(By.name("EIN1"));
			waitForElementToBeVisible(EIN1, 10);
			EIN1.sendKeys("612092646");
			click(driver.findElement(By.xpath(".//button[@name='Btn_Add']")));
			WebElement agree = driver.findElement(By.xpath(".//input[@id='FuncAccAgree']"));
			waitForElementToBeVisible(agree, 10);
			click(agree);
			click(driver.findElement(By.xpath(".//button[@name='Btn_Add']")));
			switchToFramePFFrame();
			waitForElementToBeVisible(shoppingBasketPage.emptyBasketBtn, 5);
			productFound = shoppingBasketPage.searchForProductInTable("Order Target Functional Account");

		}
		return productFound;

	}

	public boolean verifyEnterPriseMessenger() {
		boolean productFound = false;
		clickOnEnterpriseMessenger();
		WebElement SelectEMOpt = driver.findElement(By.name("SelectEMOpt"));
		Select select = new Select(SelectEMOpt);
		List<WebElement> list = select.getOptions();

		List<String> expectedOption = new LinkedList<String>();
		expectedOption.add("Please select an Enterprise Messenger option");
		expectedOption.add("Enterprise Messenger normal account");
		expectedOption.add("Enterprise Messenger corporate account");
		expectedOption.add("Add SMTP addresses");
		expectedOption.add("Remove SMTP addresses");
		expectedOption.add("Cease account");
		for (WebElement element : list) {
			select.selectByVisibleText(element.getText());
		}
		select.selectByVisibleText("Cease account");
		WebElement continueBtn1 = driver.findElement(By.name("Btn_Submit"));
		waitForElementToBeVisible(continueBtn1, 5);
		click(continueBtn1);
		WebElement continueBtn2 = driver.findElement(By.name("Btn_Submit"));
		waitForElementToBeVisible(continueBtn2, 5);
		click(continueBtn2);
		
		WebElement Add_Submit = driver.findElement(By.name("Add_Submit"));
		checkRailoError();
		waitForElementToBeVisible(Add_Submit, 5);
		click(driver.findElement(By.id("EnterpriseAccAgree")));
		click(Add_Submit);
			
		switchToFramePFFrame();
		waitForElementToBeVisible(shoppingBasketPage.emptyBasketBtn, 5);
		productFound = shoppingBasketPage.searchForProductInTable("Enterprise Messenger - Disabling Account");
		return productFound;
	}

	public boolean verifyIncomingFax() {
		clickOnIncomingFax();
		WebElement orderFax = driver.findElement(By.name("Order"));
		WebElement amend = driver.findElement(By.name("Amend"));
		WebElement cancel = driver.findElement(By.name("Cancel"));
		if (!orderFax.isDisplayed() && !amend.isDisplayed() && !cancel.isDisplayed()) {
			Assert.assertTrue(false, "All butons not displayed.");
		}
		click(orderFax);
		WebElement boatId = driver.findElement(By.id("BoatID"));
		WebElement justification = driver.findElement(By.id("Justification"));
		WebElement ContinueBtn = driver.findElement(By.name("Continue"));
		if (boatId.isDisplayed() && justification.isDisplayed() && ContinueBtn.isDisplayed()) {
			type(boatId, "KUMARN67");
			type(justification, "Test Order");
			click(ContinueBtn);
		} else {
			Assert.assertTrue(false, "All butons not displayed in incomefaxing.");

		}
		WebElement ContinueSubmitBtn = driver.findElement(By.name("Submit"));
		click(ContinueSubmitBtn);
		WebElement FuncAccAgree = driver.findElement(By.name("FuncAccAgree"));
		WebElement addToBasket = driver.findElement(By.xpath(".//input[@value='Add to basket']"));
		click(FuncAccAgree);
		click(addToBasket);
		return true;
	}

	public boolean verifyEmailAddressChange() {

		click(EmailAddressChanges);
		WebElement continueBtn = driver.findElement(By.xpath(".//input[@value='Continue']"));
		if (continueBtn.isDisplayed()) {
			click(continueBtn);
		} else {
			Assert.assertTrue(false, "All option not displayed in emailAddressChange.");
		}
		WebElement newFriendlyName = driver.findElement(By.name("NewFriendlyName"));
		WebElement spam = driver.findElement(By.id("spam"));
		WebElement freeText = driver.findElement(By.id("freeText"));
		WebElement addToBasket = driver.findElement(By.xpath(".//input[@value='Add to basket']"));
		if (newFriendlyName.isDisplayed() && spam.isDisplayed() && freeText.isDisplayed()
				&& addToBasket.isDisplayed()) {
			type(newFriendlyName, "colin.a.fletcher@bt.com");
			//for testing commneted
			/*click(spam);
			type(freeText, "Test Order");
			click(addToBasket);*/
		} else {
			Assert.assertTrue(false, "All option not displayed in emailAddressChange.");
		}
		return true;

	}

	public boolean verifyRegisterThirdPartyEmail() {
		click(RegisterThirdPartyEmail);
		WebElement continueBtn = driver.findElement(By.xpath(".//input[@value='Continue']"));
		if (continueBtn.isDisplayed()) {
			click(continueBtn);
		} else {
			Assert.assertTrue(false, "Register Third Party Email Continue button not displayed.");
		}
		WebElement emailAddress = driver.findElement(By.id("EmailAddress"));
		WebElement freeText = driver.findElement(By.name("freeText"));
		WebElement addToBasket = driver.findElement(By.xpath(".//input[@value='Add to basket']"));
		if (emailAddress.isDisplayed() && freeText.isDisplayed() && addToBasket.isDisplayed()) {
			type(emailAddress, "karen.rodger");
			type(freeText, "Test Order.");
			click(addToBasket);

		} else {
			Assert.assertTrue(false, "All option not displayed in RegisterThirdPartyEmail.");

		}
		return true;
	}

}
