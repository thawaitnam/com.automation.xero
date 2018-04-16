package com.automation.xero.module;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.xero.base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.utils.FileUtil;

public class VerificationModule extends TestBase {

	/*
	 * Name of the Method: validateTextBoxContent
	 * Brief Description: Validate
	 * Text Box elements Arguments: obj--->WebElement Objects ,expectedText -->
	 * Expected text , objName---> name of object Created By : Automation team
	 * Creation date : Apr 14 2018 
	 * Last Modified Date: Apr 14 2018 
	 * Last Modified by: Namrata
	 */

	public static void validateTextBoxContent(WebElement obj, String expectedText, String objName) {
		if (obj.isDisplayed()) {
			String actualText = obj.getAttribute("value");
			// String actualText=obj.getText();

			// System.out.println("Actual Text is" +actualText);
			if (expectedText.equals(actualText)) {
				System.out.println("Pass: " + " Expected text '" + expectedText + "' is matching with actual text.");
			} else {
				System.out.println("Fail: " + " Expected text '" + expectedText + "' is not matching with actual text '"
						+ actualText + "'.");
			}

		} else {
			System.out.println("Fail :" + objName + " is not diplayed, please check your application");
		}
	}

	/*
	 * Name of the Method: validateTextMessage 
	 * Brief Description: Validate Text
	 * Message Arguments: obj--->WebElement Objects ,expectedText --> Expected text , objName---> name of object
	 * Created By : Automation team Creation
	 * Created date : Mar 10 2018 
	 * Last Modified Date: Apr 15 2018 
	 * Last Modified by:Namrata
	 * 
	 */

	public static void validateTextMessage(WebElement obj, String expectedText, String objName) {
		if (obj.isDisplayed()) {
			// String actualText = obj.getAttribute("value");
			String actualText = obj.getText();
			if (expectedText.equalsIgnoreCase(actualText)) {
				System.out.println("Pass: " + " Expected text '" + expectedText + "' is matching with actual text.");
			} else {
				System.out.println("Fail: " + " Expected text '" + expectedText + "' is not matching with actual text '"
						+ actualText + "'.");
			}

		} else {
			System.out.println("Fail :" + objName + " is not diplayed, please check your application");
		}
	}

	/*
	 * Name of the Method: validatePageTitle Brief Description: Validate Page
	 * title Arguments: obj--->Webdriver Objects ,expectedText --> Expected Page
	 * title , objName---> name of object 
	 * Created By : Automation team Creation
	 * date : Mar 10 2018 
	 * Last Modified Date: Apr 14 2018 
	 * Last Modified by:Namrata
	 * 
	 */

	public static void validatePageTitle(String expectedTitle, String objName,ExtentTest loggers) throws IOException {
		//extent = LogReport.createLogReport(extent);
		//loggers=LogReport.createLogReport(testcasename);
		String actualTitle = driver.getTitle();

		System.out.println("Actual Text is" + actualTitle);
		if (expectedTitle.equalsIgnoreCase(actualTitle)) {
			loggers.log(Status.INFO, "Pager verified");
			loggers.log(Status.PASS, MarkupHelper.createLabel(actualTitle, ExtentColor.GREEN));

			System.out.println(
					"Pass: " + " Expected page title '" + expectedTitle + "' is matching with actual page title.");
		} else {

			loggers.log(Status.FAIL, MarkupHelper.createLabel(actualTitle, ExtentColor.RED));
			loggers.log(Status.INFO, "Pager not verified");
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			//FileUtils.copyFile(src, new File("./Screenshots/error.jpg"));
			loggers.fail("Details",
					MediaEntityBuilder.createScreenCaptureFromPath(Paths.get("").toAbsolutePath().toString()
							+ "/Screenshots/error.png").build());

			System.out.println("Fail: " + " Expected page title '" + expectedTitle
					+ "' is not matching with actual page title '" + actualTitle + "'.");
		}

	}

}
