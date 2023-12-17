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
import com.TarlaDalal.utils.ConfigReader;



public class SearchAlpha {
	protected WebDriver driver;
	Properties prop;
	ConfigReader configReader;
	HomePage homePage;
    PageNumberPage pageNumberPage;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@BeforeTest
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		//driver = new EdgeDriver();
		//driver = new FirefoxDriver();
		configReader = new ConfigReader();
		prop = configReader.initializeProperties();	
		//driver.get(ConfigReader.getBaseUrl());		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.manage().window().maximize();
	}

	@AfterTest
	public void tearDown() throws Exception {
	}

	//@Test
	public void testDiabetes() throws InterruptedException, IOException {
		driver.get(ConfigReader.getDiabetesUrl());
		pageNumberPage = PageFactory.initElements(driver, PageNumberPage.class);		
		pageNumberPage.GetPage();
	}
	
	@Test
	public void testHypoThyroidism() throws InterruptedException, IOException {
		driver.get(ConfigReader.getAllRecipeUrl());
		pageNumberPage = PageFactory.initElements(driver, PageNumberPage.class);		
		pageNumberPage.GetAllRecipePage();
	}
	
}
