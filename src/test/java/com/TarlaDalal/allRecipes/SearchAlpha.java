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
import com.TarlaDalal.utils.XLUtility;

public class SearchAlpha extends AllActions {
	WebDriver driver;
	Properties prop;
	ConfigReader configReader;
	HomePage homePage;
	PageNumberPage pageNumberPage;
	RecipeCategoryPage recipeCategoryPage;
    XLUtility xlUtility;
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
	    String path = System.getProperty("user.dir")+"/src/test/resources/Lists/ListOfRecipes.xlsx";
	    xlUtility = new XLUtility(path, "All");
	    xlUtility.WriteAllSheetsHeading(); 
	}

	@AfterTest
	public void tearDown() throws Exception {
	}

	@Test
	public void testA1Diabetes() throws InterruptedException, IOException {
		driver.get(ConfigReader.getDiabetesUrl());
		pageNumberPage = PageFactory.initElements(driver, PageNumberPage.class);
		pageNumberPage.GetDiabetesPage();
	} 
/*
	@Test
	public void testA2Breakfast() throws InterruptedException, IOException {
		driver.get(ConfigReader.getBaseUrl());
		recipeCategoryPage = PageFactory.initElements(driver, RecipeCategoryPage.class);
		recipeCategoryPage.GetCategory("Breakfast");
	}
	@Test
	public void testA3Lunch() throws InterruptedException, IOException {
		driver.get(ConfigReader.getBaseUrl());
		recipeCategoryPage = PageFactory.initElements(driver, RecipeCategoryPage.class);
		recipeCategoryPage.GetCategory("Lunch");
	} 
	@Test
	public void testA4Snacks() throws InterruptedException, IOException {
		driver.get(ConfigReader.getBaseUrl());
		recipeCategoryPage = PageFactory.initElements(driver, RecipeCategoryPage.class);
		recipeCategoryPage.GetCategory("snack");
	} 
	@Test
	public void testA5Dinner() throws InterruptedException, IOException {
		  driver.get(ConfigReader.getDinnerUrl());
	      homePage = PageFactory.initElements(driver,HomePage.class);
	      homePage.GetRecipe(driver.getCurrentUrl());
	} 
	@Test
	public void testA6EggRecipes() throws InterruptedException, IOException {
		  driver.get(ConfigReader.geteggUrl());
	      homePage = PageFactory.initElements(driver,HomePage.class);
	      homePage.GetRecipe(driver.getCurrentUrl());
	} 
	
	@Test
	public void testA8AZRecipes() throws InterruptedException, IOException {	
		driver.get(ConfigReader.getAllRecipeUrl());
		pageNumberPage = PageFactory.initElements(driver, PageNumberPage.class);		
		pageNumberPage.GetAZRecipePage();
	}
	*/
	@Test
	public void testA9PCOS() throws InterruptedException, IOException {	
		driver.get(ConfigReader.getBaseUrl());
		pageNumberPage = PageFactory.initElements(driver, PageNumberPage.class);		
		pageNumberPage.GetPCOSPage(driver);
	}		
	@Test
	public void testA10HypoThyroidism() throws InterruptedException, IOException {	
		driver.get(ConfigReader.getBaseUrl());
		pageNumberPage = PageFactory.initElements(driver, PageNumberPage.class);		
		pageNumberPage.GetHypoThyroidismPage(driver);	
	}		
	@Test
	public void testA10Hypertension() throws InterruptedException, IOException {	
		driver.get(ConfigReader.getBaseUrl());
		pageNumberPage = PageFactory.initElements(driver, PageNumberPage.class);		
		pageNumberPage.GetHypertensionPage(driver);	
	}		
	
	@Test
	public void testZA9999() throws InterruptedException, IOException {
		Quit_Driver(driver);      
	}

}
