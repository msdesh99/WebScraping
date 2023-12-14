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
    String handle;
    By locator;
    String subMenu;
    RecipeDetails recipeDetails;
    byte count;
    Recipes recipes;
    
	public HomePage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		//scrapedRecipeList = new ArrayList<Recipes>();

	}
	
	@FindBy(xpath="//*[@class='respglink' or @class='rescurrpg']")
    WebElement pages;

	@FindBy(xpath="//*[@id='maincontent']//*[@class='rcc_rcpcore']//*/a[@itemprop='url']")
    List<WebElement> recipeList;
	//*[@class='rcc_rcpcore']//*/a[@itemprop="url" and text()='Cabbage Jowar Muthias']

	public void getRecipe() throws InterruptedException, IOException {
		  String[][] list = GetHyperText(recipeList);

		   count =0;
		   //driver.get(ConfigReader.getDiabetesUrl());
     
		  // for(WebElement recipe: recipeList) {
		   for(String[] recipe: list) {	   
			 //  System.out.println("subhome: "+recipe[0]);
			  // System.out.println("subid: "+recipe[1]);

				if (count <= 5) {
				ScreenScrollDown(driver);
					locator = By.xpath("//*[@class='rcc_rcpcore']//*/a[@itemprop='url' "
							+ "and text()='" + recipe[0] + "']");
					ClickElement(CallDriverWait(driver, locator), driver);
                     
					//AddInRecipesObject(recipe);
					recipeDetails = PageFactory.initElements(driver,RecipeDetails.class);
					//recipeDetails.GetRecipeDetails(recipe[0],driver.getCurrentUrl());
					recipeDetails.GetRecipeDetails(recipe,driver.getCurrentUrl());

					//driver.get(ConfigReader.getDiabetesUrl());
					//ScreenScrollDown(driver);
					count++;
						break;
				}
			    
    
			/*		//locator = By.xpath("//*[@id='taxonomy-nodes__chop_1-0']//span");
					//ClickElement(CallDriverWait(driver, locator), driver);

				//	subListPage = PageFactory.initElements(driver, SubListPage.class);
				//	subListPage.GetSubList(subMenu, driver.getCurrentUrl());
				//	driver.get(ConfigReader.getBaseUrl());
					// break;
				}*/
		   }
			//AddInRecipesXLS();

		
	}

	

	

}
