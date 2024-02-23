package com.TarlaDalal.Runner;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

public class SearchRecipe extends AllActions {
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
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--disable-popup-blocking");
		chromeOptions.addArguments("--disable-notifications");
		chromeOptions.addArguments("--disable-extensions");
		chromeOptions.addArguments("--blink-settings-imageEnabled=false");
	
		driver = new ChromeDriver(chromeOptions);
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
		Quit_Driver(driver);      
	}

	@Test
	public void testAADiabetes() throws InterruptedException, IOException {
		driver.get(ConfigReader.getDiabetesUrl());
		pageNumberPage = PageFactory.initElements(driver, PageNumberPage.class);
		pageNumberPage.GetDiabetesPage();
	} 
	@Test
	public void testAAZRecipes() throws InterruptedException, IOException {	
		driver.get(ConfigReader.getAllRecipeUrl());
		pageNumberPage = PageFactory.initElements(driver, PageNumberPage.class);		
		pageNumberPage.GetAZRecipePage();
	}
	
/*	@Test
	public void testAAPCOS() throws InterruptedException, IOException {	
		driver.get(ConfigReader.getBaseUrl());
		pageNumberPage = PageFactory.initElements(driver, PageNumberPage.class);		
		pageNumberPage.GetPCOSPage(driver);
	}		
	@Test
	public void testAAHypoThyroidism() throws InterruptedException, IOException {	
		driver.get(ConfigReader.getBaseUrl());
		pageNumberPage = PageFactory.initElements(driver, PageNumberPage.class);		
		pageNumberPage.GetHypoThyroidismPage(driver);	
	}		
	@Test
	public void testAAHypertension() throws InterruptedException, IOException {	
		driver.get(ConfigReader.getBaseUrl());
		pageNumberPage = PageFactory.initElements(driver, PageNumberPage.class);		
		pageNumberPage.GetHypertensionPage(driver);	
	}		
	

	@Test
	public void testAABreakfast() throws InterruptedException, IOException {
		driver.get(ConfigReader.getBaseUrl());
		recipeCategoryPage = PageFactory.initElements(driver, RecipeCategoryPage.class);
		recipeCategoryPage.GetCategory("Breakfast");
	}
  @Test
	public void testAALunch() throws InterruptedException, IOException {
		driver.get(ConfigReader.getBaseUrl());
		recipeCategoryPage = PageFactory.initElements(driver, RecipeCategoryPage.class);
		recipeCategoryPage.GetCategory("Lunch");
	} 
	@Test
	public void testAASnacks() throws InterruptedException, IOException {
		driver.get(ConfigReader.getBaseUrl());
		recipeCategoryPage = PageFactory.initElements(driver, RecipeCategoryPage.class);
		recipeCategoryPage.GetCategory("snack");
	} 
	@Test
	public void testAADinner() throws InterruptedException, IOException {
		  driver.get(ConfigReader.getDinnerUrl());
	      homePage = PageFactory.initElements(driver,HomePage.class);
	      homePage.GetRecipe(driver.getCurrentUrl());
	} 
	@Test
	public void testAAEggRecipes() throws InterruptedException, IOException {
		  driver.get(ConfigReader.geteggUrl());
	      homePage = PageFactory.initElements(driver,HomePage.class);
	      homePage.GetRecipe(driver.getCurrentUrl());
	} 
	
*/
}
