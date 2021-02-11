package com.datadriven.framework.utils;

public class tempreaddata {
	
	public static void main(String args[]) {
		
		ReadExcelDataFile readData = new ReadExcelDataFile(System.getProperty("user.dir") + "/src/main/java/testData/TestData.xlsx");
		
		int totalRows = readData.getRowCount("sampleSheet");
		System.out.println("Total Number of Rows Are : " + totalRows);
		
		System.out.println(readData.getCellData("SampleSheet", 0, 3));
		System.out.println(readData.getCellData("SampleSheet", 1, 4));
		
		System.out.println(readData.getColumnCount("SampleSheet"));
	}

}
