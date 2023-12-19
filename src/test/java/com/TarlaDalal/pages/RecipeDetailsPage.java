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

import com.TarlaDalal.model.Recipes;
import com.TarlaDalal.utils.AllActions;
import com.TarlaDalal.utils.ConfigReader;
import com.TarlaDalal.utils.LoggerLoad;
import com.TarlaDalal.utils.XLUtility;

public class RecipeDetailsPage extends AllActions {

	WebDriver driver;
	By locator;
	String path = "";
	XLUtility xlUtility;
	int flagStatus;
	String flagText;
	String targetedMorbid;
	String[] ingredientsText;
	String flagTxt;
	List<String> flag = new ArrayList<String>();

	List<String> eliminateArr;

	Recipes recipes;

	public RecipeDetailsPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@itemprop='recipeIngredient']//a")
	List<WebElement> ingredients;

	@FindBy(css = "#rcpnutrients td")
	List<WebElement> nutrient;

	@FindBy(css = "[itemprop=recipeCategory] span")
	List<WebElement> categories;

	@FindBy(css = "[itemprop=prepTime]")
	WebElement prepTime;

	@FindBy(css = "[itemprop=cookTime]")
	WebElement cookTime;

	@FindBy(css = "div#recipe_small_steps>span>[itemprop=recipeInstructions] [itemprop=itemListElement] span")
	List<WebElement> methodList;

	public void GetRecipeDetails(String recipeId, String recipeName, String url)
			throws InterruptedException, IOException {

		recipes = new Recipes();
		recipes.setRecipeID(recipeId);
		recipes.setRecipeName(recipeName);
		recipes.setUrl(url);

		GetMethod();
		GetTime();
		GetCategory();
		GetNutrientValues();
		GetIngredients();
	}

	public void GetIngredients() throws IOException {
		String indgOri = driver.findElement(By.xpath("//div[@id='rcpinglist']")).getText();
		String indg = indgOri.replace("\n", " ").toLowerCase().replace("(", " ").replace(")", " ");

		recipes.setIngredients(indgOri);

		if (indg != null) {
			flag.add("NA");
			ingredientsText = indg.split(",");
			CheckDiabetesMorbid();
			CheckHypothyroidismMorbid();
			CheckHypertensionMorbid();
			CheckPCOSMorbid();
			Checkallergies();
			flag.remove(0);
			flag.add(flagTxt);
			WriteRecipeIntoFile("All", flag.get(0));
		}
	}

	public void Checkallergies() throws IOException {
		eliminateArr = Arrays.asList(ConfigReader.getallergies());
		flagStatus = SetFlag(ingredientsText, eliminateArr);
		if (flagStatus > 0) {
			flagText = "Allergies";
			targetedMorbid = "NA";
			flagTxt = (flagTxt == null) ? flagText + "\n" : flagTxt + "," + flagText + "\n";
			WriteRecipeIntoFile(flagText, targetedMorbid);
		}
	}

	public void CheckPCOSMorbid() throws IOException {
		//eliminateArr = Arrays.asList(ConfigReader.geteliminatePCOS());
		  eliminateArr = Arrays.asList(checkPCOSElimination());
		flagStatus = SetFlag(ingredientsText, eliminateArr);
		if (flagStatus == 0) {
			flagText = "Eliminated";
			targetedMorbid = "PCOS";
			flagTxt = (flagTxt == null) ? targetedMorbid + "\n" : flagTxt + "," + targetedMorbid + "\n";
			WriteRecipeIntoFile(flagText, targetedMorbid);
			eliminateArr = Arrays.asList(ConfigReader.getAddPCOS());
			flagStatus = SetFlag(ingredientsText, eliminateArr);
			if (flagStatus > 0) {
				flagText = "Eliminated-Add";
				targetedMorbid = "PCOS";
				WriteRecipeIntoFile(flagText, targetedMorbid);
			}
		}

	}

	public void CheckHypertensionMorbid() throws IOException {
		eliminateArr = Arrays.asList(ConfigReader.geteliminateHypertension());
		flagStatus = SetFlag(ingredientsText, eliminateArr);
		if (flagStatus == 0) {
			flagText = "Eliminated";
			targetedMorbid = "HyperTension";
			flagTxt = (flagTxt == null) ? targetedMorbid + "\n" : flagTxt + "," + targetedMorbid + "\n";
			WriteRecipeIntoFile(flagText, targetedMorbid);
			eliminateArr = Arrays.asList(ConfigReader.getAddHypertension());
			flagStatus = SetFlag(ingredientsText, eliminateArr);
			if (flagStatus > 0) {
				flagText = "Eliminated-Add";
				targetedMorbid = "HyperTension";
				WriteRecipeIntoFile(flagText, targetedMorbid);
			}
		}
	}

	public void CheckHypothyroidismMorbid() throws IOException {
		eliminateArr = Arrays.asList(ConfigReader.geteliminateHypothyroidism());
		flagStatus = SetFlag(ingredientsText, eliminateArr);
		if (flagStatus == 0) {
			flagText = "Eliminated";
			targetedMorbid = "Hypothyroidism";
			flagTxt = (flagTxt == null) ? targetedMorbid + "\n" : flagTxt + "," + targetedMorbid + "\n";
			WriteRecipeIntoFile(flagText, targetedMorbid);
			eliminateArr = Arrays.asList(ConfigReader.getAddHypothyroidism());
			flagStatus = SetFlag(ingredientsText, eliminateArr);
			if (flagStatus > 0) {
				flagText = "Eliminated-Add";
				targetedMorbid = "Hypothyroidism";
				WriteRecipeIntoFile(flagText, targetedMorbid);
			}
		}

	}

	public void CheckDiabetesMorbid() throws IOException {
		eliminateArr = Arrays.asList(ConfigReader.geteliminateDiebetes());
		flagStatus = SetFlag(ingredientsText, eliminateArr);
		if (flagStatus == 0) {
			flagText = "Eliminated";
			targetedMorbid = "Diabetes";
			flagTxt = (flagTxt == null) ? targetedMorbid + "\n" : flagTxt + "," + targetedMorbid + "\n";
			WriteRecipeIntoFile(flagText, targetedMorbid);
			eliminateArr = Arrays.asList(ConfigReader.getAddDiabetes());
			flagStatus = SetFlag(ingredientsText, eliminateArr);
			if (flagStatus > 0) {
				flagText = "Eliminated-Add";
				targetedMorbid = "Diabetes";
				WriteRecipeIntoFile(flagText, targetedMorbid);
			}
		}
	}

	public void GetNutrientValues() {
		String nutri = "";
		for (WebElement nutriText : nutrient)
			nutri = (nutri == null) ? nutriText.getText() + "\n" : nutri + nutriText.getText() + "\n";
		recipes.setNutrient(nutri);
	}

	public void GetCategory() {
		String[] categoryName = GetCategoryText(categories);
		List<String> category = Arrays.asList(categoryName);
		List<String> categoryArr = Arrays.asList(ConfigReader.getRecipeCategory());
		List<String> foodArr = Arrays.asList(ConfigReader.getFoodCategory());
		List<String> matchCategory = Arrays.asList();
		String recipeText = null;
		matchCategory = category.stream().filter(s -> categoryArr.stream().anyMatch(s1 -> s.contains(s1)))
				.collect(Collectors.toList());
		if (matchCategory.size() == 0) {
			recipes.setRecipeCategory("Snack/Lunch");
		} else {
			for (String result : matchCategory) {
				recipes.setRecipeCategory(result);
			}
		}
		matchCategory = category.stream().filter(s -> foodArr.stream().anyMatch(s1 -> s.contains(s1)))
				.collect(Collectors.toList());
		if (matchCategory.size() == 0)
			recipeText = "Veg";
		else {
			for (String result : matchCategory) {
				recipeText = (recipeText == null) ? result : recipeText + result;
			}
		}
		if (recipeText.equalsIgnoreCase("egg") || recipeText.equalsIgnoreCase("Egg"))
			recipeText = "Eggitarian";
		recipes.setFoodCategory(recipeText);
	}

	public void GetTime() {
		try {
			recipes.setPrepTime(prepTime.getText());
		} catch (Exception e) {
			recipes.setPrepTime("NA");
		}
		try {
			recipes.setCookTime(cookTime.getText());

		} catch (Exception e) {
			recipes.setCookTime("NA");
		}
	}

	public void GetMethod() {
		String methodTxt = "";
		for (WebElement methodText : methodList)
			methodTxt = (methodTxt == null) ? methodText.getText() + "\n" : methodTxt + methodText.getText() + "\n";
		recipes.setMethod(methodTxt);

	}

	public void WriteRecipeIntoFile(String sheet, String morbid) throws IOException {
		System.out.println("In Writing recipe for :" + morbid.replace("\n", " "));
	    LoggerLoad.info("<===In Writing recipe for : "+ morbid.replace("\n", " ") +" ===> ");	


		path = System.getProperty("user.dir") + "/src/test/resources/Lists/ListOfRecipes.xlsx";
		xlUtility = new XLUtility(path, sheet);
		int rowCount = xlUtility.getRowCount(sheet);

		xlUtility.CreateNewCell(sheet, rowCount, recipes.getRecipeID());
		xlUtility.CreateNewCell(sheet, rowCount, recipes.getRecipeName());

		xlUtility.CreateNewCell(sheet, rowCount, recipes.getRecipeCategory());
		xlUtility.CreateNewCell(sheet, rowCount, recipes.getFoodCategory());
		xlUtility.CreateNewCell(sheet, rowCount, recipes.getIngredients());
		xlUtility.CreateNewCell(sheet, rowCount, recipes.getPrepTime());
		xlUtility.CreateNewCell(sheet, rowCount, recipes.getCookTime());
		xlUtility.CreateNewCell(sheet, rowCount, recipes.getMethod());
		xlUtility.CreateNewCell(sheet, rowCount, recipes.getNutrient());
		xlUtility.CreateNewCell(sheet, rowCount, morbid);
		xlUtility.CreateNewCell(sheet, rowCount, recipes.getUrl());
		xlUtility.CreateNewCell(sheet, rowCount, flag.get(0));
		xlUtility.CreateNewCell(sheet, rowCount, "CloseFile");
	}

	public String[] checkPCOSElimination() throws IOException
	{	
		String pcosText=null;
        String[] pcosArr = new String[1];
		String  projectpath = System.getProperty("user.dir"); 
		String xlpath=projectpath+"/src/test/resources/DataToEliminate/PCOS_Eliminate_Ingredient_List.xlsx";
		String sheetName= "PCOS_Eliminate_Ingredients";
		
		XLUtility reader = new XLUtility(xlpath, sheetName);
		
		int rowCount= reader.getRowCount("PCOS_Eliminate_Ingredients"); 
		
		int colNum=0;
		for(int rowNum=1; rowNum<rowCount; rowNum++){
			pcosText = (pcosText==null)? reader.getCellData("PCOS_Eliminate_Ingredients",rowNum, colNum):
				pcosText+","+ reader.getCellData("PCOS_Eliminate_Ingredients",rowNum, colNum);
		}
		pcosArr[0] = pcosText;
		return pcosArr;

	}		
}
