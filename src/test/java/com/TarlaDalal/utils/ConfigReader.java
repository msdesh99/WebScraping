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
    public static String[] getcellNames() {
    	return prop.getProperty("cellNames").split(",");		
	}
/*	public static Object getModuleUrl() {
		return prop.getProperty("moduleUrl");
	}
	public static Object getDropDown() {
		return prop.getProperty("dropdown");
	}
	public static void setProperties() {		
	}
	public static String getJsonFile() {
		return prop.getProperty("jsonpythonfile");		
	}
*/	
}

