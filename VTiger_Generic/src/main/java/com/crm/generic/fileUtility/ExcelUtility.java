package com.crm.generic.fileUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getDataFromExcelFile(String sheetName, int rowNum, int colNum) throws Throwable, IOException {
		
		FileInputStream fis = new FileInputStream("./resources/testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cell = row.getCell(colNum);
		String value = cell.getStringCellValue();
		wb.close();
		return value;
		
	}
	
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./resources/testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int count = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return count;
	}
	
	public void writeDataIntoExcelFile(String sheetName, int rowNum, int colNum, String data) throws Throwable, IOException {
		
		FileInputStream fis = new FileInputStream("./resources/testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(colNum).setCellValue(data);
		
		FileOutputStream fos = new FileOutputStream("./resources/testdata.xlsx");
		wb.write(fos);
		wb.close();
	}
}
