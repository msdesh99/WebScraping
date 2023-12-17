package com.TarlaDalal.pages;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.TarlaDalal.utils.AllActions;
import com.TarlaDalal.utils.ConfigReader;
public class RecipeDetailsPage extends AllActions{
	
	WebDriver driver;
	By locator;
	
	public RecipeDetailsPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@itemprop='recipeIngredient']//a")
	List<WebElement> ingredients;
	
	@FindBy(css="#rcpnutrients td")
	List<WebElement> nutrient;

	@FindBy(css="[itemprop=recipeCategory] span")
	List<WebElement> categories;
	
	@FindBy(css="[itemprop=prepTime]")
	WebElement prepTime;
	
	@FindBy(css="[itemprop=cookTime]")   
	WebElement cookTime;
	
	@FindBy(css="div#recipe_small_steps>span>[itemprop=recipeInstructions] [itemprop=itemListElement] span")
	List<WebElement> methodList;
 	
	public void GetRecipeDetails(String[] recipe, String url) throws InterruptedException, IOException {
		GetIngredients(recipe);
	    GetMethod(recipe);
	    GetTime(recipe);
	    GetCategory(recipe);
	    GetNutrientValues(recipe); 

	    recipe[10]= url;

       if(recipe[4]!=null)
		AddInRecipesObject(recipe);
		
		//AddInRecipesXLS();

	}
	public void GetIngredients(String[] recipe) {		
		  String[] ingredients_links = GetPageText(ingredients);

		    for(String ingeText : ingredients_links) {
		        if(recipe[4]!=null)	
		    	recipe[4] = recipe[4]+" ,\n "+ingeText;
		       else 
		    	   recipe[4]= ingeText;		      	
		    }
		    //System.out.println("ingr[4]: "+ recipe[4]);
	}

	
	public void GetNutrientValues(String[] recipe) {
		  String[] nutrientTbl = GetPageText(nutrient);
		  int i=0;
		    for(String nutrients : nutrientTbl) {
		    	
                   if(recipe[8]!=null) {	
                	  if(i==0) {
        		    	  recipe[8] = recipe[8]+": "+nutrients;
        		    	  i++;}
                	  else {
       		    	     recipe[8] = recipe[8]+",\n "+nutrients;
       		    	     i=0;}
                   }
       		       else 
       		    	   recipe[8]= nutrients;
		    }
	    	//System.out.println("nutrientRec[8]: "+ recipe[8]);
	
}

	public void GetCategory(String[] recipe) {		
         //String[] categoryName = GetCategoryText(categories);
          String[] categoryName  =  GetCategoryText(categories);
  	      List<String> category = Arrays.asList(categoryName);
		  List<String> categoryArr = Arrays.asList(ConfigReader.getRecipeCategory());
		  List<String> foodArr = Arrays.asList(ConfigReader.getFoodCategory());
		  List<String> matchCategory = Arrays.asList();

		  matchCategory = category.stream().filter(
	    	                   s -> categoryArr.stream().anyMatch(s1 -> s.contains(s1))
	    	                   ).collect(Collectors.toList());

	    	  for (String result : matchCategory) {
	    	       //System.out.println(category+" : "+ result); 
                   recipe[2] = result;
          	   } 
	    	  if (recipe[2]== null) recipe[2] ="Snack/Lunch"; 
		    	  matchCategory = category.stream().filter(
		    			s -> foodArr.stream().anyMatch(s1 -> s.contains(s1))
		    			).collect(Collectors.toList());  	
		    	  for (String result : matchCategory) {
		    	     //  System.out.println(category+" : "+ result); 
		                recipe[3] = result;
		    	  }
		    	  if (recipe[3] == null) recipe[3] ="Veg";
		    	  if (recipe[3].equalsIgnoreCase("egg") || recipe[3].equalsIgnoreCase("Egg") ) 
		    		  recipe[3] ="Eggitarian";
		  
		    //System.out.println("recipeCategory[2]: "+recipe[2]);
		    //System.out.println("foodCategory[2]: "+recipe[3]);

	}




	public void GetTime(String[] recipe) {	  	
		try {
		    recipe[5]= prepTime.getText();
	  	    recipe[6]= cookTime.getText();
		}catch(Exception e) {
			recipe[5]="NA";
			recipe[6]="NA";
		}    
		    //System.out.println("cook time: "+recipe[6]+" pre "+recipe[5]);		
	}



	public void GetMethod(String[] recipe) {	
		  String[] methodText = GetPageText(methodList);
		    for(String method : methodText) {
                     if(recipe[7]!=null)	
         		    	recipe[7] = recipe[7]+",\n"+method;
         		       else 
         		    	   recipe[7]= method;
		    }
	    	//System.out.println("ingr[7]: "+ recipe[7]);
	}
}
