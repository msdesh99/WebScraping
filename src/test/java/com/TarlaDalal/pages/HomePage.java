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
 	
 	@FindBy(xpath="//span[@class='rcc_recipename']//a") //all recipe  
	List<WebElement> allRecipeList;

	public void GetRecipe(String url) throws InterruptedException, IOException {
		  
		  String[][] list = GetHyperText(recipeList);

		   count =0;
     
		   for(String[] recipe: list) {	   
				//if (count <= 2) {
				ScreenScrollDown(driver);
					locator = By.xpath("//*[@class='rcc_rcpcore']//*/a[@itemprop='url' "
							+ "and text()='" + recipe[0] + "']");
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
	
	public void GetAllRecipe(String url) throws InterruptedException, IOException {		
		String[][] list = GetHyperText(allRecipeList);

		count = 0;

		for (int i = 0; i < list.length; i++) {
			System.out.println("Processing Recipe : " + list[i][0] +"-"+list[i][1]);
			String[] recipe = list[i];
			WebElement hrefElement = allRecipeList.get(i);
			ScreenScrollDown(driver);
			hrefElement.click();
			recipeDetails = PageFactory.initElements(driver, RecipeDetailsPage.class);
			recipeDetails.GetRecipeDetails(recipe, driver.getCurrentUrl());

			driver.get(url);
			ScreenScrollDown(driver);
			count++;
		}
		driver.get(url);
	}
  }

