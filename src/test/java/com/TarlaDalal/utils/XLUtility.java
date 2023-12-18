package com.TarlaDalal.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.TarlaDalal.model.Recipes;

public class XLUtility {
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

	public XLUtility(String path, String sheet) throws IOException {
			super();
			this.path = path;
			//this.path = System.getProperty("user.dir")+"/src/test/resources/Lists/ListOfRecipes.xlsx";
            //this.path = "/src/test/resources/TestData/currencyData.xlsx";
			 this.dataFile = new File(this.path);
			 this.sheet1 = sheet;
		}
	 public int getRowCount(String sheetName) throws IOException
	 {
	  fi = new FileInputStream(path);
	  wkb = new XSSFWorkbook(fi);
	  sh = wkb.getSheet(sheetName);
	  int rowcount=sh.getLastRowNum();
	  wkb.close();
	  fi.close();
	  return rowcount;
	 }
	  public String getCellData(String sheetName,int rownum,int colnum) throws IOException
	   {
	    fi = new FileInputStream(path);
	    wkb = new XSSFWorkbook(fi);
	    sh = wkb.getSheet(sheetName);
	    row= sh.getRow(rownum);
	    cell=row.getCell(colnum);
	    
	    DataFormatter formatter=new DataFormatter();
	    String data;
	    try {
	    data=formatter.formatCellValue(cell);//Returns the formatted value of regardless of the cell type
	   }
	    catch(Exception e)
	    {
	     data="";
	    }
	    wkb.close();
	    fi.close();
	    return data;
	   }
	/*
public void WriteIntoFile(ArrayList<Recipes> detailArr) throws IOException{
	//System.out.println("data: "+this.dataFile);
	// List<Recipes> filteredArr = null;
	 wkb = new XSSFWorkbook();
	 sh = wkb.createSheet(this.sheet1);
	 
	 CellStyle cs = wkb.createCellStyle();
	 cs.setWrapText(true);
	 cs.setVerticalAlignment(VerticalAlignment.CENTER);


	 Row r1;
	 int rowCount =0;
	 String[] cellName = ConfigReader.getcellNames();
	 r1 = sh.createRow(rowCount);
	 rowCount++;
	 for(int i=0;i<cellName.length;i++) {
		  Cell cell = r1.createCell(i);
		 // cell.setCellStyle(cs);
		  cell.setCellValue(cellName[i]);
		 //r1.createCell(i).setCellValue(cellName[i])
	 }
	for(Recipes recipe1: detailArr) {
		 int cellCount=0;
     	//System.out.println("name: "+recipe1.getRecipeName());
     	//System.out.println("id: "+recipe1.getRecipeID());  
		  r1 = sh.createRow(rowCount);
		  rowCount++;
		  r1.createCell(cellCount).setCellValue(recipe1.getRecipeID());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getRecipeName());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getRecipeCategory());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getFoodCategory());
		  cellCount++;
		  
		  Cell cell =r1.createCell(cellCount);
		  cell.setCellValue(recipe1.getIngredients());
		 // cell.setCellStyle(cs);
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getPrepTime());
		  cellCount++;

		  r1.createCell(cellCount).setCellValue(recipe1.getCookTime());
		  cellCount++;

		  Cell cellMethod = r1.createCell(cellCount);
          cellMethod.setCellValue(recipe1.getMethod());
		  //cellMethod.setCellStyle(cs);

		  cellCount++;
		  
		  Cell cellNutrient = r1.createCell(cellCount);
		  cellNutrient.setCellValue(recipe1.getNutrient());
		 // cellNutrient.setCellStyle(cs);

		  cellCount++;

		  r1.createCell(cellCount).setCellValue(recipe1.getMorbid());
		  cellCount++;

		  r1.createCell(cellCount).setCellValue(recipe1.getUrl());
		  cellCount++;

		  r1.createCell(cellCount).setCellValue(recipe1.getRecipeCategoryBySearch());
		  cellCount++;

		  r1.createCell(cellCount).setCellValue(recipe1.getFoodCategoryBySearch());
		  cellCount++;

		  r1.createCell(cellCount).setCellValue(recipe1.getFlag());
		  cellCount++;


		  r1.createCell(cellCount).setCellValue(recipe1.getDiabetes_Eliminated());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getDiabetes_Add());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getHypothyroidism_Eliminated());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getHypothyroidism_Add());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getHypertension_Eliminated());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getHypertension_Add());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getPCOS_Eliminated());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getPCOS_Add());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getAllergies());
	     }
	 
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
	
		//need to override method to write data. in CreateTestData file
}    */
	
public void WriteAllSheetsIntoFile(List<Recipes> detailArr) throws IOException{
	 List<Recipes> filteredArr = null;
	 wkb = new XSSFWorkbook();
	 CellStyle cs = wkb.createCellStyle();
	 cs.setWrapText(true);
	 cs.setVerticalAlignment(VerticalAlignment.TOP);
	 Row r1;
	 int rowCount =0;

	// String[] allSheets = ConfigReader.getSheetNames();
	 String[] allSheets = ConfigReader.getAllSheets();

	for(String sheet1: allSheets) {
	  sh = wkb.createSheet(sheet1);
	 rowCount =0;
	 String[] cellName = ConfigReader.getcellNames();
	 r1 = sh.createRow(rowCount);
	 rowCount++;
	 for(int i=0;i<cellName.length;i++) {
		  Cell cell = r1.createCell(i);
		 // cell.setCellStyle(cs);
		  cell.setCellValue(cellName[i]);
	 }

	 if(sheet1.contentEquals("OnlyAdd")) {
		 filteredArr = detailArr.stream()
				  .filter(a -> a.getFlag()== "Add")
				  .collect(Collectors.toList());
	 }
	 if(sheet1.contentEquals("Allergies")) {
		 filteredArr = detailArr.stream()
				  .filter(a -> a.getFlag()== "Allergies")
				  .collect(Collectors.toList());
	 }
	 if(sheet1.contentEquals("Eliminated")) {
		 filteredArr = detailArr.stream()
			       .filter(a -> a.getDiabetes_Eliminated()== "Yes"
			                 || a.getHypertension_Eliminated()== "Yes"
			                 || a.getHypothyroidism_Eliminated() == "Yes")
				  .collect(Collectors.toList());
	 }
	 if(sheet1.contentEquals("All")) {
		 filteredArr = detailArr.stream()
				  .collect(Collectors.toList());
	 }
if(filteredArr!= null) {
	// for(Recipes recipe1: detailArr) {   
     for(Recipes recipe1: filteredArr) {        	 
		  int cellCount=0;
		  r1 = sh.createRow(rowCount);
		  rowCount++;
		  r1.createCell(cellCount).setCellValue(recipe1.getRecipeID());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getRecipeName());
		  cellCount++;
	
		  r1.createCell(cellCount).setCellValue(recipe1.getRecipeCategory());
		  cellCount++;

		  r1.createCell(cellCount).setCellValue(recipe1.getFoodCategory() );
		  cellCount++;
		  
		  Cell cell =r1.createCell(cellCount);
		  cell.setCellValue(recipe1.getIngredients());
		 // cell.setCellStyle(cs);
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getPrepTime());
		  cellCount++;

		  r1.createCell(cellCount).setCellValue(recipe1.getCookTime());
		  cellCount++;

		  Cell cellMethod = r1.createCell(cellCount);
          cellMethod.setCellValue(recipe1.getMethod());
		  //cellMethod.setCellStyle(cs);

		  cellCount++;
		  
		  Cell cellNutrient = r1.createCell(cellCount);
		  cellNutrient.setCellValue(recipe1.getNutrient());
		 // cellNutrient.setCellStyle(cs);

		  cellCount++;

		  r1.createCell(cellCount).setCellValue(recipe1.getMorbid());
		  cellCount++;

		  r1.createCell(cellCount).setCellValue(recipe1.getUrl());
		  cellCount++;
		 
	
		  r1.createCell(cellCount).setCellValue(recipe1.getFlag());
		  cellCount++;


		  r1.createCell(cellCount).setCellValue(recipe1.getDiabetes_Eliminated());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getDiabetes_Add());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getHypothyroidism_Eliminated());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getHypothyroidism_Add());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getHypertension_Eliminated());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getHypertension_Add());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getPCOS_Eliminated());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getPCOS_Add());
		  cellCount++;
		  
		  r1.createCell(cellCount).setCellValue(recipe1.getAllergies());

	 }
}
	}
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

    public static void main(String[] args) throws IOException {
		String path = System.getProperty("user.dir")+"/src/test/resources/Lists/ListOfRecipes.xlsx";
		//String path = "/src/test/resources/Lists/ListOfRecipes.xlsx";
		//File newFile = new File(path);	

        String sheet1 = "EliminatedIngredients";
        System.out.println("path: "+ path);
    	XLUtility xlUtility = new XLUtility(path, sheet1);
    	//xlUtility.WriteIntoFile();    	
    	
 
    }
    
}
