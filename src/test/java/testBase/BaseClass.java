package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import co.in.bymat.seleniumTraining.DataCollection;
import co.in.bymat.seleniumTraining.ExcelReader;

public class BaseClass {

	public static WebDriver driver;
//	public static String browsername = "Chrome";
	public static Properties config;
	public static Properties or;
	public static ExtentTest test;
	public static ExtentReports report;
	public static ExcelReader excel;
	public static String testcaseName;
	public static Hashtable<String,String> htRunMode;

	@BeforeSuite
	public void initialization() throws IOException {
		FileInputStream fis = new FileInputStream(
	    System.getProperty("user.dir") + "\\src\\test\\resources\\Files\\Config.properties");
		config = new Properties();
		config.load(fis);
		System.out.println("Config file has been loaded");
		FileInputStream fis2 = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Files\\OR.properties");
		or = new Properties();
		or.load(fis2);
		System.out.println("OR file has been loaded");
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		report = new ExtentReports(System.getProperty("user.dir")
				+ "\\src\\test\\resources\\ExecutionReport\\ExtentReportResults" + timeStamp + ".html");
		excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\Master_Sheet.xlsx");
	    htRunMode=new  Hashtable<String,String>();
	     loadRunModeHashtable();
	}
	
	public void launchBrowser() throws IOException {

		test = report.startTest(testcaseName);
		if (config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			test.log(LogStatus.PASS, "Chrome has been launched");
		}
		else if (config.getProperty("Browser").equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\src\\test\\resources\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			test.log(LogStatus.PASS, "Firefox has been launched");
		}
		else {
			System.setProperty("webdriver.edge.driver", ".\\src\\test\\resources\\Drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
			test.log(LogStatus.PASS, "Edge has been launched");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(config.getProperty("Application_URL"));
		System.out.println("User has navigated to the Application URL");
		test.log(LogStatus.PASS, "User has navigated to the Application URL" + config.getProperty("Application_URL"));
	}

	public void closeBrowser() {

		driver.quit();
		System.out.println("Driver is closed");
		test.log(LogStatus.PASS, "Driver is closed");
	}

	@AfterSuite
	public void generateReport() {
		report.endTest(test);
		report.flush();

	}

	public static void takeScreenshot() throws Exception {

		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		String screenshotPath = System.getProperty("user.dir") + "//src//test//resources//Screenshots//Passed//Screenshot"
				+ timeStamp + ".png";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(screenshotPath);
		FileUtils.copyFile(scrFile, destFile);
		test.log(LogStatus.PASS, test.addScreenCapture(screenshotPath));

	}
	
	public static void takeScreenshot_failed() throws Exception {

		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		String screenshotPath = System.getProperty("user.dir") + "//src//test//resources//Screenshots//Failed//Screenshot"
				+ timeStamp + ".png";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(screenshotPath);
		FileUtils.copyFile(scrFile, destFile);
		test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));

	}
	
	@DataProvider
	public Object[][] dataCollection(){
		
		DataCollection dc=new DataCollection(excel, "Test_data", testcaseName);
		return dc.dataArray();

	}
	
	public void loadRunModeHashtable() {
		
		
		int rows=excel.getRowCount("TestCases");
		
		for(int i=2; i<=rows; i++) {
		
		String key=excel.getCellData("TestCases", "TestCaseName", i);
		String value=excel.getCellData("TestCases", "Run_Mode", i);
		htRunMode.put(key, value);
		System.out.println("HashTable runmode is " +htRunMode);
	}
	}
}
