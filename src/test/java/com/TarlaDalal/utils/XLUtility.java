package com.TarlaDalal.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
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

public void WriteIntoFile(List<Recipes> detailArr) throws IOException{
	//System.out.println("data: "+this.dataFile);
	
	 wkb = new XSSFWorkbook();
	 sh = wkb.createSheet(this.sheet1);
	 
	 CellStyle cs = wkb.createCellStyle();
	 cs.setWrapText(true);
	 cs.setVerticalAlignment(VerticalAlignment.CENTER);

	 /*HSSFCellStyle verStyle = wkb.createCellStyle();
     verStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);

     XSSFCellStyle style = wkb.createCellStyle();
	 cs.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP);*/
	 
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
}   
public void WriteAllSheetsIntoFile(List<Recipes> detailArr) throws IOException{
	
	 wkb = new XSSFWorkbook();
	 CellStyle cs = wkb.createCellStyle();
	 cs.setWrapText(true);
	 cs.setVerticalAlignment(VerticalAlignment.TOP);
	 Row r1;
	 int rowCount =0;

	 String[] allSheets = ConfigReader.getSheetNames();
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
	}
	 for(Recipes recipe1: detailArr) {
         
		// String[] flag = recipe1.getFlag().split(",");
		 rowCount=1;
		 String sheetName="";
		 //for(String sheetName: flag) {
			 System.out.println("sheetName: "+ sheetName.substring(0, sheetName.indexOf(" ", sheetName.indexOf(" ")+1)));
			sh = wkb.getSheet(sheetName.substring(0, sheetName.indexOf(" ", sheetName.indexOf(" ")+1)));
		  //rowCount = GetLastRow(sheetName.substring(0, sheetName.indexOf(" ", sheetName.indexOf(" ")+1)))+1;
		  int cellCount=0;
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

		  //r1.createCell(cellCount).setCellValue(recipe1.getMorbid());
		  r1.createCell(cellCount).setCellValue(sheetName.substring(0, sheetName.indexOf(" ")));
		  cellCount++;

		  r1.createCell(cellCount).setCellValue(recipe1.getUrl());
		  cellCount++;


	   //  }
		 rowCount++;
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
}   

public void CreateNewCell(String sheetName, int rowNumber, String cellValue) throws IOException{
	try {
		fi = new FileInputStream(this.dataFile);
		wkb = new XSSFWorkbook(fi);	
		sh = wkb.getSheet(sheetName);
		
		Row row = sh.getRow(rowNumber);
		int lastCell = row.getLastCellNum();
		Cell cell = row.createCell(lastCell);
		cell.setCellValue(cellValue);    
		
		fout = new FileOutputStream(this.dataFile);
		wkb.write(fout);
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	finally {
		wkb.close();
		fout.close();
		fi.close();
	}

}	
public int GetLastRow(String sheetName) throws IOException {
	int lastRow=0;
    try {
		fi  = new FileInputStream(this.dataFile);
		wkb = new XSSFWorkbook(fi);
		sh = wkb.getSheet(sheetName);
		lastRow = sh.getLastRowNum();
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	finally {
		wkb.close();
		fi.close();
	}
	return lastRow;

}
public int GetLastCell(String sheetName, int row) throws IOException {
	   int lastCell=0;
	   try {
			fi  = new FileInputStream(this.dataFile);
			wkb = new XSSFWorkbook(fi);
			sh = wkb.getSheet(sheetName);
			Row rw = sh.getRow(row);
			lastCell = rw.getLastCellNum();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			wkb.close();
			fi.close();
		}
		return lastCell;
	
}

public void UpdateCellData(String sheetName, int row, int col, String cellValue) throws IOException{
	try {
		fi = new FileInputStream(this.dataFile);
		wkb = new XSSFWorkbook(fi);
		sh = wkb.getSheet(sheetName);
		Row rw = sh.getRow(row);

		if(rw.getCell(col) != null) {
		Cell cell = rw.getCell(col);  
		cell.setCellValue(cellValue);
		}
		else CreateNewCell(sheetName, row, "Failed-upd-Cre");
		
		fout = new FileOutputStream(this.dataFile); // 2 Variables are needed to close the output and input stream
		wkb.write(fout);
		//wkb.getSheet(sheetName).getRow(Row).getCell(col).setCellValue(cellValue);
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	finally {
		wkb.close();
		fout.close();
		fi.close();
	}
}	

public String GetCellData(String sheetName, int row, int col) throws IOException{	   
	//System.out.println("GEtCell: "+wkb.getSheet(sheetName).getRow(row).getCell(col).getStringCellValue());
	    try {
			fi = new FileInputStream(this.dataFile);
			wkb = new XSSFWorkbook(fi);
			sh = wkb.getSheet(sheetName);
		    Row rw = sh.getRow(row);
			formatter = new DataFormatter();
			if(rw.getCell(col) != null) {
				Cell ce = rw.getCell(col);
				return formatter.formatCellValue(ce); 
			}
			else  return null; //CreateCell("sheet1", row,"Failed-upd-Cre");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	    finally {
			wkb.close();
			fi.close();
		}
	}	
public void FillGreenColor(String sheetName, int row, int cell) throws IOException{
		try {
			  fi = new FileInputStream(this.dataFile);
			  wkb = new XSSFWorkbook(fi);
			  sh = wkb.getSheet(sheetName);
			  
			  Row rw = sh.getRow(row);
			  Cell ce = rw.getCell(cell);
			  
			  style = wkb.createCellStyle();
			  style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			  style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			  
			  ce.setCellStyle(style);
			  fout = new FileOutputStream(this.dataFile);
			  wkb.write(fout);
		} catch (FileNotFoundException e) {
					e.printStackTrace();
		}
		finally {
			fout.close();
			wkb.close();
			fi.close();
		}
			
	
}
 public void ReadFile(String sheetName) throws IOException {
	try {
            fi  = new FileInputStream(this.dataFile);
			wkb = new XSSFWorkbook(fi);
			sh = wkb.getSheet(sheetName);
		Iterator<Row> rw1 = sh.rowIterator();
		while(rw1.hasNext()) {
		     Row row1 = rw1.next();
		     Iterator<Cell> cell1 = row1.cellIterator();
		     System.out.println();
		     while(cell1.hasNext()) {
		    	 Cell cell2 = cell1.next();
		    	//System.out.println(cell2);
		    	System.out.printf("%s ",cell2);
		     }
		}
		/*
		Iterator<Row> rw = sh.rowIterator(); 
		while (rw.hasNext()) {
			Row row = rw.next();
			Iterator<Cell> cell = row.cellIterator();	
			while(cell.hasNext()) {
				System.out.printf("%s  \n",cell.next()); // ".getStringCellValue());
			}
		}*/		

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
    finally {
		wkb.close();
		fi.close();
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
    	
   /* 	xlUtility.CreateNewCell(sheet1, 3, "New");

    	int totalRows =xlUtility.GetLastRow(sheet1);
    	System.out.println("get totalrows: "+totalRows);
    		
    	int totalCells = xlUtility.GetLastCell(sheet1, 1);
    	System.out.println("get totalcell : "+totalCells);
  	
    	xlUtility.UpdateCellData(sheet1, 3, 3, "updated");
		
   
		System.out.println(xlUtility.GetCellData("sheet1", 1, 0));
    	
		xlUtility.FillGreenColor(sheet1, 3, 5);
		xlUtility.ReadFile(sheet1); */

    }
    
}
