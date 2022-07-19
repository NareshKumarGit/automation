package com.bt.og.utility;

public class Constant {

	// Browser
	public static String browser = "chrome";
	// Select Execution environment
	public static boolean setup_Dev = false;
	public static boolean setup_Prod = true;

	public static String EIN_UK = "802766791";
	public static String EIN_NonUK = "612092646";
	public static String EIN_US = "600402983";

	public static String shoppingbasket_Title = "Shopping basket";
	public static String CustomerDetailsTitle = "Customer details";

	// 21C Auth credential
	public static String Auth21CEIN = "613047034";
	public static String CAuth21CEINPASS = "Bangalore@1234";

	// Wait time
	public static long IMPLICIT_WAIT = 5;
	public static long PAGE_LOAD_TIMEOUT = 60;
	
	// Dev url
	public static String DEV_URL = "http://ogsys.dev.nat.bt.com";

	// Prod url
	public static String Prod_URL = "http://ordergateway.intra.bt.com/";

	// Test data for dev
	public static final String excelFilePathDev = System.getProperty("user.dir")
			+ "\\src\\main\\java\\com\\bt\\og\\testdata\\testDataDev.xlsx";
	
	// Test data for prod
	public static final String excelFilePathProd = System.getProperty("user.dir")
			+ "\\src\\main\\java\\com\\bt\\og\\testdata\\testDataProd.xlsx";

}
