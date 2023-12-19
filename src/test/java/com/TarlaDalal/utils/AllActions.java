/*Code details:
	#Author: Meenakshi Dated: 6-Nov-2023
*/

package com.TarlaDalal.utils;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    static ArrayList<Recipes> scrapedRecipeList = new ArrayList<Recipes>();
    static List<Recipes> filteredRecipeList = new ArrayList<Recipes>();
   // static List<String> flagData = new ArrayList<String>();
    static Map<String,String> flagData = new HashMap<String, String>();
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
		return true;
	}
	public void Quit_Driver(WebDriver driver) {
		driver.quit();
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
				   count++;
				   }
			 return pageArr;
		}
		public String[] GetCategoryText(List<WebElement> categories) {
              String[] arr = null;
			 for(WebElement textArr: categories) {
				   arr = textArr.getText().split(" ");				   
			 }
			 
			return arr;
		}
		public String[] GetHyperLink(List<WebElement> pages) {
			 String[] pageArr = new String[pages.size()];
			 int count=0;
			 for(WebElement page: pages) {
					   pageArr[count] = page.getAttribute("href");
				   count++;
				   }
			 return pageArr;
		}


	public static int SetFlag(String[] ingredientsText,List<String> eliminateArr) {
		List<String> ingredText = Arrays.asList(ingredientsText);

		List<String> result = ingredText.stream().filter(
    			s -> eliminateArr.stream().anyMatch(s1 -> s.contains(s1))
    			).collect(Collectors.toList());  	
		 return result.size();
	}		 
			   	  
}