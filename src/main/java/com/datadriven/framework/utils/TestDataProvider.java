package com.datadriven.framework.utils;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import jdk.internal.misc.FileSystemOption;

public class TestDataProvider {

	/*
	@Test(dataProvider="getTestData")
	public void sampleTestOne(Hashtable<String, String> table){
		
		System.out.println(table.get("col3"));
	}
	*/
	
	/********************** Data for TestCase ***************************/
	

	public static Object[][] getTestData(String DataFileName, String SheetName, String TestName) {
		
		ReadExcelDataFile readdata = new ReadExcelDataFile(System.getProperty("user.dir") + "/src/main/java/testData/" + DataFileName );
		
		String sheetName = SheetName;
		String testName = TestName;
		
		int startRowNum = 0;
		while(!readdata.getCellData(sheetName, 0, startRowNum).equalsIgnoreCase(testName)) {
			startRowNum++;
		}
		//System.out.println("Test Start from Row Number (startRowNum) " + startRowNum);
		
		int startTestColumn = startRowNum + 1;
		int startTestRow = startRowNum + 2;
		
		//Find Number of Rows of TestCase
		int rows = 0;
		while(!readdata.getCellData(sheetName, 0, startTestRow + rows).equals("")) {
			rows++;
		}
		//System.out.println("Total Number of Rows in Test : " + testName + " is (rows) " + rows );
		
		//Find Number of Columns of TestCase
		int columns = 0;
		while(!readdata.getCellData(sheetName, columns, startTestColumn).equals("")) {
			columns++;
		}
				
		//Define Two Object Array
		Object[][] dataSet = new Object[rows][1];
		Hashtable<String, String> dataTable = null;
		int dataRowNumber = 0;
		
		for (int rowNumber = startTestRow; rowNumber <= startRowNum + rows +1; rowNumber++) {
			dataTable = new Hashtable<String, String>();
			//System.out.println("Inner loop rowNumber is " + rowNumber);
			for (int colNumber = 0; colNumber < columns; colNumber++) {
				String key = readdata.getCellData(sheetName, colNumber, startTestColumn);
				String value = readdata.getCellData(sheetName, colNumber, rowNumber);
				dataTable.put(key, value);
				//dataSet[dataRowNumber][colNumber] = readdata.getCellData(sheetName, colNumber, rowNumber);
			
			}
			dataSet[dataRowNumber][0] = dataTable;
			dataRowNumber++;
		}
		return dataSet;
	}
}
