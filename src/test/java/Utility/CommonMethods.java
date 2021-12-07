package Utility;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import testBase.BaseClass;

public class CommonMethods extends BaseClass {
	
	public static void clickOnButton(String Xpath, String buttonName) throws Exception
	{
		driver.findElement(By.xpath(or.getProperty(Xpath))).click();
 		System.out.println("User has clicked the "+buttonName+" button");
 		test.log(LogStatus.PASS, "User has clicked the "+buttonName+" button");
 		takeScreenshot();
		
	}

	public static void enterTextIntoTextBoxConfig(String Xpath, String input_data, String textBoxName) throws Exception {
		driver.findElement(By.xpath(or.getProperty(Xpath))).sendKeys(config.getProperty(input_data));
		System.out.println("User has entered " +textBoxName+ " address");
		test.log(LogStatus.PASS, "User has entered " +textBoxName+ " address");
		takeScreenshot();
	}
	
	public static void enterTextIntoTextBoxTestDataSheet(String Xpath, Hashtable<String,String>htData,String input_data,String textBoxName) throws Exception
	{
		driver.findElement(By.xpath(or.getProperty(Xpath))).sendKeys(htData.get(input_data));
		System.out.println("User has entered "+textBoxName);
		test.log(LogStatus.PASS, "User has entered "+textBoxName);
		takeScreenshot();	
	}
	public static void verifyPageTitle(String expectedResult) throws Exception {
		
		String pageTitle = driver.getTitle();
		Assert.assertEquals(pageTitle, expectedResult);
		System.out.println("Page title is verified and it is " + pageTitle);
		test.log(LogStatus.PASS, "Page title is verified and it is " + pageTitle);
		takeScreenshot();
	}
}
