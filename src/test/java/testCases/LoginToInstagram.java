package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.in.bymat.seleniumTraining.ExcelReader;

public class LoginToInstagram {

	public static WebDriver driver;
//	public static String browsername = "Chrome";
	public static Properties config;
	public static Properties or;
	public static ExcelReader excel;

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
		excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\Master_Sheet.xlsx");
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
	}

	@Test
	public static void loginToInstagram() throws Exception {

         String pageTitle =driver.getTitle();
         Assert.assertEquals(pageTitle, "Instagram");
         System.out.println("Page title is verified and it is "+pageTitle);
		driver.findElement(By.xpath(or.getProperty("UserName"))).sendKeys(excel.getCellData("Test_data", "Email_ID", 6));
		takeScreenshot();
		System.out.println("User has entered email address");
		Thread.sleep(3000);
		driver.findElement(By.xpath(or.getProperty("Pswd"))).sendKeys(excel.getCellData("Test_data", "Password", 6));
		takeScreenshot();
		System.out.println("User has entered password");
		Thread.sleep(3000);
		driver.findElement(By.xpath(or.getProperty("Login_btn"))).click();
		takeScreenshot();
		System.out.println("User has clicked on Login button");
		Thread.sleep(3000);
	}

	@Test
	public static void loginToInstagram2() throws Exception {

         String pageTitle =driver.getTitle();
         Assert.assertEquals(pageTitle, "Instagram");
         System.out.println("Page title is verified and it is "+pageTitle);
		driver.findElement(By.xpath(or.getProperty("UserName"))).sendKeys(excel.getCellData("Test_data", "Email_ID", 4));
		takeScreenshot();
		System.out.println("User has entered email address");
		Thread.sleep(3000);
		driver.findElement(By.xpath(or.getProperty("Pswd"))).sendKeys(excel.getCellData("Test_data", "Password", 4));
		takeScreenshot();
		System.out.println("User has entered password");
		Thread.sleep(3000);
		driver.findElement(By.xpath(or.getProperty("Login_btn"))).click();
		takeScreenshot();
		System.out.println("User has clicked on Login button");
		Thread.sleep(3000);
	}
	@Test
	public static void loginToInstagram3() throws Exception {

         String pageTitle =driver.getTitle();
         Assert.assertEquals(pageTitle, "Instagram");
         System.out.println("Page title is verified and it is "+pageTitle);
		driver.findElement(By.xpath(or.getProperty("UserName"))).sendKeys(excel.getCellData("Test_data", "Email_ID", 3));
		takeScreenshot();
		System.out.println("User has entered email address");
		Thread.sleep(3000);
		driver.findElement(By.xpath(or.getProperty("Pswd"))).sendKeys(excel.getCellData("Test_data", "Password", 3));
		takeScreenshot();
		System.out.println("User has entered password");
		Thread.sleep(3000);
		driver.findElement(By.xpath(or.getProperty("Login_btn"))).click();
		takeScreenshot();
		System.out.println("User has clicked on Login button");
		Thread.sleep(3000);
	}


	@AfterMethod
	public void closeBrowser() {

		driver.quit();

	}

	public static void takeScreenshot() throws Exception {

		
		String timeStamp=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		String screenshotPath = System.getProperty("user.dir")+"//src//test//resources//Screenshots//Screenshot" +timeStamp+ ".png";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(screenshotPath);
		FileUtils.copyFile(scrFile, destFile);

	}

}