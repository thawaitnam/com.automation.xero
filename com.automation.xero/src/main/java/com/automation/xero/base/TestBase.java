package com.automation.xero.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.automation.xero.module.LogReport;
import com.automation.xero.module.TestUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestBase {
	public static WebDriver driver;
	public static Properties propertyFile;
	public static ExtentTest loggers;
	public static ExtentReports extent;
	public static ExtentHtmlReporter htmlReporter;

	public TestBase() {
		try {
			propertyFile = new Properties();
			FileInputStream file;

			String cur_dir = System.getProperty("user.dir");
			System.out.println(cur_dir);
			String filepath = cur_dir + "/src/main/java/com/automation/xero/config/MyProperty.properties";
			file = new FileInputStream(filepath);
			System.out.println(file);
			propertyFile.load(file);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	public static WebDriver launchBrowser(String url, String browserName) {
	
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Num\\Google Drive\\NAMWorld\\QATester\\NamWorkPlace\\com.automation.xero\\src\\test\\resources\\chromedriver1.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		
		}

		else if (browserName.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\Num\\Google Drive\\NAMWorld\\QATester\\NamWorkPlace\\com.automation.xero\\src\\test\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();

		}

		else if (browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver",
					"C:\\Users\\Num\\Google Drive\\NAMWorld\\QATester\\NamWorkPlace\\com.automation.xero\\src\\test\\resources\\IEDriverServer1.exe");
			driver = new InternetExplorerDriver();
		}
		// driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.implicitly_wait, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.page_load_timeout, TimeUnit.SECONDS);

		if (url == "SignUPURL") {
			driver.get(propertyFile.getProperty("URL_SIGNUP"));

		}

		else {
			driver.get(propertyFile.getProperty("URL"));
			// driver.manage().window().maximize();
		}
		return driver;

	}

}
