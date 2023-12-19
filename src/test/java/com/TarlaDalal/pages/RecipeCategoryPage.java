package com.TarlaDalal.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.TarlaDalal.utils.AllActions;
import com.TarlaDalal.utils.ConfigReader;
import com.TarlaDalal.utils.LoggerLoad;


public class RecipeCategoryPage extends AllActions{
	WebDriver driver;
    HomePage homePage; 
    int count=0;

    public RecipeCategoryPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="#search input[class='txtsearch']") //category search	  
    WebElement searchCategory;

	@FindBy(css="#search input[type='submit']") //category search	  
    WebElement search;
	
	public void GetCategory(String category) throws InterruptedException, IOException {
		  searchCategory.sendKeys(category);
		  search.click();

		  List<WebElement> foodList = driver.findElements(By.xpath("//li[@class='rcpsrch_suggest']/a[contains(text(),'Vegan') or contains(text(),'Veg') or contains(text(),'Jain')]"));
		  String[] recipe = GetHyperLink(foodList);		  
           
			System.out.println("In "+category);
		    LoggerLoad.info("<=== In "+category +" ===> ");	


			for(String link: recipe) {
				if(count<5) {
				System.out.println("Processing Recipe : "+ link);
			    LoggerLoad.info("<=== Processing Recipe : "+ link +" ===> ");	

				driver.get(link);				
				homePage = PageFactory.initElements(driver,HomePage.class);
				homePage.GetRecipe(driver.getCurrentUrl());
				count++;
			}	
				else break;
				//driver.get(ConfigReader.getDiabetesUrl()); 
		    }
			driver.get(ConfigReader.getBaseUrl()); 

  }
}
