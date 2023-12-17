package com.TarlaDalal.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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
		AddInRecipesXLS("ListOfRecipesAll.xlsx");

  }
	
	public void GetAllRecipePage() throws InterruptedException, IOException {
		int page = 1;
		boolean pageExists = true;
		while (pageExists){
			System.out.println("Processing page : " + page);
			locator = By.xpath("(//div//a[(@class='respglink' or @class='rescurrpg') and text()='" + page + "'])");
			try {
				ClickElement(CallDriverWait(driver, locator), driver);			
				homePage = PageFactory.initElements(driver, HomePage.class);
				homePage.GetAllRecipe(driver.getCurrentUrl());
			}
			catch(TimeoutException e) {
				pageExists = false;
				System.out.println("End of pages. Page "+page+" Does not exist.");
			}
			page++;
		}
		System.out.println("Writing A to Z recipies to excel");
		AddInRecipesXLS("AtoZRecipes.xlsx");

	}
}
