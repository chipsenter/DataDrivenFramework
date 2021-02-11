package com.datadriven.framework.utils;


/*
 * 
 * This class is not going to be used in Framework!
 * 
 * 
 */


public class ReadTestData {

	public static void main(String args[]) {
		ReadExcelDataFile readdata = new ReadExcelDataFile(System.getProperty("user.dir")+ "/src/main/java/testData/TestData_Testmanagement.xlsx");
	
		String sheetName = "Feature2";
		String testName = "Test Three";
		
		int startRowNum = 1;
		while(!readdata.getCellData(sheetName, 0, startRowNum).equalsIgnoreCase(testName)) {
			startRowNum++;
		}
		System.out.println("Test Start from Row Number "+ startRowNum);
		
		int startTestColumn = startRowNum+1;
		int startTestRow = startRowNum+2;
		
		//Find Number of Rows of TestCase
		int rows = 0;
		while(!readdata.getCellData(sheetName, 0, startTestRow+rows).equals("")) {
			rows++;
		}
		System.out.println("Total Number of Rows in Test : " + testName + " is " + rows );
		
		//Find Number of Columns of TestCase
		int columns = 0;
		while(!readdata.getCellData(sheetName, columns, startTestColumn).equals("")) {
			columns++;
		}
		System.out.println("Total Number of Columns in Test : " + testName + " is - " + columns );
		
		for (int rowNumber=startTestRow; rowNumber < startRowNum+rows; rowNumber++) {
			for (int colNumber=0; colNumber<columns; colNumber++) {
				System.out.println(readdata.getCellData(sheetName, colNumber, rowNumber));
			}
			
		}
	}
}
