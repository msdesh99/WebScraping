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


public class HomePage extends AllActions{
	WebDriver driver;
    By locator;
    RecipeDetailsPage recipeDetails;
    byte count;
    Recipes recipes;
	
    public HomePage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
 	@FindBy(xpath="//*[@id='maincontent']//*[@class='rcc_rcpcore']//*/a[@itemprop='url']") //dia  
	List<WebElement> recipeList;
 

	public void GetRecipe(String url) throws InterruptedException, IOException {
		  
		  String[][] list = GetHyperText(recipeList);

		   count =0;
     
		   for(String[] recipe: list) {	   
				//if (count <= 2) {
				ScreenScrollDown(driver);
					
				 locator = By.xpath("//*[@class='rcc_rcpcore']//*/a[@itemprop='url' and contains(text(), \"" + recipe[0] + "\")]");

					ClickElement(CallDriverWait(driver, locator), driver);
                     
					recipeDetails = PageFactory.initElements(driver,RecipeDetailsPage.class);
					recipeDetails.GetRecipeDetails(recipe,driver.getCurrentUrl());

				     driver.get(url);
					ScreenScrollDown(driver);
					count++;
						//break;
				//}
		}
		     driver.get(url);
	}

  }
