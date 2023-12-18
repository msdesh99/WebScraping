package com.TarlaDalal.allRecipes;

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
import com.TarlaDalal.utils.ConfigReader;



public class SearchAlpha {
	WebDriver driver;
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
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		 options.addArguments("--disable-gpu");
		 options.addArguments("--blink-settings=imagesEnabled=false");
		 
		driver = new ChromeDriver(options);
		
		configReader = new ConfigReader();
		prop = configReader.initializeProperties();	
		
		driver.get(ConfigReader.getHypertensionUrl());
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.manage().window().maximize();
		
	}

	@AfterTest
	public void tearDown() throws Exception {
	}

	@Test
	public void testA1() throws InterruptedException, IOException {		
		pageNumberPage = PageFactory.initElements(driver, PageNumberPage.class);		
		pageNumberPage.GetPage();
	}
	
}
