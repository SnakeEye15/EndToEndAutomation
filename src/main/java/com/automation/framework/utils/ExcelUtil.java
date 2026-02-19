package com.automation.framework.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	//Read the first column for mentioned sheet from 
	//excel sheet where we store our test data
	//for getting data for station only
	
	public static List<String> readColumn(String filePath, String sheetName){
		List<String> data= new ArrayList<>();
		
		// try-with-resources ensures file automatically close(to prevents memory leak)
		try(FileInputStream fis=new FileInputStream(filePath);
				Workbook  workbook=new XSSFWorkbook(fis);){
			 Sheet sheet= workbook.getSheet(sheetName);
			 
			 //Iterate each row instead of fixed range → supports dynamic data size
			 
			 for(Row row: sheet) {
				 	Cell cell=row.getCell(0);
				 	// Ignore blank rows to avoid null comparison issues
	                if (cell != null) {
	                    data.add(cell.toString().trim());
	                }
	                
			 }
			 
		}catch(Exception e) {
			// Fail fast — if test data cannot be read test should stop immediately
            throw new RuntimeException("Failed to read excel file: " + filePath);
		}
		return data;
	}
	
	//creating method to read the userName and password field from excel 
	
	public static List<List<String>> readSheet(String path, String sheetName){
		List<List<String>> data= new ArrayList<>();
		
		try(FileInputStream fis= new FileInputStream(path);
				Workbook workbook= new XSSFWorkbook(fis)){
			
			Sheet sheet= workbook.getSheet(sheetName);
			
			//for iterating throw sheet to get required data
			for(Row row: sheet) {
				List<String> rowData=new ArrayList<>();
				
				for(Cell cell: row) {
			        rowData.add(cell.getStringCellValue().trim());
				}
				data.add(rowData);
			}
			
		}catch(Exception e) {
			throw new RuntimeException("Failed to read excel sheet: " + path);
		}
		
		return data;
	}
	
	
	
	
	//Store list of values in new sheet
	//help to store the values of dropdown
	
	public static void writeColumn(String filePath, String sheetName, List<String> values) {
		try(Workbook workbook= new XSSFWorkbook()){
			Sheet sheet= workbook.createSheet(sheetName);
			
			//Create row per value dynamically 
			for(int i=0; i<values.size();i++) {
				Row row=sheet.createRow(i);
				Cell cell=row.createCell(0);
				
				//Store value
				cell.setCellValue(values.get(i));
			}
			
			//write the file to system
			FileOutputStream fos= new FileOutputStream(filePath);
			workbook.write(fos);
			fos.close();
		}catch(Exception e) {
			throw new RuntimeException("Failed to write excel file: " + filePath);
		}
	}
	 /***
     Compares expected vs actual list.
     Instead of strict equality we verify business validation:
     "All expected stations must exist in UI results"
     Returns list of missing stations for reporting.
     ***/
	
	public static List<String> compareList(List<String> expected, List<String> actual){
		
		List<String> missing = new ArrayList<>();
		
		for(String exp : expected) {
			if(!actual.contains(exp)) {
				missing.add(exp);
			}
		}
		return missing;
	}
	
	
	
}
