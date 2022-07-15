package com.bt.og.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.bt.og.listner.WebEventListener;
import com.bt.og.pages.LoginPage;
import com.bt.og.utility.Constant;

public class TestBase {
	public static final Logger logger = Logger.getLogger(TestBase.class.getName());
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static WebDriverWait wait;

	public TestBase() {
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\bt\\og\\config\\config.properties");
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Initialising the browser
	public void initialise() {
		if (Constant.browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (Constant.browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\drivers\\IEDriverServer.exe");
			DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
			cap.setCapability("nativeEvents", false);
			cap.setCapability("unexpectedAlertBehaviour", "accept");
			cap.setCapability("ignoreProtectedModeSettings", true);
			cap.setCapability("disable-popup-blocking", true);
			cap.setCapability("enablePersistentHover", true);
			cap.setCapability("ignoreZoomSetting", true);
			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.merge(cap);
			driver = new InternetExplorerDriver(options);
		}
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(Constant.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Constant.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		if (Constant.setup_Dev == true) {
			driver.get(Constant.DEV_URL);
			new21CAuthPage();

		}
		if (Constant.setup_Prod == true) {
			driver.get(Constant.Prod_URL);
			old21CAuthPage();
		}

	}

	public void old21CAuthPage() {
		WebElement userein = driver.findElement(By.xpath(".//input[@name='USER']"));
		type(userein, Constant.Auth21CEIN);
		WebElement authPass = driver.findElement(By.xpath(".//input[@name='PASSWORD']"));
		type(authPass, Constant.CAuth21CEINPASS);
		WebElement signOn = driver.findElement(By.xpath(".//input[@value='Sign On']"));
		click(signOn);
		WebElement yesBtn = driver.findElement(By.xpath(".//input[@value=' Yes ']"));
		waitForElementToBeVisible(yesBtn, 5);
		click(yesBtn);
		LoginPage loginPage = new LoginPage();
		waitForElementToBeVisible(loginPage.einTxtBox, 60);

	}

	public void new21CAuthPage() {
		WebElement imAmOnWebtop = driver.findElement(By.xpath(".//button"));
		click(imAmOnWebtop);
		LoginPage loginPage = new LoginPage();
		waitForElementToBeVisible(loginPage.einTxtBox, 60);
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		getFailedScreenShootResult(result);
		driver.quit();
	}

	public void getFailedScreenShootResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			getScreenShot(result.getMethod().getMethodName());
		}
	}

	public String getScreenShot(String imageName) {
		if (imageName.equals("")) {
			imageName = "blank";
		}
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String imageLocation = System.getProperty("user.dir") + "\\src\\main\\java\\com\\bt\\og\\screenshots\\";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String actualImageName = imageLocation + imageName + "_" + formater.format(calendar.getTime()) + ".png";
		try {
			FileHandler.copy(src, new File(actualImageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return actualImageName;
	}

	public void getScreenShot() {
		String imageName = "blank";
		getScreenShot(imageName);
	}

	public WebElement waitForElementToClickable(WebElement eL, long timeout) {
		wait = new WebDriverWait(driver, timeout);
		wait.ignoring(StaleElementReferenceException.class, NoSuchElementException.class);
		return wait.until(ExpectedConditions.elementToBeClickable(eL));
	}

	public WebElement waitForElementToBeVisible(WebElement eL, long timeout) {
		wait = new WebDriverWait(driver, timeout);
		wait.ignoring(StaleElementReferenceException.class, NoSuchElementException.class);
		return wait.until(ExpectedConditions.visibilityOf(eL));
	}

	public void implicitWait(long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public void type(WebElement eL, String text) {
		try {
			eL.clear();
			eL.sendKeys(text);
		} catch (StaleElementReferenceException e) {
			System.out.println("StaleElementReferenceException");
		}
	}

	public void clear(WebElement eL) {
		try {
			eL.clear();
		} catch (StaleElementReferenceException e) {
			System.out.println("StaleElementReferenceException");
		}
	}

	public void click(WebElement eL) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", eL);
		} catch (StaleElementReferenceException e) {
			System.out.println("Stale Element Exception is occured while clicking element.");
		}
	}

	public void wait(int t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void clear(String elementName) {
		try {
			WebElement eL = driver.findElement(By.name(elementName));
			eL.clear();
		} catch (Exception e) {
			System.out.println("Stale Element Exception is occured while clearing the text.");
		}
	}

	protected void clickLink(String linkName) {
		try {
			WebElement eL = driver.findElement(By.linkText(linkName));
			eL.click();
		} catch (StaleElementReferenceException e) {
			System.out.println("Exception occured while clicking the " + linkName);
		} catch (Exception e) {
			System.out.println("Exception occured while clicking the " + linkName);
		}
	}

	protected void clickButton(String buttonName) {
		try {
			WebElement eL = driver.findElement(By.name(buttonName));
			eL.click();
		} catch (Exception e) {
			System.out.println("Stale Element Exception is while clicking button");
		}
	}

	protected void clickCheckBox(String checkBoxName) {
		try {
			WebElement eL = driver.findElement(By.name(checkBoxName));
			eL.click();
		} catch (Exception e) {
			System.out.println("Stale Element Exception is handled.");
		}
	}

	protected void clickRadiobutton(String radioButtonName) {
		try {
			WebElement eL = driver.findElement(By.name(radioButtonName));
			eL.click();
		} catch (Exception e) {
			System.out.println("Stale Element Exception is handled.");
		}
	}

	protected void selectByValue(String selectorName, String value) {
		try {
			WebElement eL = driver.findElement(By.name(selectorName));
			Select select = new Select(eL);
			select.selectByValue(value);
		} catch (Exception e) {
			System.out.println("Stale Element Exception is handled.");
		}

	}

	protected void selectByText(String selectorName, String text) {
		try {
			WebElement eL = driver.findElement(By.name(selectorName));
			Select select = new Select(eL);
			select.selectByVisibleText(text);
		} catch (Exception e) {
			System.out.println("Stale Element Exception is handled.");
		}

	}

	protected void selectByTextID(String selectorID, String text) {
		try {
			WebElement eL = driver.findElement(By.id(selectorID));
			Select select = new Select(eL);
			select.selectByVisibleText(text);
		} catch (Exception e) {
			System.out.println("Stale Element Exception is handled.");
		}
	}

	public void scrollPage(int xPosition, int yPosition) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy('" + xPosition + "','" + yPosition + "');");
	}

	public void scrollInToView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollInToView(true);", element);
	}

	public void switchToNavigationMenu() {
		try {
			driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("Header")));

		} catch (Exception e) {
			System.out.println("Exception occured while switching Nav Frame.");
		}

	}

	public void switchToFramePFFrame() {
		try {
			driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("PFFrame")));
		} catch (Exception e) {

		}
	}

	public void switchToBasketContentFrame() {
		try {
			wait(3000);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("basketContent")));
		} catch (Exception e) {
			System.out.println("Exception occured while switching to basket Frame.");
		}
	}

	public static boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			System.out.println("switched to alert");
			return true;
		} catch (Exception e) {
			System.out.println("Alert is not present");
			return false;
		}
	}

	public static void switchToAlert() {
		driver.switchTo().alert();
	}

	public static void alertAccept() {
		driver.switchTo().alert().accept();

	}

	public static void alertDismiss() {
		driver.switchTo().alert().dismiss();
	}

	public static String getAlertText() {
		return driver.switchTo().alert().getText();

	}

	public static void waitForAlertToBePresent() {
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();

	}

	public static void checkRailoError() {
		boolean found = false;
		List<WebElement> railoError = driver.findElements(By.tagName("td"));
		for (WebElement err : railoError) {
			if (err.getText().contains("Railo 4.2.1.000 Error (java.io.IOException")) {
				found = true;
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>Railo Eror>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

				try {
					Actions action = new Actions(driver);
					action.moveToElement(err).wait(5);
					action.build().perform();
				} catch (InterruptedException e) {

					e.printStackTrace();
				}

				break;

			}
		}
		Assert.assertTrue(!found, "Railo Error Found");
	}

}
