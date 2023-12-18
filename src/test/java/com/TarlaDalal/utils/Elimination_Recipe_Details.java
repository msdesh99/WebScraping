package com.TarlaDalal.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriverException;

//import com.numpyninja.taraladala.food.categories.FoodCategory;
//import com.numpyninja.taraladala.food.categories.RecipeCategory;

public class Elimination_Recipe_Details{
    //XLUtility xlUtility;
	public static List<String> checkElimination() throws IOException
	{		
		List<String> PCOSEliminateIngredients =  new ArrayList<String>();

		try {
		String  projectpath = System.getProperty("user.dir"); 
		String xlpath=projectpath+"/src/test/resources/TestData/PCOS_Eliminate_Ingredient_List.xlsx";
		String sheetName= "PCOS_Eliminate_Ingredients";
		
		XLUtility reader = new XLUtility(xlpath, sheetName);
		
		int rowCount= reader.getRowCount("PCOS_Eliminate_Ingredients"); 
		
		//ingd= driver.findElement(By.xpath("//div[@id='rcpinglist']")).getText().replace("\n", " ").toLowerCase().replace("(", " ").replace(")", " ");		
		//String[] ingdArray=ingd.split(" ");
		//ingdList= Arrays.asList(ingdArray);
		int colNum=0;
		for(int rowNum=1; rowNum<rowCount; rowNum++){
			PCOSEliminateIngredients.add(reader.getCellData("PCOS_Eliminate_Ingredients",rowNum, colNum));
		}
		}catch(WebDriverException e) {
			e.printStackTrace();
		}
		return PCOSEliminateIngredients;

	}	
}
