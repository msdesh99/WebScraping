/*Code details:
	#Author: Meenakshi Dated: 6-Nov-2023
*/
package com.TarlaDalal.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigReader {
    static Properties prop;

    public Properties initializeProperties() throws Exception {
    	prop = new Properties();
    	File file = new File("src/test/resources/config/config.properties");    	
    	FileInputStream fis;

    	try {
			fis = new FileInputStream(file);
			prop.load(fis); //persistent set of properties loading from fis into properties object
		} catch (FileNotFoundException e) {
			throw new Exception("Property file Not Found\n" + e);
		}
    	return prop;    	
    }
    public static String getBaseUrl() {
		return prop.getProperty("baseUrl");   	
    }
    
    public static String getDiabetesUrl() {
		return prop.getProperty("diabetesUrl");   	
    }
    public static String getDinnerUrl() {
		return prop.getProperty("dinnerUrl");   	
    }
    public static String geteggUrl() {
		return prop.getProperty("eggUrl");   	
    }
    
    public static String[] getcellNames() {
    	return prop.getProperty("cellNames").split(",");		
	}
	public static String[] geteliminateDiebetes() {
		return prop.getProperty("eliminateDiebetes").split(",");
	}
	public static String[] getAddDiabetes() {
		return prop.getProperty("addDiabetes").split(",");
	}
	public static String[] geteliminateHypothyroidism() {
		return prop.getProperty("eliminateHypothyroidism").split(",");
	}
	public static String[] getAddHypothyroidism() {
		return prop.getProperty("addHypothyroidism").split(",");
	}
	public static String[] geteliminateHypertension() {
		return prop.getProperty("eliminateHypertension").split(",");
	}
	public static String[] getAddHypertension() {
		return prop.getProperty("addHypertension").split(",");
	}
	public static String[] geteliminatePCOS() {
		return prop.getProperty("eliminatePCOS").split(",");
	}
	public static String[] getAddPCOS() {
		return prop.getProperty("addPCOS").split(",");
	}
	public static String[] getallergies() {
		return prop.getProperty("allergies").split(",");
	}
	public static String[] getSheetNames() {
		return prop.getProperty("sheets").split(",");
	}
	public static String[] getAllSheets() {
		return prop.getProperty("allsheets").split(",");
	}
	public static String getAllRecipeUrl() {
		return prop.getProperty("allRecipeUrl");   	
	}
	
	public static String[] getFoodCategory() {
		return prop.getProperty("foodcategory").split(",");
	}
	public static String[] getRecipeCategory() {
		return prop.getProperty("recipecategory").split(",");
	}
	public static String getPCOSUrl() {
		return prop.getProperty("pcosUrl");   	
	}

	
}

