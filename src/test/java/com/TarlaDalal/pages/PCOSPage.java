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


public class PCOSPage extends AllActions{
	WebDriver driver;
    By locator;
    HomePage homePage; 
    byte count;
    Recipes recipes;
    int size;
    String ingd,targetmorbidcondition,RecURL,labels;
    
	public PCOSPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//*[@id='cardholder']//*[@class='respglink' or @class='rescurrpg']") //diebetic	  
    List<WebElement> pages;

	public void GetPCOSPage(WebDriver driver) throws InterruptedException, IOException {
		System.out.println("inPCOS");
	    WebElement recipies = driver.findElement(By.xpath("//div[contains(text(),'RECIPES')]"));
	    recipies.click();
	    WebElement PCOSrecipes = driver.findElement(By.xpath("//a[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht335']"));
	    PCOSrecipes.click();
	
	     int pageCount=driver.findElements(By.xpath("//div[@id='pagination']/a")).size();
	      System.out.println("Page count:"+pageCount);
	   //   page
	size= driver.findElements(By.xpath("//span[@class='rcc_recipename']")).size();
	System.out.println("recipe size"+size);
	
	targetmorbidcondition= driver.findElement(By.xpath("//span[@id='ctl00_cntleftpanel_lblSearchTerm']//span//h1")).getText();
	System.out.println("Target Morbid Condition :"+targetmorbidcondition);
	for(int i=1; i<pageCount; i++) {
	 if(i<2){
	
		System.out.println("Processing Page: "+i);
		System.out.println("Processing Page: "+driver.getCurrentUrl());

	homePage = PageFactory.initElements(driver,HomePage.class);
	homePage.GetRecipe(driver.getCurrentUrl()); 
	 }
	else break;
	}
		/*
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
  
	*/
}
}
