package testCases2;

import java.util.Hashtable;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.PersonalInformationPage;
import testBase.BaseClass;

public class CreateAnAccountToMyStore2 extends BaseClass {

	@BeforeClass
	public void getClassName() {
		testcaseName = this.getClass().getSimpleName();
		System.out.println("Testcase name is " + testcaseName);
	if(htRunMode.get(testcaseName).equalsIgnoreCase("Y"))
		System.out.println("Executing Testcase "+testcaseName);
	else
		throw new SkipException("Skipping testcase "+testcaseName+" as its run mode is set to No");
	}

	@Test(dataProvider = "dataCollection")
	public static void createAnAccountToMyStore2(Hashtable<String, String> htData) throws Exception {
     
		AuthenticationPage.createAnAccount(htData);
		PersonalInformationPage.enterPersonalInformationPage(htData);

	}
}
