package com.automation.xero.module;

import com.automation.xero.base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class LogReport extends TestBase {

	/*
	 * Name of the Method: startReport 
	 * Brief Description:Create external report
	 * Arguments: reportPath --> location of report
     * Created By: Nam
	 * Creation date: Apr 15
	 * Last Modified Date: Apr 15 2018
	 */
	
	public static ExtentReports startReport(String reportPath){
		System.out.println(reportPath);
		htmlReporter = new ExtentHtmlReporter(reportPath);
		extent = new ExtentReports ();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("Host Name", "NaM");
		extent.setSystemInfo("User Name", "Namrata");

		htmlReporter.config().setDocumentTitle("report status");
		htmlReporter.config().setReportName("customized report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		return extent;
	}

	public static ExtentTest createTestReport(String testName,ExtentReports extent){
		loggers = extent.createTest(testName);
		return loggers;
	}
	public static void endReport(ExtentReports extent){
		extent.flush();
	}

}
