package com.datadriven.framework.test.LoginTest;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.datadriven.framework.base.BaseUI;
import com.datadriven.framework.utils.TestDataProvider;

public class ZohoLoginTest extends BaseUI {

	@Test(dataProvider="getDatadoZohoLoginTest")
	public void doZohoLoginTest(Hashtable<String, String> dataTable) {
		
		logger = report.createTest("Zoho Login Test Case : " + dataTable.get("Comment"));
		invokeBrowser("Chrome");
		openURL("websiteURL");
		elementClick("zohoLoginTestBtn_Classname");
		enterText("zohoUserNameInput_Id", dataTable.get("UserName"));
		elementClick("zohoNextBtn_Id");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			System.out.println("Cant Find Element");
			e.printStackTrace();
		}
		enterText("zohoPasswordInput_Id", dataTable.get("Password"));
		elementClick("zohoNextBtn_Id");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		verifyPageTitle(dataTable.get("PageTitle"));
		
	}
	
	@DataProvider
	public Object[][] getDatadoZohoLoginTest(){
		
		return TestDataProvider.getTestData("zohoTestData.xlsx", "LoginTest", "doZohoLoginTest");
	}
}
