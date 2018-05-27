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
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

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

		String cur_dir = System.getProperty("user.dir");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", cur_dir + "/src/test/resources/chromedriver1.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();

		}

		else if (browserName.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", cur_dir + "/src/test/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			//FirefoxProfile profile=new FirefoxProfile();
			//profile.setAcceptUntrustedCertificates(true);
			
		}

		else if (browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", cur_dir + "/src/test/resources/IEDriverServer1.exe");
			driver = new InternetExplorerDriver();
			//DesiredCapabilities cap=DesiredCapabilities.internetExplorer();
			//cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			
			
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
