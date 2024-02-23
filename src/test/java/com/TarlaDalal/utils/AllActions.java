/*Code details:
	#Author: Meenakshi Dated: 6-Nov-2023
*/

package com.TarlaDalal.utils;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AllActions {
	static JavascriptExecutor js;
	static Actions action;
	static List<String> eliminateDiebetesArr,eliminateHypothroidArr,eliminateHypertensionArr,eliminatePCOSArr,eliminateAllergiesArr;
	static List<String> addDiebetesArr,addHypothroidArr,addHypertensionArr,addPCOSArr;

	public static WebElement DriverWaitForElement(WebDriver driver, WebElement element) {
		WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(6))
				.until(ExpectedConditions.visibilityOf(element));
		return ele;
	}

	public static boolean ClickElement(WebElement element, WebDriver driver) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);
		return true;
	}

	public void Quit_Driver(WebDriver driver) {
		driver.quit();
	}

	public static WebElement CallDriverWait(WebDriver driver, By locator) {
		return new WebDriverWait(driver, Duration.ofSeconds(6))
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static void ScreenScrollDown(WebDriver driver) {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1850)", "");
	}

	public String[][] GetHyperText(List<WebElement> recipeList) {
	//	String[][] list = new String[recipeList.size()][ConfigReader.getcellNames().length];
		String[][] list = new String[recipeList.size()][2];

		String rawId = "";
		int count = 0;
		for (WebElement menu : recipeList) {
			rawId = menu.getAttribute("href").toString();
			list[count][0] = menu.getText();
			list[count][1] = rawId.substring(rawId.lastIndexOf('-') + 1, rawId.length() - 1);
			count++;
		}
		return list;
	}

	public String[] GetPageText(List<WebElement> pages) {
		String[] pageArr = new String[pages.size()];
		int count = 0;
		for (WebElement page : pages) {
			pageArr[count] = page.getText();
			count++;
		}
		return pageArr;
	}

	public String[] GetCategoryText(List<WebElement> categories) {
		String[] arr = null;
		for (WebElement textArr : categories) {
			arr = textArr.getText().split(" ");
		}

		return arr;
	}

	public String[] GetHyperLink(List<WebElement> pages) {
		String[] pageArr = new String[pages.size()];
		int count = 0;
		for (WebElement page : pages) {
			pageArr[count] = page.getAttribute("href");
			count++;
		}
		return pageArr;
	}

	public static int SetFlag(String[] ingredientsText, List<String> eliminateArr) {
		List<String> ingredText = Arrays.asList(ingredientsText);

		List<String> result = ingredText.stream().filter(s -> eliminateArr.stream().anyMatch(s1 -> s.contains(s1)))
				.collect(Collectors.toList());
		return result.size();
	}
	public static void setAllEliminatedListArr() throws IOException {
		eliminateDiebetesArr = Arrays.asList(ConfigReader.geteliminateDiebetes());	
		eliminateHypothroidArr = Arrays.asList(ConfigReader.geteliminateHypothyroidism());
		eliminateHypertensionArr = Arrays.asList(ConfigReader.geteliminateHypertension());
		eliminatePCOSArr = Arrays.asList(checkPCOSElimination());
		eliminateAllergiesArr = Arrays.asList(ConfigReader.getallergies());

		addDiebetesArr = Arrays.asList(ConfigReader.getAddDiabetes());	
		addHypothroidArr = Arrays.asList(ConfigReader.getAddHypothyroidism());
		addHypertensionArr = Arrays.asList(ConfigReader.getAddHypertension());
		addPCOSArr = Arrays.asList(ConfigReader.getAddPCOS());;

	}
	public static List<String> getEliminatedDiebetesListArr() {
		return eliminateDiebetesArr ;		
	}
	public static List<String> getEliminatedHypothroidArr() {
		return eliminateHypothroidArr ;		
	}
	public static List<String> getEliminateHypertensionArr() {
		return eliminateHypothroidArr ;		
	}
	public static List<String> getEliminatePCOSArr() {
		return eliminatePCOSArr ;		
	}
	public static List<String> getEliminateAllergiesArr() {
		return eliminateAllergiesArr ;		
	}
	
	public static List<String> getAddDiebetesListArr() {
		return addDiebetesArr ;		
	}
	public static List<String> getAddHypothroidArr() {
		return addHypothroidArr ;		
	}
	public static List<String> getAddHypertensionArr() {
		return addHypothroidArr ;		
	}
	public static List<String> getAddPCOSArr() {
		return addPCOSArr ;		
	}
	
	
	public static String[] checkPCOSElimination() throws IOException
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