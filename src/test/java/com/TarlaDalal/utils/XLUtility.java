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
	CellStyle style;
	DataFormatter formatter;

	String path = null;
	File dataFile;
	XSSFWorkbook wkb;
	XSSFSheet sh;
	String sheet1;

	public XLUtility(String path, String sheet) throws IOException {
		super();
		this.path = path;
		// this.path =
		// System.getProperty("user.dir")+"/src/test/resources/Lists/ListOfRecipes.xlsx";
		// this.path = "/src/test/resources/TestData/currencyData.xlsx";
		this.dataFile = new File(this.path);
		this.sheet1 = sheet;
	}

	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		wkb = new XSSFWorkbook(fi);
		sh = wkb.getSheet(sheetName);
		int rowcount = sh.getLastRowNum();
		Row r1 = sh.createRow(rowcount + 1);
		wkb.close();
		fi.close();
		return rowcount + 1;
	}

	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		wkb = new XSSFWorkbook(fi);
		sh = wkb.getSheet(sheetName);
		row = sh.getRow(rownum);
		cell = row.getCell(colnum);

		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);// Returns the formatted value of regardless of the cell type
		} catch (Exception e) {
			data = "";
		}
		wkb.close();
		fi.close();
		return data;
	}

	public void WriteAllSheetsHeading() throws IOException {
		wkb = new XSSFWorkbook();
		CellStyle cs = wkb.createCellStyle();
		cs.setWrapText(true);
		cs.setVerticalAlignment(VerticalAlignment.TOP);
		Row r1;
		int rowCount = 0;
		String[] allSheets = ConfigReader.getAllSheets();
		for (String sheet1 : allSheets) {
			sh = wkb.createSheet(sheet1);
			rowCount = 0;
			String[] cellName = ConfigReader.getcellNames();
			r1 = sh.createRow(rowCount);
			rowCount++;
			for (int i = 0; i < cellName.length; i++) {
				Cell cell = r1.createCell(i);
				// cell.setCellStyle(cs);
				cell.setCellValue(cellName[i]);
			}
		}
		try {
			fout = new FileOutputStream(this.dataFile);
			wkb.write(fout);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			wkb.close();
			fout.close();
		}

	}

	public void CreateNewCell(String sheetName, int rowNumber, String cellValue) throws IOException {
		try {
			if (cellValue.contentEquals("CloseFile")) {
				wkb.close();
				fout.close();
				fi.close();
			} else {
				fi = new FileInputStream(this.dataFile);
				wkb = new XSSFWorkbook(fi);
				sh = wkb.getSheet(sheetName);
				if (sh.getRow(rowNumber) == null)
					row = sh.createRow(rowNumber);
				row = sh.getRow(rowNumber);
				int lastCell = row.getLastCellNum();
				if (lastCell < 0)
					lastCell = 0;
				Cell cell = row.createCell(lastCell);
				cell.setCellValue(cellValue);

				fout = new FileOutputStream(this.dataFile);
				wkb.write(fout);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// wkb.close();
			// fout.close();
			// fi.close();
		}

	}

	public static void main(String[] args) throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/resources/Lists/ListOfRecipes.xlsx";
		// String path = "/src/test/resources/Lists/ListOfRecipes.xlsx";
		// File newFile = new File(path);

		String sheet1 = "EliminatedIngredients";
		System.out.println("path: " + path);
		XLUtility xlUtility = new XLUtility(path, sheet1);
		// xlUtility.WriteIntoFile();

	}

}
