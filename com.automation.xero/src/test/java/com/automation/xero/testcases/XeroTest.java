package com.automation.xero.testcases;

import java.io.IOException;

import javax.net.ssl.SSLEngineResult.Status;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.xero.base.TestBase;
import com.automation.xero.module.ActionModule;
import com.automation.xero.module.LogReport;
import com.automation.xero.module.VerificationModule;
import com.aventstack.extentreports.ExtentTest;

public class XeroTest extends TestBase {

	public static String cur_dir;

	public XeroTest() {
		super();

	}

	@BeforeMethod
	public void setup() throws InterruptedException {
		// driver=launchBrowser("SignUPURL");
		Thread.sleep(5000);
	}

	/*
	 * @DataProvider public Object[][] readData() throws IOException { //Object
	 * data[][]=TestUtil.readDataFromXl(propertyFile.getProperty(
	 * "TESTDATA_SHEETNAME")); return data; }
	 */

	@Test(priority = 1, enabled = false)
	@Parameters("browser")
	public void loginXero(String browserName) throws IOException, InterruptedException {

		driver = launchBrowser("URL", browserName);
		Thread.sleep(5000);
		cur_dir = System.getProperty("user.dir");
		extent = LogReport.startReport(cur_dir + "/ExtentLogReport/ExtentLogReport.html");

		loggers = LogReport.createTestReport("loginXero", extent);
		// loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case//

		/* Enter username in username field.. */
		WebElement username = driver.findElement(By.id("email"));
		ActionModule.enterText(username, propertyFile.getProperty("USERNAME"), "Username");

		/* Verify user name in text field */
		VerificationModule.validateTextBoxContent(username, propertyFile.getProperty("USERNAME"), "username");

		/* Enter password in password field.. */
		WebElement password = driver.findElement(By.id("password"));
		ActionModule.enterText(password, propertyFile.getProperty("PASSWORD"), "Password");

		/* Verify password in text field */
		VerificationModule.validateTextBoxContent(password, propertyFile.getProperty("PASSWORD"), "password");

		/* Click login button */
		WebElement loginButton = driver.findElement(By.xpath(".//*[@id='submitButton']"));
		ActionModule.clickObj(loginButton, "Login Button");

		/* Verify User's Home Page should be displayed */
		Thread.sleep(4000);
		VerificationModule.validatePageTitle("Xero | Dashboard | tekarch", "Xero Home Page", loggers);
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Pass");
		driver.quit();

	}

	@Test(priority = 2, enabled = false)
	@Parameters("browser")
	public void loginIncorrectPass(String browserName) throws InterruptedException {
		driver = launchBrowser("URL", browserName);
		Thread.sleep(5000);

		loggers = LogReport.createTestReport("loginIncorrectPass", extent);
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Start");

		/* Enter username in username field.. */
		WebElement username = driver.findElement(By.id("email"));
		ActionModule.enterText(username, propertyFile.getProperty("USERNAME"), "UserName");

		/* Verify user name in text field */
		VerificationModule.validateTextBoxContent(username, propertyFile.getProperty("USERNAME"), "username");

		/* Enter password in password field.. */
		WebElement password = driver.findElement(By.id("password"));
		ActionModule.enterText(password, "ghghgh", "Password");

		/* Verify password in text field */
		VerificationModule.validateTextBoxContent(password, "ghghgh", "password");

		/* Click login button */
		WebElement loginButton = driver.findElement(By.xpath(".//*[@id='submitButton']"));
		ActionModule.clickObj(loginButton, "Login Button");

		WebElement txtError = driver.findElement(By.xpath(".//*[@id='contentTop']/div[2]/div[1]/div[2]/p"));
		System.out.println(txtError.getText());
		VerificationModule.validateTextMessage(txtError, "Your email or password is incorrect", "ErrorMessage");

		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Pass");
		driver.quit();
		LogReport.endReport(extent);

	}

	@Test(priority = 3, enabled = false)
	@Parameters("browser")
	public void loginIncorrectEmail(String browserName) throws InterruptedException {
		driver = launchBrowser("URL", browserName);
		Thread.sleep(5000);
		loggers = LogReport.createTestReport("loginIncorrectEmail", extent);
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Start");

		/* Enter wrong username in username field.. */
		WebElement username = driver.findElement(By.id("email"));
		ActionModule.enterText(username, "thawait.bb@gmail.com", "UserName");

		/* Verify user name in text field */
		VerificationModule.validateTextBoxContent(username, "thawait.bb@gmail.com", "username");

		/* Enter password in password field.. */
		WebElement password = driver.findElement(By.id("password"));
		ActionModule.enterText(password, propertyFile.getProperty("PASSWORD"), "Password");

		/* Verify password in text field */
		VerificationModule.validateTextBoxContent(password, propertyFile.getProperty("PASSWORD"), "password");

		/* Click login button */
		WebElement loginButton = driver.findElement(By.xpath(".//*[@id='submitButton']"));
		ActionModule.clickObj(loginButton, "Login Button");

		WebElement txtError = driver.findElement(By.xpath(".//*[@id='contentTop']/div[2]/div[1]/div[2]/p"));
		VerificationModule.validateTextMessage(txtError, "Your email or password is incorrect", "ErrorMessage");
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Pass");
		driver.quit();
		LogReport.endReport(extent);

	}

	@Test(priority = 4, enabled = false)
	@Parameters("browser")
	public void forgotPassword(String browserName) throws IOException, InterruptedException {

		driver = launchBrowser("URL", browserName);
		Thread.sleep(5000);
		loggers = LogReport.createTestReport("forgotPassword", extent);
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Start");

		/* Click forgot password link */
		WebElement forgotpass = driver.findElement(By.xpath(".//*[@id='contentTop']/div[2]/div[1]/a"));
		ActionModule.clickObj(forgotpass, "Forgot Password");

		Thread.sleep(5000);
		/* Verify Xero forgot password page is displayed */
		VerificationModule.validatePageTitle("Forgotten Password", "Forgotten Password", loggers);

		/* Enter username in username field.. */
		WebElement username = driver.findElement(By.xpath(".//*[@id='UserName']"));
		ActionModule.enterText(username, propertyFile.getProperty("USERNAME"), "Username");

		/* Click forgot password link */
		WebElement sendlink = driver.findElement(By.xpath(".//*[@id='submitButton']/a"));
		// ActionModule.clickObj(forgotpass, "Send Link");

		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Pass");
		driver.quit();
		LogReport.endReport(extent);

	}

	@Test(priority = 6, enabled = false)
	@Parameters("browser")
	public void xdcSignup1(String browserName) throws IOException, InterruptedException {
		driver = launchBrowser("SignUPURL", browserName);
		Thread.sleep(5000);
		loggers = LogReport.createTestReport("xdcSignup1", extent);
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Start");

		/* Click on Free Trial */
		Thread.sleep(5000);
		WebElement freetrial = driver.findElement(By.xpath("//a[@class='btn btn-primary global-ceiling-bar-btn']"));
		ActionModule.clickObj(freetrial, "Free Trial");

		Thread.sleep(3000);
		WebElement freetrialMsg = driver.findElement(By.xpath("//div[@class='title-text']//h2"));

		/* Verify Free Trial message */
		VerificationModule.validateTextMessage(freetrialMsg, "30 day free trial", "Free Trial Message");

		/* Click on Get Started Button */
		/*
		 * WebElement getStartBtn = driver.findElement(By .xpath(
		 * "//button[@class='btn btn-primary btn-disabled']"));
		 * ActionModule.clickObj(getStartBtn, "Get Started");
		 */
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector(\"button[class='btn btn-primary btn-disabled']\").click()");

		/* Verify First Name can not be Empty */
		WebElement txtFirstNameError = driver.findElement(By.xpath(".//*[@id='signup-form-error-message-1']"));
		VerificationModule.validateTextMessage(txtFirstNameError, "First name can't be empty", "ErrorMessage");

		/* Verify Last Name can not be Empty */
		WebElement txtLastNameError = driver.findElement(By.xpath(".//*[@id='signup-form-error-message-2']"));
		VerificationModule.validateTextMessage(txtLastNameError, "Last name can't be empty", "ErrorMessage");

		/* Verify Email Address can not be Empty */
		WebElement txtEmailAddError = driver.findElement(By.xpath(".//*[@id='signup-form-error-message-3']"));
		VerificationModule.validateTextMessage(txtEmailAddError, "Email address can't be empty", "ErrorMessage");

		/* Verify Phone Number can not be Empty */
		WebElement txtPhnNumError = driver.findElement(By.xpath(".//*[@id='signup-form-error-message-4']"));
		VerificationModule.validateTextMessage(txtPhnNumError, "Phone number can't be empty", "ErrorMessage");

		/* Enter Email address in wron format */
		WebElement emailAdd = driver.findElement(By.xpath("//input[@name='EmailAddress']"));
		ActionModule.enterText(emailAdd, "bnbnnnb", "Username");

		/* Verify Email address is invalid */
		WebElement txtEmailError = driver.findElement(By.xpath(".//*[@id='signup-form-error-message-5']"));
		VerificationModule.validateTextMessage(txtEmailError, "Email address is invalid", "ErrorMessage");

		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Pass");
		driver.quit();
		LogReport.endReport(extent);
	}

	@Test(priority = 7, enabled = false)
	@Parameters("browser")
	public void xdcSignup2(String browserName) throws IOException, InterruptedException {
		driver = launchBrowser("SignUPURL", browserName);
		Thread.sleep(5000);
		loggers = LogReport.createTestReport("xdcSignup2", extent);
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Start");

		/* Click on Free Trial */
		Thread.sleep(5000);
		WebElement freetrial = driver.findElement(By.xpath("//a[@class='btn btn-primary global-ceiling-bar-btn']"));
		ActionModule.clickObj(freetrial, "Free Trial");

		Thread.sleep(3000);
		WebElement freetrialMsg = driver.findElement(By.xpath("//div[@class='title-text']//h2"));

		/* Verify Free Trial message */
		VerificationModule.validateTextMessage(freetrialMsg, "30 day free trial", "Free Trial Message");

		/* Click the terms of use link */
		Thread.sleep(5000);
		WebElement terms = driver.findElement(By.linkText("terms of use"));
		ActionModule.clickObj(terms, "Terms of use");

		// driver.switchTo().window("Xero Terms of Use | Xero US").close();
		String mainWin = driver.getWindowHandle();
		driver.switchTo().window(mainWin);
		Thread.sleep(5000);
		/* Click the terms of use link */

		WebElement privacy = driver.findElement(By.linkText("privacy policy"));
		ActionModule.clickObj(privacy, "privacy policy");
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Pass");
		driver.quit();
		LogReport.endReport(extent);

	}

	@Test(priority = 7, enabled = false)
	@Parameters("browser")
	public void xdcSignup3(String browserName) throws IOException, InterruptedException {

		driver = launchBrowser("SignUPURL", browserName);
		Thread.sleep(5000);
		loggers = LogReport.createTestReport("xdcSignup3", extent);
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Start");

		/* Click on Free Trial */
		Thread.sleep(5000);
		WebElement freetrial = driver.findElement(By.xpath("//a[@class='btn btn-primary global-ceiling-bar-btn']"));
		ActionModule.clickObj(freetrial, "Free Trial");

		Thread.sleep(3000);
		WebElement freetrialMsg = driver.findElement(By.xpath("//div[@class='title-text']//h2"));

		/* Verify Free Trial message */
		VerificationModule.validateTextMessage(freetrialMsg, "30 day free trial", "Free Trial Message");

		/* Click offer details link */
		Thread.sleep(5000);
		WebElement terms = driver.findElement(By.linkText("offer details"));
		ActionModule.clickObj(terms, "offer details");

		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Pass");
		driver.quit();
		LogReport.endReport(extent);
	}

	@Test(priority = 9, enabled = false)
	@Parameters("browser")
	public void xdcSignup4(String browserName) throws IOException, InterruptedException {
		driver = launchBrowser("SignUPURL", browserName);
		Thread.sleep(5000);
		loggers = LogReport.createTestReport("xdcSignup4", extent);
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Start");
		/* Click on Free Trial */
		Thread.sleep(5000);
		WebElement freetrial = driver.findElement(By.xpath("//a[@class='btn btn-primary global-ceiling-bar-btn']"));
		ActionModule.clickObj(freetrial, "Free Trial");

		Thread.sleep(3000);
		WebElement freetrialMsg = driver.findElement(By.xpath("//div[@class='title-text']//h2"));

		/* Verify Free Trial message */
		VerificationModule.validateTextMessage(freetrialMsg, "30 day free trial", "Free Trial Message");

		/* Click accountant or bookkeeper link */
		Thread.sleep(5000);
		WebElement terms = driver.findElement(By.linkText("accountant or bookkeeper"));
		ActionModule.clickObj(terms, "accountant or bookkeeper");

		/* Verify page Let’s get started displayed */
		WebElement txtPage = driver.findElement(By.xpath("//div[@class='component text text-center']//h2"));
		VerificationModule.validateTextMessage(txtPage, "Let’s get started", "Page");

		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Pass");
		driver.quit();
		LogReport.endReport(extent);

	}

	@Test(priority = 10, enabled = false)
	@Parameters("browser")
	public void testAllTabs(String browserName) throws IOException, InterruptedException {

		driver = launchBrowser("URL", browserName);
		Thread.sleep(5000);
		loggers = LogReport.createTestReport("testAllTabs", extent);
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Start");
		/* Enter username in username field.. */
		WebElement username = driver.findElement(By.id("email"));
		ActionModule.enterText(username, propertyFile.getProperty("USERNAME"), "Username");

		/* Verify user name in text field */
		VerificationModule.validateTextBoxContent(username, propertyFile.getProperty("USERNAME"), "username");

		/* Enter password in password field.. */
		WebElement password = driver.findElement(By.id("password"));
		ActionModule.enterText(password, propertyFile.getProperty("PASSWORD"), "Password");

		/* Verify password in text field */
		VerificationModule.validateTextBoxContent(password, propertyFile.getProperty("PASSWORD"), "password");

		/* Click login button */
		WebElement loginButton = driver.findElement(By.xpath(".//*[@id='submitButton']"));
		ActionModule.clickObj(loginButton, "Login Button");

		/* Verify User's Home Page should be displayed */
		Thread.sleep(4000);

		/* Click on DashBoard button */
		WebElement dashboardTab = driver.findElement(By.xpath(".//*[@id='Dashboard']"));
		ActionModule.clickObj(dashboardTab, "DashBoard Tab");

		/* Click on Account button */
		WebElement accountTab = driver.findElement(By.xpath(".//*[@id='Accounts']"));
		ActionModule.clickObj(accountTab, "Account Tab");

		/* Click on Patroll button */
		WebElement patrollTab = driver.findElement(By.xpath(".//*[@id='Payroll']"));
		ActionModule.clickObj(patrollTab, "Patroll Tab");

		/* Click on Patroll button */
		WebElement reportsTab = driver.findElement(By.xpath(".//*[@id='Reports']"));
		ActionModule.clickObj(reportsTab, "Reports Tab");

		/* Click on Contacts tab */
		WebElement contactsTab = driver.findElement(By.xpath(".//*[@id='Contacts']"));
		ActionModule.clickObj(contactsTab, "Contacts Tab");

		/* Click on Settings tab */
		WebElement settingsTab = driver.findElement(By.xpath(".//*[@id='Settings']"));
		ActionModule.clickObj(settingsTab, "Settings Tab");

		/* Click on Plus Icon */
		WebElement plusTab = driver.findElement(By.xpath(".//*[@id='quicklaunchTab']"));
		ActionModule.clickObj(plusTab, "Plus Icon");

		/* Click on File icon */
		WebElement fileIcon = driver.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[2]/div[2]/ul/li[2]/a"));
		ActionModule.clickObj(fileIcon, "File icon");

		/* Click on Notification Icon */
		WebElement notificationIcon = driver
				.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[2]/div[2]/ul/li[3]/a"));
		ActionModule.clickObj(notificationIcon, "Notification Icon");

		/* Click on Search icon */
		WebElement searchIcon = driver.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[2]/div[2]/ul/li[2]/a"));
		ActionModule.clickObj(searchIcon, "Search icon");

		/* Click on Help Icon */
		WebElement helpIcon = driver.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[2]/div[2]/ul/li[5]/a"));
		ActionModule.clickObj(helpIcon, "Help Icon");

		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Pass");
		driver.quit();
		LogReport.endReport(extent);

	}

	@Test(priority = 11, enabled = false)
	@Parameters("browser")
	public void xeroLogout(String browserName) throws IOException, InterruptedException {
		driver = launchBrowser("URL", browserName);
		Thread.sleep(5000);
		loggers = LogReport.createTestReport("xeroLogout", extent);
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Start");

		/* Enter username in username field.. */
		WebElement username = driver.findElement(By.id("email"));
		ActionModule.enterText(username, propertyFile.getProperty("USERNAME"), "Username");

		/* Verify user name in text field */
		VerificationModule.validateTextBoxContent(username, propertyFile.getProperty("USERNAME"), "username");

		/* Enter password in password field.. */
		WebElement password = driver.findElement(By.id("password"));
		ActionModule.enterText(password, propertyFile.getProperty("PASSWORD"), "Password");

		/* Verify password in text field */
		VerificationModule.validateTextBoxContent(password, propertyFile.getProperty("PASSWORD"), "password");

		/* Click login button */
		WebElement loginButton = driver.findElement(By.xpath(".//*[@id='submitButton']"));
		ActionModule.clickObj(loginButton, "Login Button");

		/* Verify User's Home Page should be displayed */
		Thread.sleep(4000);

		/* Click on User menu */
		WebElement clickmenu = driver.findElement(By.xpath("//a[@class='username']"));
		ActionModule.clickObj(clickmenu, "User Menu");

		/* Click on Logout from User menu */
		WebElement clickmenuItem = driver.findElement(By.linkText("Logout"));
		ActionModule.clickObj(clickmenuItem, "Logout");

		Thread.sleep(9000);
		WebElement txtMsg = driver.findElement(By.xpath("//h2[@class='x-boxed noBorder']"));
		VerificationModule.validateTextMessage(txtMsg, "Welcome to Xero", "Message");

		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Pass");
		driver.quit();
		LogReport.endReport(extent);

	}

	@Test(priority = 12, enabled = false)
	@Parameters("browser")
	public void testUpload(String browserName) throws IOException, InterruptedException {

		driver = launchBrowser("URL", browserName);
		Thread.sleep(5000);
		loggers = LogReport.createTestReport("testUpload", extent);
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Start");

		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Pass");
		driver.quit();
		LogReport.endReport(extent);
	}

	@Test(priority = 13, enabled = false)
	@Parameters("browser")
	public void addOrg1(String browserName) throws IOException, InterruptedException {
		driver = launchBrowser("URL", browserName);
		Thread.sleep(5000);
		loggers = LogReport.createTestReport("addOrg1", extent);
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Start");

		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Pass");
		driver.quit();
		LogReport.endReport(extent);
	}

	@Test(priority = 14, enabled = false)
	@Parameters("browser")
	public void addOrg2(String browserName) throws IOException, InterruptedException {
		driver = launchBrowser("URL", browserName);
		Thread.sleep(5000);
		loggers = LogReport.createTestReport("addOrg2", extent);
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Start");

		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Pass");
		driver.quit();
		LogReport.endReport(extent);
	}

	@Test(priority = 15, enabled = false)
	@Parameters("browser")
	public void addOrg3(String browserName) throws IOException, InterruptedException {

		driver = launchBrowser("URL", browserName);
		Thread.sleep(5000);
		loggers = LogReport.createTestReport("addOrg3", extent);
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Start");

		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Pass");
		driver.quit();
		LogReport.endReport(extent);
	}

	@Test(priority = 16, enabled = false)
	@Parameters("browser")
	public void addOrg4(String browserName) throws IOException, InterruptedException {

		driver = launchBrowser("URL", browserName);
		Thread.sleep(5000);
		loggers = LogReport.createTestReport("addOrg4", extent);
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Start");

		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Pass");
		driver.quit();
		LogReport.endReport(extent);
	}

	@Test(priority = 17, enabled = false)
	@Parameters("browser")
	public void addOrg5(String browserName) throws IOException, InterruptedException {

		driver = launchBrowser("URL", browserName);
		Thread.sleep(5000);
		loggers = LogReport.createTestReport("addOrg5", extent);
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Start");

		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Pass");
		driver.quit();
		LogReport.endReport(extent);
	}

	@Test(priority = 18, enabled = false)
	@Parameters("browser")
	public void addOrg6(String browserName) throws IOException, InterruptedException {

		driver = launchBrowser("URL", browserName);
		Thread.sleep(5000);
		loggers = LogReport.createTestReport("addOrg6", extent);
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Start");

		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Pass");
		driver.quit();
		LogReport.endReport(extent);
	}

	@Test(priority = 19, enabled = true)
	@Parameters("browser")
	public void userLookout(String browserName) throws IOException, InterruptedException {

		driver = launchBrowser("URL_SIGNUP", browserName);
		Thread.sleep(5000);
		loggers = LogReport.createTestReport("userLookout", extent);
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Start");
		
		/* Click login link */
		WebElement loginLink = driver.findElement(By.linkText("Login"));
		ActionModule.clickObj(loginLink, "Login Linl");
		
		/* Enter username in username field.. */
		WebElement username = driver.findElement(By.id("email"));
		ActionModule.enterText(username, propertyFile.getProperty("USERNAME"), "Username");

		/* Verify user name in text field */
		VerificationModule.validateTextBoxContent(username, propertyFile.getProperty("USERNAME"), "username");

		/* Enter password in password field.. */
		WebElement password = driver.findElement(By.id("password"));
		ActionModule.enterText(password, propertyFile.getProperty("PASSWORD"), "Password");

		/* Verify password in text field */
		VerificationModule.validateTextBoxContent(password, propertyFile.getProperty("PASSWORD"), "password");

		/* Click login button */
		WebElement loginButton = driver.findElement(By.xpath(".//*[@id='submitButton']"));
		ActionModule.clickObj(loginButton, "Login Button");

		/* Verify User's Home Page should be displayed */
		Thread.sleep(4000);
		VerificationModule.validatePageTitle("Xero | Dashboard | tekarch", "Xero Home Page", loggers);
	    
		
		/* Click on Account from Tab*/
		WebElement clickmenu = driver.findElement(By.xpath(".//*[@id='Accounts']"));
		ActionModule.clickObj(clickmenu, "Account Menu");

		/* Click on Purchases Tab */
		WebElement clickmenuItem = driver.findElement(By.linkText("Purchases"));
		ActionModule.clickObj(clickmenuItem, "Purchases");
	
		loggers.log(com.aventstack.extentreports.Status.INFO, "Test Case Pass");
		driver.quit();
		LogReport.endReport(extent);
	}

	@AfterMethod
	public void tearDown() {

	}

}
