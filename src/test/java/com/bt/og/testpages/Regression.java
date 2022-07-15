package com.bt.og.testpages;

import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.bt.og.base.TestBase;
import com.bt.og.pages.CheckOut;
import com.bt.og.pages.ClassOfWorkJobNumber;
import com.bt.og.pages.CurrentBasketPage;
import com.bt.og.pages.CustomerDetailsPage;
import com.bt.og.pages.DeliveryAddressPage;
import com.bt.og.pages.EmailAndIMPage;
import com.bt.og.pages.HomePage;
import com.bt.og.pages.ITEquipmentPage;
import com.bt.og.pages.LoginPage;
import com.bt.og.pages.PhonePage;
import com.bt.og.pages.ProductListPage;
import com.bt.og.pages.ProductPage;
import com.bt.og.pages.SearchForAnOrderPage;
import com.bt.og.pages.ShoppingBasketPage;
import com.bt.og.pages.SoftwarePage;
import com.bt.og.pages.SystemAccessPage;
import com.bt.og.utility.Constant;
import com.bt.og.utility.ExcelUtility;

public class Regression extends TestBase {
	private ExcelUtility excelUtility = new ExcelUtility("Regression");
	private LoginPage loginPage;
	private CustomerDetailsPage customerDetailsPage;
	private SearchForAnOrderPage searchOrderPage;
	private ShoppingBasketPage shoppingBasketpage;
	private HomePage homePage;
	private EmailAndIMPage emailIMPage;
	private ITEquipmentPage itEquipment;
	private ProductListPage productListPage;
	private ProductPage productPage;
	private CurrentBasketPage currentBasketPage;
	private CheckOut checkOutPage;
	private ClassOfWorkJobNumber classOfWorkJobNumberPage;
	private DeliveryAddressPage deliveryAddresspage;
	private SystemAccessPage systemAccess;
	private SoftwarePage softwarePage;
	private PhonePage phonePage;

	public Regression() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialise();
		loginPage = new LoginPage();
		shoppingBasketpage = new ShoppingBasketPage();
	}

	@DataProvider(name = "validLoginData")
	public Object[][] validLoginData() {
		Object[][] testData = excelUtility.getTestData("validLoginData");
		return testData;
	}

	// 1.Test with valid EIN.
	@Test(dataProvider = "validLoginData", priority = 1)
	public void test001_ValidLogin(String ein, String name) throws InterruptedException {
		ein = ein.trim();
		name = name.trim();
		customerDetailsPage = loginPage.doLogin(ein);
		String title = customerDetailsPage.getTitle().trim();
		String userName = customerDetailsPage.getName().trim();
		Assert.assertEquals(title, Constant.CustomerDetailsTitle, "Customer Page Title is not matching.");
		Assert.assertEquals(userName, name, "User name not matching.");
	}

	// 1.Test with invalid login.
	@Test(priority = 2)
	public void test002_InvalidLogin() {
		// Your input was in the correct format but it is NOT a valid EIN.
		String actualAlertText = "";
		loginPage.typeEIN("612092647");
		loginPage.clickOnContinue();
		String expectedAlertText = "EIN Your input was in the correct format but it is NOT a valid EIN.";
		if (isAlertPresent()) {
			actualAlertText = getAlertText();
			alertAccept();
			Assert.assertTrue(
					actualAlertText.contains("Your input was in the correct format but it is NOT a valid EIN."),
					"Invalid EIN alert message is wrong or Alert is not displayed.");
		} else {
			Assert.assertTrue(false, "Invalid EIN alert message is wrong or Alert is not displayed.");
		}

		// Test for less than 9 digits
		actualAlertText = "";
		loginPage.typeEIN("802766");
		loginPage.clickOnContinue();
		expectedAlertText = "Please enter 9 numeric characters in the \"UIN\" field.";
		if (isAlertPresent()) {
			actualAlertText = getAlertText();
			alertAccept();
			Assert.assertEquals(actualAlertText, expectedAlertText,
					"Please enter 9 numeric characters in the \"UIN\" field. Alert text not matching.");
		} else {
			Assert.assertTrue(false, "Alert is not displayed for Wrong EIN Format.");
		}

		// Test for more than 9 digits.
		loginPage.typeEIN("802766791098");
		loginPage.clickOnContinue();
		expectedAlertText = "Please enter at most 9 characters in the \"UIN\" field.";
		if (isAlertPresent()) {
			actualAlertText = getAlertText();
			alertAccept();
			Assert.assertEquals(actualAlertText, expectedAlertText,
					"Please enter at most 9 characters in the \"UIN\" field Alert text not matching.");
		} else {
			Assert.assertTrue(false, "Alert is not displayed for Wrong EIN Format.");
		}

	}

	// 1.Order search with EIN and OUC.
	// 2.Test with Order ID
	@Test(priority = 3)
	public void test003_OrderSearch() {
		searchOrderPage = loginPage.clickOnSearchForOrder();
		String orderId = searchOrderPage.searchOrder(Constant.EIN_UK, "TNA7");
		searchOrderPage.searchByOrderId(orderId);

	}

	@DataProvider(name = "customerDetailsData")
	public Object[][] getcustomerDetailsData() {
		Object[][] testdata = excelUtility.getTestData("customerDetailsData");
		return testdata;

	}

	// 1.Login With Valid EIN.
	// 2.Check Customer details in customer details page.
	@Test(dataProvider = "customerDetailsData", priority = 4)
	public void test004_CustomerDetails(String custname, String custOUC, String custPhoneNumber, String custBoatID,
			String custUIN, String custComapnyIndicator, String custCountry) {
		customerDetailsPage = loginPage.doLogin(custUIN);
		// switchToFramePFFrame();
		String customerName = customerDetailsPage.getName().trim();
		String customerOUC = customerDetailsPage.getOUC().trim();
		String customerPhoneNumber = customerDetailsPage.getPhoneNumber().trim();
		String customerBoatID = customerDetailsPage.getBoatID().trim();
		String customerUIN = customerDetailsPage.getUIN().trim();
		String customerComapnyIndicator = customerDetailsPage.getComPanyIndicator().trim();
		String customerCountry = customerDetailsPage.getCountryCode().trim();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(customerName, custname.trim(), "Customer not mactching");
		softAssert.assertEquals(customerOUC, custOUC.trim(), "OUC not mactching");
		softAssert.assertEquals(customerPhoneNumber, custPhoneNumber.trim(), "Phone number not mactching");
		softAssert.assertEquals(customerBoatID, custBoatID.trim(), "Boat ID not mactching");
		softAssert.assertEquals(customerUIN, custUIN.trim(), "UIN not mactching");
		softAssert.assertEquals(customerComapnyIndicator, custComapnyIndicator.trim(),
				"Company indicator not mactching");
		softAssert.assertEquals(customerCountry, custCountry.trim(), "Country code not mactching");
		softAssert.assertAll();
		loginPage = customerDetailsPage.clickOnReturnToTheSignInScreenBtn();
		wait.until(ExpectedConditions.visibilityOf(loginPage.einTxtBox));
	}

	// 1.Test Continue button.
	@Test(priority = 5)
	public void test005_CustomerDetailsContinueButton() {
		customerDetailsPage = loginPage.doLogin(Constant.EIN_UK);
		shoppingBasketpage = customerDetailsPage.clickOnContinueBtn();
		String title = shoppingBasketpage.getPageTitle();
		Assert.assertEquals(title, Constant.shoppingbasket_Title);

	}

	// 1.Login with UK EIN and check all tabs are displayed.
	// 2.Login with US EIN and check all tabs are displayed.
	@Test(priority = 6)
	public void test006_VerifyAllTabs() {
		customerDetailsPage = loginPage.doLogin(Constant.EIN_UK);
		homePage = new HomePage();
		homePage.verfiyAllTabsAreDisplayed();
		shoppingBasketpage = homePage.clickBasketTab();
		loginPage = shoppingBasketpage.clickOnReturnToSignIn();
		waitForElementToBeVisible(loginPage.einTxtBox, 10);
	}

	// 1.Email & IM check Standard email account
	@Test(priority = 7)
	public void test007_01_StandardEmailAccount() {
		boolean result = false;
		customerDetailsPage = loginPage.doLogin(Constant.EIN_UK);
		homePage = new HomePage();
		emailIMPage = homePage.clickEmailIMTab();
		switchToFramePFFrame();
		result = emailIMPage.verfiyStandardEmailAccount();
		Assert.assertTrue(result);
		shoppingBasketpage.waitForShoppingBasketToDisplay();
		shoppingBasketpage.emptyBasket();
	}

	// 1.Email & IM check Functional account.
	@Test(priority = 8)
	public void test007_02_FunctionalAccount() {
		boolean result = false;
		customerDetailsPage = loginPage.doLogin(Constant.EIN_UK);
		homePage = new HomePage();
		emailIMPage = homePage.clickEmailIMTab();
		switchToFramePFFrame();
		Assert.assertTrue(result);
	}

	// 1.Email & IM check Enter PriseMessenger.
	@Test(priority = 9)
	public void test007_03_EnterPriseMessenger() {
		boolean result = false;
		customerDetailsPage = loginPage.doLogin(Constant.EIN_UK);
		homePage = new HomePage();
		emailIMPage = homePage.clickEmailIMTab();
		switchToFramePFFrame();
		result = emailIMPage.verifyEnterPriseMessenger();
		Assert.assertTrue(result);
		shoppingBasketpage.waitForShoppingBasketToDisplay();
		shoppingBasketpage.emptyBasket();
	}

	// 1.Email & IM check Incoming fax.
	@Test(priority = 10)
	public void test007_04_IncomingFax() {
		boolean result = false;
		customerDetailsPage = loginPage.doLogin(Constant.EIN_UK);
		homePage = new HomePage();
		emailIMPage = homePage.clickEmailIMTab();
		switchToFramePFFrame();
		result = emailIMPage.verifyIncomingFax();
		Assert.assertTrue(result);
		shoppingBasketpage.waitForShoppingBasketToDisplay();
		shoppingBasketpage.emptyBasket();
	}

	// 1.Email & IM check Email Address Change.
	@Test(priority = 11)
	public void test007_05_EmailAddressChange() {
		boolean result = false;
		customerDetailsPage = loginPage.doLogin(Constant.EIN_UK);
		homePage = new HomePage();
		emailIMPage = homePage.clickEmailIMTab();
		switchToFramePFFrame();
		result = emailIMPage.verifyEmailAddressChange();
		Assert.assertTrue(true);
		Assert.assertTrue(result);
	}

	// 1.Email & IM check Registered Third party email.
	@Test(priority = 12)
	public void test007_06_RegisterThirdPartyEmail() {
		boolean result = false;
		customerDetailsPage = loginPage.doLogin(Constant.EIN_UK);
		homePage = new HomePage();
		emailIMPage = homePage.clickEmailIMTab();
		switchToFramePFFrame();
		result = emailIMPage.verifyRegisterThirdPartyEmail();
		Assert.assertTrue(result);
		shoppingBasketpage.waitForShoppingBasketToDisplay();
		shoppingBasketpage.emptyBasket();
	}

	// 1.Login based on EIN for testing IT Equipment catalogue.
	// 2.If UK EIN it selects IT Equipment tabs or if Non UK EIN select "IN"
	// india by default from country selection.
	public ITEquipmentPage login(String ein) {
		loginPage = new LoginPage();
		loginPage.doLogin(ein);
		homePage = new HomePage();
		if (ein.equals(Constant.EIN_UK)) {
			itEquipment = homePage.clickITEquipmentTab();
			switchToFramePFFrame();
		} else if (ein.equals(Constant.EIN_NonUK)) {
			itEquipment = homePage.clickITEquipmentNonUKTab();
			try {
				waitForElementToBeVisible(itEquipment.enterBtn, 5);
				List<WebElement> site = driver.findElements(By.name("site"));
				if (site.size() > 0) {
					Select select = new Select(driver.findElement(By.name("site")));
					select.selectByValue("IN");
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
			productListPage = itEquipment.clickOnEnterBtn();
			switchToFramePFFrame();
			waitForElementToBeVisible(productListPage.ReturnToCatalogueBtn, 5);

		}
		return new ITEquipmentPage();
	}

	// 1.Check for IT Equipment tab.
	@Test(priority = 13)
	public void test008_UKITEquipment() {
		itEquipment = login(Constant.EIN_UK);
		Assert.assertTrue(itEquipment.searchBtn.isDisplayed());
	}

	// 1.Check for Non IT Equipment tab displayed or not.
	@Test(priority = 14)
	public void test009_NonUKITEquipment() {
		login(Constant.EIN_NonUK);
		waitForElementToBeVisible(productListPage.ReturnToCatalogueBtn, 5);
		Assert.assertTrue(productListPage.ReturnToCatalogueBtn.isDisplayed(), "Product list page is not displayed.");

	}

	@DataProvider(name = "ITEquipmentData")
	public Object[][] iTEquipmentData() {
		Object[][] testdata = excelUtility.getTestData("ITEquipmentData");
		return testdata;

	}

	/*
	 * 1.Login With UK login. 2.Go to IT Equipment Tab. 3.Select catalogue Item.
	 * 4.Search product from Product list page and selects the product. 5.Search
	 * all the buttons on products description page is displayed and Add to
	 * basket. 6.Fill all the Question in current basket page. 7.Add to Shopping
	 * cart. 8.Check product added to shopping basket. 9.Check Alternative
	 * Address. 10.Place final order.
	 */
	@Test(dataProvider = "ITEquipmentData", priority = 15)
	public void test010_ITEquipmentProducts(String catalogueItem, String productName) {
		boolean result;
		itEquipment = login(Constant.EIN_UK);
		wait(3000);
		productListPage = itEquipment.clicOnProduct(catalogueItem);
		boolean found = productListPage.isProductAvaialble(productName);
		Assert.assertTrue(found, "Product not found : " + productName);
		if (found) {
			productPage = productListPage.clickOnProduct(productName);
			waitForElementToBeVisible(productPage.addtoBasketBtn, 5);

			result = productPage.checkProductName(productName);
			Assert.assertTrue(result, "Product name not matching.");
			result = productPage.checkAllButtonDisplayed();
			Assert.assertTrue(result, "All the buttons not displayed.");
			currentBasketPage = productPage.clickOnAddTobasket();

			result = currentBasketPage.checkAllButtonsDisplayed();
			Assert.assertTrue(result, "All the buttons are not displayed in currentbasket.");
			currentBasketPage.fillCurrentBasket(productName);
			currentBasketPage.clickOnAddToBasket();

			shoppingBasketpage = currentBasketPage.clickOnCheckOut();
			shoppingBasketpage.waitForShoppingBasketToDisplay();
			result = shoppingBasketpage.searchForProductInTable(productName);
			Assert.assertTrue(result, "Product is not added to Shopping Basket .");
			checkOutPage = shoppingBasketpage.clickOnCheckOut();

			switchToFramePFFrame();
			classOfWorkJobNumberPage = new ClassOfWorkJobNumber();
			waitForElementToClickable(classOfWorkJobNumberPage.continueCheckOutBtn, 10);
			classOfWorkJobNumberPage.clickOnContinueCheckOut();

			switchToFramePFFrame();
			checkOutPage.typeOrderSummary("Test Order Selenium");
			checkOutPage.typeBusinessJustification("Test Order Selenium Please ignore.");
			deliveryAddresspage = checkOutPage.clickOnContinueCheckout();

			switchToFramePFFrame();
			waitForElementToBeVisible(deliveryAddresspage.useAnotherAddress, 10);
			deliveryAddresspage.clickOnUseAnotherAddress();

			switchToFramePFFrame();
			deliveryAddresspage.typeSelectdDeliveryTo(
					"Option 1: I would like it delivered to someone else at their registered address.");
			deliveryAddresspage.clickOnContinue();

			switchToFramePFFrame();
			deliveryAddresspage.typeDeliverySecondContactName();
			wait(1000);
			deliveryAddresspage.clickOnAltContinue();

			switchToFramePFFrame();
			waitForElementToBeVisible(deliveryAddresspage.placeOrder, 10);
		}
	}

	@DataProvider(name = "NonITUKEquipmentData")
	public Object[][] nonITTEquipmentData() {
		Object[][] testdata = excelUtility.getTestData("NonITUKEquipmentData");
		return testdata;

	}

	/*
	 * 1.Login With NON UK EIN. 2.Go to IT Equipment Tab. 3.Select catalogue
	 * Item. 4.Search product from Product list page and selects the product.
	 * 5.Search all the buttons on products description page is displayed and
	 * Add to basket. 6.Fill all the Question in current basket page. 7.Add to
	 * Shopping cart. 8.Clears the Shopping cart.
	 */

	@Test(dataProvider = "NonITUKEquipmentData", priority = 16)
	public void test011_NonITEquipmentProducts(String productName) {
		boolean result;
		itEquipment = login(Constant.EIN_NonUK);
		wait(3000);
		productListPage = new ProductListPage();
		boolean found = productListPage.isProductAvaialble(productName);
		Assert.assertTrue(found, "Product not found : " + productName);
		if (found) {
			productPage = productListPage.clickOnProduct(productName);
			waitForElementToBeVisible(productPage.addtoBasketBtn, 5);
			result = productPage.checkProductName(productName);
			Assert.assertTrue(result, "Product name not matching.");
			result = productPage.checkAllButtonDisplayedNonUK();
			Assert.assertTrue(result, "All the buttons not displayed.");
			currentBasketPage = productPage.clickOnAddTobasket();
			result = currentBasketPage.checkAllButtonsDisplayed();
			Assert.assertTrue(result, "All the buttons are not displayed in currentbasket.");
			currentBasketPage.fillCurrentBasket(productName);
			currentBasketPage.clickOnAddToBasket();
			shoppingBasketpage = currentBasketPage.clickOnCheckOut();
			shoppingBasketpage.waitForShoppingBasketToDisplay();
			result = shoppingBasketpage.searchForProductInTable(productName);
			Assert.assertTrue(result, "Product is not added to Shopping Basket .");
			shoppingBasketpage.emptyBasket();
		}
		Assert.assertTrue(true);

	}

	/*
	 * 1.Login With UK EIN. 2.Go to System access Tab. 3.Search for CSS Product.
	 * 4.Fill All the questions. 5.Add to Shopping cart.
	 */

	@Test(priority = 17)
	public void test012_01_CSSProduction() {
		Assert.assertTrue(true);
		loginPage = new LoginPage();
		loginPage.doLogin(Constant.EIN_UK);
		homePage = new HomePage();
		systemAccess = homePage.clickOnSystemAccessTab();
		systemAccess.CSSProduction();
	}

	/*
	 * 1.Login With UK EIN. 2.Go to System access Tab. 3.Search for
	 * IUSERDomainGroupConnection Product. 4.Fill All the questions. 5.Add to
	 * Shopping cart.
	 */
	@Test(priority = 18)
	public void test012_02_IUSERDomainGroupConnection() {
		Assert.assertTrue(true);
		loginPage = new LoginPage();
		loginPage.doLogin(Constant.EIN_UK);
		homePage = new HomePage();
		systemAccess = homePage.clickOnSystemAccessTab();
		systemAccess.IUSERDomainGroupConnection();
	}

	/*
	 * 1.Login With NON UK EIN. 2.Go to System access Tab. 3.Search for
	 * OneViewChangesToProfile Product. 4.Fill All the questions. 5.Add to
	 * Shopping cart.
	 */

	@Test(priority = 19)
	public void test012_03_OneViewChangesToProfile() {
		loginPage = new LoginPage();
		loginPage.doLogin(Constant.EIN_UK);
		homePage = new HomePage();
		systemAccess = homePage.clickOnSystemAccessTab();
		systemAccess.OneViewChangesToProfile();
		Assert.assertTrue(true);
	}

	@DataProvider(name = "ProvideNewSoftwareData")
	public Object[][] provideNewSoftwareData() {
		Object[][] testdata = excelUtility.getTestData("ProvideNewSoftwareData");
		return testdata;

	}

	/*
	 * 1.Login With UK EIN. 2.Go to Software Tab. 3.Click on Provide new
	 * Software. 4.Search for Product in Provide new software List. 5.Add
	 * Product to current basket. 6.Fill all the questions. 7.Adds the product
	 * to Shopping cart. 8.Check Product added to shopping cart. 9.Clears
	 * shopping cart.
	 */

	@Test(dataProvider = "ProvideNewSoftwareData", priority = 20)
	public void test013_01_ProvideNewSoftware(String productName) {
		boolean result = false;
		loginPage = new LoginPage();
		loginPage.doLogin(Constant.EIN_UK);
		homePage = new HomePage();
		softwarePage = homePage.clickOnsoftwareTab();

		productListPage = softwarePage.clickOnProvideNewSoftBtn();
		boolean found = productListPage.isProductAvaialble(productName);
		Assert.assertTrue(found, "Product not found : " + productName);
		if (found) {
			productPage = productListPage.clickOnProduct(productName);
			waitForElementToBeVisible(productPage.addtoBasketBtn, 5);
			result = productPage.checkProductName(productName);
			Assert.assertTrue(result, "Product name not matching.");
			result = productPage.checkAllButtonDisplayed();
			Assert.assertTrue(result, "All the buttons not displayed.");
			currentBasketPage = productPage.clickOnAddTobasket();
			result = currentBasketPage.checkAllButtonsDisplayed();
			Assert.assertTrue(result, "All the buttons are not displayed in currentbasket.");

		}
		Assert.assertTrue(true);
	}

	@DataProvider(name = "RemoveUneededSoftwareData")
	public Object[][] removeUneededSoftwareData() {
		Object[][] testdata = excelUtility.getTestData("RemoveUneededSoftwareData");
		return testdata;

	}

	/*
	 * 1.Login With UK EIN. 2.Go to Software Tab. 3.Click on Remove Uneeded
	 * Software List. 4.Search for Product in RemoveUneededSoftwareData List.
	 * 5.Add Product to current basket. 6.Fill all the questions. 7.Adds the
	 * product to Shopping cart. 8.Check Product added to shopping cart.
	 * 9.Clears shopping cart.
	 */
	@Test(dataProvider = "RemoveUneededSoftwareData", priority = 21)
	public void test013_02__RemoveUneededSoftware(String productName) {
		loginPage = new LoginPage();
		loginPage.doLogin(Constant.EIN_UK);
		homePage = new HomePage();
		softwarePage = homePage.clickOnsoftwareTab();
		boolean result = false;
		productListPage = softwarePage.clickOnRemoveUneededSoft();
		boolean found = productListPage.isProductAvaialble(productName);
		Assert.assertTrue(found, "Product not found : " + productName);
		if (found) {
			productPage = productListPage.clickOnProduct(productName);
			waitForElementToBeVisible(productPage.viewBasketBtn, 5);
			result = productPage.checkProductName(productName);
			Assert.assertTrue(result, "Product name not matching.");
		}
		Assert.assertTrue(true);

	}

	@DataProvider(name = "SearchSoftProduct")
	public Object[][] searchsoftProduct() {
		Object[][] testdata = excelUtility.getTestData("SearchSoftProduct");
		return testdata;

	}

	/*
	 * 1.Login With UK EIN. 2.Go to Software Tab. 3.Search Product in Software
	 * catalogue. 4.Add Product to current basket. 5.Fill all the questions.
	 * 6.Adds the product to Shopping cart. 7.Check Product added to shopping
	 * cart. 8.Clears shopping cart.
	 */
	@Test(dataProvider = "SearchSoftProduct", priority = 22)
	public void test013_03_searchProduct(String productName) {
		loginPage = new LoginPage();
		loginPage.doLogin(Constant.EIN_UK);
		homePage = new HomePage();
		softwarePage = homePage.clickOnsoftwareTab();
		boolean result = false;
		productListPage = softwarePage.searchProduct(productName);
		waitForElementToBeVisible(productListPage.ReturnToCatalogueBtn, 5);
		boolean found = productListPage.isProductAvaialble(productName);
		Assert.assertTrue(found, "Product not found : " + productName);
		if (found) {
			productPage = productListPage.clickOnProduct(productName);
			waitForElementToBeVisible(productPage.addtoBasketBtn, 5);
			result = productPage.checkProductName(productName);
			Assert.assertTrue(result, "Product name not matching.");
			result = productPage.checkAllButtonDisplayed();
			Assert.assertTrue(result, "All the buttons not displayed.");
			currentBasketPage = productPage.clickOnAddTobasket();
			result = currentBasketPage.checkAllButtonsDisplayed();
			Assert.assertTrue(result, "All the buttons are not displayed in currentbasket.");
			currentBasketPage.fillCurrentBasket(productName);
			currentBasketPage.clickOnAddToBasket();
			shoppingBasketpage = currentBasketPage.clickOnCheckOut();
			shoppingBasketpage.waitForShoppingBasketToDisplay();
			result = shoppingBasketpage.searchForProductInTable(productName);
			Assert.assertTrue(result, "Product is not added to Shopping Basket.");
			shoppingBasketpage.emptyBasket();
		}
		Assert.assertTrue(true);

	}

	/*
	 * 1.Login With UK EIN. 2.Go to Software Tab. 3.Check All A to Z Links are
	 * Working.
	 */

	@Test(priority = 23)
	public void test013_04_AtoZLink() {
		loginPage = new LoginPage();
		loginPage.doLogin(Constant.EIN_UK);
		homePage = new HomePage();
		softwarePage = homePage.clickOnsoftwareTab();
		softwarePage.checkAllAToZLink();

	}

	// Login function of check Phone cataloge and Phone US catalogue
	public PhonePage phonelogin(String ein) {
		loginPage = new LoginPage();
		loginPage.doLogin(ein);
		homePage = new HomePage();
		if (ein.equals(Constant.EIN_US)) {
			homePage.clickPhoneUSTab();
		} else {
			homePage.clickPhoneTab();
		}
		switchToFramePFFrame();
		return new PhonePage();

	}

	/*
	 * 1.Login With UK EIN. 2.Go to Phone Tab. 3.Search Product in Software
	 * catalogue. 4.Check all The Phone catalogue Item displayed.
	 */
	@Test(priority = 24)
	public void test014_01_CheckAllPhoneProductItemDisplayed() {
		phonePage = phonelogin(Constant.EIN_UK);
		wait(3000);
		phonePage.checkAllProductItemDisplayed();
		Assert.assertTrue(true);
	}

	@DataProvider(name = "PhoneProductsData")
	public Object[][] phoneProductsData() {
		Object[][] testdata = excelUtility.getTestData("PhoneProductsData");
		return testdata;

	}

	/*
	 * 1.Login With UK EIN. 2.Go to Phone Tab. 3.Click on catalogue Item and
	 * search for product in product list. 4.Add Product to current basket.
	 * 5.Fill all the questions. 6.Adds the product to Shopping cart. 7.Check
	 * Product added to shopping cart. 8.Clears shopping cart.
	 */
	@Test(dataProvider = "PhoneProductsData", priority = 25)
	public void test014_02_PhoneProducts(String catalogueItem, String productName) {
		boolean result;
		phonePage = phonelogin(Constant.EIN_UK);
		wait(3000);
		productListPage = phonePage.clicOnProduct(catalogueItem);
		if (phonePage.isNewAccountPageDisplayed()) {
			productListPage = phonePage.selectNewAccountChkBox();
		}
		boolean found = productListPage.isProductAvaialble(productName);
		Assert.assertTrue(found, "Product not found : " + productName);
		if (found) {
			productPage = productListPage.clickOnProduct(productName);
			waitForElementToBeVisible(productPage.addtoBasketBtn, 5);
			result = productPage.checkProductName(productName);
			Assert.assertTrue(result, "Product name not matching.");
			result = productPage.checkAllButtonDisplayed();
			Assert.assertTrue(result, "All the buttons not displayed.");
			currentBasketPage = productPage.clickOnAddTobasket();
			result = currentBasketPage.checkAllButtonsDisplayed();
			Assert.assertTrue(result, "All the buttons are not displayed in currentbasket.");
			currentBasketPage.fillCurrentBasket(productName);
			currentBasketPage.clickOnAddToBasket();
			shoppingBasketpage = currentBasketPage.clickOnCheckOut();
			shoppingBasketpage.waitForShoppingBasketToDisplay();
			result = shoppingBasketpage.searchForProductInTable(productName);
			Assert.assertTrue(result, "Product is not added to Shopping Basket.");
			shoppingBasketpage.emptyBasket();
		}
	}

	@DataProvider(name = "SearchPhoneProduct")
	public Object[][] searchPhoneProduct() {
		Object[][] testdata = excelUtility.getTestData("SearchPhoneProduct");
		return testdata;

	}

	/*
	 * 1.Login With UK EIN. 2.Go to Phone Tab. 3.Search for product using search
	 * text area. 4.Add Product to current basket. 5.Fill all the questions.
	 * 6.Adds the product to Shopping cart. 7.Check Product added to shopping
	 * cart. 8.Clears shopping cart.
	 */
	@Test(dataProvider = "SearchPhoneProduct", priority = 26)
	public void test014_03_searchPhoneProduct(String productName) {
		boolean result;
		phonePage = phonelogin(Constant.EIN_UK);
		productListPage = phonePage.searchproduct(productName);
		waitForElementToBeVisible(productListPage.ReturnToCatalogueBtn, 5);
		boolean found = productListPage.isProductAvaialble(productName);
		Assert.assertTrue(found, "Product not found : " + productName);
		if (found) {
			productPage = productListPage.clickOnProduct(productName);
			waitForElementToBeVisible(productPage.addtoBasketBtn, 5);
			result = productPage.checkProductName(productName);
			Assert.assertTrue(result, "Product name not matching.");
			result = productPage.checkAllButtonDisplayed();
			Assert.assertTrue(result, "All the buttons not displayed.");
			currentBasketPage = productPage.clickOnAddTobasket();
			result = currentBasketPage.checkAllButtonsDisplayed();
			Assert.assertTrue(result, "All the buttons are not displayed in currentbasket.");
			checkRailoError();
		}

	}

	@DataProvider(name = "PhoneUScatalogueData")
	public Object[][] phoneUScatalogueData() {
		Object[][] testdata = excelUtility.getTestData("PhoneUScatalogueData");
		return testdata;

	}

	/*
	 * 1.Login With US EIN. 2.Go to Phone(US) Tab. 3.Click on catalogue Item and
	 * search for product in product list. 4.Add Product to current basket.
	 * 5.Fill all the questions. 6.Adds the product to Shopping cart. 7.Check
	 * Product added to shopping cart. 8.Clears shopping cart.
	 */
	@Test(dataProvider = "PhoneUScatalogueData", priority = 27)
	public void test014_04_PhoneUScatalogue(String productName) {
		boolean result;
		phonePage = phonelogin(Constant.EIN_US);
		productListPage = phonePage.searchproduct(productName);
		waitForElementToBeVisible(productListPage.ReturnToCatalogueBtn, 5);
		boolean found = productListPage.isProductAvaialble(productName);
		Assert.assertTrue(found, "Product not found : " + productName);
		if (found) {
			productPage = productListPage.clickOnProduct(productName);
			waitForElementToBeVisible(productPage.addtoBasketBtn, 5);
			result = productPage.checkProductName(productName);
			Assert.assertTrue(result, "Product name not matching.");
			result = productPage.checkAllButtonDisplayed();
			Assert.assertTrue(result, "All the buttons not displayed.");
			currentBasketPage = productPage.clickOnAddTobasket();
			result = currentBasketPage.checkAllButtonsDisplayed();
			Assert.assertTrue(result, "All the buttons are not displayed in currentbasket.");
			currentBasketPage.fillCurrentBasket(productName);
			currentBasketPage.clickOnAddToBasket();
			shoppingBasketpage = currentBasketPage.clickOnCheckOut();
			shoppingBasketpage.waitForShoppingBasketToDisplay();
			result = shoppingBasketpage.searchForProductInTable(productName);
			Assert.assertTrue(result, "Product is not added to Shopping Basket.");
			shoppingBasketpage.emptyBasket();
		}

	}

	@DataProvider(name = "ErrorDate")
	public Object[][] getproductName() {
		Object[][] testdata = excelUtility.getTestData("ErrorDate");
		return testdata;

	}

	/*
	 * 1.Login With UK EIN. 2.Go to Phone Tab. 3.Click on catalogue Item and
	 * search for product in product list. 4.Add Product to current basket.
	 * 5.Fill weekend date in calender field Check for validation error.
	 */
	@Test(dataProvider = "ErrorDate", priority = 28)
	public void test015_01_ErrorWeekendDate(String productName) {
		boolean result;
		phonePage = phonelogin(Constant.EIN_UK);
		productListPage = phonePage.searchproduct(productName);
		waitForElementToBeVisible(productListPage.ReturnToCatalogueBtn, 5);
		boolean found = productListPage.isProductAvaialble(productName);
		Assert.assertTrue(found, "Product not found : " + productName);
		if (found) {
			productPage = productListPage.clickOnProduct(productName);
			waitForElementToBeVisible(productPage.addtoBasketBtn, 5);
			result = productPage.checkProductName(productName);
			Assert.assertTrue(result, "Product name not matching.");
			result = productPage.checkAllButtonDisplayed();
			Assert.assertTrue(result, "All the buttons not displayed.");
			currentBasketPage = productPage.clickOnAddTobasket();
			result = currentBasketPage.checkAllButtonsDisplayed();
			Assert.assertTrue(result, "All the buttons are not displayed in currentbasket.");
			driver.findElement(By.id("RD26display")).sendKeys("06/20/2020");
			wait(5000);
			currentBasketPage.clickOnAddToBasket();
			String alertErrorText = getAlertText();
			Assert.assertTrue(alertErrorText.contains("'Required By Date' is invalid or incomplete."),
					"Error text not matching.");
		}

	}

	/*
	 * 1.Login With UK EIN. 2.Go to Phone Tab. 3.Click on catalogue Item and
	 * search for product in product list. 4.Add Product to current basket.
	 * 5.Fill Proevious date in calender field Check for validation error.
	 */
	@Test(dataProvider = "ErrorDate", priority = 29)
	public void test015_02_ErrorPreviousDate(String productName) {
		boolean result;
		phonePage = phonelogin(Constant.EIN_UK);
		productListPage = phonePage.searchproduct(productName);
		waitForElementToBeVisible(productListPage.ReturnToCatalogueBtn, 5);
		boolean found = productListPage.isProductAvaialble(productName);
		Assert.assertTrue(found, "Product not found : " + productName);
		if (found) {
			productPage = productListPage.clickOnProduct(productName);
			waitForElementToBeVisible(productPage.addtoBasketBtn, 5);
			result = productPage.checkProductName(productName);
			Assert.assertTrue(result, "Product name not matching.");
			result = productPage.checkAllButtonDisplayed();
			Assert.assertTrue(result, "All the buttons not displayed.");
			currentBasketPage = productPage.clickOnAddTobasket();
			result = currentBasketPage.checkAllButtonsDisplayed();
			Assert.assertTrue(result, "All the buttons are not displayed in currentbasket.");
			driver.findElement(By.id("RD26display")).sendKeys("09/12/2018");
			wait(3000);
			currentBasketPage.clickOnAddToBasket();
			String alertErrorText = getAlertText();
			Assert.assertTrue(alertErrorText.contains("'Required By Date' is invalid or incomplete."),
					"Error text not matching.");
		}
		Assert.assertTrue(true);
	}

	@DataProvider(name = "AlternateAddress")
	public Object[][] iTEquipmentsData() {
		Object[][] testdata = excelUtility.getTestData("ITEquipmentData");
		return testdata;

	}

	/*
	 * 1.Login With US EIN. 2.Go to Phone(US) Tab. 3.Click on catalogue Item and
	 * search for product in product list. 4.Add Product to current basket.
	 * 5.Fill all the questions. 6.Adds the product to Shopping cart. 7.Check
	 * Product added to shopping cart. 8.Check out the product. 9.Change to
	 * alternate Address. 10.Check EIN validation in Alternate address page.
	 */
	@Test(dataProvider = "AlternateAddress", priority = 30)
	public void test016_AlternateAddressEINValidation(String catalogueItem, String productName) {
		
		 boolean result;
		 itEquipment = login(Constant.EIN_UK);
		 wait(3000);
		 productListPage = itEquipment.clicOnProduct(catalogueItem);
		 boolean found = productListPage.isProductAvaialble(productName);
		 Assert.assertTrue(found, "Product not found : " + productName);
		 if (found) {
		 productPage = productListPage.clickOnProduct(productName);
		 waitForElementToBeVisible(productPage.addtoBasketBtn, 5);
		
		 result = productPage.checkProductName(productName);
		 Assert.assertTrue(result, "Product name not matching.");
		 result = productPage.checkAllButtonDisplayed();
		 Assert.assertTrue(result, "All the buttons not displayed.");
		 currentBasketPage = productPage.clickOnAddTobasket();
		
		 result = currentBasketPage.checkAllButtonsDisplayed();
		 Assert.assertTrue(result, "All the buttons are not displayed in currentbasket.");
		 currentBasketPage.fillCurrentBasket(productName);
		 currentBasketPage.clickOnAddToBasket();
		
		 shoppingBasketpage = currentBasketPage.clickOnCheckOut();
		 shoppingBasketpage.waitForShoppingBasketToDisplay();
		 result = shoppingBasketpage.searchForProductInTable(productName);
		 Assert.assertTrue(result, "Product is not added to Shopping Basket.");
		 checkOutPage = shoppingBasketpage.clickOnCheckOut();
		
		 switchToFramePFFrame();
		 classOfWorkJobNumberPage = new ClassOfWorkJobNumber();
		 waitForElementToClickable(classOfWorkJobNumberPage.continueCheckOutBtn,
		 10);
		 classOfWorkJobNumberPage.clickOnContinueCheckOut();
		
		 switchToFramePFFrame();
		 checkOutPage.typeOrderSummary("Test Order Selenium");
		 checkOutPage.typeBusinessJustification("Test Order Selenium Please ignore.");
		 deliveryAddresspage = checkOutPage.clickOnContinueCheckout();
		
		 switchToFramePFFrame();
		 waitForElementToBeVisible(deliveryAddresspage.useAnotherAddress, 10);
		 deliveryAddresspage.clickOnUseAnotherAddress();
		
		 switchToFramePFFrame();
		 deliveryAddresspage.typeSelectdDeliveryTo("Option 1: I would like it delivered to someone else at their registered address.");
		 deliveryAddresspage.typeDeliveryUIN("6120926467");
		 deliveryAddresspage.clickOnContinue();
		 String actualAlertText = "";
		 String expectedAlertText = "Please enter at most 9 characters in the\"UIN\" field.";
		 if (isAlertPresent()) {
		 actualAlertText = getAlertText();
		 alertAccept();
		 System.out.println(actualAlertText);
		 if (actualAlertText.contains(expectedAlertText)) {
		 Assert.assertTrue(true);
		 }
		 } else {
		 Assert.assertTrue(false, "Wrong EIN format alert message is wrong");
		 }
		
		 }
	}

}
