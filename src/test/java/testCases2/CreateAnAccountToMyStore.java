package testCases2;

import java.util.Hashtable;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Utility.CommonMethods;
import testBase.BaseClass;

public class CreateAnAccountToMyStore extends BaseClass {
	@BeforeClass
	public void getClassName() {
		testcaseName = this.getClass().getSimpleName();
		System.out.println("Testcase name is " + testcaseName);

	}
	
	@Test(dataProvider="dataCollection")
	public static void createAnAccountToMyStore(Hashtable<String,String>htData) throws Exception {
		CommonMethods.verifyPageTitle(	htData.get("PageTitle"));
        CommonMethods.clickOnButton("Sign_in", "Sign_in");
		CommonMethods.enterTextIntoTextBoxTestDataSheet("Email_address", htData, "Email_ID", "Email_ID");
		CommonMethods.clickOnButton("CreateAnAccount", "CreateAnAccount");
		CommonMethods.clickOnButton("Radio_btn", "Radio_btn");
		CommonMethods.enterTextIntoTextBoxTestDataSheet("First_Name", htData, "First_Name", "First_Name");
		CommonMethods.enterTextIntoTextBoxTestDataSheet("Last_Name", htData, "Last_Name", "Last_Name");
		CommonMethods.enterTextIntoTextBoxTestDataSheet("Password", htData, "Password", "Password");
	}
}
