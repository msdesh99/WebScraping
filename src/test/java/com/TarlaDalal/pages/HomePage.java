package com.TarlaDalal.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.TarlaDalal.utils.AllActions;
import com.TarlaDalal.utils.ConfigReader;


public class HomePage extends AllActions{
	WebDriver driver;
    String handle;
    By locator;
    String subMenu;
    SubListPage subListPage;
    byte count;
    
	public HomePage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='respglink' or @class='rescurrpg']")
    WebElement pages;

	//@FindBy(xpath="//*[@class='rcc_recipethumbnail']")
    //List<WebElement> recipeList;
	
	//@FindBy(xpath="//*[@class='rcc_rcpcore']//*/a[@itemprop='url']")
	@FindBy(xpath="//*[@id='maincontent']//*[@class='rcc_rcpcore']//*/a[@itemprop='url']")
    List<WebElement> recipeList;
	//*[@class='rcc_rcpcore']//*/a[@itemprop="url" and text()='Cabbage Jowar Muthias']


	public void getRecipe() throws InterruptedException {
		   String[] list = GetHyperText(recipeList);
		   count =0;
		   //driver.get(ConfigReader.getDiabetesUrl());
     
		  // for(WebElement recipe: recipeList) {
		   for(String recipe: list) {	   
		   //for(String subMenu: recipeList) {
			   System.out.println("subhome: "+recipe);
			  // driver.get(ConfigReader.getDiabetesUrl());
                //subMenu="Occasions";
			//	if (count <= 5) {
				ScreenScrollDown(driver);

					locator = By.xpath("//*[@class='rcc_rcpcore']//*/a[@itemprop='url' "
							+ "and text()='" + recipe + "']");
					//System.out.println("loca: "+locator);
					ClickElement(CallDriverWait(driver, locator), driver);
					//ScreenScrollDown(driver);

					   //driver.get(ConfigReader.getDiabetesUrl());
						//ScreenScrollDown(driver);
						//count++;
						break;
				//}

			/*		//locator = By.xpath("//*[@id='taxonomy-nodes__chop_1-0']//span");
					//ClickElement(CallDriverWait(driver, locator), driver);

				//	subListPage = PageFactory.initElements(driver, SubListPage.class);
				//	subListPage.GetSubList(subMenu, driver.getCurrentUrl());
				//	driver.get(ConfigReader.getBaseUrl());
					// break;
				}*/
		   }
	}
	
	

}
