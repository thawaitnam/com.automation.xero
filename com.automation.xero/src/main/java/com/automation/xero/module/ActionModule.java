package com.automation.xero.module;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.xero.base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.utils.FileUtil;

//import com.aventstack.extentreports.MediaEntityBuilder;

public class ActionModule extends TestBase{
	public static ExtentReports extent;
	public static Properties myProperty;


	/*
	 * Name of the Method: enterText 
	 * Brief Description:Enter text to text boxes
	 * Arguments: obj --> Object, textval --> test value to be entered, objName --> Name of the object 
     * Created By: Automation team 
	 * Creation date: Mar 09
	 * 2018 Last Modified By: NAm 
	 * Last Modified Date: Apr 15 2018
	 */

	public static void enterText(WebElement obj, String textVal, String objName) {
		if (obj.isDisplayed()) {
			obj.clear();
			obj.sendKeys(textVal);
			System.out.println("Pass: " + textVal + " is entered in " + objName
					+ " field.");
		} else {
			System.out.println("Fail: " + objName
					+ " field does not exist please check your application.");
		}
	}

	/*
	 * Name of the Method: clickObj 
	 * Brief Description: Click on Object
	 * Arguments: obj--->Object, objName---> name of object 
	 * Created By : Nam
	 * Creation date : Mar 09 2018 
	 * Last Modified Date: Apr 09
	 * 2018 Last Modified by: Nam
	 */

	public static void clickObj(WebElement obj, String objName) {
		if (obj.isDisplayed()) {
			obj.click();
			System.out.println("Pass : " + objName + " is clicked " + objName);
		} else {
			System.out.println("Fail :" + objName
					+ " is not diplayed, please check your application");
		}
	}
	
	
	/*
	 * Name of the Method: selectDropDown Brief Description: Select Drop Down
	 * Arguments: obj---> select obj, String --> value to be selected from drop
	 * down, objName---> name of object 
	 * Created By : Automation team Creation
	 * date : Mar 09 2018 
	 * Last Modified Date: Mar 09 2018 
	 * Last Modified by:Namrata
	 * 
	 */

	public static void selectDropDown(WebElement obj, String selValue,
			String objName) {
		if (obj.isDisplayed()) {
			Select sel = new Select(obj);
			// sel.selectByValue("00B6A000005YLXN");
			sel.selectByVisibleText(selValue);
			// sel.selectByIndex(2);
			System.out.println("Pass : " + objName + " is selected " + objName);
		} else {
			System.out.println("Fail :" + objName
					+ " is not diplayed, please check your application");
		}
	}

}
