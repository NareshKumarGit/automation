package com.bt.og.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bt.og.base.TestBase;

public class DelegatePage extends TestBase{
	@FindBy(id = "Ein1")
	public WebElement EinTextFiled;
	
	@FindBy(id = "FromDate1")
	public WebElement Fromdate;
	
	@FindBy(id = "ToDate1")
	public WebElement ToDate;

	
	@FindBy(id = "AddDelegateSubmit")
	public WebElement AddDelegateSubmit;


	public DelegatePage() {
		PageFactory.initElements(driver, this);
	}
	
	public void enterEIN()
	{
		type(EinTextFiled, "612092646");
		
	}

public void typeFromDate()
{
	type(Fromdate, "06/14/2019");

}

public void typeToDate()
{
	type(ToDate, "06/14/2019");

}

public void clickAddDelegate()
{
	click(AddDelegateSubmit);

}

}
