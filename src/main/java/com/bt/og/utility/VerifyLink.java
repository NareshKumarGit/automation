package com.bt.og.utility;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.bt.og.base.TestBase;



public class VerifyLink extends TestBase {
	public static List<String>urlNotFoundList= new ArrayList<String>(); 
	
	public static void checkBrokenLinks() {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total links are " + links.size());
		for (int i = 0; i < links.size(); i++) {
			WebElement ele = links.get(i);
			if (ele.getAttribute("href") != null) {
				String url = ele.getAttribute("href");
				String linkText = ele.getText();
				verifyLinkActive(url, linkText);
			}
		}
		
	}

	public static void checkBrokenLinks(WebElement element) {
		List<WebElement> links = element.findElements(By.tagName("a"));
		System.out.println("Total links are " + links.size());
		for (int i = 0; i < links.size(); i++) {
			WebElement ele = links.get(i);
			if (ele.getAttribute("href") != null) {
				String url = ele.getAttribute("href");
				String linkText = ele.getText();
				verifyLinkActive(url, linkText);
			}
		}

	}
	
	public static void verifyLinkActive(String linkUrl, String linktext) {
		URL url;
		
		try {
			url = new URL(linkUrl);
			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
			httpURLConnect.setConnectTimeout(3000);
			httpURLConnect.connect();
			if (httpURLConnect.getResponseCode() == 200) {
				System.out.println(linktext + ": " + linkUrl + " - " + httpURLConnect.getResponseMessage());
			}
			if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				System.out.println(linktext + ": " + linkUrl + " - " + httpURLConnect.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);
				urlNotFoundList.add(linktext);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
