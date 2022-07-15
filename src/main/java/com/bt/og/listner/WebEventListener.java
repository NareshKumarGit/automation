package com.bt.og.listner;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.bt.og.base.TestBase;

public class WebEventListener extends TestBase implements WebDriverEventListener {
	private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(WebEventListener.class.getName());
	
	public void beforeNavigateTo(String url, WebDriver driver) {
		log.info("Before navigating to: '" + url + "'");
		System.out.println("Before navigating to: '" + url + "'");

	}

	public void afterNavigateTo(String url, WebDriver driver) {
		log.info("Navigated to:'" + url + "'");
		System.out.println("Navigated to:'" + url + "'");

	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		log.info("Value of the:" + element.getText() + " before any changes made");
		System.out.println("Value of the:" + element.getText() + " before any changes made");
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		log.info("Element value changed to: " + element.getText());
		System.out.println("Element value changed to: " + element.getText());
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		
		log.info("Trying to click on: " + element.getText());
		System.out.println("Trying to click on: " + element.getText());
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		log.info("Clicked on: " + element.getText());
		System.out.println("Clicked on: " + element.getText());
	}

	public void beforeNavigateBack(WebDriver driver) {
		log.info("Before Navigating back to previous page: " + driver.getCurrentUrl() + "'");
		System.out.println("Before Navigating back to previous page: " + driver.getCurrentUrl() + "'");
	}

	public void afterNavigateBack(WebDriver driver) {
		log.info("After Navigated back to previous page: '" + driver.getCurrentUrl() + "'");
		System.out.println("After Navigated back to previous page: '" + driver.getCurrentUrl() + "'");
	}

	public void beforeNavigateForward(WebDriver driver) {
		log.info("Before Navigating forward '" + driver.getCurrentUrl() + "'");
		System.out.println("Before Navigating forward '" + driver.getCurrentUrl() + "'");
	}

	public void afterNavigateForward(WebDriver driver) {
		log.info("After Navigated forward '" + driver.getCurrentUrl() + "'");
		System.out.println("After Navigated forward '" + driver.getCurrentUrl() + "'");
	}

	public void onException(Throwable error, WebDriver driver) {
		log.error("Exception occured : '" + error.getMessage() + "'");
		System.out.println("Exception occured : '" + error.getMessage() + "'");
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		log.info("Trying to find Element By : " + by.toString());
		System.out.println("Trying to find Element By : " + by.toString());
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		log.info("Found Element By : " + by.toString());
		System.out.println("Found Element By : " + by.toString());
	}

	public void beforeScript(String script, WebDriver driver) {
	}

	public void afterScript(String script, WebDriver driver) {
	}

	public void beforeAlertAccept(WebDriver driver) {
		log.info("Before Alert Accept");
		System.out.println("Before Alert Accept");

	}

	public void afterAlertAccept(WebDriver driver) {
		log.info("After Accepting Alert");
		System.out.println("After Accepting Alert");

	}

	public void afterAlertDismiss(WebDriver driver) {
		log.info("After Alert DismisS");
		System.out.println("After Alert DismisS");

	}

	public void beforeAlertDismiss(WebDriver driver) {
		log.info("Before Alert Dismiss");
		System.out.println("Before Alert Dismiss");

	}

	public void beforeNavigateRefresh(WebDriver driver) {
		log.info("Before Navigate Refresh");
		System.out.println("Before Navigate Refresh");

	}

	public void afterNavigateRefresh(WebDriver driver) {
		log.info("After Navigate Refresh");
		System.out.println("After Navigate Refresh");
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		log.info("Before Change value of " + element.toString());
		System.out.println("Before Change value of " + element.toString());
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		log.info("After Change value of " + element.toString());
		System.out.println("After Change value of " + element.toString());
	}

	public void afterSwitchToWindow(String arg0, WebDriver driver) {
		log.info("After switch To Window '" + driver.getWindowHandle() + "'");
		System.out.println("After switch To Window '" + driver.getWindowHandle() + "'");

	}

	public void beforeSwitchToWindow(String arg0, WebDriver driver) {
		log.info("Before switch To Window '" + driver.getWindowHandle() + "'");
		System.out.println("Before switch To Window '" + driver.getWindowHandle() + "'");
	}

}
