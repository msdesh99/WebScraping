package com.TarlaDalal.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.TarlaDalal.model.Recipes;
import com.TarlaDalal.utils.AllActions;
import com.TarlaDalal.utils.ConfigReader;


public class PageNumberPage extends AllActions{
	WebDriver driver;
    String handle;
    By locator;
    String subMenu;
    HomePage homePage; 
    byte count;
    Recipes recipes;
    int prePage =1;
    
	public PageNumberPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		//scrapedRecipeList = new ArrayList<Recipes>();
	}

	//@FindBy(xpath="//*[@id='maincontent']//*[@class='respglink' or @class='rescurrpg']")//for a-z
	@FindBy(xpath="//*[@id='cardholder']//*[@class='respglink' or @class='rescurrpg']") //diebetic	  
    List<WebElement> pages;

	public void GetPage() throws InterruptedException, IOException {
		  String[] pageArr = GetPageText(pages);		  
		  
		for(String pageNo: pageArr) {
		//  for(int i=0;i<pageArr.length;i++) {
			System.out.println("pagenu: "+pageNo);
			if(Integer.valueOf(pageNo)>1) {
				locator = By.xpath("//*[@id='cardholder']//*[(@class='respglink' or @class='rescurrpg')"
						+ "and text()='"+pageNo+"']");
				ClickElement(CallDriverWait(driver, locator), driver);
			}				
			homePage = PageFactory.initElements(driver,HomePage.class);
			homePage.GetRecipe(driver.getCurrentUrl());
 //break;
			driver.get(ConfigReader.getDiabetesUrl()); 
	    }
		AddInRecipesXLS();

  }
}
