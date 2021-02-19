package com.datadriven.framework.test.LoginTest;

import org.testng.annotations.Test;

import com.datadriven.framework.base.BaseUI;

public class ZohoLoginTest extends BaseUI {

	@Test
	public void doZohoLoginTest() {
		
		logger = report.createTest("Zoho Login Test Case");
		invokeBrowser("Chrome");
		openURL("websiteURL");
		elementClick("zohoLoginTestBtn_Classname");
		enterText("zohoUserNameInput_Id", "USER_ID");
		enterText("zohoPasswordInput_Id", "PASS");
		elementClick("zohoSignInBtn_Id");
		
	}
}
