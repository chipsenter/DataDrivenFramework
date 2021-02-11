package com.datadriven.framework.utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jdk.internal.misc.FileSystemOption;

public class TestDataProvider {

	@Test(dataProvider="getTestData")
	public void sampleTestOne(String col1, String col2, String col3, String col4){
		
	}
	
	/********************** Data for TestCase ***************************/
	
	@DataProvider
	public Object[][] getTestData() {
		
		ReadExcelDataFile readdata = new ReadExcelDataFile(System.getProperty("user.dir")+ "/src/main/java/testData/TestData_Testmanagement.xlsx");
		
		String sheetName = "Feature1";
		String testName = "Test Three";
		
		int startRowNum = 0;
		while(!readdata.getCellData(sheetName, 0, startRowNum).equalsIgnoreCase(testName)) {
			startRowNum++;
		}
		System.out.println("Test Start from Row Number (startRowNum) " + startRowNum);
		
		int startTestColumn = startRowNum + 1;
		int startTestRow = startRowNum + 2;
		
		//Find Number of Rows of TestCase
		int rows = 0;
		while(!readdata.getCellData(sheetName, 0, startTestRow + rows).equals("")) {
			rows++;
		}
		System.out.println("Total Number of Rows in Test : " + testName + " is (rows) " + rows );
		
		//Find Number of Columns of TestCase
		int columns = 0;
		while(!readdata.getCellData(sheetName, columns, startTestColumn).equals("")) {
			columns++;
		}
		System.out.println("Total Number of Columns in Test : " + testName + " is (columns) " + columns );
		
		//Define Two Object Array
		Object[][] dataSet = new Object[rows][columns];
		
		int dataRowNumber = 0;
		
		for (int rowNumber = startTestRow; rowNumber <= startRowNum + rows +1; rowNumber++) {
			System.out.println("Inner loop rowNumber is " + rowNumber);
			for (int colNumber = 0; colNumber < columns; colNumber++) {
				dataSet[dataRowNumber][colNumber] = readdata.getCellData(sheetName, colNumber, rowNumber);
			
			}
			dataRowNumber++;
		}
		return dataSet;
	}
}
