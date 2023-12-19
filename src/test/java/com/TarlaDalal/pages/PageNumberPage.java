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

	public void GetDiabetesPage() throws InterruptedException, IOException {
		System.out.println("In Diabetes");
		  String[] pageArr = GetPageText(pages);		  
		  count=0;
		for(String pageNo: pageArr) {
			//if((count<6)||(count<Integer.parseInt(pageNo))) {
				System.out.println("Processing Page: "+pageNo);
			if(Integer.valueOf(pageNo)>1) {
				locator = By.xpath("//*[@id='cardholder']//*[(@class='respglink' or @class='rescurrpg')"
						+ "and text()='"+pageNo+"']");
				ClickElement(CallDriverWait(driver, locator), driver);
			}				
			homePage = PageFactory.initElements(driver,HomePage.class);
			homePage.GetRecipe(driver.getCurrentUrl());
			count++;
			//}
			//else break;
			driver.get(ConfigReader.getDiabetesUrl()); 
	    }
	}	
	public void GetPCOSPage(WebDriver driver) throws InterruptedException, IOException {
		System.out.println("In PCOS");
	    WebElement recipies = driver.findElement(By.xpath("//div[contains(text(),'RECIPES')]"));
	    recipies.click();
	    WebElement PCOSrecipes = driver.findElement(By.xpath("//a[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht335']"));
	    PCOSrecipes.click();
	
	    List<WebElement> pages = driver.findElements(By.xpath("//div[@id='pagination']/a"));
	    // int pageCount=driver.findElements(By.xpath("//div[@id='pagination']/a")).size();
	      System.out.println("Page count:"+pages.size());
		  String[] pageArr = GetPageText(pages);		  
		  count=0;
			for(String pageNo: pageArr) {
				//if((count<6)&&(count<Integer.valueOf(pageNo))) {
			 if(count<6) {
					System.out.println("Processing Page: "+pageNo);

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
				//driver.get(ConfigReader.getDiabetesUrl()); 
		    }		
}
	public void GetHypertensionPage(WebDriver driver) throws InterruptedException, IOException {
		System.out.println("In Hypertension");
	    WebElement recipies = driver.findElement(By.xpath("//div[contains(text(),'RECIPES')]"));
	    recipies.click();
	    WebElement hypertensionRecipes = driver.findElement(By.xpath("//a[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht152']"));
	    hypertensionRecipes.click();
	
	    List<WebElement> pages = driver.findElements(By.xpath("//div[@id='pagination']/a"));
		  String[] pageArr = GetPageText(pages);		  
		  count=0;
			for(String pageNo: pageArr) {
				System.out.println("Processing Page: "+pageNo);
			//	if(count<2) {
				if(Integer.valueOf(pageNo)>1) {
					locator = By.xpath("//*[@id='cardholder']//*[(@class='respglink' or @class='rescurrpg')"
							+ "and text()='"+pageNo+"']");
					ClickElement(CallDriverWait(driver, locator), driver);
				}				
				homePage = PageFactory.initElements(driver,HomePage.class);
				homePage.GetRecipe(driver.getCurrentUrl());
				count++;
				//}
				//else break;
				driver.get(ConfigReader.getDiabetesUrl()); 
		    }		
}
	public void GetHypoThyroidismPage(WebDriver driver) throws InterruptedException, IOException {
		System.out.println("In HypoThyroidism");
	    WebElement recipies = driver.findElement(By.xpath("//div[contains(text(),'RECIPES')]"));
	    recipies.click();
	    WebElement hypoThyroidismRecipes = driver.findElement(By.xpath("//*[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht207']"));
	    hypoThyroidismRecipes.click();
	
	    List<WebElement> pages = driver.findElements(By.xpath("//div[@id='pagination']/a"));
		  String[] pageArr = GetPageText(pages);		  
		  count=0;
			for(String pageNo: pageArr) {
				System.out.println("Processing Page: "+pageNo);
			//	if(count<2) {
				if(Integer.valueOf(pageNo)>1) {
					locator = By.xpath("//*[@id='cardholder']//*[(@class='respglink' or @class='rescurrpg')"
							+ "and text()='"+pageNo+"']");
					ClickElement(CallDriverWait(driver, locator), driver);
				}				
				homePage = PageFactory.initElements(driver,HomePage.class);
				homePage.GetRecipe(driver.getCurrentUrl());
				count++;
				//}
				//else break;
				driver.get(ConfigReader.getDiabetesUrl()); 
		    }		
}
	
		public void GetAZRecipePage() throws InterruptedException, IOException {
			int page = 1;
			boolean pageExists = true;
			while (pageExists){
			 // while(page<=2) {	
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
	