package com.datadriven.framework.test.LoginTest;

import java.util.Hashtable;

import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.datadriven.framework.base.BaseUI;
import com.datadriven.framework.utils.TestDataProvider;

public class LoginTest extends BaseUI {

	@Test(dataProvider="getTestOneData")
	public void testOne(Hashtable<String, String> dataTable) {
		
	    logger = report.createTest("Enter UserName and Password in Rediff" + dataTable.get("col1"));
		invokeBrowser("chrome");
		openURL("websiteURL");
		elementClick("signinBtn_Xpath");
		enterText("usernameInput_Id", dataTable.get("Col2"));
		enterText("passwordInput_Xpath", dataTable.get("Col4"));	
		
		}

	@AfterTest
	public void endReport() {
	report.flush();

	}
	
	@DataProvider
	public Object[][] getTestOneData(){
		return TestDataProvider.getTestData("TestData_Testmanagement.xlsx", "Feature1", "Test Three");
	}
	

	//@Test
	public void testTwo() {
		logger = report.createTest("Open Rediff and Enter UserName");
		invokeBrowser("chrome");
		openURL("websiteURL");
		elementClick("signinBtn_Xpath");
		enterText("usernameInput_Id", "Test");
	}

	// @Test(dependsOnMethods="testTwo")
	public void testThree() {
		invokeBrowser("Mozila");
		openURL("websiteURL");
		elementClick("signinBtn_Xpath");
		enterText("usernameInput_Id", "Test");
		tearDown();

	}

}
