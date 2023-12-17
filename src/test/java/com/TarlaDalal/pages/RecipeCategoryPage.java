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


public class RecipeCategoryPage extends AllActions{
	WebDriver driver;
    HomePage homePage; 
    int count=0;
    String foodCategory;

    public RecipeCategoryPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//*[@id="search"]/input[@class="txtsearch"]  //xpath	
	@FindBy(css="#search input[class='txtsearch']") //category search	  
    WebElement searchCategory;

	@FindBy(css="#search input[type='submit']") //category search	  
    WebElement search;
	
	public void GetCategory(String category) throws InterruptedException, IOException {
		  searchCategory.sendKeys(category);
		  search.click();
		 
		//li[@class='rcpsrch_suggest']/a[contains(text(),'Vegan') or contains(text(),'Veg') or contains(text(),'Jain')  or contains(text(),'non vegetarian')]
		 // driver.findElement(By.xpath("//li[@class='rcpsrch_suggest']/a[contains(text(),'Jain Breakfast')]")).click();
	
		  List<WebElement> foodList = driver.findElements(By.xpath("//li[@class='rcpsrch_suggest']/a[contains(text(),'Vegan') or contains(text(),'Veg') or contains(text(),'Jain')]"));
		  System.out.println("Bre List: "+ foodList.size());
		  String[] recipe = GetHyperLink(foodList);		  
		  System.out.println("count List: "+ recipe.length);
           
			for(String link: recipe) {
				System.out.println("link: "+ link);
				System.out.println("count: "+count);

				if(count<2) {
				//System.out.println("link: "+ link);
				
			  if(link.contains("-veg-")) { System.out.println(" Veg found link: "+link);
			    foodCategory = "Veg";
			  }
			  if(link.contains("-vegan-")) { System.out.println(" Vegan found link: "+link);
			    foodCategory = "Vegan";
			  }
			  if(link.contains("-jain-")) { System.out.println(" jain found link: "+link);
			    foodCategory = "Jain";
			  }
			  
				driver.get(link);				
				homePage = PageFactory.initElements(driver,HomePage.class);
				homePage.GetRecipe(driver.getCurrentUrl(),category,foodCategory);
				count++;
			}	
				else break;
				//driver.get(ConfigReader.getDiabetesUrl()); 
		    }
			driver.get(ConfigReader.getBaseUrl()); 

  }
}