package com.TarlaDalal.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.TarlaDalal.model.Recipes;


public class CreateTestData extends XLUtility{
	   static String[] currArr = new String[]{"USD","EUR","GBP","INR"};
	    static String[] statArr = new String[]{"Valid","Valid","Valid","InValid"};
	    static String[] resultArr = new String[]{"Passed","Passes","Passes","Failed"};


 	InputStream fi;
	OutputStream fout;
	XSSFRow row;
	XSSFCell cell;
	CellStyle style ;
	DataFormatter formatter;

    String path = null;
	File dataFile;
    XSSFWorkbook wkb;
	XSSFSheet sh;
	String sheet1;
	int totalCell;

	public CreateTestData(String path, String sheet) throws IOException {
		super(path,sheet);
		this.path = path;
		//this.path = System.getProperty("user.dir")+"/src/test/resources/TestData/currencyData.xlsx";
		 this.dataFile = new File(path);
		 this.sheet1 = sheet;
	}

	@Override
	public void WriteIntoFile(List<Recipes> detailArr) throws IOException{
		//inp = getClass().getResourceAsStream("TutorialNinjaData.xls");		
		 wkb = new XSSFWorkbook();
		 sh = wkb.createSheet(sheet1);
		 String[] cellName = new String []{"Recipe Name","Recipe Category","Food Category","Ingredients",
				 "Preparation Time","Cooking Time","Preparation method","Nutrient values","Morbid condition"};
		 Row r1 = sh.createRow(0);
		 //totalCell = cellName.length;
		 for(int i=0;i<cellName.length;i++)
		    r1.createCell(i).setCellValue(cellName[i]);
		 
		/* for(int i=1;i<=4;i++) {
			 Row rw = sh.createRow(i);
			 rw.createCell(0).setCellValue(currArr[i-1]);
			 rw.createCell(1).setCellValue(statArr[i-1]);
			// rw.createCell(2).setCellValue(resultArr[i-1]);
	         rw.createCell(2,CellType.BLANK);
		 } */
		try {
			 fout = new FileOutputStream(this.dataFile);
		     wkb.write(fout);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			wkb.close();
			fout.close();
		} 

	}
	public static void TestDataCurrency() {
			
	}
	
}