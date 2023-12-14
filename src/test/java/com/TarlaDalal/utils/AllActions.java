/*Code details:
	#Author: Meenakshi Dated: 6-Nov-2023
*/

package com.TarlaDalal.utils;

import static com.TarlaDalal.utils.AllActions.scrapedRecipeList;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

    static XLUtility xlUtility;

	public static void SendKeysElement(WebDriver driver, WebElement element, String inputString) {
		action = new Actions(driver);
		action.sendKeys(element, inputString).build().perform();
	}
	public static void ClickArrElement(WebDriver driver, WebElement[] element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();arguments[1].click();", element[0], element[1]);
	}
	public static void ClickRegisterArrElement(WebDriver driver, WebElement[] element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();arguments[1].click();arguments[2].click();", element[0], element[1],element[2]);
	}
	public static boolean ClickElement(WebElement element, WebDriver driver) {
		WebElement ele;
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
	  }
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
	
	public static WebElement FindElementWithLocator(WebDriver driver, By locator) {
		return new WebDriverWait(driver, Duration.ofSeconds(7))
				.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public static void DriverWaitForUrl(WebDriver driver, String url) {
		new WebDriverWait(driver, Duration.ofSeconds(6))
		    .until(ExpectedConditions.urlMatches(url));
	}
	public static WebElement DriverWaitForElement(WebDriver driver, WebElement element) {		
		WebElement ele =  new WebDriverWait(driver, Duration.ofSeconds(6))
				.until(ExpectedConditions.visibilityOf(element));
		return ele;
	}
	public static void DriverWaitForClickable(WebDriver driver, By locator) {
		new WebDriverWait(driver, Duration.ofSeconds(6))
		.until(ExpectedConditions.elementToBeClickable(locator));
	}
	public static WebElement CallDriverWait(WebDriver driver, By locator) {
		return new WebDriverWait(driver, Duration.ofSeconds(6))
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
}
	public static void DriverWaitForTextAndVisible(WebDriver driver, WebElement element, String text) {		
	new WebDriverWait(driver, Duration.ofSeconds(6))
		.until(ExpectedConditions.and(
		ExpectedConditions.textToBePresentInElement(element, text),
		ExpectedConditions.visibilityOf(element)));
}
	public static String WaitAndClickAlert(WebDriver driver) {
			try {
				alert = new WebDriverWait(driver,Duration.ofSeconds(4)).until(ExpectedConditions.alertIsPresent());
				String alertText = alert.getText();
				System.out.println(alertText);
				alert.accept();			
				return alertText;
			} catch (Exception e) {
				new Exception("Alert element Not Found: "+e);
				return null;
			}		
	}
	
	public static void ScrollToElementjs(WebDriver driver, WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true);", element);

	}
	public static void TextIndentation(WebDriver driver, WebElement pythonElement, int row, int space,boolean flag) {
		 action = new Actions(driver);
	       // Keys cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
		for(int i=1;i<=row;i++) {
		      action.sendKeys(Keys.ARROW_UP).keyUp(Keys.SHIFT).perform();
		       for(int j=1;j<=space;j++) {
	            if(i==1 && flag) action.sendKeys(Keys.BACK_SPACE).perform();
	            else action.sendKeys(Keys.DELETE).perform();
			   }
		}  
	}	
	public static void TextIndentationForPractice(WebDriver driver, WebElement pythonElement) {
		 action = new Actions(driver);
		      action.keyDown(Keys.CONTROL).sendKeys(Keys.chord("a")).keyUp(Keys.CONTROL).perform();
		      //action.sendKeys(Keys.chord(Keys.CONTROL, "a")).perform();
		      action.sendKeys(Keys.DELETE).perform();		      
	}	
	public static String DriverWaitForElementOrAlert(WebDriver driver, WebElement pythonResult) {
		    String output=null;
	         try {
	           alert = new WebDriverWait(driver, Duration.ofSeconds(4))
	            		.until(ExpectedConditions.alertIsPresent());  
	            		     try {
	            		    	 output = driver.switchTo().alert().getText();

							} catch (Exception e) {
	         	            	DriverWaitForElement(driver, pythonResult);
							}
	 	         	        alert.accept();		 
	         } catch (Exception e) {
			 }
	         return output;
	    }
		
    public static void ScreenScrollDown(WebDriver driver) {
    	js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollBy(0,1850)", "");
    }	
    public static void GoToParentWindowHandle(WebDriver driver, String parentWindowHandler) {
		Set<String> s = driver.getWindowHandles();
	
		// Now iterate using Iterator
		Iterator<String> I1 = s.iterator();
		while (I1.hasNext()) {
		String child_window = I1.next();
		System.out.println("itr parent han: "+parentWindowHandler);
		System.out.println("itr child han: "+child_window);

		if (parentWindowHandler.equals(child_window)) {
		    driver.switchTo().window(parentWindowHandler);
			//return FindElementWithLocator(driver,locator);
		   // element1.click() 
		    break;
		}
	   }
}
	public static WebElement GetCurrentWindowHandle(WebDriver driver, By locator) {
		String parent = driver.getWindowHandle();
		System.out.println("parent han: "+parent);
		//FindElementWithLocator(driver,locator);
		//driver.findElement(By.xpath("//*[@id='answer_form']//*[contains(@class,'CodeMirror')]//textarea")).click();
		Set<String> s = driver.getWindowHandles();
	
		// Now iterate using Iterator
		Iterator<String> I1 = s.iterator();
		while (I1.hasNext()) {
		String child_window = I1.next();
		System.out.println("itr parent han: "+parent);
		System.out.println("itr child han: "+child_window);

		if (!parent.equals(child_window)) {
		    driver.switchTo().window(child_window);
			return FindElementWithLocator(driver,locator);
		   // element1.click() 
		}
	   }
		return null;	
}
	 public String[] GetUrlString(List<WebElement> menuList) {
	    	String[]  list = new String[menuList.size()];
	    	System.out.println("action list count: "+list.length);
			int count=0;
			String subMenu1="";
			String menuText="";
			   for(WebElement menu: menuList) {
				   menuText = menu.getText();
				   if(menuText.contains("'")) 						   
					   menuText= menuText.substring(0, menuText.indexOf("'"));

				   String[] subMenu = menuText.split(" ");
				   
				   for(int j=0;j<subMenu.length;j++) {
							  if(j==0)
						         subMenu1 = subMenu[j].charAt(0)+subMenu[j].substring(1).toLowerCase();
							  else {
								  if(subMenu[j].contentEquals("AND")|| subMenu[j].contentEquals("FOR")
										  || subMenu[j].contentEquals("DE") || subMenu[j].contentEquals("OF")
										  || subMenu[j].contentEquals("TO")) 
							            subMenu1 = subMenu1+" "+ subMenu[j].toLowerCase();
								  else
						             subMenu1 = subMenu1+" "+ subMenu[j].charAt(0)+subMenu[j].substring(1).toLowerCase(); 
							  }   
						 
				   }
					list[count] = subMenu1;
					count++;
					subMenu1="";
				   	
			   }
			   return list;
	    }
		public String[][] GetHyperText(List<WebElement> recipeList) {
	    	String[][]  list = new String[recipeList.size()][ConfigReader.getcellNames().length];
	    	System.out.println("cell leng: "+ ConfigReader.getcellNames().length);
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
		public String[]  GetRecipeID(List<WebElement> recipesID) {
	    	String[]  list = new String[recipesID.size()];
	    	//System.out.println("action list count: "+list.length);
			int count=0;
			   for(WebElement menu: recipesID) {
				    System.out.println("int recipe id: "+ Integer.valueOf(menu.getText()));
					list[count] = menu.getText();
					count++;
			   }
			   return list;
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

			recipes.setMorbid(recipe[1]);

			scrapedRecipeList.add(recipes);		
		   // System.out.println("count: "+scrapedRecipeList.size());
           // for(Recipes recipe1: scrapedRecipeList) {
            	//System.out.println("name: "+recipe1.getRecipeName());
            	//System.out.println("id: "+recipe1.getRecipeID());             
            //}  	
		}

      public static void AddInRecipesXLS() throws IOException {
    	  System.out.println("scr: "+scrapedRecipeList.size());
    	 	String path = System.getProperty("user.dir")+"/src/test/resources/Lists/ListOfRecipes.xlsx";
    	    xlUtility = new XLUtility(path, "DiabetesEliminated");
    	    xlUtility.WriteIntoFile(scrapedRecipeList);
  	}
  }
