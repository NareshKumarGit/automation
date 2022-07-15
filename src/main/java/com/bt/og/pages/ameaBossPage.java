package com.bt.og.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.bt.og.base.TestBase;

public class ameaBossPage extends TestBase{
	@FindBy(xpath = ".//input[@type='radio' and @value='ADD']")
	public WebElement addAccess;
	
	@FindBy(xpath = ".//input[@type='radio' and @value='DEL']")
	public WebElement delAccess;
	
	@FindBy(xpath = ".//input[@type='radio' and @value='MOD']")
	public WebElement modAccess;
	
	@FindBy(name = "btn_Submit")
	public WebElement submitBtn;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Australia']")
	public WebElement austrailia;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='New Zealand']")
	public WebElement newZealand;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Japan']")
	public WebElement japan;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Algeria']")
	public WebElement Algeria;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Bahrain']")
	public WebElement Bahrain;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Botswana']")
	public WebElement Botswana;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Cyprus']")
	public WebElement Cyprus;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Egypt']")
	public WebElement Egypt
	;
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Ghana']")
	public WebElement Ghana;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Greece']")
	public WebElement Greece;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Israel']")
	public WebElement Israel;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Ivory Coast']")
	public WebElement IvoryCoast;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Jordan']")
	public WebElement Jordan;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Kenya']")
	public WebElement Kenya;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Kuwait']")
	public WebElement Kuwait;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Lebanon']")
	public WebElement Lebanon;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Malawi']")
	public WebElement Malawi;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Malta']")
	public WebElement Malta;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Mauritius']")
	public WebElement Mauritius;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Morocco']")
	public WebElement Morocco;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Mozambique']")
	public WebElement Mozambique;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Namibia']")
	public WebElement Namibia;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Nigeria']")
	public WebElement Nigeria;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Oman']")
	public WebElement Oman;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Pakistan']")
	public WebElement Pakistan;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Qatar']")
	public WebElement Qatar;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='South Africa']")
	public WebElement SouthAfrica;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Tanzania']")
	public WebElement Tanzania;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Tunisia']")
	public WebElement Tunisia;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Turkey']")
	public WebElement Turkey;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='UAE']")
	public WebElement UAE;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Uganda']")
	public WebElement Uganda;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Zambia']")
	public WebElement Zambia;
	
	@FindBy(xpath = ".//input[@type='checkbox' and @value='Zimbabwe']")
	public WebElement Zimbabwe;
	
	public void selectDropDownOption(String option)
	{
		Select select = new Select(driver.findElement(By.id("QID5394")));
		select.selectByVisibleText(option);
		
	}
	public ameaBossPage() {
		PageFactory.initElements(driver, this);
	
	}
	
	public void clickonCyprus()
	{
		click(Cyprus);
	}
	
	public void clickonEgypt()
	{
		click(Egypt);
	}
	
	public void clickonGhana()
	{
		click(Ghana);
	}
	
	public void clickonGreece()
	{
		click(Greece);
	}
	
	public void clickonIsrael()
	{
		click(Israel);
	}
	
	public void clickonIvoryCoast()
	{
		click(IvoryCoast);
	}
	
	public void clickonJordan()
	{
		click(Jordan);
	}
	
	public void clickonKenya()
	{
		click(Kenya);
	}
	
	public void clickonKuwait()
	{
		click(Kuwait);
	}
	
	public void clickonLebanon()
	{
		click(Lebanon);
	}
	
	public void clickonMalawi()
	{
		click(Malawi);
	}
	
	public void clickonMalta()
	{
		click(Malta);
	}
	
	public void clickonMauritius()
	{
		click(Mauritius);
	}
	
	public void clickonMozambique()
	{
		click(Mozambique);
	}
	
	public void clickonNamibia()
	{
		click(Namibia);
	}
	
	public void clickonNigeria()
	{
		click(Nigeria);
	}
	
	public void clickonOman()
	{
		click(Oman);
	}
	
	public void clickonPakistan()
	{
		click(Pakistan);
	}
	
	public void clickonQatar()
	{
		click(Qatar);
	}
	
	
	public void clickonSouthAfrica()
	{
		click(SouthAfrica);
	}
	
	public void clickonTanzania()
	{
		click(Tanzania);
	}
	
	public void clickonTunisia()
	{
		click(Tunisia);
	}
	
	
	public void clickonTurkey()
	{
		click(Turkey);
	}
	
	public void clickonUAE()
	{
		click(UAE);
	}
	
	public void clickonUganda()
	{
		click(Uganda);
	}
	
	public void clickonZambia()
	{
		click(Zambia);
	}
	 
	public void clickonZimbabwe()
	{
		click(Zimbabwe);
	}
	
	public void clickonBotswana()
	{
		click(Botswana);
	}
	
	
	public void clickonBahrain()
	{
		click(Bahrain);
	}

	
	public void clickonAlgeria()
	{
		click(Algeria);
	}
	
	
	public void clickonAustralia()
	{
		click(austrailia);
	}
	
	public void clickonNewZeland()
	{
		click(newZealand);
	}
	
	public void clickonJapan()
	{
		click(japan);
	}
	
	public void clickonsubmitBtn()
	{
		click(submitBtn);
	}
	
	
	
	

}
