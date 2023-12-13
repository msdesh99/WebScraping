package com.TarlaDalal.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.TarlaDalal.utils.AllActions;
import com.TarlaDalal.utils.ConfigReader;



public class SubListPage extends AllActions{
	
	WebDriver driver;
	By locator;
	
	public SubListPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//*[@id='taxonomy-nodes__list_1-0']/li/a/span")
	List<WebElement> subListMenu;
    
	@FindBy(xpath="//*[@class='header-nav__sublist']//li/following::a[contains(text(),'View All')]")
	WebElement viewAll;

	public void GetSubList(String subMenu, String url) throws InterruptedException {
		PageFactory.initElements(driver, this);
		   System.out.println("SubList count: "+subListMenu.size());
		   String[] list = GetUrlString(subListMenu);
		   for(String subList: list) {
			   if(subList.contentEquals("U.s. Recipes"))
				   subList = "U.S. Recipes";
			   if(subList.contentEquals("Bbq & Grilling"))
				   subList = "BBQ & Grilling";
			   if(subList.contentEquals("Diy Recipes"))
				   subList = "DIY Recipes";
			  // System.out.println("subList: "+subList); 

			    locator = By.xpath("//*[@id='taxonomy-nodes__list_1-0']/li/a/span"
			    		+ "[contains(text(),'"+subList +"')]");
				ClickElement(CallDriverWait(driver, locator),driver);
				
			   driver.get(url);
				
			   ScreenScrollDown(driver);
			   locator=By.xpath("//*[@id='taxonomy-nodes__chop_1-0']//span");
			   ClickElement(CallDriverWait(driver, locator),driver);
				
		   }
		   driver.get(ConfigReader.getBaseUrl());

	}


	
     
}
