package pages;

import java.util.Hashtable;

import Utility.CommonMethods;

public class PersonalInformationPage {
	
	public static void enterPersonalInformationPage(Hashtable<String, String> htData) throws Exception {
		
		CommonMethods.clickOnButton("Radio_btn", "Radio_btn");
		CommonMethods.enterTextIntoTextBoxTestDataSheet("First_Name", htData, "First_Name", "First_Name");
		CommonMethods.enterTextIntoTextBoxTestDataSheet("Last_Name", htData, "Last_Name", "Last_Name");
		CommonMethods.enterTextIntoTextBoxTestDataSheet("Password", htData, "Password", "Password");

	}

}
