package com.TarlaDalal.pages;

import java.io.IOException;
import java.util.ArrayList;
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
    By locator;
    HomePage homePage; 
    byte count;
    Recipes recipes;
    
	public PageNumberPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		//scrapedRecipeList = new ArrayList<Recipes>();
	}

	@FindBy(xpath="//*[@id='cardholder']//*[@class='respglink' or @class='rescurrpg']") //diebetic	  
    List<WebElement> pages;

	public void GetPage() throws InterruptedException, IOException {
		  String[] pageArr = GetPageText(pages);		  
		  count=0;
		for(String pageNo: pageArr) {
			if(count<2) {
			if(Integer.valueOf(pageNo)>1) {
				locator = By.xpath("//*[@id='cardholder']//*[(@class='respglink' or @class='rescurrpg')"
						+ "and text()='"+pageNo+"']");
				ClickElement(CallDriverWait(driver, locator), driver);
			}				
			homePage = PageFactory.initElements(driver,HomePage.class);
			homePage.GetRecipe(driver.getCurrentUrl());
			count++;
			}
			else break;
			driver.get(ConfigReader.getDiabetesUrl()); 
	    }
	}	
		public void GetAllRecipePage() throws InterruptedException, IOException {
			int page = 1;
			boolean pageExists = true;
			//while (pageExists){
			  while(page<=2) {	
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
		}
  
	
}
