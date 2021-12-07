package testCases2;

import java.util.Hashtable;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Utility.CommonMethods;
import testBase.BaseClass;

public class CreateAnAccountToMyStore4 extends BaseClass{
	@BeforeClass
	public void getClassName() {
		testcaseName = this.getClass().getSimpleName();
		System.out.println("Testcase name is " + testcaseName);
		if(htRunMode.get(testcaseName).equalsIgnoreCase("Y"))
			System.out.println("Executing Testcase "+testcaseName);
		else
			throw new SkipException("Skipping testcase "+testcaseName+" as its run mode is set to No");
	}

	@Test(dataProvider="dataCollection")
	public static void createAnAccountToMyStore4(Hashtable<String,String>htData) throws Exception {

		 CommonMethods.verifyPageTitle(	htData.get("PageTitle"));
         CommonMethods.clickOnButton("Sign_in", "Sign_in");
	     CommonMethods.enterTextIntoTextBoxTestDataSheet("Email_address", htData, "Email_ID", "Email Address");				
	     CommonMethods.clickOnButton("CreateAnAccount", "CreateAnAccount");
	     CommonMethods.clickOnButton("Radio_btn", "Radio_btn");	
	     CommonMethods.enterTextIntoTextBoxTestDataSheet("First_Name", htData, "First_Name", "First_Name");
	     CommonMethods.enterTextIntoTextBoxTestDataSheet("Last_Name", htData, "Last_Name", "Last_Name");
	     CommonMethods.enterTextIntoTextBoxTestDataSheet("Password", htData, "Password", "Password");
	}

}
