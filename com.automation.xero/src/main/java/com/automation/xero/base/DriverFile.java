package com.automation.xero.base;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.automation.xero.module.LogReport;
import com.automation.xero.module.TestUtil;
import com.automation.xero.testcases.XeroTest;

public class DriverFile extends TestBase {
	public static boolean status = true;

	@Test
	public static void runTestSuit() throws IOException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException {
		String cur_dir = System.getProperty("user.dir");
		String suitePath = cur_dir + "/src/test/resources/TestSuite.xls";
		System.out.println(suitePath);
		String[][] recData = TestUtil.readXlSheet(suitePath, "Xero");
		extent = LogReport.startReport(cur_dir + "/ExtentLogReport/ExtentLogReport.html");

		String testCase, flag, firefoxStatus, chromeStatus, ieStatus;

		for (int i = 1; i < recData.length; i++) {
			testCase = recData[i][0];
			flag = recData[i][1];
			if (flag.equalsIgnoreCase("y")) {

				firefoxStatus = recData[i][2];
				chromeStatus = recData[i][4];
				ieStatus = recData[i][6];

				if (firefoxStatus.equalsIgnoreCase("y")) {

					loggers = LogReport.createTestReport(testCase + " in firefox", extent);
					Method tc = XeroTest.class.getMethod(testCase, String.class);
					tc.invoke(new XeroTest(), "firefox");
					if (status == true) {
						TestUtil.writeXlSheet(suitePath, "Xero", "pass", i, 3);
						TestUtil.setXlColorStyle(suitePath, "Xero", i, 3, "pass");
					} else {
						TestUtil.writeXlSheet(suitePath, "Xero", "fail", i, 3);
						TestUtil.setXlColorStyle(suitePath, "Xero", i, 3, "fail");
					}
				}
				if (chromeStatus.equalsIgnoreCase("y")) {
					Thread.sleep(5000);
					loggers = LogReport.createTestReport(testCase + " in chrome", extent);
					Method tc = XeroTest.class.getMethod(testCase, String.class);
					tc.invoke(new XeroTest(), "chrome");
					if (status == true) {
						TestUtil.writeXlSheet(suitePath, "Xero", "pass", i, 5);
						TestUtil.setXlColorStyle(suitePath, "Xero", i, 5, "pass");
					} else {
						TestUtil.writeXlSheet(suitePath, "Xero", "fail", i, 5);
						TestUtil.setXlColorStyle(suitePath, "Xero", i, 5, "fail");
					}
				}

				if (ieStatus.equalsIgnoreCase("y")) {
					loggers = LogReport.createTestReport(testCase + " in internet explorer", extent);
					Method tc = XeroTest.class.getMethod(testCase, String.class);
					tc.invoke(new XeroTest(), "ie");
					if (status == true) {
						TestUtil.writeXlSheet(suitePath, "Xero", "pass", i, 7);
						TestUtil.setXlColorStyle(suitePath, "Xero", i, 7, "pass");
					} else {
						TestUtil.writeXlSheet(suitePath, "Xero", "fail", i, 7);
						TestUtil.setXlColorStyle(suitePath, "Xero", i, 7, "fail");
					}
				}
			}
		}
		LogReport.endReport(extent);
	}
}
