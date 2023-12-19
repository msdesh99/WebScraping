package com.TarlaDalal.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.TarlaDalal.utils.AllActions;
import com.TarlaDalal.utils.ConfigReader;
import com.TarlaDalal.utils.XLUtility;
public class RecipeDetailsPage extends AllActions{
	
	WebDriver driver;
	By locator;
    String path="";
    XLUtility xlUtility;
	 int flagStatus;
	 String flagText;
	 String targetedMorbid;
	 String[] ingredientsText;
     String flagTxt;
   // List<String> recipe = new ArrayList<String>();
    List<String>id = new ArrayList<String>();
    List<String>name = new ArrayList<String>();
    List<String>recipeCategory = new ArrayList<String>();
    List<String>foodCategory = new ArrayList<String>();
    List<String>ingred = new ArrayList<String>();
    List<String>prepTimeList = new ArrayList<String>();
    List<String>cookTimeList = new ArrayList<String>();
    List<String>method = new ArrayList<String>();
    List<String>nutrientList = new ArrayList<String>();
    List<String>morbid = new ArrayList<String>();
    List<String>recipeUrl = new ArrayList<String>();
    List<String>flag = new ArrayList<String>();
    
    List<String> eliminateArr;
    
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
	
	//@FindBy(xpath="//div[@id='rcpinglist']")
    //WebElement ingd;
			
	@FindBy(css="[itemprop=prepTime]")
	WebElement prepTime;
	
	@FindBy(css="[itemprop=cookTime]")   
	WebElement cookTime;
	
	@FindBy(css="div#recipe_small_steps>span>[itemprop=recipeInstructions] [itemprop=itemListElement] span")
	List<WebElement> methodList;
 	
	//public void GetRecipeDetails(String[] recipe, String url) throws InterruptedException, IOException {
	public void GetRecipeDetails(String recipeId,String recipeName, String url) throws InterruptedException, IOException {
	
		id.add(recipeId);
		name.add(recipeName);
		recipeUrl.add(url);
		
	    GetMethod();
	    GetTime();
	    GetCategory();
	    GetNutrientValues(); 
		GetIngredients();
	}
	//public void GetIngredients(String[] recipe) {
	 public void GetIngredients() throws IOException {		
		 // String[] ingredients_links = GetPageText(ingredients);
		 String indgOri = driver.findElement(By.xpath("//div[@id='rcpinglist']")).getText();				 
		 String indg = indgOri.replace("\n", " ").toLowerCase().replace("(", " ").replace(")", " ");
		 ingred.add(indgOri); 

		// System.out.println("ingr:"+ indgOri);
		 if(indg!=null) {
	           
	   	    	flag.add("NA");

           ingredientsText = indg.split(",");
           CheckDiabetesMorbid();
           CheckHypothyroidismMorbid();  	    
           CheckHypertensionMorbid();
   	       CheckPCOSMorbid();
   	       Checkallergies();
   	    	flag.remove(0);
   		   flag.add(flagTxt);
            WriteRecipeIntoFile("All",flag.get(0));      
 	        

		 }   
  
	 }

	
	private void Checkallergies() throws IOException {
		   eliminateArr = Arrays.asList(ConfigReader.getallergies());
 	       flagStatus = SetFlag(ingredientsText, eliminateArr);
       //  System.out.println("flag stat: "+ flagStatus);
 	        if(flagStatus>0) {       
            	flagText = "Allergies" ;
                targetedMorbid = "NA";           
  	          // System.out.println("Writing recipe for :"+ flagText);
  	           flagTxt = (flagTxt==null)?flagText+"\n":flagTxt+","+flagText+"\n";
                //flag.add(targetedMorbid);
                WriteRecipeIntoFile(flagText,targetedMorbid);
        	}
 	        
 			
	}
	private void CheckPCOSMorbid() throws IOException {
		   eliminateArr = Arrays.asList(ConfigReader.geteliminatePCOS());
 	       flagStatus = SetFlag(ingredientsText, eliminateArr);
       //  System.out.println("flag stat: "+ flagStatus);
 	        if(flagStatus==0) {       
            	flagText = "Eliminated" ;
                targetedMorbid = "PCOS";           
  	          // System.out.println("Writing recipe for :"+ flagText);
  	           flagTxt = (flagTxt==null)?targetedMorbid+"\n":flagTxt+","+targetedMorbid+"\n";
                //flag.add(targetedMorbid);
                WriteRecipeIntoFile(flagText,targetedMorbid);
        		eliminateArr = Arrays.asList(ConfigReader.getAddPCOS());
     	        flagStatus = SetFlag(ingredientsText, eliminateArr);
     	        if(flagStatus>0) {
     	          	flagText = "OnlyAdd" ;
                    targetedMorbid = "PCOS";           
                    WriteRecipeIntoFile(flagText,targetedMorbid);      
     	        }
            }
 	        
 			
	}
	public void CheckHypertensionMorbid() throws IOException {
		   eliminateArr = Arrays.asList(ConfigReader.geteliminateHypertension());
 	       flagStatus = SetFlag(ingredientsText, eliminateArr);
        // System.out.println("flag stat: "+ flagStatus);
 	        if(flagStatus==0) {       
            	flagText = "Eliminated" ;
                targetedMorbid = "HyperTension";           
  	          // System.out.println("Writing recipe for :"+ targetedMorbid);
  	           flagTxt = (flagTxt==null)?targetedMorbid+"\n":flagTxt+","+targetedMorbid+"\n";
                //flag.add(targetedMorbid);
                WriteRecipeIntoFile(flagText,targetedMorbid);
        		eliminateArr = Arrays.asList(ConfigReader.getAddHypertension());
     	        flagStatus = SetFlag(ingredientsText, eliminateArr);
     	        if(flagStatus>0) {
     	          	flagText = "OnlyAdd" ;
                    targetedMorbid = "HyperTension";           
                    WriteRecipeIntoFile(flagText,targetedMorbid);      
     	        }
            }
 	        
 			
	}
	public void CheckHypothyroidismMorbid() throws IOException {
		   eliminateArr = Arrays.asList(ConfigReader.geteliminateHypothyroidism());
 	       flagStatus = SetFlag(ingredientsText, eliminateArr);
        // System.out.println("flag stat: "+ flagStatus);
 	        if(flagStatus==0) {       
            	flagText = "Eliminated" ;
                targetedMorbid = "Hypothyroidism";           
  	          // System.out.println("Writing recipe for :"+ targetedMorbid);
  	           flagTxt = (flagTxt==null)?targetedMorbid+"\n":flagTxt+","+targetedMorbid+"\n";
                //flag.add(targetedMorbid);
                WriteRecipeIntoFile(flagText,targetedMorbid);
        		eliminateArr = Arrays.asList(ConfigReader.getAddHypothyroidism());
     	        flagStatus = SetFlag(ingredientsText, eliminateArr);
     	        if(flagStatus>0) {
     	          	flagText = "OnlyAdd" ;
                    targetedMorbid = "Hypothyroidism";           
                    WriteRecipeIntoFile(flagText,targetedMorbid);      
     	        }
            }
 	        
 	
	}
	public void CheckDiabetesMorbid() throws IOException {
		   eliminateArr = Arrays.asList(ConfigReader.geteliminateDiebetes());
 	       flagStatus = SetFlag(ingredientsText, eliminateArr);
        // System.out.println("flag stat: "+ flagStatus);
 	        if(flagStatus==0) {       
            	flagText = "Eliminated" ;
                targetedMorbid = "Diabetes";           
  	           //System.out.println("Writing recipe for :"+ targetedMorbid);
  	           flagTxt = (flagTxt==null)?targetedMorbid+"\n":flagTxt+","+targetedMorbid+"\n";
                //flag.add(targetedMorbid);
                WriteRecipeIntoFile(flagText,targetedMorbid);
        		eliminateArr = Arrays.asList(ConfigReader.getAddDiabetes());
     	        flagStatus = SetFlag(ingredientsText, eliminateArr);
     	        if(flagStatus>0) {
     	          	flagText = "OnlyAdd" ;
                    targetedMorbid = "Diabetes";           
                    WriteRecipeIntoFile(flagText,targetedMorbid);      
     	        }
            }
 	        
 	        		
	}
	public void GetNutrientValues() {
		String nutri = "";
		for(WebElement nutriText: nutrient) 
			nutri = (nutri==null)? nutriText.getText()+"\n":nutri+nutriText.getText()+"\n"; 
        nutrientList.add(nutri) ;	    	
	    	//System.out.println("nutrientRec[8]: "+ recipe[8]);
	
}

	public void GetCategory() {		
         //String[] categoryName = GetCategoryText(categories);
          String[] categoryName  =  GetCategoryText(categories);
  	      List<String> category = Arrays.asList(categoryName);
		  List<String> categoryArr = Arrays.asList(ConfigReader.getRecipeCategory());
		  List<String> foodArr = Arrays.asList(ConfigReader.getFoodCategory());
		  List<String> matchCategory = Arrays.asList();
          String recipeText=null;
		  matchCategory = category.stream().filter(
	    	                   s -> categoryArr.stream().anyMatch(s1 -> s.contains(s1))
	    	                   ).collect(Collectors.toList());

		  if(matchCategory.size()==0)
              recipeCategory.add("Snack/Lunch");
		  else {
		   for (String result : matchCategory) {
	    	       //System.out.println(category+" : "+ result); 
                   recipeCategory.add(result);
          	   }
		  }
		    	  matchCategory = category.stream().filter(
		    			s -> foodArr.stream().anyMatch(s1 -> s.contains(s1))
		    			).collect(Collectors.toList());  	
		        if(matchCategory.size()==0)
		        	recipeText = "Veg";
		        else
		    	  for (String result : matchCategory) {
		    	     //  System.out.println(category+" : "+ result); 
		    		  recipeText = (recipeText==null)? result:recipeText+result;
		    	  }
		    	  if (recipeText.equalsIgnoreCase("egg") || recipeText.equalsIgnoreCase("Egg") ) 
		    		   recipeText ="Eggitarian";
		          foodCategory.add(recipeText);
		    //System.out.println("recipeCategory[2]: "+recipe[2]);
		    //System.out.println("foodCategory[2]: "+recipe[3]);

	}




	public void GetTime() {	  	
		try {
		    prepTimeList.add(prepTime.getText());
		}  catch(Exception e) {
	        prepTimeList.add("NA");
		}    
	    try {   
		  	 cookTimeList.add(cookTime.getText());

		}catch(Exception e) {
	  	    cookTimeList.add("NA");
		}    
		    //System.out.println("cook time: "+recipe[6]+" pre "+recipe[5]);		
	}



	public void GetMethod() {	
		String methodTxt="";
		for(WebElement methodText: methodList) 
			methodTxt = (methodTxt==null)? methodText.getText()+"\n":methodTxt+methodText.getText()+"\n"; 

            method.add(methodTxt) ;	    	
		
	
	    	//System.out.println("ingr[7]: "+ recipe[7]);
	}

	
	public void WriteRecipeIntoFile(String sheet, String morbid) throws IOException{
         System.out.println("In Writing recipe for :"+ morbid.replace("\n"," "));

	    path = System.getProperty("user.dir")+"/src/test/resources/Lists/ListOfRecipes.xlsx";
	    xlUtility = new XLUtility(path, sheet);
	    int rowCount = xlUtility.getRowCount(sheet); 
	    //rowCount++;

	   // System.out.println("row: "+rowCount);
       //  System.out.println(id.get(0));
	   
	    xlUtility.CreateNewCell(sheet, rowCount, id.get(0));
	    xlUtility.CreateNewCell(sheet, rowCount, name.get(0));
	    xlUtility.CreateNewCell(sheet, rowCount, recipeCategory.get(0));
	    xlUtility.CreateNewCell(sheet, rowCount, foodCategory.get(0));
	    xlUtility.CreateNewCell(sheet, rowCount, ingred.get(0));
	    xlUtility.CreateNewCell(sheet, rowCount, prepTimeList.get(0));
	    xlUtility.CreateNewCell(sheet, rowCount, cookTimeList.get(0));
	    xlUtility.CreateNewCell(sheet, rowCount, method.get(0));
	    xlUtility.CreateNewCell(sheet, rowCount, nutrientList.get(0));
	    xlUtility.CreateNewCell(sheet, rowCount, morbid);
	    xlUtility.CreateNewCell(sheet, rowCount, recipeUrl.get(0));
	    xlUtility.CreateNewCell(sheet, rowCount, flag.get(0));
	    xlUtility.CreateNewCell(sheet, rowCount, "CloseFile");

	}   
	
	
}
