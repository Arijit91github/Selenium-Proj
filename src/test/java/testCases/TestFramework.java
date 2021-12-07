package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import co.in.bymat.seleniumTraining.DataCollection;
import co.in.bymat.seleniumTraining.ExcelReader;

public class TestFramework {

	public static WebDriver driver;
//	public static String browsername = "Chrome";
	public static Properties config;
	public static Properties or;
	public static ExtentTest test;
	public static ExtentReports report;
	public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\Master_Sheet.xlsx");
	public static String timeStamp=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());

	@BeforeMethod
	public void setBrowser() throws IOException {
		
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Files\\Config.properties");
		config=new Properties();
		config.load(fis);
		System.out.println("Config file has been loaded");
		FileInputStream fis2=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Files\\OR.properties");
		or=new Properties();
		or.load(fis2);
		System.out.println("OR file has been loaded");
		report = new ExtentReports(System.getProperty("user.dir")+"\\src\\test\\resources\\ExecutionReport\\ExtentReportResults"+timeStamp+".html");
		test = report.startTest("CreateAnAccountToMyStore");
		if (config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (config.getProperty("Browser").equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\src\\test\\resources\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		else {
			System.setProperty("webdriver.edge.driver", ".\\src\\test\\resources\\Drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(config.getProperty("Application_URL"));
		System.out.println("User has navigated to the Application URL");
		test.log(LogStatus.PASS, "User has navigated to the Application URL");
	}


	@Test(dataProvider="dataCollection")
	public static void createAnAccountToMyStore(Hashtable<String,String>htData) throws Exception {

         String pageTitle =driver.getTitle();
         Assert.assertEquals(pageTitle, excel.getCellData("Test_data", "PageTitle", 2));
         System.out.println("Page title is verified and it is "+pageTitle);
         test.log(LogStatus.PASS, "Page title is verified and it is"+pageTitle);
		driver.findElement(By.xpath(or.getProperty("Sign_in"))).click();
		takeScreenshot();
		System.out.println("User has clicked sign_in button");
		test.log(LogStatus.PASS, "User has clicked sign_in button");
		takeScreenshot();
		Thread.sleep(3000);
		driver.findElement(By.xpath(or.getProperty("Email_address"))).sendKeys(excel.getCellData("Test_data", "Email_ID", 4));
		System.out.println("User has entered email address");
		test.log(LogStatus.PASS, "User has entered email address");
		takeScreenshot();
		Thread.sleep(3000);
		driver.findElement(By.xpath(or.getProperty("CreateAnAccount"))).click();
		System.out.println("User has clicked on Create Account button");
		test.log(LogStatus.PASS, "User has clicked on Create Account button");
		takeScreenshot();
		Thread.sleep(3000);
	}


	@AfterMethod
	public void closeBrowser() {

		driver.quit();
		System.out.println("Driver is closed");
		test.log(LogStatus.PASS,"Driver is closed");
		report.endTest(test);
		report.flush();

	}

	public static void takeScreenshot() throws Exception {

		
		String timeStamp=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		String screenshotPath = System.getProperty("user.dir")+"//src//test//resources//Screenshots//Screenshot" +timeStamp+ ".png";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(screenshotPath);
		FileUtils.copyFile(scrFile, destFile);
		test.log(LogStatus.PASS, test.addScreenCapture(screenshotPath));

	}
	
	@DataProvider
	public Object[][] dataCollection(){
		
		DataCollection dc=new DataCollection(excel, "Test_data", "CreateAnAccountToMyStore");
		return dc.dataArray();

	}

}

