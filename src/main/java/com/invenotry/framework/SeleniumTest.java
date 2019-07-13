package com.invenotry.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class SeleniumTest {
	
	static ExtentReports extent;
	static ExtentTest logger;
	static WebDriver driver;
	WebDriverWait wait;
	
	static String APPLICATION_URL = "http://localhost:8080/InventoryManagement/";
	
	@BeforeTest
	public void beforeTest() {
		extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/ExtentReport.html", true);
		extent.addSystemInfo("Reports Generated By: ", "Akshata Vernekar");
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\lib\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		wait = new WebDriverWait(driver, 10);
	}
	
	
	
	
	
	
	@Test
	void loginTest() throws IOException, InterruptedException{
		logger = extent.startTest("Login Test", "This Test is done for testing login functionality of a application");
		loginFunction();
	}
	
	@Test
	void logoutTest() throws IOException, InterruptedException{
		logger = extent.startTest("Logout Test", "This Test is done for testing logout functionality of a application");
		logoutFunction();
	}
	
	
	
	
	void loginFunction() throws IOException, InterruptedException{	
		driver.get(APPLICATION_URL);
		logger.log(LogStatus.PASS, "Successfully started application");		
		captureScreenshot();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).click();		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys("akshata");
		captureScreenshot();
		logger.log(LogStatus.PASS, "Successfully entered username");		
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).sendKeys("ak123");
		captureScreenshot();
		logger.log(LogStatus.PASS, "Successfully entered password");		
		
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='loginapp']//input[@value='Login']"))).click();
		captureScreenshot();
		logger.log(LogStatus.PASS, "Successfully clicked on log in");		
	}
	
	
	
	void logoutFunction() throws IOException, InterruptedException{
		loginFunction();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log Out"))).click();
		captureScreenshot();
		logger.log(LogStatus.PASS, "Successfully clicked on log out");
		
	}
	
	
	
	
	
	
	
	/*
	
	@Test
	public void facebookLogin()
	{
		logger = extent.startTest("Skip Test", "This Test is performed only to show how a skip test works");
		driver.get("https://www.facebook.com/");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
		//Add a log in the test if the url is successfully loaded.		
		String titileOfPage = driver.getTitle();
		Assert.assertEquals(titileOfPage, "Facebook  log in or sign up");
		logger.log(LogStatus.PASS, "The facebook title matches with expected");		
		//Add a log in the test to if email id	entered
		email.sendKeys("akshata@gmail.com");
		logger.log(LogStatus.PASS, "Email Id is entered successfully");		
		//Skip the test as I do not want to enter my password
		throw new SkipException("Skipped: I do not wish to enter my password ID");		
	}
	
	
	@Test
	public void gmailLoginFailed()
	{
		WebDriverWait wait = new WebDriverWait(driver, 6);
		logger = extent.startTest("Fail Test", "This Test is performed only to show how a fail test works");
		driver.get("https://www.google.co.in/");
		logger.log(LogStatus.PASS, "Successfully went to the google url");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Gmail"))).click();
		logger.log(LogStatus.PASS, "Clicked on gmail successfully");			
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("identifierId"))).sendKeys("akshatav10@gmail.com");
		logger.log(LogStatus.PASS, "Successfully entered email");	
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"identifierNext\"]/content/span"))).click();
		logger.log(LogStatus.PASS, "Clicked on Next after entering email successfully");	
		
		wait.until(ExpectedConditions.elementToBeClickable(By.name("password"))).sendKeys("wrongpassword");
		logger.log(LogStatus.PASS, "Successfully entered password");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"passwordNext\"]/content/span"))).click();
		logger.log(LogStatus.PASS, "Clicked on Next after entering password successfully");	
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'COMPOSE')]"))).click();
		logger.log(LogStatus.PASS, "Clicked on Compose mail successfully");	
	}
	
	
	@Test
	
	public void yahooSitePassd()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		logger = extent.startTest("Fail Test", "This Test is performed only to show how a passed test works");
		driver.get("https://in.yahoo.com/");		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("uh-search-box")));
		//Add a log in the test if the url is successfully loaded.		
		String titileOfPage = driver.getTitle();
		Assert.assertEquals(titileOfPage, "Yahoo");
		logger.log(LogStatus.PASS, "Visited the yahoo site successfully");	
	}*/
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException, InterruptedException {
		if(result.getStatus() == ITestResult.FAILURE)
		{
			captureScreenshot();
			logger.log(LogStatus.FAIL, "Test Case Failed is "+ result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is "+ result.getThrowable());
		}
		else if(result.getStatus() == ITestResult.SKIP)
		{
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
		extent.endTest(logger);
	}
	
	@AfterTest
	public void endReport()
	{
		extent.flush();
	    extent.close();
	    
	    driver.quit();
	}
	
	public static void captureScreenshot() throws IOException, InterruptedException
	   {
	        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        File screenshotLocation = new File(System.getProperty("user.dir")+"\\pic.png");
	        FileUtils.copyFile(screenshot, screenshotLocation);
	        Thread.sleep(2000);
	        InputStream is = new FileInputStream(screenshotLocation);
	        byte[] imageBytes = IOUtils.toByteArray(is);
	        Thread.sleep(2000);
	        String base64 = Base64.getEncoder().encodeToString(imageBytes);
	        logger.log(LogStatus.INFO, "Snapshot below: " + logger.addBase64ScreenShot("data:image/png;base64,"+base64));       
	   }


}
