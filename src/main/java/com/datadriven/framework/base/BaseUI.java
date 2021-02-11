package com.datadriven.framework.base;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.datadriven.framework.utils.DateUtils;
import com.datadriven.framework.utils.ExtentReportManager;


public class BaseUI  {

	public WebDriver driver;
	public Properties prop;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;

	SoftAssert softAssert = new SoftAssert();

	/****************** Invoke Browser ***********************/
	public void invokeBrowser(String browserName) {

		try {
			if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver88");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("Mozila")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/src/test/resources/drivers/geckodriver");
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("Opera")) {
				System.setProperty("webdriver.opera.driver",
						System.getProperty("user.dir") + "/src/test/resources/drivers/operadriver");
				driver = new OperaDriver();
			} else if (browserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "/src/test/resources/drivers/IEDriverServer.exe");
				driver = new OperaDriver();
			} else {
				driver = new SafariDriver();
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// System.out.println(System.getProperty("user.dir"));

		if (prop == null) {
			prop = new Properties();

			// Mac
			try {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir")
						+ "/src/test/resources/ObjectRepository/projectConfig.properties");
				prop.load(file);
			} catch (Exception e) {
				reportFail(e.getMessage());
				e.printStackTrace();
			}

			// Windows
			// FileInputStream file = new
			// FileInputStream(System.getProperty("user.dir")+"//src/test//resources//ObjectRepository//projectConfig.properties");
		}

	}

	/****************** Open URL ***********************/
	public void openURL(String websiteURLKey) {
		try {
		driver.get(prop.getProperty(websiteURLKey));
		reportPass(websiteURLKey + " Identified Succesfully");
		}catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/****************** Close Browser ***********************/
	public void tearDown() {
		driver.close();

	}

	/****************** Quit Browser ***********************/
	public void quitBrowser() {
		driver.quit();

	}

	/****************** Enter Text ***********************/
	public void enterText(String xpathKey, String data) {
		try {
		getElement(xpathKey).sendKeys(data);
		reportPass(data + "Enetered Text Successfully Into Element" + xpathKey);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/****************** Click Element ***********************/
	public void elementClick(String xpathKey) {
		try {
		getElement(xpathKey).click();
		reportPass(xpathKey + " Element Clicked Successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	/****************** Select Elements ***********************/
	public WebElement getElement(String locatorKey) {
		WebElement element = null;
		
		try {
		if(locatorKey.endsWith("_Id")) {
			element = driver.findElement(By.id(prop.getProperty(locatorKey)));
			logger.log(Status.INFO, "Locator Indetified : " + locatorKey);
		} else if(locatorKey.endsWith("_Xpath")) {
			element = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
			logger.log(Status.INFO, "Locator Indetified : " + locatorKey);
		} else if(locatorKey.endsWith("_Css")) {
			element = driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
			logger.log(Status.INFO, "Locator Indetified : " + locatorKey);
		} else if(locatorKey.endsWith("_LinkText")) {
			element = driver.findElement(By.linkText(prop.getProperty(locatorKey)));
			logger.log(Status.INFO, "Locator Indetified : " + locatorKey);
		} else if(locatorKey.endsWith("_PartialLinkText")) {
			element = driver.findElement(By.partialLinkText(prop.getProperty(locatorKey)));
			logger.log(Status.INFO, "Locator Indetified : " + locatorKey);
		} else if(locatorKey.endsWith("_Name")) {
			element = driver.findElement(By.name(prop.getProperty(locatorKey)));
			logger.log(Status.INFO, "Locator Indetified : " + locatorKey);
		} else if(locatorKey.endsWith("_TagName")) {
			element = driver.findElement(By.tagName(prop.getProperty(locatorKey)));
			logger.log(Status.INFO, "Locator Indetified : " + locatorKey);
		} else {
			reportFail("Failing the Testcase, Invalid Locator " + locatorKey);
			Assert.fail("Failing the Testcase, Invalid Locator " + locatorKey);
		}
		} catch (Exception e) {
			
			//Fail the TestCase and Report the error
			reportFail(e.getMessage());
			e.printStackTrace();
			
			Assert.fail("Failing the TestCase : " + e.getMessage());
		}
		return element;
	}
	
	/****************** Verify Element ***********************/
	
	public boolean isElementPresent(String locatorKey) {
		
		try {
			if(getElement(locatorKey).isDisplayed()){
				reportPass(locatorKey + " : Element Is Displayed");
				return true;
			}
			
		}catch (Exception e) {
			reportFail(e.getMessage());
		}
		
		return false;		
	}
	
	public boolean isElementSelected(String locatorKey) {
		
		try {
			if(getElement(locatorKey).isSelected()){
				reportPass(locatorKey + " : Element Is Selected");
				return true;
			}
			
		}catch (Exception e) {
			reportFail(e.getMessage());
		}
		
		return false;		
	}
	
	public boolean isElementEnabled(String locatorKey) {
		
		try {
			if(getElement(locatorKey).isEnabled()){
				reportPass(locatorKey + " : Element Is Enabled");
				return true;
			}
			
		}catch (Exception e) {
			reportFail(e.getMessage());
		}
		
		return false;		
	}
	
	/****************** Assertion Functions ***********************/
	
	public void assertTrue(boolean flag) {
	
		softAssert.assertTrue(flag);
	
	}
	
	public void assertFalse(boolean flag) {
		
		softAssert.assertFalse(flag);
	
	}
	
	public void assertEquals(String actual, String expected) {
		
		softAssert.assertEquals(actual, expected);
	
	}
	
	
	/****************** Report Functions ***********************/
	
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShotOnFailure();
		Assert.fail(reportString);
	}
	
	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}
	
	@AfterMethod
	public void afterTest() {
		softAssert.assertAll();
		driver.quit();
	}
	
	public void takeScreenShotOnFailure() {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		
		File destFile = new File(System.getProperty("user.dir") + "/ScreenShots" + DateUtils.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(System.getProperty("user.dir") + "/ScreenShots" + DateUtils.getTimeStamp() + ".png");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
