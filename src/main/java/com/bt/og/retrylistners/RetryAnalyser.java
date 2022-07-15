package com.bt.og.retrylistners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {
	int maxCount = 1;
	int counter=0;

	public boolean retry(ITestResult result) {
		if (counter < maxCount) {
			counter++;
			System.out.println("Retrying failed testcase maximum " + maxCount + " times.");

			return true;
		}

		return false;
	}

}
