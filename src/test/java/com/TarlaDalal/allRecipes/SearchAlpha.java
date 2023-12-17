package com.TarlaDalal.allRecipes;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.TarlaDalal.pages.HomePage;
import com.TarlaDalal.pages.PageNumberPage;
import com.TarlaDalal.pages.RecipeCategoryPage;
import com.TarlaDalal.utils.AllActions;
import com.TarlaDalal.utils.ConfigReader;

public class SearchAlpha extends AllActions {
	WebDriver driver;
	Properties prop;
	ConfigReader configReader;
	HomePage homePage;
	PageNumberPage pageNumberPage;
	RecipeCategoryPage recipeCategoryPage;
    AllRecipes allRecipes;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@BeforeTest
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		configReader = new ConfigReader();
		prop = configReader.initializeProperties();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.manage().window().maximize();
	}

	@AfterTest
	public void tearDown() throws Exception {
	}

	@Test
	public void testA1() throws InterruptedException, IOException {
		driver.get(ConfigReader.getDiabetesUrl());
		pageNumberPage = PageFactory.initElements(driver, PageNumberPage.class);
		pageNumberPage.GetPage();
	} 

/*	@Test
	public void testA2() throws InterruptedException, IOException {
		driver.get(ConfigReader.getBaseUrl());
		recipeCategoryPage = PageFactory.initElements(driver, RecipeCategoryPage.class);
		recipeCategoryPage.GetCategory("Breakfast");
	}
	@Test
	public void testA3() throws InterruptedException, IOException {
		driver.get(ConfigReader.getBaseUrl());
		recipeCategoryPage = PageFactory.initElements(driver, RecipeCategoryPage.class);
		recipeCategoryPage.GetCategory("Lunch");
	} 
	@Test
	public void testA4() throws InterruptedException, IOException {
		driver.get(ConfigReader.getBaseUrl());
		recipeCategoryPage = PageFactory.initElements(driver, RecipeCategoryPage.class);
		recipeCategoryPage.GetCategory("snack");
	} 
	@Test
	public void testA5() throws InterruptedException, IOException {
		  driver.get(ConfigReader.getDinnerUrl());
	      homePage = PageFactory.initElements(driver,HomePage.class);
	      homePage.GetRecipe(driver.getCurrentUrl(),"Dinner","Veg");
	} 
	@Test
	public void testA6() throws InterruptedException, IOException {
		  driver.get(ConfigReader.geteggUrl());
	      homePage = PageFactory.initElements(driver,HomePage.class);
	      homePage.GetRecipe(driver.getCurrentUrl(),"snack","Egg");
	} 
	
	@Test
//	public void testHypoThyroidism() throws InterruptedException, IOException {
	public void testA8() throws InterruptedException, IOException {	
		driver.get(ConfigReader.getAllRecipeUrl());
		pageNumberPage = PageFactory.initElements(driver, PageNumberPage.class);		
		pageNumberPage.GetAllRecipePage();
	}
	
*/
	/*
	@Test
	public void testA7() throws InterruptedException, IOException {
		  //driver.get(ConfigReader.geteggUrl());
		  allRecipes = new AllRecipes();
		  allRecipes.getAZRecipe(driver);
	     // homePage = PageFactory.initElements(driver,HomePage.class);
	      //homePage.GetRecipe(driver.getCurrentUrl(),"snack","Egg");
	}  */
	

	@Test
	public void testC9999() throws InterruptedException, IOException {
		AddInRecipesXLS();
      
	}

}
