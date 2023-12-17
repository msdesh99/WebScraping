package com.TarlaDalal.pages;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.TarlaDalal.utils.AllActions;
public class RecipeDetailsPage1 extends AllActions{
	
	WebDriver driver;
	By locator;
	
	public RecipeDetailsPage1(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//*[@itemprop='recipeIngredient']//span")
	List<WebElement> ingredients;
	
 //Recipe ID,Recipe Name,Recipe Category,Food Category,Ingredients,Preparation Time,Cooking Time,
	//Preparation method,Nutrient values,Morbid condition,Url,Flag
	
	public void GetRecipeDetails(String[] recipe, String url) throws InterruptedException, IOException {
		PageFactory.initElements(driver, this);
		//org.jsoup.nodes.Document document = Jsoup.connect(url.toLowerCase()).userAgent("chrome").timeout(10 * 1000).get();
		org.jsoup.nodes.Document document = Jsoup.connect(url.toLowerCase()).timeout(0).get();
		//Jsoup.connect("link").userAgent("Opera").get();

		GetIngredients(document, recipe);
	    GetMethod(document,recipe);
	    GetTime(document,recipe);
	    GetCategory(document,recipe);
	    GetNutrientValues(document,recipe);
	    System.out.println("final url: "+url);
    	 recipe[10]= url;

		AddInRecipesObject(recipe);
		
		//AddInRecipesXLS();

	}
	public static void GetNutrientValues(org.jsoup.nodes.Document document, String[] recipe) {
		Elements nutrientTbl = document.select("#rcpnutrients td");
		 // Elements nutrientText = nutrientTbl.select("[itemprop=itemListElement] span");
		    for(Element nutrient : nutrientTbl) {
                   //System.out.println("Nutrient: "+ nutrient.text());
                   if(recipe[8]!=null)	
       		    	recipe[8] = recipe[8]+", "+nutrient.text();
       		       else 
       		    	   recipe[8]= nutrient.text();
		    }
	    	//System.out.println("nutrientRec[8]: "+ recipe[8]);
	
}

	public static  void GetCategory(org.jsoup.nodes.Document document, String[] recipe) {
	    String recipeCategory = document.select("[itemprop=recipeCategory] span").first().text();
	  	   recipe[2]= recipeCategory;
		//String foodCategory = document.select("[itemprop=recipeCategory] span").first() .text();
	  	   recipe[3]= "";
		    //System.out.println("recipeCategory[2]: "+recipe[2]);
	}



	public static void GetTime(org.jsoup.nodes.Document document, String[] recipe) {
		    String preTime="";
		    String cookTime="";
		     //prepTime = Integer.parseInt(document.select("[itemprop=prepTime]").first().text().replaceAll("[^\\d]", "").strip());
		
		    preTime = document.select("[itemprop=prepTime]").first().text();
		    cookTime = document.select("[itemprop=cookTime]").first().text();
	  	 
		    recipe[5]= preTime;
	  	    recipe[6]= cookTime;
		 //   System.out.println("cook time: "+recipe[6]+" pre "+recipe[5]);
		
	}



	public static void GetMethod(org.jsoup.nodes.Document document, String[] recipe) {
		
	//	#recipe_small_steps>span>[itemprop="recipeInstructions"]>[itemprop="itemListElement"]
	  
		Element methodDiv = document.select("div#recipe_small_steps>span>[itemprop=recipeInstructions]").first();
		  Elements methodText = methodDiv.select("[itemprop=itemListElement] span");
		    for(Element method : methodText) {
                     if(recipe[7]!=null)	
         		    	recipe[7] = recipe[7]+", "+method.text();
         		       else 
         		    	   recipe[7]= method.text();
		    }
	    	//System.out.println("ingr[7]: "+ recipe[7]);

	}



	public static void GetIngredients(org.jsoup.nodes.Document document, String[] recipe) {		
	     Element ingredients_div = document.select("div#rcpinglist").first();
		    //  Element ingredients_div = document.select("div#rcpinglist>span#ingsection1").first();
		    //  Element ingredients_div = document.select("div#rcpinglist>div>span").first();
		     // div#rcpinglist>div>span#ingsection1 + span span
		    
		     // System.out.println("inte: "+ingredients_div);
		    Elements ingredients_links = ingredients_div.select("a");
		    //Elements span = ingredients_div.select("spani#ingsection1");
	    	//System.out.println("links: "+ingredients_links.size());

	    	//List<String> ingredientsText = new ArrayList<String>();
           //  String[] ingredientsText = new String[ingredients_links.size()];
             //int count=0;
		    for(Element ingeText : ingredients_links) {
		    	//ingredientsText.add(ingeText.text());
		    	
		    	  // ingredientsText[count]= ingeText.text();
		    	   //count++;

		        if(recipe[4]!=null)	
		    	recipe[4] = recipe[4]+" , "+ingeText.text();
		       else 
		    	   recipe[4]= ingeText.text();		      	
		    }
		
		            
		    //System.out.println("ingr[4]: "+ recipe[4]);
	    	//System.out.println("ingr[4]: "+ ingredientsText);


	}

	
	
     
}