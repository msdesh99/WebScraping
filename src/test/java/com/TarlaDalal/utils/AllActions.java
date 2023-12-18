/*Code details:
	#Author: Meenakshi Dated: 6-Nov-2023
*/

package com.TarlaDalal.utils;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.TarlaDalal.model.Recipes;

public class AllActions {
	static JavascriptExecutor js;
	static Actions action;
	static Alert alert;
	static String path;
	static Recipes recipes;
    static List<Recipes> scrapedRecipeList = new ArrayList<Recipes>();
    static List<Recipes> filteredRecipeList = new ArrayList<Recipes>();


    static XLUtility xlUtility;

	// 2 neeeded
	public static WebElement DriverWaitForElement(WebDriver driver, WebElement element) {		
		WebElement ele =  new WebDriverWait(driver, Duration.ofSeconds(6))
				.until(ExpectedConditions.visibilityOf(element));
		return ele;
	}
	public static boolean ClickElement(WebElement element, WebDriver driver) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);
	/*	WebElement ele;
		try {
		ele =  DriverWaitForElement(driver, element);
		if(ele.isDisplayed()) {
			if(ele.isEnabled()) {			 
				try {
					action = new Actions(driver);
					action.moveToElement(ele).click().build().perform();	
					return true;
				} catch (Exception e) {
					js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click()", element);
					return true;					
				}
	
			}else  throw new Exception("Element not enabled"); 				
		}else throw new Exception("Element is not displayed");
			
	  } catch (Exception e) {
		e.printStackTrace();
		return false;
	  } */
		return true;
	}
/*	public void Quit_Driver(WebDriver driver) {
		driver.quit();
	}*/
    public static void DriverImpliciteWait(WebDriver driver) {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }
    public static void DriverExplicitWait(WebDriver driver, Duration timeInSec) {
        new WebDriverWait(driver, timeInSec);
    }
    
	public static void DriverWaitForLocatorOrUrl(WebDriver driver, By locator, String url) {
	new WebDriverWait(driver,Duration.ofSeconds(6))
	  .until(ExpectedConditions.or(
			  ExpectedConditions.visibilityOfElementLocated(locator),
			  ExpectedConditions.urlContains(url))) ;
}
	
	//needed
	public static WebElement CallDriverWait(WebDriver driver, By locator) {
		return new WebDriverWait(driver, Duration.ofSeconds(6))
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
}
	
	
	
	//needed
    public static void ScreenScrollDown(WebDriver driver) {
    	js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollBy(0,1850)", "");
    }	
 

		public String[][] GetHyperText(List<WebElement> recipeList) {
	    	String[][]  list = new String[recipeList.size()][ConfigReader.getcellNames().length];
	    //	System.out.println("cell leng: "+ ConfigReader.getcellNames().length);
            String rawId="";
			int count=0;
			   for(WebElement menu: recipeList) {
				    rawId = menu.getAttribute("href").toString();
					list[count][0] = menu.getText();
					list[count][1] = rawId.substring(rawId.lastIndexOf('-')+1,rawId.length()-1);

					count++;
			   }
			    return list;
		}
		public String[] GetPageText(List<WebElement> pages) {
			 String[] pageArr = new String[pages.size()];
			 int count=0;
			 for(WebElement page: pages) {
					   pageArr[count] = page.getText();
				  // System.out.println("pagetext: "+page.getText());
				   count++;
				   }
			 return pageArr;
		}

		
      public static void AddInRecipesObject(String[] recipe) throws IOException {

			recipes = new Recipes();
			recipes.setRecipeName(recipe[0]);
			recipes.setRecipeID(recipe[1]);
			recipes.setRecipeCategory(recipe[2]);
			recipes.setFoodCategory(recipe[3]);
			recipes.setIngredients(recipe[4]);
			recipes.setPrepTime(recipe[5]);
			recipes.setCookTime(recipe[6]);
			recipes.setMethod(recipe[7]);
			recipes.setNutrient(recipe[8]);

			recipes.setMorbid(recipe[9]);
			recipes.setUrl(recipe[10]);

			scrapedRecipeList.add(recipes);		
	
			// System.out.println("count: "+scrapedRecipeList.size());
           // for(Recipes recipe1: scrapedRecipeList) {
            	//System.out.println("name: "+recipe1.getRecipeName());
            	//System.out.println("id: "+recipe1.getRecipeID());             
            //}  	
		}

      public static void AddFlagInRecipe() throws IOException {
      	  //System.out.println("scr: "+scrapedRecipeList.size());
      for(int i=0; i<scrapedRecipeList.size();i++) {	      	  
    	  String ingredientsTextlist = scrapedRecipeList.get(i).getIngredients();
           if (ingredientsTextlist != null) {
        	   String[] ingredientsText = ingredientsTextlist.split(",");
           
    	    CheckDiabetesEliminate(ingredientsText,scrapedRecipeList.get(i));
    	   
    	    CheckHypothyroidismEliminate(ingredientsText,scrapedRecipeList.get(i));
    	    
    	    CheckHypertensionEliminate(ingredientsText,scrapedRecipeList.get(i));
    	    
    	    CheckPCOSEliminate(ingredientsText,scrapedRecipeList.get(i));
    	    
    	    Checkallergies(ingredientsText,scrapedRecipeList.get(i));
      }
           else {
        	   System.out.println("Ingredients are null for recipe at index " + i);
           }
      }   
      }
      public static void AddInRecipesXLS() throws IOException {
			AddFlagInRecipe();
			String path;
    	 	path = System.getProperty("user.dir")+"/src/test/resources/Lists/ListofRecipes1.xlsx";
    	    xlUtility = new XLUtility(path, "AllModules");
    	    xlUtility.WriteIntoFile(scrapedRecipeList);
    	 	
    	  /*  path = System.getProperty("user.dir")+"/src/test/resources/Lists/ListOfRecipes.xlsx";
    	    xlUtility = new XLUtility(path, "All");
    	    xlUtility.WriteAllSheetsIntoFile(scrapedRecipeList); */
    	    
  	}
  	public static void CheckDiabetesEliminate(String[] ingredientsText, Recipes recipe) {
		List<String> eliminateArr = Arrays.asList(ConfigReader.geteliminateDiebetes());
	    SetFlag(ingredientsText,eliminateArr,"Diabetes_Eliminated",recipe);
	}
  	public static void CheckDiabetesAdd(String[] ingredientsText, Recipes recipe) {
		List<String> diabetesAddArr = Arrays.asList(ConfigReader.getAddDiabetes());
		SetFlag(ingredientsText,diabetesAddArr,"Diabetes_Add",recipe);		
	}
	public  static void CheckHypothyroidismEliminate(String[] ingredientsText, Recipes recipe) {
		List<String> eliminateArr = Arrays.asList(ConfigReader.geteliminateHypothyroidism());
	    SetFlag(ingredientsText,eliminateArr,"Hypothyroidism_Eliminated",recipe);
	}
 	public static void CheckHypothyroidismAdd(String[] ingredientsText, Recipes recipe) {
		List<String> diabetesAddArr = Arrays.asList(ConfigReader.getAddHypothyroidism());
	    SetFlag(ingredientsText,diabetesAddArr,"Hypothyroidism_Add",recipe);
	}
	public  static void CheckHypertensionEliminate(String[] ingredientsText, Recipes recipe) {
		List<String> eliminateArr = Arrays.asList(ConfigReader.geteliminateHypertension());
	    SetFlag(ingredientsText,eliminateArr,"Hypertension_Eliminated",recipe);
	}
 	public static void CheckHypertensionAdd(String[] ingredientsText, Recipes recipe) {
		List<String> diabetesAddArr = Arrays.asList(ConfigReader.getAddHypertension());
	    SetFlag(ingredientsText,diabetesAddArr,"Hypertension_Add",recipe);
	}
	public  static void CheckPCOSEliminate(String[] ingredientsText, Recipes recipe) {
		List<String> eliminateArr = Arrays.asList(ConfigReader.geteliminatePCOS());
	    SetFlag(ingredientsText,eliminateArr,"PCOS_Eliminated",recipe);
	}
 	public static void CheckPCOSAdd(String[] ingredientsText, Recipes recipe) {
		List<String> diabetesAddArr = Arrays.asList(ConfigReader.getAddPCOS());
	    SetFlag(ingredientsText,diabetesAddArr,"PCOS_Add",recipe);
	}
	public static void Checkallergies(String[] ingredientsText, Recipes recipe) {
		List<String> diabetesAddArr = Arrays.asList(ConfigReader.getallergies());
	    SetFlag(ingredientsText,diabetesAddArr,"Allergies_Add",recipe);
	}

	public static void SetFlag(String[] ingredientsText,List<String> eliminateArr, String flagText, Recipes recipe) {
		String addIngred ="";
		List<String> ingredText = Arrays.asList(ingredientsText);
		//System.out.println("elimin text +1: "+flagText.substring(flagText.indexOf(' ')+1).contentEquals("Eliminated"));
		//System.out.println("Add text: "+flagText.substring(flagText.indexOf(' ')).contentEquals("Add"));

		List<String> result = ingredText.stream().filter(
    			s -> eliminateArr.stream().anyMatch(s1 -> s.contains(s1))
    			).collect(Collectors.toList());  	
    	  for (String s1 : result) {
    	        System.out.println(flagText+" : "+ s1); 
    	        if(addIngred=="") addIngred = s1;
    	        else addIngred = addIngred+"-"+s1;
    	        }
    			if((result.size()==0 && flagText.substring(flagText.indexOf('_')+1) .contentEquals("Eliminated"))||
    			  (result.size()!=0 && flagText.substring(flagText.indexOf('_')+1).contentEquals("Add")))
    			{		
        	       // System.out.println(flagText+" adding"); 
                       
    		    	  if(recipe.getMorbid()==null)
    		  		     recipe.setMorbid(flagText.substring(0, flagText.indexOf('_')));
    		    	  else
     		  		     recipe.setMorbid(recipe.getMorbid()+","+flagText.substring(0, flagText.indexOf('_')));

    		  		if(flagText.contentEquals("Diabetes_Eliminated")) {
    		  			recipe.setDiabetes_Eliminated("Yes");
    		  			CheckDiabetesAdd(ingredientsText,recipe);
    		  		}
    		  		if(flagText.contentEquals("Diabetes_Add"))
    		  		    recipe.setDiabetes_Add("Yes: "+addIngred);	
     		  		if(flagText.contentEquals("Hypothyroidism_Eliminated")) {  		  			
    		  			recipe.setHypothyroidism_Eliminated("Yes");
    		  			CheckHypothyroidismAdd(ingredientsText,recipe);
     		  		}	
     		  		if(flagText.contentEquals("Hypothyroidism_Add")) 		  			
    		  		    recipe.setHypothyroidism_Add("Yes: "+addIngred);	
     		  		
     		  		if(flagText.contentEquals("Hypertension_Eliminated")) {
     		  			recipe.setHypertension_Eliminated("Yes");
    		  			CheckHypertensionAdd(ingredientsText,recipe);
     		  		}
     		  		if(flagText.contentEquals("Hypertension_Add")) 
    		  		    recipe.setHypertension_Add("Yes: "+addIngred);	
     		  		
     		  		if(flagText.contentEquals("PCOS_Eliminated")) {
    		  			recipe.setPCOS_Eliminated("Yes");
     		  			CheckPCOSAdd(ingredientsText,recipe);
     		  		}
     		  		if(flagText.contentEquals("PCOS_Add"))
     		  			recipe.setPCOS_Add("Yes: "+addIngred);
    		  		
     		  		if(flagText.contentEquals("Allergies_Add")) 
     		  			recipe.setAllergies("Yes: "+ addIngred);
    		  		
    			}
    			addIngred="";
    			
    	/*for (String s1 : filteredResult1)
            System.out.println(s1); */
	
  }
	   	  
}
