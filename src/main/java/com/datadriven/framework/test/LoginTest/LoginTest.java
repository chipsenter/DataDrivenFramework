package com.datadriven.framework.test.LoginTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.datadriven.framework.base.BaseUI;

public class LoginTest extends BaseUI {

	@Test
	public void testOne() {
		
	    logger = report.createTest("Initializing the Browser");
		invokeBrowser("chrome");
		logger = report.createTest("Opening the Website");
		openURL("websiteURL");
		logger = report.createTest("Click Sign In Button");
		elementClick("signinBtn_Xpath");
		logger = report.createTest("Entering UserName And Password");
		enterText("usernameInput_Id", "test");
		enterText("passwordInput_Xpath", "Test123");	
		//logger.log(Status.FAIL, "Test Execution Failed ");
		//logger.log(Status.PASS, "Test Execution Failed ");
		//tearDown();
		
		takeScreenShotOnFailure();
	}

	@AfterTest
	public void endReport() {
	report.flush();

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