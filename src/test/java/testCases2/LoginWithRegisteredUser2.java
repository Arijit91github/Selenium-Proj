package testCases2;

import java.util.Hashtable;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Utility.CommonMethods;
import testBase.BaseClass;
public class LoginWithRegisteredUser2 extends BaseClass {
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
	public static void loginWithRegisteredUser2(Hashtable<String,String>htData) throws Exception {

		CommonMethods.verifyPageTitle(	htData.get("PageTitle"));
        CommonMethods.clickOnButton("Sign_in", "Sign_in_btn");
		CommonMethods.enterTextIntoTextBoxConfig("RegUserEmail", "Email_id", "RegUser Email_ID");
		CommonMethods.enterTextIntoTextBoxConfig("RegUserPassword", "Password", "RegUser Password");
		CommonMethods.clickOnButton("Sign_in_btn_RegUser", "RegUser Sign_in_btn");
	}
}
